
<!DOCTYPE html>
<html>
    <head>
        <title>Pizza CR</title>
        <meta charset="UTF-8">
        <link rel="icon" type="image/png" href="imagenes/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="css/indexcss.css">
        <script src="js/index.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="fondo" style="background-image: url('imagenes/pizza.jpg')">
            <h1>
                Bienvenido a Pizza CR
            </h1>
            <div class="contenedor">
                <div class="wrap">
                    <span class="tituloFormulario">
                        Ingrese
                    </span>
                    <section class="formulario">
                        <table id="tablaIngresar">
                            <tbody>
                                <tr>
                                    <td class="wrapInput">
                                        <input class="input" type="text" name="usuario" id="usuario" placeholder="Usuario" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="wrapInput">
                                        <input class="input" type="password" name="contrasena" id="contrasena" placeholder="Contraseña" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="contenedorBoton">
                                        <button onclick="validarUsuario();" class="formularioBoton"  id="botonIngresar">
                                            Ingresar
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </section>
                </div>
                <div class="wrap">
                    <span class="tituloFormulario">
                        Registrese
                    </span>
                    <div class="formulario">
                        <table>
                            <tbody>
                                <tr>
                                    <td class="wrapInput">
                                        <input class="input" type="text" name="usuarioNuevo" id="usuarioNuevo" placeholder="Usuario" required size="15">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="wrapInput">
                                        <input class="input" type="password" name="contrasenaNueva" id="contrasenaNueva" placeholder="Contrasena"
                                               size="15" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <select class="select" name="tipoNuevo" id="tipoNuevo" size="1">
                                            <option value="1">Cliente</option>
                                            <option value="2">Administrador</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="wrapInput">
                                        <input class="input" type="text" name="cedulaNueva" id="cedulaNueva" placeholder="Cedula" 
                                               pattern="[0-9]{9}" title="Número de cédula: 123456789" autofocus="autofocus" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="wrapInput">
                                        <input class="input" type="text" name="nuevoNombre" id="nuevoNombre" placeholder="Nombre" size="15" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="wrapInput">
                                        <input class="input" type="text" name="apellidosNuevos" id="apellidosNuevos" placeholder="Apellidos" size="25"
                                               required>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="wrapInput">
                                        <input class="input" type="text" name="direccionNueva" id="direccionNueva" placeholder="Direccion" size="35"
                                               required>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="wrapInput">
                                        <input class="input" type="text" name="telefonoNuevo" id="telefonoNuevo" placeholder="Telefono" 
                                               pattern="[1-9]{8}" title="Número de telefono: 12345678" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="contenedorBoton">
                                        <button onclick="crearUsuario();" class="formularioBoton" name="botonRegistrarse" id="botonRegistrarse">
                                            Registrarse
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>