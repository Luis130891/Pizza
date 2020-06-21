/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeoo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import modelo.Ingrediente;
import modelo.Pizza;

/**
 *
 * @author Luis Venegas Ulloa
 */
public class ServicioPizza {

    public ServicioPizza() {
        configuracion = new Properties();
        try {
            configuracion.load(getClass().getResourceAsStream("configuration.properties"));
            String cnxPool = configuracion.getProperty("conexion");
            System.out.println(cnxPool);
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(cnxPool);
            configurar();
        } catch (IOException
                | NamingException e) {

        }
    }

    private void configurar() {
        if (configuracion != null) {
            this.contraseña = configuracion.getProperty("contraseña");
            this.usuario = configuracion.getProperty("usuario");
        }
    }

    public static ServicioPizza obtenerInstancia() {
        if (instancia == null) {
            instancia = new ServicioPizza();
        }
        return instancia;
    }

    public ArrayList<Pizza> pizzas() {
        ArrayList<Pizza> pizzas = new ArrayList();
        try (Connection cnx = ds.getConnection(usuario,contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_LISTAR);) {
            stm.clearParameters();
           try( ResultSet rs = stm.executeQuery();){
               int id_anterior=0;
               int i=0;
               while(rs.next()){
                   if(id_anterior!=rs.getInt(1)){
                       ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
                       ingredientes.add(new Ingrediente(rs.getString(5),rs.getInt(4)));
                       pizzas.add(new Pizza(rs.getInt(1),rs.getString(2),rs.getDouble(3),ingredientes));
                       i++;
                       id_anterior=rs.getInt(1);
                   }else{
                       pizzas.get(i-1).getIngredientes().add(new Ingrediente(rs.getString(5),rs.getInt(4)));
                   }
               }
           }
        } catch (SQLException e) {
            System.out.printf("ERROR-->%s",e.getMessage());
        }
        
        System.out.println("cantidad :"+pizzas.size());
        return pizzas;
    }

    
    
    
    private static final String CMD_LISTAR
            = "select p.id,p.nombre,p.precio,i.id, i.nombre from ingrediente i,pizza p,detalleIngrediente d"
            + " where i.id=d.pk_id_ingrediente_fk and d.pk_id_pizza_fk= p.id ;";
    private String contraseña;
    private String usuario;
    private DataSource ds;
    private Properties configuracion;
    private static ServicioPizza instancia;

}
