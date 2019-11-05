<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 17/10/2019
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Регистрация</title>
    <script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
<%--    <script type="text/javascript">--%>
<%--        function check_login() {--%>
<%--            $.ajax({--%>
<%--                type: "POST",--%>
<%--                url: "/registration",--%>
<%--                data: $('#reg').serialize(),--%>
<%--                success: function (msg) {--%>
<%--                    if (msg == 'true') {--%>
<%--                        $('#ans').html("");--%>
<%--                        $('#ans').append("<p>этот логин занят</p>");--%>
<%--                    }--%>
<%--                }--%>
<%--            })--%>
<%--        }--%>
<%--    </script>--%>
</head>
<body>
    <form method="post"  name="regform" id="reg" enctype="multipart/form-data">
        <label>Логин  </label><input type="text" name="login" id="login"><br>
        <br>
        <label>Имя   </label><input type="text" name="first_name"><br>
        <br>
        <label>Фамилия  </label><input type="text" name="last_name"><br>
        <br>
        <label>Пароль  </label><input type="password" name="password"><br>
        <br>
        <label>Фото  </label><input type="file" name="photo"><br>
        <br>
        <input type="submit" value="Зарегистрироваться">
    </form>
<%--    <div id="ans">--%>

<%--    </div>--%>
</body>
</html>
