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
                    <form:form modelAttribute="subjectInstance" action="/edit-subject-instance/${subjectInstance.id}"
                               method="post" class="form-horizontal"
                               role="form">
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