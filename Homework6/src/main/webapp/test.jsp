<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>JSP Tag Testing</title>
</head>
<body>

<h2>Привіт, ${userName}!</h2> <!-- Відображаємо ім'я користувача -->

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

<fmt:setLocale value="uk_UA" />
<p>Гривні: <fmt:formatNumber value="123456.789" type="currency" /></p>

<br>

<fmt:setLocale value="en_US" />
<p>Долари США: <fmt:formatNumber value="123456.789" type="currency" /></p>

<br>

<fmt:setLocale value="de_DE" />
<p>Євро: <fmt:formatNumber value="123456.789" type="currency" /></p>

<br>

<fmt:setLocale value="en_GB" />
<p>Фунти стерлінгів: <fmt:formatNumber value="123456.789" type="currency" /></p>

<br>

<p>Число з двома знаками після коми: <fmt:formatNumber value="123456.789" type="number" minFractionDigits="2" /></p>

<br>

<!-- Форматування відсотків -->
<p>Відсоток: <fmt:formatNumber value="0.75" type="percent" /></p>

<p>Відсоток із двома знаками після коми: <fmt:formatNumber value="0.7567" type="percent" minFractionDigits="2" maxFractionDigits="2" /></p>



<br>

<!-- Форматування дати та часу -->
<%
    java.util.Date currentDate = new java.util.Date(); // Получаем текущую дату и время
    request.setAttribute("currentDate", currentDate);  // Передаем дату в request
%>

<p>Поточна дата та час (український формат): <fmt:setLocale value="uk_UA" /><fmt:formatDate value="${currentDate}" type="both" dateStyle="long" timeStyle="medium" /></p>

<p>Поточна дата та час (США формат): <fmt:setLocale value="en_US" /><fmt:formatDate value="${currentDate}" type="both" dateStyle="long" timeStyle="medium" /></p>

<p>Поточна дата та час (німецький формат): <fmt:setLocale value="de_DE" /><fmt:formatDate value="${currentDate}" type="both" dateStyle="long" timeStyle="medium" /></p>

</body>
</html>