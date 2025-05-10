<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 40px;
        }

        h1, h2 {
            color: #333366;
        }

        table {
            width: 60%;
            border-collapse: collapse;
            margin-bottom: 30px;
        }

        th, td {
            border: 1px solid #999;
            padding: 12px 15px;
            text-align: center;
        }

        th {
            background-color: #004466;
            color: white;
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

        input[type="text"], input[type="number"] {
            padding: 8px;
            width: 90%;
            margin: 10px 0;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #0066cc;
            color: white;
            border: none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #004d99;
        }

        hr {
            margin-top: 40px;
        }
    </style>
</head>
<body>

<h1>Authors</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
    </tr>
    <c:forEach items="${authors}" var="author">
        <tr>
            <td>${author.id}</td>
            <td>${author.name}</td>
            <td>${author.age}</td>
        </tr>
    </c:forEach>
</table>

<h2>Create New Author</h2>
<form action="${pageContext.request.contextPath}/web/authors" method="post">
    Name: <input type="text" name="name" required/><br/>
    Age: <input type="number" name="age" required/><br/>
    <input type="submit" value="Add Author" />
</form>

<hr/>

</body>
</html>
