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

        log.info("Pulling Completed Demos for date range: ${dateFilter}.")
//        def demoData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.DEMO_COMPLETED_SEARCH, NetSuiteUtil.DEMO_COMPLETED_SEARCH_JOIN, NetSuiteUtil.DEMO_COMPLETED_SEARCH_DATE, dateFilter) as JSON

        log.info("Pulling Total Opportunities for date range: ${dateFilter}.")
//        def oppData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.TOTAL_OPPORTUNITIES_SEARCH, null, NetSuiteUtil.TOTAL_OPPORTUNITIES_SEARCH_DATE, dateFilter) as JSON

        log.info("Pulling Closed Sales for date range: ${dateFilter}.")
//        def salesData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.CLOSED_SALES_SEARCH, null, NetSuiteUtil.CLOSED_SALES_SEARCH_DATE, dateFilter) as JSON

        log.info("Pulling Logged Calls for date range: ${dateFilter}.")
//        def nsCallData = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.LOGGED_CALLS_SEARCH, null, NetSuiteUtil.LOGGED_CALLS_SEARCH_DATE, dateFilter) as JSON

        KPIStats kpiStats = new KPIStats()
        SalesRepIndicators salesRepIndicators = new SalesRepIndicators()

//        dataMassageService.setTotalOpportunitiesFields(kpiStats, salesRepIndicators, oppData)

 //       render oppData
    }

    def scoreCard() {

    }
}
