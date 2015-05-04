function dashBoardInit(managers, salesReps, scoreCardUrl, teamScoreCardUrl, dateFilter, dayOfPeriod, totalDays) {
    var metricMultiplier = 1;

    switch(dateFilter) {
        case "thisyear":
            metricMultiplier = 12;
            break;
        case "thisfiscalquarter":
            metricMultiplier = 3;
            break;
        default:
            metricMultiplier = 1;
    }

    for (var id in managers) {
        if (managers.hasOwnProperty(id)) {
            var managerTotal = managers[id];
            var managerRow = "<tr class='managerRow' id='manager" + managerTotal.id + "'></tr>";
            var managerTotalDiv = $('#manager' + managerTotal.id + ' > thead');
            managerTotalDiv.append(managerRow);

            managerTotal.callSetting = 0;
            managerTotal.revenueSetting = 0;
            managerTotal.demoSetting = 0;
            managerTotal.pipelineSetting = 0;
            managerTotal.closingSetting = 0;

            var $managerTableData = "<td class='repName managerName'><a href='" + teamScoreCardUrl + "?repId=" + managerTotal.id + "&dateFilter=" + dateFilter + "'>Team: " + managerTotal.name + "</a></td><td class='weighted-grade'></td><td class='calls'>" + managerTotal.totalCalls + "</td><td>$" + managerTotal.totalRevenueAttainment.formatMoney(0, ".", ",") + "</td><td>$" + managerTotal.quota.formatMoney(0, ".", ",") + "</td><td>" + ((managerTotal.totalRevenueAttainment / managerTotal.quota) * 100).toFixed(0) + "%</td><td>" + managerTotal.totalDemos + "</td><td>$" + managerTotal.totalPipelineManagement.formatMoney(0, ".", ",") + "</td><td>" + (managerTotal.totalClosingPercentage * 100).toFixed(0) + "%</td>";
            $("#manager" + managerTotal.id + " > thead").append($managerTableData);

        }
    }

    for (var repId in salesReps) {
        if (salesReps.hasOwnProperty(repId)) {
            var rep = salesReps[repId];
            var managerIndex = -1;
            for (var i = 0; i < managers.length; i++) {
                if (managers[i].id == rep.managerId) {
                    managerIndex = i;
                    break;
                }
            }

            var manager = managers[managerIndex];
            manager.callSetting += rep.callSetting;
            manager.revenueSetting += rep.revenueSetting;
            manager.demoSetting += rep.demoSetting;
            manager.pipelineSetting += rep.pipelineSetting;
            manager.closingSetting += rep.closingSetting;

            var $repRow = "<tr id='rep" + rep.repId + "' class='rep-row'></tr>";
            var managerDiv = $('#manager' + rep.managerId + ' > tbody');
            managerDiv.append($repRow);

            var callsWeighted = calcWeightedPercentage(rep.calls, rep.callSetting, metricMultiplier, dayOfPeriod, totalDays);
            var revenueWeighted = calcWeightedPercentage(rep.revenueAttainment, rep.revenueSetting, metricMultiplier, dayOfPeriod, totalDays);
            var demosWeighted = calcWeightedPercentage(rep.demos, rep.demoSetting, metricMultiplier, dayOfPeriod, totalDays);
            var pipelineWeighted = calcWeightedPercentage(rep.pipelineManagement, rep.pipelineSetting, metricMultiplier, dayOfPeriod, totalDays);
            var closingWeighted = calcWeightedPercentage(rep.closingPercentage, rep.closingSetting, metricMultiplier, dayOfPeriod, totalDays);

            var kpis = [
                {score: callsWeighted, weight: kpiWeights.calls},
                {score: revenueWeighted, weight: kpiWeights.revenue},
                {score: demosWeighted, weight: kpiWeights.demos},
                {score: pipelineWeighted, weight: kpiWeights.pipeline},
                {score: closingWeighted, weight: kpiWeights.closing}
            ];

            var score = rollupScores(kpis);
            var grade = grabGrade(score);

            var $tableData = "<td class='repName'><a href='" + scoreCardUrl + "?repId=" + rep.repId + "'>" + rep.repName + "</a></td><td class='weighted-grade' data-score='" + score + "'>" + grade + "</td><td class='calls'>" + rep.calls + "</td><td>$" + rep.revenueAttainment.formatMoney(0, ".", ",") + "</td><td>$" + rep.revenueSetting.formatMoney(0, ".", ",") + "</td><td>" + ((rep.revenueAttainment / rep.revenueSetting) * 100).toFixed(0) + "%</td><td>" + rep.demos + "</td><td>$" + rep.pipelineManagement.formatMoney(0, ".", ",") + "</td><td>" + (rep.closingPercentage * 100).toFixed(0) + "%</td>";
            $("#rep" + rep.repId + "").append($tableData);
        }
    }

    for (var managerId in managers) {
       if (managers.hasOwnProperty(managerId)) {
           var manager = managers[managerId];
           var callScore = calcWeightedPercentage(manager.totalCalls, manager.callSetting, metricMultiplier, dayOfPeriod, totalDays);
           var revenueScore = calcWeightedPercentage(manager.totalRevenueAttainment, manager.revenueSetting, metricMultiplier, dayOfPeriod, totalDays);
           var demoScore = calcWeightedPercentage(manager.totalDemos, manager.demoSetting, metricMultiplier, dayOfPeriod, totalDays);
           var pipelineScore = calcWeightedPercentage(manager.totalPipelineManagement, manager.pipelineSetting, metricMultiplier, dayOfPeriod, totalDays);
           var closingScore = calcWeightedPercentage(manager.totalClosingPercentage, manager.closingSetting, metricMultiplier, dayOfPeriod, totalDays);

           var managerKpis = [
               {score: callScore, weight: kpiWeights.calls},
               {score: revenueScore, weight: kpiWeights.revenue},
               {score: demoScore, weight: kpiWeights.demos},
               {score: pipelineScore, weight: kpiWeights.pipeline},
               {score: closingScore, weight: kpiWeights.closing}
           ];

           var managerScore = rollupScores(managerKpis);
           var managerGrade = grabGrade(managerScore);
           $('#manager' + manager.id + ' > thead .weighted-grade').html(managerGrade);
       }
    }


    $(document).ready(function () {
//        $.ajax({
//            type: "post",
//            url: "getCallData",
//            dataType: "json",
//            data: JSON.stringify({salesReps: salesReps, dateFilter: dateFilter}),
//            contentType: "application/json",
//            success: function(data) {
//                if (data.success) {
//                    for (var repId in data.salesReps) {
//                        if (data.salesReps.hasOwnProperty(repId)) {
//                            $("#rep" +rep.repId + " >.class").append(rep.calls);
//                        }
//                    }
//                } else {
//                    alert ("Unable to load Call Data.");
//                }
//            },
//            error: function() {
//                alert ("USomething went wrong trying to load Call Data.");
//            }
//        });
    });

//    $(".expandableTable").click(function () {
//        var $this = $(this);
//        if ($this.find(".teamTable").is(':visible')) {
//            $this.find('table.teamTable').css('background-position', '0 0');
//        } else {
//            $this.find('table.teamTable').css('background-position', '0 -12px');
//        }
//        $this.find(".teamTable").slideToggle("slow");
//    });

}

Number.prototype.formatMoney = function(c, d, t){
    var n = this,
        c = isNaN(c = Math.abs(c)) ? 2 : c,
        d = d == undefined ? "." : d,
        t = t == undefined ? "," : t,
        s = n < 0 ? "-" : "",
        i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "",
        j = (j = i.length) > 3 ? j % 3 : 0;
    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
};



