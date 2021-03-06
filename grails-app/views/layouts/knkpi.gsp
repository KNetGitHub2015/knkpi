<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:700,400' rel='stylesheet' type='text/css'>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'kpi.css')}" type="text/css">
    <g:layoutHead/>
</head>
<body>
<div id="grailsLogo" role="banner">
    <a href="${createLink(uri: "/")}"><image src="${resource(dir: 'images', file: 'kn.png')}" alt="KPI"/></a>
    <sec:ifLoggedIn>
        <span id="logoutLink"><g:link controller="logout">Log out</g:link></span>
    </sec:ifLoggedIn>
</div>

<g:layoutBody/>
<div class="footer" role="contentinfo"><p class="copyright">&copy; ${new Date().format("yyyy")} Knowledgenet. All rights reserved</p></div>
<r:layoutResources/>
</body>
</html>
