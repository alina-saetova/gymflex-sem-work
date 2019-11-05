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
    <script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
    <script type="text/javascript">
        function like(id) {
            $.ajax({
                type: "POST",
                url: "/like_article",
                data: {
                    type : "training",
                    training_id : id
                }
            })
        }
        function send_comment(tr_id) {
            $.ajax({
                type: "POST",
                url: "/commentary",
                data: {
                    id : tr_id,
                    type : "training",
                    text : $("#comment").val()
                },
                dataType: "json",
                success: function (msg) {
                    if (msg.objects.length > 0) {
                        $("#resptext").append("<p>" + msg.objects[0].content + "</p>");
                    } else {
                        $("#resptext").html("No results..");
                    }
                }
            })
        }
    </script>
</head>
<body>
    <p><c:out value="${training.getName()}"/></p>
    <div id="resptext">
        <c:forEach var="com" items="${comms}">
            <p>${com.getContent()}</p>
        </c:forEach>
    </div>
    <form method="post">
        <b>Введите ваш отзыв:</b>
        <br>
        <textarea rows="10" cols="45" name="text" id="comment"></textarea>
        <br>
        <input type="button" value="Отправить" onclick="send_comment(${training.getId()})">
    </form>
    <%--    проверяет наличие лайка--%>
    <c:if test="${flag.equals('true')}">
        <p>сохранено</p>
    </c:if>
    <c:if test="${flag.equals('false')}">
        <form method="post">
            <input type="button" value="типа лайк" onclick="like(${training.getId()})">
        </form>
    </c:if>
    <c:if test="${flag.equals('no_auth')}">
        <p>юзер не авторизован</p>
    </c:if>
</body>
</html>
