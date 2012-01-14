<%-- 
    Document   : changeUserPassword
    Created on : 2012-01-14, 23:58:18
    Author     : All
--%>
<%@page import="pl.polsl.flota.model.User"%>
<%@page import="pl.polsl.flota.controller.UserController"%>
<%  UserController userController = new UserController(session.getAttribute("usersFilePath").toString());
    Integer userId = Integer.parseInt(request.getParameter("id"));

    User user = userController.getUserList().getUserById(userId);
    if (user != null) {
        pageContext.setAttribute("fullName", user.getFullName(), PageContext.PAGE_SCOPE);
        pageContext.setAttribute("userId", userId, PageContext.PAGE_SCOPE);
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Zmiana hasła</h1>
        <p> Zmiana hasła dla użytkownika : ${fullName} </p>
        <form action="/JiPwSI/changeUserPassword" method="POST">
            <input type="hidden" name="userId" value="${userId}" />
            <label for="password">Nowe hasło: </label>
            <input type="text" name="password" value="" size="20" /><br> 
            <input type="submit" value="Zapisz" name="save" /> 
        </form>
        <br>
        <a href="../index.jsp">Powrót</a>
    </body>
</html>
