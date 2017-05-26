<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-bordered table-hover">
    <thead>
    <tr>
        <th colspan="3" class="text-center">
            ${group.name}
        </th>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <th></th>
        </sec:authorize>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${periods}" var="period">
        <c:set var="teacherSubjectInstance" value="${period.teacherSubjectInstance}"/>
        <c:set var="teacherSubjectInstance" value="${teacherSubjectInstance}"/>
        <c:set var="subjectInstance" value="${teacherSubjectInstance.subjectInstance}"/>
        <tr>
            <td><spring:message code="label.day.of.week.${period.dayOfWeek}"/></td>
            <td>${period.periodNumber}</td>
            <td>
                    ${subjectInstance.subject.name} (${teacherSubjectInstance.periodType}) <br>
                    ${period.classroom.name} ${teacherSubjectInstance.teacher.account.abbreviationName}
                <c:if test="${period.oddEven != 'BOTH'}">
                    <strong>
                        (<spring:message code="label.${period.oddEven}"/>)
                    </strong>
                </c:if>
            </td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td>
                    <a href="/groups/${groupId}/edit-period/${period.id}?year=${param.year}&semester_number=${param.semester_number}">
                        <button class="btn btn-primary">
                            <i class="fa fa-pencil-square-o"></i>
                        </button>
                    </a>
                    <a href="/groups/${groupId}/delete-period/${period.id}">
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