package entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */

@Entity
@Table(name="Users")
@XmlRootElement

@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByMobileNumber", query = "SELECT u FROM User u WHERE u.mobileNumber = :mobileNumber"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findByPhoto", query = "SELECT u FROM User u WHERE u.photo = :photo"),
    @NamedQuery(name = "User.findByIsConfirmed", query = "SELECT u FROM User u WHERE u.isConfirmed = :isConfirmed")})
   


public class User implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
    @Column(name = "Id")
	private int id;
	
	@Basic(optional = false)
    @Column(name = "Username")
	private String username;
	
	@Basic(optional = false)
    @Column(name = "Password")
	private String password;
	
	@Column(name = "FirstName")
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Basic(optional = false)
    @Column(name = "Email")
	private String email;
	
	@Column(name = "MobileNumber")
	private String mobileNumber;
	
	@Basic(optional = false)
    @Column(name = "Role")
	private String role;
	
	@Column(name = "Photo")
	private String photo;
	
	@Basic(optional = false)
    @Column(name = "isConfirmed")
	private boolean isConfirmed;

	
	//bi-directional many-to-one association to Booking (GuestId Foreign Key)
	@OneToMany(mappedBy="user")
	private List<Booking> bookings;

	//bi-directional many-to-one association to Hostingroom (HostId Foreign Key)
	@OneToMany(mappedBy="user")
	private List<Hostingroom> hostingrooms;

	//bi-directional many-to-one association to Message (Sender_FK)
	@OneToMany(mappedBy="user1")
	private List<Message> messages1;

	//bi-directional many-to-one association to Message (Recipient_FK)
	@OneToMany(mappedBy="user2")
	private List<Message> messages2;

	//bi-directional many-to-one association to Reviewsforroom (FromUserId_FK)
	@OneToMany(mappedBy="user1")
	private List<Reviewsforroom> reviewsforrooms1;

	//bi-directional many-to-one association to Reviewsforroom (ToUserId_FK)
	@OneToMany(mappedBy="user2")
	private List<Reviewsforroom> reviewsforrooms2;

	//bi-directional many-to-one association to Searchhistory (UserId_FK)
	@OneToMany(mappedBy="user")
	private List<Searchhistory> searchhistories;

	
	
	
	//Constructors
	public User() 
	{
	}
	
	public User(int id_)
	{
		this.id = id_;
	}
	
	public User(int id_,String username_, String password_, String email_, String role_, boolean isConfirmed_  )
	{
		this.id = id_;
		this.username = username_;
		this.password = password_;
		this.email = email_;
		this.role = role_;
		this.isConfirmed = isConfirmed_;	
	}
	
	public User(int id_,String username_, String password_, String firstname_, String lastname_, String email_, String mobilenumber_,String role_, String photo_, boolean isConfirmed_)
	{
		this.id = id_;
		this.username = username_;
		this.password = password_;
		this.firstName = firstname_;
		this.lastName = lastname_;
		this.email = email_;
		this.mobileNumber = mobilenumber_;
		this.role = role_;
		this.photo = photo_;
		this.isConfirmed = isConfirmed_;
	}
	


    // Getters and setters
	public int getId() 
	{
		return this.id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getPassword() 
	{
		return this.password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	
	public String getFirstName() 
	{
		return this.firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
	public String getLastName() 
	{
		return this.lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	
	public String getEmail() 
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getMobileNumber() 
	{
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) 
	{
		this.mobileNumber = mobileNumber;
	}
	
	public String getRole()
	{
		return this.role;
	}

	public void setRole(String role) 
	{
		this.role = role;
	}
	
	public String getPhoto()
	{
		return this.photo;
	}

	public void setPhoto(String photo) 
	{
		this.photo = photo;
	}

	public boolean getIsConfirmed() 
	{
		return this.isConfirmed;
	}

	public void setIsConfirmed(boolean isConfirmed) 
	{
		this.isConfirmed = isConfirmed;
	}
	
	
	
	//---------------------------------------------------
	//(GuestId Foreign Key)
	public List<Booking> getBookings() 
	{
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) 
	{
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) 
	{
		getBookings().add(booking);
		booking.setUser(this);
		return booking;
	}

	public Booking removeBooking(Booking booking) 
	{
		getBookings().remove(booking);
		booking.setUser(null);
		return booking;
	}
	//------------------------------------------------------
	
	
	
	//------------------------------------------------------
	//(HostId Foreign Key)
	public List<Hostingroom> getHostingrooms() 
	{
		return this.hostingrooms;
	}

	public void setHostingrooms(List<Hostingroom> hostingrooms) 
	{
		this.hostingrooms = hostingrooms;
	}

	public Hostingroom addHostingroom(Hostingroom hostingroom)
	{
		getHostingrooms().add(hostingroom);
		hostingroom.setUser(this);
		return hostingroom;
	}

	public Hostingroom removeHostingroom(Hostingroom hostingroom) 
	{
		getHostingrooms().remove(hostingroom);
		hostingroom.setUser(null);
		return hostingroom;
	}
	//---------------------------------------------------------

	
	
	//------------------------------------------------------
	//(Sender_FK)
	public List<Message> getMessages1() 
	{
		return this.messages1;
	}

	public void setMessages1(List<Message> messages1) 
	{
		this.messages1 = messages1;
	}

	public Message addMessages1(Message messages1) 
	{
		getMessages1().add(messages1);
		messages1.setUser1(this);
		return messages1;
	}

	public Message removeMessages1(Message messages1) 
	{
		getMessages1().remove(messages1);
		messages1.setUser1(null);
		return messages1;
	}
	//-----------------------------------------------------

	
	//-----------------------------------------------------
	// (Recipient_FK)
	public List<Message> getMessages2() 
	{
		return this.messages2;
	}

	public void setMessages2(List<Message> messages2) 
	{
		this.messages2 = messages2;
	}

	public Message addMessages2(Message messages2) 
	{
		getMessages2().add(messages2);
		messages2.setUser2(this);
		return messages2;
	}

	public Message removeMessages2(Message messages2) 
	{
		getMessages2().remove(messages2);
		messages2.setUser2(null);
		return messages2;
	}
	//-------------------------------------------------------

	
	
	//-------------------------------------------------------
	// (FromUserId_FK)
	public List<Reviewsforroom> getReviewsforrooms1()
	{
		return this.reviewsforrooms1;
	}

	public void setReviewsforrooms1(List<Reviewsforroom> reviewsforrooms1)
	{
		this.reviewsforrooms1 = reviewsforrooms1;
	}

	public Reviewsforroom addReviewsforrooms1(Reviewsforroom reviewsforrooms1) 
	{
		getReviewsforrooms1().add(reviewsforrooms1);
		reviewsforrooms1.setUser1(this);
		return reviewsforrooms1;
	}

	public Reviewsforroom removeReviewsforrooms1(Reviewsforroom reviewsforrooms1) 
	{
		getReviewsforrooms1().remove(reviewsforrooms1);
		reviewsforrooms1.setUser1(null);
		return reviewsforrooms1;
	}
	//---------------------------------------------------------------

	
	//-------------------------------------------------------
	// (ToUserId_FK)
	public List<Reviewsforroom> getReviewsforrooms2() 
	{
		return this.reviewsforrooms2;
	}

	public void setReviewsforrooms2(List<Reviewsforroom> reviewsforrooms2) 
	{
		this.reviewsforrooms2 = reviewsforrooms2;
	}

	public Reviewsforroom addReviewsforrooms2(Reviewsforroom reviewsforrooms2)
	{
		getReviewsforrooms2().add(reviewsforrooms2);
		reviewsforrooms2.setUser2(this);
		return reviewsforrooms2;
	}

	public Reviewsforroom removeReviewsforrooms2(Reviewsforroom reviewsforrooms2) 
	{
		getReviewsforrooms2().remove(reviewsforrooms2);
		reviewsforrooms2.setUser2(null);
		return reviewsforrooms2;
	}
	//---------------------------------------------------------

	
	//----------------------------------------------------------
	// UserId_FK
	public List<Searchhistory> getSearchhistories() 
	{
		return this.searchhistories;
	}

	public void setSearchhistories(List<Searchhistory> searchhistories) 
	{
		this.searchhistories = searchhistories;
	}

	public Searchhistory addSearchhistory(Searchhistory searchhistory) 
	{
		getSearchhistories().add(searchhistory);
		searchhistory.setUser(this);
		return searchhistory;
	}

	public Searchhistory removeSearchhistory(Searchhistory searchhistory)
	{
		getSearchhistories().remove(searchhistory);
		searchhistory.setUser(null);
		return searchhistory;
	}
	
	
	 @Override
     public int hashCode() 
	 {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
     }

     @Override
     public boolean equals(Object object)
     {
        // Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User))
        {
            return false;
        }
        User other = (User) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username)))
        {
            return false;
        }
        return true;
	 }

     @Override
     public String toString() 
     {
        return "entities.User[ username=" + username + " ]";
     }
	 

}