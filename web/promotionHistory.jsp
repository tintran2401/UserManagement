<%-- 
    Document   : promotionHistory
    Created on : May 3, 2020, 2:18:10 PM
    Author     : TiTi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotion History Page</title>
        <style>
            th, td {
                text-align: center;
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
        <h1>Promotion History</h1>

        <c:set var="promotions" value="${PROMOTION_HISTORY}"/>

        <c:if test="${empty promotions}">
            <h3>Your promotion history is empty!</h3>
        </c:if>
        <c:if test="${not empty promotions}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Rank</th>
                        <th>Modified date</th>
                        <th>Action type</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="promotion" items="${promotions}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${promotion.username.username}</td>
                            <td>${promotion.rank}</td>
                            <td>${promotion.createdDate}</td>
                            <td>${promotion.action}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
            <form action="ProcessServlet" method="POST">
             <br/>   <input type="submit" value="Back"/>
            </form>
    </body>
</html>
