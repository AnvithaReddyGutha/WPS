package in.ac.vce;
import java.sql.*;

public class MySQLDBCon 
{
	public Boolean authenticate(String username, String password) throws Exception{
		
		String url = "jdbc:mysql://localhost:3306/test";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =DriverManager.getConnection(url, "root', '');
		PreparedStatement stmt=con.prepareStatement("select username from login where username like ? and password like ?");
		stmt.setString(1,username);
		stmt.setString(2,password);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return true;
		else
			return false;
			
		}
	}
	public boolean islogged(String username) {
		return true;
	}
}
