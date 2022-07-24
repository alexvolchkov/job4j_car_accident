<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
          integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
            integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>

    <title>Accident</title>
</head>
<body>
    <form action="<c:url value='/update'/>" method='post'>
        <table>
            <tr>
                <td><input type='hidden' name='id' value="${accident.id}"></td>
            </tr>
            <tr>
                <td>Название:</td>
                <td><input type='text' name='name' value="${accident.name}"></td>
            </tr>
            <tr>
                <td>Текст:</td>
                <td><input type='text' name='text' value="${accident.text}"></td>
            </tr>
            <tr>
                <td>Адрес:</td>
                <td><input type='text' name='address' value="${accident.address}"></td>
            </tr>

            <tr>
                <td>Тип:</td>
                <td>
                    <select name="type.id">
                        <c:forEach var="type" items="${types}">
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}">
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </td>
            </tr>

            <tr>
                <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
