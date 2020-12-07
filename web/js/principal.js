/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global fetch, int, total */
/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 *          Autores: 
 *          116720428Kenneth Ariel Chaves Herrera
 *          702000163 Luis Venegas Ulloa
 */


var facturas = [];
var usuario = [];
var pizzas = [];
var complementos;
var pizzasAgregadas = [];
var complementosAgregados = [];


function init() {
    usuario = JSON.parse(window.localStorage.getItem("usuario"));
    solicitarDatos("ServletPrincipal", "principal", fetchJSON, crearTabla);
    document.getElementById("hCar").innerHTML = pizzasAgregadas.length + complementosAgregados.length;
    var car = document.getElementById("hCar");
    car.onmouseover = btnColor;
    car.onmouseout = btnColorTrans;


    var car = document.getElementById("h1");
    car.onmouseover = btnColor;
    car.onmouseout = btnColorTrans;
    var car = document.getElementById("h2");
    car.onmouseover = btnColor;
    car.onmouseout = btnColorTrans;
    var car = document.getElementById("h3");
    car.onmouseover = btnColor;
    car.onmouseout = btnColorTrans;
    var car = document.getElementById("h4");
    if (usuario.usuario.tipo === true)
        car.style = "display:none";
    car.onmouseover = btnColor;
    car.onmouseout = btnColorTrans;
    var car = document.getElementById("h5");
    if (usuario.usuario.tipo === true)
        car.style = "display:none";
    car.onmouseover = btnColor;
    car.onmouseout = btnColorTrans;
    var car = document.getElementById("h6");
    if (usuario.usuario.tipo === true)
        car.style = "display:none";
    car.onmouseover = btnColor;
    car.onmouseout = btnColorTrans;
    var car = document.getElementById("h7");
    if (usuario.usuario.tipo === true)
        car.style = "display:none";
    car.onmouseover = btnColor;
    car.onmouseout = btnColorTrans;
    var car = document.getElementById("h8");
    car.onmouseover = btnColor;
    car.onmouseout = btnColorTrans;


    var p = document.getElementById("p");
    p.onmouseover = btnColor;
    p.onmouseout = btnColorTrans;
    var a = document.getElementById("a");
    a.onmouseover = btnColor;
    a.onmouseout = btnColorTrans;
    document.getElementById("cliente").innerHTML = usuario.nombre + " " + usuario.apellidos;

}


function solicitarDatos(servlet, tabla, fn, callback) {
    fn(callback, servlet, tabla);

}

function crearTabla(idTabla, datosJSON) {
    pizzas = JSON.parse(datosJSON['pizzas']);
    complementos = JSON.parse(datosJSON.complementos);

    var refTabla = document.getElementById(idTabla);
    if (refTabla) {
        var refhTabla = document.getElementById("hprincipal");
        refhTabla.textContent = "Pizzas";
        while (refTabla.firstChild) {
            refTabla.removeChild(refTabla.firstChild);
        }

        pizzas.forEach(function (v, i, a) {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            var ima = document.createElement("img");
            ima.src = agregarImgPizza(v.nombre);
            ima.className = 'imaPizza';
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(ima);


            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = v.nombre;

            nuevaCelda = nuevaFila.insertCell(-1);
            opcTamaño(nuevaCelda, v);



            nuevaCelda = nuevaFila.insertCell(-1);
            v.ingredientes.forEach(function (vec, j, ar) {
                nuevaCelda.innerText += (j === 0) ? vec.nombre : " ," + vec.nombre;
            });

            nuevaCelda = nuevaFila.insertCell(-1);
            var btn = document.createElement("BUTTON");
            btn.id = 'bnt' + i;
            btn.value = v.id;
            btn.className = "btn";
//            btn.innerHTML = "<i class='material-icons' ></i>";
            btn.onmouseover = btnColor;
            btn.onmouseout = btnColorTrans;
            btn.onclick = agregarPzza;
            nuevaCelda.appendChild(btn);

        });
    }
}


