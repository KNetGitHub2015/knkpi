<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'kpi.css')}" type="text/css">
    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript src="grades.js" />
    <g:javascript src="dashBoard.js" />
    <title>KPI Dashboard</title>
</head>

<body>
<div id="wrapper">
    <div id="topBar">
        <div id="filter">
            <g:form controller="KPI" action="dashboard">
                <g:radioGroup name="dateFilter" value="${dateFilter}"
                              labels="['MTD','QTD','YTD']"
                              values="['thismonth','thisfiscalquarter','thisyear']"
                              onchange="submit()">
                    <p class="radioGroup" >${it.label} ${it.radio}</p>
                </g:radioGroup>
            </g:form>
        </div> <br>
        <div id="revenueGraph"></div>
        <div id="overviewData"></div>
    </div>

    <div id="teamOverview" class="tableData">
        <g:each var="manager" in="${managers}" status="i" >
            <div class="expandableTable">
                <table id="manager${manager.id}" class="knTable">
                    <thead>
                        <tr>
                            <td class="repName">name</td>
                            <td style="text-align: center;">grade</td>
                            <td>calls</td>
                            <td>revenue</td>
                            <td>quota</td>
                            <td>demos</td>
                            <td>pipeline</td>
                            <td class="closing-percentage">closing<br>percentage</td>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </g:each>
    </div>
</div>
<jq:jquery>
    var scoreCardUrl = "${createLink(controller: 'KPI', action: 'scoreCard')}";
    dashBoardInit(${raw(managersJson)}, ${raw(salesReps)}, scoreCardUrl, '${dateFilter}', ${dayOfPeriod}, ${totalDays});
</jq:jquery>
</body>
</html>