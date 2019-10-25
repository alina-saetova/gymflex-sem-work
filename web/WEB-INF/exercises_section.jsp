<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="iso-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Title</title>
</head>
<body>
    <form method="post" name="muscle_select">
        <select name="muscle">
            <option selected disabled>Choose group of muscles</option>
            <option value="legs">Legs</option>
            <option value="arms">Arms</option>
            <option value="shoulders">Shoulders</option>
            <option value="abdominals">Abdominals</option>
        </select>
        <p><input type="submit" id="sendreq" value="Send"></p>
    </form>
    <div id="resptext">
        <c:if test="${allexercises != null}">
            <c:forEach var="ex" items="${allexercises}">
                <p>${ex.getName()}</p>
            </c:forEach>
        </c:if>
        <c:if test="${selected_exs != null}">
                <c:forEach var="ex" items="${selected_exs}">
                    <p>${ex.getName()}</p>
                </c:forEach>
        </c:if>
    </div>
</body>
</html>