function crearTablaComplemento(idTabla, datosJSON) {

    var refTabla = document.getElementById(idTabla);
    if (refTabla) {

        complementos.forEach(function (v, i, a) {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;
            nuevaCelda = nuevaFila.insertCell(-1);

            nuevaCelda.innerText = v.nombre;

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = v.precio;

            nuevaCelda = nuevaFila.insertCell(-1);
            var btn = document.createElement("BUTTON");
            btn.id = 'bnt' + i;
            btn.value = v.id;
            btn.className = "btn";
//            btn.innerHTML = "<i class='material-icons' ></i>";
            btn.onmouseover = btnColor;
            btn.onmouseout = btnColorTrans;
            btn.onclick = agregarComplemento;
            nuevaCelda.appendChild(btn);

        });
    }
}



function crearTablaCarrito(idTabla, datosJSON) {
    var total = 0;
    var refTabla = document.getElementById(idTabla);

    if (refTabla) {
        var lista = datosJSON;
        lista.forEach(function (v, i, a) {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            var ima = document.createElement("img");
            ima.src = agregarImgPizza(v.nombre);
            ima.className = 'imaPizza';
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(ima);


            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = v.nombre;

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = v.precio;
            total += parseInt(v.precio);


            nuevaCelda = nuevaFila.insertCell(-1);
            v.ingredientes.forEach(function (vec, j, ar) {
                nuevaCelda.innerText += (j === 0) ? vec.nombre : " ," + vec.nombre;

            });

            /////////////////////////////////////////////////////////
            nuevaCelda = nuevaFila.insertCell(-1);
            var btn = document.createElement("BUTTON");
            btn.id = 'bnt' + i;
            btn.value = i;
            btn.className = "borrar";
//            btn.innerHTML = "<i class='borrar'></i>";
            btn.onmouseover = btnColor;
            btn.onmouseout = btnColorTrans;
            btn.onclick = eliminarPizza;
            nuevaCelda.appendChild(btn);
            ////////////////////////////////////////////////////////eliminar pizza
        });

        complementosAgregados.forEach(function (v, i, a) {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;
            nuevaCelda = nuevaFila.insertCell(-1);

            nuevaCelda.innerText = v.nombre;

            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = v.precio;
            total += v.precio;


            /////////////////////////////////////////////////////////eliminar complemento
            nuevaCelda = nuevaFila.insertCell(-1);
            var btn = document.createElement("BUTTON");
            btn.id = 'bnt' + (i + pizzasAgregadas.length);
            btn.value = i;
            btn.className = "borrar";
            btn.innerHTML = "<i class='borrar'></i>";
            btn.onmouseover = btnColor;
            btn.onmouseout = btnColorTrans;
            btn.onclick = eliminarComplemento;
            nuevaCelda.appendChild(btn);
            ////////////////////////////////////////////////////////

        });

        var nuevaFila = refTabla.insertRow(-1);
        var nuevaCelda;
        nuevaFila.className = "total";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.innerText = "total :";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.innerText = total;

//////////////////////////////////////////////////////////////////////////////boton pago en linea

        nuevaCelda = nuevaFila.insertCell(-1);
        var btn = document.createElement("BUTTON");
//        btn.id = 'bnt' + (i + pizzasAgregadas.length);
//        btn.value = i;
        btn.className = "pago_tarjeta";
        btn.value = true;
        btn.onmouseover = btnColor;
        btn.onmouseout = btnColorTrans;
        btn.onclick = enviarFactura;
        nuevaCelda.appendChild(btn);
//////////////////////////////////////////////////////////////////////// boton pago de contado
        nuevaCelda = nuevaFila.insertCell(-1);
        var btn = document.createElement("BUTTON");
//        btn.id = 'bnt' + (i + pizzasAgregadas.length);
//        btn.value = i;
        btn.className = "enviar";
        btn.value = false;
        btn.onmouseover = btnColor;
        btn.onmouseout = btnColorTrans;
        btn.onclick = enviarFactura;
        nuevaCelda.appendChild(btn);
    }
}

