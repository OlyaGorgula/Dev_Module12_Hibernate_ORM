package com.feature.dbhelper.hibernate;

import com.feature.entity.Client;
import com.feature.entity.Planet;
import com.feature.entity.Ticket;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    static {
        INSTANCE = new HibernateUtil();
    }

    @Getter
    private SessionFactory sessionFactory;

    public HibernateUtil(){
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance(){
        return INSTANCE;
    }

    public void close(){
        sessionFactory.close();
    }

}
