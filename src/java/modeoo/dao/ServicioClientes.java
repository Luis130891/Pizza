/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeoo.dao;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import modelo.Cliente;

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
    public boolean existeUsuario(String usu) {
        try (Connection cnx = ds.getConnection();
                PreparedStatement stm = cnx.prepareStatement(CMD_EXISTE_USUARIO);) {
            stm.clearParameters();
            stm.setString(1, usu);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return false;
    }

    public void insertarCliente(Cliente x) {
        try (Connection cnx = ds.getConnection();
                PreparedStatement stm = cnx.prepareCall(CMD_INSERTAR1);) {
            stm.clearParameters();
            stm.setInt(1, x.getCedula());
            stm.setString(2, x.getNombre());
            stm.setString(3, x.getApellidos());
            stm.setString(4, x.getDirección());
            stm.setInt(5, x.getTelefono());
            stm.execute();
        } catch (SQLException ex) {
            System.err.printf("Excepcion :'%s'%n", ex.getMessage());
        }
        try (Connection cnx = ds.getConnection();
                PreparedStatement stm = cnx.prepareCall(CMD_INSERTAR2);) {
            stm.clearParameters();
            stm.setString(1, x.getUsuario().getNombreUsuario());
            stm.setString(2, x.getUsuario().getContraseña());
            stm.setBoolean(3, x.getUsuario().isTipo());
            stm.setInt(4, x.getCedula());
            stm.execute();
        } catch (SQLException ex) {
            System.err.printf("Excepcion :'%s'%n", ex.getMessage());
        }
    }

    private static ServicioClientes instancia;
    private Properties configuracion;
    private DataSource ds;
    private String usuario;
    private String contraseña;

    private static final String CMD_INSERTAR1 = "INSERT INTO persona (pk_cedula,nombre,apellidos,direccion, telefono) VALUES(?,?,?,?,?)";
    private static final String CMD_INSERTAR2 = "INSERT INTO usuario (pk_usuario, contrasena, tipo, persona_fk) VALUES(?,?,?,?)";
    private static final String CMD_EXISTE_USUARIO = "select u.pk_usuario from usuario u where u.pk_usuario = ?";

}
