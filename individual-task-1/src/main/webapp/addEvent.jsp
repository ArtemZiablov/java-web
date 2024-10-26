<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Додати Подію</title>
</head>
<body>
<h2>Додати Нову Подію</h2>
<form action="addEvent" method="post">
    <label for="name">Назва:</label>
    <input type="text" id="name" name="name" required/><br/><br/>

    <label for="date">Дата:</label>
    <input type="date" id="date" name="date" required/><br/><br/>

    <label for="time">Час:</label>
    <input type="time" id="time" name="time" required/><br/><br/>

    <label for="description">Опис:</label><br/>
    <textarea id="description" name="description" rows="4" cols="50" required></textarea><br/><br/>

    <label for="place">Місце:</label>
    <input type="text" id="place" name="place"/><br/><br/>

    <label for="conferenceLink">Посилання на конференцію:</label>
    <input type="text" id="conferenceLink" name="conferenceLink"/><br/><br/>

    <!-- Прихований параметр для збереження поточного тижня -->
    <input type="hidden" name="weekStart" value="${currentWeekStart}" />

    <input type="submit" value="Додати Подію"/>
</form>
<br/>
<a href="calendar">Назад до календаря</a>

<!-- Відображення помилок -->
<br/>
<span style="color:red;">
    <c:if test="${not empty error}">
        ${error}
    </c:if>
</span>
</body>
</html>
