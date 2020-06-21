/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Luis Venegas Ulloa
 */
public class Pizza {
    private String nombre;
    private String tamaño;
    private double precio;
    ArrayList<Ingrediente> ingredientes;

    public Pizza() {
        this("","", 0,new ArrayList<Ingrediente>());
    }

    public Pizza(String nombre, String tamaño, double precio, ArrayList<Ingrediente> ingredientes) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.precio = precio;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
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
}
