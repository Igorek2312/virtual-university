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
                    <spring:message code="label.new.subject"/>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="subject" action="/subjects" method="post" class="form-horizontal"
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
            <th><spring:message code="label.name"/></th>
            <th><spring:message code="label.department"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="subject" items="${subjects.content}">
            <tr>
                <td>${subject.name}</td>
                <td>${subject.department}</td>
                <th>
                    <a href="/edit-subject/${subject.id}">
                        <button class="btn btn-primary">
                            <i class="fa fa-pencil-square-o"></i>
                        </button>
                    </a>
                    <a href="/delete-subject/${subject.id}">
                        <button class="btn btn-danger">
                            <i class="fa fa-trash"></i>
                        </button>
                    </a>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:set value="${subjects.totalPages-1}" var="maxPage"/>
    <c:if test="${maxPage>=0}">
        <div class="row">
            <div class="col-sm-2 col-centered">
                <ul class="pagination">
                    <li><a href="/subjects?page=0">&laquo;</a></li>
                    <c:forEach var="current" begin="0" end="${maxPage}">
                        <li><a href="/subjects?page=${current}">${current+1}</a></li>
                    </c:forEach>
                    <li><a href="/subjects?page=${maxPage}">&raquo;</a></li>
                </ul>
            </div>
        </div>
    </c:if>
</t:layout>