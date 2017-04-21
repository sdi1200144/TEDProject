package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the bookings database table.
 * 
 */
@Entity
@Table(name="bookings")


@NamedQueries({
	@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b"),
	@NamedQuery(name="Booking.findById", query="SELECT b FROM Booking b WHERE b.id = :id"),
	@NamedQuery(name="Booking.findBybookingDate", query="SELECT b FROM Booking b WHERE b.bookingDate = :bookingDate"),
	@NamedQuery(name="Booking.findBycheckInDate", query="SELECT b FROM Booking b WHERE b.checkInDate = :checkInDate"),
	@NamedQuery(name="Booking.findBycheckOutDate", query="SELECT b FROM Booking b WHERE b.checkOutDate = :checkOutDate"),
	@NamedQuery(name="Booking.findByUser", query="SELECT b FROM Booking b WHERE b.user = :user"),
	@NamedQuery(name="Booking.findByhostingroom", query="SELECT b FROM Booking b WHERE b.hostingroom = :hostingroom")
    })


public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
    @Column(name = "Id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = false)
    @Column(name = "BookingDate")
	private Date bookingDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = false)
    @Column(name = "CheckInDate")
	private Date checkInDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = false)
    @Column(name = "CheckOutDate")
	private Date checkOutDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="GuestId_FK")
	private User user;

	//bi-directional many-to-one association to Hostingroom
	@ManyToOne
	@JoinColumn(name="RoomId_FK")
	private Hostingroom hostingroom;

	public Booking() 
	{
	}
	
	public Booking(int id_)
	{
		this.id = id_;
	}
	
	public Booking(int id_, Date checkInDate_, Date checkOutDate_, Date bookingDate_, User user_, Hostingroom hostingRoom_)
	{
		this.id = id_;
		this.checkInDate = checkInDate_;
		this.checkOutDate = checkOutDate_;
		this.bookingDate = bookingDate_;
		this.user = user_;
		this.hostingroom = hostingRoom_;
	}

	public int getId() 
	{
		return this.id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public Date getBookingDate() 
	{
		return this.bookingDate;
	}

	public void setBookingDate(Date bookingDate)
	{
		this.bookingDate = bookingDate;
	}

	public Date getCheckInDate() 
	{
		return this.checkInDate;
	}

	public void setCheckInDate(Date checkInDate) 
	{
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() 
	{
		return this.checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate)
	{
		this.checkOutDate = checkOutDate;
	}

	public User getUser() 
	{
		return this.user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}

	public Hostingroom getHostingroom() 
	{
		return this.hostingroom;
	}

	public void setHostingroom(Hostingroom hostingroom)
	{
		this.hostingroom = hostingroom;
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
		if (!(object instanceof Booking)) {
			return false;
		}
		Booking other = (Booking) object;

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
		return "entities.[ Booking id=" + id + " ]";
	}

}