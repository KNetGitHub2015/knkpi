
<%@ page import="com.knowledgenet.knkpi.Setting" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'setting.label', default: 'Setting')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-setting" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-setting" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="auth" title="${message(code: 'setting.auth.label', default: 'Auth')}" />
					
						<g:sortableColumn property="baseUrl" title="${message(code: 'setting.baseUrl.label', default: 'Base Url')}" />
					
						<g:sortableColumn property="employeesUrl" title="${message(code: 'setting.employeesUrl.label', default: 'Employees Url')}" />
					
						<g:sortableColumn property="searchesUrl" title="${message(code: 'setting.searchesUrl.label', default: 'Searches Url')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${settingInstanceList}" status="i" var="settingInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${settingInstance.id}">${fieldValue(bean: settingInstance, field: "auth")}</g:link></td>
					
						<td>${fieldValue(bean: settingInstance, field: "baseUrl")}</td>
					
						<td>${fieldValue(bean: settingInstance, field: "employeesUrl")}</td>
					
						<td>${fieldValue(bean: settingInstance, field: "searchesUrl")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${settingInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
