<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="pagecontent" />

<html lang="${language}">
<head>
    <title><fmt:message key="header.title" /></title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" media="all">

    <link rel="stylesheet" type="text/css" href="/css/jquery.multiselect.css"/>
    <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"/>
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
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