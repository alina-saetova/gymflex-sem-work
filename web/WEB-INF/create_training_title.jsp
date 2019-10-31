<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 30/10/2019
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${flag == false}">
        <p>Войдите, чтобы получить возможность создать свою тренировку</p>
        <form action="/login">
            <button>Войти</button>
        </form>
    </c:if>
    <p>rr</p>
    <c:if test="${flag == true}">
        <form method="post">
            <input type="text" name="training_name" placeholder="название тренировки">
            <input type="number" name="exercises_number" placeholder="кол-во упражнений">
            <input type="submit" value="Далее">
        </form>
    </c:if>
</body>
</html>
