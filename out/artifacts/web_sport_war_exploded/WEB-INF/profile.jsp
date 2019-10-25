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
</head>
<body>
    <p>First name: ${user.getFirstName()}</p>
    <p>Last name: ${user.getLastName()}</p>
    <p>Login: ${user.getLogin()}</p><br>
    <input type="submit" name="rewrite">
</body>
</html>
