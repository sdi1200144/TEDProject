package user_interface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import database.HostingRoomDAO;
import database.UserDAO;
import entities.Hostingroom;
import entities.User;

@ManagedBean(name = "rooms")
@RequestScoped
public class HostingroomListBean 
{

	private DataModel<HostingroomBean> hostRoomsList;    
	private HostingRoomDAO roomDB;
	
	public HostingroomListBean() 
	{
		
	}

	public DataModel<HostingroomBean> getHostRoomList(String hostUsername) 
	{
		roomDB = new HostingRoomDAO();
		
		List list = roomDB.getAllHostListings(hostUsername);
		if (list != null) 
		{
			ArrayList<HostingroomBean> rList = new ArrayList<HostingroomBean>();

			for (Object o : list) 
			{
				
				HostingroomBean rb = new HostingroomBean();
				
				rb.setId(((Hostingroom) o).getId());
				rb.setFrontPhoto(((Hostingroom) o).getFrontPhoto());
				rb.setCostPerNight(((Hostingroom) o).getCostPerNight());
				
				
				rb.setReviewsNum(((Hostingroom) o).getReviewsNum());
				rb.setAverageRatingStars(((Hostingroom) o).getAverageRatingStars());
				rb.setBedsNumber(((Hostingroom) o).getBedsNumber());
				rb.setBathroomsNumber(((Hostingroom) o).getBathroomsNumber());
				rb.setRoomType(((Hostingroom) o).getRoomType());
				rb.setBedroomsNumber(((Hostingroom) o).getBedroomsNumber());
				rb.setLivingRoomNumber(((Hostingroom) o).getLivingRoomNumber());
				rb.setArea(((Hostingroom) o).getArea());
				rb.setDescription(((Hostingroom) o).getDescription());
				rb.setSmokingRule(((Hostingroom) o).getSmokingRule());
				rb.setPetsRule(((Hostingroom) o).getPetsRule());
				rb.setEventsRule(((Hostingroom) o).getEventsRule());
				rb.setMinHostingDays(((Hostingroom) o).getMinHostingDays());
				rb.setLatitude(((Hostingroom) o).getLatitude());
				rb.setLongitude(((Hostingroom) o).getLongitude());
				rb.setAddress(((Hostingroom) o).getAddress());
				rb.setNeighborhood(((Hostingroom) o).getNeighborhood());
				rb.setPublicTransit(((Hostingroom) o).getPublicTransit());
				rb.setMaxGuestsNumber(((Hostingroom) o).getMaxGuestsNumber());
				rb.setMinCost(((Hostingroom) o).getMinCost());
				rb.setExtraPeopleCost(((Hostingroom) o).getExtraPeopleCost());
				rb.setWifiAmenity(((Hostingroom) o).getWifiAmenity());
				rb.setCoolingAmenity(((Hostingroom) o).getCoolingAmenity());
				rb.setHeatingAmenity(((Hostingroom) o).getHeatingAmenity());
				rb.setKitchenAmenity(((Hostingroom) o).getKitchenAmenity());
				rb.setTVAmenity(((Hostingroom) o).getTVAmenity());
				rb.setParkingAmenity(((Hostingroom) o).getParkingAmenity());
				rb.setElevatorAmenity(((Hostingroom) o).getElevatorAmenity());
				rb.setHostOwner(((Hostingroom) o).getUser());
				
				rList.add(rb);

			}

			// Sort them based on their average rating stars
			Collections.sort(rList, new Comparator<HostingroomBean>() 
			{

				public int compare(HostingroomBean o1, HostingroomBean o2) 
				{
					return  Double.compare(o2.getAverageRatingStars(),o1.getAverageRatingStars());
				}
			});

			hostRoomsList = new ListDataModel<HostingroomBean>(rList);
		}

		return hostRoomsList;
	}

	
	        
	       
	          
	        
	    


}
