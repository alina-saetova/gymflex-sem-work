<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 25/10/2019
  Time: 10:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<html>
<head lang="ru">
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
    <script type="text/javascript">
        function get() {
            $.ajax({
                type: "POST",
                url: "/trainings_section",
                data: $('#muscle_select').serialize(),
                dataType: "json",
                success: function (msg) {
                    if (msg.objects.length > 0) {
                        $("#resptext").html("");
                        for (var i = 0; i < msg.objects.length; i++) {
                            $("#resptext").append("<p>" + msg.objects[i].name + "</p>");
                        }
                    } else {
                        $("#resptext").html("No results..");
                    }
                }
            })
        }
    </script>
</head>
<body>
    <form method="post" id="muscle_select">
        <select name="gender" id="gender">
            <option selected disabled>Выберите пол</option>
            <option value="female">Женский</option>
            <option value="male">Мужской</option>
        </select>
        <select name="purpose" id="purpose">
            <option selected disabled>Выберите цель</option>
            <option value="pohudenie">Похудение</option>
            <option value="massa">Масса</option>
            <option value="podderjanie">Поддержание</option>
        </select>
        <select name="location" id="location">
            <option selected disabled>Выберите место тренировки</option>
            <option value="home">Дома</option>
            <option value="gym">В зале</option>
        </select>
        <p><input type="button" id="sendreq" value="Send" onclick="get()"></p>
    </form>
    <p>trenirov 04ki</p>
    <div id="resptext">
        <c:if test="${alltrainings != null}">
            <c:forEach var="tr" items="${alltrainings}">
                <p>${tr.getName()}</p>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>
