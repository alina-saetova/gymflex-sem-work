<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 30/10/2019
  Time: 8:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание своей тренировки</title>
</head>
<body>
    <p>${name}</p>
    <form method="post">
        <input type="hidden" name="ex_num" value="${ex_num}">
        <input type="hidden" name="tr_id" value="${tr_id}">
        <c:forEach var="i" begin="1" end="${ex_num}" step="1">
            <input type="text" name="${i}name" placeholder="название упражнения">
            <br>
            <input type="text" name="${i}reps" placeholder="кол-во повторений">
            <br>
        </c:forEach>
        <input type="submit" value="сохранить">
    </form>
</body>
</html>
