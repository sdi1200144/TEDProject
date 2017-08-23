package user_interface;

import entities.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import database.UserDAO;

@ManagedBean(name = "profile")
@SessionScoped
public class ProfileBean 
{

	private String id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String role;
	private String photo;
	private boolean isConfirmed;
	private User currentUser;

   

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}


   
    public String makeProfile(String p_username) 
    {
        UserDAO userDB = new UserDAO();
        User p = userDB.getUser(p_username);
       
        id = String.valueOf(p.getId());
    	username = p.getUsername();
    	password = p.getPassword();
    	firstName = p.getFirstName();
    	lastName = p.getLastName();
    	email = p.getEmail();
    	mobileNumber = p.getMobileNumber();
    	role = p.getRole();
    	photo = p.getPhoto();
    	isConfirmed = p.getIsConfirmed();
    	
        return "/restricted/user_profile?faces-redirect=true";
    }

    public String checkConfirm() 
    {
        if (isConfirmed) 
        {
            return "Approved";
        }
        else 
        {
            return "Approve";
        }
    }

    public String iconConfirm() 
    {
        if (isConfirmed)
        {
            return "ui-icon-check";
        }
        else 
        {
            return "";
        }
    }

    public String approve() 
    {
        UserDAO userDB = new UserDAO();
        String retMessage = userDB.updateUserConfirmation(id) ;
        isConfirmed = true;
        return "/restricted/user_profile?faces-redirect=true";
    }

    public String goBack(String role) 
    {
        if (role.equals("Admin")) 
        {
            return "/restricted/audit_accounts?faces-redirect=true";
        } 
        else 
        {
            return "/restricted/user_home?faces-redirect=true";
        }
    }
    
    public String getUserProfilePhoto()
	{
		if(photo ==null)
		{
			return "/resources/images/default_avatar.png";
		}
		else
		{
			return photo;
		}
	}

}