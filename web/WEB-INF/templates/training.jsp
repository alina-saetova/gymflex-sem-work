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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/styles.css" type="text/css">
    <script src="../js/like.js"></script>
    <title>Тренировка</title>
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
                    text : $("#textarea1").val()
                },
                dataType: "json",
                success: function (msg) {
                    if (msg.objects.length > 0) {
                        $("#comments_cont").append("<li class=\"media\">\n" +
                            "                    <div class=\"media-left\">\n" +
                            "                        <a href=\"#\">\n" +
                            "                            <img class=\"media-object rounded-circle\" src=\"" + msg.objects[0].user.photo + "\" alt=\"...\">\n" +
                            "                        </a>\n" +
                            "                    </div>\n" +
                            "                    <div class=\"media-body\">\n" +
                            "                        <div class=\"panel panel-info\">\n" +
                            "                            <div class=\"panel-heading\">\n" +
                            "                                <div class=\"author\">" + msg.objects[0].user.firstName + " " + msg.objects[0].user.lastName + "</div>\n" +
                            "                                <div class=\"metadata\">\n" +
                            "                                    <span class=\"date\">" + msg.objects[0].date.toString() + "</span>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                            <div class=\"panel-body\">\n" +
                            "                                <div class=\"media-text text-justify\">" + msg.objects[0].content + "</div>\n" +
                            "                            </div>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                </li>");
                    }
                }
            })
        }
    </script>
</head>
<body class="body-with-img">
<%@include file= "includes/nav.jsp"%>
<%--    <p><c:out value="${training.getName()}"/></p>--%>
<%--    <div id="resptext">--%>
<%--        <c:forEach var="com" items="${comms}">--%>
<%--            <p>${com.getContent()}</p>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>
<%--    <form method="post">--%>
<%--        <b>Введите ваш отзыв:</b>--%>
<%--        <br>--%>
<%--        <textarea rows="10" cols="45" name="text" id="comment"></textarea>--%>
<%--        <br>--%>
<%--        <input type="button" value="Отправить" onclick="send_comment(${training.getId()})">--%>
<%--    </form>--%>
<%--    проверяет наличие лайка&ndash --%>
<%--    <c:if test="${flag.equals('true')}">--%>
<%--        <p>сохранено</p>--%>
<%--    </c:if>--%>
<%--    <c:if test="${flag.equals('false')}">--%>
<%--        <form method="post">--%>
<%--            <input type="button" value="типа лайк" onclick="like(${training.getId()})">--%>
<%--        </form>--%>
<%--    </c:if>--%>
<%--    <c:if test="${flag.equals('no_auth')}">--%>
<%--        <p>юзер не авторизован</p>--%>
<%--    </c:if>--%>
<div class="container bootstrap snippet">
    <div class="row">
        <div class="col-sm-10"><h1 class="my-4">     </h1></div>
    </div>
    <div class="row">
        <div class="col-lg-4 col-sm-6 mb-4">
            <div class="list-group-item tr">
                <div class='like'>
                    <button class='like-toggle basic'> ♥</button>
                    <span class='hidden'>${training.getCnt_likes()} likes</span>
                </div>
            </div>
            <c:forEach var="ex" items="${exercises}">
                <div class="list-group-item tr">
                    <a href="/exercise?id=${ex.getId()}" class="list-group-item col-sm-12">${ex.getName()}</a>
                </div>
            </c:forEach>
        </div>
        <div class="col-lg-8 col-sm-12">
            <div class="card">
                <div class="card-body">
<%--                    пофиксить отображение названия тренировки--%>
                    <div class="col-sm-10"><h1 class="my-4" style="text-align: center">${training.getName()} </h1></div>
                    <p class="card-text">${training.getInfo()}</p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="comments">
        <h3 class="title-comments">Комментарии </h3>
        <ul class="media-list" id="comments_cont">
            <!-- Комментарий (уровень 1) -->
            <c:forEach var="com" items="${comms}">
                <li class="media">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object rounded-circle" src="${com.getUser().getPhoto()}" alt="...">
                        </a>
                    </div>
                    <div class="media-body">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <div class="author">${com.getUser().getFirstName()} ${com.getUser().getLastName()}</div>
                                <div class="metadata">
                                    <span class="date">${com.getDate()}</span>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="media-text text-justify">${com.getContent()}</div>
                            </div>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <c:if test="${user != null}">
            <div class="form-group panel-body">
                <form method="post">
                    <label for="textarea1"><h6>Добавьте комментарий</h6></label>
                    <textarea class="form-control" id="textarea1"></textarea>
                    <script>
                        document.getElementById("textarea1").onkeyup = function() {
                            var x = document.getElementById("textarea1");
                            if (x.value.length > 200) {
                                x.value = x.value.substring(0, 200);
                            }
                        }
                    </script>
                    <input type="button" class="btn" value="Прокомментировать" onclick="send_comment(${training.getId()})">
                </form>
            </div>
        </c:if>
        <c:if test="${user == null}">
            <div class="form-group alt">
                <form action ="/login">
                    <button class="btn btn-sm btn-comment">Войдите, чтобы прокомментировать</button>
                </form>
            </div>
        </c:if>
    </div>
</div>
<%@include file= "includes/footer.jsp"%>
</body>
</html>