function agregarPzza(e) {
    var id = this.value;

    pizzas.map(function (pizza) {
        if (pizza.id == id) {
            let precio = parseInt(document.getElementById(pizza.id).value);
            let precioA = pizza.precio;
            let grande = (pizza.precio * 1.5);
            let familiar = (pizza.precio * 2);
            switch (precio) {
                case pizza.precio:
                    pizza.tamaño = "pequena";
                    break;
                case grande:
                    pizza.tamaño = "grande";
                    break;
                case familiar:
                    pizza.tamaño = "familiar";
                    break;
                default:
                    pizza.tamaño = "xxxxxxx";
                    break;
            }
            pizza.precio = precio;
            pizzasAgregadas.push({...pizza});
            pizza.precio = precioA;
            document.getElementById("hCar").innerHTML = pizzasAgregadas.length + complementosAgregados.length;
        }
    });

}


function eliminarPizza(e) {
    var indice = this.value;
    pizzasAgregadas.splice(indice, 1);
    verificarCarrito();
}


function eliminarComplemento(e) {
    var indice = this.value;
    complementosAgregados.splice(indice, 1);
    verificarCarrito();
}


function agregarComplemento(e) {
    var id = this.value;
    complementos.map(function (cmp) {
        if (cmp.id == id) {
            complementosAgregados.push(cmp);
            document.getElementById("hCar").innerHTML = pizzasAgregadas.length + complementosAgregados.length;
        }
    });
}


function verificarCarrito() {
    var refhTabla = document.getElementById("hprincipal");
    refhTabla.textContent = "Carrito de compras";
    var refTabla = document.getElementById("principal");
    while (refTabla.firstChild) {
        refTabla.removeChild(refTabla.firstChild);
    }
    document.getElementById("hCar").innerHTML = pizzasAgregadas.length + complementosAgregados.length;
    crearTablaCarrito("principal", pizzasAgregadas);
}


function opcTamaño(nuevaCelda, datos) {

    var select = document.createElement("select");
    var peq = document.createElement("option");
    var mediana = document.createElement("option");
    var grande = document.createElement("option");
    var textPrecio = document.createElement("label");

    peq.value = datos.precio;
    peq.innerHTML = "pequena";
    mediana.value = datos.precio * 1.5;
    mediana.innerHTML = "grande";
    grande.value = datos.precio * 2;
    grande.innerHTML = "familiar";

    select.id = datos.id;
    select.size = 1;
    select.appendChild(peq);
    select.appendChild(mediana);
    select.appendChild(grande);

    nuevaCelda.onchange = actualizarPrecio;

    textPrecio.innerText = datos.precio + '$';

    nuevaCelda.appendChild(textPrecio);
    nuevaCelda.appendChild(select);

}


function categorias() {
    var refhTabla = document.getElementById("hprincipal");
    refhTabla.textContent = "Complementos";
    var refTabla = document.getElementById("principal");
    while (refTabla.firstChild) {
        refTabla.removeChild(refTabla.firstChild);
    }

    crearTablaComplemento("principal", pizzasAgregadas);
}



function actualizarPrecio(e) {
    this.firstChild.innerText = this.lastChild.value + '$';
}



function agregarImgPizza(nombre) {
    var ima = '';
    switch (nombre) {
        case 'Margarita':
            ima = 'imagenes/margarita.jpg';
            break;
        case 'Suprema':
            ima = 'imagenes/suprema.jpg';
            break;
        case 'jamon y queso':
            ima = 'imagenes/jamon.jpg';
            break;
        case 'peperoni':
            ima = 'imagenes/peperoni.jpg';
            break;
        case 'hawaiana':
            ima = 'imagenes/hawaiana.jpg';
            break;
        case 'mexicana':
            ima = 'imagenes/mexicana.jpg';
            break;
    }
    return ima;
}


function btnColor(e) {
//    document.getElementById(e.target.id).style = "background-color:#e3e3e3";
    this.style = "background-color:#e3e3e3";
}



