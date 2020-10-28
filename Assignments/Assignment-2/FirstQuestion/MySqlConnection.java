package in.ac.vce;
import java.sql.*;

public class MySqlConnection 
{
	public Connection MySqlConnec() throws Exception{
		String url = "jdbc:mysql://localhost:3306/test";
		String username = "root";
		String password = "";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
}
