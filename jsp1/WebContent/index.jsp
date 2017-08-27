<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(session.getAttribute("user")==null){
	response.sendRedirect("/user/login.jsp");
}else{
	Map<String,String> user = (Map)session.getAttribute("user");
	out.println(user.get("name")+"님 환영합니다.");
}
%>

<%-- <%=session.getId() %>
<%
session.setAttribute("test","test");%> --%>

<%-- <%=request.getParameter("id")%>
<%

String login = "false";
if(session.getAttribute("login")!=null){
	login = (String)session.getAttribute("login");
}


if(login.equals("false")){
%>
<form action="login_ok.jsp">
아이디 : <input type="text" name="id1" id="id"> <br>
비밀번호: <input type="password" name="pwd1" id="pwd">
<input type = "submit">

</form>


<%
}else if(login.equals("true")){
	out.println(session.getAttribute("id")+"님 환영합니다");
}
%> --%>

<!-- 	<form action="login_ok.jsp">
	
		ID : <input type="text" name="id1" id="id" value="red"> <br>
		PWD: <input type="password" name="pwd1" id="pwd" value="blue"> <br>
		<input type="submit" value="로그인" >
		<input type="reset" value="취소">
	</form> -->
</body>
</html>