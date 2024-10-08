<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />

<html>
<head>
    <title>Приклад форми JSP</title>
</head>
<body>
<h2>Форма для передачі даних</h2>
<form action="processForm" method="post">
    Ім'я: <input type="text" name="name"><br><br>
    Вік: <input type="number" name="age"><br><br>

    Виберіть улюблені фрукти (можна кілька):<br>
    <input type="checkbox" name="fruits" value="apple">Яблуко<br>
    <input type="checkbox" name="fruits" value="banana">Банан<br>
    <input type="checkbox" name="fruits" value="orange">Апельсин<br><br>

    <input type="submit" value="Надіслати">
</form>
</body>
</html>

<jsp:include page="footer.jsp" />
