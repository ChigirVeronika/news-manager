<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <tiles:importAttribute name="header.title" />
    <title><spring:message code="header.title"/></title>

    <link rel="stylesheet" type="text/css"
          href="<c:url value='/asserts/css/main.css'/>" media="all">
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/asserts/css/multiple-select.css'/>" media="all">
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/asserts/css/tiles.css'/>" media="all">
</head>
<body>
<div class="page-tile">
    <div class="header-tile">
        <tiles:insertAttribute name="header"/>
    </div>
    <div class="content-tile">
        <div class="menu-tile">
            <tiles:insertAttribute name="menu"/>
        </div>
        <div class="body-tile">
            <tiles:insertAttribute name="body" />
        </div>
    </div>
    <div class="footer-tile">
        <tiles:insertAttribute name="footer"/>
    </div>
</div>
</body>
</html>