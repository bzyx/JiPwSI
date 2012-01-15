<%-- 
    Document   : addRefuel
    Created on : 2011-12-15, 16:01:23
    Author     : Marcin Jabrzyk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fleet Manager</title>
    </head>
    <body>
        <h1>Tankowanie auta</h1>
        <form method="post" action="/JiPwSI/addRefuel">
            <div>
                <p>Zanotuj tankowanie i wciśnij zapisz.</p>
            </div>						
            <ul >		
                <li>
                    <label for="amountOfFuel">Ilość paliwa: </label>
                    <div>
                        <input id="amoutOfFuel" name="amoutOfFuel"  type="text" maxlength="255" title="Liczba z przecinkiem." value=""/> 
                    </div> 
                </li>
                <li>
                    <label  for="price">Kwota zakupu: </label>
                    <div>
                        <input id="price" name="price" type="text" maxlength="255" title="Liczba z przecinkiem." value=""/> 
                    </div> 
                </li>
                <li>
                    <label  for="distance">Stan licznika: </label>
                    <div>
                        <input id="distance" name="distance" type="text" maxlength="255" title ="Liczba bez przecinka" value=""/> 
                    </div> 
                </li>
            </ul>
            <input id="saveForm" type="submit" name="submit" value="Zapisz" />
        </form>	
        <br>
        <a href="../index.jsp">Powrót</a>
    </body>
</html>
