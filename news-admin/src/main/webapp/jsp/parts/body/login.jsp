<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<c:url var="loginUrl" value="/login"/>
<form:form class="nm-login-area clearfix"
           action="${loginUrl}" method="post">
    <div class="nm-block nm-login-line nm-text-align-right">
        <label class="nm-float-left" for="login"><spring:message code="login" /></label>
        <input id="login" name="username" required=""/>
    </div>
    <div class="nm-block nm-login-line nm-text-align-right">
        <label class="nm-float-left" for="password"><spring:message code="password" /></label>
        <input id="password" name="password" type="password" required=""/>
    </div>
    <c:if test="${not empty error}">
        <div class="nm-error-message">
            <spring:message code="${error}"/>
        </div>
    </c:if>
    <button class="nm-news-style-button nm-login-button">
        <spring:message code="button.login" />
    </button>
</form:form>
</body>
</html>
