
function updateScoreCard(data) {
    $(".spinner").hide();
    rep = JSON.parse(data);
    $("#repInfo").html("Name: " + rep.repName);
}

function showSpinner() {
    $(".spinner").show();
}