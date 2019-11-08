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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/styles.css" type="text/css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <title>Расчет калорий</title>
    <script type="text/javascript">
        function calculate() {
            var age = document.getElementById("age").value;
            var sel = document.getElementById("gender-select");
            var gender = sel.options[sel.selectedIndex].value;
            var weight = document.getElementById("weight").value;
            var height = document.getElementById("height").value;
            var sel2 = document.getElementById("active-select");
            var activity = sel2.options[sel2.selectedIndex].value;
            var sel3 = document.getElementById("formula-select");
            var formula = sel3.options[sel3.selectedIndex].value;
            var xmlhttp = new XMLHttpRequest();
            alert(2);
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
<body class="body-with-img">
<%--    <jsp:include page="includes/nav.jsp"/>--%>
    <%@include file= "includes/nav.jsp"%>
    <div class="container type2">
        <div class="d-flex justify-content-center h-100">
            <div class="card form-card formula-card">
                <div class="card-header">
                    <h3><strong>Расчет калорий</strong></h3>
                </div>
                <div class="card-body">
                    <form method="post">
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-history" aria-hidden="true"></i></span>
                            </div>
                            <input type="number" class="form-control" id="age" placeholder="Возраст">
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-venus-mars" aria-hidden="true"></i></span>
                            </div>
                            <select class="custom-select" id="gender-select">
                                <option selected disabled>Пол</option>
                                <option value="male">Муж</option>
                                <option value="female">Жен</option>
                            </select>
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-ruler"></i></span>
                            </div>
                            <input type="number" class="form-control" id="height" placeholder="Рост">
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-weight" aria-hidden="true"></i></i></span>
                            </div>
                            <input type="number" class="form-control" id="weight" placeholder="Вес">
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-dumbbell"></i></span>
                            </div>
                            <select class="custom-select" id="active-select">
                                <option selected disabled>Физическая активность</option>
                                <option value="none">Отсутствие физ. активности</option>
                                <option value="small">1–2 раза в неделю</option>
                                <option value="medium">3–4 раза в неделю</option>
                                <option value="big">5-7 раз в неделю</option>
                                <option value="huge">ежедневные многократные тренировки</option>
                            </select>
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-calculator" aria-hidden="true"></i></span>
                            </div>
                            <select class="custom-select" id="formula-select">
                                <option selected disabled>Формула</option>
                                <option value="mifflin">Миффлина-Сан Жеора</option>
                                <option value="harris">Хариса-Бенедикта</option>
                            </select>
                        </div>
                        <hr>
                        <div>
                            <input type="button" class="btn float-right" id="show_res" value="Рассчитать" onclick="calculate()">
                        </div>
                        <div id="answer">
                            <h6>Результат</h6>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<%@include file= "includes/footer.jsp"%>
</body>
</html>
