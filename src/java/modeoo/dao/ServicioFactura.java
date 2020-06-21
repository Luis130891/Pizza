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
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import modelo.Factura;

/**
 *
 * @author Luis Venegas Ulloa
 */
public class ServicioFactura {

    public ServicioFactura() {

        configuracion = new Properties();
        try {
            configuracion.load(getClass().getResourceAsStream("configuration.properties"));
            String cnxPool = configuracion.getProperty("conexion");

            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(cnxPool);
            configurar();
        } catch (IOException
                | NamingException ex) {
            System.out.printf("ERROR-->%S", ex.getMessage());
        }
    }

    private void configurar() {
        if (configuracion != null) {
            usuario = configuracion.getProperty("usuario");
            contraseña = configuracion.getProperty("contraseña");
        }
    }

    private boolean insertar(Factura factura) {
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CDM_INSERTAR);) {
            stm.clearParameters();
            stm.setInt(1, factura.getId());
            stm.setDouble(2, factura.getId());
            stm.setBoolean(3, factura.isMetodo_pago());
            stm.setString(4, factura.getEstado());
            stm.setTimestamp(5, new Timestamp(factura.getFecha().getTime()));
            stm.setString(6, factura.getUsuario().getNombreUsuario());
            stm.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public Factura buscarFactura(int numFactura) {
        Factura f = null;
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_BUSCAR);) {
            stm.clearParameters();
            stm.setInt(1, numFactura);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    f = new Factura(rs.getInt(1), rs.getDouble(2), rs.getBoolean(3), rs.getString(4), rs.getDate(5), null, null, null);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicioFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    
    
    
    public static ServicioFactura obtenerInstancia() {
        if (instancia == null) {
            instancia = new ServicioFactura();
        }
        return instancia;
    }
    
    
    private final String CMD_BUSCAR
            = "select *from factura where id = ?";
    private final String CDM_INSERTAR
            = "insert into Factura(id,precioTotal,metodoPago,estado,tiempo,pk_usuario_fk) values(?,?,?,?,?,?)";

    private DataSource ds;
    private String contraseña;
    private String usuario;
    private Properties configuracion;
    private static ServicioFactura instancia;

}
