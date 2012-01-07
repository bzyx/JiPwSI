<%@page import="pl.polsl.flota.model.Car"%>
<%@page import="java.io.IOException"%>
<%@page import="pl.polsl.flota.controller.CarController"%>
<%@page import="pl.polsl.flota.controller.UserController"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%-- 
    Document   : viewCars
    Created on : 2011-12-15, 15:58:17
    Author     : bzyx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fleet manager - view Cars </title>
    </head>
    <body>
        <% CarController carController = new CarController("/home/bzyx/dev/java/flota-servlet/JiPwSI/cars.json");  %>
        
        
        <%-- <%
       //     synchronized (pageContext) {
       //     String[] names;
       //     pageContext.setAttribute("names", carController.getCarStringList(), PageContext.PAGE_SCOPE);
       // %> 
        --%>
        
        <%
            synchronized (pageContext) {
            List<Car> names; 
                try {
                    pageContext.setAttribute("names", carController.getRawList(), PageContext.PAGE_SCOPE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        %>
        
        <h1>Przeglądanie aut!</h1>

        <p> <%= carController.getRawList().size() %> <p>
        
       <table> 
            <tr>
                <td>
                  Lp.  
                </td>
                <td>
                  Nazwa 
                </td>
                <td>
                Średnie spalanie 
                </td>
                 <td>
                  Przebieg
                </td>
                 <td>
                  Tankownaie
                </td>
            </tr>
            <c:forEach var="car" items="${pageScope.names}" varStatus="status">
                
            <tr>
                <td>
                    <c:out value="${status.count}" />
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
                       <a href="/JiPwSI/showRefuel?id=${car.getRegNumber()}">Pokaż tankowania (<c:out value="${car.getHistoryOfRefuel().size()}" /> )</a>
                  </td>               
            </tr>
                <%--//Family member # is
                <c:out value="${car.getName()}" /> <br />--%>
            </c:forEach> 
       </table> 
        <a href="../index.jsp">Powrót</a>
    </body>
</html>
