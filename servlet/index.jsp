<%-- 
    Document   : index
    Created on : 2011-12-15, 15:04:15
    Author     : Marcin Jabrzyk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setAttribute("carsFilePath", session.getServletContext().getRealPath("/") + "cars.json");
    session.setAttribute("usersFilePath", session.getServletContext().getRealPath("/") + "users.json");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fleet manager</title>
    </head>
    <body>
        <h1>Menadżer zarządzania flotą.</h1>
        <p> Wybierz akcję </p>

        <h2> Kierowca </h2>
        <ul>
            <li> <a href="driver/addRefuel.jsp">Zanotuj tankowanie</a> </li>
            <li><a href="driver/changeCar.jsp">Zmień pojazd</a></li>
        </ul>

        <h2> Administrator </h2>
        <ul>
            <li> <a href="admin/addCar.jsp">Dodaj pojazd</a> </li>
            <li> <a href="admin/viewCars.jsp">Przeglądaj pojazdy</a> </li>
            <li> <a href="admin/addDriver.jsp">Dodaj kierowcę</a> </li>
            <li> <a href="admin/viewDrivers.jsp">Przeglądaj kierowców</a> </li>
        </ul>
    </body>
</html>
