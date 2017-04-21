package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the roomphotos database table.
 * 
 */
@Entity
@Table(name="roomphotos")


@NamedQueries({
	@NamedQuery(name="Roomphoto.findAll", query="SELECT r FROM Roomphoto r"),
	@NamedQuery(name = "Roomphoto.findById", query = "SELECT r FROM Roomphoto r WHERE r.id = :id"),
    @NamedQuery(name = "Roomphoto.findByPhotoPath", query = "SELECT r FROM Roomphoto r WHERE r.photoPath = :photoPath"),
    @NamedQuery(name = "Roomphoto.findByHostingRoom", query = "SELECT r FROM Roomphoto r WHERE r.hostingroom = :hostingroom")})


public class Roomphoto implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
    @Column(name = "Id")
	private int id;

	@Basic(optional = false)
    @Column(name = "PhotoPath")
	private String photoPath;

	//bi-directional many-to-one association to Hostingroom
	@ManyToOne
	@JoinColumn(name="RoomId_FK")
	private Hostingroom hostingroom;

	//Constructors
	public Roomphoto() 
	{
	}
	
	public Roomphoto(int id_)
	{
		 this.id = id_;
	}
	
	public Roomphoto(int id_, String photopath_, Hostingroom hostingroom_)
	{
		this.id = id_;
		this.photoPath = photopath_;
		this.hostingroom = hostingroom_;
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

	public String getPhotoPath() 
	{
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) 
	{
		this.photoPath = photoPath;
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
		if (!(object instanceof Roomphoto)) {
			return false;
		}
		Roomphoto other = (Roomphoto) object;

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
		return "entities.[ Roomphoto id=" + id + " ]";
	}


}