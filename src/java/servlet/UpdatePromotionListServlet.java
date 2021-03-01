/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.PromotionListService;

/**
 *
 * @author TiTi
 */
@WebServlet(name = "UpdatePromotionListServlet", urlPatterns = {"/UpdatePromotionListServlet"})
public class UpdatePromotionListServlet extends HttpServlet {

    private final String promotionListPage = "promotionList.jsp";
    private final String errorPage = "error.jsp";

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

        String url = promotionListPage;
        try {
            String idStr = request.getParameter("id");
            String rankStr = request.getParameter("rank");
            String btAction = request.getParameter("btAction");

            int id = Integer.parseInt(idStr);
            int rank = Integer.parseInt(rankStr);

            PromotionListService promotionListService = new PromotionListService();
            if ("Update".equals(btAction)) {
                promotionListService.updatePromotion(id, rank);
            } else if ("Delete".equals(btAction)) {
                promotionListService.deletePromotion(id);
            }
        } catch (Exception e) {
            url = errorPage;
        } finally {
            response.sendRedirect(url);
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
