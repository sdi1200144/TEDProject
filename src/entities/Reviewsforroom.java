package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reviewsforrooms database table.
 * 
 */
@Entity
@Table(name="reviewsforrooms")


@NamedQueries({
	@NamedQuery(name="Reviewsforroom.findAll", query="SELECT r FROM Reviewsforroom r"),
    @NamedQuery(name = "Reviewsforroom.findById", query = "SELECT r FROM Reviewsforroom r WHERE r.id = :id"),
    @NamedQuery(name = "Reviewsforroom.findByReviewText", query = "SELECT r FROM Reviewsforroom r WHERE r.reviewText = :reviewText"),
    @NamedQuery(name = "Reviewsforroom.findByStars", query = "SELECT r FROM Reviewsforroom r WHERE r.stars = :stars"),
    @NamedQuery(name = "Reviewsforroom.findByFromUserId_FK", query = "SELECT r FROM Reviewsforroom r WHERE r.user1 = :user1"),
    @NamedQuery(name = "Reviewsforroom.findByToUserId_FK", query = "SELECT r FROM Reviewsforroom r WHERE r.user2 = :user2")})



public class Reviewsforroom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
    @Column(name = "Id")
	private int id;

	@Lob
	@Basic(optional = false)
    @Column(name = "ReviewText")
	private String reviewText;

	@Basic(optional = false)
    @Column(name = "Stars")
	private int stars;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="FromUserId_FK")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="ToUserId_FK")
	private User user2;

	
	//Constructors
	public Reviewsforroom() {
	}
	
	public Reviewsforroom(int id_)
	{
		this.id = id_;
	}
	
	public Reviewsforroom(int id_, String reviewText_, int stars_,User user1_, User user2_)
	{
		this.id = id_;
		this.reviewText = reviewText_;
		this.stars = stars_;
		this.user1 = user1_;
		this.user2 = user2_;
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

	public String getReviewText() 
	{
		return this.reviewText;
	}

	public void setReviewText(String reviewText)
	{
		this.reviewText = reviewText;
	}

	public int getStars()
	{
		return this.stars;
	}

	public void setStars(int stars) 
	{
		this.stars = stars;
	}

	public User getUser1() 
	{
		return this.user1;
	}

	public void setUser1(User user1)
	{
		this.user1 = user1;
	}

	public User getUser2() 
	{
		return this.user2;
	}

	public void setUser2(User user2)
	{
		this.user2 = user2;
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
		if (!(object instanceof Reviewsforroom)) {
			return false;
		}
		Reviewsforroom other = (Reviewsforroom) object;

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
		return "entities.[ Reviewsforroom id=" + id + " ]";
	}



}