package database;

import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class UserDAO 
{

	public List getAllUsers() 
    {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u");
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }
	
    public List getAllUsersExceptAdmins() 
    {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.role!='Admin' ");
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }
    
    public List getAllHosts() 
    {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.role='Host' ");
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }
    
    public List getAllGuests() 
    {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.role='Guest' ");
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }
    
    public List getAllHostAndGuests() 
    {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.role='Host&Guest' ");
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }
    
    public List getAllNotConfirmedHosts()
    {
    	List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.isConfirmed=false ");
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }

    public List getAllUsersExceptGiven(String username) {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.role!='Admin' and u.username!= :username");
        q.setParameter("username", username);
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }

    public User findUser(String username, String password) 
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
    
    public boolean hasSameEmail(String email)
    {
    	System.out.println("eMAIL TO BE TESTED IS "+email);
    	User user = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.email = :email");
        q.setParameter("email", email);
        List users = q.getResultList();
        tx.commit();
        em.close();

        if (users != null && users.size() >= 1) 
        {
            return true;
        } 
        else 
        {
        	return false;
        }
    }
    
   
    
    public String hasSameUsername_Email_MobilePhone(String username , String email , String mobileNumber)
    {
    	User user = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.username = :username");
        q.setParameter("username", username);
        List users = q.getResultList();
        tx.commit();
        em.close();

        if (users != null && users.size() >= 1) 
        {
            return "SameUsername";
        } 
        else 
        {
        	user = null;

            emf = Persistence.createEntityManagerFactory("TEDProject");
            em = emf.createEntityManager();

            tx = em.getTransaction();
            tx.begin();

            q = em.createQuery("Select u from User u where u.email = :email");
            q.setParameter("email", email);
            users = q.getResultList();
            tx.commit();
            em.close();
            if (users != null && users.size() >= 1) 
            {
                return "SameEmail";
            } 
            else 
            {
            	user = null;

                emf = Persistence.createEntityManagerFactory("TEDProject");
                em = emf.createEntityManager();

                tx = em.getTransaction();
                tx.begin();

                q = em.createQuery("Select u from User u where u.mobileNumber = :mobileNumber");
                q.setParameter("mobileNumber", mobileNumber);
                users = q.getResultList();
                tx.commit();
                em.close();
                if (users != null && users.size() >= 1) 
                {
                    return "SameMobileNumber";
                } 
                else 
                {
                	return "OtherError";
                }
            	
            }
            
        }

    }

    public String insertNewUser(User user) 
    {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
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

    public User getUser(String username) 
    {
        User user = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.username = :username");
        q.setParameter("username", username);
        List users = q.getResultList();
        tx.commit();
        em.close();

        if (users != null && users.size() == 1) 
        {
            user = (User) users.get(0);
        }

        return user;
    }

    public String updateUserConfirmation(String username) 
    {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Update User u set u.isConfirmed = true where u.username = :username");
        q.setParameter("username", username);
        q.executeUpdate();
        try 
        {
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

    
    public String updateAllUsersConfirmation() 
    {
        String retMessage = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Update User u set u.isConfirmed = true where u.isConfirmed=false");
        q.executeUpdate();
        try 
        {
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
    
    public String updateUserProfilePicture(String username , String profilePicture)
    {
    	String retMessage = "";
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();

    	Query q = em.createQuery("Update User u set u.photo = :profilePicture where u.username = :username"); 
    	q.setParameter("username", username);
    	q.setParameter("profilePicture", profilePicture);
    	try 
    	{
    		q.executeUpdate(); 


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
    
    
    public String updateUser(String username, String password, String email, String mobileNumber) 
    {
    	String retMessage = "";
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();

    	Query q = em.createQuery("Update User u set u.password = :password, u.email = :email, u.mobileNumber = :mobileNumber where u.username = :username"); 
    	q.setParameter("username", username);
    	q.setParameter("password", password);
    	q.setParameter("email", email);
    	q.setParameter("mobileNumber", mobileNumber);

    	try 
    	{
    		q.executeUpdate(); 


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
    
    
    
    public User findIdUser(int userId) 
    {
        User user = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select u from User u where u.id = :userId");
        q.setParameter("userId", userId);
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