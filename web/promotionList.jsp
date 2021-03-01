<%-- 
    Document   : promotionList
    Created on : May 3, 2020, 1:09:55 PM
    Author     : TiTi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotion List Page</title>
        <style>
            th, td {
                padding-left: 5px;
                padding-right: 5px;
            }
            input {
                text-align: center;
                width: auto;
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
        <h1>Promotion List</h1>

        <c:set var="promotions" value="${PROMOTION_LISTS}"/>

        <c:if test="${empty promotions}">
            <h3><em>The promotion list is empty!</em></h3>
        </c:if>

        <c:if test="${not empty promotions}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Rank</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="promotion" items="${promotions}" varStatus="counter">
                    <form action="UpdatePromotionListServlet" method="POST">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${promotion.username.username}</td>
                            <td>
                                <input type="number" min="1" max="10" name="rank" value="${promotion.rank}" required/>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction" />
                                <input type="submit" value="Delete" name="btAction" style="margin-left: 2px; color: orangered"/>
                                <input type="hidden" name="id" value="${promotion.id}" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
            <form action="ProcessServlet" method="POST">
              <br/>  <input type="submit" value="Back"/>
        </form>
    </c:if>

</body>
</html>
