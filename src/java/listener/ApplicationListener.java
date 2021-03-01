/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import utils.DBUtils;

/**
 * Web application lifecycle listener.
 *
 * @author TiTi
 */
@WebListener()
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManager em = DBUtils.getEntityManager();
        if (em != null) {
            em.close();
        }
    }
}
