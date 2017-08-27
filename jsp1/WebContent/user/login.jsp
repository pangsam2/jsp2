<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="/js/jquery-3.2.1.min.js"></script>

<script>

$(document).ready(function(){

	$("input[type='button']").click(function(){
		//alert(this.value);
		var value = this.value;
		if(value=="로그아웃"){
			$("#command").val("logout");
			
		}
		if(value=="회원탈퇴"){
			$("#command").val("delete");
			
		}else if(value=="회원정보수정"){
			location.href ="/user/update.jsp";
			return;
		}else if(value=="회원리스트"){
			location.href ="/user/list.jsp";
			return;
		}
		
		
		this.form.submit();
		
		
		
			
	})
	
	
})

</script>


<body>
<%-- <%=session.getId() %>
<%
session.setAttribute("test","test");%> --%>



login
<%

Map<String,String> user = null;
if(session.getAttribute("user")!=null){
	 user = (Map<String,String>)session.getAttribute("user");
	
}

if(user==null){
%>
<form action="login.user" method="post">
아이디 : <input type="text" name="id" id="id"> <br>
비밀번호: <input type="password" name="pwd" id="pwd">
<input type="hidden" name = "command" value="login"> 
<input type = "submit" value="로그인">

</form>


<%
}else {
	String id = user.get("id");
	String userNo = user.get("user_no");
	String name = user.get("name");
	String hobby = user.get("hobby");

	String result = userNo+"번째로 가입하신 "+name+"님 반갑습니다.</br>";
	result += name+"님의 id 는 "+ id; 
	result += name+ "님의  취미는"+hobby; 
	
	out.println(result);

%>
<form action="some.user" method="post">

<input type ="button" value="로그아웃">

<input type ="button" value="회원탈퇴">
<input type ="button" value="회원정보수정">
<input type ="button" value="회원리스트">

<input type = "hidden" name="command" id="command" value="logout">
<input type = "hidden" name="userNo" value="<%=userNo%>">



</form>
<%
	}
%>

<!-- 	<form action="login_ok.jsp">
	
		ID : <input type="text" name="id1" id="id" value="red"> <br>
		PWD: <input type="password" name="pwd1" id="pwd" value="blue"> <br>
		<input type="submit" value="로그인" >
		<input type="reset" value="취소">
	</form> -->
</body>
</html>