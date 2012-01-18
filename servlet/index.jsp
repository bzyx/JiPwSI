
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="sun.security.x509.OIDMap"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 2011-12-15, 15:04:15
    Author     : Marcin Jabrzyk
--%>

<%@page import="pl.polsl.flota.model.User"%>
<%@page import="pl.polsl.flota.controller.UserController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Class.forName("org.sqlite.JDBC");
    //Connection connection = null;

    //connection = DriverManager.getConnection("jdbc:sqlite:H:/JiPwSI/fmFull.db");
    session.setAttribute("DB_URL", "jdbc:sqlite:H:/JiPwSI/fmFull.db");
    session.setAttribute("carsFilePath", session.getServletContext().getRealPath("/") + "cars.json");
    //session.setAttribute("usersFilePath", session.getServletContext().getRealPath("/") + "users.json");
    UserController userController = new UserController((String)session.getAttribute("DB_URL"));
    

    Integer userId = -1;
    Boolean isAdmin = false;
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String userFullName = null;

    //If user was logged get his id.
    Integer loginUserId = (Integer) session.getAttribute("userId");
    User user = null;

    //User was loged the id is as it was.
    if (loginUserId != null) {
        userId = loginUserId;
    }

    //If we have a new login/password pair lets try to login the user
    if ((username != null) && (password != null)) {
        if ((!username.isEmpty()) && (!password.isEmpty())) {
            userId = userController.checkUser(username, password);
            //User found and logged in
            session.setAttribute("userId", userId);
        }
    }

    //If we have a valid user lets get his user profile
    if (userId != -1) {
        user = userController.getUserList().getUserById(userId);
        userFullName = user.getFullName();
    }

    //If we have a valid user lets chceck if the user is an admin
    if (user != null) {
        isAdmin = userController.isAdmin(user.getUserId());
    }


    pageContext.setAttribute("isAdmin", isAdmin, PageContext.PAGE_SCOPE);
    pageContext.setAttribute("userId", userId, PageContext.PAGE_SCOPE);
    pageContext.setAttribute("userName", userFullName, PageContext.PAGE_SCOPE);
    
    for (User us : userController.getRawList()){
       String s = us.getUserId() +" "+ us.getFullName() +" "+ us.getIsAdminString() +" "+ us.getUserName() +" "+ us.getPassword() +" "+ us.getLastUserId();
       System.out.println(s);
      }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fleet manager</title>
    </head>
    <body>
        <h1>Menadżer zarządzania flotą.</h1>
        <c:if test="${ pageScope.userId != -1}"> 
            <h2> Witaj, ${userName} </h2>
            <h4> Wybierz akcję </h4>

            <c:if test="${ pageScope.isAdmin == false}">
                <ul>
                    <li> <a href="driver/addRefuel.jsp">Zanotuj tankowanie</a> </li>
                    <li><a href="driver/changeCar.jsp">Zmień pojazd</a></li>
                </ul>
            </c:if>

            <c:if test="${ pageScope.isAdmin == true}">
                <ul>
                    <li> <a href="admin/addCar.jsp">Dodaj pojazd</a> </li>
                    <li> <a href="admin/viewCars.jsp">Przeglądaj pojazdy</a> </li>
                    <li> <a href="admin/addDriver.jsp">Dodaj kierowcę</a> </li>
                    <li> <a href="admin/viewDrivers.jsp">Przeglądaj kierowców</a> </li>
                </ul>
            </c:if>
        </c:if>

        <c:if test="${ pageScope.userId == -1 }"> 
            <h4>Podaj login i hasło, a następnie naciśnij zaloguj.</h4>
            <form action="index.jsp" method="POST">
                <table>
                    <tr>
                        <td> Login  : </td><td> <input name="username" size=15 type="text" /> </td> 
                    </tr>
                    <tr>
                        <td> Hasło  : </td><td> <input name="password" size=15 type="text" /> </td> 
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Zaloguj" />
                        </td>
                    </tr>
                </table>
            </form>
        </c:if>

        <c:if test="${ pageScope.userId != -1}"> 
            <br>
            <a href="logout.jsp"> Wyloguj </a>
        </c:if>

    </body>
</html>
