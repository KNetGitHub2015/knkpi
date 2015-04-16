<%@ page import="com.knowledgenet.knkpi.SalesRep" %>



<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'birthDay', 'error')} required">
	<label for="birthDay">
		<g:message code="salesRep.birthDay.label" default="Birth Day" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="birthDay" required="" value="${salesRepInstance?.birthDay}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'callSetting', 'error')} required">
	<label for="callSetting">
		<g:message code="salesRep.callSetting.label" default="Call Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="callSetting" required="" value="${salesRepInstance?.callSetting}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'calls', 'error')} required">
	<label for="calls">
		<g:message code="salesRep.calls.label" default="Calls" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="calls" type="number" value="${salesRepInstance.calls}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'closingPercentage', 'error')} required">
	<label for="closingPercentage">
		<g:message code="salesRep.closingPercentage.label" default="Closing Percentage" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="closingPercentage" value="${fieldValue(bean: salesRepInstance, field: 'closingPercentage')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'closingSetting', 'error')} required">
	<label for="closingSetting">
		<g:message code="salesRep.closingSetting.label" default="Closing Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="closingSetting" required="" value="${salesRepInstance?.closingSetting}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'demoSetting', 'error')} required">
	<label for="demoSetting">
		<g:message code="salesRep.demoSetting.label" default="Demo Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="demoSetting" required="" value="${salesRepInstance?.demoSetting}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'demos', 'error')} required">
	<label for="demos">
		<g:message code="salesRep.demos.label" default="Demos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="demos" type="number" value="${salesRepInstance.demos}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'forecastingAccuracy', 'error')} required">
	<label for="forecastingAccuracy">
		<g:message code="salesRep.forecastingAccuracy.label" default="Forecasting Accuracy" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="forecastingAccuracy" value="${fieldValue(bean: salesRepInstance, field: 'forecastingAccuracy')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'managerId', 'error')} required">
	<label for="managerId">
		<g:message code="salesRep.managerId.label" default="Manager Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="managerId" required="" value="${salesRepInstance?.managerId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'managerName', 'error')} required">
	<label for="managerName">
		<g:message code="salesRep.managerName.label" default="Manager Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="managerName" required="" value="${salesRepInstance?.managerName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'pipelineManagement', 'error')} required">
	<label for="pipelineManagement">
		<g:message code="salesRep.pipelineManagement.label" default="Pipeline Management" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="pipelineManagement" value="${fieldValue(bean: salesRepInstance, field: 'pipelineManagement')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'pipelineSetting', 'error')} required">
	<label for="pipelineSetting">
		<g:message code="salesRep.pipelineSetting.label" default="Pipeline Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="pipelineSetting" required="" value="${salesRepInstance?.pipelineSetting}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'repId', 'error')} required">
	<label for="repId">
		<g:message code="salesRep.repId.label" default="Rep Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="repId" required="" value="${salesRepInstance?.repId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'repName', 'error')} required">
	<label for="repName">
		<g:message code="salesRep.repName.label" default="Rep Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="repName" required="" value="${salesRepInstance?.repName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'revenueAttainment', 'error')} required">
	<label for="revenueAttainment">
		<g:message code="salesRep.revenueAttainment.label" default="Revenue Attainment" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="revenueAttainment" value="${fieldValue(bean: salesRepInstance, field: 'revenueAttainment')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'revenueSetting', 'error')} required">
	<label for="revenueSetting">
		<g:message code="salesRep.revenueSetting.label" default="Revenue Setting" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="revenueSetting" required="" value="${salesRepInstance?.revenueSetting}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="salesRep.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="startDate" required="" value="${salesRepInstance?.startDate}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: salesRepInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="salesRep.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${salesRepInstance?.title}"/>

</div>

