<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="row">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="label.edit"/>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="subject" action="/edit-subject/${subject.id}" method="post"
                               class="form-horizontal" role="form">
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
                        <spring:bind path="department">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.department" var="department"/>
                                    <form:input path="department" type="text" class="form-control"
                                                placeholder="${department}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="department" cssClass="text-danger"/>
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