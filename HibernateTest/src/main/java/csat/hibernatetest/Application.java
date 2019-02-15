/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csat.hibernatetest;

import csat.hibernatetest.jpa.A;
import csat.hibernatetest.utils.HibernateUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author Csore_A_Tamas
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("APPLICATION STARTED");
        Logger logger = Logger.getLogger("main");
        
        BasicConfigurator.configure();
        
        logger.info("CREATE SESSION");
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        logger.info("GET ENTITY");
        A a = session.find(A.class, 10);
        
        logger.info("ENTITY SUCCESSFULLY RETRIEVED");
    }
}
