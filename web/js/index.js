/* global fetch */

/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 *          Autores: 
 *          116720428Kenneth Ariel Chaves Herrera
 *          702000163 Luis Venegas Ulloa
 */

function validarUsuario() {
    var datos = new FormData();
    var usuario = document.getElementById("usuario");
    var contrasena = document.getElementById("contrasena");
    datos.append("usuario", usuario.value);
    datos.append("contrasena", contrasena.value);
    getJSON("servletCliente", datos, procesarRespuesta);
}

function procesarRespuesta(aux) {
  
    if (!(aux.cliente == "null")) {
        window.localStorage.setItem("usuario", aux.cliente);
        document.location.href = "principal.jsp";
    }
}

function getJSON(source, data, callback) {
    fetch(source, {
        method: 'POST',
        body: data
    }).then((result) => {
        return result.json();
    }).then(callback);
}

function crearUsuario() {
    var datos = new FormData();
    var usuario = document.getElementById("usuarioNuevo");
    var contrasena = document.getElementById("contrasenaNueva");
    var tipo = document.getElementById("tipoNuevo");
    var cedula = document.getElementById("cedulaNueva");
    var nombre = document.getElementById("nuevoNombre");
    var apellidos = document.getElementById("apellidosNuevos");
    var direccion = document.getElementById("direccionNueva");
    var telefono = document.getElementById("telefonoNuevo");
    alert(tipo.value);
    datos.append("usuarioNuevo", usuario.value);
    datos.append("contrasenaNueva", contrasena.value);
    datos.append("tipoNuevo", tipo.value);
    datos.append("cedulaNueva", cedula.value);
    datos.append("nuevoNombre", nombre.value);
    datos.append("apellidosNuevos", apellidos.value);
    datos.append("direccionNueva", direccion.value);
    datos.append("telefonoNuevo", telefono.value);
    getJSON("servletClienteNuevo", datos,proceso);
}

function proceso(datos){
    console.log(datos);
}