<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="row">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="label.new.teacher.subject"/>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="teacherSubjectInstance"
                               action="/subject-instances/${subjectInstanceId}/teacher-subject-instances"
                               method="post"
                               class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-sm-4">
                                <spring:message code="label.teacher"/>
                            </label>
                            <div class="col-sm-8">
                                <form:select path="teacher" type="text" class="form-control">
                                    <form:options items="${teachers}" itemLabel="account.firstName"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4">
                                <spring:message code="label.period.type"/>
                            </label>
                            <div class="col-sm-8">
                                <form:select path="periodType" type="text" class="form-control">
                                    <form:option value="лекції">лекції</form:option>
                                    <form:option value="лабораторні">лабораторні</form:option>
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
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th><spring:message code="label.teacher"/></th>
            <th><spring:message code="label.subject"/></th>
            <th><spring:message code="label.period.type"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teacherSubjectInstances}" var="teacherSubjectInstance">
            <tr>
                <td>${teacherSubjectInstance.teacher.account.fullName}</td>
                <td>${teacherSubjectInstance.subjectInstance.subject.name}</td>
                <td>${teacherSubjectInstance.periodType}</td>
                <td>
                    <a href="/edit-teacher-subject-instance/${teacherSubjectInstance.id}">
                        <button class="btn btn-primary">
                            <i class="fa fa-pencil-square-o"></i>
                        </button>
                    </a>
                    <a href="/subject-instances/${subjectInstanceId}/delete-teacher-subject-instance/${teacherSubjectInstance.id}">
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
