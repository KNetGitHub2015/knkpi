package com.knowledgenet.knkpi

import grails.transaction.Transactional

@Transactional
class DataMassageService {

    public static Boolean setTotalOpportunitiesFields(KPIStats kpiStats, SalesRepIndicators salesRepIndicators, def jsonData) {
        Boolean retVal = false

        if (kpiStats || salesRepIndicators) {

            kpiStats.buttsInSeats = 50
            retVal = true
        }

        return retVal
    }

    public static Boolean setClosedSalesFields(KPIStats kpiStats, SalesRepIndicators salesRepIndicators, def jsonData) {
        Boolean retVal = false

        if (kpiStats || salesRepIndicators) {


            retVal = true
        }

        return retVal
    }

    public static Boolean setDemosCompletedFields(KPIStats kpiStats, SalesRepIndicators salesRepIndicators, def jsonData) {
        Boolean retVal = false

        if (kpiStats || salesRepIndicators) {


            retVal = true
        }

        return retVal
    }

    public static Boolean setLoggedCallsFields(KPIStats kpiStats, SalesRepIndicators salesRepIndicators, def jsonData) {
        Boolean retVal = false

        if (kpiStats || salesRepIndicators) {


            retVal = true
        }

        return retVal
    }
}
