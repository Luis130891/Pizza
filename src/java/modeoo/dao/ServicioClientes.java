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
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import modelo.Cliente;
import modelo.Comentario;
import modelo.Usuario;



/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 

 *          702000163 Luis Venegas Ulloa
 */
public class ServicioClientes implements Serializable {

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

    public Cliente existeUsuario(String usu, String con) {
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareStatement(CMD_EXISTE_USUARIO);) {
            stm.clearParameters();
            stm.setString(1, usu);
            stm.setString(2, con);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), new Usuario(rs.getString(6),
                            rs.getString(7), rs.getBoolean(8)));
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return null;
    }

    public void insertarComentario(Comentario c) {
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_INSERTAR_COMENTARIO);) {
            stm.clearParameters();
            stm.setString(1, c.getUsuario());
            stm.setString(2, c.getComentario());
            stm.execute();
        } catch (SQLException ex) {
            System.err.printf("Excepcion :'%s'%n", ex.getMessage());
        }
    }

    public ArrayList<Comentario> listarComentarios() {
        ArrayList<Comentario> obj = new ArrayList<Comentario>();
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_COMENTARIOS);) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    obj.add(new Comentario(rs.getString(1), rs.getString(2)));
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return obj;
    }
    
    
    public String getGsonComentarios(ArrayList<Comentario> x){
        Gson g =  new GsonBuilder().setPrettyPrinting().create();
          return g.toJson(x);
    }

    public void actualizarCliente(String con, String nom, String ape, String dir, int tel, String usu) {
        System.out.println(con);
        try (Connection cnx = ds.getConnection(usuario, contraseña);
                PreparedStatement stm = cnx.prepareCall(CMD_ACTUALIZAR_CLIENTE);) {
            stm.clearParameters();
            stm.setString(1, con);
            stm.setString(2, nom);
            stm.setString(3, ape);
            stm.setString(4, dir);
            stm.setInt(5, tel);
            stm.setString(6, usu);
            stm.execute();
        } catch (SQLException ex) {
            System.err.printf("Excepcion :'%s'%n", ex.getMessage());
        }
    }

    public String getGson(Cliente x) {
        Gson y = new GsonBuilder().setPrettyPrinting().create();
        return y.toJson(x);
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

    public String rastrearFactura(int n) {
        try (Connection cnx = ds.getConnection();
                PreparedStatement stm = cnx.prepareStatement(CMD_RASTREAR_FACTURA);) {
            stm.clearParameters();
            stm.setInt(1, n);
            stm.execute();
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return null;
    }

    private static ServicioClientes instancia;
    private Properties configuracion;
    private DataSource ds;
    private String usuario;
    private String contraseña;

    private static final String CMD_INSERTAR1 = "INSERT INTO persona (pk_cedula,nombre,apellidos,direccion, telefono) VALUES(?,?,?,?,?)";
    private static final String CMD_INSERTAR2 = "INSERT INTO usuario (pk_usuario, contrasena, tipo, persona_fk) VALUES(?,?,?,?)";
    private static final String CMD_EXISTE_USUARIO = "select p.pk_cedula, p.nombre, p.apellidos, p.direccion, p.telefono, u.pk_usuario, u.contrasena,"
            + " u.tipo from usuario u, persona p where u.pk_usuario = ? and u.contrasena = ? and u.persona_fk = p.pk_cedula";

    private static final String CMD_INSERTAR_COMENTARIO
            = "insert into comentario(pk_usuario_fk, comentario) values (?,?)";

    private static final String CMD_LISTAR_COMENTARIOS
            = "select * from comentario;";

    private static final String CMD_ACTUALIZAR_CLIENTE = "update persona p, usuario u set u.contrasena = ?, p.nombre = ?, p.apellidos = ?, "
            + "p.direccion = ?, p.telefono = ? where u.pk_usuario = ? and u.persona_fk = p.pk_cedula";
    private static final String CMD_RASTREAR_FACTURA
            = "select estado from factura where id = ?";
}
