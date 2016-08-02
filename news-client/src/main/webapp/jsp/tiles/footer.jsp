<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="text" />
<html>
<body>
<div class="footer">
    <fmt:message key="footer.copyright" />
</div>
</body>
</html>
