function dashBoardInit(managers, salesReps) {

    for (var id in managers) {
        if (managers.hasOwnProperty(id)) {
            var managerTotal = managers[id];
            var managerRow = "<tr class='managerRow' id='manager" + managerTotal.id + "'></tr>";
            var managerTotalDiv = $('#manager' + managerTotal.id + ' > thead');
            managerTotalDiv.append(managerRow);

            var $managerTableData = "<td class='repName managerName'>Team: " + managerTotal.name + "</td><td class='calls'>" + managerTotal.totalCalls + "</td><td>" + managerTotal.totalRevenueAttainment + "</td><td>" + managerTotal.totalDemos + "</td><td>" + managerTotal.totalPipelineManagement + "</td><td>" + (managerTotal.totalClosingPercentage * 100).toFixed(2);
            +"%</td>";
            $("#manager" + managerTotal.id + " > thead").append($managerTableData);

        }
    }

    for (var repId in salesReps) {
        if (salesReps.hasOwnProperty(repId)) {
            var rep = salesReps[repId];
            var manager = rep.managerId;

            var $repRow = "<tr id='rep" + rep.repId + "'></tr>";
            var managerDiv = $('#manager' + rep.managerId + ' > tbody');
            managerDiv.append($repRow);

            var $tableData = "<td class='repName'><a href='/knkpi/KPI/scoreCard?repId=" + rep.repId + "'>" + rep.repName + "</a></td><td class='calls'>" + rep.calls + "</td><td>" + rep.revenueAttainment + "</td><td>" + rep.demos + "</td><td>" + rep.pipelineManagement + "</td><td>" + (rep.closingPercentage * 100).toFixed(2);
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



