package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the hostingroom database table.
 * 
 */
@Entity
@NamedQuery(name="Hostingroom.findAll", query="SELECT h FROM Hostingroom h")
public class Hostingroom implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	private double area;

	private double averageRatingStars;

	private int bathroomsNumber;

	private int bedroomsNumber;

	private int bedsNumber;

	private byte coolingAmenity;

	private double costPerNight;

	@Lob
	private String description;

	private byte elevatorAmenity;

	private byte eventsRule;

	private double extraPeopleCost;

	private String frontPhoto;

	private byte heatingAmenity;

	private byte kitchenAmenity;

	private BigDecimal latitude;

	private int livingRoomNumber;

	private BigDecimal longitude;

	private int maxGuestsNumber;

	private double minCost;

	private int minHostingDays;

	private String neighborhood;

	private byte parkingAmenity;

	private byte petsRule;

	private String publicTransit;

	private int reviewsNum;

	private String roomType;

	private byte smokingRule;

	private byte TVAmenity;

	private byte wifiAmenity;

	//bi-directional many-to-one association to Availablehostingperiod
	@OneToMany(mappedBy="hostingroom")
	private List<Availablehostingperiod> availablehostingperiods;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="hostingroom")
	private List<Booking> bookings;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="HostId_FK")
	private User user;

	//bi-directional many-to-one association to Roomphoto
	@OneToMany(mappedBy="hostingroom")
	private List<Roomphoto> roomphotos;

	//bi-directional many-to-one association to Searchhistory
	@OneToMany(mappedBy="hostingroom")
	private List<Searchhistory> searchhistories;

	public Hostingroom() {
	}
	
	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getArea() {
		return this.area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getAverageRatingStars() {
		return this.averageRatingStars;
	}

	public void setAverageRatingStars(double averageRatingStars) {
		this.averageRatingStars = averageRatingStars;
	}

	public int getBathroomsNumber() {
		return this.bathroomsNumber;
	}

	public void setBathroomsNumber(int bathroomsNumber) {
		this.bathroomsNumber = bathroomsNumber;
	}

	public int getBedroomsNumber() {
		return this.bedroomsNumber;
	}

	public void setBedroomsNumber(int bedroomsNumber) {
		this.bedroomsNumber = bedroomsNumber;
	}

	public int getBedsNumber() {
		return this.bedsNumber;
	}

	public void setBedsNumber(int bedsNumber) {
		this.bedsNumber = bedsNumber;
	}

	public byte getCoolingAmenity() {
		return this.coolingAmenity;
	}

	public void setCoolingAmenity(byte coolingAmenity) {
		this.coolingAmenity = coolingAmenity;
	}

	public double getCostPerNight() {
		return this.costPerNight;
	}

	public void setCostPerNight(double costPerNight) {
		this.costPerNight = costPerNight;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getElevatorAmenity() {
		return this.elevatorAmenity;
	}

	public void setElevatorAmenity(byte elevatorAmenity) {
		this.elevatorAmenity = elevatorAmenity;
	}

	public byte getEventsRule() {
		return this.eventsRule;
	}

	public void setEventsRule(byte eventsRule) {
		this.eventsRule = eventsRule;
	}

	public double getExtraPeopleCost() {
		return this.extraPeopleCost;
	}

	public void setExtraPeopleCost(double extraPeopleCost) {
		this.extraPeopleCost = extraPeopleCost;
	}

	public String getFrontPhoto() {
		return this.frontPhoto;
	}

	public void setFrontPhoto(String frontPhoto) {
		this.frontPhoto = frontPhoto;
	}

	public byte getHeatingAmenity() {
		return this.heatingAmenity;
	}

	public void setHeatingAmenity(byte heatingAmenity) {
		this.heatingAmenity = heatingAmenity;
	}

	public byte getKitchenAmenity() {
		return this.kitchenAmenity;
	}

	public void setKitchenAmenity(byte kitchenAmenity) {
		this.kitchenAmenity = kitchenAmenity;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public int getLivingRoomNumber() {
		return this.livingRoomNumber;
	}

	public void setLivingRoomNumber(int livingRoomNumber) {
		this.livingRoomNumber = livingRoomNumber;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public int getMaxGuestsNumber() {
		return this.maxGuestsNumber;
	}

	public void setMaxGuestsNumber(int maxGuestsNumber) {
		this.maxGuestsNumber = maxGuestsNumber;
	}

	public double getMinCost() {
		return this.minCost;
	}

	public void setMinCost(double minCost) {
		this.minCost = minCost;
	}

	public int getMinHostingDays() {
		return this.minHostingDays;
	}

	public void setMinHostingDays(int minHostingDays) {
		this.minHostingDays = minHostingDays;
	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public byte getParkingAmenity() {
		return this.parkingAmenity;
	}

	public void setParkingAmenity(byte parkingAmenity) {
		this.parkingAmenity = parkingAmenity;
	}

	public byte getPetsRule() {
		return this.petsRule;
	}

	public void setPetsRule(byte petsRule) {
		this.petsRule = petsRule;
	}

	public String getPublicTransit() {
		return this.publicTransit;
	}

	public void setPublicTransit(String publicTransit) {
		this.publicTransit = publicTransit;
	}

	public int getReviewsNum() {
		return this.reviewsNum;
	}

	public void setReviewsNum(int reviewsNum) {
		this.reviewsNum = reviewsNum;
	}

	public String getRoomType() {
		return this.roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public byte getSmokingRule() {
		return this.smokingRule;
	}

	public void setSmokingRule(byte smokingRule) {
		this.smokingRule = smokingRule;
	}

	public byte getTVAmenity() {
		return this.TVAmenity;
	}

	public void setTVAmenity(byte TVAmenity) {
		this.TVAmenity = TVAmenity;
	}

	public byte getWifiAmenity() {
		return this.wifiAmenity;
	}

	public void setWifiAmenity(byte wifiAmenity) {
		this.wifiAmenity = wifiAmenity;
	}

	public List<Availablehostingperiod> getAvailablehostingperiods() {
		return this.availablehostingperiods;
	}

	public void setAvailablehostingperiods(List<Availablehostingperiod> availablehostingperiods) {
		this.availablehostingperiods = availablehostingperiods;
	}

	public Availablehostingperiod addAvailablehostingperiod(Availablehostingperiod availablehostingperiod) {
		getAvailablehostingperiods().add(availablehostingperiod);
		availablehostingperiod.setHostingroom(this);

		return availablehostingperiod;
	}

	public Availablehostingperiod removeAvailablehostingperiod(Availablehostingperiod availablehostingperiod) {
		getAvailablehostingperiods().remove(availablehostingperiod);
		availablehostingperiod.setHostingroom(null);

		return availablehostingperiod;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setHostingroom(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setHostingroom(null);

		return booking;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Roomphoto> getRoomphotos() {
		return this.roomphotos;
	}

	public void setRoomphotos(List<Roomphoto> roomphotos) {
		this.roomphotos = roomphotos;
	}

	public Roomphoto addRoomphoto(Roomphoto roomphoto) {
		getRoomphotos().add(roomphoto);
		roomphoto.setHostingroom(this);

		return roomphoto;
	}

	public Roomphoto removeRoomphoto(Roomphoto roomphoto) {
		getRoomphotos().remove(roomphoto);
		roomphoto.setHostingroom(null);

		return roomphoto;
	}

	public List<Searchhistory> getSearchhistories() {
		return this.searchhistories;
	}

	public void setSearchhistories(List<Searchhistory> searchhistories) {
		this.searchhistories = searchhistories;
	}

	public Searchhistory addSearchhistory(Searchhistory searchhistory) {
		getSearchhistories().add(searchhistory);
		searchhistory.setHostingroom(this);

		return searchhistory;
	}

	public Searchhistory removeSearchhistory(Searchhistory searchhistory) {
		getSearchhistories().remove(searchhistory);
		searchhistory.setHostingroom(null);

		return searchhistory;
	}
	
	
	@Override
	public int hashCode() 
	{
		Integer id = this.id;
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) 
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Hostingroom)) {
			return false;
		}
		Hostingroom other = (Hostingroom) object;

		Integer this_id = this.id;
		Integer other_id = other.id;

		if ((this_id == null && other_id != null) || (this_id != null && !this_id.equals(other_id))) 
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entities.[ Hostingroom id=" + id + " ]";
	}

}