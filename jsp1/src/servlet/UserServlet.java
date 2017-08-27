package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.DBConnector;
import service.UserService;
import service.implement.UserServiceImpl;

/**
 * Servlet implementation class TestServlet
 */


public class UserServlet extends HttpServlet {
	
	
	private UserService us = new UserServiceImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		if(command==null) {
			doProcess(resp,"잘못된 요청입니다.");
		
		}else {
			if(command.equals("signin")){
				//회원가입 로직
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String name = request.getParameter("id");
				String[] hobbies = request.getParameterValues("hobby");
				String hobby ="";
		
				for(String h:hobbies) {
					hobby += h+",";
				}
		//마지막 , 제거
				hobby= hobby.substring(0,hobby.length()-1);
				Map<String,String> hm = new HashMap<String,String>();
				hm.put("id",id);
				hm.put("pwd",pwd);
				hm.put("name",name);
				hm.put("hobby",hobby);
			
				String result = us.insertUser(hm);
				doProcess(resp,result);
			
			}else if(command.equals("login")) {
				//로그인 로직
				
				String id = request.getParameter("id");
				String pwd= request.getParameter("pwd");
				System.out.println("로그인 로직 "+id+pwd);
				Map<String,String> hm = new HashMap<String,String>();
				hm.put("id",id);
				hm.put("pwd",pwd);
				
				Map<String,String> resultMap = us.selectUser(hm);
				String url="location.href='/user/login.jsp'";
				
				if(resultMap.get("id")!=null) {
					HttpSession session = request.getSession();
					session.setAttribute("user",resultMap);
/*					session.setAttribute("id",resultMap.get("id"));
					session.setAttribute("user_no",resultMap.get("user_no"));
					session.setAttribute("name",resultMap.get("name"));
					session.setAttribute("hobby",resultMap.get("hobby"));*/
					
				}
				String result = "<script>";
				result += "alert('"+resultMap.get("result") +"');";
				result += url;
				result += "</script>";
				doProcess(resp,result);
		//		doProcess(resp,resultMap.get("result"));
			}else if(command.equals("logout")) {
				//로그아웃 로직
				HttpSession session = request.getSession();
				
				//session key (user) 삭제 
				session.invalidate();
				resp.sendRedirect("/user/login.jsp");
				
				
				
				
			}else if(command.equals("delete")) {
				String userNo = request.getParameter("userNo");
				Map<String,String> hm = new HashMap<String,String>();
				hm.put("user_no", userNo);
				int rCnt = us.deletUser(hm);
				String result = "회원탈퇴에 실패하셨습니다.";
				if(rCnt==1) {
					result = "회원탈퇴에 성공하셨습니다.";
					result += "<script>";
					result += "alert('회원탈퇴에 성공하셨습니다.');";
					result += "</script>";
				}
				doProcess(resp,result);
			}else if(command.equals("update")) {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String name = request.getParameter("id");
				String[] hobbies = request.getParameterValues("hobby");
				String hobby ="";
		
				for(String h:hobbies) {
					hobby += h+",";
				}
		//마지막 , 제거
				hobby= hobby.substring(0,hobby.length()-1);
				String userNo= request.getParameter("userNo");
				Map<String,String> hm = new HashMap<String,String>();
				hm.put("id",id);
				hm.put("pwd",pwd);
				hm.put("name",name);
				hm.put("hobby",hobby);
				hm.put("user_no",userNo);
				int rCnt = us.updateUser(hm);
				String result="회원 정보 수정이 실패했습니다.";
				if(rCnt==1) {
					result = "회원정보 수정이 성공했습니다.";
				}
				doProcess(resp,result);
			
				
			}else if(command.equals("list")) {
				Map<String,String> hm = new HashMap<String,String>();
				List<Map<String,String>> userList = us.selectUserList(hm);
				String result = "<table border='1'>";
				
				for(Map<String,String> m: userList) {
					result +="<tr>";
					result +="<td>"+m.get("name")+"</td>";
					result +="<td>"+m.get("id")+"</td>";
					result +="<td>"+m.get("hobby")+"</td>";
					result +="</tr>";
				}				
				result += "</table>";
				doProcess(resp,result);	
			}
		
			
			
		}
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
