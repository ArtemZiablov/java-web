<!-- WebContent/login.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Авторизація</title>
</head>
<body>
<h2>Авторизація</h2>
<form action="login" method="post">
    <label for="username">Логін:</label>
    <input type="text" id="username" name="username" required/><br/><br/>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required/><br/><br/>
    <input type="submit" value="Увійти"/>
</form>
<br/>
<a href="registration.jsp">Не маєте акаунту? Зареєструйтесь тут.</a>
<br/><br/>
<span style="color:red;">
        <c:if test="${not empty error}">
            ${error}
        </c:if>
    </span>
</body>
</html>
