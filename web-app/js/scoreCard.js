
function updateScoreCard(data, dateFilter) {
    var metricMultiplier = 1;

    switch(dateFilter) {
        case "thisyear":
            metricMultiplier = 3;
            break;
        case "thisfiscalquarter":
            metricMultiplier = 2;
            break;
        default:
        metricMultiplier = 1;
    }

    $("#repStatsTable tbody").children().remove()
    $(".spinner").hide();

    rep = JSON.parse(data);

    $("#repTitle").html(rep.title);
    $("#repManager").html(rep.managerName);
    $("#repStartDate").html(rep.startDate);
    $("#repBirthDate").html(rep.birthDay);

//    $("#revenueAmount").html(rep.revenueAttainment);
//    $("#demoAmount").html(rep.demos);
//    $("#pipelineAmount").html(rep.pipelineManagement);
//    $("#closingPercentageAmount").html((rep.closingPercentage * 100).toFixed(2));

    var callSetting = JSON.parse(rep.callSetting);
    var demoSetting = JSON.parse(rep.demoSetting);
    var closingSetting = JSON.parse(rep.closingSetting);
    var pipelineSetting = JSON.parse(rep.pipelineSetting);
    var revenueSetting = JSON.parse(rep.revenueSetting);


    $tableId = $("#repStatsTable > tbody");

    var callGrade = grabGrade(rep.calls, callSetting);
    var callsCalc = ((rep.calls / (callSetting["A"] * metricMultiplier)) * 100).toFixed(0);
    $callData = "<tr><td>Calls</td><td>" + rep.calls + "</td><td>" + (callSetting["A"] * metricMultiplier) + "</td><td>" + callsCalc + "%</td><td>" + callGrade + "</td></tr>";

    var revenueGrade = grabGrade(rep.revenueAttainment, revenueSetting);
    var revenueCalc = ((rep.revenueAttainment / (revenueSetting["A"] * metricMultiplier)) * 100).toFixed(0);
    $revenueData = "<tr><td>Revenue</td><td>$" + rep.revenueAttainment.formatMoney(0, ".", ",") + "</td><td>$" + (revenueSetting["A"] * metricMultiplier).formatMoney(0, ".", ",") + "</td><td>" + revenueCalc + "%</td><td>" + revenueGrade + "</td></tr>";

    var demoGrade = grabGrade(rep.demos, demoSetting);
    var demoCalc = ((rep.demos / (demoSetting["A"] * metricMultiplier)) * 100).toFixed(0);
    $demoData = "<tr><td>Demo</td><td>" + rep.demos + "</td><td>" + (demoSetting["A"] * metricMultiplier) + "</td><td>" + demoCalc + "%</td><td>" + demoGrade + "</td></tr>";

    var pipelineGrade = grabGrade(rep.pipelineManagement, pipelineSetting);
    var pipelineCalc = ((rep.pipelineManagement / (pipelineSetting["A"] * metricMultiplier)) * 100).toFixed(0);
    $pipelineData = "<tr><td>Pipeline</td><td>$" + rep.pipelineManagement.formatMoney(0, ".", ",") + "</td><td>$" + (pipelineSetting["A"] * metricMultiplier).formatMoney(0, ".", ",") + "</td><td>" + pipelineCalc + "%</td><td>" + pipelineGrade + "</td></tr>";

    var closingGrade = grabGrade(rep.closingPercentage, closingSetting);
    var closingCalc = ((rep.closingPercentage / (closingSetting["A"] * metricMultiplier)) * 100).toFixed(0);
    $closingData = "<tr><td>Closing</td><td>" + (rep.closingPercentage * 100).toFixed(0) + "%</td><td>" + ((closingSetting["A"] * metricMultiplier) * 100).toFixed(0) + "%</td><td>" + closingCalc + "%</td><td>" + closingGrade + "</td></tr>";

    $allData = $callData + $revenueData + $demoData + $pipelineData + $closingData

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

function grabGrade(repValue, metrics) {
    var result = "";
    $.each(metrics, function(k, v) {
        if (repValue >= v) {
            result = k;
            return (false);
        } else if (k == "F" && repValue <= v) {
            result = k;
            return (false);
        }
    });

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