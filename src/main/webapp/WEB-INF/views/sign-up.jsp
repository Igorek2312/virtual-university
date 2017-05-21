<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="col-sm-6 col-sm-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <spring:message code="label.fill.in.fields"/>
            </div>
            <div class="panel-body">
                <form:form modelAttribute="signUpForm" action="/sign-up" method="post" class="form-horizontal"
                           role="form">
                    <spring:bind path="username">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <div class="col-sm-12">
                                <spring:message code="label.username" var="username"/>
                                <form:input path="username" type="text" class="form-control"
                                            placeholder="${username}"/>
                            </div>
                            <div class="col-sm-12">
                                <form:errors path="username" cssClass="text-danger"/>
                            </div>
                        </div>
                    </spring:bind>
                    <spring:bind path="password">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <div class="col-sm-12">
                                <spring:message code="label.password" var="password"/>
                                <form:input path="password" type="text" class="form-control"
                                            placeholder="${password}"/>
                            </div>
                            <div class="col-sm-12">
                                <form:errors path="password" cssClass="text-danger"/>
                            </div>
                        </div>
                    </spring:bind>
                    <spring:bind path="documentNumber">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <div class="col-sm-12">
                                <spring:message code="label.document.number" var="documentNumber"/>
                                <form:input path="documentNumber" type="text" class="form-control"
                                            placeholder="${documentNumber}"/>
                            </div>
                            <div class="col-sm-12">
                                <form:errors path="documentNumber" cssClass="text-danger"/>
                            </div>
                        </div>
                    </spring:bind>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-primary">
                                <spring:message code="label.sign.up"/>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</t:layout>
