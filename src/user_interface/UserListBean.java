package user_interface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import database.UserDAO;
import entities.User;

@ManagedBean(name = "users")
@RequestScoped
public class UserListBean 
{

    private DataModel<UserBean> userList;    
    private UserDAO userDB;
    
    public DataModel<UserBean> getUserList() 
    {
        return userList;
    }

    public UserListBean() 
    {
        userDB = new UserDAO();
        if (userList == null) {
            List list = userDB.getAllUsersExceptAdmins();
            if (list != null) 
            {
                ArrayList<UserBean> uList = new ArrayList<UserBean>();

                for (Object o : list) 
                {

                    UserBean ub = new UserBean();
                    ub.setId(String.valueOf(((User) o).getId()));
                    ub.setUsername(((User) o).getUsername());
                    ub.setPassword(((User) o).getPassword());
                    ub.setFirstName(((User) o).getFirstName());
                    ub.setLastName(((User) o).getLastName());
                    ub.setEmail(((User) o).getEmail());
                    ub.setMobileNumber(((User) o).getMobileNumber());
                    ub.setRole(((User) o).getRole());
                    ub.setPhoto(((User) o).getPhoto());
                    ub.setConfirmed(((User) o).getIsConfirmed());
                    
                    uList.add(ub);

                }
                
                Collections.sort(uList, new Comparator<UserBean>() 
                {

                    public int compare(UserBean o1, UserBean o2) 
                    {
                        return - Boolean.compare(o2.getIsConfirmed(),o1.getIsConfirmed());
                    }
                });
                
                userList = new ListDataModel<UserBean>(uList);
            }
        }
    }


}

