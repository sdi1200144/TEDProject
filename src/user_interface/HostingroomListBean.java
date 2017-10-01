package user_interface;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import database.HostingRoomDAO;
import entities.Hostingroom;

@ManagedBean(name = "rooms")
@SessionScoped
public class HostingroomListBean {

	private DataModel<HostingroomBean> hostRoomsList;
	private DataModel<HostingroomBean> searchRoomsList;
	private HostingRoomDAO roomDB;
	
	 private int idChosen;

	 public int getIdChosen()
	 {
		 return idChosen;
	 }

	 public void setIdChosen(int idChosen) {
		 this.idChosen = idChosen;
	 }
	
	public HostingroomListBean() 
	{
	
	}
	
	
	public String showListings(String username)
	{
		//hostRoomsList = getHostRoomList(username);
		return "/restricted/host/mylistings?faces-redirect=true";
	}


	public DataModel<HostingroomBean> getHostRoomList(String hostUsername) {
		roomDB = new HostingRoomDAO();

		List list = roomDB.getAllHostListings(hostUsername);
		if (list != null) {
			ArrayList<HostingroomBean> rList = new ArrayList<HostingroomBean>();

			for (Object o : list)
			{

				HostingroomBean rb = new HostingroomBean();

				rb.setId(((Hostingroom) o).getId());
				rb.setFrontPhoto(((Hostingroom) o).getFrontPhoto());
				rb.setCostPerNight(String.valueOf(((Hostingroom) o).getCostPerNight()));

				rb.setReviewsNum(((Hostingroom) o).getReviewsNum());
				rb.setAverageRatingStars(((Hostingroom) o).getAverageRatingStars());
				rb.setInt_averageratingstars(Double.valueOf(rb.getAverageRatingStars()).intValue());
				rb.setBedsNumber(String.valueOf(((Hostingroom) o).getBedsNumber()));
				rb.setBathroomsNumber(String.valueOf(((Hostingroom) o).getBathroomsNumber()));
				rb.setRoomType(((Hostingroom) o).getRoomType());
				rb.setBedroomsNumber(String.valueOf(((Hostingroom) o).getBedroomsNumber()));
				rb.setLivingRoomNumber(String.valueOf(((Hostingroom) o).getLivingRoomNumber()));
				rb.setArea(String.valueOf(((Hostingroom) o).getArea()));
				rb.setDescription(((Hostingroom) o).getDescription());
				rb.setSmokingRule(((Hostingroom) o).getSmokingRule());
				rb.setPetsRule(((Hostingroom) o).getPetsRule());
				rb.setEventsRule(((Hostingroom) o).getEventsRule());
				rb.setMinHostingDays(String.valueOf(((Hostingroom) o).getMinHostingDays()));
				rb.setLatitude(String.valueOf(((Hostingroom) o).getLatitude()));
				rb.setLongitude(String.valueOf(((Hostingroom) o).getLongitude()));
				rb.setAddress(((Hostingroom) o).getAddress());
				rb.setNeighborhood(((Hostingroom) o).getNeighborhood());
				rb.setPublicTransit(((Hostingroom) o).getPublicTransit());
				rb.setMaxGuestsNumber(String.valueOf(((Hostingroom) o).getMaxGuestsNumber()));
				rb.setMinCost(String.valueOf(((Hostingroom) o).getMinCost()));
				rb.setExtraPeopleCost(String.valueOf(((Hostingroom) o).getExtraPeopleCost()));
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
			Collections.sort(rList, new Comparator<HostingroomBean>() {

				public int compare(HostingroomBean o1, HostingroomBean o2) {
					return Double.compare(o2.getAverageRatingStars(), o1.getAverageRatingStars());
				}
			});

			hostRoomsList = new ListDataModel<HostingroomBean>(rList);
		}

		return hostRoomsList;
	}

	// ArrayList<HostingroomBean> rList = new ArrayList<HostingroomBean>();
	//
	//
	// public ArrayList<HostingroomBean> getrList() {
	// return rList;
	// }
	//
	// public void setrList(ArrayList<HostingroomBean> rList) {
	// this.rList = rList;
	// }
	// int index = 0;
	//
	// public int getIndex() {
	// return index;
	// }
	//
	// public void setIndex(int index) {
	// this.index = index;
	// }

	public DataModel<HostingroomBean> getSearchRoomList(String roomPlace, int numberOfGuests, String checkInDate,
			String checkOutDate, String roomType, double startingPrice, double endingPrice, boolean wifiAmenity,
			boolean coolingAmenity, boolean heatingAmenity, boolean kitchenAmenity, boolean tvAmenity,
			boolean parkingAmenity, boolean elevatorAmenity) throws ParseException {
		System.out.println("roomplace is " + roomPlace + "date is " + checkInDate);
		roomDB = new HostingRoomDAO();

		List list = roomDB.searchForRooms(roomPlace, numberOfGuests, checkInDate, checkOutDate, roomType, startingPrice,
				endingPrice, wifiAmenity, coolingAmenity, heatingAmenity, kitchenAmenity, tvAmenity, parkingAmenity,
				elevatorAmenity);
		if (list != null) {
			ArrayList<HostingroomBean> rList = new ArrayList<HostingroomBean>();

			for (Object o : list) {

				HostingroomBean rb = new HostingroomBean();

				rb.setId(((Hostingroom) o).getId());
				rb.setFrontPhoto(((Hostingroom) o).getFrontPhoto());
				rb.setCostPerNight(String.valueOf(((Hostingroom) o).getCostPerNight()));

				rb.setReviewsNum(((Hostingroom) o).getReviewsNum());
				rb.setAverageRatingStars(((Hostingroom) o).getAverageRatingStars());
				rb.setInt_averageratingstars(Double.valueOf(rb.getAverageRatingStars()).intValue());
				rb.setBedsNumber(String.valueOf(((Hostingroom) o).getBedsNumber()));
				rb.setBathroomsNumber(String.valueOf(((Hostingroom) o).getBathroomsNumber()));
				rb.setRoomType(((Hostingroom) o).getRoomType());
				rb.setBedroomsNumber(String.valueOf(((Hostingroom) o).getBedroomsNumber()));
				rb.setLivingRoomNumber(String.valueOf(((Hostingroom) o).getLivingRoomNumber()));
				rb.setArea(String.valueOf(((Hostingroom) o).getArea()));
				rb.setDescription(((Hostingroom) o).getDescription());
				rb.setSmokingRule(((Hostingroom) o).getSmokingRule());
				rb.setPetsRule(((Hostingroom) o).getPetsRule());
				rb.setEventsRule(((Hostingroom) o).getEventsRule());
				rb.setMinHostingDays(String.valueOf(((Hostingroom) o).getMinHostingDays()));
				rb.setLatitude(String.valueOf(((Hostingroom) o).getLatitude()));
				rb.setLongitude(String.valueOf(((Hostingroom) o).getLongitude()));
				rb.setAddress(((Hostingroom) o).getAddress());
				rb.setNeighborhood(((Hostingroom) o).getNeighborhood());
				rb.setPublicTransit(((Hostingroom) o).getPublicTransit());
				rb.setMaxGuestsNumber(String.valueOf(((Hostingroom) o).getMaxGuestsNumber()));
				rb.setMinCost(String.valueOf(((Hostingroom) o).getMinCost()));
				rb.setExtraPeopleCost(String.valueOf(((Hostingroom) o).getExtraPeopleCost()));
				rb.setWifiAmenity(((Hostingroom) o).getWifiAmenity());
				rb.setCoolingAmenity(((Hostingroom) o).getCoolingAmenity());
				rb.setHeatingAmenity(((Hostingroom) o).getHeatingAmenity());
				rb.setKitchenAmenity(((Hostingroom) o).getKitchenAmenity());
				rb.setTVAmenity(((Hostingroom) o).getTVAmenity());
				rb.setParkingAmenity(((Hostingroom) o).getParkingAmenity());
				rb.setElevatorAmenity(((Hostingroom) o).getElevatorAmenity());
				rb.setHostOwner(((Hostingroom) o).getUser());

				rList.add(rb);
				// rList.get(0).getCostPerNight()
				// index++;

			}

			// Sort them based on their cost per night
			Collections.sort(rList, new Comparator<HostingroomBean>() {

				public int compare(HostingroomBean o1, HostingroomBean o2) {
					return Double.compare(Double.valueOf(o1.getCostPerNight()), Double.valueOf(o2.getCostPerNight()));
				}
			});

			hostRoomsList = new ListDataModel<HostingroomBean>(rList);
		}

		System.out.println("LIST IS " + hostRoomsList.getRowCount());
		return hostRoomsList;
	}
	
	
	 public Hostingroom searchRoomDetails() 
	 {
		  // Map<String,String> params =
		  // FacesContext.getExternalContext().getRequestParameterMap();
		  // String action = params.get("action");
		  roomDB = new HostingRoomDAO();
		  Hostingroom rb = new Hostingroom();
		  rb = roomDB.findHostingroomFromId(idChosen);
		  

		  return rb;
	 }
	 
	 public String roomDetails(int id)
	 {
		  idChosen = id;
		  return "/room_details?faces-redirect=true";
	 }

}
