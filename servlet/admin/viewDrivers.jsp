<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : viewDrivers
    Created on : 2011-12-15, 15:58:44
    Author     : bzyx
--%>

        <%@page import="pl.polsl.flota.controller.UserController"%>
<%
            UserController userController = new UserController(session.getAttribute("usersFilePath").toString());
            pageContext.setAttribute("users", userController.getRawList(), PageContext.PAGE_SCOPE);
        %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Lp.</th>
                    <th>Id: </th>
                    <th>Login: </th>
                    <th>Imię i Nazwisko: </th>
                    <th>Administrator: </th>
                    <th>Zmień hasło: </th>
                    <th>Usuń: </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="userElement" items="${pageScope.users}" varStatus="status">
                <tr>
                    <td> <c:out value="${status.count}" /> </td>
                    <td> <c:out value="${userElement.getUserId()}"></c:out> </td>
                    <td> <c:out value="${userElement.getUserName() }"></c:out> </td>
                    <td> <c:out value="${userElement.getFullName() }"></c:out> </td>
                    <td> <c:out value="${userElement.getIsAdminString() }"></c:out> </td>
                    <td></td>
                    <td></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
