<%--
  Created by IntelliJ IDEA.
  User: KUMAR
  Date: 16-05-2025
  Time: 11:47 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
</head>
<body style="text-align: center">

<form action="${pageContext.request.contextPath}/api/web/login" method="post">
    <label>Username:</label>
    <label>
        <input type="text" name="username" required>
    </label>
    <br>

    <label>Password:</label>

    <label>
        <input type="password" name="password" required>
    </label>
    <br>
<%--    <input type="text" name="role" value="role">--%>
    <button type="submit">Login</button>
</form>

<h4>If you are not register</h4>

<input type="button" value="REGISTER" onclick="window.location.href='${pageContext.request.contextPath}/api/web/register'">
</body>
</html>
