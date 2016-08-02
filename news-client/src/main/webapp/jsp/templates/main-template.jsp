<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title><fmt:message key="header.title" /></title>

    <link rel="stylesheet" type="text/css" href="css/style.css" media="all">
    <link rel="stylesheet" type="text/css" href="lib/multiselect/multiple-select.css" media="all">

    <script type="text/javascript" src="lib/jquery/jquery-3.0.0.min.js" defer></script>
    <script type="text/javascript" src="lib/multiselect/multiple-select.js" defer></script>
    <script type="text/javascript" src="js/multiselect.js" defer></script>
    <script type="text/javascript" src="js/page-number-select.js" defer></script>
</head>
<body>
<div class="nm-flex-content">
    <tiles:insertAttribute name="header" />
    <div class="nm-body">
        <div class="nm-inner-body">
            <tiles:insertAttribute name="body" />
        </div>
    </div>
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>