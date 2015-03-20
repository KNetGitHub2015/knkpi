package com.knowledgenet.knkpi

import grails.converters.JSON
import groovy.json.JsonSlurper

class KPIController {
    def netSuiteAccessorService
    def dataMassageService

    def dashboard() {
        def viewInfo = new JsonSlurper().parseText(request.JSON.toString())
        String dateFilter = viewInfo.dateFilter

        if (!dateFilter) {
            dateFilter = "thismonth"
        }

        log.info("Pulling Employees...")
        List<SalesRep> salesReps = getEmployees()

        log.info("Pulling Completed Demos for date range: ${dateFilter}.")
        def demoData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.DEMO_COMPLETED_SEARCH, NetSuiteUtil.DEMO_COMPLETED_SEARCH_JOIN, NetSuiteUtil.DEMO_COMPLETED_SEARCH_DATE, dateFilter)
        dataMassageService.setDemosCompletedFields(salesReps, demoData)

        log.info("Pulling Total Opportunities for date range: ${dateFilter}.")
        def oppData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.TOTAL_OPPORTUNITIES_SEARCH, null, NetSuiteUtil.TOTAL_OPPORTUNITIES_SEARCH_DATE, dateFilter)
        dataMassageService.setTotalOpportunitiesFields(salesReps, oppData)

        log.info("Pulling Closed Sales for date range: ${dateFilter}.")
        def salesData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.CLOSED_SALES_SEARCH, null, NetSuiteUtil.CLOSED_SALES_SEARCH_DATE, dateFilter)
        dataMassageService.setClosedSalesFields(salesReps, salesData)


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

        def salesRepsJson = salesReps as JSON

        //TODO: converting salesReps to jsonMap

        [salesReps: salesRepsJson.toString(), managers: managers]
    }

    def getEmployees() {
        List<SalesRep> salesReps = []
        def employees = netSuiteAccessorService.getEmployees()

        employees.each {
            SalesRep rep = new SalesRep()
            rep.repId = it.id
            rep.repName = it.columns["entityid"]
            rep.title = it.columns["title"]
            rep.managerName = it.columns["supervisor"].name
            rep.managerId = it.columns["supervisor"].internalid


            salesReps << rep
        }

        return salesReps
    }

    def scoreCard() {

    }

    def getCallData() {
        def viewInfo = new JsonSlurper().parseText(request.JSON.toString())
        String dateFilter = viewInfo.dateFilter
        List<SalesRep> salesReps = []
        bindData(salesReps, viewInfo.salesReps)

        if (!dateFilter) {
            dateFilter = "thismonth"
        }

        log.info("Pulling Logged Calls for date range: ${dateFilter}.")
        def nsCallData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.LOGGED_CALLS_SEARCH, null, NetSuiteUtil.LOGGED_CALLS_SEARCH_DATE, dateFilter)
        dataMassageService.setLoggedCallsFields(salesReps, nsCallData)

        [salesReps: salesReps]

    }
}
