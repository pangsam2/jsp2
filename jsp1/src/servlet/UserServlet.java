package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DBConnector;

/**
 * Servlet implementation class TestServlet
 */


public class UserServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("id");
		String[] hobbies = request.getParameterValues("hobby");
		String hobby ="";
		
		for(String h:hobbies) {
			hobby += h+",";
		}
		//마지막 , 제거
		if(hobby != null) {
			hobby= hobby.substring(0,hobby.length()-1);
		}
		
		String result="알수없는 원인으로 실패하였습니다.";
		/*
		result = "입력하신 ID :" +id +"<br>";
		result += "입력하신 ID :" +hobby +"<br>";*/
		Connection con;
		try {
			con=DBConnector.getCon();
			String sql = "insert into user(id,password,name,hobby)";
			sql += " values(?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, id);
			ps.setString(2, pwd);
			ps.setString(3, name);
			ps.setString(4, hobby);
			
			int row= ps.executeUpdate();
			
			if(row==1) {
				result =name+ "님 회원가입에 성공하셨습니다.";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		doProcess(resp,result);
		
		
		
	

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String[]> m = req.getParameterMap();
		Iterator<String> it = m.keySet().iterator();
		String result ="두두두 get!!";
		
		while(it.hasNext()) {
			String key = it.next();
			result += key+":"+req.getParameter(key);
			
		}
		doProcess(resp,result+req.getParameterMap());
		/*System.out.println(req.getParameterMap());
		System.out.println("do get!");
		PrintWriter pwOut = resp.getWriter();
		pwOut.println("두두두 겟! !!!");
		pwOut.println(req.getParameterMap());*/
		
		
		
	}
	public void doProcess(HttpServletResponse resp, String writeStr) throws IOException{
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter pwOut= resp.getWriter();
		pwOut.print(writeStr);
		
	}

	

}
