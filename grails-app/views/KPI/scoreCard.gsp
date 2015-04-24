<%@ page import="com.knowledgenet.knkpi.Role" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'kpi.css')}" type="text/css">
    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript src="scoreCard.js"/>
    <title>KPI Scorecard</title>
</head>

<body>
<div id="wrapper">
    <div id="topBar">
        <div id="filter">
            <g:radioGroup name="duration" id="duration" value="thismonth"
                          labels="['MTD', 'QTD', 'YTD']"
                          values="['thismonth', 'thisfiscalquarter', 'thisyear']"
                          onchange="showSpinner();${remoteFunction(
                                  controller: 'KPI',
                                  action: 'getScoreCardRepData',
                                  params: '\'repId=\' + getSalesRepValue() + \'&dateFilter=\' + getDateFilter()',
                                  onSuccess: 'updateScoreCard(data, getDateFilter())')}">
                <p class="radioGroup">${it.label} ${it.radio}</p>
            </g:radioGroup>
        </div> <br>
    </div>

    <div id="rep">
        <form>
            <sec:ifAnyGranted roles="${Role.ADMIN}, ${Role.MANAGER}">
                <g:select from="${salesReps}" value="${repId}" name="salesRep" id="salesRep"
                          optionValue="repName" optionKey="repId"
                          onchange="showSpinner();${remoteFunction(
                                  controller: 'KPI',
                                  action: 'getScoreCardRepData',
                                  params: '\'repId=\' + this.value + \'&dateFilter=\' + getDateFilter()',
                                  onSuccess: 'updateScoreCard(data, getDateFilter())')}"/>
            </sec:ifAnyGranted>
            <sec:ifNotGranted roles="${Role.ADMIN}, ${Role.MANAGER}">
                <g:hiddenField id="salesRep" name="salesRep" value="${repId}"/>
            </sec:ifNotGranted>
        </form><span id="spinner" class="spinner" style="display:none;"></span><br>

        <div id="repInfo">
            <label class="repLabels" for="repTitle">Title:</label>
            <span id="repTitle" class="infoFields"></span>

            <label class="repLabels" for="repManager">Manager:</label>
            <span id="repManager" class="infoFields"></span>

            <label class="repLabels" for="repStartDate">Start Date:</label>
            <span id="repStartDate" class="infoFields"></span>

            <label class="repLabels" for="repBirthDate">Birth Day:</label>
            <span id="repBirthDate" class="infoFields"></span>
        </div>

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
    ${remoteFunction(
            controller: 'KPI',
            action: 'getScoreCardRepData',
            params: '\'repId=\' + ' + repId + ' + \'&dateFilter=\' + getDateFilter()',
            onSuccess: 'updateScoreCard(data, getDateFilter())')}
</jq:jquery>
</body>
</html>