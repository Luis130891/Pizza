<%-- 
    Document   : index
    Created on : 20-jun-2020, 17:08:28
    Author     : Luis Venegas Ulloa
--%>

<%@page import="modeoo.dao.ServicioFactura"%>
<%@page import="modeoo.dao.ServicioIngredientes"%>
<%@page import="modeoo.dao.ServicioPizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css_principal.css" rel="stylesheet" type="text/css"/>
        <script src="scripts_principal.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body onload="init();">
        <header>
            <button  id="hCar"  onclick="verificarCarrito();"></button>
        </header>
        <div id="wrapper">

            
            
            <section id="categoria">
                <caption><strong>Categorias</strong></caption>
                <input type="button" name="pizzas" value="Pizzas">
                <input type="button" name="adicionales" value="adicionales">
                
            </section>
            <section id="menu">
                <table>
                    <thead>
                    <caption><strong id="hprincipal">Pizzas</strong></caption>
                    </thead>
                    <tbody id="principal">
                    </tbody>
                </table>
            </section>
        </div>
    </body>
</html>
