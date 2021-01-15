
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HELLO_2</title>
</head>
<body>

<h1>    HELLO_2   ${name}</h1>

<p/>

<form action="upload" method="post" enctype="multipart/form-data" >
<%--    <input type="text" name="description" />--%>
    <input type="file" name="file" />
    <input type="submit" />
</form>

</body>

</body>
</html>
