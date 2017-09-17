package user_interface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

@SessionScoped
@ManagedBean(name = "bean")
public class FileUploadManagedBean 
{
	
	private UploadedFile uploadedFile; // +getter+setter
	
	

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}



	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}



	public void upload() throws IOException 
	{
	    String fileName = uploadedFile.getFileName();
	    String contentType = uploadedFile.getContentType();
	    byte[] contents = uploadedFile.getContents(); // Or getInputStream()
	    System.out.println("fileName is "+fileName);
	    System.out.println("contentType is "+contentType);
	    System.out.println("contents are "+contents.toString());
	 
	    
	    //  /webapps/uploads/profile_photo_submissions
	    
	    
	    	
	    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    
	    
	    	String newFileName = servletContext.getRealPath("")+ "uploaded\\" +uploadedFile.getFileName() ;
//	    			+ "uploaded" + File.separator+ uploadedFile.getFileName();
	    
	    	System.out.println("The new filename is "+newFileName);
	    	
	    	FacesMessage msg = new FacesMessage("Succesful", uploadedFile.getFileName() + " is uploaded.");
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
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
	    	}
	    	catch(IOException e)
	    	{ }
	

	}
}