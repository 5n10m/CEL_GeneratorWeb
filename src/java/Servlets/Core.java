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
            HttpSession session = request.getSession(true);
            session.invalidate();
            session = request.getSession();
            
            Contract.Builder cb = new Contract.Builder(CleanInvalid(request.getParameter("contractId").replace(" ", "_")));
            if (CleanInvalid(request.getParameter("governingLaw")).length() > 0) {
                cb.setGoverningLaw(CleanInvalid(request.getParameter("governingLaw")));
            }
            if (CleanInvalid(request.getParameter("court")).length() > 0) {
                cb.setCourt(CleanInvalid(request.getParameter("court")));
            }
            if (CleanInvalid(request.getParameter("textVersion")).length() > 0) {
                cb.setTextVersion(CleanInvalid(request.getParameter("textVersion")));
            }
            session.setAttribute("Contract", cb);

            response.sendRedirect("party.html");

        }
    }
    
    protected String CleanInvalid(String s){
        return s.replace("<","").replace(">","").replace("&","").replace("'","");
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
