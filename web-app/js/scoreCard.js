
function updateScoreCard(data) {
    $("#repStatsTable tbody").children().remove()
    $(".spinner").hide();

    rep = JSON.parse(data);

    $("#repTitle").html(rep.title);
    $("#repManager").html(rep.managerName);
    $("#repStartDate").html(rep.startDate);
    $("#repBirthDate").html(rep.birthDate);

//    $("#revenueAmount").html(rep.revenueAttainment);
//    $("#demoAmount").html(rep.demos);
//    $("#pipelineAmount").html(rep.pipelineManagement);
//    $("#closingPercentageAmount").html((rep.closingPercentage * 100).toFixed(2));

    var demoSetting = JSON.parse(rep.demoSetting);
    var closingSetting = JSON.parse(rep.closingSetting);
    var pipelineSetting = JSON.parse(rep.pipelineSetting);
    var revenueSetting = JSON.parse(rep.revenueSetting);


    $tableId = $("#repStatsTable > tbody");

    var revenueCalc = ((rep.revenueAttainment / revenueSetting["A"]) * 100).toFixed(2);
    $revenueData = "<tr><td>Revenue</td><td>" + rep.revenueAttainment + "</td><td>" + revenueSetting["A"] + "</td><td>" + revenueCalc + "</td></tr>";

    var demoCalc = ((rep.demos / demoSetting["A"]) * 100).toFixed(2);
    $demoData = "<tr><td>Demo</td><td>" + rep.demos + "</td><td>" + demoSetting["A"] + "</td><td>" + demoCalc + "</td></tr>";

    var pipelineCalc = ((rep.pipelineManagement / pipelineSetting["A"]) * 100).toFixed(2);
    $pipelineData = "<tr><td>Pipeline</td><td>" + rep.pipelineManagement + "</td><td>" + pipelineSetting["A"] + "</td><td>" + pipelineCalc + "</td></tr>";

    var closingCalc = ((rep.closingPercentage / closingSetting["A"]) * 100).toFixed(2);
    $closingData = "<tr><td>Closing</td><td>" + (rep.closingPercentage * 100).toFixed(2) + "</td><td>" + closingSetting["A"] + "</td><td>" + closingCalc + "</td></tr>";

    $allData = $revenueData + $demoData + $pipelineData + $closingData

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