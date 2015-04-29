<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="knkpi"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'kpi.css')}" type="text/css">
    <g:javascript library="jquery" plugin="jquery"/>
    <title>KPI Home</title>
</head>

<body>
<div id="wrapper">
    <div id="topBar">
        <h1>Home</h1>
    </div>
    <div id="content">
        <ul class="links">
            <li><g:link controller="KPI" action="dashboard">Dashboard</g:link></li>
            <sec:ifAllGranted roles="${com.knowledgenet.knkpi.Role.ADMIN}">
                <li><g:link controller="salesRep" action="index">Employees</g:link></li>
                <li><g:link controller="setting" action="index">Settings</g:link></li>
                <li><g:link controller="user" action="userSearch">Users</g:link></li>
            </sec:ifAllGranted>
        </ul>
    </div>
</div>
</body>
</html>