<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <ol class="breadcrumb">
        <li>
            <a href="/faculties"><spring:message code="label.univercity"/></a>
        </li>
        <li>
            <a href="/faculties/${group.specialty.faculty.id}/specialties">
                    ${group.specialty.faculty.name}
            </a>
        </li>
        <li>
            <a href="/specialties/${group.specialty.id}/groups">
                    ${group.specialty.name}
            </a>
        </li>
        <li class="active">${group.name}</li>
    </ol>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="row">
            <div class="col-sm-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <spring:message code="label.new.student"/>
                    </div>
                    <div class="panel-body">
                        <form:form modelAttribute="student" action="/groups/${group.id}/students" method="post"
                                   class="form-horizontal" role="form">
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
                            <spring:bind path="recordBookNumber">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="col-sm-12">
                                        <spring:message code="label.record.book.number" var="recordBookNumber"/>
                                        <form:input path="recordBookNumber" type="text" class="form-control"
                                                    placeholder="${recordBookNumber}"/>
                                    </div>
                                    <div class="col-sm-12">
                                        <form:errors path="recordBookNumber" cssClass="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <spring:bind path="financeType">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="col-sm-12">
                                        <spring:message code="label.finance.type" var="financeType"/>
                                        <form:input path="financeType" type="text" class="form-control"
                                                    placeholder="${financeType}"/>
                                    </div>
                                    <div class="col-sm-12">
                                        <form:errors path="financeType" cssClass="text-danger"/>
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
    </sec:authorize>

    <div class="table-responsive">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th><spring:message code="label.person.name"/></th>
                <th><spring:message code="label.document.number"/></th>
                <th><spring:message code="label.record.book.number"/></th>
                <th><spring:message code="label.finance.type"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th></th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach varStatus="loop" var="student" items="${students}">
                <tr>
                    <td>${loop.count}</td>
                    <td>${student.account.lastName} ${student.account.firstName} ${student.account.middleName}</td>
                    <td>${student.account.documentNumber}</td>
                    <td>${student.recordBookNumber}</td>
                    <td>${student.financeType}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <a href="/edit-student/${student.id}">
                                <button class="btn btn-primary">
                                    <i class="fa fa-pencil-square-o"></i>
                                </button>
                            </a>
                            <a href="/groups/${group.id}/delete-student/${student.id}">
                                <button class="btn btn-danger">
                                    <i class="fa fa-trash"></i>
                                </button>
                            </a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:layout>