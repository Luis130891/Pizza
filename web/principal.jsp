
<%@page import="modeoo.dao.ServicioFactura"%>
<%@page import="modeoo.dao.ServicioIngredientes"%>
<%@page import="modeoo.dao.ServicioPizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/css_principal.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" href="imagenes/favicon.ico"/>
        <script src="js/principal.js" type="text/javascript"></script>
        <title>Pizza CR</title>
    </head>
    <body onload = "init()">
        <header id="header">
            <button id="hCar"  onclick="verificarCarrito();"></button>
            <button id="h1" class="h" onclick="actualizarUsuario();">Actulizar Usuario</button>
            <button id="h2" class="h" onclick="consultarPedidio();">Consultar Pedido</button>
            <button id="h3" class="h" onclick="crearComentario();">Comentario</button>
            <button id="h4" class="h" onclick="listarComentario();">Consultar Comentarios</button>
            <button id="h5" class="h" onclick="listarPizzas();">Adm Pizzas</button>
            <button id="h6" class="h" onclick="actualizarEstado();">Pedidos en proceso</button>
            <button id="h7" class="h" onclick="busFacFecha();">Listar Facturas Fecha</button>
            <button id="h8" class="h" onclick="salir();">Salir</button>
            <label id="cliente" style="padding-left: 20px"></label>
        </header>
        <div id="wrapper">
            <section id="categoria">
                <caption><strong>Categorias</strong></caption>
                <button type="button"  id="p"  onclick="init();" name="Pizzas" value="Pizzas">Pizzas</button>
                <button type="button" id="a" onclick="categorias();"  name="Adicionales" value="adicionales">Complementos</button>

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
