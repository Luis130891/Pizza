/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 *          Autores: 
 *          116720428Kenneth Ariel Chaves Herrera
 *          702000163 Luis Venegas Ulloa
 */
public class Cliente {

    private int cedula;
    private String nombre;
    private String apellidos;
    private String dirección;
    private int telefono;
    private Usuario usuario;

    public Cliente() {
    }

    public Cliente(int cedula, String nombre, String apellidos, String dirección, int telefono, Usuario usuario) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dirección = dirección;
        this.telefono = telefono;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Cliente{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direcci\u00f3n=" + dirección + ", telefono=" + telefono + ", usuario=" + usuario + '}';
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
