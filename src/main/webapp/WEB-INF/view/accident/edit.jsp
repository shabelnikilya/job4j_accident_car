<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1 align="center">Редактирование нарушения</h1>
<form id="form"  action="<c:url value='/update?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Название:</td>
            <td><input type='text' name='name' value="${accident.name}" required></td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td><input type='text' name='text' value="${accident.text}" required></td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td><input type='text' name='address' value="${accident.address}" required></td>
        </tr>
        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td>Статьи:</td>
            <td>
                <select name="rIds" multiple required>
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" onclick="return validate()" value="Изменить" /></td>
        </tr>
    </table>
</form>
</body>
</html>