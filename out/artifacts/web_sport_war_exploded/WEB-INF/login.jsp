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
                        document.getElementById("display").innerHTML = "неправильно введен логин или пароль";
                    }
                    else {
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
<body>
    <form method="post">
        <table>
            <tr>
                <td>
                    <label for="loginField">Логин</label>
                </td>
                <td>
                    <input id="loginField" type="text" name="login">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="passwordField">Пароль</label>
                </td>
                <td>
                    <input id="passwordField" type="password" name="password">
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <input type="button" value="Log in" onclick="check()">
                </td>
            </tr>
        </table>
    </form>
    <p id="display"></p>
</body>
</html>
