/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csat.hibernatetest.utils;

import java.util.Properties;
import java.util.Set;
import javax.persistence.Entity;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;

/**
 *
 * @author Csore_A_Tamas
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Logger logger = Logger.getLogger(HibernateUtil.class.getSimpleName());;
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/test?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.POOL_SIZE, 1);
                settings.put(Environment.MAX_FETCH_DEPTH, 5);
                configuration.setProperties(settings);
                
                for (Class entity : getEntityClassesFromPackageByRefection("csat.hibernatetest.jpa")) {
                    configuration.addAnnotatedClass(entity);
                    logger.debug("JPA LOADED: " + entity.getCanonicalName() + ": " + entity.toString());
                }
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                logger.fatal("Fatal exception during creation of Hibernate session.", e);
                System.exit(1);
            }
        }
        return sessionFactory;
    }
    
    private static Set<Class<?>> getEntityClassesFromPackageByRefection(String _packageName){
        Reflections reflections = new Reflections(_packageName);
        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Entity.class);
        return entities;
    }
}
