package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {

	private static Connection con;
	
	
	public static Connection getCon() throws ClassNotFoundException, SQLException{
		if(con==null) {
	

			String db_url="jdbc:mysql://localhost:3306/jsp_study";
			String db_id ="root";
			String db_pwd="manager";
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(db_url,db_id,db_pwd);
			PreparedStatement ps;
		}
		return con;
	}
	
	public static void closeCon() throws SQLException{
		
		if(con!=null) {
			con.close();
			con=null;
		}
		
	}
	
	
}
