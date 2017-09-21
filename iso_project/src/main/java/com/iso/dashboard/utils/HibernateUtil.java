///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.iso.dashboard.utils;
//
//import java.util.HashMap;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//
///**
// *
// * @author VIET_BROTHER
// */
//public class HibernateUtil {
//
////    private static SessionFactory sessionFactory;
////
////    static {
////        try {
////            if (sessionFactory == null) {
////                // loads configuration and mappings
////                Configuration configuration = new Configuration().configure();
////                ServiceRegistry serviceRegistry
////                        = new StandardServiceRegistryBuilder()
////                        .applySettings(configuration.getProperties()).build();
////
////                // builds a session factory from the service registry
////                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
////            }
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
////
////    public static void shutdown() {
////        if (sessionFactory != null) {
////            sessionFactory.close();
////        }
////    }
////
////    public static Session getSessionAndBeginTransaction() {
////        if (sessionFactory == null) {
////            System.out.println("Error!");
////        }
////        Session session = sessionFactory.getCurrentSession();
////        session.beginTransaction();
////        return session;
////    }
//    private static SessionFactory sessionFactory;
//
//    static {
//        try {
//            if (sessionFactory == null) {
//                // loads configuration and mappings
//                Configuration configuration = new Configuration().configure();
//                ServiceRegistry serviceRegistry
//                        = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
//
//                // builds a session factory from the service registry
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public static void shutdown() {
//        if (sessionFactory != null) {
//            sessionFactory.close();
//        }
//    }
//
//    public static Session getSessionAndBeginTransaction() {
//        if (sessionFactory == null) {
//            System.out.println("Error!");
//        }
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        return session;
//    }
//
//    public static Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    public static void commitCurrentSessions()
//            throws Exception {
//        if (sessionFactory != null) {
//            Session session = sessionFactory.getCurrentSession();
//            if (session.isOpen()) {
//                Transaction t = session.getTransaction();
//                if (t.isActive()) {
//                    try {
//                        t.commit();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    public static void closeCurrentSessions()
//            throws Exception {
//        if (sessionFactory != null) {
//            Session session = sessionFactory.getCurrentSession();
//            if (session.isOpen()) {
//                try {
//                    session.close();
//                } catch (Exception e) {
//                }
//            }
//        }
//    }
//
//    public static void rollBackSessions() {
//        if (sessionFactory != null) {
//            Session session = sessionFactory.getCurrentSession();
//            if (session.isOpen()) {
//                Transaction t = session.getTransaction();
//                try {
//                    t.rollback();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
package com.iso.dashboard.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
