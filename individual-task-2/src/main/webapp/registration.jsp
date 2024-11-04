<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Реєстрація</title>
</head>
<body>
<h2>Реєстрація</h2>
<form action="register" method="post">
    <label for="username">Логін:</label>
    <input type="text" id="username" name="username" required/><br/><br/>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required/><br/><br/>
    <label for="confirmPassword">Підтвердження пароля:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required/><br/><br/>
    <input type="submit" value="Зареєструватися"/>
</form>
<br/>
<a href="login.jsp">Вже маєте акаунт? Увійдіть тут.</a>
<br/><br/>
<span style="color:red;">
    <c:if test="${not empty error}">
        ${error}
    </c:if>
</span>
</body>
</html>
