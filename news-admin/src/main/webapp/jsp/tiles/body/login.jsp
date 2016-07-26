<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <c:url var="loginUrl" value="/login"/>
    <form:form action="${loginUrl}" method="post">
        <div>
            <label for="login"><spring:message code="login"/></label>
            <input id="login" name="username" required=""/>
        </div>
        <div>
            <label for="password"><spring:message code="password"/></label>
            <input id="password" name="password" type="password" required=""/>
        </div>
        <c:if test="${not empty error}">
            <div>
                <spring:message code="${error}"/>
            </div>
        </c:if>
        <button>
            <spring:message code="button.login"/>
        </button>
    </form:form>
</div>
