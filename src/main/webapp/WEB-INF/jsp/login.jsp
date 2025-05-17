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
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/api/web/login" method="post">
    <label>Username:</label>
    <input type="text" name="username" required>
    <label>Password:</label>
    <input type="password" name="password" required>
    <input type="text" name="role" value="role">
    <button type="submit">Login</button>
</form>
</body>
</html>
