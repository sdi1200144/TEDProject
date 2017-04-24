package database;

import entities.User;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@ManagedBean(name="userDAO")
@SessionScoped
public class UserDAO {

	@ManagedProperty(value="#{jpaResourceBean}")
	protected JPAResourceBean jpaResourceBean;
	

	@SuppressWarnings("unchecked")
	public List<User> getUsers()
	{
		List<User> users = null;
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select u from User u");
		users = q.getResultList();

		tx.commit();
		em.close();
		return users;
	}


	public String insertUser(User user) 
	{
		String retMessage = "";
		EntityManager em = jpaResourceBean.getEMF().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try 
		{
			em.persist(user);
			tx.commit();
			retMessage = "ok";
			return retMessage;
		} 
		catch (PersistenceException e) 
		{
			if (tx.isActive())
				tx.rollback();
			retMessage = e.getMessage();
			return retMessage;
		} 
		finally 
		{
			em.close();
		}
	}
	

	public void setJpaResourceBean(JPAResourceBean jpaResourceBean) 
	{
		this.jpaResourceBean = jpaResourceBean;
	}

	public JPAResourceBean getJpaResourceBean() 
	{
		return jpaResourceBean;
	}

}