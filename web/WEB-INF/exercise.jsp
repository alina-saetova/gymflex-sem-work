<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 25/10/2019
  Time: 9:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
    <script type="text/javascript">
        function like(id) {
            $.ajax({
                type: "POST",
                url: "/like_article",
                data: {
                    type : "exercise",
                    exercise_id : id
                }
            })
        }
    </script>
</head>
<body>
    <p><c:out value="${exercise.getName()}"/></p>
    <c:forEach var="com" items="${comms}">
        <p>${com.getContent()}</p>
    </c:forEach>
    <form method="post">
        <p><b>Введите ваш отзыв:</b></p>
        <p><textarea rows="10" cols="45" name="text"></textarea></p>
        <p><input type="submit" value="Отправить"></p>
    </form>
<%--    проверяет наличие лайка--%>
    <div id="like_button">
        <c:if test="${flag.equals('true')}">
            <p>сохранено</p>
        </c:if>
        <c:if test="${flag.equals('false')}">
            <form method="post">
                <input type="button" value="типа лайк" onclick="like(${exercise.getId()})">
            </form>
        </c:if>
        <c:if test="${flag.equals('no_auth')}">
            <p>юзер не авторизован</p>
        </c:if>
    </div>
</body>
</html>
