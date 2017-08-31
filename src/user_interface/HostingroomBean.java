package user_interface;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import entities.Hostingroom;
import entities.User;


@ManagedBean(name = "room")
@ViewScoped
public class HostingroomBean 
{
	private int id;
	private String frontPhoto;
	private double costPerNight;
	private int reviewsNum;
	private double averageRatingStars;
	private int bedsNumber;
	private int bathroomsNumber;
	private String roomType;
	private int bedroomsNumber;
	private int livingRoomNumber;
	private double area;
	private String name;
	private String description;
	private boolean smokingRule;
	private boolean petsRule;
	private boolean eventsRule;
	private int minHostingDays;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String address;
	private String neighborhood;
	private String publicTransit;
	private int maxGuestsNumber;
	private double minCost;
	private double extraPeopleCost;
	private boolean wifiAmenity;
	private boolean coolingAmenity;
	private boolean heatingAmenity;
	private boolean kitchenAmenity;
	private boolean TVAmenity;
	private boolean parkingAmenity;
	private boolean elevatorAmenity;
	private User hostOwner;

	private String[] selectedAmenities;
	private String[] selectedPublicTransit;

	private Hostingroom currentHostingroom;

	// --------------------------------------------------------------------------------------------------
	private final MapModel draggableModel = new DefaultMapModel();
	private Marker marker;


	@PostConstruct
	public void init() 
	{
		//draggableModel = new DefaultMapModel();

		//Shared coordinates
		LatLng coord1 = new LatLng(36.879466, 30.667648);

		//Draggable
		draggableModel.addOverlay(new Marker(coord1, "Your new home"));

		for(Marker premarker : draggableModel.getMarkers()) 
		{
			premarker.setDraggable(true);
		}
	}

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
	        System.out.println("Updated marker location: " + "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng());
	    }
	  
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));  
	}
	
	// -----------------------------------------------------------------------------------------------------------------------

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


	public double getCostPerNight() {
		return costPerNight;
	}


	public void setCostPerNight(double costPerNight) {
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


	public int getBedsNumber() {
		return bedsNumber;
	}


	public void setBedsNumber(int bedsNumber) {
		this.bedsNumber = bedsNumber;
	}


	public int getBathroomsNumber() {
		return bathroomsNumber;
	}


	public void setBathroomsNumber(int bathroomsNumber) {
		this.bathroomsNumber = bathroomsNumber;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public int getBedroomsNumber() {
		return bedroomsNumber;
	}


	public void setBedroomsNumber(int bedroomsNumber) {
		this.bedroomsNumber = bedroomsNumber;
	}


	public int getLivingRoomNumber() {
		return livingRoomNumber;
	}


	public void setLivingRoomNumber(int livingRoomNumber) {
		this.livingRoomNumber = livingRoomNumber;
	}


	public double getArea() {
		return area;
	}


	public void setArea(double area) {
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
		this.name = this.name.substring(0, this.name.indexOf("#"));
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


	public int getMinHostingDays() {
		return minHostingDays;
	}


	public void setMinHostingDays(int minHostingDays) {
		this.minHostingDays = minHostingDays;
	}


	public BigDecimal getLatitude() {
		return latitude;
	}


	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}


	public BigDecimal getLongitude() {
		return longitude;
	}


	public void setLongitude(BigDecimal longitude) {
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


	public int getMaxGuestsNumber() {
		return maxGuestsNumber;
	}


	public void setMaxGuestsNumber(int maxGuestsNumber) {
		this.maxGuestsNumber = maxGuestsNumber;
	}


	public double getMinCost() {
		return minCost;
	}


	public void setMinCost(double minCost) {
		this.minCost = minCost;
	}


	public double getExtraPeopleCost() {
		return extraPeopleCost;
	}


	public void setExtraPeopleCost(double extraPeopleCost) {
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
		return "/restricted/host_upload_new_home?faces-redirect=true";
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
		
}
