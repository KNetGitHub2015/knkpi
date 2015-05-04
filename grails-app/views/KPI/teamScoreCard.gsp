<%@ page import="grails.converters.JSON; com.knowledgenet.knkpi.Role" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="knkpi"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'kpi.css')}" type="text/css">
    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript src="grades.js" />
    <g:javascript src="scoreCard.js" />
    <g:javascript src="teamScoreCard.js" />
    <title>KPI Team Scorecard</title>
</head>

<body>
<div id="wrapper">
    <div id="topBar">
        <div id="filter">
            <g:form controller="KPI" action="teamScoreCard" method="get">
                <g:hiddenField name="repId" value="${repId}" />
                <g:radioGroup name="dateFilter" value="${dateFilter}"
                              labels="['MTD', 'QTD', 'YTD']"
                              values="['thismonth', 'thisfiscalquarter', 'thisyear']"
                              onchange="submit()">
                    <p class="radioGroup">${it.label} ${it.radio}</p>
                </g:radioGroup>
            </g:form>
        </div>
        <br>
    </div>

    <div id="rep">
        <form>
            <sec:ifAnyGranted roles="${Role.ADMIN}, ${Role.MANAGER}">
                <g:form controller="KPI" action="teamScoreCard">
                    <g:hiddenField name="dateFilter" value="${dateFilter}"/>
                    <g:select from="${allManagers}" value="${repId}" name="repId" id="repId"
                              optionValue="name" optionKey="id"
                              onchange="submit()"/>
                </g:form>
            </sec:ifAnyGranted>
            <sec:ifNotGranted roles="${Role.ADMIN}, ${Role.MANAGER}">
                <g:hiddenField id="salesRep" name="salesRep" value="${repId}"/>
            </sec:ifNotGranted>
        </form><span id="loading" class="loading" style="display:none;">Fetching data...</span><br>
        <div id="repInfo">
            <label class="repLabels" for="repTitle">Team:</label>
            <span id="repTitle" class="infoFields">${manager.name}</span> <br>
        </div>
        <div id="gradeAverage" style="margin-top: -58px;"></div>
    </div>

    <div id="repStats" class="expandableTable">
        %{--<div id="repRevenue">--}%
        %{--<label for="revenueAmount">Total Revenue:</label>--}%
        %{--<span id="revenueAmount"></span>--}%
        %{--</div>--}%

        %{--<div id="repDemos">--}%
        %{--<label for="demoAmount">Total Demos:</label>--}%
        %{--<span id="demoAmount"></span>--}%
        %{--</div>--}%

        %{--<div id="repPipeline">--}%
        %{--<label for="pipelineAmount">Pipeline Total:</label>--}%
        %{--<span id="pipelineAmount"></span>--}%
        %{--</div>--}%

        %{--<div id="repClosingPercentage">--}%
        %{--<label for="closingPercentageAmount">Closing Percentage:</label>--}%
        %{--<span id="closingPercentageAmount"></span>--}%
        %{--</div>--}%

        <table id="repStatsTable" class="knTable">
            <thead>
            <tr>
                <td>KPI</td><td>Value</td><td>Measure</td><td>Score</td><td>Grade</td>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
<jq:jquery>
    showSpinner();
    initTeamScoreCard(${raw((manager as JSON).toString())}, ${raw((teamMembers as JSON).toString())}, '${dateFilter}', ${dayOfPeriod}, ${totalDays});
</jq:jquery>
</body>
</html>