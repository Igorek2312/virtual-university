<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="row">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="label.new.teacher"/>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="teacher" action="/teachers" method="post" class="form-horizontal"
                               role="form">
                        <spring:bind path="account.lastName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.last.name" var="lastName"/>
                                    <form:input path="account.lastName" type="text" class="form-control"
                                                placeholder="${lastName}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="account.lastName" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="account.firstName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.first.name" var="firstName"/>
                                    <form:input path="account.firstName" type="text" class="form-control"
                                                placeholder="${firstName}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="account.firstName" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="account.middleName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.middle.name" var="middleName"/>
                                    <form:input path="account.middleName" type="text" class="form-control"
                                                placeholder="${middleName}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="account.middleName" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="account.documentNumber">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.document.number" var="documentNumber"/>
                                    <form:input path="account.documentNumber" type="text" class="form-control"
                                                placeholder="${documentNumber}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="account.documentNumber" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-primary">
                                    <spring:message code="label.add"/>
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th><spring:message code="label.person.name"/></th>
            <th><spring:message code="label.document.number"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachers.content}" var="teacher">
            <tr>
                <td>${teacher.account.lastName} ${teacher.account.firstName} ${teacher.account.middleName}</td>
                <td>${teacher.account.documentNumber}</td>
                <td>
                    <a href="/edit-teacher/${teacher.id}">
                        <button class="btn btn-primary">
                            <i class="fa fa-pencil-square-o"></i>
                        </button>
                    </a>
                    <a href="/delete-teacher/${teacher.id}">
                        <button class="btn btn-danger">
                            <i class="fa fa-trash"></i>
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:layout>
