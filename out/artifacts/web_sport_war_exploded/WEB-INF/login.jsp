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
                    <input type="submit" value="Log in">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
