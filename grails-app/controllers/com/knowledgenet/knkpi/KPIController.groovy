package com.knowledgenet.knkpi

import grails.converters.JSON
import groovy.json.JsonSlurper
import org.springframework.security.access.annotation.Secured

@Secured([Role.ADMIN, Role.MANAGER])
class KPIController {
    def netSuiteAccessorService
    def dataMassageService
    def springSecurityService

    @Secured([Role.USER])
    def index() {
        if (request.isUserInRole(Role.ADMIN) || request.isUserInRole(Role.MANAGER)) {
            forward action: 'admin'
        } else {
            SalesRep salesRep = getUserSalesRep()
            if (salesRep) {
                redirect controller: "KPI", action: "scoreCard", params: [repId: salesRep.repId]
            }
            forward action: 'user'
        }
    }

    def admin() {
    }

    def user() {
    }

    def dashboard(String dateFilter) {
        if (!dateFilter) {
            dateFilter = "thismonth"
        }

        DashboardData dashboardData = buildDashboardData(dateFilter)

        def salesRepsJson = dashboardData.salesReps as JSON
        def managersJson = dashboardData.managers as JSON

        int totalDays
        int dayOfPeriod
        switch (dateFilter) {
            case "thisfiscalquarter":
                totalDays = WorkDayUtil.totalDaysInQuarter()
                dayOfPeriod = WorkDayUtil.dayOfQuarter()
                break
            case "thisyear":
                totalDays = WorkDayUtil.totalDaysInYear()
                dayOfPeriod = WorkDayUtil.dayOfYear()
                break
            default:
                totalDays = WorkDayUtil.totalDaysInMonth()
                dayOfPeriod = WorkDayUtil.dayOfMonth()
                break
        }

        [salesReps: salesRepsJson.toString(), managers: dashboardData.managers, managersJson: managersJson.toString(), dateFilter: dateFilter, totalDays: totalDays, dayOfPeriod: dayOfPeriod]
    }

    @Secured([Role.USER])
    def scoreCard(String repId) {
        List<SalesRep> salesReps

        if (!request.isUserInRole(Role.ADMIN) && !request.isUserInRole(Role.MANAGER)) {
            salesReps = [getUserSalesRep()]
        } else {
            //TODO: Can probably strip this down to only the fields we need
            salesReps = SalesRep.getAll()
        }

        [salesReps: salesReps, repId: repId]
    }

    def teamScoreCard(String repId, String dateFilter) {
        if (!dateFilter) {
            dateFilter = "thismonth"
        }

        DashboardData dashboardData = buildDashboardData(dateFilter)

        Manager manager = dashboardData.managers.find { repId.equals(it.id) }
        List<SalesRep> teamMembers = dashboardData.salesReps.findAll {
            manager.id.equals(it.managerId)
        }

        int totalDays
        int dayOfPeriod
        switch (dateFilter) {
            case "thisfiscalquarter":
                totalDays = WorkDayUtil.totalDaysInQuarter()
                dayOfPeriod = WorkDayUtil.dayOfQuarter()
                break
            case "thisyear":
                totalDays = WorkDayUtil.totalDaysInYear()
                dayOfPeriod = WorkDayUtil.dayOfYear()
                break
            default:
                totalDays = WorkDayUtil.totalDaysInMonth()
                dayOfPeriod = WorkDayUtil.dayOfMonth()
                break
        }

        [repId: repId, manager: manager, teamMembers: teamMembers, allManagers: dashboardData.managers, dateFilter: dateFilter, totalDays: totalDays, dayOfPeriod: dayOfPeriod]
    }

    def getEmployee(String repId) {
        List<SalesRep> salesReps = SalesRep.findAllByRepId(repId)
        return salesReps
    }

//    def getRevenueSetting(List<SalesRep> salesReps, String repId) {
//        // Need to get pipeline setting from NS. Frequency of how often it is changed is unknown. Pulling it every time for now. This is turrrrrrble
//        def pipeLineSettings = netSuiteAccessorService.getSavedSearch()
//    }

    @Secured([Role.USER])
    def getScoreCardRepData(String repId, String dateFilter) {
        SalesRep selectedRep = new SalesRep()

        if (!request.isUserInRole(Role.ADMIN) && !request.isUserInRole(Role.MANAGER)) {
            SalesRep loggedInRep = getUserSalesRep()
            if (!loggedInRep.repId || loggedInRep.repId != repId) {
                return render([error: "Invalid rep id"] as JSON)
            }
        }

        if (!dateFilter) {
            dateFilter = "thismonth"
        }

        if (repId?.toInteger() > 0) {
            List<SalesRep> selectedReps = getEmployee(repId)

            dataMassageService.clearKPI(selectedReps)

            //TODO: This can be pulled out into a method that the dashboard can also use. ...This project started so clean, then deadlines killed everything. :(
            log.info("Pulling Completed Demos for date range: ${dateFilter}.")
            def demoData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.DEMO_COMPLETED_SEARCH, NetSuiteUtil.DEMO_COMPLETED_SEARCH_JOIN, NetSuiteUtil.DEMO_COMPLETED_SEARCH_DATE, dateFilter, NetSuiteUtil.DEMO_COMPLETED_SEARCH_REP, repId)
            dataMassageService.setDemosCompletedFields(selectedReps, demoData)

            log.info("Pulling Total Opportunities for date range: ${dateFilter}.")
            def oppData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.TOTAL_OPPORTUNITIES_SEARCH, null, NetSuiteUtil.TOTAL_OPPORTUNITIES_SEARCH_DATE, dateFilter, NetSuiteUtil.TOTAL_OPPORTUNITIES_SEARCH_REP, repId)
            dataMassageService.setTotalOpportunitiesFields(selectedReps, oppData)

