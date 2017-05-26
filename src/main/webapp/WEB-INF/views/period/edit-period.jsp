<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="row">
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="label.edit"/>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="periodToEdit" action="/groups/${groupId}/edit-period/${periodToEdit.id}"
                               method="post"
                               class="form-horizontal" role="form">

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
                        <div class="form-group">
                            <label for="periodNumber" class="control-label col-sm-4">Номер пари:</label>
                            <div class="col-sm-8">
                                <form:input path="periodNumber" type="number" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="periodNumber" class="control-label col-sm-4">Аудеторія:</label>
                            <div class="col-sm-8">
                                <form:select path="classroom" cssClass="form-control">
                                    <form:options items="${classrooms}" itemLabel="name"/>
                                </form:select>
                            </div>
                        </div>
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
