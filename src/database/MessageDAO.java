package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import entities.Message;

public class MessageDAO {
	
	public String insertMessage(Message message) 
    {
		String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        try 
        {
            em.persist(message);
            tx.commit();
            retMessage = "ok";
            return retMessage;
        } 
        catch (PersistenceException e) 
        {
            if (tx.isActive()) 
            {
                tx.rollback();
            }
            retMessage = e.getMessage();
            return retMessage;
        } 
        finally 
        {
            em.close();
        }
    }
	
	public List getAllMessagess() 
    {
        List messages = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from Message u");
        messages = q.getResultList();

        tx.commit();
        em.close();
        return messages;
    }

}
