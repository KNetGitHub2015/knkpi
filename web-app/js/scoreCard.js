
function updateScoreCard(data, dateFilter) {
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

    $("#repStatsTable tbody").children().remove()
    $(".spinner").hide();

    rep = data.repData;

    var dayOfMonth = data.dayOfMonth;
    var totalDays = data.totalDays;

    $("#repTitle").html(rep.title);
    $("#repManager").html(rep.managerName);
    $("#repStartDate").html(rep.startDate);
    $("#repBirthDate").html(rep.birthDay);

//    $("#revenueAmount").html(rep.revenueAttainment);
//    $("#demoAmount").html(rep.demos);
//    $("#pipelineAmount").html(rep.pipelineManagement);
//    $("#closingPercentageAmount").html((rep.closingPercentage * 100).toFixed(2));

    var callSetting = rep.callSetting;
    var demoSetting = rep.demoSetting;
    var closingSetting = rep.closingSetting;
    var pipelineSetting = rep.pipelineSetting;
    var revenueSetting = rep.revenueSetting;


    $tableId = $("#repStatsTable > tbody");

    var callPercentage = calcPercentage(rep.calls, callSetting, metricMultiplier);
    var callWeightedPercentage = calcWeightedPercentage(rep.calls, callSetting, metricMultiplier, dayOfMonth, totalDays);
    var callGrade = grabGrade(callPercentage);
    var callWeightedGrade = grabGrade(callWeightedPercentage);
    var callsCalc = (callPercentage * 100).toFixed(0);
    $callData = "<tr><td>Calls</td><td>" + rep.calls + "</td><td>" + (callSetting * metricMultiplier) + "</td><td>" + callsCalc + "%</td><td>" + callGrade + "</td><td>" + callWeightedGrade + "</td></tr>";

    var revenuePercentage = calcPercentage(rep.revenueAttainment, revenueSetting, metricMultiplier);
    var revenueWeightedPercentage = calcWeightedPercentage(rep.revenueAttainment, revenueSetting, metricMultiplier, dayOfMonth, totalDays);
    var revenueGrade = grabGrade(revenuePercentage);
    var revenueWeightedGrade = grabGrade(revenueWeightedPercentage);
    var revenueCalc = (revenuePercentage * 100).toFixed(0);
    $revenueData = "<tr><td>Revenue</td><td>$" + rep.revenueAttainment.formatMoney(0, ".", ",") + "</td><td>$" + (revenueSetting * metricMultiplier).formatMoney(0, ".", ",") + "</td><td>" + revenueCalc + "%</td><td>" + revenueGrade + "</td><td>" + revenueWeightedGrade + "</td></tr>";

    var demoPercentage = calcPercentage(rep.demos, demoSetting, metricMultiplier);
    var demoWeightedPercentage = calcWeightedPercentage(rep.demos, demoSetting, metricMultiplier, dayOfMonth, totalDays);
    var demoGrade = grabGrade(demoPercentage);
    var demoWeightedGrade = grabGrade(demoWeightedPercentage);
    var demoCalc = (demoPercentage * 100).toFixed(0);
    $demoData = "<tr><td>Demo</td><td>" + rep.demos + "</td><td>" + (demoSetting * metricMultiplier) + "</td><td>" + demoCalc + "%</td><td>" + demoGrade + "</td><td>" + demoWeightedGrade + "</td></tr>";

    var pipelinePercentage = calcPercentage(rep.pipelineManagement, pipelineSetting, metricMultiplier);
    var pipelineWeightedPercentage = calcWeightedPercentage(rep.pipelineManagement, pipelineSetting, metricMultiplier, dayOfMonth, totalDays);
    var pipelineGrade = grabGrade(pipelinePercentage);
    var pipelineWeightedGrade = grabGrade(pipelineWeightedPercentage);
    var pipelineCalc = (pipelinePercentage * 100).toFixed(0);
    $pipelineData = "<tr><td>Pipeline</td><td>$" + rep.pipelineManagement.formatMoney(0, ".", ",") + "</td><td>$" + (pipelineSetting * metricMultiplier).formatMoney(0, ".", ",") + "</td><td>" + pipelineCalc + "%</td><td>" + pipelineGrade + "</td><td>" + pipelineWeightedGrade + "</td></tr>";

    var closingPercentage = calcPercentage(rep.closingPercentage, closingSetting, metricMultiplier);
    var closingWeightedPercentage = calcWeightedPercentage(rep.closingPercentage, closingSetting, metricMultiplier, dayOfMonth, totalDays);
    var closingGrade = grabGrade(closingPercentage);
    var closingWeightedGrade = grabGrade(closingWeightedPercentage);
    var closingCalc = (closingPercentage * 100).toFixed(0);
    $closingData = "<tr><td>Closing</td><td>" + (rep.closingPercentage * 100).toFixed(0) + "%</td><td>" + ((closingSetting * metricMultiplier) * 100).toFixed(0) + "%</td><td>" + closingCalc + "%</td><td>" + closingGrade + "</td><td>" + closingWeightedGrade + "</td></tr>";

    $allData = $callData + $revenueData + $demoData + $pipelineData + $closingData;

    $tableId.append($allData);


}

function showSpinner() {
    $(".spinner").show();
}

function redirectedRep(repId) {
    $("#salesRep").val(repId);
    $("#salesRep").change();
}

function getDateFilter() {
    return $("#duration:checked").val();
}

function getSalesRepValue() {
    return $("#salesRep").val();
}

function calcPercentage(actual, goal, multiplier) {
    return (actual / (goal * multiplier));
}

function calcWeightedPercentage(actual, goal, multiplier, dayOfMonth, totalDays) {
    var weightedGoal = (goal / totalDays) * dayOfMonth;

    return calcPercentage(actual, weightedGoal, multiplier);
}

function grabGrade(percentage) {
    var result = "F";

    if (percentage != null) {
        if (percentage >= 0.9) {
            result = "A"
        } else if (percentage >= 0.8) {
            result = "B"
        } else if (percentage >= 0.7) {
            result = "C"
        } else if (percentage >= 0.6) {
            result = "D"
        }
    }

    return result;
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


//TODO: Need to use this to fix the dateFilter switch bug
//function changedFilter(repId) {
//    console.log("entered changedFilter");
//    $.ajax({
//        url: "getScoreCardRepData",
//        type:"post",
//        data: 'repId=' + repId + '&dateFilter=' + getDateFilter(),
//        success: function(data) {
//            updateScoreCard(data);
//        },
//        error: function(xhr){
//            alert(xhr.responseText); //<----when no data alert the err msg
//        }
//    });
//}