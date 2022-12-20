<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@
	page import="Main.NursingHospital.model.ov.NursingHospital, 
				Main.Cnotice.model.vo.Cnotice, 
				java.util.ArrayList"%>

<%
	NursingHospital loginHospital = (NursingHospital)session.getAttribute("loginHospital");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록 페이지</title>
<script type="text/javascript">
function movelist(){
	location.href = "/NHMP/allqna";
	return false; // click 이벤트 전달 막음. (submit) 안
}
</script>
</head>
<body>
<h2 align="center">QNA 등록 페이지</h2>
<table align="center" border="1" cellspacing="0" cellpadding="3">
<form action="/NHMP/oinqna" method="post">
<tr><th>제목</th><td><input type="text" name="title"></td></tr>
<tr><th>작성자</th>
<td><input type="text" name="writer" value="<%= loginHospital.getNH_NAME() %>" readonly></td></tr>
<tr><th>카테고리</th><td>
<select name="category">
<option value="이용관련">이용관련</option>
<option value="서비스관련">서비스관련</option>
<option value="결제관련">결제관련</option>
<option value="기타">기타</option>
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