function btnColorTrans(e) {
//    document.getElementById(e.target.id).style = "background-color:transparent";
    this.style = "background-color:transparent";
}


function imprimirCodigo(datos) {

    document.getElementById("hprincipal").innerHTML = "Orden de compra número"
    document.getElementById("principal").innerHTML = datos.respuesta;
    complementosAgregados = [];
    pizzasAgregadas = [];
    document.getElementById("hCar").innerHTML = pizzasAgregadas.length + complementosAgregados.length;
}


function enviarFactura() {
    let total = 0;
    var datos = new FormData();
    var bandP = (pizzasAgregadas.length > 0) ? true : false;
    var bandC = (complementosAgregados.length > 0) ? true : false;

    datos.append("bandP", bandP);
    datos.append("bandC", bandC);
    datos.append("usuario", usuario.usuario.nombreUsuario);
    datos.append("metodo", this.value);
    pizzasAgregadas.forEach(function (pizza) {
        total += pizza.precio;
        datos.append("pizza_codigo", pizza.id);
        datos.append("tamaño", pizza.tamaño);
        datos.append("precio", pizza.precio);
    });

    complementosAgregados.forEach(function (complemento) {
        total += complemento.precio;
        datos.append("ComplementoId", complemento.id);
        datos.append("ComplementoNombre", complemento.nombre);
        datos.append("ComplementoPrecio", complemento.precio);
    });

    datos.append("total", total);
    if(complementosAgregados.length > 0 || pizzasAgregadas.length > 0){
    getJSON('ServletFactura', datos, imprimirCodigo);
    }
}



function fetchJSON(callback, url, obj) {
    fetch(url).then((resultados) => {
        return resultados.json();
    }).then((datosJSON) => {
        callback(obj, datosJSON);
    });
}



function getJSON(source, data, callback) {
    fetch(source, {
        method: 'POST',
        body: data
    }).then((result) => {
        return result.json();
    }).then(callback);
}


function crearComentario(e) {
    document.getElementById("hprincipal").innerHTML = "Agrege su comentario";

    var tablaRef = document.getElementById("principal");
    while (tablaRef.firstChild) {
        tablaRef.removeChild(tablaRef.firstChild);
    }

    var textArea = document.createElement("textarea");
    textArea.style = "width:80%";
    textArea.id = "comentario";
    tablaRef.appendChild(textArea);

    var enviar = document.createElement("button");
    enviar.onclick = enviarComentario;
    enviar.innerHTML = "Enviar";
    enviar.className = "h";
    enviar.onmouseover = btnColor;
    enviar.onmouseout = btnColorTrans;
    tablaRef.appendChild(enviar);

}


function enviarComentario() {
    var comentario = document.getElementById("comentario").value;
    var datos = new FormData();
    datos.append("comentario", comentario);
    datos.append("usuario", usuario.usuario.nombreUsuario);
    getJSON('ServletComentario', datos, respuestaComentario);
}

function respuestaComentario(datos) {
    document.getElementById("hprincipal").innerHTML = datos.respuesta;

    var tablaRef = document.getElementById("principal");
    while (tablaRef.firstChild) {
        tablaRef.removeChild(tablaRef.firstChild);
    }
}


function consultarPedidio() {
    document.getElementById("hprincipal").innerHTML = "Ingrese número de pedido";

    var tablaRef = document.getElementById("principal");
    while (tablaRef.firstChild) {
        tablaRef.removeChild(tablaRef.firstChild);
    }

    var input = document.createElement("input");
    input.style = "width:50%";
    input.id = "numeroPedido";
    input.type = "number";
    tablaRef.appendChild(input);

    var enviar = document.createElement("button");
    enviar.onclick = enviarConsultaPedido;
    enviar.innerHTML = "Enviar";
    enviar.className = "h";
    enviar.onmouseover = btnColor;
    enviar.onmouseout = btnColorTrans;
    tablaRef.appendChild(enviar);

}


