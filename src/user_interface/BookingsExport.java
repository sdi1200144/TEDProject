package user_interface;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BOOKINGS")
public class BookingsExport {
	List<BookingExport> bookings;

	@XmlElement(name = "BOOKING")
	public void setBookings(List<BookingExport> bookings) {
		this.bookings = bookings;
	}

	public void add(BookingExport booking) {
		if (this.bookings == null) {
			this.bookings = new ArrayList<BookingExport>();
		}
		this.bookings.add(booking);

	}

	public List<BookingExport> getBookings() {
		return bookings;
	}

}
