<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 40px;
        text-align: center;
    }
    h1{
        align-content: center;
        background-color: #a5e8a0;
    }

     h2 {

        color: #f2f2fa;
    }

    table {
        align-content: center;
        padding-left: 20%;
        width:100%;
        border-collapse: collapse;
        margin-bottom: 30px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }

    th, td {
        border: 1px solid #999;
        padding: 12px 15px;
        text-align: center;
    }

    th {
        background-color: rgba(111, 169, 239, 0.9);
        color: white;
    }

    a{
        color: #f6eded;

    }
    a:hover{
        color: #FFF000;
    }
    tr:nth-child(even) {
        background-color: #e6f2ff;
    }

    tr:hover {
        background-color: #cce0ff;
    }

    form {
        background-color: #ffffff;
        padding: 20px;
        border-radius: 8px;
        width: 40%;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    </style>
<body>

<H1>Welcome to the Book Store</H1>
<table>
    <th>
        <a href="${pageContext.request.contextPath}/web/books">BOOKS</a>
    </th>

    <th>
        <a href="${pageContext.request.contextPath}/web/authors">AUTHORS</a>
    </th>

</table>

<a href="/swagger-ui.html">
    <h1 style="width: 100%" align="center">Go to Swagger UI</h1>
</a>
 <h3> this is swagger to api doc easy to hit the CRUD Opertions still project is not prepared but next spring security </h3>
<br>
<p> i am learning </p>

</body>
</html>