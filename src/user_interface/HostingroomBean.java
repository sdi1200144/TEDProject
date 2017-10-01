package user_interface;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import database.AvailablehostingperiodDAO;
import database.BookingDAO;
import database.HostingRoomDAO;
import database.UserDAO;
import entities.Availablehostingperiod;
import entities.Booking;
import entities.Hostingroom;
import entities.User;


@ManagedBean(name = "room")
//@ViewScoped
//@RequestScoped
@SessionScoped
public class HostingroomBean 
{
	private int id;
	private String frontPhoto;
	private String costPerNight;
	private int reviewsNum;
	private double averageRatingStars;
	private String bedsNumber;
	private String bathroomsNumber;
	private String roomType;
	private String bedroomsNumber;
	private String livingRoomNumber;
	private String area;
	private String name;
	private String description;
	private boolean smokingRule;
	private boolean petsRule;
	private boolean eventsRule;
	private String minHostingDays;
	private String latitude;
	private String longitude;
	private String address;
	private String neighborhood;
	private String publicTransit;
	private String maxGuestsNumber;
	private String minCost;
	private String extraPeopleCost;
	private boolean wifiAmenity;
	private boolean coolingAmenity;
	private boolean heatingAmenity;
	private boolean kitchenAmenity;
	private boolean TVAmenity;
	private boolean parkingAmenity;
	private boolean elevatorAmenity;
	private User hostOwner;
	
	private String hostCheckIn;
	private String hostCheckOut;
	
	
	private int int_averageratingstars;

	private String[] selectedAmenities;
	private String[] selectedPublicTransit;

	private Hostingroom currentHostingroom;

	// --------------------------------------------------------------------------------------------------
	private final MapModel draggableModel = new DefaultMapModel();
//	private final MapModel simpleModel = new DefaultMapModel();
	private Marker marker;
	
	private String roomPlace;
	private String checkInChosen;
	private String checkOutChosen;
	 
	/**
	  * @return the roomPlace
	  */
	 public String getRoomPlace() {
	  return roomPlace;
	 }

	 /**
	  * @param roomPlace the roomPlace to set
	  */
	 public void setRoomPlace(String roomPlace) {
	  this.roomPlace = roomPlace;
	 }

	/**
	  * @return the checkInChosen
	  */
	 public String getCheckInChosen() {
	  return checkInChosen;
	 }

	 /**
	  * @param checkInChosen the checkInChosen to set
	  */
	 public void setCheckInChosen(String checkInChosen) {
	  this.checkInChosen = checkInChosen;
	 }

	 /**
	  * @return the checkOutChosen
	  */
	 public String getCheckOutChosen() {
	  return checkOutChosen;
	 }

	 /**
	  * @param checkOutChosen the checkOutChosen to set
	  */
	 public void setCheckOutChosen(String checkOutChosen) {
	  this.checkOutChosen = checkOutChosen;
	 }

	 Integer a = null;
	 //nullify guestchosen TODO
	 private int guestsChosen;
	 //guestsChosen = a.intValue();
	 /**
	   * @return the guestsChosen
	   */
	  public int getGuestsChosen() {
	   return guestsChosen;
	  }

	  /**
	   * @param guestsChosen the guestsChosen to set
	   */
	  public void setGuestsChosen(int guestsChosen) {
	   this.guestsChosen = guestsChosen;
	  }
	  
	  public String search() {
			return "/search_results?faces-redirect=true";
		}
		
	@PostConstruct
	public void init() 
	{
		//draggableModel = new DefaultMapModel();

		//Shared coordinates
		LatLng coord1 = new LatLng(37.983810,23.727539);
		//Draggable
		draggableModel.addOverlay(new Marker(coord1, "Your new home"));

		for(Marker premarker : draggableModel.getMarkers()) 
		{
			premarker.setDraggable(true);
		}
		
		/*//Shared coordinates
		if(!latitude.isEmpty() && !longitude.isEmpty() && !(latitude==null) && !(longitude==null))
		{
			LatLng coord2 = new LatLng(Double.parseDouble(latitude),Double.parseDouble("longitude"));
			//Draggable
			simpleModel.addOverlay(new Marker(coord2, description));

			for(Marker premarker : simpleModel.getMarkers()) 
			{
				premarker.setDraggable(false);
			}
		}*/
			
	}
	

	/*public MapModel getSimpleModel()
	{
		return simpleModel;
	}*/
	
