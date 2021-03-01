/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.TblUser;
import entities.TblUserGroup;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import utils.DBUtils;

/**
 *
 * @author TiTi
 */
public class UserDAO {

    public TblUser getUserByUsername(String username) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<TblUser> users = em.createNamedQuery("TblUser.findByUsername")
                    .setParameter("username", username)
                    .getResultList();

            transaction.commit();

            if (!users.isEmpty()) {
                return users.get(0);
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return null;
    }

    public List<TblUser> getAllActiveUsers() {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<TblUser> users = em.createNamedQuery("TblUser.findAllActive")
                    .getResultList();

            transaction.commit();

            return users;
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return null;
    }
    
    public List<TblUser> getAllActiveUsers(String name) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<TblUser> users = em.createNamedQuery("TblUser.findByName")
                    .setParameter("name", "%" + name + "%")
                    .getResultList();

            transaction.commit();

            return users;
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return null;
    }
    
    public List<TblUser> getAllActiveUsers(TblUserGroup group) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<TblUser> users = em.createNamedQuery("TblUser.findByGroup")
                    .setParameter("group", group)
                    .getResultList();

            transaction.commit();

            return users;
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return null;
    }
    
    public TblUser updateUser(TblUser user) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.merge(user);

            transaction.commit();
            return user;
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return null;
    }
    
    public TblUser createUser(TblUser user) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.persist(user);

            transaction.commit();
            return user;
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return null;
    }
}
