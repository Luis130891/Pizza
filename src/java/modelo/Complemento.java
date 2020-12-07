/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 

 *          702000163 Luis Venegas Ulloa
 */
public class Complemento {
    private String nombre;
    private double precio;
    private int cantidad; 
    private int id;

    @Override
    public String toString() {
        return "Complemento{" + "nombre=" + nombre + ", precio=" + precio + ", id=" + id + '}';
    }

    public Complemento() {
    }

    public Complemento(String nombre, double precio, int id) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad=1;
        this.id = id;
    }

    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
