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
                        <div class="form-group">
                            <label for="controlType" class="control-label col-sm-4"><spring:message
                                    code="label.control.type"/></label>
                            <div class="col-sm-8">
                                <form:select path="controlType" class="form-control">
                                    <form:option value="екзамен">екзамен</form:option>
                                    <form:option value="курсовий проект">курсовий проект</form:option>
                                    <form:option value="залік">залік</form:option>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subjectType" class="control-label col-sm-4">
                                <spring:message code="label.subject.type"/>
                            </label>
                            <div class="col-sm-8">
                                <form:select path="subjectType" class="form-control">
                                    <form:option value="Звичайна">Звичайна</form:option>
                                    <form:option value="Факультатив">Факультатив</form:option>
                                    <form:option value="Секція">Секція</form:option>
                                </form:select>
                            </div>
                        </div>
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