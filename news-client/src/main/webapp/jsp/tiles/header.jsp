<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="i18n.text" />

<div class="page-header">
    <h1 class="header-title"><fmt:message key="header.title" /></h1>
    <div class="header-lang-panel">
        <a href="?language=en"><fmt:message key="en" /></a>
        <a href="?language=ru"><fmt:message key="ru" /></a>
        <label for="option-1">
            <input type="radio" id="option-1" onchange="submit()" name="language" value="en" ${language == 'en' ? 'checked' : ''}>
            <span>EN</span>
        </label>
        <label  for="option-2">
            <input type="radio" id="option-2" onchange="submit()" name="language" value="ru" ${language == 'ru' ? 'checked' : ''}>
            <span>RU</span>
        </label>
    </div>
</div>

