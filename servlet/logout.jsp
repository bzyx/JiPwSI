<%-- 
    Document   : logout
    Created on : 2012-01-15, 12:38:01
    Author     : All
--%>

<%@page import="pl.polsl.flota.model.User"%>
<%@page import="pl.polsl.flota.controller.UserController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fleet Manager</title>
    </head>
    <body>
        <h1>Wylogowano</h1>
        <% Integer userId = (Integer)session.getAttribute("userId");
           UserController userController = new UserController(session.getAttribute("usersFilePath").toString());
           
           User user = userController.getUserList().getUserById(userId);
           pageContext.setAttribute("userName", user.getFullName() , PageContext.PAGE_SCOPE);
           session.removeAttribute("userId");
%> 
<h4> Do widzenia, ${userName} </h4>
<p> Zostałeś wylogowany. Kliknij <a href="index.jsp"> tutaj</a> ,aby zalogować powownie. </p>
    </body>
</html>
