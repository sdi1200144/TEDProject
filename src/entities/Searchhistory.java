package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the searchhistory database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Searchhistory.findAll", query="SELECT s FROM Searchhistory s"),
	@NamedQuery(name = "Searchhistory.findById", query = "SELECT s FROM Searchhistory s WHERE s.id = :id"),
    @NamedQuery(name = "Searchhistory.findByUserId_FK", query = "SELECT s FROM Searchhistory s WHERE s.user = :user"),
    @NamedQuery(name = "Searchhistory.findByRoomId_FK", query = "SELECT s FROM Searchhistory s WHERE s.hostingroom = :hostingroom")})


public class Searchhistory implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
    @Column(name = "Id")
	private int id;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserId_FK")
	private User user;

	//bi-directional many-to-one association to Hostingroom
	@ManyToOne
	@JoinColumn(name="RoomId_FK")
	private Hostingroom hostingroom;

	
	//Constructors
	public Searchhistory() {
	}
	
	public Searchhistory(int id_)
	{
		this.id = id_;
	}
	
	public Searchhistory(int id_,User user_, Hostingroom hosting_room_)
	{
		this.id = id_;
		this.user = user_;
		this.hostingroom = hosting_room_;
	}

	
	//Getters and setters
	public int getId() 
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
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
        if (!(object instanceof Searchhistory)) {
            return false;
        }
        Searchhistory other = (Searchhistory) object;
        
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
        return "entities.[ Searchhistory id=" + id + " ]";
    }

}