package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConTest {

	
	
	public static void main(String[] args) {
		Connection con;
		String url="jdbc:mysql://localhost:3306/jsp_study";
		String id ="root";
		String pwd="manager";
		Statement st;
		try {
			//드라이버 클래스를 사용하겠다.
			Class.forName("org.mariadb.jdbc.Driver");
			//DB연결객체 생성
			con = DriverManager.getConnection(url,id,pwd);
			st=con.createStatement();
			System.out.println("연결 성공");
			ResultSet rs = st.executeQuery("select * from user");
			while(rs.next()) {
				System.out.print(rs.getString("user_no")+" : ");
				System.out.print(rs.getString("id")+" : ");
				System.out.print(rs.getString("password")+" : ");
				System.out.println(rs.getString("name"));
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
