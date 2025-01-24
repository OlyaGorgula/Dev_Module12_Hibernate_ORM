package com.feature.dbhelper.crud;

import com.feature.dbhelper.hibernate.HibernateUtil;
import com.feature.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TicketCrudService {
    public long create(Ticket ticket){
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){

            Transaction transaction = session.beginTransaction();

            ticket.getClient().setTickets(Arrays.asList(ticket));
            ticket.getFromPlanet().setTicketsFromPlanet(Arrays.asList(ticket));
            ticket.getToPlanet().setTicketsToPlanet(Arrays.asList(ticket));

            session.persist(ticket);
            transaction.commit();

            NativeQuery<Long> query = session.createNativeQuery(
                    "SELECT max(id) AS maxId FROM ticket",
                    Long.class
            );
            return query.getSingleResult();
        }catch (Exception e){
            return -1;
        }
    }

    public LocalDateTime getTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }

    public Ticket getById(long id){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Ticket ticket = session.get(Ticket.class, id);
        session.close();
        return ticket;
    }

    public void update(long id, Ticket ticket){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Ticket editTicket = session.get(Ticket.class, id);
        editTicket.setCreatedAt(ticket.getCreatedAt());
        editTicket.setFromPlanet(ticket.getFromPlanet());
        editTicket.setToPlanet(ticket.getToPlanet());
        editTicket.setClient(ticket.getClient());

        session.persist(editTicket);

        transaction.commit();
        session.close();
    }

    public void deleteById(long id){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Ticket ticket = session.get(Ticket.class, id);

        Transaction transaction = session.beginTransaction();
        session.remove(ticket);
        transaction.commit();

        session.close();
    }
}
