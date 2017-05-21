<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="col-sm-5">
        <div class="panel panel-default">
            <div class="panel-body">

                <div class="row">
                    <div class="col-sm-4">
                        <span class="text-muted">
                            <spring:message code="label.person.name"/>:
                        </span>
                    </div>
                    <div class="col-sm-8">
                        <span class="text-info">
                                ${account.lastName} ${account.firstName} ${account.middleName}
                        </span>
                    </div>
                </div>
                <c:if test="${student!=null}">
                    <div class="row">
                        <div class="col-sm-4">
                        <span class="text-muted">
                            <spring:message code="label.record.book.number"/>:
                        </span>
                        </div>
                        <div class="col-sm-8">
                        <span class="text-info">
                                ${student.recordBookNumber}
                        </span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4">
                        <span class="text-muted">
                            <spring:message code="label.finance.type"/>:
                        </span>
                        </div>
                        <div class="col-sm-8">
                        <span class="text-info">
                                ${student.financeType}
                        </span>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</t:layout>