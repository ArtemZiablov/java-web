<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Event Details</title>
</head>
<body>
<h2>Event Details</h2>
<p><strong>Name:</strong> ${event.name}</p>
<p><strong>Date:</strong> ${event.date}</p>
<p><strong>Time:</strong> ${fn:substring(event.time, 0,5)}</p>

<c:if test="${not empty event.place}">
    <p><strong>Place:</strong> ${event.place}</p>
</c:if>

<c:if test="${not empty event.conferenceLink}">
    <p><strong>Online:</strong> <a href="${event.conferenceLink}" target="_blank">Join Conference</a></p>
</c:if>

<p><strong>Description:</strong> ${event.description}</p>
<br/>
<a href="${pageContext.request.contextPath}/calendar">Back to Calendar</a>
</body>
</html>
