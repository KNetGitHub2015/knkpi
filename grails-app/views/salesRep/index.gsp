
<%@ page import="com.knowledgenet.knkpi.SalesRep" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salesRep.label', default: 'SalesRep')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-salesRep" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link controller="salesRep" action="getEmployees">Refresh Employees</g:link> </li>
			</ul>
		</div>
		<div id="list-salesRep" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="birthDay" title="${message(code: 'salesRep.birthDay.label', default: 'Birth Day')}" />
					
						<g:sortableColumn property="callSetting" title="${message(code: 'salesRep.callSetting.label', default: 'Call Setting')}" />
					
						<g:sortableColumn property="calls" title="${message(code: 'salesRep.calls.label', default: 'Calls')}" />
					
						<g:sortableColumn property="closingPercentage" title="${message(code: 'salesRep.closingPercentage.label', default: 'Closing Percentage')}" />
					
						<g:sortableColumn property="closingSetting" title="${message(code: 'salesRep.closingSetting.label', default: 'Closing Setting')}" />
					
						<g:sortableColumn property="demoSetting" title="${message(code: 'salesRep.demoSetting.label', default: 'Demo Setting')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${salesRepInstanceList}" status="i" var="salesRepInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${salesRepInstance.id}">${fieldValue(bean: salesRepInstance, field: "birthDay")}</g:link></td>
					
						<td>${fieldValue(bean: salesRepInstance, field: "callSetting")}</td>
					
						<td>${fieldValue(bean: salesRepInstance, field: "calls")}</td>
					
						<td>${fieldValue(bean: salesRepInstance, field: "closingPercentage")}</td>
					
						<td>${fieldValue(bean: salesRepInstance, field: "closingSetting")}</td>
					
						<td>${fieldValue(bean: salesRepInstance, field: "demoSetting")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${salesRepInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
