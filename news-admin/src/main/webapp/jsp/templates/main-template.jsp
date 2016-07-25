<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

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

        </div>
    </div>
    <div class="footer-tile">
        <tiles:insertAttribute name="footer"/>
    </div>
</div>
</body>
</html>