<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="pagecontent" />

<html lang="${language}">
<body>
<div class="center-block">
    <h1><fmt:message key="error.message" /></h1>
    <a href="<c:url value="/jsp/news-list-page.jsp"/>"><fmt:message key="news.home" /></a>
</div>
</body>
</html>
