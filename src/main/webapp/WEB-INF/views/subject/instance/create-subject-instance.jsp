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
                    <spring:message code="label.new.subject.instance"/>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="subjectInstance" action="/groups/${groupId}/subject-instances"
                               method="post"
                               class="form-horizontal" role="form">
                        <spring:bind path="controlType">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.control.type" var="controlType"/>
                                    <form:input path="controlType" type="text" class="form-control"
                                                placeholder="${controlType}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="controlType" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="subjectType">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.subject.type" var="subjectType"/>
                                    <form:input path="subjectType" type="text" class="form-control"
                                                placeholder="${subjectType}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="subjectType" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="dateBegin">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.date.begin" var="dateBegin"/>
                                    <form:input path="dateBegin" type="date" class="form-control"
                                                placeholder="${dateBegin}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="dateBegin" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="dateEnd">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.date.end" var="dateEnd"/>
                                    <form:input path="dateEnd" type="date" class="form-control"
                                                placeholder="${dateEnd}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="dateEnd" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="hours">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.hours" var="hours"/>
                                    <form:input path="hours" type="text" class="form-control"
                                                placeholder="${hours}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="hours" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="subject">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.subject" var="subject"/>
                                    <form:select path="subject" cssClass="form-control">
                                        <form:options items="${subjects}" itemLabel="name"/>
                                    </form:select>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="subject" cssClass="text-danger"/>
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
            <th><spring:message code="label.year"/></th>
            <th><spring:message code="label.semester"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="semester" items="${semesters}">
            <tr>
                <td>${semester.year}</td>
                <td>${semester.semesterNumber}</td>
                <td>
                    <a href="/groups/${groupId}/subject-instances?year=${semester.year}&semester_number=${semester.semesterNumber}">
                        <spring:message code="label.curriculum"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:layout>