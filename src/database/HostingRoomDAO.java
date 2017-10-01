package database;

import entities.Hostingroom;
import entities.User;

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
	public List getAllRooms() 
    {
        List users = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query q = em.createQuery("Select h from Hostingroom h");
        users = q.getResultList();

        tx.commit();
        em.close();
        return users;
    }
	
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
	
	
	public List searchForRooms(String roomPlace, int numberOfGuests , String checkInDate , String checkOutDate , 
			String roomType , double startingPrice , double endingPrice , 
			boolean wifiAmenity , boolean coolingAmenity , boolean heatingAmenity , boolean kitchenAmenity , boolean tvAmenity , boolean parkingAmenity , boolean elevatorAmenity ) throws ParseException 
	{
		List rooms = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		
		String sqlQuery = "Select h from Hostingroom h where ( h.address like :roomPlace or h.neighborhood like :roomPlace) and h.maxGuestsNumber >= :numberOfGuests ";
		
		if(!roomType.isEmpty())
		{
			sqlQuery += "and h.roomType = :roomType ";
		}
		if(endingPrice > 0)
		{
			sqlQuery += " and ( h.costPerNight >= :startingPrice and h.costPerNight <= :endingPrice ) ";
		}
		if(wifiAmenity != false)
		{
			sqlQuery += " and h.wifiAmenity = :wifiAmenity ";
		}
		if(coolingAmenity != false)
		{
			sqlQuery += " and h.coolingAmenity = :coolingAmenity ";
		}
		if(heatingAmenity != false)
		{
			sqlQuery += " and h.heatingAmenity = :heatingAmenity ";
		}
		if(kitchenAmenity != false)
		{
			sqlQuery += " and h.kitchenAmenity = :kitchenAmenity ";
		}
		if(tvAmenity != false)
		{
			sqlQuery += " and h.TVAmenity = :tvAmenity ";
		}
		if(parkingAmenity != false)
		{
			sqlQuery += " and h.parkingAmenity = :parkingAmenity ";
		}
		if(elevatorAmenity != false)
		{
			sqlQuery += " and h.elevatorAmenity = :elevatorAmenity ";
		}
		
		
		Query q = em.createQuery(sqlQuery);
		q.setParameter("roomPlace", roomPlace+"%");
		q.setParameter("numberOfGuests", numberOfGuests);
		
		System.out.println("roomplace is " + roomPlace);
		System.out.println("roomtype is " + roomType);
		System.out.println("numberOfGuests is " + numberOfGuests);
		System.out.println("start is " + startingPrice);
		System.out.println("end is " + endingPrice);
		
		if(!roomType.isEmpty())
		{
			q.setParameter("roomType", roomType);
		}
		if(endingPrice > 0)
		{
			q.setParameter("startingPrice", startingPrice);
			q.setParameter("endingPrice", endingPrice);
		}
		if(wifiAmenity != false)
		{
			System.out.println("wifi is " + wifiAmenity);
			q.setParameter("wifiAmenity",wifiAmenity );
		}
		if(coolingAmenity != false)
		{
			q.setParameter("coolingAmenity", coolingAmenity);
		}
		if(heatingAmenity != false)
		{
			q.setParameter("heatingAmenity", heatingAmenity);
		}
		if(kitchenAmenity != false)
		{
			q.setParameter("kitchenAmenity", kitchenAmenity);
		}
		if(tvAmenity != false)
		{
			q.setParameter("tvAmenity", tvAmenity );
		}
		if(parkingAmenity != false)
		{
			q.setParameter("parkingAmenity", parkingAmenity);
		}
		if(elevatorAmenity != false)
		{
			q.setParameter("elevatorAmenity", elevatorAmenity);
		}

		rooms = q.getResultList();
		System.out.println("rooms are " + rooms.size());
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



	public Hostingroom findHostingRoom(int hostingRoomId) 
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
	

	public Hostingroom findHostingroomFromId(int id) 
	{
		Hostingroom hostingroom = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select h from Hostingroom h where h.id = :id ");
		q.setParameter("id", id);
		List hostingrooms = q.getResultList();
		tx.commit();
		em.close();

		if (hostingrooms != null && hostingrooms.size() == 1) {
			hostingroom = (Hostingroom) hostingrooms.get(0);
		} else {
			hostingroom = null;
		}

		return hostingroom;
	}
	
	public List getAllHostingRooms() {
		List hostList = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TEDProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query q = em.createQuery("Select u from Hostingroom u");

		hostList = q.getResultList();

		tx.commit();
		em.close();
		return hostList;
	}

}
