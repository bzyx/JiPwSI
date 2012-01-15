<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : changeCar
    Created on : 2011-12-15, 16:01:45
    Author     : bzyx
--%>

<%@page import="pl.polsl.flota.controller.CarController"%>
<%@page import="pl.polsl.flota.model.Car"%>
<%  CarController carController = new CarController(session.getAttribute("carsFilePath").toString());
    pageContext.setAttribute("carsToUse", carController.getCarWithNoDiverCarList(), PageContext.PAGE_SCOPE);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Zmiana pojazdu.</h1>
        <h4>Należy wybrać pojazd z listy </h4>
        <form method="POST" action="/JiPwSI/changeCar">
            <select name="changeCar">
                <c:forEach  var="car" items="${ pageScope.carsToUse }" varStatus="status">
                    <option value="${ car.getRegNumber()}">  <c:out value="${ car.getName() }" /> ( <c:out value="${ car.getRegNumber() }" /> ) </option>
                </c:forEach>
            </select>
            <input type="submit" value="Zapisz" /> 
        </form> 

    </body>
</html>
