<%-- 
    Document   : newUser
    Created on : May 3, 2020, 9:51:44 AM
    Author     : TiTi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
        <style>
            td {
                padding-right: 20px;
            }
        </style>
    </head>
    <body>
        <c:if test="${not empty USER}">
            <h3 style="color: orangered">Welcome, ${USER.username}!
                <span style="color: turquoise"><em>Group: ${USER.groupId.name}</em></span>
            </h3>
            <a href="LogoutServlet">Logout</a>
        </c:if>
        <h1>Create New User</h1>

        <form action="CreateUserServlet" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td> <input type="text" name="username" value="" required/> </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td> <input type="password" name="password" value="" required/> </td>
                    </tr>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value="" /> </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value="" /> </td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><input type="text" name="phone" value="" /> </td>
                    </tr>
                    <tr>
                        <td>Photo Url</td>
                        <td><input type="text" name="photo" value="" /> </td>
                    </tr>
                    <tr>
                        <td>Group</td>
                        <td>
                            <select name="groupId">
                                <c:forEach var="singleGroup" items="${ALL_GROUPS}">
                                    <option value="${singleGroup.id}">
                                        ${singleGroup.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="padding-top: 20px; text-align: right">
                            <button type="reset">Reset</button>
                            <button type="submit">Create</button>
                        </td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
