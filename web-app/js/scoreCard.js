
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

    var callsCalc = ((rep.calls / (callSetting["A"] * metricMultiplier)) * 100).toFixed(2);
    $callData = "<tr><td>Calls</td><td>" + rep.calls + "</td><td>" + (callSetting["A"] * metricMultiplier) + "</td><td>" + callsCalc + "%</td></tr>";

    var revenueCalc = ((rep.revenueAttainment / (revenueSetting["A"] * metricMultiplier)) * 100).toFixed(2);
    $revenueData = "<tr><td>Revenue</td><td>" + rep.revenueAttainment + "</td><td>" + (revenueSetting["A"] * metricMultiplier) + "</td><td>" + revenueCalc + "%</td></tr>";

    var demoCalc = ((rep.demos / (demoSetting["A"] * metricMultiplier)) * 100).toFixed(2);
    $demoData = "<tr><td>Demo</td><td>" + rep.demos + "</td><td>" + (demoSetting["A"] * metricMultiplier) + "</td><td>" + demoCalc + "%</td></tr>";

    var pipelineCalc = ((rep.pipelineManagement / (pipelineSetting["A"] * metricMultiplier)) * 100).toFixed(2);
    $pipelineData = "<tr><td>Pipeline</td><td>" + rep.pipelineManagement + "</td><td>" + (pipelineSetting["A"] * metricMultiplier) + "</td><td>" + pipelineCalc + "%</td></tr>";

    var closingCalc = ((rep.closingPercentage / (closingSetting["A"] * metricMultiplier)) * 100).toFixed(2);
    $closingData = "<tr><td>Closing</td><td>" + (rep.closingPercentage * 100).toFixed(2) + "%</td><td>" + (closingSetting["A"] * metricMultiplier) + "%</td><td>" + closingCalc + "%</td></tr>";

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