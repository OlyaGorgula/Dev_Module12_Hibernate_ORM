package com.feature.dbhelper.crud;

import com.feature.dbhelper.hibernate.HibernateUtil;
import com.feature.entity.Planet;
import com.feature.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlanetCrudService {
    
    public String create(Planet planet){
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
            return planet.getId();
        }catch (Exception e){
            return null;
        }

    }

    public Planet getById(String id){
        return getById(id, false);
    }

    public Planet getById(String id, boolean printTickets){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Planet planet = session.get(Planet.class, id);
        if (printTickets){
            for (Ticket fromPlanet : planet.getTicketsFromPlanet());
            for (Ticket toPlanet : planet.getTicketsToPlanet());
        }
        session.close();
        return planet;
    }

    public void update(String id, Planet Planet){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Planet editPlanet = session.get(Planet.class, id);
        editPlanet.setName(Planet.getName());
        session.persist(editPlanet);
        transaction.commit();
        session.close();
    }

    public void deleteById(String id){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Planet planet = getById(id);

        Transaction transaction = session.beginTransaction();
        session.remove(planet);
        transaction.commit();

        session.close();
    }
}
