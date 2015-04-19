package com.knowledgenet.knkpi

import grails.converters.JSON
import groovy.json.JsonSlurper
import org.springframework.security.access.annotation.Secured

@Secured([Role.ADMIN])
class KPIController {
    def netSuiteAccessorService
    def dataMassageService
    def springSecurityService

    @Secured([Role.USER])
    def index() {
        if (request.isUserInRole(Role.ADMIN)) {
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
                    manager.totalClosingPercentage = manager.totalClosingPercentage + it.closingPercentage
                    manager.totalDemos = manager.totalDemos + it.demos
                    manager.totalPipelineManagement = manager.totalPipelineManagement + it.pipelineManagement
                    manager.totalRevenueAttainment = manager.totalRevenueAttainment + it.revenueAttainment
                }
            }
            manager.totalClosingPercentage = manager.totalClosingPercentage / repCount
        }

        def salesRepsJson = salesReps as JSON
        def managersJson = managers as JSON

        [salesReps: salesRepsJson.toString(), managers: managers, managersJson: managersJson.toString(), dateFilter: dateFilter]
    }

    @Secured([Role.USER])
    def scoreCard(String repId) {
        List<SalesRep> salesReps

        if (!request.isUserInRole(Role.ADMIN)) {
            salesReps = [getUserSalesRep()]
        } else {
            //TODO: Can probably strip this down to only the fields we need
            salesReps = SalesRep.getAll()
        }

        [salesReps: salesReps, repId: repId]
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

        if (!request.isUserInRole(Role.ADMIN)) {
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

        def selectedRepJson = selectedRep as JSON

        render selectedRepJson.toString()
    }

    private SalesRep getUserSalesRep() {
        SalesRep retVal = null

        User user = springSecurityService.currentUser as User
        if (user) {
            retVal = SalesRep.findByUser(user)
        }

        return retVal
    }
}
