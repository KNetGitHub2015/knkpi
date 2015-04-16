<%@ page import="com.knowledgenet.knkpi.Setting" %>



<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'auth', 'error')} required">
	<label for="auth">
		<g:message code="setting.auth.label" default="Auth" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="auth" required="" value="${settingInstance?.auth}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'baseUrl', 'error')} required">
	<label for="baseUrl">
		<g:message code="setting.baseUrl.label" default="Base Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="baseUrl" required="" value="${settingInstance?.baseUrl}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'employeesUrl', 'error')} required">
	<label for="employeesUrl">
		<g:message code="setting.employeesUrl.label" default="Employees Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="employeesUrl" required="" value="${settingInstance?.employeesUrl}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'searchesUrl', 'error')} required">
	<label for="searchesUrl">
		<g:message code="setting.searchesUrl.label" default="Searches Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="searchesUrl" required="" value="${settingInstance?.searchesUrl}"/>

</div>

