<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />

<html>
<head>
    <title>Результаты формы</title>
</head>
<body>
<h2>Результати</h2>
<p>Ім'я: ${name}</p>
<p>Вік: ${age}</p>

<h3>Обрані фрукти:</h3>
<ul>
    <c:if test="${selectedFruits != null}">
        <c:forEach var="fruit" items="${selectedFruits}">
            <li>${fruit}</li>
        </c:forEach>
    </c:if>
    <c:if test="${selectedFruits == null}">
        <li>Фруктів не обрано.</li>
    </c:if>
</ul>
</body>
</html>

<jsp:include page="footer.jsp" />
