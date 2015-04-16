package com.knowledgenet.knkpi

import grails.converters.JSON
import groovy.json.JsonSlurper

class KPIController {
    def netSuiteAccessorService
    def dataMassageService

    def dashboard(String dateFilter) {
//        def viewInfo = new JsonSlurper().parseText(request.JSON.toString())
//        String dateFilter = viewInfo.dateFilter

        if (!dateFilter) {
            dateFilter = "thismonth"
        }

        List<SalesRep> salesReps = SalesRep.getAll()

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
            it.closingPercentage = it.revenueAttainment / it.pipelineManagement
            if (it.closingPercentage.isNaN()) {
                it.closingPercentage = 0
            }
        }

        salesReps.sort {
            it.managerId
        }

//       List<Map<String, String>> managers = [[:]]
//
//        salesReps.each {
//            if (!managers.size() == 0) {
//                if (!managers.contains(it.managerId)) {
//                    managers.add(managerId: it.managerId, managerName: it.managerName)
//                }
//            } else {
//                managers.add(managerId: it.managerId, managerName: it.managerName)
//            }
//        }

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

    def scoreCard(String repId) {
        //TODO: Can probably strip this down to only the fields we need
        List<SalesRep> salesReps = SalesRep.getAll()
        List selectReps = salesReps.asList()

        [salesReps: selectReps, repId: repId]
    }

    def getEmployee(String repId) {
        List<SalesRep> salesReps = SalesRep.findAllByRepId(repId)
        return salesReps
    }

    def getScoreCardRepData(String repId, String dateFilter) {
        SalesRep selectedRep = new SalesRep()

        if (!dateFilter) {
            dateFilter = "thismonth"
        }

        if (repId?.toInteger() > 0) {
            //This is a list because I wanted the existing dataMassageService methods to work with one or many reps.
            List<SalesRep> selectedReps = getEmployee(repId)

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

//    def getCallData() {
//        def viewInfo = new JsonSlurper().parseText(request.JSON.toString())
//        String dateFilter = viewInfo.dateFilter
//        List<SalesRep> salesReps = []
//        bindData(salesReps, viewInfo.salesReps)
//
//        if (!dateFilter) {
//            dateFilter = "thismonth"
//        }
//
//        log.info("Pulling Logged Calls for date range: ${dateFilter}.")
//        def nsCallData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.LOGGED_CALLS_SEARCH, null, NetSuiteUtil.LOGGED_CALLS_SEARCH_DATE, dateFilter)
//        dataMassageService.setLoggedCallsFields(salesReps, nsCallData)
//
//        def salesRepsJson = salesReps as JSON
//
//        [success: true, salesReps: salesReps.toString()]
//
//    }
}
