function initTeamScoreCard(manager, teamMembers, dateFilter, dayOfPeriod, totalDays) {
    var managerData = {
        callSetting: 0,
        demoSetting: 0,
        closingSetting: 0,
        pipelineSetting: 0,
        revenueSetting: 0
    };
    managerData.calls = manager.totalCalls;
    managerData.revenueAttainment = manager.totalRevenueAttainment;
    managerData.demos = manager.totalDemos;
    managerData.pipelineManagement = manager.totalPipelineManagement;
    managerData.closingPercentage = manager.totalClosingPercentage;

    teamMembers.forEach(function(teamMember) {
        managerData.callSetting += teamMember.callSetting;
        managerData.demoSetting += teamMember.demoSetting;
        managerData.closingSetting += teamMember.closingSetting;
        managerData.pipelineSetting += teamMember.pipelineSetting;
        managerData.revenueSetting += teamMember.revenueSetting;
    });

    var data = {};
    data.repData = managerData;
    data.dayOfPeriod = dayOfPeriod;
    data.totalDays = totalDays;

    updateScoreCard(data, dateFilter);
}