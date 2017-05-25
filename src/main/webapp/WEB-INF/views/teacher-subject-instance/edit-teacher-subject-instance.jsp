<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="row">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="label.edit"/>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="teacherSubjectInstance"
                               action="/edit-teacher-subject-instance/${teacherSubjectInstanceId}" method="post"
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
                                    <spring:message code="label.edit"/>
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</t:layout>
