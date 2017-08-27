package service.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.DBConnector;
import service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public String insertUser(Map<String,String> hm) {
		String result= hm.get("name") +"알수없는 원인으로 실패하였습니다.";
		/*
		result = "입력하신 ID :" +id +"<br>";
		result += "입력하신 ID :" +hobby +"<br>";*/
		Connection con;
		try {
			con=DBConnector.getCon();
			String sql = "insert into user(id,password,name,hobby)";
			sql += " values(?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, hm.get("id"));
			ps.setString(2, hm.get("pwd"));
			ps.setString(3, hm.get("name"));
			ps.setString(4, hm.get("hobby"));
			
			int row= ps.executeUpdate();
			
			if(row==1) {
				result = hm.get("name")+ "님 회원가입에 성공하셨습니다.";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, String> selectUser(Map<String,String> hm) {


	Connection con;
	PreparedStatement ps;
	Map<String,String> resultMap = new HashMap<String,String>();
	String result = "없는 아이디입니다.";
	try {
		
		//드라이버 클래스를 사용하겠다.
		
		//DB연결객체 생성
		con = DBConnector.getCon();

		System.out.println("연결 성공");
		String sql ="select * from user where id =?";
		ps=con.prepareStatement(sql);
		ps.setString(1,hm.get("id"));
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			if(hm.get("pwd").equals(rs.getString("password"))){
			
				resultMap.put("id",rs.getString("id"));
				resultMap.put("name",rs.getString("name"));
				resultMap.put("hobby",rs.getString("hobby"));
				resultMap.put("user_no",rs.getString("user_no"));
				
				result=rs.getString("id")+"님 로그인 하셨습니다";
				
				//out.println(result);
			}else{
				result = "비밀번호가 틀리셨습니다.";
				//out.println(result);
			}
		}
		
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	resultMap.put("result",result);
	return resultMap;


	}

	@Override
	public int deletUser(Map<String, String> hm) {
		Connection con;
		try {
			con=DBConnector.getCon();
			String sql = "delete from user where user_no=?";
			
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, hm.get("user_no"));
			
			int row= ps.executeUpdate();
			
			return row;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateUser(Map<String, String> hm) {
		Connection con;
		try {
			con=DBConnector.getCon();
			String sql = "update user set name =?,password=?,hobby=? where user_no=?";
			
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, hm.get("name"));
			ps.setString(2, hm.get("pwd"));
			ps.setString(3, hm.get("hobby"));
			ps.setString(4, hm.get("user_no"));
			
			int row= ps.executeUpdate();
			
			return row;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Map<String, String>> selectUserList(Map<String, String> hm) {
		Connection con;
		PreparedStatement ps;
		
		List<Map<String,String>> userList = new ArrayList<Map<String,String>>();
		String result = "없는 아이디입니다.";
		try {
			
			//드라이버 클래스를 사용하겠다.
			
			//DB연결객체 생성
			con = DBConnector.getCon();

			System.out.println("연결 성공");
			String sql ="select * from user ";
			ps=con.prepareStatement(sql);
	
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
					Map<String,String> resultMap= new HashMap<String,String>();
				
					resultMap.put("id",rs.getString("id"));
					resultMap.put("name",rs.getString("name"));
					resultMap.put("hobby",rs.getString("hobby"));
					resultMap.put("user_no",rs.getString("user_no"));
					userList.add(resultMap);
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//userList.put("result",userList);
		return userList;
	}

	
	
	
	
	
	
}
