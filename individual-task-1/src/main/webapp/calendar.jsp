<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="https://example.com/tags/displayEvents" prefix="de" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Календар Подій</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #000;
            width: 14%;
            vertical-align: top;
            height: 100px;
            padding: 5px;
        }
        th {
            background-color: #f2f2f2;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        .add-event {
            margin-bottom: 20px;
        }
        .navigation {
            margin-bottom: 20px;
        }
        .week-info {
            font-size: 1.2em;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h2>Календар Подій</h2>
<p>Ласкаво просимо, ${sessionScope.username}!</p>
<a href="logout">Вийти</a>
<br/><br/>
<div class="navigation">
    <form action="calendar" method="get" style="display:inline;">
        <input type="hidden" name="weekStart" value="${prevWeekStart}" />
        <input type="submit" value="Попередній тиждень" />
    </form>
    <form action="calendar" method="get" style="display:inline; margin-left: 10px;">
        <input type="hidden" name="weekStart" value="${nextWeekStart}" />
        <input type="submit" value="Наступний тиждень" />
    </form>
</div>
<div class="add-event">
    <a href="addEvent.jsp">Додати Нову Подію</a>
</div>

<!-- Відображення номера тижня -->
<div class="week-info">
    <p>Номер тижня: <c:out value="${weekNumber}" /></p>
</div>

<c:if test="${empty weekDates}">
    <p style="color:red;">Список дат тижня порожній!</p>
</c:if>

<table>
    <tr>
        <th>Понеділок</th>
        <th>Вівторок</th>
        <th>Середа</th>
        <th>Четвер</th>
        <th>П'ятниця</th>
        <th>Субота</th>
        <th>Неділя</th>
    </tr>
    <tr>
        <c:forEach var="date" items="${weekDates}" varStatus="status">
            <td>
                <strong>${date}</strong>
                <ul>
                    <de:displayEvents date="${date}" />
                </ul>
            </td>
        </c:forEach>
    </tr>
</table>
</body>
</html>
