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

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'callSetting', 'error')} required">
	<label for="callSetting">
		<g:message code="setting.callSetting.label" default="Call Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="callSetting" required="" value="${settingInstance?.callSetting}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'closingSetting', 'error')} required">
	<label for="closingSetting">
		<g:message code="setting.closingSetting.label" default="Closing Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="closingSetting" required="" value="${settingInstance?.closingSetting}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'demoSetting', 'error')} required">
	<label for="demoSetting">
		<g:message code="setting.demoSetting.label" default="Demo Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="demoSetting" required="" value="${settingInstance?.demoSetting}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'employeesUrl', 'error')} required">
	<label for="employeesUrl">
		<g:message code="setting.employeesUrl.label" default="Employees Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="employeesUrl" required="" value="${settingInstance?.employeesUrl}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'pipelineSetting', 'error')} required">
	<label for="pipelineSetting">
		<g:message code="setting.pipelineSetting.label" default="Pipeline Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="pipelineSetting" required="" value="${settingInstance?.pipelineSetting}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'revenueSetting', 'error')} required">
	<label for="revenueSetting">
		<g:message code="setting.revenueSetting.label" default="Revenue Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="revenueSetting" required="" value="${settingInstance?.revenueSetting}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: settingInstance, field: 'searchesUrl', 'error')} required">
	<label for="searchesUrl">
		<g:message code="setting.searchesUrl.label" default="Searches Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="searchesUrl" required="" value="${settingInstance?.searchesUrl}"/>

</div>

