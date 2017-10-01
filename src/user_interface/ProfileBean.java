package user_interface;

import entities.User;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

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

	private UploadedFile uploadedFile;

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
	
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}



	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
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
    	
        return "/restricted/admin/user_profile?faces-redirect=true";
    }
    
    public String changeProfile(String p_username) 
    {
    	System.out.println("changeProfile yes");
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
    	
        return "/restricted/account_settings?faces-redirect=true";
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
        String retMessage = userDB.updateUserConfirmation(username) ;
        isConfirmed = true;
        return "/restricted/admin/user_profile?faces-redirect=true";
    }

    public String goBack(String role) 
    {
        if (role.equals("Admin")) 
        {
            return "/restricted/admin/audit_accounts?faces-redirect=true";
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
			System.out.println("photo is "+photo);
			return "/resources/images/"+photo;
		}
	}
    
    
    public String updateUserProfile() throws IOException 
	{
    
		UserDAO userDB = new UserDAO();
		
		uploadPhoto();
		
		
		String retMessage = userDB.updateUser(username, password, email, mobileNumber);
		if (retMessage.equals("ok")) 
		{
			return "/restricted/account_settings?faces-redirect=true";
		} 
		else 
		{
			if(userDB.hasSameEmail(email))
			{
				FacesContext.getCurrentInstance().addMessage("changeUser_form:email",new FacesMessage("Email is already taken"));
				return null;
			}
			else
			{
			
				FacesContext.getCurrentInstance().addMessage("changeUser_form:role",new FacesMessage("Oops, something went wrong! Please try again later!"));
				return email = null;
			}
		}
	}
    
    
    
    public void uploadPhoto()
    {
    	UserDAO userDB = new UserDAO();
    	
			System.out.println("here");
			// The user submitted a file for his profile picture
			// First we need to check if this an image and its size is less than 1 megabyte
			
			String contentType = uploadedFile.getContentType();
			long size = uploadedFile.getSize();
			if(!contentType.contains("image"))
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload profile picture error" ," You are allowed to upload only images"));
				//FacesContext.getCurrentInstance().addMessage("changeUser_form:photo",new FacesMessage("Upload profile picture error - You are allowed to upload only images"));
				return ;
			}
			else if(size > 1048576)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload profile picture error" ," The maximum file size allowed is 1 Megabyte !"));
				//FacesContext.getCurrentInstance().addMessage("changeUser_form:photo",new FacesMessage("Upload profile picture error - The maximum file size allowed is 1 Megabyte !"));
				return ;
			}
			else
			{
				//String newFileName = servletContext.getRealPath("")+ "uploaded\\" +uploadedFile.getFileName() ;
				// upload file to uploaded folder
				
				int p=uploadedFile.getFileName().lastIndexOf(".");
				String imageType = uploadedFile.getFileName().substring(p);
			

				String newFileName = "C:\\Users\\User\\workspace\\TEDProject\\WebContent\\resources\\images\\"+username+imageType;
				
				
				
		    	// TODO 
		    	photo = username+imageType;
		    	
		    
		    	try
		    	{
		    		FileOutputStream fos = new FileOutputStream(new File(newFileName));
		    		InputStream is = uploadedFile.getInputstream();
		    		int BUFFER_SIZE = 8192;
		    		byte[] buffer = new byte[BUFFER_SIZE];
		    		int a;
		    		while(true)
		    		{
		    			a = is.read(buffer);
		    			if(a < 0) break;
		    			fos.write(buffer, 0, a);
		    			fos.flush();
		    		}
		    		fos.close();
		    		is.close();
		    		
		    		// Update profile picture path to database
		    		userDB.updateUserProfilePicture(username,photo);
		    	}
		    	catch(IOException e)
		    	{
		    		System.out.println(e);
		    	}
		
			}
			
		}
    
    
  
    
    
      

}