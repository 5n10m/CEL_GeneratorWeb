/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.marcobrador.tfm.cel.db.model.*;
import com.marcobrador.tfm.cel.db.model.actions.*;
import java.io.Serializable;

/**
 *
 * @author david
 */
@WebServlet(name = "DeonticStructuredClause", urlPatterns = {"/DeonticStructuredClause"})
public class DeonticStructuredClause extends HttpServlet implements Serializable{

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
            /* TODO output your page here. You may use following sample code. */
            /*PreCondition pc = new PreCondition(0, request.getParameter("PreConditionId"), response, clause);
            request.getParameter("PreConditionId")
                    request.getParameter("PreConditionActionStarted")
                    request.getParameter("PreConditionActionDone")
                    request.getParameter("PreConditionDelay")
                    request.getParameter("PreConditionValidity")
                            */
            Action a;
            switch (request.getParameter("ActionType")){
                case "GenethicAnalysis":
                    a = new GenethicAnalysis();
                    break;
                case "Donation":
                    a = new Donation();
                    break;
                case "View":
                    a = new View();
                    break;
                case "ResearchProject":
                    a = new ResearchProject();
                    break;
                case "PaternityTest":
                    a = new PaternityTest();
                    break;
                case "Forensic":
                    a = new Forensic();
                    break;
            }
            
            switch(request.getParameter("ObjectType")){
                case "Event":
                    Event e = new Event.Builder().setName(request.getParameter("EventType")).setRelatedIdentifier(new RelatedIdentifier(request.getParameter("EventIdentifier"))).build();
                    
                    break;
                case "Item":
                    Item i = new Item.Builder().setName(request.getParameter("ItemType")).setRelatedIdentifier(new RelatedIdentifier(request.getParameter("ItemIdentifier"))).build();
                    
                    break;
                case "Subject":
                    String IdRef = request.getParameter("SubjectIdentifier");
                    break;
                case "Service":
                    
                    break;
                
            }            
            
            if (request.getParameter("nextaction") != "") {  // PORQUE COJONES NO FUNCIONA?
                RequestDispatcher rd = request.getRequestDispatcher("ChooseOperativePartType.html");
                rd.forward(request, response);
            }
            else{
                RequestDispatcher rd = request.getRequestDispatcher("ChooseOperativePartType.html");
                rd.forward(request, response);
            }
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
