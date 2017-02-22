package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.marcobrador.tfm.cel.db.model.Body;
import com.marcobrador.tfm.cel.db.model.Contract;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author david
 */
@WebServlet(urlPatterns = {"/Core"})
public class Core extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        
        try (PrintWriter out = response.getWriter()) {
            //https://www.youtube.com/watch?v=g1Hzy3nEH18
            HttpSession session= request.getSession();
            session.invalidate();
            session = request.getSession();
            //Body b = new Body();
            /*Contract c =new Contract();
            c.setContractId(request.getParameter("contractId"));
            c.setGoverningLaw(request.getParameter("governingLaw"));
            c.setCourt(request.getParameter("court"));
            c.setTextVersion(request.getParameter("textVersion"));
            session.setAttribute("ontract", c);*/
            session.setAttribute("contractId", (String) request.getParameter("contractId"));
            session.setAttribute("governingLaw", (String) request.getParameter("governingLaw"));
            session.setAttribute("court", (String) request.getParameter("court"));
            session.setAttribute("textVersion", (String) request.getParameter("textVersion"));
            
            response.sendRedirect("party.html");
            //RequestDispatcher rd = request.getRequestDispatcher("party.html");
            //rd.forward(request, response);
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