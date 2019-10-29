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
    </script>
</head>
<body>
    <form method="post">
        <p>First name:</p>
        <input type="text" id="firstname" value="${user.getFirstName()}">
        <p>Last name:</p>
        <input type="text" id="lastname" value="${user.getLastName()}">
        <p>Login:</p>
        <input type="text" id="login" value="${user.getLogin()}">
        <input type="button" name="rewrite" onclick="save()" value="сохранить">
    </form>
    <form method="post">
        <p>Old password:</p>
        <input type="text" id="old_password">
        <p>New password:</p>
        <input type="text" id="new_password">
        <input type="button" name="rewrite" onclick="change_password()" value="изменить">
    </form>
    <p>Сохраненные тренировки</p>
    <c:forEach var="s_tr" items="${saved_trainings}">
        <p>${s_tr.getName()}</p>
    </c:forEach>
    <p>Сохраненные упражнения</p>
    <c:forEach var="s_ex" items="${saved_exercises}">
        <p>${s_ex.getName()}</p>
    </c:forEach>
</body>
</html>
