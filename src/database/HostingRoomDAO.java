package database;

import entities.Hostingroom;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class HostingRoomDAO 
{

	public List getAllHostListings(String hostUsername) 
	{
		List hostListings = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select h from Hostingroom h where h.user.username= :username ");
		q.setParameter("username", hostUsername);

		hostListings = q.getResultList();

		tx.commit();
		em.close();
		return hostListings;
	}
	
	
	public List searchForRooms(String roomPlace, String numberOfGuests , String checkInDate , String checkOutDate , 
			String roomType , String startingPrice , String endingPrice , 
			String wifiAmenity , String coolingAmenity , String heatingAmenity , String kitchenAmenity , String tvAmenity , String parkingAmenity , String elevatorAmenity ) throws ParseException 
	{
		List rooms = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		
		String sqlQuery = "Select h from Hostingroom h where ( h.address like :roomPlace or h.neighborhood like :roomPlace) and h.maxGuestsNumber <= :numberOfGuests ";
		
		if(roomType !=null)
		{
			sqlQuery += " and h.roomType = :roomType ";
		}
		if(startingPrice !=null && endingPrice !=null)
		{
			sqlQuery += " and ( h.costPerNight >= :startingPrice and h.costPerNight <= :endingPrice ) ";
		}
		if(wifiAmenity !=null)
		{
			sqlQuery += " and h.wifiAmenity = :wifiAmenity ";
		}
		if(coolingAmenity !=null)
		{
			sqlQuery += " and h.coolingAmenity = :coolingAmenity ";
		}
		if(heatingAmenity !=null)
		{
			sqlQuery += " and h.heatingAmenity = :heatingAmenity ";
		}
		if(kitchenAmenity !=null)
		{
			sqlQuery += " and h.kitchenAmenity = :kitchenAmenity ";
		}
		if(tvAmenity !=null)
		{
			sqlQuery += " and h.TVAmenity = :tvAmenity ";
		}
		if(parkingAmenity !=null)
		{
			sqlQuery += " and h.parkingAmenity = :parkingAmenity ";
		}
		if(elevatorAmenity !=null)
		{
			sqlQuery += " and h.elevatorAmenity = :elevatorAmenity ";
		}
		
		
		Query q = em.createQuery(sqlQuery);
		q.setParameter("roomPlace", roomPlace);
		q.setParameter("numberOfGuests", numberOfGuests);
		if(roomType !=null)
		{
			q.setParameter("roomType", roomType);
		}
		if(startingPrice !=null && endingPrice !=null)
		{
			q.setParameter("startingPrice", startingPrice);
			q.setParameter("endingPrice", endingPrice);
		}
		if(wifiAmenity !=null)
		{
			q.setParameter("wifiAmenity",wifiAmenity );
		}
		if(coolingAmenity !=null)
		{
			q.setParameter("coolingAmenity", coolingAmenity);
		}
		if(heatingAmenity !=null)
		{
			q.setParameter("heatingAmenity", heatingAmenity);
		}
		if(kitchenAmenity !=null)
		{
			q.setParameter("kitchenAmenity", kitchenAmenity);
		}
		if(tvAmenity !=null)
		{
			q.setParameter("tvAmenity", tvAmenity );
		}
		if(parkingAmenity !=null)
		{
			q.setParameter("parkingAmenity", parkingAmenity);
		}
		if(elevatorAmenity !=null)
		{
			q.setParameter("elevatorAmenity", elevatorAmenity);
		}

		rooms = q.getResultList();
		tx.commit();
		em.close();
		
		if( rooms !=null && rooms.size() > 0)
		{
			ArrayList<Integer> roomIdsToCheckDates = new ArrayList<>();
			for(Object o : rooms)
			{
				roomIdsToCheckDates.add( ((Hostingroom) o).getId() );
				
			}	
			AvailablehostingperiodDAO ahpDAO = new AvailablehostingperiodDAO();
			return ahpDAO.getAvailableRoomsForPeriod(roomIdsToCheckDates,checkInDate,checkOutDate);
		}
		else
		{
			return rooms;
		}
		
	}



	public Hostingroom findHostingRoom(String hostingRoomId) 
	{
		Hostingroom hostingroom = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select h from Hostingroom h where h.id = :hostingRoomId ");
		q.setParameter("hostingRoomId", hostingRoomId);

		List hostingrooms = q.getResultList();
		tx.commit();
		em.close();

		if (hostingrooms != null && hostingrooms.size() == 1) 
		{
			hostingroom = (Hostingroom) hostingrooms.get(0);
		} 
		else 
		{
			hostingroom = null;
		}

		return hostingroom;
	}


	public String insertNewHostingroom(Hostingroom hostingroom) 
	{
		String retMessage = "";
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try 
		{
			em.persist(hostingroom);
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
