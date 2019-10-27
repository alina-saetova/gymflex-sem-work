<%--
  Created by IntelliJ IDEA.
  User: alina
  Date: 27/10/2019
  Time: 12:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Калькулятор</title>
    <script type="text/javascript">
        function calculate() {
            var age = document.getElementById("age").value;
            var sel = document.getElementById("gender");
            var gender = sel.options[sel.selectedIndex].value;
            var weight = document.getElementById("weight").value;
            var height = document.getElementById("height").value;
            var sel2 = document.getElementById("activity");
            var activity = sel2.options[sel2.selectedIndex].value;
            var sel3 = document.getElementById("formula");
            var formula = sel3.options[sel3.selectedIndex].value;
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    document.getElementById("answer").innerHTML = xmlhttp.responseText;
                }
            };
            xmlhttp.open("POST", "/calculator", true);
            xmlhttp.setRequestHeader("Content-Type",
                "application/x-www-form-urlencoded");
            xmlhttp.send("age=" + age + "&gender=" + gender + "&weight=" + weight +
                        "&height=" + height + "&activity=" + activity + "&formula=" + formula);
        }
    </script>
</head>
<body>
    <form method="post">
        <input type="text" id="age">
        <br>
        <select id="gender">
            <option selected disabled>Пол</option>
            <option value="female">женский</option>
            <option value="male">мужской</option>
        </select>
        <br>
        <input type="text" id="weight">
        <br>
        <input type="text" id="height">
        <br>
        <select id="activity">
            <option selected disabled>Физическая активность</option>
            <option value="none">Отсутствие физ. активности</option>
            <option value="small">1–2 раза в неделю</option>
            <option value="medium">3–4 раза в неделю</option>
            <option value="big">5-7 раз в неделю</option>
            <option value="huge">ежедневные многократные тренировки</option>

        </select>
        <br>
        <select id="formula">
            <option selected disabled>Формула</option>
            <option value="harris">Харриса-Бенедикта</option>
            <option value="mifflin">Миффлин Сан-Жеора</option>
        </select>
        <br>
        <input type="button" onclick="calculate()" value="рассчитать">
    </form>
    <p id="answer"></p>
</body>
</html>
