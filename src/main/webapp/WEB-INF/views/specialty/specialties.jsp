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
        <li class="active">${faculty.name}</li>
    </ol>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="row">
            <div class="col-sm-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <spring:message code="label.new.specialty"/>
                    </div>
                    <div class="panel-body">
                        <form:form modelAttribute="specialty" action="/faculties/${faculty.id}/specialties"
                                   method="post" class="form-horizontal"
                                   role="form">
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
                <th><spring:message code="label.name"/></th>
                <th><spring:message code="label.groups"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th></th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="specialty" items="${specialties}">
                <tr>
                    <td>${specialty.name}</td>
                    <td>
                        <a href="/specialties/${specialty.id}/groups">
                            <spring:message code="label.groups"/>
                        </a>
                    </td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <a href="/edit-specialty/${specialty.id}">
                                <button class="btn btn-primary">
                                    <i class="fa fa-pencil-square-o"></i>
                                </button>
                            </a>
                            <a href="/faculties/${faculty.id}/delete-specialty/${specialty.id}">
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