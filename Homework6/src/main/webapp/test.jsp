<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>JSP Tag Testing</title>
</head>
<body>

<h2>Привет, ${userName}!</h2> <!-- Відображаємо ім'я користувача -->

<h2>Умовні теги</h2>

<c:set var="isLoggedIn" value="true" />
<c:if test="${isLoggedIn}">
    <p>Користувач увійшов в систему</p>
</c:if>
<c:if test="${!isLoggedIn}">
    <p>Користувач не увійшов в систему</p>
</c:if>

<h2>Теги ітератори (Передані фрукти):</h2>
<ul>
    <c:forEach var="fruit" items="${fruits}">
        <li>${fruit}</li>
    </c:forEach>
</ul>

<h2>Форматування валют, дат, часу та чисел</h2>

<!-- Форматування валюти -->
<fmt:setLocale value="uk_UA" />
<fmt:formatNumber value="123456.789" type="currency" />

<br><br>

<!-- Форматування чисел -->
<fmt:formatNumber value="123456.789" type="number" minFractionDigits="2" />

<br><br>

<!-- Форматування відсотків -->
<fmt:formatNumber value="0.75" type="percent" />

<br><br>

<!-- Форматування дати та часу -->
<%
    java.util.Date currentDate = new java.util.Date(); // Получаем текущую дату и время
    request.setAttribute("currentDate", currentDate);  // Передаем дату в request
%>

<fmt:formatDate value="${currentDate}" type="both" dateStyle="long" timeStyle="medium" />

</body>
</html>
