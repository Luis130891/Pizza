/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Luis Venegas Ulloa
 */
public class Ingrediente {
    private String nombre;
    private double precio;
    private int id;

    @Override
    public String toString() {
        return "Ingrediente{" + "nombre=" + nombre + ", precio=" + precio + ", id=" + id + '}';
    }

    public Ingrediente() {
    }

    public Ingrediente(String nombre, double precio, int id) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
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
