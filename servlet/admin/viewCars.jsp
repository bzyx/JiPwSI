<%@page import="pl.polsl.flota.model.Car"%>
<%@page import="java.io.IOException"%>
<%@page import="pl.polsl.flota.controller.CarController"%>
<%@page import="pl.polsl.flota.controller.UserController"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%-- 
    Document   : viewCars
    Created on : 2011-12-15, 15:58:17
    Author     : Marcin Jabrzyk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fleet manager - view Cars </title>
    </head>
    <body>
        <%
            CarController carController = new CarController((String)session.getAttribute("DB_URL"));
            List<Car> names;
            pageContext.setAttribute("names", carController.getRawList(), PageContext.PAGE_SCOPE);
        %>

        <h1>Przeglądanie aut!</h1>
        <table> 
            <tr>
                <td>Lp.</td>
                <td>Numer rejestracyjny </td>
                <td>Nazwa </td>
                <td>Średnie spalanie </td>
                <td>Przebieg</td>
                <td>Tankownaie</td>
                <td>Usuń</td>
            </tr>
            <c:forEach var="car" items="${pageScope.names}" varStatus="status">

                <tr>
                    <td>
                        <c:out value="${status.count}" />
                    </td>
                    <td>
                        <a href="/JiPwSI/admin/editCar.jsp?regNumber=${car.getRegNumber()}" alt="Edytuj ten samochód"> <c:out value="${car.getRegNumber()}" /> </a>
                    </td>
                    <td>
                        <c:out value="${car.getName()}" />
                    </td>

                    <td>
                        <c:out value="${car.getAvgConsumpion() }" />
                    </td>
                    <td>
                        <c:out value="${car.getDistance() }" />
                    </td>
                    <td>
                        <a href="/JiPwSI/admin/viewRefuelList.jsp?regNumber=${car.getRegNumber()}">Pokaż tankowania (<c:out value="${car.getHistoryOfRefuel().size()}" /> )</a>
                    </td>  
                    <td>
                        <a href="/JiPwSI/deleteCar?regNumber=${car.getRegNumber()}">Usuń</a>
                    </td>
                </tr>
            </c:forEach> 
        </table> 
        <br>
        <a href="../index.jsp">Powrót</a>
    </body>
</html>
