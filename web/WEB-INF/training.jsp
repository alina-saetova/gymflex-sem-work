<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 25/10/2019
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p><c:out value="${training.getName()}"/></p>
    <c:forEach var="com" items="${comms}">
        <p>${com.getContent()}</p>
    </c:forEach>
    <form method="post">
        <p><b>Введите ваш отзыв:</b></p>
        <p><textarea rows="10" cols="45" name="text"></textarea></p>
        <p><input type="submit" value="Отправить"></p>
    </form>
</body>
</html>
