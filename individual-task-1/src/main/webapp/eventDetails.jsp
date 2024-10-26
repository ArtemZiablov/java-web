<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Деталі Події</title>
</head>
<body>
<h2>Деталі Події</h2>
<p><strong>Назва:</strong> ${event.name}</p>
<p><strong>Дата:</strong> ${event.date}</p>
<p><strong>Час:</strong> ${event.time}</p>
<p><strong>Місце:</strong>
    <c:choose>
        <c:when test="${not empty event.place}">
            ${event.place}
        </c:when>
        <c:otherwise>
            Онлайн: <a href="${event.conferenceLink}" target="_blank">Перейти до конференції</a>
        </c:otherwise>
    </c:choose>
</p>
<p><strong>Опис:</strong> ${event.description}</p>
<br/>
<a href="calendar">Назад до календаря</a>
</body>
</html>
