package user_interface;

import database.UserDAO;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;



@ManagedBean(name="users")
@RequestScoped
public class UserListBean 
{

    private DataModel<UserBean> userList;
    
    @ManagedProperty(value="#{userDAO}")
    private UserDAO userDAO;
   
    
    public DataModel<UserBean> getList()
    {
        if (userList == null)
        {
            List<User> list = userDAO.getUsers();
            if (list !=null) 
            {
                ArrayList<UserBean> uList = new ArrayList<UserBean>();

                for (User u: list)
                {
               	System.out.println(u.toString());
                    UserBean ub = new UserBean();
                    
                    ub.setId(u.getId());
                    ub.setUsername(u.getUsername());
                    ub.setPassword(u.getPassword());
                    ub.setFirstName(u.getFirstName());
                    ub.setLastName(u.getLastName());
                    ub.setEmail(u.getEmail());
                    ub.setMobileNumber(u.getMobileNumber());
                    ub.setRole(u.getRole());
                    ub.setPhoto(u.getPhoto());
                    ub.setIsConfirmed(u.getIsConfirmed());
                    
                    uList.add(ub);
                }
                userList = new ListDataModel<UserBean>(uList);
            }
        }
        return userList;
    }


	public UserDAO getUserDAO() {
		return userDAO;
	}


	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
    
    
}

