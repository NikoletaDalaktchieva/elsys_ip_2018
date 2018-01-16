<%--
  Created by IntelliJ IDEA.
  User: Zori
  Date: 11/7/2017
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Password</th>
        </tr>
        <tr><form action="/addUser" method="post">
            <td><input type="number" name="id"></td>
            <td><input type="text" name="n"></td>
            <td><input type="text" name="email"></td>
            <td><input type="text" name="password"></td>
            <td><input type="submit" value="save"/></td>
        </form></tr>

    </table>

</body>
</html>
