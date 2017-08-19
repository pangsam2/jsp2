<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf-8"%>
 
  <%@ page import="java.sql.Connection" %>
 <%@ page import= "java.sql.DriverManager"%>
 <%@ page import= "java.sql.ResultSet"%>
 <%@ page import= " java.sql.SQLException"%>
 <%@ page import= "java.sql.Statement"%>
  <%@ page import= "java.sql.PreparedStatement"%>
  
  
  <%@ page buffer="8kb" autoFlush="false" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>



<%
Connection con;
String id=request.getParameter("id1");
String pwd=request.getParameter("pwd1");

String db_url="jdbc:mysql://localhost:3306/jsp_study";
String db_id ="root";
String db_pwd="manager";
Statement st;
PreparedStatement ps;
String result = "없는 아이디입니다.";
try {
	//드라이버 클래스를 사용하겠다.
	Class.forName("org.mariadb.jdbc.Driver");
	//DB연결객체 생성
	con = DriverManager.getConnection(db_url,db_id,db_pwd);
	st=con.createStatement();
	System.out.println("연결 성공");
	String sql ="select * from user where id =?";
	ps=con.prepareStatement(sql);
	ps.setString(1,id);
	
	//ResultSet rs = st.executeQuery(sql);
	ResultSet rs = ps.executeQuery();
	while(rs.next()) {
		if(pwd.equals(rs.getString("password"))){
			result=rs.getString("id")+"님 로그인 하셨습니다";
			session.setAttribute("login","true");
			session.setAttribute("id",id);
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



boolean login = false;



%>

<%-- <%=session.getId()%>
<%=session.getAttribute("test") %>
 --%>
<script>
	alert("<%=result%>");
	location.href="/login.jsp";
</script>


</body>
</html>