function enviarConsultaPedido() {
    var numero = document.getElementById("numeroPedido").value;
    var datos = new FormData();
    datos.append("numero", numero);
    getJSON('ServletRastrearFactura', datos, respuestaComentario);
}


function actualizarUsuario() {

    var refTabla = document.getElementById("principal");
    if (refTabla) {
        var refhTabla = document.getElementById("hprincipal");
        refhTabla.textContent = "Datos del usuario";
        while (refTabla.firstChild) {
            refTabla.removeChild(refTabla.firstChild);
        }

        var nuevaFila = refTabla.insertRow(-1);
        var nuevaCelda;

        var label = document.createElement("label");
        label.innerHTML = "Nombre:";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);

        label = document.createElement("label");
        label.innerHTML = usuario.nombre + " " + usuario.apellidos;

        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);

//////////////////////////////////////////////////////////////////////////////////
        nuevaFila = refTabla.insertRow(-1);
        label = document.createElement("label");
        label.innerHTML = "Usuario:";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);

        label = document.createElement("label");
        label.innerHTML = usuario.usuario.nombreUsuario;

        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);


///////////////////////////////////////////////////////////////////////////////////        
        nuevaFila = refTabla.insertRow(-1);
        label = document.createElement("label");
        label.innerHTML = "Cédula:";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);

        label = document.createElement("label");
        label.innerHTML = usuario.cedula;

        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);







        nuevaFila = refTabla.insertRow(-1);
        label = document.createElement("label");
        label.innerHTML = "Direccion:";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);

        label = document.createElement("input");
        label.value = usuario.dirección;
        label.id = "direccion";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);







        nuevaFila = refTabla.insertRow(-1);
        label = document.createElement("label");
        label.innerHTML = "Telefono:";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);
        label = document.createElement("input");
        label.value = usuario.telefono;
        label.id = "telefono";
        label.type = "number";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);



        nuevaFila = refTabla.insertRow(-1);
        label = document.createElement("label");
        label.innerHTML = "Contraseña:";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);
        label = document.createElement("input");
        label.value = usuario.usuario.contraseña;
        label.id = "contraseña";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);




        nuevaFila = refTabla.insertRow(-1);
        nuevaCelda = nuevaFila.insertCell(-1);
        var btn = document.createElement("BUTTON");
        btn.innerHTML="Enviar";
        btn.value = 0;
        btn.onmouseover = btnColor;
        btn.onmouseout = btnColorTrans;
        btn.onclick = enviarDatosCliente;
        nuevaCelda.appendChild(btn);
    }
}



function enviarDatosCliente() {
    var direccion = document.getElementById("direccion").value;
    var telefono = document.getElementById("telefono").value;
    var contraseña = document.getElementById("contraseña").value;
    var datos = new FormData();


    usuario.usuario.contraseña = contraseña;
    usuario.telefono = telefono;
    usuario.dirección = direccion;

    datos.append("nombre", usuario.nombre);
    datos.append("apellido", usuario.apellidos);
    datos.append("direccion", direccion);
    datos.append("telefono", telefono);
    datos.append("usuario", usuario.usuario.nombreUsuario);
    datos.append("contrasena", contraseña);

    getJSON('ServletActualizarCliente', datos, respuestaComentario);
}


function consultarComentarios(idTabla, datos) {

    var refTabla = document.getElementById(idTabla);
    if (refTabla) {
        var refhTabla = document.getElementById("hprincipal");
        refhTabla.textContent = "Comentarios";
        while (refTabla.firstChild) {
            refTabla.removeChild(refTabla.firstChild);
        }
        var comentarios = JSON.parse(datos.comentarios);
        comentarios.forEach(function (comentario) {
            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;
            label = document.createElement("label");
            label.innerHTML = "Cliente:";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);
            label = document.createElement("label");
            label.innerHTML = comentario.comentario;
            label.id = comentario.usuario;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);

            label = document.createElement("label");
            label.innerHTML = "Comentario:";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);
            label = document.createElement("label");
            label.innerHTML = comentario.usuario;
            label.id = comentario.comentario;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);

        });

    }
}

