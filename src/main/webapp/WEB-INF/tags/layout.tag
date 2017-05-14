<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Page Description">
    <meta name="author" content="Igor Rybak">
    <title>Page Title</title>

    <link href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">

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
        <a class="navbar-brand" href="/">Title</a>
    </div>

    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <li class="<%=request.getRequestURI().startsWith("/faculties")?"active":""%>">
                <a href="/faculties"><spring:message code="label.students"/></a>
            </li>
        </ul>
    </div>
</nav>

<div class="container-fluid">
    <jsp:doBody/>
</div>

</body>
</html>
