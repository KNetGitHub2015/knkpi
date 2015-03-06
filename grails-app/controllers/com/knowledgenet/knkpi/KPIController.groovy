package com.knowledgenet.knkpi

import grails.converters.JSON
import groovy.json.JsonSlurper
import jline.internal.Log

class KPIController {
    def netSuiteAccessorService

    def dashboard() {
        def viewInfo = new JsonSlurper().parseText(request.JSON.toString())
        String dateFilter = viewInfo.dateFilter

        if (!dateFilter) {
            dateFilter = "thismonth"
        }

        Log.info("Pulling Completed Demos for date range: ${dateFilter}.")
        def demo = netSuiteAccessorService.getSavedSearch(NetSuiteUtil.DEMO_COMPLETED_SEARCH, NetSuiteUtil.DEMO_COMPLETED_SEARCH_JOIN, NetSuiteUtil.DEMO_COMPLETED_SEARCH_DATE, dateFilter) as JSON

        render demo
    }

    def scoreCard() {

    }
}
