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

        log.info("Pulling Logged Calls for date range: ${dateFilter}.")
        def nsCallData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.LOGGED_CALLS_SEARCH, null, NetSuiteUtil.LOGGED_CALLS_SEARCH_DATE, dateFilter)
        dataMassageService.setLoggedCallsFields(salesReps, nsCallData)

        salesReps.each {
            it.closingPercentage = it.revenueAttainment / it.pipelineManagement
        }

        KPIStats kpiStats = new KPIStats()
        SalesRepIndicators salesRepIndicators = new SalesRepIndicators()

//        dataMassageService.setTotalOpportunitiesFields(kpiStats, salesRepIndicators, oppData)

 //       render oppData
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
}
