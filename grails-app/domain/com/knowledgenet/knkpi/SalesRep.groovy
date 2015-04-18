package com.knowledgenet.knkpi

class SalesRep {
    String repId
    String repName
    String title
    String managerName
    String managerId
    String startDate
    String birthDay
    Double revenueSetting = 30000
    Integer callSetting = 900
    Integer demoSetting = 20
    Double pipelineSetting = 0
    Double closingSetting = 0.25
    Double revenueAttainment = 0
    Integer calls = 0
    Integer demos = 0
    Double pipelineManagement = 0
    Double closingPercentage = 0
    Double forecastingAccuracy = 0

    static constraints = {
        title (nullable: true)
        startDate (nullable: true)
        birthDay (nullable: true)
    }
}
