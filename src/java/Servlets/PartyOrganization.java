/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.marcobrador.tfm.cel.db.model.Contract;
import com.marcobrador.tfm.cel.db.model.Organization;
import com.marcobrador.tfm.cel.db.model.Party;
import com.marcobrador.tfm.cel.db.model.Person;
import com.marcobrador.tfm.cel.db.model.PartyBasicGroup;
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
@WebServlet(name = "PartyOrganization", urlPatterns = {"/PartyOrganization"})
public class PartyOrganization extends HttpServlet {

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

            PartyBasicGroup pbg = new Person.Builder(CleanInvalid(request.getParameter("name")))
                    .setIdentifier(CleanInvalid(request.getParameter("identifier").replace(" ", "_")))
                    .setDescription(CleanInvalid(request.getParameter("description")))
                    .setDetails(CleanInvalid(request.getParameter("details")))
                    .build();
            PartyBasicGroup p = new Organization.Builder(request.getParameter("oname"))
                    .setSignatory(pbg)
                    .setDescription(CleanInvalid(request.getParameter("odescription")))
                    .setIdentifier("oidentifier")
                    .setDetails("odetails")
                    .build();
            /*PartyBasicGroup.Builder ob = new Organization.Builder(request.getParameter("oname"));
                    //ob.setSignatory(pbg);
                    ob.setDescription(CleanInvalid(request.getParameter("odescription")));
                    ob.setIdentifier("oidentifier");
                    ob.setDetails("odetails");
            PartyBasicGroup p = ob.build();*/
            
            Party part = new Party.Builder(CleanInvalid(request.getParameter("oidentifier").replace(" ", "_")), p).setRol(request.getParameter("Rol")).setAddress(CleanInvalid(request.getParameter("oaddress"))).build();

            HttpSession session = request.getSession(true);
            Contract.Builder cb = (Contract.Builder) session.getAttribute("Contract");
            cb.addParty(part);
            session.setAttribute("Contract", cb);

            switch (request.getParameter("NextAction")) {
                case "AddAnother":
                    RequestDispatcher rd = request.getRequestDispatcher("party.html");
                    rd.forward(request, response);
                    break;
                case "Finish":
                    RequestDispatcher rd2 = request.getRequestDispatcher("operativePart.jsp");
                    rd2.forward(request, response);
                    break;
            }
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    protected String CleanInvalid(String s) {
        return s.replace("<", "").replace(">", "").replace("&", "").replace("'", "");
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
