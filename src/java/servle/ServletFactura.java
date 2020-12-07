/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Complemento;
import modelo.Factura;
import modelo.Pizza;
import modeoo.dao.ServicioFactura;
import org.apache.jasper.tagplugins.jstl.ForEach;
import org.json.JSONObject;

/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 *          Autores: 
 *          116720428Kenneth Ariel Chaves Herrera
 *          702000163 Luis Venegas Ulloa
 */
@MultipartConfig
@WebServlet(name = "ServletFactura", urlPatterns = {"/ServletFactura"})
public class ServletFactura extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject r = new JSONObject();
            Factura factura = null;
            ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
            ArrayList<Complemento> complementos = new ArrayList<Complemento>();
            Enumeration<String> p = request.getParameterNames();
            System.out.println(p);
            while (p.hasMoreElements()) {

                String bandeP = p.nextElement();
                String bandeC = p.nextElement();
                String[] banP = request.getParameterValues(bandeP);
                String[] banC = request.getParameterValues(bandeC);

                boolean banderaPizza = Boolean.parseBoolean(banP[0]);
                boolean banderaComplemento = Boolean.parseBoolean(banC[0]);

                String usuario = p.nextElement();
                String metodo = p.nextElement();

                String[] nombreUsuario = request.getParameterValues(usuario);
                String[] metodoPago = request.getParameterValues(metodo);

                if (banderaPizza) {
                    String pizza_codigo = p.nextElement();
                    String tamaño = p.nextElement();
                    String precio = p.nextElement();
                    String[] tama = request.getParameterValues(tamaño);
                    String[] codi = request.getParameterValues(pizza_codigo);
                    String[] pre = request.getParameterValues(precio);
                    for (int i = 0; i < codi.length; i++) {
                        boolean band = true;
                        for (Pizza pi : pizzas) {
                            if ((pi.getId() == Integer.parseInt(codi[i]) && (pi.getPrecio() == Double.parseDouble(pre[i])))) {
                                pi.setCantidad(pi.getCantidad() + 1);
                                band = false;
                            }
                        }
                        if (band) {
                            pizzas.add(new Pizza(Integer.parseInt(codi[i]), tama[i], Double.parseDouble(pre[i])));
                        }
                    }

                }
                
                System.out.println(metodoPago[0]);
                if (banderaComplemento) {
                    String cId = p.nextElement();
                    String cNom = p.nextElement();
                    String cPre = p.nextElement();

                    String[] comp_id = request.getParameterValues(cId);
                    String[] comp_nombre = request.getParameterValues(cNom);
                    String[] comp_precio = request.getParameterValues(cPre);

                    for (int i = 0; i < comp_id.length; i++) {
                        boolean band = true;
                        for (Complemento com : complementos) {
                            if (com.getId() == Integer.parseInt(comp_id[i])) {
                                com.setCantidad(com.getCantidad() + 1);
                                band = false;
                            }
                        }
                        if (band) {
                            complementos.add(new Complemento(comp_nombre[i], Double.parseDouble(comp_precio[i]), Integer.parseInt(comp_id[i])));
                        }
                    }
                }
                String total = p.nextElement();
                String[] tot = request.getParameterValues(total);

                
                factura = new Factura(0, Double.parseDouble(tot[0]), Boolean.parseBoolean(metodoPago[0]), "EN PREPARACIÓN", new Date(), nombreUsuario[0], pizzas, complementos);
            }
            r.put("respuesta", ServicioFactura.obtenerInstancia().insertar(factura));

            System.out.println("servle.ServletFactura.processRequest()======================================================");
            out.print(r.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
