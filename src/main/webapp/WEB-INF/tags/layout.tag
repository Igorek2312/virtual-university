<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Page Description">
    <meta name="author" content="Igor Rybak">
    <title>Page Title</title>

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/main.css">
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/"><spring:message code="label.virtual.university"/></a>
    </div>
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <sec:authorize access="isAnonymous()">
                <li class="${pageContext.request.requestURI eq '/WEB-INF/views/login.jsp' ? ' active' : ''}">
                    <a href="/login"><spring:message code="label.log.in"/></a>
                </li>
                <li class="${pageContext.request.requestURI eq '/WEB-INF/views/sing-up.jsp' ? ' active' : ''}">
                    <a href="/sign-up"><spring:message code="label.sign.up"/></a>
                </li>
            </sec:authorize>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <sec:authorize access="isAuthenticated()">
                <li class="${pageContext.request.requestURI eq '/WEB-INF/views/profile.jsp' ? ' active' : ''}">
                    <spring:message code="label.profile" var="profile"/>
                    <a href="/profile" data-toggle="tooltip" title="${profile}">
                        <i class="fa fa-user"></i>
                        <sec:authentication property="principal.firstName"/>
                        <sec:authentication property="principal.lastName"/>
                    </a>
                </li>
                <li>
                    <a href="/logout"><spring:message code="label.log.out"/></a>
                </li>
            </sec:authorize>
        </ul>
    </div>
</nav>

<div class="container-fluid">
    <jsp:doBody/>
</div>

<script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

</body>
</html>
