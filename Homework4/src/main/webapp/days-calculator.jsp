<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 08.10.2024
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Підрахунок кількості прожитих днів</title>
</head>
<body>
<h1>Поточна дата і час</h1>
<p>
    <%= java.time.LocalDateTime.now() %>
</p>

<h2>Введіть вашу дату народження</h2>
<form action="calculate-days" method="post">
    <label for="birthdate">Дата народження (формат: рррр-мм-дд):</label>
    <input type="date" id="birthdate" name="birthdate" required>
    <input type="submit" value="Підрахувати дні">
</form>

<c:if test="${not empty result}">
    <h2>${result}</h2>
</c:if>

</body>
</html>
