package user_interface;

import database.UserDAO;
import entities.User;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

@ManagedBean(name="user")
@SessionScoped
public class UserBean 
{
	
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String role;
	private String photo;
	private boolean isConfirmed;

	private User current;

	@ManagedProperty(value="#{userDAO}")
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public String registerUser()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setUsername(username);
        user.setRole(role);
        user.setIsConfirmed(false);
        user.setEmail(email);
        String message = userDAO.insertUser(user);
        if (!message.equals("ok"))
        {
            context.addMessage(null, new FacesMessage(message));
        }
        if (context.getMessageList().size() > 0)
            return null;
        else 
            return "userlist";
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


}