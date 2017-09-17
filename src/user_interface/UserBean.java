package user_interface;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import database.UserDAO;
import entities.User;

@ManagedBean(name = "user")
@SessionScoped
public class UserBean {

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
	
	private String registerMessage;

	public String getRegisterMessage() {
		return registerMessage;
	}

	public void setRegisterMessage(String registerMessage) {
		this.registerMessage = registerMessage;
	}

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

	public String login() 
	{
		UserDAO userDB = new UserDAO();
		currentUser = userDB.findUser(username, password);
		
		if (currentUser == null) {
			FacesContext.getCurrentInstance().addMessage("loginFormId:username",new FacesMessage("Username or password incorrect"));
			return (username = password = null);
		} 
		else
		{
			this.copyUser(currentUser);
			if (role.equals("Admin")) 
			{
				return "/restricted/admin_home?faces-redirect=true";
			}
			else {
				if (isConfirmed) 
				{
					return "/restricted/user_home?faces-redirect=true";
				}
				else
				{
					return "/restricted/not_approved?faces-redirect=true";
				}
			}
		}
	}

	public String insertUser() 
	{
		UserDAO userDB = new UserDAO();
		User nuser = new User();

		nuser.setId(userDB.getAllUsers().size() + 1);
		nuser.setUsername(username);
		nuser.setPassword(password);
		nuser.setFirstName(firstName);
		nuser.setLastName(lastName);
		nuser.setEmail(email);
		nuser.setMobileNumber(mobileNumber);
		
		if(role.equals("Host_Guest"))
		{
			nuser.setIsConfirmed(false);
			nuser.setRole("Host&Guest");
			registerMessage = "The approval of enrollment in DIAirbnb with the role of the host is pending";
		}
		else
		{
			nuser.setRole(role);
			if(role.equals("Host"))
			{
				nuser.setIsConfirmed(false);
				registerMessage = "The approval of enrollment in DIAirbnb with the role of the host is pending";
			}
			else
			{
				nuser.setIsConfirmed(true);
				registerMessage = "You can now be an active member of DIAirbnb with the role of the guest!";
			}
		}
		nuser.setPhoto(null);
		

		String retMessage = userDB.insertNewUser(nuser);

		if (retMessage.equals("ok")) 
		{
			return "/registerMessage?faces-redirect=true";
		} 
		else 
		{	
			if(userDB.hasSameUsername_Email_MobilePhone(username, email, mobileNumber).equals("SameUsername"))
			{
				FacesContext.getCurrentInstance().addMessage("register_form:username",new FacesMessage("Username is already taken"));
			}
			else if(userDB.hasSameUsername_Email_MobilePhone(username, email, mobileNumber).equals("SameEmail"))
			{
				FacesContext.getCurrentInstance().addMessage("register_form:email",new FacesMessage("Email is already taken"));
			}
			else if(userDB.hasSameUsername_Email_MobilePhone(username, email, mobileNumber).equals("SameMobileNumber"))
			{
				FacesContext.getCurrentInstance().addMessage("register_form:mobileNumber",new FacesMessage("Mobile number is already taken"));
			}
			else if(userDB.hasSameUsername_Email_MobilePhone(username, email, mobileNumber).equals("OtherError"))
			{
				FacesContext.getCurrentInstance().addMessage("register_form:role",new FacesMessage("Oops, something went wrong! Please try again later!"));
			}
			
			return (id = username = password = firstName = lastName = email = mobileNumber = role = photo = null);
		}
	}

	public String logout() 
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=true";
	}

	public boolean isLoggedIn() 
	{
		return currentUser != null;
	}

	public String register() 
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "registration?faces-redirect=true";
	}
	
	
	public String approveAll() 
	{
        UserDAO userDB = new UserDAO();
        String retMessage = userDB.updateAllUsersConfirmation();
        return "/restricted/audit_accounts?faces-redirect=true";
    }


	public void copyUser(User user)
	{
		id = String.valueOf(user.getId());
		username = user.getUsername();
		password = user.getPassword();
		firstName = user.getFirstName();
		lastName = user.getLastName();
		email = user.getEmail();
		mobileNumber = user.getMobileNumber();
		role = user.getRole();
		photo = user.getPhoto();
		isConfirmed = user.getIsConfirmed();
	}


	public boolean isAdmin() 
	{
		return role.equals("Admin");
	}
	
	public boolean isHost()
	{
		return role.equals("Host");
	}

	public boolean isGuest()
	{
		return role.equals("Guest");
	}
	
	public boolean isHostAndGuest()
	{
		return role.equals("Host&Guest");
	}
	
	public boolean showAuditAccounts()
	{
		System.out.println("showAuditAccounts() is called");
		System.out.println(isAdmin());
		return isAdmin();
	}
	
	public boolean showExportXMLData()
	{
		return isAdmin();
	}
	
	public boolean showListings()
	{
		return (isHost() || isHostAndGuest() );
	}
	
	public boolean showTrips()
	{
		return (isGuest() || isHostAndGuest() );
	}
	
	public boolean showMessages()
	{
		return (isHost() || isGuest() || isHostAndGuest() );
	}
	
	public boolean showAccountSettings()
	{
		return (isHost() || isGuest() || isHostAndGuest() );
	}

	
	public String getPendingUsers() 
	{ 
		UserDAO userDB = new UserDAO();
		return String.valueOf(userDB.getAllNotConfirmedHosts().size());
	}

}