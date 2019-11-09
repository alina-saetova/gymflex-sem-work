<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 25/10/2019
  Time: 8:47 AM
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
    <title>Профиль</title>
    <script type="text/javascript">
        function save() {
            var fname = document.getElementById("firstname").value;
            var lname = document.getElementById("lastname").value;
            var login = document.getElementById("login").value;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    alert("успешно сохранено")
                }
            };
            xmlhttp.open("POST", "/edit_profile", true);
            xmlhttp.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded");
            xmlhttp.send("firstname=" + fname + "&lastname=" + lname + "&login=" + login);
        }
        function change_password() {
            var oldp = document.getElementById("old_password").value;
            var newp = document.getElementById("new_password").value;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    if (xmlhttp.responseText == 1) {
                        alert("успешно сохранено")
                    }
                    else {
                        alert("введен неправильный старый пароль")
                    }
                }
            };
            xmlhttp.open("POST", "/change_password", true);
            xmlhttp.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded");
            xmlhttp.send("oldpassword=" + oldp + "&newpassword=" + newp);
        }
        function delete_training(s_tr) {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("POST", "/delete_article", true);
            xmlhttp.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded");
            xmlhttp.send("type=training" + "&id=" + s_tr);
        }
        function delete_exercise(s_ex) {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("POST", "/delete_article", true);
            xmlhttp.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded");
            xmlhttp.send("type=exercise" + "&id=" + s_ex);
        }
        function delete_own_training(c_tr) {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("POST", "/delete_article", true);
            xmlhttp.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded");
            xmlhttp.send("type=own_training" + "&id=" + c_tr);
        }
        function remove(id) {
            document.getElementById(id).remove();
        }
    </script>
</head>
<body class="body-with-img">
<%@include file= "includes/nav.jsp"%>
<%--    <form method="post">--%>
<%--        <p>First name:</p>--%>
<%--        <input type="text" id="firstname" value="${user.getFirstName()}">--%>
<%--        <p>Last name:</p>--%>
<%--        <input type="text" id="lastname" value="${user.getLastName()}">--%>
<%--        <p>Login:</p>--%>
<%--        <input type="text" id="login" value="${user.getLogin()}">--%>
<%--        <input type="button" name="rewrite" onclick="save()" value="сохранить">--%>
<%--    </form>--%>
<%--    <img src="${user.getPhoto()}" width="200">--%>
<%--    <form method="post">--%>
<%--        <p>Old password:</p>--%>
<%--        <input type="text" id="old_password">--%>
<%--        <p>New password:</p>--%>
<%--        <input type="text" id="new_password">--%>
<%--        <input type="button" name="rewrite" onclick="change_password()" value="изменить">--%>
<%--    </form>--%>

