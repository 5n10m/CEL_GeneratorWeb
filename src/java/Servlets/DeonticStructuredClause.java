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
import com.marcobrador.tfm.cel.db.model.Item.*;
import com.marcobrador.tfm.cel.db.model.DeonticStructuredClause.*;
import com.marcobrador.tfm.cel.db.model.actions.*;
import java.io.Serializable;
import javax.servlet.http.HttpSession;

/**
 *
 * @author david
 */
@WebServlet(name = "DeonticStructuredClause", urlPatterns = {"/DeonticStructuredClause"})
public class DeonticStructuredClause extends HttpServlet implements Serializable {

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

            OperativePart.Builder opb = new OperativePart.Builder();
            Action a;
            switch (request.getParameter("ActionType")) {
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
                default:
                    a = new View(); // para evitar el warning/error
                    break;
            }
            Act act = new Act(CleanInvalid(request.getParameter("ActionId")), a);
            Subject s = new Subject(request.getParameter("PartyRef"));
            String op_id = CleanInvalid(request.getParameter("OperativePartId"));

            switch (request.getParameter("ObjectType")) {
                case "Event":
                    switch (request.getParameter("OperativePartType")) {
                        case "Obligation":
                            Obligation.Builder Odscb = new Obligation.Builder(op_id, s, act);
                            Odscb.setObject(new CelObject(new Event.Builder().setName(request.getParameter("EventType")).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("EventIdentifier")))).build()));
                            if (CleanInvalid(request.getParameter("ResultantObjectIdentifier")).length() > 0) {
                                Odscb.setResultantObject(new Item.Builder().setName(CleanInvalid(request.getParameter("ResultantObject"))).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ResultantObjectIdentifier")))).build());
                            }
                            opb.addClause(Odscb.build());
                            break;
                        case "Permission":
                            Permission.Builder Pedscb = new Permission.Builder(op_id, s, act);
                            Pedscb.setObject(new CelObject(new Event.Builder().setName(request.getParameter("EventType")).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("EventIdentifier")))).build()));
                            if (CleanInvalid(request.getParameter("ResultantObjectIdentifier")).length() > 0) {
                                Pedscb.setResultantObject(new Item.Builder().setName(CleanInvalid(request.getParameter("ResultantObject"))).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ResultantObjectIdentifier")))).build());
                            }
                            opb.addClause(Pedscb.build());
                            break;
                        case "Prohibition":
                            Prohibition.Builder Prodscb = new Prohibition.Builder(op_id, s, act);
                            Prodscb.setObject(new CelObject(new Event.Builder().setName(request.getParameter("EventType")).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("EventIdentifier")))).build()));
                            if (CleanInvalid(request.getParameter("ResultantObjectIdentifier")).length() > 0) {
                                Prodscb.setResultantObject(new Item.Builder().setName(CleanInvalid(request.getParameter("ResultantObject"))).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ResultantObjectIdentifier")))).build());
                            }
                            opb.addClause(Prodscb.build());
                            break;
                    }
                    break;
                case "Item":
                    Item.Region r = new Region();
                    if (CleanInvalid(request.getParameter("RegionId")).length() > 0) {
                        r.setIdentifier(CleanInvalid(request.getParameter("RegionId")));
                    }
                    if (CleanInvalid(request.getParameter("RegionClass")).length() > 0) {
                        r.setRegionClass(CleanInvalid(request.getParameter("RegionClass")));
                    }
                    if (CleanInvalid(request.getParameter("RegionStart")).length() > 0) {
                        r.setStart(Integer.parseInt(CleanInvalid(request.getParameter("RegionStart"))));
                    }
                    if (CleanInvalid(request.getParameter("RegionEnd")).length() > 0) {
                        r.setEnd(Integer.parseInt(CleanInvalid(request.getParameter("RegionEnd"))));
                    }
                    switch (request.getParameter("OperativePartType")) {
                        case "Obligation":
                            Obligation.Builder Odscb = new Obligation.Builder(op_id, s, act);
                            Odscb.setObject(new CelObject(new Item.Builder().setName(request.getParameter("ItemType")).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ItemIdentifier")))).setRegion(r).build()));
                            if (CleanInvalid(request.getParameter("ResultantObjectIdentifier")).length() > 0) {
                                Odscb.setResultantObject(new Item.Builder().setName(CleanInvalid(request.getParameter("ResultantObject"))).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ResultantObjectIdentifier")))).build());
                            }
                            opb.addClause(Odscb.build());
                            break;
                        case "Permission":
                            Permission.Builder Pedscb = new Permission.Builder(op_id, s, act);
                            Pedscb.setObject(new CelObject(new Item.Builder().setName(request.getParameter("ItemType")).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ItemIdentifier")))).setRegion(r).build()));
                            if (CleanInvalid(request.getParameter("ResultantObjectIdentifier")).length() > 0) {
                                Pedscb.setResultantObject(new Item.Builder().setName(CleanInvalid(request.getParameter("ResultantObject"))).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ResultantObjectIdentifier")))).build());
                            }
                            opb.addClause(Pedscb.build());
                            break;
                        case "Prohibition":
                            Prohibition.Builder Prodscb = new Prohibition.Builder(op_id, s, act);
                            Prodscb.setObject(new CelObject(new Item.Builder().setName(request.getParameter("ItemType")).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ItemIdentifier")))).setRegion(r).build()));
                            if (CleanInvalid(request.getParameter("ResultantObjectIdentifier")).length() > 0) {
                                Prodscb.setResultantObject(new Item.Builder().setName(CleanInvalid(request.getParameter("ResultantObject"))).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ResultantObjectIdentifier")))).build());
                            }
                            opb.addClause(Prodscb.build());
                            break;
                    }
                    break;
                case "Subject":
                    switch (request.getParameter("OperativePartType")) {
                        case "Obligation":
                            Obligation.Builder Odscb = new Obligation.Builder(op_id, s, act);
                            Odscb.setObject(new CelObject(new Issuer(request.getParameter("SubjectIdentifier"))));
                            if (CleanInvalid(request.getParameter("ResultantObjectIdentifier")).length() > 0) {
                                Odscb.setResultantObject(new Item.Builder().setName(CleanInvalid(request.getParameter("ResultantObject"))).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ResultantObjectIdentifier")))).build());
                            }
                            opb.addClause(Odscb.build());
                            break;
                        case "Permission":
                            Permission.Builder Pedscb = new Permission.Builder(op_id, s, act);
                            Pedscb.setObject(new CelObject(new Issuer(request.getParameter("SubjectIdentifier"))));
                            if (CleanInvalid(request.getParameter("ResultantObjectIdentifier")).length() > 0) {
                                Pedscb.setResultantObject(new Item.Builder().setName(CleanInvalid(request.getParameter("ResultantObject"))).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ResultantObjectIdentifier")))).build());
                            }
                            opb.addClause(Pedscb.build());
                            break;
                        case "Prohibition":
                            Prohibition.Builder Prodscb = new Prohibition.Builder(op_id, s, act);
                            Prodscb.setObject(new CelObject(new Issuer(request.getParameter("SubjectIdentifier"))));
                            if (CleanInvalid(request.getParameter("ResultantObjectIdentifier")).length() > 0) {
                                Prodscb.setResultantObject(new Item.Builder().setName(CleanInvalid(request.getParameter("ResultantObject"))).setRelatedIdentifier(new RelatedIdentifier(CleanInvalid(request.getParameter("ResultantObjectIdentifier")))).build());
                            }
                            opb.addClause(Prodscb.build());
                            break;
                    }
                    break;
                case "Service":
                    //AQUI TAMBIEN FALTA AÑADIR ALGO
                    break;
            }
///////////////////////////////////////////////////////
            if (CleanInvalid(request.getParameter("TextualPart")).length() > 0) {
                opb.addStatement(new Statement("TP"+CleanInvalid(request.getParameter("OperativePartId")), CleanInvalid(request.getParameter("TextualPart"))));
            }

            Body b = new Body(opb.build());

            HttpSession session = request.getSession(true);
            Contract.Builder cb = (Contract.Builder) session.getAttribute("Contract");
            cb.addBody(b);
            session.setAttribute("Contract", cb);

            switch (request.getParameter("NextAction")) {
                case "AddAnother":
                    RequestDispatcher rd = request.getRequestDispatcher("operativePart.jsp");
                    rd.forward(request, response);
                    break;
                case "Finish":
                    //Contract c = cb.build();
                    //out.print("<p>" + ConditionalCreator.WriteContract(c) + "</p>");
                    //RequestDispatcher rd2 = request.getRequestDispatcher("PrintContract.jsp");
                    Contract c = cb.build();
                    session.setAttribute("Contract", c);
                    RequestDispatcher rd2 = request.getRequestDispatcher("prePostConditions.jsp");                    
                    rd2.forward(request, response);
                    break;
            }
        } catch (Throwable e) {
            e.getMessage();
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
