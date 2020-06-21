/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeoo.dao;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Luis Venegas Ulloa
 */
public class ServicioClientes implements Serializable{

    public ServicioClientes() {
        configuracion = new Properties();
        try {
            this.configuracion.load(getClass().getResourceAsStream("configuration.properties"));
            String cnxPool = configuracion.getProperty("conexion");
            
            InitialContext ctx = new InitialContext();
            this.ds = (DataSource) ctx.lookup(cnxPool);
            configurar();
        } catch (IOException
                | NamingException ex) {
            System.out.printf("ERROR--->%s", ex.getMessage());
        }
    }
   

    private void configurar() {
        if (configuracion != null) {
            usuario = configuracion.getProperty("usuario");
            contraseña = configuracion.getProperty("contraseña");
        }
    }

    public static ServicioClientes getInstancia() {
        if (instancia == null) {
            instancia = new ServicioClientes();
        }
        return instancia;
    }
    
    
    
    
    
    private static ServicioClientes instancia;

    private Properties configuracion;
    private DataSource ds;
    private String usuario;
    private String contraseña;

}
