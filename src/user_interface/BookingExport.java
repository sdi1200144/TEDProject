package user_interface;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BOOKING")
public class BookingExport {
	private int id;
	private Date bookingDate;
	private Date checkInDate;
	private Date checkOutDate;
	private int guestId;
	private int hostingroomId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getUserId() {
		return guestId;
	}
	public void setUserId(int userId) {
		this.guestId = userId;
	}
	public int getHostingroomId() {
		return hostingroomId;
	}
	public void setHostingroomId(int hostingroomId) {
		this.hostingroomId = hostingroomId;
	}

}
