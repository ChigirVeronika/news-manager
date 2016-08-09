<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.text" var="lang"/>

<html>
<head>
    <title><fmt:message key="header.title" /></title>

    <link rel="stylesheet" type="text/css" href="/css/style.css" media="all">

</head>
<body>
<div class="flex-content">
    <tiles:insertAttribute name="header" />
    <div class="body">
        <div class="inner-body">
            <tiles:insertAttribute name="body" />
        </div>
    </div>
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>