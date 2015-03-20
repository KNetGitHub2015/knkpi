function dashBoardInit(salesReps) {

    for (var repId in salesReps) {
        if (salesReps.hasOwnProperty(repId)) {
            var rep = salesReps[repId];
            var manager = rep.managerId;

            var $repRow = "<tr id='rep" + rep.repId + "'></tr>";
            var managerDiv = $('#manager' + rep.managerId + ' > tbody');
            managerDiv.append($repRow);

            var $tableData = "<td>" + rep.repName + "</td><td class='calls'>" + rep.calls + "</td><td>" + rep.revenueAttainment + "</td><td>" + rep.demos + "</td><td>" + rep.pipelineManagement + "</td><td>" + (rep.closingPercentage * 100).toFixed(2); + "%</td>";
            $("#rep" + rep.repId + "").append($tableData);
        }
    }

    $( document ).ready(function() {
//        $.ajax({
//            type: "post",
//            url: "getCallData",
//            dataType: "json",
//            data: JSON.stringify({order: opportunityCartOrder, card: opportunityCartCardInfo}),
//            contentType: "application/json",
//            success: function(data) {
//                if (data.success) {
//
//                } else {
//
//                }
//            },
//            error: function() {
//
//            }
//        });
        alert("Loaded");
    });
}



