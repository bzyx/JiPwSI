<%-- 
    Document   : editCar
    Created on : 2012-01-12, 21:53:20
    Author     : Marcin Jabrzyk
--%>
<%@page import="pl.polsl.flota.model.Car"%>
<%@page import="pl.polsl.flota.controller.CarController"%>
<% CarController carController = new CarController(session.getAttribute("DB_URL").toString());
    String carRegistartionNumer = request.getParameter("regNumber");

    String regNumber = carRegistartionNumer;
    String carName = "";
    String consumption = "";
    String distance = "";

    if (carRegistartionNumer != "") {
        Car foundCar = carController.getCarByRegistrationNumber(carRegistartionNumer);

        carName = foundCar.getName();
        consumption = foundCar.getAvgConsumpion().toString();
        distance = foundCar.getDistance().toString();
    }

    pageContext.setAttribute("registrationNumber", regNumber, PageContext.PAGE_SCOPE);
    pageContext.setAttribute("carName", carName, PageContext.PAGE_SCOPE);
    pageContext.setAttribute("consumption", consumption, PageContext.PAGE_SCOPE);
    pageContext.setAttribute("distance", distance, PageContext.PAGE_SCOPE);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fleet Manager</title>
    </head>
    <body>
        <h1>Edycja samochodu</h1>
        <form method="post" action="/JiPwSI/editCar">
            <div>
                <p>Formularz edycji samochodu</p>
            </div>						
            <ul >		
                <li>
                    <label for="reg_number">Numer rejestracyjny </label>
                    <div>
                        <input id="reg_number" name="reg_number"  type="text" maxlength="255" value="${registrationNumber}" readonly=""/> 
                    </div> 
                </li>		
                <li>
                    <label for="carName">Marka i model </label>
                    <div>
                        <input id="carName" name="carName"  type="text" maxlength="255" value="${carName}"/> 
                    </div> 
                </li>
                <li>
                    <label  for="consumption">Spalanie</label>
                    <div>
                        <input id="consumption" name="consumption" type="text" maxlength="255" title="Wartość integer" value="${consumption}"/> 
                    </div> 
                </li>
                <li>
                    <label  for="distance">Przebieg </label>
                    <div>
                        <input id="distance" name="distance" type="text" maxlength="255" title ="Wartośc float" value="${distance}"/> 
                    </div> 
                </li>
            </ul>
            <input id="saveForm" type="submit" name="submit" value="Zapisz" />
        </form>	
        <br>
        <a href="../index.jsp">Powrót</a>
    </body>
</html>
