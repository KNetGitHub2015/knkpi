<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'kpi.css')}" type="text/css">
    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript src="scoreCard.js"/>
    <title>KPI Scorecard</title>
</head>

<body>
<g:if test="${repId}">
    <jq:jquery>
        redirectedRep(${repId});
    </jq:jquery>

</g:if>
<div id="wrapper">
    <div id="topBar">
        <div id="filter">
            <g:radioGroup name="duration" id="duration" value="thismonth"
                          labels="['Month', 'Quarter', 'Year']"
                          values="['thismonth', 'thisfiscalquarter', 'thisyear']">
                <p class="radioGroup">${it.label} ${it.radio}</p>
            </g:radioGroup>
        </div> <br>
    </div>

    <div id="rep">
        <form>
            <g:select from="${salesReps}" value="${salesReps.repId.toString()}" name="salesRep" id="salesRep"
                      optionValue="repName" optionKey="repId"
                      onchange="showSpinner();${remoteFunction(
                              controller: 'KPI',
                              action: 'getScoreCardRepData',
                              params: '\'repId=\' + this.value + \'&dateFilter=\' + getDateFilter()',
                              onSuccess: 'updateScoreCard(data)')}"/>
        </form><span id="spinner" class="spinner" style="display:none;"></span><br>

        <div id="repInfo">
            <label class="repLabels" for="repTitle">Title:</label>
            <span id="repTitle" class="infoFields"></span>

            <label class="repLabels" for="repManager">Manager:</label>
            <span id="repManager" class="infoFields"></span>

            <label class="repLabels" for="repStartDate">Start Date:</label>
            <span id="repStartDate" class="infoFields"></span>

            <label class="repLabels" for="repBirthDate">Birth Date:</label>
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

        <table id="repStatsTable" class="teamTable">
            <thead>
            <tr>
                <td>KPI</td><td>Value</td><td>Measure</td><td>Score</td>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>