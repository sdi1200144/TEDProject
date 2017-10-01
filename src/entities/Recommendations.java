package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the recommendations database table.
 * 
 */
@Entity
@Table(name="Recommendations")
@XmlRootElement
@NamedQuery(name="Recommendations.findAll", query="SELECT r FROM Recommendations r")
public class Recommendations implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	private double MaxCost;

	private double EntireRoomType;
	private double SharedRoomType;
	private double PrivateRoomType;
	
	
	private double Wifi;
	private double Cooling;
	private double Heating;
	private double Kitchen;
	private double TV;
	private double Parking;
	private double Elevator;
	
	private boolean Wifi_flag;
	private boolean Cooling_flag;
	private boolean Heating_flag;
	private boolean Kitchen_flag;
	private boolean TV_flag;
	private boolean Parking_flag;
	private boolean Elevator_flag;
	private boolean EntireRoomType_flag;
	private boolean SharedRoomType_flag;
	private boolean PrivateRoomType_flag;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserId_FK")
	private User user;
	
	
	public Recommendations()
	{
		
	}
	
	 
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the maxCost
	 */
	public double getMaxCost() {
		return MaxCost;
	}

	/**
	 * @param maxCost the maxCost to set
	 */
	public void setMaxCost(double maxCost) {
		MaxCost = maxCost;
	}

	/**
	 * @return the entireRoomType
	 */
	public double getEntireRoomType() {
		return EntireRoomType;
	}

	/**
	 * @param entireRoomType the entireRoomType to set
	 */
	public void setEntireRoomType(double entireRoomType) {
		EntireRoomType = entireRoomType;
	}

	/**
	 * @return the sharedRoomType
	 */
	public double getSharedRoomType() {
		return SharedRoomType;
	}

	/**
	 * @param sharedRoomType the sharedRoomType to set
	 */
	public void setSharedRoomType(double sharedRoomType) {
		SharedRoomType = sharedRoomType;
	}

	/**
	 * @return the privateRoomType
	 */
	public double getPrivateRoomType() {
		return PrivateRoomType;
	}

	/**
	 * @param privateRoomType the privateRoomType to set
	 */
	public void setPrivateRoomType(double privateRoomType) {
		PrivateRoomType = privateRoomType;
	}

	/**
	 * @return the wifi
	 */
	public double getWifi() {
		return Wifi;
	}

	/**
	 * @param wifi the wifi to set
	 */
	public void setWifi(double wifi) {
		Wifi = wifi;
	}

	/**
	 * @return the cooling
	 */
	public double getCooling() {
		return Cooling;
	}

	/**
	 * @param cooling the cooling to set
	 */
	public void setCooling(double cooling) {
		Cooling = cooling;
	}

	/**
	 * @return the heating
	 */
	public double getHeating() {
		return Heating;
	}

	/**
	 * @param heating the heating to set
	 */
	public void setHeating(double heating) {
		Heating = heating;
	}

	/**
	 * @return the kitchen
	 */
	public double getKitchen() {
		return Kitchen;
	}

	/**
	 * @param kitchen the kitchen to set
	 */
	public void setKitchen(double kitchen) {
		Kitchen = kitchen;
	}

	/**
	 * @return the tV
	 */
	public double getTV() {
		return TV;
	}

	/**
	 * @param tV the tV to set
	 */
	public void setTV(double tV) {
		TV = tV;
	}

	/**
	 * @return the parking
	 */
	public double getParking() {
		return Parking;
	}

	/**
	 * @param parking the parking to set
	 */
	public void setParking(double parking) {
		Parking = parking;
	}

	/**
	 * @return the elevator
	 */
	public double getElevator() {
		return Elevator;
	}

	/**
	 * @param elevator the elevator to set
	 */
	public void setElevator(double elevator) {
		Elevator = elevator;
	}

	/**
	 * @return the wifi_flag
	 */
	public boolean isWifi_flag() {
		return Wifi_flag;
	}

	/**
	 * @param wifi_flag the wifi_flag to set
	 */
	public void setWifi_flag(boolean wifi_flag) {
		Wifi_flag = wifi_flag;
	}

	/**
	 * @return the cooling_flag
	 */
	public boolean isCooling_flag() {
		return Cooling_flag;
	}

	/**
	 * @param cooling_flag the cooling_flag to set
	 */
	public void setCooling_flag(boolean cooling_flag) {
		Cooling_flag = cooling_flag;
	}

	/**
	 * @return the heating_flag
	 */
	public boolean isHeating_flag() {
		return Heating_flag;
	}

	/**
	 * @param heating_flag the heating_flag to set
	 */
	public void setHeating_flag(boolean heating_flag) {
		Heating_flag = heating_flag;
	}

	/**
	 * @return the kitchen_flag
	 */
	public boolean isKitchen_flag() {
		return Kitchen_flag;
	}

	/**
	 * @param kitchen_flag the kitchen_flag to set
	 */
	public void setKitchen_flag(boolean kitchen_flag) {
		Kitchen_flag = kitchen_flag;
	}

	/**
	 * @return the tV_flag
	 */
	public boolean isTV_flag() {
		return TV_flag;
	}

	/**
	 * @param tV_flag the tV_flag to set
	 */
	public void setTV_flag(boolean tV_flag) {
		TV_flag = tV_flag;
	}

	/**
	 * @return the parking_flag
	 */
	public boolean isParking_flag() {
		return Parking_flag;
	}

	/**
	 * @param parking_flag the parking_flag to set
	 */
	public void setParking_flag(boolean parking_flag) {
		Parking_flag = parking_flag;
	}

	/**
	 * @return the elevator_flag
	 */
	public boolean isElevator_flag() {
		return Elevator_flag;
	}

	/**
	 * @param elevator_flag the elevator_flag to set
	 */
	public void setElevator_flag(boolean elevator_flag) {
		Elevator_flag = elevator_flag;
	}

	/**
	 * @return the entireRoomType_flag
	 */
	public boolean isEntireRoomType_flag() {
		return EntireRoomType_flag;
	}

	/**
	 * @param entireRoomType_flag the entireRoomType_flag to set
	 */
	public void setEntireRoomType_flag(boolean entireRoomType_flag) {
		EntireRoomType_flag = entireRoomType_flag;
	}

	/**
	 * @return the sharedRoomType_flag
	 */
	public boolean isSharedRoomType_flag() {
		return SharedRoomType_flag;
	}

	/**
	 * @param sharedRoomType_flag the sharedRoomType_flag to set
	 */
	public void setSharedRoomType_flag(boolean sharedRoomType_flag) {
		SharedRoomType_flag = sharedRoomType_flag;
	}

	/**
	 * @return the privateRoomType_flag
	 */
	public boolean isPrivateRoomType_flag() {
		return PrivateRoomType_flag;
	}

	/**
	 * @param privateRoomType_flag the privateRoomType_flag to set
	 */
	public void setPrivateRoomType_flag(boolean privateRoomType_flag) {
		PrivateRoomType_flag = privateRoomType_flag;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Recommendations other = (Recommendations) object;

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
		return "entities.[ Recommendations id=" + id + " ]";
	}
	  
	  
	  

}
