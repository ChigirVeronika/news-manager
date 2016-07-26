<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="menu">

    <img src="<c:url value='/asserts/images/arrow.png'/>"   style="width:20px;height:18px;" >
    <a href="<c:url value='/newsList' />" style="color: blue;"><spring:message code="menu.news_list"/></a>
    <br>
    <br>
    <img src="<c:url value='/asserts/images/arrow.png'/>"   style="width:20px;height:18px;" >
    <a href="<c:url value='/addNews' />" style="color: blue;"><spring:message code="menu.add_news"/></a>
    <br>
    <br>
    <img src="<c:url value='/asserts/images/arrow.png'/>"   style="width:20px;height:18px;" >
    <a href="<c:url value='/authorPage' />" style="color: blue;"><spring:message code="menu.add_update_authors"/></a>
    <br>
    <br>
    <img src="<c:url value='/asserts/images/arrow.png'/>"   style="width:20px;height:18px;" >
    <a href="<c:url value='/tagPage' />" style="color: blue;"><spring:message code="menu.add_update_tags"/></a>
    <br>
    <br>
</div>

<script src="<c:url value="/resources/js/menuscript.js"/>"></script>