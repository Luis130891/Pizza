/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 * 
 *          702000163 Luis Venegas Ulloa
 */
public class Pizza {
    
    private int id;
    private String tamaño;
    private int cantidad;
    private String nombre;
    private double precio;
    ArrayList<Ingrediente> ingredientes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
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

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", tama\u00f1o=" + tamaño + ", cantidad=" + cantidad + ", nombre=" + nombre + ", precio=" + precio + ", ingredientes=" + ingredientes + '}';
    }

    public Pizza(int id, String tamaño, int cantidad, String nombre, double precio, ArrayList<Ingrediente> ingredientes) {
        this.id = id;
        this.tamaño = tamaño;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio = precio;
        this.ingredientes = ingredientes;
    }

    
    public Pizza(int id, String tamaño,double precio) {
        this.id = id;
        this.tamaño = tamaño;
        this.cantidad = 1;
        this.nombre = "";
        this.precio = precio;
        this.ingredientes = null;
    }

    
    public Pizza() {
        this(0,"",0,"",0.0,null);
    }

}
