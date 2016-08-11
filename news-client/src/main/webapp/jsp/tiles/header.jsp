<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="pagecontent" />

<div class="page-header">
    <h1 class="header-title"><fmt:message key="header.title"/></h1>
    <div class="header-lang-panel">
        <a href="/main?command=change_language&language=en&page=${pageContext.request.servletPath}"><fmt:message key="en" /></a>
        <a href="/main?command=change_language&language=ru&page=${pageContext.request.servletPath}"><fmt:message key="ru" /></a>
    </div>
</div>

