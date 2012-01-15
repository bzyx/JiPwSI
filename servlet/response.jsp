<%-- 
    Document   : response
    Created on : 2012-01-14, 19:15:45
    Author     : All
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String title = (String)request.getAttribute("title");
String inner = (String)request.getAttribute("inner");

pageContext.setAttribute("titleText", title, PageContext.PAGE_SCOPE);
pageContext.setAttribute("innerHTML", inner, PageContext.PAGE_SCOPE);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fleet Manager</title>
    </head>
    <body>
        <h1>${titleText}</h1>
        ${innerHTML}
        <br>
        <a href="index.jsp">Powrót</a>
    </body>
</html>
