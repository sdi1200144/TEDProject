package database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class RecommendationsDAO 
{

	
	public List getAllRecommendations(String guestUsername) 
	{
		List hostListings = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select r from Recommendations r where r.user.username= :username ");
		q.setParameter("username", guestUsername);

		hostListings = q.getResultList();

		tx.commit();
		em.close();
		return hostListings;
	}
	
}
