<%--
  Created by IntelliJ IDEA.
  User: KUMAR
  Date: 20-05-2025
  Time: 09:10 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body style="text-align: center">
<form action="${pageContext.request.contextPath}/api/web/register" method="post"
 style="text-align: center">
    <Label>User Name :</Label>
    <label>
        <input type="text" name="username">
    </label>

    <br>
<%--    <input type="text" name="email">--%>
    <Label>Password :</Label>
    <label>
        <input type="text" name="password">
    </label>
    <br>
    <div>
        <label>
            <input type="checkbox" name="role" value="ADMIN" >
        </label>
        <label>ADMIN</label>
    <br>
        <label>
            <input type="checkbox" name="role" value="USER" >
        </label>
        <label> USER </label>
    </div>
    <br>
<%--    <input type="checkbox" name="role" value="ADMIN" >--%>

    <input type="submit" value="Register" required>

</form>
<br>

<button><a href="${pageContext.request.contextPath}/api/web/login">Login</a></button>

</body>
</html>
