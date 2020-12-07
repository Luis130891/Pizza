/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeoo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import modelo.Complemento;
import modelo.Factura;
import modelo.Pizza;

/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 *          Autores: 
 *          116720428Kenneth Ariel Chaves Herrera
 *          702000163 Luis Venegas Ulloa
 */
public class ServicioPizzaAdministrador implements Serializable {

    private static ServicioPizzaAdministrador instancia;
    private Properties configuracion;
    private DataSource ds;
    private String usuario;
    private String contraseña;

    public ServicioPizzaAdministrador() {
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

    public static ServicioPizzaAdministrador getInstancia() {
        if (instancia == null) {
            instancia = new ServicioPizzaAdministrador();
        }
        return instancia;
    }

    public void insertarPizza(Pizza x) {
        int aux = 0;
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareStatement(CMD_INSERTAR_ID_PIZZA);) {
            stm.clearParameters();
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    aux = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_INSERTAR_PIZZA);) {
            stm.clearParameters();
            stm.setInt(1, aux + 3001);
            stm.setString(2, x.getNombre());
            stm.setDouble(3, x.getPrecio());
            stm.execute();
        } catch (SQLException ex) {
            System.err.printf("Excepcion :'%s'%n", ex.getMessage());
        }
    }

    public void insertarComplemento(Complemento x) {
        int aux = 0;
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareStatement(CMD_INSERTAR_ID_COMPLEMENTO);) {
            stm.clearParameters();
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    aux = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_INSERTAR_COMPLEMENTO);) {
            stm.clearParameters();
            stm.setInt(1, aux + 2001);
            stm.setString(2, x.getNombre());
            stm.setDouble(3, x.getPrecio());
            stm.execute();
        } catch (SQLException ex) {
            System.err.printf("Excepcion :'%s'%n", ex.getMessage());
        }
    }

    public void eliminarPizza(int x) {
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_ELIMINAR_PIZZA);) {
            stm.clearParameters();
            stm.setInt(1, x);
            stm.execute();
        } catch (SQLException ex) {
            System.err.printf("Excepcion :'%s'%n", ex.getMessage());
        }
    }

    public void eliminarComplemento(int x) {
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_ELIMINAR_COMPLEMENTO);) {
            stm.clearParameters();
            stm.setInt(1, x);
            stm.execute();
        } catch (SQLException ex) {
            System.err.printf("Excepcion :'%s'%n", ex.getMessage());
        }
    }

    public void actualizarPizza(int i, double p) {
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_ACTUALIZAR_PIZZA);) {
            stm.clearParameters();
            stm.setDouble(1, p);
            stm.setInt(2, i);
            stm.execute();
        } catch (SQLException ex) {
            System.err.printf("Excepcion :'%s'%n", ex.getMessage());
        }
    }

    public void actualizarComplemento(int i, String n, double p) {
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_ACTUALIZAR_COMPLEMENTO);) {
            stm.clearParameters();
            stm.setString(1, n);
            stm.setDouble(2, p);
            stm.setInt(3, i);
            stm.execute();
        } catch (SQLException ex) {
            System.err.printf("Excepcion :'%s'%n", ex.getMessage());
        }
    }

    public String actualizarFactura(int id, String estado) {
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_ACTUALIZAR_FACTURA_ESTADO);) {
            stm.clearParameters();
            stm.setString(1, estado);
            stm.setInt(2, id);
            stm.execute();
        } catch (SQLException ex) {
               return "facturas no actualizadas";
        }
        return "facturas actualizadas";
    }

    public ArrayList<Factura> listarFacturaFecha(String x ,String y) {
        ArrayList<Factura> obj = new ArrayList();
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_FACTURA_FECHA);) {
            stm.clearParameters();
            stm.setString(1, x);
            stm.setString(2, y);
            stm.execute();
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    obj.add(new Factura(rs.getInt(1), rs.getDouble(2), rs.getBoolean(3), rs.getString(4), rs.getDate(5), null, null, null));
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return obj;
    }

    public ArrayList<Factura> listarFacturaEstado(String x) {
        ArrayList<Factura> obj = new ArrayList();
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_FACTURA_ESTADO);) {
            stm.clearParameters();
            stm.setString(1, x);
            stm.execute();
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    obj.add(new Factura(rs.getInt(1), rs.getDouble(2), rs.getBoolean(3), rs.getString(4), rs.getDate(5), null, null, null));
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return obj;
    }

    public String getGson(ArrayList<Factura> x) {
        Gson y = new GsonBuilder().setPrettyPrinting().create();
        return y.toJson(x);
    }

    private static final String CMD_INSERTAR_ID_PIZZA = "select count(*) from pizza";
    private static final String CMD_INSERTAR_PIZZA = "insert into pizza(id, nombre, precio) values (?,?,?)";
    private static final String CMD_INSERTAR_ID_COMPLEMENTO = "select count(*) from complemento";
    private static final String CMD_INSERTAR_COMPLEMENTO = "insert into complemento(id, nombre, precio) values (?,?,?)";
    private static final String CMD_ELIMINAR_PIZZA = "delete from pizza where id = ?";
    private static final String CMD_ELIMINAR_COMPLEMENTO = "delete from complemento where id = ?";
    private static final String CMD_ACTUALIZAR_PIZZA = "update pizza set precio = ? where id = ?";
    private static final String CMD_ACTUALIZAR_COMPLEMENTO = "update complemento set nombre = ?, precio = ? where id = ?";
    private static final String CMD_LISTAR_FACTURA_FECHA = "select * from factura  where tiempo >= ? and tiempo <= ?";
    private static final String CMD_LISTAR_FACTURA_ESTADO = "select * from factura  where estado = ?";
    private static final String CMD_ACTUALIZAR_FACTURA_ESTADO = "update factura set estado = ? where id = ?";
}
