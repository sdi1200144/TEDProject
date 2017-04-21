package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the availablehostingperiods database table.
 * 
 */
@Entity
@Table(name="availablehostingperiods")

@NamedQueries({
	@NamedQuery(name="Availablehostingperiod.findAll", query="SELECT a FROM Availablehostingperiod a"),
	@NamedQuery(name="Availablehostingperiod.findById", query="SELECT a FROM Availablehostingperiod a WHERE a.id = :id"),
	@NamedQuery(name="Availablehostingperiod.findByFromDate", query="SELECT a FROM Availablehostingperiod a WHERE a.fromDate = :fromDate"),
	@NamedQuery(name="Availablehostingperiod.findByToDate", query="SELECT a FROM Availablehostingperiod a WHERE a.toDate = :toDate"),
	@NamedQuery(name="Availablehostingperiod.findByHostingRoom", query="SELECT a FROM Availablehostingperiod a WHERE a.hostingroom = :hostingroom")
	})




public class Availablehostingperiod implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
    @Column(name = "Id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;

	//bi-directional many-to-one association to Hostingroom
	@ManyToOne
	@JoinColumn(name="RoomId_FK")
	private Hostingroom hostingroom;

	public Availablehostingperiod()
	{
	}
	
	public Availablehostingperiod(int id_)
	{
		this.id = id_;
	}
	
	public Availablehostingperiod(int id_, Date fromDate_, Date toDate_, Hostingroom hostingRoom_)
	{
		this.id = id_;
		this.fromDate = fromDate_;
		this.toDate = toDate_;
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

	public Date getFromDate() 
	{
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) 
	{
		this.fromDate = fromDate;
	}

	public Date getToDate() 
	{
		return this.toDate;
	}

	public void setToDate(Date toDate) 
	{
		this.toDate = toDate;
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
		if (!(object instanceof Availablehostingperiod)) {
			return false;
		}
		Availablehostingperiod other = (Availablehostingperiod) object;

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
		return "entities.[ Availablehostingperiod id=" + id + " ]";
	}


}