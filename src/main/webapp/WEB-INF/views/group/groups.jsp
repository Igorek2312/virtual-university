<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <ol class="breadcrumb">
        <li>
            <a href="/faculties"><spring:message code="label.univercity"/></a>
        </li>
        <li>
            <a href="/faculties/${specialty.faculty.id}/specialties">
                    ${specialty.faculty.name}
            </a>
        </li>
        <li class="active">${specialty.name}</li>
    </ol>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="row">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="label.new.group"/>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="group" action="/specialties/${specialty.id}/groups" method="post" class="form-horizontal" role="form">
                        <spring:bind path="name">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.name" var="name"/>
                                    <form:input path="name" type="text" class="form-control"
                                                placeholder="${name}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="name" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="yearEntered">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.year.entered" var="yearEntered"/>
                                    <form:input path="yearEntered" type="text" class="form-control"
                                                placeholder="${yearEntered}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="yearEntered" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="yearOfStudyEntered">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.year.of.study.entered" var="yearOfStudyEntered"/>
                                    <form:input path="yearOfStudyEntered" type="text" class="form-control"
                                                placeholder="${yearOfStudyEntered}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="yearOfStudyEntered" cssClass="text-danger"/>
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
        <table class="table table-hover table-bordered">
            <thead>
            <tr>
                <th><spring:message code="label.name"/></th>
                <th><spring:message code="label.year.entered"/></th>
                <th><spring:message code="label.year.of.study.entered"/></th>
                <th><spring:message code="label.students"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th></th>
                    <th></th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="group" items="${groups}">
                <tr>
                    <td>${group.name}</td>
                    <td>${group.yearEntered}</td>
                    <td>${group.yearOfStudyEntered}</td>
                    <td>
                        <a href="/groups/${group.id}/students">
                            <spring:message code="label.students"/>
                        </a>
                    </td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <a href="/groups/${group.id}/create-subject-instance">
                                <spring:message code="label.curriculum"/>
                            </a>
                        </td>
                        <td>
                            <a href="/edit-group/${group.id}">
                                <button class="btn btn-primary">
                                    <i class="fa fa-pencil-square-o"></i>
                                </button>
                            </a>
                            <a href="/specialties/${specialty.id}/delete-group/${group.id}">
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