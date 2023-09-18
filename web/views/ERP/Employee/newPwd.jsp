<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ERP.Employee.model.vo.Employee"%>
<%
	Employee emp = (Employee)request.getAttribute("emp");
	String deptName = (String)request.getAttribute("deptName");
	String posName = (String)request.getAttribute("posName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body{
 font-family: Georgia, "맑은 고딕", serif;
 }
	input[type=password]{
		width:200px;
	}
	body{
		background:white;
	}
	table, th, tr, td{
		border:1px solid gray;
	}
	
	th{
		background: rgb(117, 113, 249, 0.5);
      	color:rgba(0, 0, 0, 1);
    	font-size: 13pt;
    	text-align:center;
    	
	}
	td{
		background:
	}
	.button{
		width:100%;
		text-align:center;
		margin-top:15px;
	}
	#buttons{
	width:50px;

    background-color: #7571f9;

    border: none;

    color:#fff;

	border-radius:3px;

    text-align: center;

    text-decoration: none;

    display: inline-block;

    font-size: 15px;

    margin: 4px;

    cursor: pointer;

	}
	
	img {
		border-radius:3px;
	}
</style>
<script type="text/javascript" src="/NHMP/resources/ERP/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function checkPwd(){
	
	 var passRule = /^[a-zA-Z](([a-zA-Z])|([0-9])){5,12}$/gi;//숫자와 문자 포함 형태의 6~12자리 이내의 암호 정규식
     
     if(!passRule.test($("#newpwd").val())) {
        
   	  alert("암호는 첫번째 영문자를 포함한 숫자와 문자 포함 형태의 1~9자리 이내의 암호로 입력하셔야 합니다.");
      		$("#newpwd").val("");
      		$("#newpwd").val("");
      		$("#newpwd").focus();
      		
         return false;
     }
}


</script>
</head>
<body>
<form action="/NHMP/uppwd" method="post" onsubmit="return checkPwd();">
<input type="hidden" name="empid" value="<%=emp.getEmpId() %>">
<h3>사용자기본정보</h3>
<table class="uppwd" style="cellspacing:0;  cellpadding:3; width:100%;">
	<%if(emp.getEmpRenameFilename() != null){ %>
	<tr>
		<td colspan="4" rowspan="5"><img alt="xx" src="/NHMP/resources/ERP/emp_Img_file/<%=emp.getEmpRenameFilename() %>"
			 width="100%"height="200px"></td>
	</tr>
	<%}else{ %>
	<tr>
		<td rowspan="5"><img alt="xx" src="/NHMP/resources/ERP/images/캡처12.PNG" width="100%" height="200px"></td>
	</tr>
	<%} %>
	<tr>
		<th>사원명</th>
		<td><%= emp.getEmpName() %></td>
	</tr>
	<tr>
		<th>부서</th>
		<td><%=deptName%></td>
	</tr>
	<tr>
		<th>사번</th>
		<td><%= emp.getEmpId() %></td>
	</tr>
	<tr>
		<th>직급</th>
		<td><%= posName %></td>
	</tr>
</table>
<h3>비밀번호 초기화</h3>
<table class="uppwd" style="cellspacing:0; cellpadding:3; width:100%;">
	<tr>
		<th>초기화 비밀번호 입력</th>
		<td><input type="password" name="newpwd" id="newpwd" placeholder="내용을 입력해주세요"></td>
	</tr>
</table>
	<div class="button">
	<input type="submit" id="buttons"value="확인" >&nbsp;<input type="button"id="buttons" value="취소" onclick="javascript:self.close();">
	</div>
</form>
</body>
</html>