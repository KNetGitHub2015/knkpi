
<%@ page import="com.knowledgenet.knkpi.SalesRep" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'salesRep.label', default: 'SalesRep')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-salesRep" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<g:if test="${!salesRepInstance.user}">
					<li><g:link class="create" action="createUser" params="[id: salesRepInstance.id]">Create User</g:link></li>
				</g:if>
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
			</ul>
		</div>
		<div id="show-salesRep" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list salesRep">

                <g:if test="${salesRepInstance?.repId}">
                    <li class="fieldcontain">
                        <span id="repId-label" class="property-label"><g:message code="salesRep.repId.label" default="Rep Id" /></span>

                        <span class="property-value" aria-labelledby="repId-label"><g:fieldValue bean="${salesRepInstance}" field="repId"/></span>

                    </li>
                </g:if>

                <g:if test="${salesRepInstance?.repName}">
                    <li class="fieldcontain">
                        <span id="repName-label" class="property-label"><g:message code="salesRep.repName.label" default="Rep Name" /></span>

                        <span class="property-value" aria-labelledby="repName-label"><g:fieldValue bean="${salesRepInstance}" field="repName"/></span>

                    </li>
                </g:if>

                <g:if test="${salesRepInstance?.managerName}">
                    <li class="fieldcontain">
                        <span id="managerName-label" class="property-label"><g:message code="salesRep.managerName.label" default="Manager Name" /></span>

                        <span class="property-value" aria-labelledby="managerName-label"><g:fieldValue bean="${salesRepInstance}" field="managerName"/></span>

                    </li>
                </g:if>

				%{--<g:if test="${salesRepInstance?.title}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="title-label" class="property-label"><g:message code="salesRep.title.label" default="Title" /></span>--}%
					%{----}%
						%{--<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${salesRepInstance}" field="title"/></span>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%
			%{----}%
				%{--<g:if test="${salesRepInstance?.startDate}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="startDate-label" class="property-label"><g:message code="salesRep.startDate.label" default="Start Date" /></span>--}%
					%{----}%
						%{--<span class="property-value" aria-labelledby="startDate-label"><g:fieldValue bean="${salesRepInstance}" field="startDate"/></span>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%
			%{----}%
				%{--<g:if test="${salesRepInstance?.birthDay}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="birthDay-label" class="property-label"><g:message code="salesRep.birthDay.label" default="Birth Day" /></span>--}%
					%{----}%
						%{--<span class="property-value" aria-labelledby="birthDay-label"><g:fieldValue bean="${salesRepInstance}" field="birthDay"/></span>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%
			
				<g:if test="${salesRepInstance?.callSetting}">
				<li class="fieldcontain">
					<span id="callSetting-label" class="property-label"><g:message code="salesRep.callSetting.label" default="Call Setting" /></span>
					
						<span class="property-value" aria-labelledby="callSetting-label"><g:fieldValue bean="${salesRepInstance}" field="callSetting"/></span>
					
				</li>
				</g:if>
			
				%{--<g:if test="${salesRepInstance?.calls}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="calls-label" class="property-label"><g:message code="salesRep.calls.label" default="Calls" /></span>--}%
					%{----}%
						%{--<span class="property-value" aria-labelledby="calls-label"><g:fieldValue bean="${salesRepInstance}" field="calls"/></span>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%
			%{----}%
				%{--<g:if test="${salesRepInstance?.closingPercentage}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="closingPercentage-label" class="property-label"><g:message code="salesRep.closingPercentage.label" default="Closing Percentage" /></span>--}%
					%{----}%
						%{--<span class="property-value" aria-labelledby="closingPercentage-label"><g:fieldValue bean="${salesRepInstance}" field="closingPercentage"/></span>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%
			
				<g:if test="${salesRepInstance?.closingSetting}">
				<li class="fieldcontain">
					<span id="closingSetting-label" class="property-label"><g:message code="salesRep.closingSetting.label" default="Closing Setting" /></span>
					
						<span class="property-value" aria-labelledby="closingSetting-label"><g:fieldValue bean="${salesRepInstance}" field="closingSetting"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${salesRepInstance?.demoSetting}">
				<li class="fieldcontain">
					<span id="demoSetting-label" class="property-label"><g:message code="salesRep.demoSetting.label" default="Demo Setting" /></span>
					
						<span class="property-value" aria-labelledby="demoSetting-label"><g:fieldValue bean="${salesRepInstance}" field="demoSetting"/></span>
					
				</li>
				</g:if>
			
				%{--<g:if test="${salesRepInstance?.demos}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="demos-label" class="property-label"><g:message code="salesRep.demos.label" default="Demos" /></span>--}%
					%{----}%
						%{--<span class="property-value" aria-labelledby="demos-label"><g:fieldValue bean="${salesRepInstance}" field="demos"/></span>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%
			%{----}%
				%{--<g:if test="${salesRepInstance?.forecastingAccuracy}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="forecastingAccuracy-label" class="property-label"><g:message code="salesRep.forecastingAccuracy.label" default="Forecasting Accuracy" /></span>--}%
					%{----}%
						%{--<span class="property-value" aria-labelledby="forecastingAccuracy-label"><g:fieldValue bean="${salesRepInstance}" field="forecastingAccuracy"/></span>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%
			%{----}%
				%{--<g:if test="${salesRepInstance?.managerId}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="managerId-label" class="property-label"><g:message code="salesRep.managerId.label" default="Manager Id" /></span>--}%
					%{----}%
						%{--<span class="property-value" aria-labelledby="managerId-label"><g:fieldValue bean="${salesRepInstance}" field="managerId"/></span>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%

			
				%{--<g:if test="${salesRepInstance?.pipelineManagement}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="pipelineManagement-label" class="property-label"><g:message code="salesRep.pipelineManagement.label" default="Pipeline Management" /></span>--}%
					%{----}%
						%{--<span class="property-value" aria-labelledby="pipelineManagement-label"><g:fieldValue bean="${salesRepInstance}" field="pipelineManagement"/></span>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%
			
				<g:if test="${salesRepInstance?.pipelineSetting}">
				<li class="fieldcontain">
					<span id="pipelineSetting-label" class="property-label"><g:message code="salesRep.pipelineSetting.label" default="Pipeline Setting" /></span>
					
						<span class="property-value" aria-labelledby="pipelineSetting-label"><g:fieldValue bean="${salesRepInstance}" field="pipelineSetting"/></span>
					
				</li>
				</g:if>
			

			
				%{--<g:if test="${salesRepInstance?.revenueAttainment}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="revenueAttainment-label" class="property-label"><g:message code="salesRep.revenueAttainment.label" default="Revenue Attainment" /></span>--}%
					%{----}%
						%{--<span class="property-value" aria-labelledby="revenueAttainment-label"><g:fieldValue bean="${salesRepInstance}" field="revenueAttainment"/></span>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%
			
				<g:if test="${salesRepInstance?.revenueSetting}">
				<li class="fieldcontain">
					<span id="revenueSetting-label" class="property-label"><g:message code="salesRep.revenueSetting.label" default="Revenue Setting" /></span>
					
						<span class="property-value" aria-labelledby="revenueSetting-label"><g:fieldValue bean="${salesRepInstance}" field="revenueSetting"/></span>
					
				</li>
				</g:if>

				<g:if test="${salesRepInstance?.user}">
					<li class="fieldcontain">
						<span id="user-label" class="property-label"><g:message code="salesRep.user.label" default="User" /></span>

						<span class="property-value" aria-labelledby="user-label">
							<g:link controller="user" action="edit" params="[id: salesRepInstance.user.id]">
								<g:fieldValue bean="${salesRepInstance}" field="user.username"/>
							</g:link>
						</span>

					</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:salesRepInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${salesRepInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
