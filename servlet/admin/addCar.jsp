<%-- 
    Document   : addCar
    Created on : 2011-12-15, 15:57:56
    Author     : bzyx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fleet manager - new car</title>
    </head>
    <body>
        <h1>Dodawanie auta!</h1>
        <form method="post" action="/JiPwSI/addCar">
            <div>
                <p>Wypełnił formularz i dodaj auto.</p>
            </div>						
            <ul >		
                <li>
                    <label for="reg_number">Numer rejestracyjny </label>
                    <div>
                        <input id="reg_number" name="reg_number"  type="text" maxlength="255" value=""/> 
                    </div> 
                </li>		
                <li>
                    <label for="carName">Marka i model </label>
                    <div>
                        <input id="carName" name="carName"  type="text" maxlength="255" value=""/> 
                    </div> 
                </li>
                <li>
                    <label  for="consumption">Spalanie</label>
                    <div>
                        <input id="consumption" name="consumption" type="text" maxlength="255" title="Wartość integer" value=""/> 
                    </div> 
                </li>
                <li>
                    <label  for="distance">Przebieg </label>
                    <div>
                        <input id="distance" name="distance" type="text" maxlength="255" title ="Wartośc float" value=""/> 
                    </div> 
                </li>
            </ul>
            <input id="saveForm" type="submit" name="submit" value="Dodaj" />
        </form>	
        <a href="../index.jsp">Powrót</a>
    </body>
</html>
