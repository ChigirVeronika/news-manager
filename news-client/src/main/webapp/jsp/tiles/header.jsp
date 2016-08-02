<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="text" />
<html>
<body>
<div class="header clearfix">
    <h1 class="header-text"><fmt:message key="header.title" /></h1>
    <div class="header-language">
        <a href="?language=en"><fmt:message key="en" /></a>
        <a href="?language=ru"><fmt:message key="ru" /></a>
    </div>
</div>
</body>
</html>
