package com.feature.dbhelper.crud;

import com.feature.dbhelper.hibernate.HibernateUtil;
import com.feature.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class ClientCrudService {

    public long create(Client client){
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();

            NativeQuery<Long> query = session.createNativeQuery(
                    "SELECT max(id) AS maxId FROM client",
                    Long.class
            );
            return query.getSingleResult();
        }
    }

    public Client getById(long id){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }

    public void update(long id, Client client){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Client editClient = session.get(Client.class, id);
        editClient.setName(client.getName());
        session.persist(editClient);
        transaction.commit();
        session.close();
    }

    public void deleteById(long id){
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Client client = session.get(Client.class, id);

        Transaction transaction = session.beginTransaction();
        session.remove(client);
        transaction.commit();

        session.close();
    }
}
