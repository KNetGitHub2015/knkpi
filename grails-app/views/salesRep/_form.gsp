<%@ page import="com.knowledgenet.knkpi.SalesRep" %>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'callSetting', 'error')} required">
	<label for="callSetting">
		<g:message code="salesRep.callSetting.label" default="Call Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="callSetting" type="number" value="${salesRepInstance.callSetting}" required=""/>

</div>


<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'closingSetting', 'error')} required">
	<label for="closingSetting">
		<g:message code="salesRep.closingSetting.label" default="Closing Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="closingSetting" value="${fieldValue(bean: salesRepInstance, field: 'closingSetting')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'demoSetting', 'error')} required">
	<label for="demoSetting">
		<g:message code="salesRep.demoSetting.label" default="Demo Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="demoSetting" type="number" value="${salesRepInstance.demoSetting}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'pipelineSetting', 'error')} required">
	<label for="pipelineSetting">
		<g:message code="salesRep.pipelineSetting.label" default="Pipeline Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="pipelineSetting" type="number" value="${salesRepInstance.pipelineSetting}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'revenueSetting', 'error')} required">
	<label for="revenueSetting">
		<g:message code="salesRep.revenueSetting.label" default="Revenue Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="revenueSetting" value="${fieldValue(bean: salesRepInstance, field: 'revenueSetting')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'user', 'error')}">
	<label for="user">
		<g:message code="salesRep.user.label" default="User" />
	</label>
	<g:select name="user" from="${com.knowledgenet.knkpi.User.list()}" optionKey="id" optionValue="username" noSelection="['': '']" value="${salesRepInstance.user?.id}" />

</div>

