/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeoo.dao;

import java.io.IOException;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import modelo.Ingrediente;

/**
 *
 * @author Luis Venegas Ulloa
 */
public class ServicioIngredientes implements Serializable{

    public ServicioIngredientes() {
        try {
            this.configuracion = new Properties();
            this.configuracion.load(getClass().getResourceAsStream("configuration.properties"));
            String cnxPool = configuracion.getProperty("conexion");
            System.out.println(cnxPool);
            InitialContext ctx = new InitialContext();
            this.ds = (DataSource) ctx.lookup(cnxPool);
            configurar();
        } catch (IOException | NamingException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    private void configurar() {
        if (configuracion != null) {
            usuario = configuracion.getProperty("usuario");
            clave = configuracion.getProperty("contraseña");
        }
    }
    
 

    public static ServicioIngredientes obtenerInstancia() {
        if (instancia == null) {
            instancia = new ServicioIngredientes();
        }
        return instancia;
    }
    
    public ArrayList<Ingrediente> ingredientes(){
        ArrayList<Ingrediente> ingredientes= new ArrayList<Ingrediente>();
        try(Connection cnx = ds.getConnection(usuario,clave);
            CallableStatement stm = cnx.prepareCall(CMD_LISTAR)){
            stm.clearParameters();
           try(ResultSet rs = stm.executeQuery();){
               while(rs.next()){
                  ingredientes.add(new Ingrediente(rs.getString(1),rs.getDouble(2),rs.getInt(3)));
               }
           }
        }catch(SQLException ex){
            System.out.printf("ERROR-->%s",ex.getMessage());
        }
        System.out.println(ingredientes.toString());
        return ingredientes;
    }

    public static void main(String[] args) {
        ServicioIngredientes.obtenerInstancia();
    }
    private String CMD_LISTAR="select id,nombre,precio from ingrediente;";
    
    
    
    private static ServicioIngredientes instancia;

    private Properties configuracion = null;
    private DataSource ds = null;
    private String usuario = null;
    private String clave = null;
}
