<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@
	page import="Main.vo.NursingHospitalVo,Main.Cnotice.model.vo.Cnotice,java.util.ArrayList"%>

<%
NursingHospitalVo loginHospital = (NursingHospitalVo)session.getAttribute("loginHospital");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록 페이지</title>
<script type="text/javascript">
function movelist(){
	location.href = "/NHMP/gongall";
	return false; // click 이벤트 전달 막음. (submit) 안
}
</script>
</head>
<body>
<h2 align="center">새 공지사항 등록 페이지</h2>
<table style="align:center; border:1; cellspacing:0; cellpadding:3">
<form action="/NHMP/gongin" method="post">
<tr><th>제목</th><td><input type="text" name="title"></td></tr>
<tr><th>작성자</th>
<td><input type="text" name="writer" value="<%= loginHospital.getNH_NAME() %>" readonly></td></tr>
<tr><th>카테고리</th><td>
<select name="category">
<option value="이벤트">이벤트</option>
<option value="정기점검">정기점검</option>
<option value="긴급점검">긴급점검</option>
<option value="임시점검">임시점검</option>
<option value="알림">알림</option>
</select>
</td></tr>
<tr><th>날짜</th><td>
<input type="date" name="date">
</td></tr>
<tr><th>내용</th>
<td><textarea rows="5" cols="70" name="content"></textarea></td></tr>
<tr><th colspan="2">
	<input type="submit" value="등록"> &nbsp; 
	<input type="reset" value="작성취소"> &nbsp; 
	<input type="button" value="목록" onclick="return movelist();">
</th></tr>
</form>
</table>
</body>
</html>