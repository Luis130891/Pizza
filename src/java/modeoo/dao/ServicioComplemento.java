/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeoo.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import javax.xml.bind.JAXBException;
import modelo.Complemento;

/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 *          Autores: 
 *          116720428Kenneth Ariel Chaves Herrera
 *          702000163 Luis Venegas Ulloa
 */
public class ServicioComplemento {

    public ServicioComplemento() {
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

    public static ServicioComplemento getInstancia() {
        if (instancia == null) {
            instancia = new ServicioComplemento();
        }
        return instancia;
    }

    public ArrayList<Complemento> complementos() {
        ArrayList<Complemento> complementos = new ArrayList<Complemento>();
        try (Connection cnx = ds.getConnection(usuario, contraseña);
            PreparedStatement stm = cnx.prepareCall(CMD_LISTAR);) {
            stm.clearParameters();
            try (ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    complementos.add(new Complemento(rs.getNString(2), rs.getDouble(3), rs.getInt(1)));
                }
            }
        } catch (SQLException e) {
            System.out.printf("ERROR-->%s", e.getMessage());
        }

        return complementos;
    }

    public String datosJSON(ArrayList<Complemento> p) throws JAXBException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(p);
    }

    private static final String CMD_LISTAR = "select * from complemento";

    private DataSource ds;
    private String contraseña;
    private String usuario;
    private Properties configuracion;
    private static ServicioComplemento instancia;
}
