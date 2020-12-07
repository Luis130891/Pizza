/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Factura;
import modeoo.dao.ServicioPizzaAdministrador;
import org.json.JSONObject;

/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 *       
 *          702000163 Luis Venegas Ulloa
 */
@MultipartConfig
@WebServlet(name = "servletVentas", urlPatterns = {"/servletVentas"})
public class servletVentas extends HttpServlet {

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
        response.setContentType("aplication/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject aux = new JSONObject();

            String fechaI = request.getParameter("fechai");
            String fechaF = request.getParameter("fechaf");
//
//            Date fi = new Date(Long.parseLong(fechaI));
//            Date ff = new Date(Long.parseLong(fechaF));
            ArrayList<Factura> obj = new ArrayList();
            obj = ServicioPizzaAdministrador.getInstancia().listarFacturaFecha(fechaI, fechaF);

            aux.put("Factura", ServicioPizzaAdministrador.getInstancia().getGson(obj));
            System.out.print(obj.toString());

            out.print(aux.toString(0));
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
