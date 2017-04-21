package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.User;


public class UserDB {

    public List getUsers() 
    {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u ");
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }
    
    public User find(String username, String password) 
    {
        User user = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.username = :username and u.password = :password");
        q.setParameter("username", username);
        q.setParameter("password", password);
        List users = q.getResultList();
        tx.commit();
        em.close();

        if (users != null && users.size() == 1) 
        {
            user = (User) users.get(0);
        } 
        else 
        {
            user = null;
        }

        return user;
    }
}