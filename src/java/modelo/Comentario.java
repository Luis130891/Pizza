/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 *    
 *          702000163 Luis Venegas Ulloa
 */
public class Comentario {
    private String comentario;
    private String usuario;

    public Comentario() {
        this("","");
    }

    public Comentario(String comentario, String usuario) {
        this.comentario = comentario;
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
