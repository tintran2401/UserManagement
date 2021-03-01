<%-- 
    Document   : login
    Created on : May 2, 2020, 9:23:24 PM
    Author     : TiTi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="LoginServlet" method="POST">
            <label>Username</label>
            <input type="text" name="username" required=""/>
            <br/>
            <label>Password</label>
            <input type="password" name="password" value="" required=""/>
            <br/>
            <input type="submit" value="Login" />
        </form>
        <c:if test="${INVALID}">
            <h3 style="color: orangered">Error invalid username or password!</h3>
        </c:if>
    </body>
</html>
