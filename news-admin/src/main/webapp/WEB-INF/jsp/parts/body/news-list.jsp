<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <select>
        <c:forEach var="author" items="${authors}">
            <option>${author.name}</option>
        </c:forEach>
    </select>
    <select id="dropdown">
        <c:forEach var="tag" items="${tags}">
            <option>${tag.name}</option>
        </c:forEach>
    </select>
    <button>Filter</button>
    <button>Reset</button>
</div>
<div>
    <c:forEach var="newsDTO" items="${newsDTOList}">
        <div class="news">
            <div>${newsDTO.news.title}</div>
            <div>by ${newsDTO.author.name}</div>
            <div>
                <fmt:formatDate type="date" value="${newsDTO.news.creationDate}"/></div>
            <br/>
            <div>
                    ${newsDTO.news.shortText}
            </div>
            <spring:url value="/news/message/${newsDTO.news.id}" var="viewMessage"/>
            <div><a href="${viewMessage}">View</a></div>
            <div>Comments(${newsDTO.commentcount})</div>
            <div>
                <c:forEach var="tag" items="${newsDTO.tags}"><
                    ${tag.name}
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
<div class="pagination">
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src=<c:url value=""/>></script>
