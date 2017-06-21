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
        <li>
            <a href="/groups/${group.id}/create-subject-instance">
                    ${group.name} (<spring:message code="label.curriculum"/>)
            </a>
        </li>
        <li>
            <a href="/groups/${group.id}/subject-instances?year=${param.year}&semester_number=${param.semester_number}">
                    ${param.year} <spring:message code="label.semesterDateRange"/> ${param.semester_number}
            </a>
        </li>
        <li class="active">
                ${group.name} <spring:message code="label.lesson.schedule"/>
        </li>
    </ol>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="row">
            <div class="col-sm-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <spring:message code="label.new.period"/>
                    </div>
                    <div class="panel-body">
                        <form:form modelAttribute="period"
                                   action="/groups/${groupId}/periods"
                                   method="post" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="dayOfWeek" class="control-label col-sm-4">День тижня:</label>
                                <div class="col-sm-8">
                                    <form:select path="dayOfWeek" cssClass="form-control">
                                        <form:option value="${1}">понеділок</form:option>
                                        <form:option value="${2}">вівторок</form:option>
                                        <form:option value="${3}">середа</form:option>
                                        <form:option value="${4}">четверг</form:option>
                                        <form:option value="${5}">п'ятниця</form:option>
                                        <form:option value="${6}">субота</form:option>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <form:select path="oddEven" cssClass="form-control">
                                        <form:option value="BOTH">чисельник і знаменик</form:option>
                                        <form:option value="ODD">чисельник</form:option>
                                        <form:option value="EVEN">знаменик</form:option>
                                    </form:select>
                                </div>
                            </div>
                            <spring:bind path="periodNumber">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="periodNumber" class="control-label col-sm-4">Номер пари:</label>
                                    <div class="col-sm-8">
                                        <form:input path="periodNumber" type="number" value="1"
                                                    cssClass="form-control"/>
                                    </div>
                                    <div class="col-sm-12">
                                        <form:errors path="periodNumber" cssClass="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <%-- <div class="form-group">
                                 <label for="periodNumber" class="control-label col-sm-4">Аудеторія:</label>
                                 <div class="col-sm-8">
                                     <form:select path="classroom" cssClass="form-control">
                                         <form:options items="${classrooms}" itemLabel="name"/>
                                     </form:select>
                                 </div>
                             </div>--%>
                            <spring:bind path="classroom.id">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="col-sm-4">
                                        <spring:message code="label.classroom"/>
                                    </label>
                                    <div class="col-sm-12">
                                        <input id="classroom" type="text" class="form-control"/>
                                        <form:input path="classroom.id" id="classroom_id" type="hidden"/>
                                    </div>
                                    <div class="col-sm-12">
                                        <form:errors path="classroom.id" cssClass="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <div class="form-group">
                                <label for="periodNumber" class="control-label col-sm-4">Предмет:</label>
                                <div class="col-sm-8">
                                    <form:select path="teacherSubjectInstance" cssClass="form-control">
                                        <form:options items="${teacherSubjectInstances}" itemLabel="detailName"/>
                                    </form:select>
                                </div>
                            </div>

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
    <jsp:include page="periods-schedule.jsp"/>
</t:layout>
