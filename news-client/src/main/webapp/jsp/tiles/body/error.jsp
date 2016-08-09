<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.text" />
<html>
<body>
<div class="center-block">
    <h1><fmt:message key="error.message" /></h1>
    <a href="${command}"><fmt:message key="news.back" /></a>
</div>
</body>
</html>
