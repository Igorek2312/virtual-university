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
                    <form:form modelAttribute="group" action="/edit-group/${group.id}" method="post" class="form-horizontal"
                               role="form">
                        <spring:bind path="name">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.name" var="name"/>
                                    <form:input path="name" type="text" class="form-control"
                                                placeholder="${name}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="name" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="yearEntered">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.year.entered" var="yearEntered"/>
                                    <form:input path="yearEntered" type="text" class="form-control"
                                                placeholder="${yearEntered}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="yearEntered" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="yearOfStudyEntered">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.year.of.study.entered" var="yearOfStudyEntered"/>
                                    <form:input path="yearOfStudyEntered" type="text" class="form-control"
                                                placeholder="${yearOfStudyEntered}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="yearOfStudyEntered" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-primary">
                                    <spring:message code="label.save"/>
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</t:layout>