<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : viewRefuelList
    Created on : 2012-01-12, 23:12:49
    Author     : Marcin Jabrzyk
--%>

<%@page import="pl.polsl.flota.model.Refuel"%>
<%@page import="java.util.List"%>
<%@page import="pl.polsl.flota.model.Car"%>
<%@page import="pl.polsl.flota.controller.CarController"%>
<%  CarController carController = new CarController(session.getAttribute("DB_URL").toString());
    String carRegistartionNumer = request.getParameter("regNumber");

    Car car = carController.getCarByRegistrationNumber(carRegistartionNumer);
    if (car != null) {
        pageContext.setAttribute("history", car.getHistoryOfRefuel(), PageContext.PAGE_SCOPE);
        pageContext.setAttribute("carRegistationNumer", car.getRegNumber(), PageContext.PAGE_SCOPE);
        pageContext.setAttribute("carName", car.getName(), PageContext.PAGE_SCOPE);
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fleet Manager</title>
    </head>
    <body>

        <h1>Tankowania dla pojazdu: ${carName} (${carRegistationNumer}) </h1>
        <c:if test="${ pageScope.history.size() == 0 }" >
            <p><em> Brak danych do wyświetlenia. </em></p>
        </c:if>
        <c:if test="${ pageScope.history.size() > 0 }" >
            <table border="1">
                <thead>
                    <tr>
                        <th>Lp. </th>
                        <th>Ilość paliwa: [l]</th>
                        <th>Data: </th>
                        <th>Przebieg: [km]</th>
                        <th>Wartość: [zł] </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach  var="car" items="${ pageScope.history }" varStatus="status">
                        <tr>
                            <td><c:out value="${status.count}" /> </td>
                            <td><c:out value="${ car.getAmount() }" /></td>
                            <td><c:out value="${ car.getDate() }" /></td>
                            <td><c:out value="${ car.getDistance() }" /> </td>
                            <td><c:out value="${ car.getValue() }" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <br>
        <a href="../index.jsp">Powrót</a>
    </body>
</html>
