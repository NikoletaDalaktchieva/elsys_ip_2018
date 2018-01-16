<%--
  Created by IntelliJ IDEA.
  User: Zori
  Date: 11/6/2017
  Time: 10:56 AM
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
        <tr><form action="/user" method="post">
            <td><input type="text" name="id" value=${user.id}></td>
            <td>${user.name}
            </td>
            <td><input type="text" name="email" value=${user.email}></td>
            <td><input type="password" name="password" value=${user.password}></td>
            <td><input type="submit" value="save"/></td>
        </form></tr>

    </table>
</body>
</html>
