package com.knowledgenet.knkpi

class SalesRep {
    String repId
    String repName
    String title
    String managerName
    String managerId
    String startDate
    String birthDay
    String revenueSetting = '{"A": "15000", "B":"10000", "C":"8000", "D":"5000", "F":"3000"}'
    String callSetting = '{"A":"900", "B":"800", "C":"700", "D":"600","F":"500"}'
    String demoSetting = '{"A": "20", "B": "15", "C": "10", "D": "7", "F": "5"}'
    String pipelineSetting = '{"A":"100000", "B":" 80000", "C": "60000", "D": "40000", "F": "30000"}'
    String closingSetting = '{"A": "0.40", "B":"0.30", "C": "0.25", "D": "0.20", "F": "0.15"}'
    Double revenueAttainment = 0
    Integer calls = 0
    Integer demos = 0
    Double pipelineManagement = 0
    Double closingPercentage = 0
    Double forecastingAccuracy = 0

    static constraints = {
        repId (nullable: true)
        repName (nullable: true)
        title (nullable: true)
        managerName (nullable: true)
        managerId (nullable: true)
        startDate (nullable: true)
        birthDay (nullable: true)
    }
}
