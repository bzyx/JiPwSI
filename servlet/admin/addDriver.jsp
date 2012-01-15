<%-- 
    Document   : addDriver
    Created on : 2011-12-15, 15:58:29
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
        <h1>Dodawanie kierowcy</h1>

        <form action="/JiPwSI/addDriver" method="POST">
            <div>
                <p>Wypełnił formularz i dodaj użytkownika.</p>
            </div>						
            <ul >		
                <li>
                    <label for="login">Login: </label>
                    <div>
                        <input id="login" name="login"  type="text" maxlength="255" value=""/> 
                    </div> 
                </li>		
                <li>
                    <label for="password">Hasło: </label>
                    <div>
                        <input id="password" name="password"  type="text" maxlength="255" value=""/> 
                    </div> 
                </li>
                <li>
                    <label  for="fullName">Imię i nazwisko: </label>
                    <div>
                        <input id="fullName" name="fullName" type="text" maxlength="255" value=""/> 
                    </div> 
                </li>
            </ul>
            <input id="saveForm" type="submit" name="submit" value="Dodaj" />
        </form>
        <br>
        <a href="../index.jsp">Powrót</a>
    </body>
</html>
