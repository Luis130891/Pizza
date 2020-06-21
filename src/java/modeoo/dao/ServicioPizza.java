/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeoo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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
            
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(cnxPool);
            configurar();
        } catch (IOException
                |NamingException e) {
            
        }
    }
    
    private void configurar(){
        if(configuracion!=null){
        this.contraseña = configuracion.getProperty("contraseña");
        this.usuario= configuracion.getProperty("usuario");
        }
    }
    
    public ServicioPizza obtenerInstancia(){
        if(instancia==null){
            instancia= new ServicioPizza();
        }
        return instancia;
    }
    
//    public ArrayList<Pizza>pizzas(){
//        ArrayList<Pizza> pizzas=new ArrayList();
//        try(Connection cnx= ds.getConnection(usuario,contraseña);
////                PreparedStatement stm= cnx.prepareCall()){
//            
//        }catch(SQLException e){
//            
//        }
//        return pizzas;
//    }
//    
    
    
    private String contraseña;
    private String usuario;
    private DataSource ds;
    private Properties configuracion;
    private static ServicioPizza instancia;
    
}
