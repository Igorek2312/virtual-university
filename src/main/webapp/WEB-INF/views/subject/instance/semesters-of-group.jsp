<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
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
                    <a href="/groups/${groupId}/periods?year=${semesterDateRange.year}&semester_number=${semesterDateRange.semesterNumber}">
                        <spring:message code="label.lesson.schedule"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</t:layout>