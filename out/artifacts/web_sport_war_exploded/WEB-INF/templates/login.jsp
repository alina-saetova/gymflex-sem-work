<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 25/10/2019
  Time: 8:39 AM
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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <title>Вход</title>
    <script type="text/javascript">
        function check() {
            var login = document.getElementById("loginField").value;
            var password = document.getElementById("passwordField").value;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    if (xmlhttp.responseText == 0) {
                        //допилить алерт
                        document.getElementById("err").innerHTML = "<p class=\"error\">Введите корректное имя</p>";
                    } else {
                        window.location.href = "/profile"
                    }
                }
            };
            xmlhttp.open("POST", "/login", true);
            xmlhttp.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded");
            xmlhttp.send("login=" + login + "&password=" + password);
        }
    </script>
</head>
<body class="body-with-img">
<%--    <form method="post">--%>
<%--        <table>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <label for="loginField">Логин</label>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <input id="loginField" type="text" name="login">--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <label for="passwordField">Пароль</label>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <input id="passwordField" type="password" name="password">--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td colspan="2" style="text-align: center">--%>
<%--                    <input type="button" value="Log in" onclick="check()">--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </form>--%>
<%--    <p id="display"></p>--%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <a class="navbar-brand" href="start_page.html">GymFlex</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="/exercises_section">Упражнения</a></li>
            <li class="nav-item"><a class="nav-link" href="/trainings_section">Тренировки</a></li>
            <li class="nav-item"><a class="nav-link" href="/calculator">Рассчет калорий</a></li>
            <li class="nav-item"><a class="nav-link" href="/create_training_one">Создать тренировку</a></li>
            <li class="nav-item"><a class="nav-link" href="/profile">Профиль</a></li>
        </ul>
    </div>
</nav>
<div class="container type1">
    <div class="d-flex justify-content-center h-100">
        <div class="card form-card log-in-card">
            <div class="card-header">
                <h3><strong>Вход</strong></h3>
            </div>
            <div class="card-body">
                <form action="" method="post">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" class="form-control" placeholder="Логин" id="loginField" name="login">
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input type="password" class="form-control" placeholder="Пароль" id="passwordField"
                               name="password">
                    </div>
                    <div class="row align-items-center remember">
                        <input type="checkbox">Запомнить меня
                    </div>
                    <hr>
                    <div>
                        <input type="button" value="Войти" class="btn float-right" onclick="check()">
                    </div>
                </form>
                <div id="err"></div>
            </div>
        </div>
    </div>
</div>
<footer>
    <div>
        <small>Спорт - это жизнь ©</small>
    </div>
</footer>
</body>
</html>
