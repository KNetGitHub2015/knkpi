package com.knowledgenet.knkpi

import grails.transaction.Transactional

@Transactional
class DataMassageService {

    public Boolean setTotalOpportunitiesFields(List<SalesRep> salesReps, def json) {
        Boolean retVal = false

        if (json || salesReps) {
            for (i in json) {
                for (r in salesReps) {
                    if (r.repId == i.columns["salesrep"]?.internalid) {
                        r.pipelineManagement = r.pipelineManagement + i.columns["projectedtotal"]
                        break
                    }
                }
            }

            retVal = true
        }

        return retVal
    }

    public Boolean setClosedSalesFields(List<SalesRep> salesReps, def json) {
        Boolean retVal = false

        if (json || salesReps) {
            for (i in json) {
                for (r in salesReps) {
                    if (r.repId == i.columns["salesteammember"]?.internalid) {
                        r.revenueAttainment = r.revenueAttainment + i.columns["formulacurrency"]
                        break
                    }
                }
            }

            retVal = true
        }

        return retVal
    }

    public  Boolean setDemosCompletedFields(List<SalesRep> salesReps, def json) {
        Boolean retVal = false

        if (json || salesReps) {
            for (i in json) {
                for (r in salesReps) {
                    if (r.repId == i.columns["salesrep"]?.internalid) {
                        r.demos = r.demos + 1
                        break
                    }
                }
            }

            retVal = true
        }

        return retVal
    }

    public  Boolean setLoggedCallsFields(List<SalesRep> salesReps, def json) {
        Boolean retVal = false

        if (json || salesReps) {
            for (i in json) {
                for (r in salesReps) {
                    if (r.repId == i.columns["assigned"]?.internalid) {
                        r.calls = r.calls + 1
                        break
                    }
                }
            }

            retVal = true
        }

        return retVal
    }

    public SalesRep
}
