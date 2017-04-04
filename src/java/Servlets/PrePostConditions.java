/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.marcobrador.tfm.cel.db.model.Body;
import com.marcobrador.tfm.cel.db.model.Contract;
import com.marcobrador.tfm.cel.db.model.DeonticStructuredClause;
import com.marcobrador.tfm.cel.db.model.PostCondition;
import com.marcobrador.tfm.cel.db.model.PreCondition;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "PrePostConditions", urlPatterns = {"/PrePostConditions"})
public class PrePostConditions extends HttpServlet {

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
            Contract c = (Contract) session.getAttribute("Contract");

            if (request.getParameter("TargetRef").length() > 0) {
                if (request.getParameter("PreConditionPartyRef").length() > 0) {
                    PreCondition.ActionStatus as = PreCondition.ActionStatus.ActionStarted;
                    switch (request.getParameter("PreConditionActionStatus")) {
                        case "ActionStarted":
                            as = PreCondition.ActionStatus.ActionStarted;
                            break;
                        case "ActionDone":
                            as = PreCondition.ActionStatus.ActionDone;
                            break;
                    }
                    PreCondition preC = new PreCondition(request.getParameter("PreConditionPartyRef"), as, request.getParameter("PreConditionDelay"), request.getParameter("PreConditionValidity"));
                    //COM colocar el precondition a on toca del target =)
                    for (Body b : c.getBody()) {
                        for (DeonticStructuredClause dsc : b.getOperativePart().getClauses()) {
                            if (dsc.isSameId(request.getParameter("TargetRef")))  {
                                Set<PreCondition> sPre = dsc.getPreConditions();
                                if (sPre == null || sPre.isEmpty()) {
                                    sPre = new HashSet<PreCondition>();
                                    sPre.add(preC);
                                } else{
                                    sPre.add(preC);
                                }
                                dsc.setPreConditions(sPre);
                            }
                        }
                    }
                }
                if (request.getParameter("PostConditionPartyRef").length() > 0) {
                    PostCondition.ActionStatus ast = PostCondition.ActionStatus.ActionStarted;
                    switch (request.getParameter("PreConditionActionStatus")) {
                        case "ActionStarted":
                            ast = PostCondition.ActionStatus.ActionStarted;
                            break;
                        case "ActionDone":
                            ast = PostCondition.ActionStatus.ActionDone;
                            break;
                    }
                    PostCondition postC = new PostCondition(request.getParameter("PreConditionPartyRef"), ast, request.getParameter("PreConditionDelay"), request.getParameter("PreConditionValidity"));
                    for (Body b : c.getBody()) {
                        for (DeonticStructuredClause dsc : b.getOperativePart().getClauses()) {
                            if (dsc.isSameId(request.getParameter("TargetRef"))) {
                                Set<PostCondition> sPost = dsc.getPostConditions();
                                if (sPost == null || sPost.isEmpty()) {
                                    sPost = new HashSet<PostCondition>();
                                    sPost.add(postC);
                                } else{
                                    sPost.add(postC);
                                }
                                dsc.setPostConditions(sPost);
                                                        }
                        }
                    }
                }
            }
            session.setAttribute("Contract", c);

            /* COMO DESCARGAR EL ARCHIVO */
            //HttpSession session = request.getSession(true);
            //Contract.Builder cb = (Contract.Builder) session.getAttribute("Contract");
            //Contract c = cb.build();
            //response.sendRedirect(ConditionalCreator.WriteContract(c));
            ConditionalCreator.WriteContract(c);
            //response.setContentType("text/xml");
            //response.setHeader("Content-Disposition", "attachment; filename=\"Contract.xml\"");
            //OutputStream outputStream = response.getOutputStream();
            /*String outputResult = ConditionalCreator.WriteContract(c);
            outputStream.write(outputResult.getBytes());
            outputStream.flush();
            outputStream.close();*/

 /*OutputStream outputStream = response.getOutputStream();
            FileInputStream in = new FileInputStream(ConditionalCreator.WriteContract(c));
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            in.close();
            out.flush();*/
        } catch (Exception ex) {
            Logger.getLogger(PrePostConditions.class.getName()).log(Level.SEVERE, null, ex);
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
