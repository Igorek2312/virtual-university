<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <li class="active">${group.name} (<spring:message code="label.curriculum"/>)</li>
    </ol>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
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

                            <div class="form-group">
                                <label for="controlType" class="control-label col-sm-4"><spring:message
                                        code="label.control.type"/></label>
                                <div class="col-sm-8">
                                    <form:select path="controlType" class="form-control">
                                        <form:option value="екзамен">екзамен</form:option>
                                        <form:option value="курсовий проект">курсовий проект</form:option>
                                        <form:option value="залік">залік</form:option>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="subjectType" class="control-label col-sm-4">
                                    <spring:message code="label.subject.type"/>
                                </label>
                                <div class="col-sm-8">
                                    <form:select path="subjectType" class="form-control">
                                        <form:option value="Звичайна">Звичайна</form:option>
                                        <form:option value="Факультатив">Факультатив</form:option>
                                        <form:option value="Секція">Секція</form:option>
                                    </form:select>
                                </div>
                            </div>

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
    </sec:authorize>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th><spring:message code="label.year"/></th>
            <th><spring:message code="label.semesterDateRange"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="semesterDateRange" items="${semesters}">
            <tr>
                <td>${semesterDateRange.year}</td>
                <td>${semesterDateRange.semesterNumber}</td>
                <td>
                    <a href="/groups/${groupId}/subject-instances?year=${semesterDateRange.year}&semester_number=${semesterDateRange.semesterNumber}">
                        <spring:message code="label.curriculum"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:layout>