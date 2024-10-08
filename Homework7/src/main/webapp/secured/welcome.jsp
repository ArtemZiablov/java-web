<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Вітаємо</title>
</head>
<body>
<h2>Ласкаво просимо, ${sessionScope.user}!</h2>
<p>Це захищена сторінка. Тільки авторизовані користувачі можуть її бачити.</p>

<form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Вийти">
</form>
</body>
</html>