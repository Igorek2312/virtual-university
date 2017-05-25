<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
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
            <th></th>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:layout>
