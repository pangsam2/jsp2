<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>회원정보 수정 </title>
</head>
<body>


<script src="/js/jquery-3.2.1.min.js"></script>

<script>
function check(){
	//trim() ->  공백을 없애줌
	var nameValue= $("#id").val().trim();
	var pwdValue= $("#pwd").val().trim();
	if(nameValue==""){
		alert("이름좀적자!!");
		$("#name").val("");
		$("#name").focus();
	}
	
	if(pwdValue==""){
		alert("비밀번호좀 적자!!");
		$("#pwd").val("");
		$("#pwd").focus();
		return false;
	}
	
	alert("check!");
	return true;
	
}</script>


<tr>
<td>아이디</td>
</tr>
<form action="sigin.user" method="post" onsubmit="return check()">
<table border="1">
	<tr>
	<td>아이디 </td>
	<td><input type="text" name="id" id="id" readonly value ="<%=user.get("id")%>"></td>
	</tr>
	
	<br/>
	<tr>
	<td>비밀번호</td>
	<td><input type="password" name="pwd" id="pwd" value="<%=user.get("pwd")%>"></td>
	</tr>
	 
	<br/>
	
	<tr>
	<td>이름 </td>
	<td><input type="text" name="name" id="name" value="<%=user.get("name")%>"></td>
	</tr>
	
	<br/>
	
	<tr>
	<td>취미 </td>
	<td>
		<%-- 수면<input type="checkbox" name="hobby" value="수면" <%=user.get("hobby").indexOf("수면")!=-1?"checked":""%>> --%>
		수면<input type="checkbox" name="hobby" value="수면"  <%= user.get("hobby").indexOf("수면")!=-1?"checked":""%>>
		음악<input type="checkbox" name="hobby" value="음악"  <%= user.get("hobby").indexOf("음악")!=-1?"checked":""%>>
		영화<input type="checkbox" name="hobby" value="영화" <%= user.get("hobby").indexOf("영화")!=-1?"checked":""%>>
		게임<input type="checkbox" name="hobby" value="게임"  <%= user.get("hobby").indexOf("게임")!=-1?"checked":""%>>
		요리<input type="checkbox" name="hobby" value="요리"  <%= user.get("hobby").indexOf("요리")!=-1?"checked":""%>>
		여행<input type="checkbox" name="hobby" value="여행"  <%= user.get("hobby").indexOf("여행")!=-1?"checked":""%>>
	</td>
	</tr>
	
	<tr>
	<td>수정 </td>
	<td><input type = "submit" value="회원정보 수정 "></td>
	</tr>
	
</table>

<input type="hidden" name="command" value="update">
<input type="hidden" name="userNo" value="<%=user.get("user_no")%>">
</form>

</body>
</html>