<%--    <p>Сохраненные тренировки</p>--%>
<%--    <c:forEach var="s_tr" items="${saved_trainings}">--%>
<%--        <div id="${s_tr.getId()}">--%>
<%--            <p>${s_tr.getName()}</p>--%>
<%--            <input type="button" value="Удалить" onclick="delete_training(${s_tr.getId()}); remove(${s_tr.getId()})">--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <p>Сохраненные упражнения</p>--%>
<%--    <c:forEach var="s_ex" items="${saved_exercises}">--%>
<%--        <div id="${s_ex.getId()}">--%>
<%--            <p>${s_ex.getName()}</p>--%>
<%--            <input type="button"  value="Удалить" onclick="delete_exercise(${s_ex.getId()});remove(${s_ex.getId()})">--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <p>Свои тренировки</p>--%>
<%--    <c:forEach var="c_tr" items="${map}">--%>
<%--        <div id="${c_tr.key.getId()}">--%>
<%--            <p>${c_tr.key.getName()}</p>--%>
<%--            <c:forEach var="c_ex" items="${c_tr.value}">--%>
<%--            <p>${c_ex.getName()} --- ${c_ex.getReps()}</p>--%>
<%--            </c:forEach>--%>
<%--            <input type="button"  value="Удалить" onclick="delete_own_training(${c_tr.key.getId()});remove(${c_tr.key.getId()})">--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--    <form method="post">--%>
<%--        <input type="submit" value="log out" formaction="/logout">--%>
<%--    </form>--%>
<div class="container bootstrap snippet">
    <div class="row">
        <div class="col-sm-10"><h1 class="my-4"><strong>Профиль</strong></h1></div>
    </div>
    <div class="row">
        <div class="col-lg-3 col-sm-6 mb-4">
            <div class="list-group ">
                <form action="" method="post">
                    <div class="list-group-item tr">
                        <img id="profile-img" src="${user.getPhoto()}" alt="img" class="rounded-circle" width="180"
                             height="180">
                    </div>
                    <div class="list-group-item tr">
                        <h5><strong>Имя</strong></h5>
                    </div>
                    <div class="list-group-item tr">
                        <input class="input-tr" type="text" id="firstname" value="${user.getFirstName()}"/>
                    </div>
                    <div class="list-group-item tr">
                        <h5><strong>Фамилия</strong></h5>
                    </div>
                    <div class="list-group-item tr">
                        <input class="input-tr" type="text" id="lastname" value="${user.getLastName()}"/>
                    </div>
                    <div class="list-group-item tr">
                        <h5><strong>Логин</strong></h5>
                    </div>
                    <div class="list-group-item tr">
                        <input class="input-tr" type="text" id="login" value="${user.getLogin()}"/>
                    </div>
                    <div class="list-group-item tr">
                        <input type="button" value="Изменить данные" class="btn" onclick="save()">
                    </div>
                </form>
            </div>
            <div class="list-group py-4">
                <form action="" method="post">
                    <div class="list-group-item tr">
                        <h5>Старый пароль</h5>
                    </div>
                    <div class="list-group-item tr">
                        <input class="input-tr" id="old_password" type="text" value=""/>
                    </div>
                    <div class="list-group-item tr">
                        <h5>Новый пароль</h5>
                    </div>
                    <div class="list-group-item tr">
                        <input class="input-tr"  id="new_password" type="text" value=""/>
                    </div>
                    <div class="list-group-item tr">
                        <input type="button" value="Изменить пароль" class="btn" onclick="change_password()">
                    </div>
                </form>
            </div>
        </div>
        <div class="col-lg-9 col-sm-12">
            <div class="list-group">
                <c:if test="${saved_trainings.size() != 0}">
                    <h3 class="my-2"><strong>Сохраненные тренировки</strong></h3>
                    <c:forEach var="s_tr" items="${saved_trainings}">
                        <form method="post">
                            <div class="list-group-item tr d-flex justify-content-between" id="${s_tr.getId()}">
                                <a href="/training?id=${s_tr.getId()}" class="list-group-item col-sm-10">${s_tr.getName()}</a>
                                <input type="button" class="btn col-sm-1" value="x" onclick="delete_training(${s_tr.getId()}); remove(${s_tr.getId()})">
                             </div>
                        </form>
                    </c:forEach>
                </c:if>
                <c:if test="${saved_exercises.size() != 0}">
                    <h3 class="my-2"><strong>Сохраненные упражнения</strong></h3>
                    <c:forEach var="s_ex" items="${saved_exercises}">
                        <div class="list-group-item tr d-flex justify-content-between" id="${s_ex.getId()}">
                            <a href="/exercise?id=${s_ex.getId()}" class="list-group-item col-sm-10">${s_ex.getName()}</a>
                            <input type="button" class="btn col-sm-1" value="x" onclick="delete_exercise(${s_ex.getId()}); remove(${s_ex.getId()})">
                        </div>
                    </c:forEach>
                </c:if>
                <hr>
                <c:if test="${map.size() != 0}">
                    <h3 class="my-2"><strong>Мои тренировки</strong></h3>
                    <c:forEach var="c_tr" items="${map}">
                        <form method="post">
                            <div class="list-group-item tr flex-container" id="${c_tr.key.getId()}">
                                <div class="d-flex justify-content-between">
                                    <h3>${c_tr.key.getName()}</h3>
                                    <input type="button" class="btn col-sm-1" value="x" onclick="delete_own_training(${c_tr.key.getId()});remove(${c_tr.key.getId()})"/>
                                </div>
                                <hr>
                                <div class="input-group form-group justify-content-around">
                                    <h6 class="col-sm-7">Упражнение</h6>
                                    <h6>Повторения/подходы</h6>
                                </div>
                                <c:forEach var="c_ex" items="${c_tr.value}">
                                    <div class="d-flex justify-content-between">
                                        <a class="list-group-item col-sm-7">${c_ex.getName()}</a>
                                        <a class="list-group-item col-sm-4">${c_ex.getReps()}</a>
                                    </div>
                                </c:forEach>
                            </div>
                            <hr>
                        </form>
                    </c:forEach>
                </c:if>
                <c:if test="${saved_exercises.size() == 0 && saved_trainings.size() == 0 && map.size() == 0}">
                        <%--       то что-то отобразить             --%>
                </c:if>
            </div>
        </div>
    </div>
</div>
<%@include file= "includes/footer.jsp"%>
</body>
</html>