            log.info("Pulling Closed Sales for date range: ${dateFilter}.")
            def salesData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.CLOSED_SALES_SEARCH, null, NetSuiteUtil.CLOSED_SALES_SEARCH_DATE, dateFilter, NetSuiteUtil.CLOSED_SALES_SEARCH_REP, repId)
            dataMassageService.setClosedSalesFields(selectedReps, salesData)

            log.info("Pulling Logged Calls for date range: ${dateFilter}.")
            def nsCallData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.LOGGED_CALLS_SEARCH, null, NetSuiteUtil.LOGGED_CALLS_SEARCH_DATE, dateFilter, NetSuiteUtil.LOGGED_CALLS_SEARCH_REP, repId)
            dataMassageService.setLoggedCallsFields(selectedReps, nsCallData)

            selectedReps.each {
                if (it.revenueSetting > 0) {
                    it.pipelineSetting = it.revenueSetting * 4
                }

                it.closingPercentage = it.revenueAttainment / it.pipelineManagement
                if (it.closingPercentage.isNaN()) {
                    it.closingPercentage = 0
                }
            }

            selectedRep = selectedReps.find {
                it.repId = repId
            }
        }

        int totalDays
        int dayOfPeriod
        switch (dateFilter) {
            case "thisfiscalquarter":
                totalDays = WorkDayUtil.totalDaysInQuarter()
                dayOfPeriod = WorkDayUtil.dayOfQuarter()
                break
            case "thisyear":
                totalDays = WorkDayUtil.totalDaysInYear()
                dayOfPeriod = WorkDayUtil.dayOfYear()
                break
            default:
                totalDays = WorkDayUtil.totalDaysInMonth()
                dayOfPeriod = WorkDayUtil.dayOfMonth()
                break
        }

        render([repData: selectedRep, totalDays: totalDays, dayOfPeriod: dayOfPeriod] as JSON)
    }

    private SalesRep getUserSalesRep() {
        SalesRep retVal = null

        User user = springSecurityService.currentUser as User
        if (user) {
            retVal = SalesRep.findByUser(user)
        }

        return retVal
    }

    private DashboardData buildDashboardData(String dateFilter) {
        DashboardData data = new DashboardData()

        List<SalesRep> salesReps = SalesRep.getAll()

        dataMassageService.clearKPI(salesReps)

        log.info("Pulling Completed Demos for date range: ${dateFilter}.")
        def demoData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.DEMO_COMPLETED_SEARCH, NetSuiteUtil.DEMO_COMPLETED_SEARCH_JOIN, NetSuiteUtil.DEMO_COMPLETED_SEARCH_DATE, dateFilter, null, null)
        dataMassageService.setDemosCompletedFields(salesReps, demoData)

        log.info("Pulling Total Opportunities for date range: ${dateFilter}.")
        def oppData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.TOTAL_OPPORTUNITIES_SEARCH, null, NetSuiteUtil.TOTAL_OPPORTUNITIES_SEARCH_DATE, dateFilter, null, null)
        dataMassageService.setTotalOpportunitiesFields(salesReps, oppData)

        log.info("Pulling Closed Sales for date range: ${dateFilter}.")
        def salesData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.CLOSED_SALES_SEARCH, null, NetSuiteUtil.CLOSED_SALES_SEARCH_DATE, dateFilter, null, null)
        dataMassageService.setClosedSalesFields(salesReps, salesData)

        log.info("Pulling Logged Calls for date range: ${dateFilter}.")
        def nsCallData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.LOGGED_CALLS_SEARCH, null, NetSuiteUtil.LOGGED_CALLS_SEARCH_DATE, dateFilter, null, null)
        dataMassageService.setLoggedCallsFields(salesReps, nsCallData)


        salesReps.each {
            if (it.revenueSetting > 0) {
                it.pipelineSetting = it.revenueSetting * 4
            }

            it.closingPercentage = it.revenueAttainment / it.pipelineManagement
            if (it.closingPercentage.isNaN()) {
                it.closingPercentage = 0
            }
        }

        salesReps.sort {
            it.managerId
        }

        List<Manager> managers = []
        def managerList = salesReps.managerId.unique()

        managerList.each {
            Manager manager = new Manager()
            manager.id = it
            SalesRep rep = salesReps.find { rep ->
                rep.managerId == it
            }

            manager.name = rep.managerName

            managers << manager
        }

        managers.each { manager ->
            int repCount = 0
            salesReps.each {
                if (manager.id == it.managerId) {
                    repCount = repCount + 1
                    manager.totalCalls = manager.totalCalls + it.calls
//                    manager.totalClosingPercentage = manager.totalClosingPercentage + it.closingPercentage
                    manager.totalDemos = manager.totalDemos + it.demos
                    manager.totalPipelineManagement = manager.totalPipelineManagement + it.pipelineManagement
                    manager.totalRevenueAttainment = manager.totalRevenueAttainment + it.revenueAttainment
                    manager.quota = manager.quota + it.revenueSetting
                }
            }
            manager.totalClosingPercentage = manager.totalRevenueAttainment / manager.totalPipelineManagement
        }

        data.salesReps = salesReps
        data.managers = managers

        return data
    }
}
