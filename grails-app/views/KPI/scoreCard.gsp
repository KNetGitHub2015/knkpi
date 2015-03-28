<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'kpi.css')}" type="text/css">
    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript src="scoreCard.js" />
    <title>KPI Scorecard</title>
</head>

<body>
    <div id="wrapper">
        <div id="topBar">
            <div id="rep">
                <form>
                    <g:select from="${salesReps}" value="${salesReps.repId.toString()}" name="salesRep" id="salesRep" optionValue="repName" optionKey="repId"
                        onchange="showSpinner();${remoteFunction(
                            controller:'KPI',
                            action:'getScoreCardRepData',
                            params:'\'repId=\' + this.value',
                            onSuccess:'updateScoreCard(data)')}"
                    />
                </form><span id="spinner" class="spinner" style="display:none;"></span>
                <div id="repInfo"></div>
            </div>
            <div id="filter">
                <g:radioGroup name="duration"
                              labels="['Month','Quarter','Year']"
                              values="['thismonth','thisquarter','thisyear']">
                    <p class="radioGroup" >${it.label} ${it.radio}</p>
                </g:radioGroup>
            </div> <br>
        </div>
    </div>
</body>
</html>