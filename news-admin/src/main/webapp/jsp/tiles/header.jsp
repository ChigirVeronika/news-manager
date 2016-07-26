<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="header">
    <div class="title">
        <spring:message code="header.title"/>
    </div>
    <div class="language-panel">
        <a href="?language=en"><spring:message code="en" /></a>
        <a href="?language=ru"><spring:message code="ru" /></a>
    </div>
</div>