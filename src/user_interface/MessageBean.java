package user_interface;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import database.MessageDAO;
import entities.Message;
import entities.User;


@ManagedBean(name = "message")
@SessionScoped
public class MessageBean {
	
	private String recipient;
	private String messageText;
	private User senderUser;
	private User recipientUser;
	
	public User getSenderUser() {
		return senderUser;
	}

	public void setSenderUser(User senderUser) {
		this.senderUser = senderUser;
	}

	public User getRecipientUser() {
		return recipientUser;
	}

	public void setRecipientUser(User recipientUser) {
		this.recipientUser = recipientUser;
	}
	
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String findUsers(User userSender, User userRecipient) {
		recipient = userRecipient.getUsername();
		senderUser = userSender;
		recipientUser = userRecipient;
		messageText = "";
		
		return "/restricted/send_message?faces-redirect=true";
	}
	
	public String sendMessage() {
		MessageDAO messageDB = new MessageDAO();
		Message nmessage = new Message();
		String retMessage;
		java.sql.Timestamp timeSent = new java.sql.Timestamp(new java.util.Date().getTime());

		
		nmessage.setId(messageDB.getAllMessagess().size() + 1);
		nmessage.setText(messageText);
		nmessage.setTimeSent(timeSent);
		nmessage.setIsRead(false);
		nmessage.setUser1(senderUser);
		nmessage.setUser2(recipientUser);
		nmessage.setHiddenFromInbox(false);
		nmessage.setHiddenFromOutbox(false);

		
		retMessage = messageDB.insertMessage(nmessage);
		
		if(retMessage.equals("ok")) {
			return "/index?faces-redirect=true";
		} else {
			return retMessage;
		}
		
	}
	
	
	

}
