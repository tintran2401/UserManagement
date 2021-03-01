/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class UserGroupDAO {

    public TblUserGroup getGroupByName(String name) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<TblUserGroup> groups = em.createNamedQuery("TblUserGroup.findByName")
                    .setParameter("name", name)
                    .getResultList();

            transaction.commit();

            if (!groups.isEmpty()) {
                return groups.get(0);
            }
        } catch (Exception e) {
            Logger.getLogger(UserGroupDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return null;
    }

    public TblUserGroup getGroupById(int id) {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<TblUserGroup> groups = em.createNamedQuery("TblUserGroup.findById")
                    .setParameter("id", id)
                    .getResultList();

            transaction.commit();

            if (!groups.isEmpty()) {
                return groups.get(0);
            }
        } catch (Exception e) {
            Logger.getLogger(UserGroupDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return null;
    }
    
    public List<TblUserGroup> getAllGroups() {
        EntityManager em = DBUtils.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            List<TblUserGroup> groups = em.createNamedQuery("TblUserGroup.findAll")
                    .getResultList();

            transaction.commit();

            return groups;
        } catch (Exception e) {
            Logger.getLogger(UserGroupDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return null;
    }
}
