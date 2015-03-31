
<%@ page import="com.knowledgenet.knkpi.Setting" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'setting.label', default: 'Setting')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-setting" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-setting" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list setting">
			
				<g:if test="${settingInstance?.auth}">
				<li class="fieldcontain">
					<span id="auth-label" class="property-label"><g:message code="setting.auth.label" default="Auth" /></span>
					
						<span class="property-value" aria-labelledby="auth-label"><g:fieldValue bean="${settingInstance}" field="auth"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${settingInstance?.baseUrl}">
				<li class="fieldcontain">
					<span id="baseUrl-label" class="property-label"><g:message code="setting.baseUrl.label" default="Base Url" /></span>
					
						<span class="property-value" aria-labelledby="baseUrl-label"><g:fieldValue bean="${settingInstance}" field="baseUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${settingInstance?.callSetting}">
				<li class="fieldcontain">
					<span id="callSetting-label" class="property-label"><g:message code="setting.callSetting.label" default="Call Setting" /></span>
					
						<span class="property-value" aria-labelledby="callSetting-label"><g:fieldValue bean="${settingInstance}" field="callSetting"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${settingInstance?.closingSetting}">
				<li class="fieldcontain">
					<span id="closingSetting-label" class="property-label"><g:message code="setting.closingSetting.label" default="Closing Setting" /></span>
					
						<span class="property-value" aria-labelledby="closingSetting-label"><g:fieldValue bean="${settingInstance}" field="closingSetting"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${settingInstance?.demoSetting}">
				<li class="fieldcontain">
					<span id="demoSetting-label" class="property-label"><g:message code="setting.demoSetting.label" default="Demo Setting" /></span>
					
						<span class="property-value" aria-labelledby="demoSetting-label"><g:fieldValue bean="${settingInstance}" field="demoSetting"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${settingInstance?.employeesUrl}">
				<li class="fieldcontain">
					<span id="employeesUrl-label" class="property-label"><g:message code="setting.employeesUrl.label" default="Employees Url" /></span>
					
						<span class="property-value" aria-labelledby="employeesUrl-label"><g:fieldValue bean="${settingInstance}" field="employeesUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${settingInstance?.pipelineSetting}">
				<li class="fieldcontain">
					<span id="pipelineSetting-label" class="property-label"><g:message code="setting.pipelineSetting.label" default="Pipeline Setting" /></span>
					
						<span class="property-value" aria-labelledby="pipelineSetting-label"><g:fieldValue bean="${settingInstance}" field="pipelineSetting"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${settingInstance?.revenueSetting}">
				<li class="fieldcontain">
					<span id="revenueSetting-label" class="property-label"><g:message code="setting.revenueSetting.label" default="Revenue Setting" /></span>
					
						<span class="property-value" aria-labelledby="revenueSetting-label"><g:fieldValue bean="${settingInstance}" field="revenueSetting"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${settingInstance?.searchesUrl}">
				<li class="fieldcontain">
					<span id="searchesUrl-label" class="property-label"><g:message code="setting.searchesUrl.label" default="Searches Url" /></span>
					
						<span class="property-value" aria-labelledby="searchesUrl-label"><g:fieldValue bean="${settingInstance}" field="searchesUrl"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:settingInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${settingInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>