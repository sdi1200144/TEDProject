package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name="messages")


@NamedQueries({
	@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
    @NamedQuery(name = "Message.findByhiddenFromInbox", query = "SELECT m FROM Message m WHERE m.hiddenFromInbox = :hiddenFromInbox"),
    @NamedQuery(name = "Message.findByhiddenFromOutbox", query = "SELECT m FROM Message m WHERE m.hiddenFromOutbox = :hiddenFromOutbox"),
    @NamedQuery(name = "Message.findByisRead", query = "SELECT m FROM Message m WHERE m.isRead = :isRead"),
    @NamedQuery(name = "Message.findBytext", query = "SELECT m FROM Message m WHERE m.text = :text"),
    @NamedQuery(name = "Message.findBytimesent", query = "SELECT m FROM Message m WHERE m.timeSent = :timeSent"),
    @NamedQuery(name = "Message.findByFromUserId_FK", query = "SELECT m FROM Message m WHERE m.user1 = :user1"),
    @NamedQuery(name = "Message.findByToUserId_FK", query = "SELECT m FROM Message m WHERE m.user2 = :user2")})



public class Message implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
    @Column(name = "Id")
	private int id;

	@Basic(optional = false)
    @Column(name = "hiddenFromInbox")
	private boolean hiddenFromInbox;

	@Basic(optional = false)
    @Column(name = "hiddenFromOutbox")
	private boolean hiddenFromOutbox;

	@Basic(optional = false)
    @Column(name = "IsRead")
	private boolean isRead;

	@Lob
	@Basic(optional = false)
    @Column(name = "Text")
	private String text;

	@Basic(optional = false)
    @Column(name = "TimeSent")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeSent;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Sender_FK")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Recipient_FK")
	private User user2;

	//Constructors
	public Message() 
	{
	}
	
	public Message(int id_)
	{
		this.id = id_;
	}
	
	public Message(int id_, boolean hiddenFromInbox_, boolean hiddenFromOutbox_, boolean isRead_, String text_, Date timeSent_, User user1_, User user2_)
	{
		this.id = id_;
		this.hiddenFromInbox = hiddenFromInbox_;
		this.hiddenFromOutbox = hiddenFromOutbox_;
		this.isRead = isRead_;
		this.text = text_;
		this.timeSent = timeSent_;
		this.user1 = user1_;
		this.user2 = user2_;
	}

	public int getId() 
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public boolean getHiddenFromInbox() 
	{
		return this.hiddenFromInbox;
	}

	public void setHiddenFromInbox(boolean hiddenFromInbox)
	{
		this.hiddenFromInbox = hiddenFromInbox;
	}

	public boolean getHiddenFromOutbox()
	{
		return this.hiddenFromOutbox;
	}

	public void setHiddenFromOutbox(boolean hiddenFromOutbox)
	{
		this.hiddenFromOutbox = hiddenFromOutbox;
	}

	public boolean getIsRead()
	{
		return this.isRead;
	}

	public void setIsRead(boolean isRead)
	{
		this.isRead = isRead;
	}

	public String getText() 
	{
		return this.text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Date getTimeSent()
	{
		return this.timeSent;
	}

	public void setTimeSent(Date timeSent)
	{
		this.timeSent = timeSent;
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
		if (!(object instanceof Message)) {
			return false;
		}
		Message other = (Message) object;

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
		return "entities.[ Message id=" + id + " ]";
	}



}