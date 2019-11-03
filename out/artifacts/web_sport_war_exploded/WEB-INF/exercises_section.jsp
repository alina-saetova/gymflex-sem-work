<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="iso-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
    <script type="text/javascript">
        function fun() {
            $.ajax({
                type: "POST",
                url: "/exercises_section",
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
        <select name="muscle" id="muscle">
            <option selected disabled>Choose group of muscles</option>
            <option value="legs">Legs</option>
            <option value="arms">Arms</option>
            <option value="shoulders">Shoulders</option>
            <option value="abdominals">Abdominals</option>
        </select>
        <p><input type="button" id="sendreq" value="Send" onclick="fun()"></p>
    </form>
    <div id="resptext">
        <c:if test="${allexercises != null}">
            <c:forEach var="ex" items="${allexercises}">
                <p>${ex.getName()}</p>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>


