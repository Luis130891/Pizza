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
    private int id;

    @Override
    public String toString() {
        return "Ingrediente{" + "nombre=" + nombre + ", id=" + id + '}';
    }

    public Ingrediente() {
    }

    public Ingrediente(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
