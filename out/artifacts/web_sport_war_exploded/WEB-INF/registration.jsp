<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 17/10/2019
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Регистрация</title>
</head>
<body>
    <form method="post"  name="regform">
        <label>Логин  </label><input type="text" name="login"><br>
        <br>
        <label>Имя   </label><input type="text" name="first_name"><br>
        <br>
        <label>Фамилия  </label><input type="text" name="last_name"><br>
        <br>
        <label>Пароль  </label><input type="password" name="password"><br>
        <br>
        <input type="submit" value="Зарегистрироваться">
    </form>
</body>
</html>