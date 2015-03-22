function dashBoardInit(salesReps) {

    for (var repId in salesReps) {
        if (salesReps.hasOwnProperty(repId)) {
            var rep = salesReps[repId];
            var manager = rep.managerId;

            var $repRow = "<tr id='rep" + rep.repId + "'></tr>";
            var managerDiv = $('#manager' + rep.managerId + ' > tbody');
            managerDiv.append($repRow);

            var $tableData = "<td>" + rep.repName + "</td><td class='calls'>" + rep.calls + "</td><td>" + rep.revenueAttainment + "</td><td>" + rep.demos + "</td><td>" + rep.pipelineManagement + "</td><td>" + (rep.closingPercentage * 100).toFixed(2);
            +"%</td>";
            $("#rep" + rep.repId + "").append($tableData);
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
        alert("Loaded");
    });

    $(".expandableTable").click(function () {
        var $this = $(this);
//        if ($this.find(".teamTable").is(':visible')) {
//            $this.find('table.teamTable').css('background-position', '0 0');
//        } else {
//            $this.find('table.teamTable').css('background-position', '0 -12px');
//        }
        $this.find(".teamTable").slideToggle("slow");
    });

}