	public MapModel getDraggableModel() 
	{
		return draggableModel;
	}

//	public void onMarkerDrag(MarkerDragEvent event) 
//	{
//		marker = event.getMarker();
//		System.out.println(marker.getLatlng().getLat());
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
//	}
	
	
	public void onMarkerDrag(MarkerDragEvent event) 
	{  
	    Marker marker = event.getMarker();  

	    if (draggableModel.getMarkers().size() > 0) 
	    {
	    	draggableModel.getMarkers().get(0).setLatlng(marker.getLatlng());
	        
	        latitude = String.valueOf(marker.getLatlng().getLat());
	        longitude = String.valueOf( marker.getLatlng().getLng());
	        RequestContext.getCurrentInstance().update("upload_newhome_form:roomlatitude");
	        RequestContext.getCurrentInstance().update("upload_newhome_form:roomlongitude");
	    }
	  
	    //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));  
	}
	
	// -----------------------------------------------------------------------------------------------------------------------

	private UploadedFile uploadedFile;
	
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}



	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFrontPhoto() {
		return frontPhoto;
	}


	public void setFrontPhoto(String frontPhoto) {
		this.frontPhoto = frontPhoto;
	}


	public String getCostPerNight() {
		return costPerNight;
	}


	public void setCostPerNight(String costPerNight) {
		this.costPerNight = costPerNight;
	}


	public int getReviewsNum() {
		return reviewsNum;
	}


	public void setReviewsNum(int reviewsNum) {
		this.reviewsNum = reviewsNum;
	}


	public double getAverageRatingStars() {
		return averageRatingStars;
	}


	public void setAverageRatingStars(double averageRatingStars) {
		this.averageRatingStars = averageRatingStars;
	}


	public String getBedsNumber() {
		return bedsNumber;
	}


	public void setBedsNumber(String bedsNumber) {
		this.bedsNumber = bedsNumber;
	}


	public String getBathroomsNumber() {
		return bathroomsNumber;
	}


	public void setBathroomsNumber(String bathroomsNumber) {
		this.bathroomsNumber = bathroomsNumber;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public String getBedroomsNumber() {
		return bedroomsNumber;
	}


	public void setBedroomsNumber(String bedroomsNumber) {
		this.bedroomsNumber = bedroomsNumber;
	}


	public String getLivingRoomNumber() {
		return livingRoomNumber;
	}


	public void setLivingRoomNumber(String livingRoomNumber) {
		this.livingRoomNumber = livingRoomNumber;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) 
	{
		this.description = description;
		
		this.name = this.description;
		this.name = this.name.substring(this.name.indexOf("#") + 1);
		//this.name = this.name.substring(0, this.name.indexOf("#"));
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}


	public boolean isSmokingRule() {
		return smokingRule;
	}


	public void setSmokingRule(boolean smokingRule) {
		this.smokingRule = smokingRule;
	}


	public boolean isPetsRule() {
		return petsRule;
	}


	public void setPetsRule(boolean petsRule) {
		this.petsRule = petsRule;
	}


	public boolean isEventsRule() {
		return eventsRule;
	}


	public void setEventsRule(boolean eventsRule) {
		this.eventsRule = eventsRule;
	}


	public String getMinHostingDays() {
		return minHostingDays;
	}


	public void setMinHostingDays(String minHostingDays) {
		this.minHostingDays = minHostingDays;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getNeighborhood() {
		return neighborhood;
	}


	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}


	public String getPublicTransit() {
		return publicTransit;
	}


	public void setPublicTransit(String publicTransit) {
		this.publicTransit = publicTransit;
	}


	public String getMaxGuestsNumber() {
		return maxGuestsNumber;
	}


	public void setMaxGuestsNumber(String maxGuestsNumber) {
		this.maxGuestsNumber = maxGuestsNumber;
	}


	public String getMinCost() {
		return minCost;
	}


	public void setMinCost(String minCost) {
		this.minCost = minCost;
	}


	public String getExtraPeopleCost() {
		return extraPeopleCost;
	}


	public void setExtraPeopleCost(String extraPeopleCost) {
		this.extraPeopleCost = extraPeopleCost;
	}


	public boolean isWifiAmenity() {
		return wifiAmenity;
	}


	public void setWifiAmenity(boolean wifiAmenity) {
		this.wifiAmenity = wifiAmenity;
	}


	public boolean isCoolingAmenity() {
		return coolingAmenity;
	}


	public void setCoolingAmenity(boolean coolingAmenity) {
		this.coolingAmenity = coolingAmenity;
	}


	public boolean isHeatingAmenity() {
		return heatingAmenity;
	}


	public void setHeatingAmenity(boolean heatingAmenity) {
		this.heatingAmenity = heatingAmenity;
	}


	public boolean isKitchenAmenity() {
		return kitchenAmenity;
	}


	public void setKitchenAmenity(boolean kitchenAmenity) {
		this.kitchenAmenity = kitchenAmenity;
	}


	public boolean isTVAmenity() {
		return TVAmenity;
	}


	public void setTVAmenity(boolean tVAmenity) {
		TVAmenity = tVAmenity;
	}


	public boolean isParkingAmenity() {
		return parkingAmenity;
	}


	public void setParkingAmenity(boolean parkingAmenity) {
		this.parkingAmenity = parkingAmenity;
	}


	public boolean isElevatorAmenity() {
		return elevatorAmenity;
	}


	public void setElevatorAmenity(boolean elevatorAmenity) {
		this.elevatorAmenity = elevatorAmenity;
	}


	public User getHostOwner() {
		return hostOwner;
	}


	public void setHostOwner(User hostOwner) {
		this.hostOwner = hostOwner;
	}


	public Hostingroom getCurrentHostingroom() {
		return currentHostingroom;
	}


	public void setCurrentHostingroom(Hostingroom currentHostingroom) {
		this.currentHostingroom = currentHostingroom;
	}
	
	public String seeRoomDetails()
	{
		return "/restricted/host_room_details?faces-redirect=true";
	}
	
	public String uploadNewHome()
	{
		return "/restricted/host/host_upload_new_home?faces-redirect=true";
	}


	/**
	 * @return the selectedAmenities
	 */
	public String[] getSelectedAmenities() {
		return selectedAmenities;
	}


	/**
	 * @param selectedAmenities the selectedAmenities to set
	 */
	public void setSelectedAmenities(String[] selectedAmenities) {
		this.selectedAmenities = selectedAmenities;
	}


	/**
	 * @return the selectedPublicTransit
	 */
	public String[] getSelectedPublicTransit() {
		return selectedPublicTransit;
	}


	/**
	 * @param selectedPublicTransit the selectedPublicTransit to set
	 */
	public void setSelectedPublicTransit(String[] selectedPublicTransit) {
		this.selectedPublicTransit = selectedPublicTransit;
	}

	/**
	 * @return the int_averageratingstars
	 */
	public int getInt_averageratingstars() {
		return int_averageratingstars;
	}

	/**
	 * @param int_averageratingstars the int_averageratingstars to set
	 */
	public void setInt_averageratingstars(int int_averageratingstars) {
		this.int_averageratingstars = int_averageratingstars;
	}

	/**
	 * @return the hostCheckIn
	 */
	public String getHostCheckIn() {
		return hostCheckIn;
	}

	/**
	 * @param hostCheckIn the hostCheckIn to set
	 */
	public void setHostCheckIn(String hostCheckIn) {
		this.hostCheckIn = hostCheckIn;
	}

	/**
	 * @return the hostCheckOut
	 */
	public String getHostCheckOut() {
		return hostCheckOut;
	}

	/**
	 * @param hostCheckOut the hostCheckOut to set
	 */
	public void setHostCheckOut(String hostCheckOut) {
		this.hostCheckOut = hostCheckOut;
	}
	
	
	public String uploadHome(String username) throws ParseException
	{
		HostingRoomDAO hrdao = new HostingRoomDAO();
		
		Hostingroom hr = new Hostingroom();
		
		int code = hrdao.getAllRooms().size()+1;
		hr.setId(code);
		hr.setAddress(address);
		hr.setArea(Double.valueOf(area));
		hr.setAverageRatingStars(0.0);
		hr.setBathroomsNumber(Integer.parseInt(bathroomsNumber));
		hr.setBedroomsNumber(Integer.parseInt(bedroomsNumber));
		hr.setBedsNumber(Integer.parseInt(bedsNumber));
		hr.setDescription(name+"#"+description);
		hr.setCostPerNight(Double.parseDouble(costPerNight));
		this.frontPhoto="/resources/images/houseicon.png";
		hr.setFrontPhoto(frontPhoto);
		hr.setExtraPeopleCost(Double.parseDouble(extraPeopleCost));
		hr.setLatitude(BigDecimal.valueOf(Double.parseDouble(latitude)));
		hr.setLongitude(BigDecimal.valueOf(Double.parseDouble(longitude)));
		hr.setMaxGuestsNumber(Integer.parseInt(maxGuestsNumber));
		hr.setLivingRoomNumber(Integer.parseInt(livingRoomNumber));
		hr.setMinCost(Double.parseDouble(minCost));
		hr.setMinHostingDays(Integer.parseInt(minHostingDays));
		hr.setNeighborhood(neighborhood);
		hr.setReviewsNum(0);
		hr.setRoomType(roomType);
		hr.setPetsRule(petsRule);
		hr.setEventsRule(eventsRule);
		hr.setSmokingRule(smokingRule);
		
		String publicTransitSelected = "";
		for(String pt : this.selectedPublicTransit)
		{
			publicTransitSelected += pt +",";
		}
		publicTransitSelected = publicTransitSelected.substring(0, publicTransitSelected.length() - 1);
		hr.setPublicTransit(publicTransitSelected);

		hr.setWifiAmenity(false);
		hr.setCoolingAmenity(false);
		hr.setHeatingAmenity(false);
		hr.setKitchenAmenity(false);
		hr.setTVAmenity(false);
		hr.setParkingAmenity(false);
		hr.setElevatorAmenity(false);
		
		for(String a : this.selectedAmenities)
		{
			if(a.equals("Wifi"))
			{
				hr.setWifiAmenity(true);
			}
			else if(a.equals("Cooling"))
			{
				hr.setCoolingAmenity(true);
			}
			else if(a.equals("Heating"))
			{
				hr.setHeatingAmenity(true);
			}
			else if(a.equals("Kitchen"))
			{
				hr.setKitchenAmenity(true);
			}
			else if(a.equals("TV"))
			{
				hr.setTVAmenity(true);
			}
			else if(a.equals("Parking"))
			{
				hr.setParkingAmenity(true);
			}
			else if(a.equals("Elevator"))
			{
				hr.setElevatorAmenity(true);
			}
			
			UserDAO userdao = new UserDAO();
			
			hr.setUser(userdao.getUser(username));
			
			String retMessage = hrdao.insertNewHostingroom(hr); 

			if (retMessage.equals("ok")) 
			{
				Hostingroom newroom = hrdao.findHostingRoom(code);
				
				// Insert available hosting period for this house
				AvailablehostingperiodDAO ahpdao = new AvailablehostingperiodDAO();
				
				Availablehostingperiod ahp = new Availablehostingperiod();
				
				ahp.setId(ahpdao.getAllPeriods().size()+1);
			
			
				DateFormat targetDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				
				Date i_checkInDate = format.parse(this.hostCheckIn);
				Date i_checkOutDate = format.parse(this.hostCheckOut);
				
				Date checkInDate = targetDateFormat.parse( targetDateFormat.format(i_checkInDate) );
				Date checkOutDate = targetDateFormat.parse( targetDateFormat.format(i_checkOutDate) );
				
				ahp.setFromDate(checkInDate);
				ahp.setToDate(checkOutDate);
				ahp.setHostingroom(newroom);
				
				retMessage = ahpdao.insertNewAvailableHostingPeriod(ahp);
				if (retMessage.equals("ok")) 
				{
					return "/restricted/host/mylistings?faces-redirect=true";
				}
				else
				{
					return null;
				}
						
			} 
			else 
			{	
				return null;
			}


		}
	
		return null;
		//INSERT INTO hostingroom (FrontPhoto, CostPerNight, ReviewsNum, AverageRatingStars, BedsNumber, BathroomsNumber, RoomType, BedroomsNumber, LivingRoomNumber, Area, Description, SmokingRule, PetsRule, EventsRule, MinHostingDays, Latitude, Longitude, Address, Neighborhood, PublicTransit, MaxGuestsNumber, MinCost, ExtraPeopleCost, WifiAmenity, CoolingAmenity, HeatingAmenity, KitchenAmenity, TVAmenity, ParkingAmenity, ElevatorAmenity, HostId_FK) VALUES ('blabk',50.5, 13, 3.45, 3, 2, 'Entire Room/Apartment', 2, 1, 66.6,' bla bla bla!', 0, 0, 0, 4, 23.5, 35.6, 'address 22', 'neighbothood', 'by bus', 5, 300.54, 50.34, 1, 0, 1, 0, 1, 0, 1, 232);
		//INSERT INTO availablehostingperiods ( RoomId_Fk, FromDate, ToDate) VALUES ( 4, '2016-01-01 00:00:00', '2020-01-01 00:00:00');
	}
	
	
	public String insertBooking(Hostingroom roomId, User guestId) 
	{
		BookingDAO bookingDB = new BookingDAO();
		Booking nbooking = new Booking();
		String retMessage;
		java.sql.Timestamp bookindDate = new java.sql.Timestamp(new java.util.Date().getTime());

		DateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date i_checkInDate = null;
		Date i_checkOutDate = null;

		Date checkInDate = null;
		Date checkOutDate = null;


		try {
			i_checkInDate = format.parse(checkInChosen);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			i_checkOutDate = format.parse(checkOutChosen);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			checkInDate = targetDateFormat.parse(targetDateFormat.format(i_checkInDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			checkOutDate = targetDateFormat.parse(targetDateFormat.format(i_checkOutDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		nbooking.setId(bookingDB.getAllBookings().size() + 1);

		nbooking.setHostingroom(roomId);
		nbooking.setUser(guestId);
		nbooking.setCheckInDate(checkInDate);
		nbooking.setCheckOutDate(checkOutDate);
		nbooking.setBookingDate(bookindDate);

		retMessage = bookingDB.insertBooking(nbooking);

		if(retMessage.equals("ok")) {
			return "/index?faces-redirect=true";
		} else {
			return retMessage;
		}

	}
		
}
