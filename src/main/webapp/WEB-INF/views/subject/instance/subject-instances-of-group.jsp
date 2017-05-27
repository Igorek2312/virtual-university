<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <li>
            <a href="/groups/${group.id}/create-subject-instance">
                    ${group.name} (<spring:message code="label.curriculum"/>)
            </a>
        </li>
        <li class="active">
                ${param.semester_number} <spring:message code="label.semesterDateRange"/> ${param.year}
        </li>
    </ol>
    <hr>
    <div class="row">
        <div class="col-sm-12">
            <a href="/groups/${groupId}/periods/?year=${param.year}&semester_number=${param.semester_number}">
                <button class="btn btn-primary">
                    Розклад занять
                </button>
            </a>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-sm-12">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th><spring:message code="label.name"/></th>
                    <th><spring:message code="label.control.type"/></th>
                    <th><spring:message code="label.subject.type"/></th>
                    <th><spring:message code="label.date.begin"/></th>
                    <th><spring:message code="label.date.end"/></th>
                    <th><spring:message code="label.hours"/></th>
                    <th></th>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <th></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${subjectInstances}" var="subjectInstance">
                    <tr>
                        <td>${subjectInstance.subject.name}</td>
                        <td>${subjectInstance.controlType}</td>
                        <td>${subjectInstance.subjectType}</td>
                        <td>${subjectInstance.dateBegin}</td>
                        <td>${subjectInstance.dateEnd}</td>
                        <td>${subjectInstance.hours}</td>
                        <td>
                            <a href="/subject-instances/${subjectInstance.id}/teacher-subject-instances">
                                <spring:message code="label.teachers"/>
                            </a>
                        </td>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <td>
                                <a href="/edit-subject-instance/${subjectInstance.id}">
                                    <button class="btn btn-primary">
                                        <i class="fa fa-pencil-square-o"></i>
                                    </button>
                                </a>
                                <a href="/groups/${groupId}/delete-subject-instance/${subjectInstance.id}">
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
    </div>
</t:layout>
