package com.knowledgenet.knkpi

class SalesRepIndicators {
    String grade //ENUM?
    Double revenueAttainment = 0
    Integer calls = 0
    Integer demos = 0
    Double pipelineManagement = 0
    Double closingPercentage = 0
    Double forecastingAccuracy

    //These atm are set by the Setting domain class on use
    String revenueSetting
    String callSetting
    String demoSetting
    String pipelineSetting
    String closingSetting


}
