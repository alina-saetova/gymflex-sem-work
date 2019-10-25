<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 25/10/2019
  Time: 10:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" name="muscle_select">
        <select name="gender">
            <option selected disabled>Выберите пол</option>
            <option value="female">Женский</option>
            <option value="male">Мужской</option>
        </select>
        <select name="purpose">
            <option selected disabled>Выберите цель</option>
            <option value="pohudenie">Похудение</option>
            <option value="massa">Масса</option>
            <option value="podderjanie">Поддержание</option>
        </select>
        <select name="location">
            <option selected disabled>Выберите место тренировки</option>
            <option value="home">Дома</option>
            <option value="gym">В зале</option>
        </select>
        <p><input type="submit" id="sendreq" value="Send"></p>
    </form>
    <div id="resptext">
        <c:if test="${alltrainings != null}">
            <c:forEach var="tr" items="${alltrainings}">
                <p>${tr.getName()}</p>
            </c:forEach>
        </c:if>
        <c:if test="${selected_trs != null}">
            <c:forEach var="tr" items="${selected_trs}">
                <p>${tr.getName()}</p>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>
