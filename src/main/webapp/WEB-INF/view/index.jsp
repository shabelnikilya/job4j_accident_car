<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    <title>Accident</title>
</head>
<body>
<h1 align="center">Заявления нарушений</h1>
<table class="table">
    <thead class="table-dark">
    <tr>
        <th>Название</th>
        <th>Описание</th>
        <th>Адрес</th>
    </tr>
    </thead>
    <tbody>
                <c:forEach items="${accidents}" var="accident">
                    <tr>
                        <td><c:out value="${accident.name}"/></td>
                        <td><c:out value="${accident.text}"/></td>
                        <td><c:out value="${accident.address}"/></td>
                    </tr>
                </c:forEach>
    </tbody>
</table>
</body>
</html>