function listarComentario() {
    fetchJSON(consultarComentarios, "ServletComentariosListar", "principal");
}


function listarPizzas() {
    var refhTabla = document.getElementById("hprincipal");
    refhTabla.textContent = "El adminstrador puede editar el precio de la pizza pequeña el cual es una referecia para hacer el cálculo de la pizza grande y familiar";
    var refTabla = document.getElementById("principal");

    if (refTabla) {
        while (refTabla.firstChild) {
            refTabla.removeChild(refTabla.firstChild);
        }
        pizzas.forEach(function (v, i, a) {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            var ima = document.createElement("img");
            ima.src = agregarImgPizza(v.nombre);
            ima.className = 'imaPizza';
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(ima);


            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = v.nombre;

            nuevaCelda = nuevaFila.insertCell(-1);
            var input = document.createElement("input");
            input.value = v.precio;
            input.type = "number";
            input.id = "precio";
            input.min = 10;
            nuevaCelda.appendChild(input);


            nuevaCelda = nuevaFila.insertCell(-1);
            v.ingredientes.forEach(function (vec, j, ar) {
                nuevaCelda.innerText += (j === 0) ? vec.nombre : " ," + vec.nombre;
            });

            /////////////////////////////////////////////////////////
            nuevaCelda = nuevaFila.insertCell(-1);
            var btn = document.createElement("BUTTON");
            btn.id = 'bnt' + i;
            btn.value = i;
            btn.className = "actualizar"
            btn.innerHTML = "Actualizar";
            btn.onmouseover = btnColor;
            btn.onmouseout = btnColorTrans;
            btn.onclick = actualizarPizzas;
            nuevaCelda.appendChild(btn);
            ////////////////////////////////////////////////////////eliminar pizza
        });

        complementosAgregados.forEach(function (v, i, a) {

            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;
            nuevaCelda = nuevaFila.insertCell(-1);

            nuevaCelda.innerText = v.nombre;

            nuevaCelda = nuevaFila.insertCell(-1);
            var input = document.getElementById("input");
            input.value = vprecio;
            nuevaCelda.appendChild(input);


            /////////////////////////////////////////////////////////eliminar complemento
            nuevaCelda = nuevaFila.insertCell(-1);
            var btn = document.createElement("BUTTON");
            btn.id = 'bnt' + (i + pizzasAgregadas.length);
            btn.value = i;
            btn.className = "borrar";
            btn.innerHTML = "<i class='borrar'></i>";
            btn.onmouseover = btnColor;
            btn.onmouseout = btnColorTrans;
            btn.onclick = eliminarComplemento;
            nuevaCelda.appendChild(btn);
            ////////////////////////////////////////////////////////

        });



    }
}

function actualizarPizzas() {
    var precio = document.getElementById("precio").value;
    var datos = new FormData();
    pizzas[parseInt(this.value)].precio = precio;


    datos.append("precio", precio);
    datos.append("id_pizza", pizzas[parseInt(this.value)].id);


    getJSON('ServletActualizarPizza', datos, listarPizzas());
}


function actualizarEstado() {
    fetchJSON(pedidosProceso, "servletVentasEstado", "principal");
}


function pedidosProceso(tabla, datos) {

    var refTabla = document.getElementById(tabla);
    if (refTabla) {
        var refhTabla = document.getElementById("hprincipal");
        refhTabla.textContent = "Actualizar orden";
        while (refTabla.firstChild) {
            refTabla.removeChild(refTabla.firstChild);
        }

        var facturas = JSON.parse(datos.Factura);
        facturas.forEach((factura) => {
            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            var label = document.createElement("label");
            label.innerHTML = "Numero facura:";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);
            label = document.createElement("label");
            label.innerHTML = factura.id;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);



            var label = document.createElement("label");
            label.innerHTML = "Total:";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);
            label = document.createElement("label");
            label.innerHTML = factura.total;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);


            var label = document.createElement("label");
            label.innerHTML = "Metodo pago:";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);
            label = document.createElement("label");

            label.innerHTML = (factura.metodo_pago) ? "Pago en linea" : "Pago contado";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);


            var label = document.createElement("label");
            label.innerHTML = "Estado:";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);
            label = document.createElement("input");
            label.value = factura.id;
            label.onchange = agregarFactura;
            label.type = "checkbox";
            nuevaCelda.appendChild(label);


        });
        var nuevaFila = refTabla.insertRow(-1);
        var nuevaCelda;
        var label = document.createElement("button");
