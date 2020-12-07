/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 *
 *          702000163 Luis Venegas Ulloa
 */
public class Factura {
    private int id;
    private double total;
    private boolean  metodo_pago;
    private String estado;
    private Date fecha;
    private String usuario;
    private ArrayList<Pizza> pizzas;
    private ArrayList<Complemento> complementos;

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", total=" + total + ", metodo_pago=" + metodo_pago + ", estado=" + estado + ", fecha=" + fecha + ", usuario=" + usuario + ", pizzas=" + pizzas + ", complementos=" + complementos + '}';
    }

    public Factura() {
        this(0,0.0,false,"",null,null,null,null);
    }

    public Factura(int id, double total, boolean metodo_pago, String estado, Date fecha, String usuario, ArrayList<Pizza> pizzas, ArrayList<Complemento> complementos) {
        this.id = id;
        this.total = total;
        this.metodo_pago = metodo_pago;
        this.estado = estado;
        this.fecha = fecha;
        this.usuario = usuario;
        this.pizzas = pizzas;
        this.complementos = complementos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(boolean metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public ArrayList<Complemento> getComplementos() {
        return complementos;
    }

    public void setComplementos(ArrayList<Complemento> complementos) {
        this.complementos = complementos;
    }
}
