package user_interface;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entities.User;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 6081417964063918994L;

	public List<User> getUsers() throws ClassNotFoundException, SQLException {

		Connection connect = null;

		String url = "jdbc:mysql://localhost:3306/TedDB";

		String username = "root";
		String password = "root";

		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = (Connection) DriverManager.getConnection(url, username, password);
			// System.out.println("Connection established"+connect);

		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}

		List<User> users = new ArrayList<User>();
		PreparedStatement pstmt = (PreparedStatement) connect
				.prepareStatement("select id, username, email, role, password from User");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setRole(rs.getString("role"));
			user.setPassword(rs.getString("password"));

			users.add(user);

		}

		// close resources
		rs.close();
		pstmt.close();
		connect.close();

		return users;

	}

}