//        label.className="actualizar";
        label.innerHTML = "enviar";
        label.onclick = actualizarPedidos;
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.appendChild(label);


    }

}


function agregarFactura(e) {
    var id = this.value;
    var band = false;
    var j = 0;
    for (var i = 0; i < facturas.length; i++) {
        if (facturas[i] == id) {
            band = true;
            j = i;
        }
    }
    if (!(band)) {
        facturas.push(id);
    } else {
        facturas.splice(j, 1);
    }
}


function actualizarPedidos() {

    var datos = new FormData();

    facturas.forEach((factura) => {
        datos.append("numero", factura);
    });

    getJSON('servletActualizarPedidos', datos, respuestaComentario);

}



function busFacFecha() {
    var refTabla = document.getElementById("principal");
    while (refTabla.firstChild) {
        refTabla.removeChild(refTabla.firstChild);
    }


    var refhTabla = document.getElementById("hprincipal");
    refhTabla.textContent = "Filtar facturas por rango de fechas";


    var nuevaFila = refTabla.insertRow(-1);
    var nuevaCelda;

    var label = document.createElement("label");
    label.innerHTML = "Fecha inicial:";
    nuevaCelda = nuevaFila.insertCell(-1);
    nuevaCelda.appendChild(label);
    label = document.createElement("input");
    label.id = "fechaInicial";
    label.type = "date"
    nuevaCelda = nuevaFila.insertCell(-1);
    nuevaCelda.appendChild(label);


    var label = document.createElement("label");
    label.innerHTML = "Fecha final:";
    nuevaCelda = nuevaFila.insertCell(-1);
    nuevaCelda.appendChild(label);
    label = document.createElement("input");
    label.type = "date"
    label.id = "fechaFinal";
    nuevaCelda = nuevaFila.insertCell(-1);
    nuevaCelda.appendChild(label);

    var nuevaCelda;
    var label = document.createElement("button");
    label.innerHTML = "enviar";
    label.onclick = enviarConFacFec;
    nuevaCelda = nuevaFila.insertCell(-1);
    nuevaCelda.appendChild(label);

}


function enviarConFacFec() {
    var fInicio = document.getElementById("fechaInicial").value;
    var fFinal = document.getElementById("fechaFinal").value;

    var datos = new FormData();

    datos.append("fechai", fInicio);
    datos.append("fechaf", fFinal);


    getJSON('servletVentas', datos, listarFacFecha);

}


function listarFacFecha(datos){
    
    
    var refTabla = document.getElementById("principal");
    if (refTabla) {
        var refhTabla = document.getElementById("hprincipal");
        refhTabla.textContent = "Actualizar orden";
        while (refTabla.firstChild) {
            refTabla.removeChild(refTabla.firstChild);
        }

        var facturas = JSON.parse(datos.Factura);
        facturas.forEach((factura) => {
            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;

            var label = document.createElement("label");
            label.innerHTML = "Numero facura:";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);
            label = document.createElement("label");
            label.innerHTML = factura.id;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);



            var label = document.createElement("label");
            label.innerHTML = "Total:";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);
            label = document.createElement("label");
            label.innerHTML = factura.total;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);


            var label = document.createElement("label");
            label.innerHTML = "Metodo pago:";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);
            label = document.createElement("label");

            label.innerHTML = (factura.metodo_pago) ? "Pago en linea" : "Pago contado";
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.appendChild(label);

        });
    }
    
}

function salir(){
    window.localStorage.clear();
    document.location.href = "index.jsp";
}