/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entities.TblUser;
import entities.TblUserGroup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.UserService;

/**
 *
 * @author TiTi
 */
@WebServlet(name = "UserListServlet", urlPatterns = {"/UserListServlet"})
public class UserListServlet extends HttpServlet {

    private final String loginPage = "login.jsp";
    private final String userListPage = "userList.jsp";

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

        HttpSession session = request.getSession(true);
        UserService userService = new UserService(session);

        String url = loginPage;
        try {
            String searchName = request.getParameter("searchName");
            searchName = searchName == null ? "" : searchName;

            TblUser user = userService.getCurrentUser();
            if (user != null) {
                List<TblUserGroup> groups;
                List<TblUserGroup> allGroups;
                List<TblUser> users;
                if (user.getGroupId().getName().equals("Admin")) {
                    groups = userService.getAllGroups();
                    users = userService.getAllUsers(searchName);
                    assignUsersToGroups(users, groups);
                } else {
                    users = new ArrayList<>();
                    users.add(user); // create user list of one current non admin user

                    TblUserGroup group = user.getGroupId();
                    group.setTblUserCollection(users);  // reduce the group of the user to only one user

                    groups = new ArrayList<>();
                    groups.add(group); // set only one group to group list
                }
                allGroups = userService.getAllGroups();
                
                request.setAttribute("USER_LIST", users);
                request.setAttribute("GROUP_LIST", groups);
                request.setAttribute("ALL_GROUPS", allGroups);
                url = userListPage;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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

    private void assignUsersToGroups(List<TblUser> users, List<TblUserGroup> groups) {
        for (TblUserGroup group : groups) {
            List<TblUser> gUsers = new ArrayList<>();
            for (TblUser user : users) {
                if (user.getGroupId().equals(group)) {
                    gUsers.add(user);
                }
            }
            
            group.setTblUserCollection(gUsers);
        }
    }

}
