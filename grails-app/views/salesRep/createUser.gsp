<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'salesRep.label', default: 'SalesRep')}" />
    <title><g:message code="default.create.label" args="[entityName]" /> User</title>
</head>
<body>
<a href="#create-salesRep" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="create-salesRep" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]" /> User</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${createUserCommandInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${createUserCommandInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form url="[action:'saveUser']" >
        <fieldset class="form">
            <g:hiddenField name="salesRepId" value="${fieldValue(bean: createUserCommandInstance, field: 'salesRepId')}" />
            <div class="fieldcontain required">
                <label for="username">Username</label>
                <g:textField name="username" value="${fieldValue(bean: createUserCommandInstance, field: 'username')}" />
            </div>
            <div class="fieldcontain required">
                <label for="password">Password</label>
                <g:passwordField name="password" />
            </div>
            <div class="fieldcontain required">
                <label for="confirmPassword">Confirm Password</label>
                <g:passwordField name="confirmPassword" />
            </div>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
