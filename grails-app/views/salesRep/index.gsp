
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
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
                <li><g:link action="getEmployees">Refresh Employees</g:link></li>
			</ul>
		</div>
		<div id="list-salesRep" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="knTable">
			<thead class="managerName">
					<tr>
						<g:sortableColumn property="repName" title="${message(code: 'salesRep.repName.label', default: 'Rep Name')}" />
					
						<g:sortableColumn property="title" title="${message(code: 'salesRep.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="managerName" title="${message(code: 'salesRep.managerName.label', default: 'Manager Name')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${salesRepInstanceList}" status="i" var="salesRepInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${salesRepInstance.id}">${fieldValue(bean: salesRepInstance, field: "repName")}</g:link></td>
					
						<td>${fieldValue(bean: salesRepInstance, field: "title")}</td>
					
						<td>${fieldValue(bean: salesRepInstance, field: "managerName")}</td>
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
