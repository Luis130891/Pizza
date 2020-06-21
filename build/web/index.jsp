<%-- 
    Document   : index
    Created on : 20-jun-2020, 17:08:28
    Author     : Luis Venegas Ulloa
--%>

<%@page import="modeoo.dao.ServicioFactura"%>
<%@page import="modeoo.dao.ServicioIngredientes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%= ServicioFactura.obtenerInstancia().buscarFactura(1).toString()%>
        <h1>Hello World!</h1>
    </body>
</html>
