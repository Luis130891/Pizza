/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Usuario;
import modeoo.dao.ServicioClientes;
import org.json.JSONObject;

/**
 *      EIF209 - Programación 4 – Proyecto #2 
 *      Junio 2020 
 *          Autores: 
 *          116720428Kenneth Ariel Chaves Herrera
 *          702000163 Luis Venegas Ulloa
 */
@MultipartConfig
@WebServlet(name = "servletClienteNuevo", urlPatterns = {"/servletClienteNuevo"})
public class servletClienteNuevo extends HttpServlet {

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

            String us = request.getParameter("usuarioNuevo");
            String co = request.getParameter("contrasenaNueva");
            String ti = request.getParameter("tipoNuevo");
            String ce = request.getParameter("cedulaNueva");
            String no = request.getParameter("nuevoNombre");
            String ap = request.getParameter("apellidosNuevos");
            String di = request.getParameter("direccionNueva");
            String te = request.getParameter("telefonoNuevo");
            boolean tip;
            if (Integer.parseInt(ti) == 1) {
                tip = true;
            } else {
                tip = false;
            }
            
            System.out.println(ti);
            int ced = Integer.parseInt(ce);
            int tel = Integer.parseInt(te);
            ServicioClientes.getInstancia().insertarCliente(new Cliente(ced, no, ap, di, tel, new Usuario(us, co, tip)));
      
            aux.put("Cliente", "bien");

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
