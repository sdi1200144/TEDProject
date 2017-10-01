package database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import entities.Availablehostingperiod;
import entities.Hostingroom;

public class AvailablehostingperiodDAO 
{
	
	public List getAllPeriods() 
    {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select a from Availablehostingperiod a");
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }
	
	public List getAvailableRoomsForPeriod(ArrayList<Integer> roomIdsToCheckDates, String checkInDate, String checkOutDate) throws ParseException
	{
		ArrayList<Hostingroom> rooms = new ArrayList<>();
		
		List tempRooms = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		
		for(Integer roomId : roomIdsToCheckDates)
		{
			Hostingroom hr = findAvailableRoom(roomId,checkInDate,checkOutDate);
			if(hr!=null)
			{
				rooms.add(hr);
			}
		}
		
		return rooms;
	}
	
	public Hostingroom findAvailableRoom(Integer roomId , String checkIn, String checkOut) throws ParseException
	{
		List tempRooms = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		DateFormat targetDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		Date i_checkInDate = format.parse(checkIn);
		Date i_checkOutDate = format.parse(checkOut);
		
		Date checkInDate = targetDateFormat.parse( targetDateFormat.format(i_checkInDate) );
		Date checkOutDate = targetDateFormat.parse( targetDateFormat.format(i_checkOutDate) );
		
		
		String sqlQuery = "Select a.hostingroom from Availablehostingperiod a where a.hostingroom.id = :roomId and ";
		sqlQuery += " a.fromDate <= :checkInDate and a.toDate >= :checkInDate and ";
		sqlQuery += " a.fromDate <= :checkOutDate and a.toDate >= :checkOutDate ";
		
		Query q = em.createQuery(sqlQuery);
		q.setParameter("roomId", roomId);
		q.setParameter("checkInDate", checkInDate);
		q.setParameter("checkOutDate", checkOutDate);
		
		tempRooms = q.getResultList();
		tx.commit();
		em.close();
		
		if(tempRooms !=null && tempRooms.size()>0)
		{
			return (Hostingroom) tempRooms.get(0);
		}
		else
		{
			return null;
		}
		
	}
	
	
	public String insertNewAvailableHostingPeriod(Availablehostingperiod availablehostingperiod) 
	{
		String retMessage = "";
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try 
		{
			em.persist(availablehostingperiod);
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
	

}
