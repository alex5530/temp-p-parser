
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>RESULT</title>
</head>
<body>

<p/>

<table border="1">
    <thead>
    <td>
    <th></th>
    <th></th>
    </td>
    </thead>
    <tbody>
    <c:forEach items="${dickWords}" var="dickWord">
        <tr>
            <td>${dickWord.amount}</td>
            <td></td>
            <td>${dickWord.world}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>



</body>
</html>
