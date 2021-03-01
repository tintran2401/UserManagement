/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import dao.UserGroupDAO;
import entities.TblUser;
import entities.TblUserGroup;
import java.util.List;
import javax.servlet.http.HttpSession;
import utils.PasswordEncrypter;

/**
 *
 * @author TiTi
 */
public class UserService {

    private HttpSession session;

    public UserService() {
    }

    public UserService(HttpSession session) {
        this.session = session;
    }

    public List<TblUser> getAllUsers(String searchName) {
        UserDAO userDAO = new UserDAO();
        return userDAO.getAllActiveUsers(searchName);
    }

    public List<TblUser> getAllUsers(TblUserGroup group) {
        UserDAO userDAO = new UserDAO();
        return userDAO.getAllActiveUsers(group);
    }

    public List<TblUserGroup> getAllGroups() {
        UserGroupDAO userGroupDAO = new UserGroupDAO();
        return userGroupDAO.getAllGroups();
    }

    public TblUser getCurrentUser() {
        if (session == null) {
            return null;
        }
        return (TblUser) session.getAttribute("USER");
    }

    public boolean setCurrentUser(TblUser user) {
        if (session == null) {
            return false;
        }

        session.setAttribute("USER", user);
        return true;
    }

    public TblUser login(String username, String password) {
        UserDAO userDAO = new UserDAO();
        TblUser user = userDAO.getUserByUsername(username);

        if (user == null) {
            return null;
        }

        String sha256Password = PasswordEncrypter.encrypt(password);
        if (user.getPassword().equals(sha256Password)) {
            return user;
        }
        return null;
    }

    public void logout() {
        this.setCurrentUser(null);
    }

    public void refreshCurrentUser() {
        TblUser currentUser = this.getCurrentUser();
        UserDAO userDAO = new UserDAO();
        TblUser dbUser = userDAO.getUserByUsername(currentUser.getUsername());
        this.setCurrentUser(dbUser);
    }

    public void deleteUser(String username) {
        UserDAO userDAO = new UserDAO();
        TblUser user = userDAO.getUserByUsername(username);
        user.setStatus("inactive");
        userDAO.updateUser(user);
    }

    public TblUser updateUser(String username, String password, String email, String phone, String photo, int groupId) {
        UserDAO userDAO = new UserDAO();
        TblUser user = userDAO.getUserByUsername(username);

        if (password != null && !password.trim().isEmpty()) {
            String passwordHash = PasswordEncrypter.encrypt(password.trim());
            user.setPassword(passwordHash);
        }

        user.setEmail(email);
        user.setPhone(phone);
        user.setPhoto(photo);

        UserGroupDAO groupDAO = new UserGroupDAO();
        TblUserGroup group = groupDAO.getGroupById(groupId);
        user.setGroupId(group);

        return userDAO.updateUser(user);
    }
    
    public TblUser createUser(String username, String password, String name, String email, String phone, String photo, int groupId) {
        TblUser user = new TblUser();
        
        user.setUsername(username.trim());
        
        if (password != null && !password.trim().isEmpty()) {
            String passwordHash = PasswordEncrypter.encrypt(password.trim());
            user.setPassword(passwordHash);
        }
        
        user.setName(name.trim());
        user.setEmail(email.trim());
        user.setPhone(phone.trim());
        user.setPhoto(photo.trim());
        
        UserGroupDAO groupDAO = new UserGroupDAO();
        TblUserGroup group = groupDAO.getGroupById(groupId);
        user.setGroupId(group);
        
        user.setStatus("active");
        
        UserDAO userDAO = new UserDAO();

        return userDAO.createUser(user);
    }
}
