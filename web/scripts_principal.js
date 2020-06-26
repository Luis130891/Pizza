/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global fetch, int */



var pizzas;
var pizzasAgregadas = [];
function init() {
    solicitarDatos("ServletPrincipal", "principal", fetchJSON, crearTabla);
    document.getElementById("hCar").innerHTML = pizzasAgregadas.length;
}


function solicitarDatos(servlet, tabla, fn, callback) {
    fn(callback, servlet, tabla);

}

function crearTabla(idTabla, datosJSON) {

    pizzas = datosJSON;

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
            btn.innerHTML = "<i class='material-icons' ></i>";
            btn.onmouseover = btnColor;
            btn.onmouseout = btnColorTrans;
            btn.onclick = agregarPzza;
            nuevaCelda.appendChild(btn);

        });
    }
}


function crearTablaCarrito(idTabla, datosJSON) {

    var refTabla = document.getElementById(idTabla);
    var total = 0;
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




            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = v.cantidad;

//            nuevaCelda = nuevaFila.insertCell(-1);
//            var btn = document.createElement("BUTTON");
//            btn.id = 'bnt' + i;
//            btn.value = v.id;
//            btn.className = "btn";
//            btn.innerHTML = "<i class='material-icons' ></i>";
//            btn.onmouseover = btnColor;
//            btn.onmouseout = btnColorTrans;
//            btn.onclick = agregarPzza;
//            nuevaCelda.appendChild(btn);

        });

        var nuevaFila = refTabla.insertRow(-1);
        var nuevaCelda;
        nuevaFila.className="total";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.innerText = "total :";
        nuevaCelda = nuevaFila.insertCell(-1);
        nuevaCelda.innerText = total;
    }
}

function agregarPzza(e) {
    var id = this.value;
    pizzas.map(function (pizza) {
        if (pizza.id == id) {
            let precio = document.getElementById(pizza.id).value;
            pizza.precio = precio;
            pizzasAgregadas.push({...pizza});
            document.getElementById("hCar").innerHTML = pizzasAgregadas.length;
        }
    });

//    let pizzaDos = pizzas.filter(pizza=>pizza.id==id);
//    let precio = document.getElementById(pizzaDos[0].id).value; 
//    pizzaDos[0].precio=precio;
//    pizzasAgregadas.push({...pizzaDos[0]});
//    document.getElementById("hCar").innerHTML = pizzasAgregadas.length;
}

function verificarCarrito() {
    var refhTabla = document.getElementById("hprincipal");
    refhTabla.textContent = "Carrito de compras";
    var refTabla = document.getElementById("principal");
    while (refTabla.firstChild) {
        refTabla.removeChild(refTabla.firstChild);
    }

    crearTablaCarrito("principal", pizzasAgregadas);
}


function opcTamaño(nuevaCelda, datos) {

    var select = document.createElement("select");

    var peq = document.createElement("option");
    var mediana = document.createElement("option");
    var grande = document.createElement("option");
    var textPrecio = document.createElement("label");

    peq.value = datos.precio;
    peq.innerHTML = "pequeña";
    mediana.value = datos.precio * 1.5;
    mediana.innerHTML = "mediana";
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
    alert(ima);
}


function btnColor(e) {
    document.getElementById(e.target.id).style = "background-color:#e3e3e3";
}



function btnColorTrans(e) {
    document.getElementById(e.target.id).style = "background-color:transparent";
}


function fetchJSON(callback, url, obj) {
    fetch(url).then((resultados) => {
        return resultados.json();
    }).then((datosJSON) => {
        callback(obj, datosJSON);
    });
}
