<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ERP.Employee.model.vo.Employee, java.util.ArrayList, ERP.Empsalary.model.vo.EmpSalary
	, ERP.Dependents.model.vo.Dependents, ERP.Career.model.vo.Career, ERP.Education.model.vo.Education" %>
<% 	Employee emp = (Employee)request.getAttribute("emp");
  	EmpSalary empSal = (EmpSalary)request.getAttribute("empSal");
	@SuppressWarnings("unchecked")
	 ArrayList<Dependents> dpenList = (ArrayList<Dependents>)request.getAttribute("dpenList");
	@SuppressWarnings("unchecked")
	 ArrayList<Career> carList = (ArrayList<Career>)request.getAttribute("carList");
	@SuppressWarnings("unchecked")
	 ArrayList<Education> eduList = (ArrayList<Education>)request.getAttribute("eduList");
	 Employee emp2 = (Employee)session.getAttribute("loginEmployee");
	 String[] rship = new String[14];
	 String[] fyshcl = new String[7];
	 String[] taking = new String[5];
   	
   	String ad1 = "";
   	String ad2 = "";
   	String ad3 = "";
   	String ad4 = "";
   	String[] adress = emp.getAddress().split(",");
   	
   	
   	if(adress.length == 1){
   		ad1 = adress[0];
   	}else if(adress.length == 2){
   		ad1 = adress[0];
   		ad2 = adress[1];
   	}else if(adress.length == 3){
   		ad1 = adress[0];
   		ad2 = adress[1];
   		ad3 = adress[2];
   	}else if(adress.length == 4){
   		ad1 = adress[0];
   		ad2 = adress[1];
   		ad3 = adress[2];
   		ad4 = adress[3];
   	}
   	
   

   
   String healRdc = "";
   String oldRdc = ""; 
   String[] hRdc = new String[4];
   if(empSal != null)
   healRdc = Integer.toString(empSal.getHealRdc());
   else
   healRdc = "1";
   
   String[] oRdc = new String[2];
   if(empSal != null)
   oldRdc = Integer.toString(empSal.getOldRdc());
   else
 	 oldRdc = "1";
   
   for(int i = 0; i < oRdc.length; i ++){
	   switch(oldRdc){
	   case "1" : oRdc[0] = "selected";break;
	   case "30" : oRdc[1] = "selected";break;
	   }
	   
   }
   if(hRdc.length > 0){
   for(int i = 0; i < hRdc.length; i ++){ 
	   switch(healRdc){
	   case "1" : hRdc[0] = "selected"; break;
	   case "30" : hRdc[1] = "selected"; break;
	   case "50" : hRdc[2] = "selected"; break;
	   case "60" : hRdc[3] = "selected"; break;
	   }
   }
 }
   String earInran = "";
   if(empSal != null)
   earInran = Integer.toString(empSal.getEarInrance());
   else
   	earInran = "1";
   String[] eIn = new String[5];
   
   for(int i = 0; i < eIn.length; i ++){ 
	   switch(earInran){
	   case "30" : eIn[0] = "selected"; break;
	   case "50" : eIn[1] = "selected"; break;
	   case "80" : eIn[2] = "selected"; break;
	   case "100" : eIn[3] = "selected"; break;
	   case "120" : eIn[4] = "selected"; break;
	   }
   }
   
   String empNo1 = null;
	String empNo2 = null;
   

	String[] result1 = emp.getEmpNo().split("-");
	
	if(result1.length > 0){
	 empNo1 = result1[0];
	 empNo2 = result1[1];
	}else{
		empNo1 = "";
		empNo2 = "";
	}
	String adTel1 = "";
	String adTel2 = "";
 	String adTel3 = "";
 	String[] adTel = new String[3];
 	
	if(emp.getAdTel() != null){
	 adTel = emp.getAdTel().split("-");
	adTel1 = adTel[0];
	adTel2 = adTel[1];
	adTel3 = adTel[2];
	}else{
		adTel[0] = " ";
		adTel[1] = " ";
		adTel[2] = " ";
		adTel1 = adTel[0];
		adTel2 = adTel[1];
		adTel3 = adTel[2];
	}
	String[] phone = new String[3];
 	String ph1 = "";
 	String ph2 = "";
 	String ph3 = "";
	if(emp.getPhone() != null){
	phone = emp.getPhone().split("-");
	ph1 = phone[0];
	ph2 = phone[1];
	ph3 = phone[2];
	}else{
		phone[0] = " ";
		phone[1] = " ";
		phone[2] = " ";
		ph1 = phone[0];
		ph2 = phone[1];
		ph3 = phone[2];
	}
	String[] pho = new String[4];
	
	 for(int i = 0; i < pho.length; i ++){
			switch(ph1){
			case "010" : pho[0] = "selected";break;
			case "011" : pho[1] = "selected";break;
			case "017" : pho[2] = "selected";break;
			case "016" : pho[3] = "selected";break;
			
			}
		} 
	
	
	 String[] ement = new String[7];
	String[] dept = new String[6];
	String[] po = new String[6];
	String[] team = new String[18];
	String[] ward = new String[3];
	String[] hold = new String[3];
	if(emp.getEmpmentCode() != null){
	for(int i = 0; i < ement.length; i ++){
		switch(emp.getEmpmentCode()){
		case "EM1" : ement[0] = "selected";break;
		case "EM2" : ement[1] = "selected";break;
		case "EM3" : ement[2] = "selected";break;
		case "EM4" : ement[3] = "selected";break;
		case "EM5" : ement[4] = "selected";break;
		case "EM6" : ement[5] = "selected";break;
		case "EM7" : ement[6] = "selected";break;
			}
		}
	}
	if(emp.getDeptCode() != null){
	for(int i = 0; i < dept.length; i ++){
		switch(emp.getDeptCode()){
		case "10" : dept[0] = "selected";break;
		case "20" : dept[1] = "selected";break;
		case "30" : dept[2] = "selected";break;
		case "40" : dept[3] = "selected";break;
		case "50" : dept[4] = "selected";break;
		case "60" : dept[5] = "selected";break;
			}
		}
	}
	if(emp.getPosCode() != null){
	for(int i = 0; i < po.length; i ++){
		switch(emp.getPosCode()){
		case "PO1" : po[0] = "selected";break;
		case "PO2" : po[1] = "selected";break;
		case "PO3" : po[2] = "selected";break;
		case "PO4" : po[3] = "selected";break;
		case "PO5" : po[4] = "selected";break;
		case "PO6" : po[5] = "selected";break;
			}
		}
	}
	if(emp.getTeamCode() != null){
	for(int i = 0; i < team.length; i ++){
		switch(emp.getTeamCode()){
		case "TM1" : team[0] = "selected";break;
		case "TM2" : team[1] = "selected";break;
		case "TM3" : team[2] = "selected";break;
		case "TM4" : team[3] = "selected";break;
		case "TM5" : team[4] = "selected";break;
		case "TM6" : team[5] = "selected";break;
		case "TM7" : team[6] = "selected";break;
		case "TM8" : team[7] = "selected";break;
		case "TM9" : team[8] = "selected";break;
		case "TM10" : team[9] = "selected";break;
		case "TM11" : team[10] = "selected";break;
		case "TM12" : team[11] = "selected";break;
		case "TM13" : team[12] = "selected";break;
		case "TM14" : team[13] = "selected";break;
		case "TM15" : team[14] = "selected";break;
		case "TM16" : team[15] = "selected";break;
		case "TM17" : team[16] = "selected";break;
		case "TM18" : team[17] = "selected";break;
		}
	}
	}
	if(emp.getWardCode() != null){
	for(int i = 0; i < ward.length; i ++){
		switch(emp.getWardCode()){
		case "BD01" : ward[0] = "selected";break;
		case "BD02" : ward[1] = "selected";break;
		case "BD03" : ward[2] = "selected";break;
			}
		}
	}
	if(emp.getHoldOffice() != null){
	 for(int i = 0; i < hold.length; i ++){
		switch(emp.getHoldOffice()){
		case "HOD1" : hold[0] = "selected";break;
		case "HOD2" : hold[1] = "selected";break;
		case "HOD3" : hold[2] = "selected";break;
		
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>TMTS</title>
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="/NHMP/resources/ERP/images/common/favicon.png">
<!-- Pignose Calender -->
<link
	href="/NHMP/resources/ERP/css/plugins/pg-calendar/css/pignose.calendar.min.css?after"
	rel="stylesheet">
<!-- Chartist -->
<link rel="stylesheet"
	href="/NHMP/resources/ERP/css/plugins/chartist/css/chartist.min.css?after">
<link rel="stylesheet"
	href="/NHMP/resources/ERP/css/plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css?after">
<!-- 메인 css 링크 -->
<link href="/NHMP/resources/ERP/css/style.css?after" rel="stylesheet">
<link href="/NHMP/resources/ERP/css/insertEmployee.css" rel="stylesheet">
<!-- 폰트 링크 추후 확인후 삭제 -->
<link href="/NHMP/resources/ERP/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
    
    <!-- 스크립트 영역~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<script type="text/javascript" src="/NHMP/resources/ERP/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
function idCheck(){
	if($("input[name=empids]").val() != ""){
	$.ajax({
		url: "/lp/idchk",
		type: "post",
		data: { userid : $("input[name=empids]").val()},
		success: function(data){
			console.log("success :" + data.length);
			
			if(data.trim() == "ok"){
				$(".errorIdCheck").css("display", "inline-block");
				$(".succesIdCheck").css("display", "none");
				$("#userId").select();
				return false;
			}else{
				$(".succesIdCheck").css("display", "inline-block");
				$("#userPwd").focus();
				$(".errorIdCheck").css("display", "none");
				return false;
			}
			
		},error : function(jqXHR,  textStatus,  errorThrown){//자료형은 자바스크립트에서 붙여주지 않는다.
	         console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
	      }
		});
	}else
		return false;
	return false;
	}//아이디 체크 이벤트 close
function chkName(str){
	 var reg_name = /^[가-힣a-zA-Z]{2,9}$/;

 	if(!reg_name.test(str)){
  
 		return false;
 		}
		return true;
	}

function formCheck(){
	
	$("input[name=empname]").val($("input[name=empname]").val().trim());
		//이름 정규식
	  if(!chkName($("input[name=empname]").val())){
	   $(".empname").css("display", "inline-block");
	   $("input[name=empname]").focus();
	   return false;
	  }
     //이메일 정규식
      var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      
      if(!emailRule.test($("input[name=email]").val())) {            
                  alert("이메일 형식에 부합하지 않습니다.");
                  return false;
      	}
      //휴대전화 정규식
      var regExp = /^\d{3,4}$/;
		//휴대폰 중앙 자리
      if(!regExp.test($("input[name=phone2]").val())) {            
          alert("휴대전화 중간번호는 숫자 3~4자리 사이의 숫자만 입력하십시오.");
          return false;
		}
		//휴대폰 끝자리
      var regExp2 = /^\d{4}$/;
      if(!regExp2.test($("input[name=phone3]").val())) {            
          alert("휴대전화 마지막 번호는 숫자 4자리 또는 숫자만 입력하십시오.");
          return false;
		}
      var adTel1 = /^\d{2,3}$/;
      var adTel2 = /^\d{3,4}$/;
      var adTel3 = /^\d{4}$/;
      //전화번호 정규식
      if(!adTel1.test($("input[name=adtel1]").val())) {            
          alert("전화 첫 번째 자리는 숫자 2자리~3자리 또는 숫자만 입력하십시오.");
          return false;
		}
      if(!adTel2.test($("input[name=adtel2]").val())) {            
          alert("전화 두번째 자리는 숫자 3~4자리 또는 숫자만 입력하십시오.");
          return false;
		}
      if(!adTel3.test($("input[name=adtel3]").val())) {            
          alert("전화 마지막 자리는 숫자 4자리 또는 숫자만 입력하십시오.");
          return false;
		}
      //급여 정규식 숫자만
      var salary = /^\d{1,}$/;
      if(!salary.test($("input[name=salary]").val())) {            
          alert("기본급은 특수기호없이 숫자만 입력하십시오.");
          return false;
		}
      //주민번호 정규식
      var empNo = /^\d{2}(0[1-9]|1[0-2])(0[1-9][12][0-9]|[3][01])$/;
      if(!empNo.test($("input[name=empno1]").val())) {            
          alert("주민번호 형식에 맞지 않습니다.");
          return false;
		}
      //주민번호 뒷자리 정규식
      var empNo2 = /^\d[1-4][0-9]{6}$/;
      if(!empNo2.test($("input[name=empno2]").val())) {            
    	  alert("주민번호 형식에 맞지 않습니다.");
          return false;
		}
      
  	//부양가족 이름 정규식
  	var fy_namechk = /^[가-힣a-zA-Z]{2,9}$/;
  	if(!fy_namechk.test($("input[name=fyname]").val())){
 	  	alert("한글 또는 영문 2~9자리 사이로 입려 하셔야 합니다.");
 	   $("input[name=empname]").focus();
 	   return false;
 	  }
}
</script>
<script type="text/javascript">
$(function(){
    	
    	$(".btn3").click(function(){
    		//학력정보 추가시 
    		$(".insertemp4").append(
    				"<tr><td><input type='checkbox' class='shchk' name='shchk'></td><td>"
					+ "<select id='shcool' name='shcool'>" 
					+ "<option value='0'>--구분--</option>"
					+ "<option value='초등학교'>초등학교</option>"
					+ "<option value='중학교'>중학교</option>"
					+ "<option value='고등학교'>고등학교</option>"
					+ "<option value='대학교'>대학교</option>"
					+ "<option value='석사'>석사</option>"
					+ "<option value='박사'>박사</option>"
					+ "</select>"
					+ "</td>"
					+ "<td><input type='date' name='AD_DATE'></td>"
					+ "<td><input type='date' name='GR_DATE'></td>"
					+ "<td><input type='text' name='SCH_NAME'></td>"
					+ "<td><input type='text' name='MAJOR'></td>"
					+ "<td><select id='TAKING' name='TAKING'> "
					+ "<option value='0'>--구분--</option>"
					+ "<option value='졸업'>졸업</option>"
					+ "<option value='수료'>수료</option>"
					+ "<option value='자퇴'>자퇴</option>"
					+ "<option value='재학'>재학</option>"
					+ "</select>"
					+ "</td>"
					+ "</tr>");
    			return false;
    	});
    	
		
		$(".btn5").click(function(){
			//경력사항 추가시
			$(".insertemp5").append(
					"<tr>"
				+	"<td><input type='checkbox' class='comchk' name='comchk'></td>"
				+	"<td><input type='text' class='comchk' name='comname'></td>"
				+	"<td><input type='date' name='HIRE_DATE'></td>"
				+	"<td><input type='date' name='LAST_DATE'></td>"
				+	"<td><input type='text' name='WORK_TEAM'></td>"
				+	"<td><input type='text' name='LAST_POSITION'></td>"
				+	"<td><input type='text' name='RES_BILTIES'></td>"
				+	"<td><textarea rows='1' cols='15' name='LEAVE_REASON'></textarea></td>"
				+	"</tr>");
			return false;
		});
		
    });
</script>
<script type="text/javascript">
$(function(){
	//근로/사업 소득자 체크시 나머지 체크박스 체크해제
	$(".money3").click(function(){
			$(".money1").prop("checked", false);
			$(".money2").prop("checked", false);
			$(".daily").prop("checked", false);
			$(".etcbsn").prop("checked", false);
			$(".chbox").prop("disabled", false);
			$(".ear1up").prop("disabled", false);
		});
	
	//사업 소득자 체크시 나머지 체크박스 체크해제
	$(".money2").click(function(){
		$(".money1").prop("checked", false);
		$(".money3").prop("checked", false);
		$(".daily").prop("checked", false);
		$(".etcbsn").prop("checked", false);
		$(".chbox").prop("disabled", false);
		$(".ear1up").prop("disabled", false);
	});
	
	//근로 소득자 체크시 나머지 체크박스 체크해제
	$(".money1").click(function(){
		$(".money2").prop("checked", false);
		$(".money3").prop("checked", false);
		$(".daily").prop("checked", false);
		$(".etcbsn").prop("checked", false);
		$(".chbox").prop("disabled", false);
		$(".ear1up").prop("disabled", false);
	});
	//일용직 체크시 나머지 체크박스 체크해제와 4대보험 disabled 처리
	$(".daily").click(function(){
		$(".money1").prop("checked", false);
		$(".money2").prop("checked", false);
		$(".money3").prop("checked", false);
		$(".etcbsn").prop("checked", false);
		$(".chbox").prop("checked", false);
		$(".chbox").prop("disabled", true);
		$(".ear1up").prop("disabled", true);
	});
	//기타소득 체크시 나머지 체크박스 체크해제와 4대보험 disabled false처리
	$(".etcbsn").click(function(){
		$(".money1").prop("checked", false);
		$(".money2").prop("checked", false);
		$(".money3").prop("checked", false);
		$(".daily").prop("checked", false);
		$(".chbox").prop("checked", false);
		$(".chbox").prop("disabled", false);
		$(".ear1up").prop("disabled", false);
		
	});
	
	//부양가족 추가버튼 누를시 처리
	$(".btn1").click(function(){
		
		$(".insertemp3").append("<tr>"
				+ "<td><input type='checkbox' class='fychk' name='fychk'></td>"
				+ "<td>"
				+ "	<select id='RSHIP' name='RSHIP'>"
				+ "	<option value='0'>--관계구분--</option>" 
				+ "	<option value='배우자'>배우자</option>"
				+ "	<option value='아들'>아들</option>"
				+ "	<option value='딸'>딸</option>"
				+ "	<option value='부'>부</option>"
				+ "	<option value='모'>모</option>"
				+ "	<option value='형제'>형제</option>"
				+ "	<option value='자매'>자매</option>"
				+ "	<option value='장인'>장인</option>"
				+ "	<option value='장모'>장모</option>"
				+ "	<option value='조부'>조부</option>"
				+ "	<option value='조모'>조모</option>"
				+ "	<option value='시아버지'>시아버지</option>"
				+ "	<option value='시어머니'>시어머니</option>"
				+ "	</select>"
				+ " </td>"
				+ "<td><input type='text' name='fyname' required></td>"
				+ "<td>"
				+ "	<select id='itfor' name='fyitfornal'>" 
				+ "	<option value='내국인'>내국인</option>"
				+ "	<option value='외국인'>외국인</option>"
				+ "	</select>"
				+ " </td>"
				+ " <td><select id='itfor' name='DIBILITY'>" 
				+ "	<option value='N'>아니오</option>"
				+ "	<option value='Y'>예</option>"
				+ "	</select></td>"
				+ "	<td><select id='itfor' name='H_ISC'>" 
				+ "	<option value='N'>아니오</option>"
				+ "	<option value='Y'>예</option>"
				+ "	</select></td>"
				+ "	<td><select id='itfor' name='I_TOGETHER'>" 
				+ "	<option value='N'>아니오</option>"
				+ "	<option value='Y'>예</option>"
				+ "	</select></td>"
				+ "	<td><select id='itfor' name='M_CHILD'>" 
				+ "	<option value='N'>아니오</option>"
				+ "	<option value='Y'>예</option>"
				+ "	</select></td>"
					+ " </tr>");
		return false;
	});	
	
	
	
	//비밀번호 중복체크 엔터키 누를시 체크
	$("#emppwd2").on("keyup", function(event){
		
		if(event.keyCode == 13){
			if($("#emppwd").val() != $(this).val()){
				$(".checkPwd").css("display", "inline-block");
				$("#emppwd2").select();
				$(".checkPwd2").css("display", "none");
				
			}else{
				$(".checkPwd2").css("display", "inline-block");
				$("input[name=empids]").select();
				$(".checkPwd").css("display", "none");
				
			}
			
		}
		return false; // 전송되지 않게 함.
	});
	
	//부양가족 학력정보 경력사항 전체선택 전체해제 처리
	$(".fychkall").click(function(){
			$(".fychk").prop("checked", this.checked);
		});
	$(".shchkall").click(function(){
			$(".shchk").prop("checked", this.checked);
		});
	$(".comchkall").click(function(){
			$(".comchk").prop("checked", this.checked);
		});
	
	//근로소득자 사업소득자 근로/사업소득자 체크시 4대보험 전체체크처리
	$(".money1").click(function(){
			$(".chbox").prop("checked", this.checked);
		});
	$(".money2").click(function(){
		$(".chbox").prop("checked", this.checked);
	});
	$(".money3").click(function(){
		$(".chbox").prop("checked", this.checked);
	});
	
	
	
});
</script>
<style type="text/css">
body{
 font-family: Georgia, "맑은 고딕", serif;
 }

input[type=checkbox]{
	background:#F3F3F9;
}
table td, th{
	
	background:rgba(117, 113, 249, 0.04);
	padding:3px;
}
input[type=file]{
	width:200px;
}
 #sample4_jibunAddress{
    	width:219px;
    }
input[type=email]{
width:300px;
}
.insertemp3 ,.insertemp4 ,.insertemp5 td{
text-align:center;
}
</style>
</head>

<body>

	<!--*******************
        Preloader start
    ********************-->
	<div id="preloader">
		<div class="loader">
			<svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none"
					stroke-width="3" stroke-miterlimit="10" />
            </svg>
		</div>
	</div>
	<!--*******************
        Preloader end
    ********************-->


	<!--**********************************
        Main wrapper start
    ***********************************-->
	<div id="main-wrapper">

		<!--**********************************
            Nav header start
        ***********************************-->
		<div class="nav-header">
			<div class="brand-logo">
				<%if(emp2 != null){ %>
				<a href="/NHMP/views/ERP/Employee.jsp">
				 <b class="logo-abbr"><img
						src="/NHMP/resources/ERP/images/logo.png" alt=""> </b> <span
					class="logo-compact"><img
						src="/NHMP/resources/ERP/images/logo-compact.png" alt=""></span> <span
					class="brand-title"> <img
						src="/NHMP/resources/ERP/images/common/logo-text.png" alt="">
				</span>
				</a>
				<%}else{ %>
				<a href="/NHMP/views/ERP/Admin_main.jsp">
				 <b class="logo-abbr"><img
						src="/NHMP/resources/ERP/images/logo.png" alt=""> </b> <span
					class="logo-compact"><img
						src="/NHMP/resources/ERP/images/logo-compact.png" alt=""></span> <span
					class="brand-title"> <img
						src="/NHMP/resources/ERP/images/common/logo-text.png" alt="">
				</span>
				</a>
				<%} %>
			</div>
		</div>
		<!--**********************************
            Nav header end
        ***********************************-->

		<!--**********************************
            Header start
        ***********************************-->
		<div class="header">
			<div class="header-content clearfix">

				<div class="nav-control">
					<div class="hamburger">
						<span class="toggle-icon"><i class="icon-menu"></i></span>
					</div>
				</div>
				<div class="header-right">
					<ul class="clearfix">
						<li class="icons dropdown">
							<div class="user-img c-pointer position-relative"
								data-toggle="dropdown">
								<span class="activity active"></span> <img
									src="/NHMP/resources/ERP/images/user/1.png" height="40"
									width="40" alt="">
							</div>
							<div
								class="drop-down dropdown-profile animated fadeIn dropdown-menu">
								<div class="dropdown-content-body">
									<ul>
										<li><a href="app-profile.html"><i class="icon-user"></i>
												<span>내정보 보기</span></a></li>


										<hr class="my-2">
										<li><a href="/NHMP/ERP/views/Employee/calendar.jsp"><i
												class="icon-lock"></i> <span>일정관리</span></a></li>
										<li><a href="/NHMP/logouts"><i class="icon-key"></i> <span>로그아웃</span></a></li>
									</ul>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!--**********************************
            Header end ti-comment-alt
        ***********************************-->

		<!--**********************************
            Sidebar start
        ***********************************-->
		<div class="nk-sidebar">
			<div class="nk-nav-scroll">
				<%if(emp2 != null){ %>
				<ul class="metismenu" id="menu">
					<%if(emp2.getAuthorityCode().equals("G5")){ %>
					<li class="mega-menu mega-menu-sm"><a class="has-arrow"
						href="javascript:void()" aria-expanded="false"> 
						<i class="fa fa-users"></i><span class="nav-text">인사관리</span> 
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/list">전체사원조회</a></li>
							<li><a href="/NHMP/views/ERP/Employee/InsertEmployee.jsp">인사정보등록</a></li>
							<li><a href="/NHMP/ochart">조직도</a></li>
						</ul>
					</li>
						<%} %>
						<%if(emp2.getAuthorityCode().equals("G2")){ %>
					<li><a class="has-arrow" href="javascript:void()"
						aria-expanded="false"> <i class="fa fa-plus-square"></i><span
							class="nav-text">환자 관리</span> <!--   <i class="icon-screen-tablet menu-icon"></i><span class="nav-text">환자 관리</span> -->
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/patientlistview">전체환자 조회</a></li>
							<li><a href="/NHMP/views/ERP/patient/PatientInsertView.jsp">환자
									입원 등록</a></li>
							<li><a href="/NHMP/counsellistview">상담일지 등록</a></li>
							<li><a href="/NHMP/recordlistview">투약일지 등록</a></li>
						</ul></li>
					<%} %>
					<li><a class="has-arrow" href="javascript:void()"
						aria-expanded="false"> <i class="fa fa-usd"></i><span
							class="nav-text">급여 관리</span> <!--    <i class="icon-grid menu-icon"></i><span class="nav-text">급여 관리</span>  -->
					</a>
						<ul aria-expanded="false">
						
							<li><a href="/NHMP/Epaylist">급여계산</a></li>
						</ul>
						<%}else{ %>
						<ul class="metismenu" id="menu">
					<li class="mega-menu mega-menu-sm"><a class="has-arrow"
						href="javascript:void()" aria-expanded="false"> 
						<i class="fa fa-users"></i><span class="nav-text">인사관리</span> 
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/list">전체사원조회</a></li>
							<li><a href="/NHMP/views/ERP/Employee/InsertEmployee.jsp">인사정보등록</a></li>
							<li><a href="/NHMP/ochart">조직도</a></li>
						</ul>
					</li>
					<li><a class="has-arrow" href="javascript:void()"aria-expanded="false"> <i class="fa fa-id-card"></i>
						<span class="nav-text">권한설정</span>
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/authlist">권한부여관리</a></li>
						</ul>
					</li>
					<li><a class="has-arrow" href="javascript:void()"
						aria-expanded="false"> <i class="fa fa-plus-square"></i><span
							class="nav-text">환자 관리</span>
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/patientlistview">전체환자 조회</a></li>
							<li><a href="/NHMP/views/ERP/patient/PatientInsertView.jsp">환자
									입원 등록</a></li>
							<li><a href="/NHMP/counsellistview">상담일지 등록</a></li>
							<li><a href="/NHMP/recordlistview">투약일지 등록</a></li>
						</ul></li>
					<li><a class="has-arrow" href="javascript:void()"
						aria-expanded="false"> <i class="fa fa-usd"></i><span
							class="nav-text">급여 관리</span> 
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/deduclise">공제항목등록</a></li>
							<li><a href="/NHMP/allowlist">수당항목등록</a></li>
							<li><a href="/NHMP/Epaylist">급여계산</a></li>
						</ul>
						
					<li><a href="/NHMP/nlist" aria-expanded="false"> <i
							class="fa fa-slideshare"></i> <span class="nav-text">공지사항</span>
					</a></li>
					<li><a href="/NHMP/drlist" aria-expanded="false"> <i
							class="fa fa-download"></i> <span class="nav-text">자료실</span>
					</a></li>
					</ul>
				<%} %>
				
				
			</div>
		</div>

		<!--**********************************
            Sidebar end
        ***********************************-->

		<!--**********************************
          	컨텐츠 바디
        ***********************************-->
		<div class="content-body">
			<div class="insertbox">
        	<h2>기본정보</h2>
        	<form action="/NHMP/empup" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="empid" value="<%=emp.getEmpId() %>">
        	<input type="hidden" name="rfile" value="<%=emp.getEmpRenameFilename() %>">
        	<input type="hidden" name="ofile" value="<%=emp.getEmpImgOriginalFilename() %>">
			<table class="insertemp" style="cellpadding:3; cellspacing:0;" >
				<tr>
					<th>성명(한글)</th>
						<td><input type="text" name="empname" value="<%=emp.getEmpName() %>"></td>
					<th>고용형태</th>
						<td>
							<select id="empment" name="empment">
								<option value="EM7">--고용형태--</option> 
								<option value="EM1"<%=ement[0] %>>일용직</option>
								<option value="EM2"<%=ement[1] %>>위촉직</option>
								<option value="EM4"<%=ement[3] %>>계약직</option>
								<option value="EM3"<%=ement[2] %>>정규직</option>
								<option value="EM6"<%=ement[5] %>>임시직</option>
								<option value="EM5"<%=ement[4] %>>파견직</option>
								<option value="EM7"<%=ement[6] %>>인턴</option>
							</select>
						</td>
				</tr>
				<tr>
				<th>부서</th>
					<td>
						<select id="dept" name="dept">
							<option value="10">--부서구분--</option> 
							<option value="10"<%=dept[0] %>>가정의학과</option>
							<option value="30"<%=dept[2] %> >한방과</option>
							<option value="40"<%=dept[3] %>>간호과</option>
							<option value="60"<%=dept[5] %> >원무과</option>
							<option value="50"<%=dept[4] %> >총무과</option>
							<option value="20"<%=dept[1] %>>외과</option>
						</select>
					</td>
				<th>직위</th>
					<td>
						<select id="job" name="job">
							<option value="0">--선택--</option> 
							<option value="PO2"<%=po[1] %>>과장</option>
							<option value="PO3"<%=po[2] %>>사원</option>
							<option value="PO5"<%=po[4] %>>간호사</option>
							<option value="PO1"<%=po[0] %>>병원장</option>
							<option value="PO4"<%=po[3] %>>팀장</option>
							<option value="PO6"<%=po[5] %>>수간호사</option>
						</select>
					</td>
				<tr>
				<tr>
				<th>내/외국인</th>
					<td>
						<select id="itfor" name="itfornal"> 
						<%if(emp.getItnalFor().equals("내국인")){ %>
							<option value="내국인" selected>내국인</option>
							<option value="외국인">외국인</option>
							<%}else{ %>
							<option value="내국인">내국인</option>
							<option value="외국인"selected>외국인</option>
							<%} %>
						</select>
					</td><th>주민번호</th>
					<td>
						<input type="text" name="empno1" value="<%=empNo1 %>" required>
						-<input type="text" value="<%=empNo2 %>"  name="empno2"required>
					</td>
				<tr>
				<tr>
					<th>주소</th>
					<td colspan="3">
						<input type="text" id="sample4_postcode" placeholder="우편번호">
						<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">&nbsp;
						<input type="text" id="sample4_roadAddress"name="address1" placeholder="도로명주소"value="<%=ad1 %>" readonly>
						<input type="text" id="sample4_jibunAddress"name="address2" placeholder="지번주소"value="<%=ad2 %>" readonly><br>
						<span id="guide" style="color:#999;display:none"></span>
						<input type="text" id="sample4_detailAddress"name="address3"value="<%=ad3 %>" placeholder="상세주소">
						<input type="text" id="sample4_extraAddress"name="address4"value="<%=ad4 %>" placeholder="참고항목" readonly>
					
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
						<%if(adTel[0] != null){ %>
						<td>
						<input typee="Tel" name="adtel1" value="<%=adTel[0]%>">-
						<input typee="Tel" name="adtel2" value="<%=adTel[1]%>">-
						<input typee="Tel" name="adtel3" value="<%=adTel[2]%>"></td>
						<%}else{%>
							<td>
						<input typee="Tel" name="adtel1">-
						<input typee="Tel" name="adtel2">-
						<input typee="Tel" name="adtel3"></td>
						<%} %>
					<th>휴대폰</th>
						<td>
							<select name="phone">
								<option value="010"<%=pho[0] %>>010</option>
								<option value="011"<%=pho[1] %>>011</option>
								<option value="017"<%=pho[2] %>>017</option>
								<option value="016"<%=pho[3] %>>016</option>
							</select>&nbsp;-<input type="Tel" name="phone2" value="<%=ph2%>">-
								<input type="Tel" name="phone3" value="<%=ph3%>">
						</td>
				</tr>
				<tr>
					<th>아이디</th>
						<td><input type="text" name="empids" value="<%=emp.getUserId() %>" readonly></td>
					<th>비밀번호</th>
						<td><input type="password" id="emppwd"name="emppwds" value="<%=emp.getUserpwd() %>" readonly></td>
				</tr>
				<tr>
					<th>이메일</th>
						<td><input type="email" name="email" value="<%=emp.getEmail() %>"></td>
					<th>기본급</th>
						<td><input type="text" name="salary" value="<%=emp.getSalary()%>" required></td>
				</tr>
				<tr>
					<th>성별</th>
						<%if(emp.getGender().equals("M") == true){ %>
						<td><input type="radio" name="gender" value="M" checked>남자&nbsp;
							<input type="radio" name="gender" value="F">여자</td>
						<%}else{ %>
							<td><input type="radio" name="gender" value="M">남자&nbsp;
							<input type="radio" name="gender" value="F" checked>여자</td>
						<%} %>
						<th>이미지</th>
						<td>
						<%if(emp.getEmpImgOriginalFilename() != null){ %>
						기존파일명:&nbsp;<%=emp.getEmpImgOriginalFilename() %>&nbsp;
						<%}else{ %>
						이미지파일 없음
						<%} %>
						<input type="file" name="upfiles"></td>
				</tr>
				<tr>
					<th>팀</th>
						<td>
							<select id="team" name="team">
								<option value="0">--팀구분--</option> 
								<option value="TM1"<%=team[0]%>>병원간호팀</option>
								<option value="TM2"<%=team[1]%>>외래간호팀</option>
								<option value="TM3"<%=team[2]%>>간병팀</option>
								<option value="TM4"<%=team[3]%>>시설관리팀</option>
								<option value="TM5"<%=team[4]%>>환경팀</option>
								<option value="TM6"<%=team[5]%>>회계팀</option>
								<option value="TM7"<%=team[6]%> >홍보팀</option>
								<option value="TM8"<%=team[7]%>>인사교육팀</option>
								<option value="TM9"<%=team[8]%>>영양실</option>
								<option value="TM10"<%=team[9]%>>심사청구팀</option>
								<option value="TM11"<%=team[10]%>>의무기록팀</option>
								<option value="TM12"<%=team[11]%>>입원상담팀</option>
								<option value="TM13"<%=team[12]%>>사회복지팀</option>
								<option value="TM14"<%=team[13]%>>차량지원팀</option>
								<option value="TM15"<%=team[14]%>>약제실</option>
								<option value="TM16"<%=team[15]%>>물리치료실</option>
								<option value="TM17"<%=team[16]%>>방사선실</option>
								<option value="TM18"<%=team[17]%>>임상병리실</option>
							</select>
						</td>
							
					<th>재직형태</th>
						<td>
							<select id="hold" name="hold">
								<option value="0">--재직구분--</option> 
								<option value="HOD1"<%=hold[0]%>>휴직</option>
								<option value="HOD2"<%=hold[1]%>>재직</option>
								<option value="HOD3"<%=hold[2]%>>퇴직</option>
							</select>
						</td>
				</tr>
				<tr>
					<th>병동</th>
						<td>
							<select id="ward" name="ward">
								<option value="0">--병동구분--</option> 
								<option value="BD01"<%=ward[0]%>>1병동</option>
								<option value="BD02"<%=ward[1]%>>2병동</option>
								<option value="BD03"<%=ward[2]%>>3병동</option>
							</select>
						</td>
							
					<th>권한</th>
						<td> &nbsp;사용자기본권한<input type="hidden" name="author" value="G1"></td>
				</tr>
				<tr>
					<th>기타사항</th>
						<td colspan="3"><textarea rows="4"name="etc" cols="100"><%=emp.getEmpEtc() %></textarea></td>
				</tr>
			</table>
			<!--  급여정보 란 -->
		<h2>급여정보</h2>
		<table class="insertemp2" style="cellpadding:5; cellspacing:0;" >
		<%if(empSal != null){%>
			<tr id="no4" align="center">
				<th>4대보험</th>
				
					<td colspan="3">&nbsp;&nbsp;국민연금(
									<%if(empSal.getNatPension() != null){ %>
									<%if(empSal.getNatPension().equals("Y") == true)%>
										<input type="checkbox" name="no1" class="chbox" value="Y" checked>
										<%}else{ %>
										<input type="checkbox" name="no1" class="chbox" value="Y" >
										<%} %>)
						&nbsp;&nbsp;건강보험(
						<%if(empSal.getHealInrance() != null){ %>
						<%if(empSal.getHealInrance().equals("Y") == true)%>
									<input type="checkbox" name="no2" class="chbox" value="Y" checked>)
									감면(<select name="no2up" class="chbox">
										<option value="1"<%=hRdc[0] %>>선택</option>
										<option value="30"<%=hRdc[1] %>>30%</option>
										<option value="50"<%=hRdc[2] %>>50%</option>
										<option value="60"<%=hRdc[3] %>>60%</option>
									</select>&nbsp;)
						<%}else{ %>
									<input type="checkbox" name="no2" class="chbox" value="Y">
							감면(<select name="no2up" class="chbox">
										<option value="1">선택</option>
										<option value="30">30%</option>
										<option value="50">50%</option>
										<option value="60">60%</option>
									</select>&nbsp;)
									<%} %>
						&nbsp;&nbsp;노인장기요양보험(
						<%if(empSal.getOldInrance() != null){ %>
						<% if(empSal.getOldInrance().equals("Y") == true)%>
						
									<input type="checkbox" class="chbox" name="no3" value="Y" checked>
							감면(<select name="no3up" class="chbox">
										<option value="1"<%=oRdc[0] %>>선택</option>
										<option value="30"<%=oRdc[1] %>>30%</option>
									</select>)
						<%}else{ %>
									<input type="checkbox" class="chbox" name="no3" value="Y">)
							감면(<select name="no3up" class="chbox">
										<option value="1">선택</option>
										<option value="30">30%</option>
									</select>)
									<%} %>
						&nbsp;&nbsp;고용보험(
						<%if(empSal.getEmentInrance() != null){ %>
						<%if(empSal.getEmentInrance().equals("Y") == true)%>
						<input type="checkbox" class="chbox" name="no4" value="Y" checked>
						<%}else{ %>
						<input type="checkbox" class="chbox" name="no4" value="Y">
						<%} %>)		
						</td>
				</tr>
				<tr>
				<th>갑근세</th>
					<td colspan="3" align="center">
						<%if(empSal.getEarIncome() != null){ %>
						<%if(empSal.getEarIncome().equals("Y") == true)%>
						<input type="checkbox" name="earner1" class="money1"value="Y" checked>
						근로소득자 (근로소득간이세액표)새액:
							<select name="earner1up" class="ear1up">
								<option value="30"<%=eIn[0] %>>30%</option>
								<option value="50"<%=eIn[1] %>>50%</option>
								<option value="80"<%=eIn[2] %>>80%</option>
								<option value="100"<%=eIn[3] %>>100%</option>
								<option value="120"<%=eIn[4] %>>120%</option>
							</select><br><br>
						<%}else{ %>
						<input type="checkbox" name="earner1" class="money1"value="Y">
						근로소득자 (근로소득간이세액표)새액:
							<select name="earner1up" class="ear1up">
								<option value="30">30%</option>
								<option value="50">50%</option>
								<option value="80">80%</option>
								<option value="100">100%</option>
								<option value="120">120%</option>
							</select><br><br>
							<%} %>
						<% if(empSal.getBsnIncome() != null){%>
						<% if(empSal.getBsnIncome().equals("Y") == true) %>
						<input type="checkbox" name="earner2" class="money2"value="3.3" checked>
						<%}else{ %>
						<input type="checkbox" name="earner2" class="money2"value="3.3" >
						<%} %>사업 소득자(3.3%)
						<% if(empSal.getDailyJob() != null){%>
						<%if(empSal.getDailyJob().equals("Y") == true)%>
						<input type="checkbox" name="earner3"class="daily" value="2.9" checked>
						<%}else{ %>
						<input type="checkbox" name="earner3"class="daily" value="2.9" >
						<%} %>일용직(2.9%)
						<% if(empSal.getEtcIncome() != null){%>
						<% if(empSal.getEtcIncome().equals("Y") == true)%>
						<input type="checkbox" name="earner4"class="etcbsn" value="8.8" checked>
						<%}else{ %>
						<input type="checkbox" name="earner4"class="etcbsn"  value="8.8">
						<%} %>기타소득자(8.8%)
						<% if(empSal.getEarBsnIncome() != null){%>
						<%if(empSal.getEarBsnIncome().equals("Y") == true)%>
						<input type="checkbox" name="earner5" class="money3" checked>
						<%}else{ %>
						<input type="checkbox" name="earner5" class="money3">
						<%} %>근로/사업소득자
					</td>
				</tr>
				<%}else{ %>
				<tr id="no4" align="center">
				<th>4대보험</th>
				
					<td colspan="3">&nbsp;&nbsp;국민연금(<input type="checkbox" name="no1" class="chbox" value="Y" checked>)
						&nbsp;&nbsp;건강보험(<input type="checkbox" name="no2" class="chbox" value="Y" checked>)
									감면(<select name="no2up" class="chbox">
										<option value="1">선택</option>
										<option value="30">30%</option>
										<option value="50">50%</option>
										<option value="60">60%</option>
									</select>&nbsp;)
						&nbsp;&nbsp;노인장기요양보험(<input type="checkbox" class="chbox" name="no3" value="Y" checked>)
						감면(<select name="no3up" class="chbox">
										<option value="1">선택</option>
										<option value="30">30%</option>
									</select>)
						&nbsp;&nbsp;고용보험(<input type="checkbox" class="chbox" name="no4" value="Y" checked>)		
									</td>
				</tr>
				<tr>
				<th>갑근세</th>
					<td colspan="3" align="center">
						<input type="checkbox" name="earner1" class="money1"value="Y" checked>근로소득자 (근로소득간이세액표)새액:
							<select name="earner1up" class="ear1up">
								<option value="30">30%</option>
								<option value="50">50%</option>
								<option value="80">80%</option>
								<option value="100" checked>100%</option>
								<option value="120">120%</option>
							</select><br><br>
						<input type="checkbox" name="earner2" class="money2"value="3.3" >사업 소득자(3.3%)
						<input type="checkbox" name="earner3"class="daily" value="2.9" >일용직(2.9%)
						<input type="checkbox" name="earner4"class="etcbsn" value="8.8" >기타소득자(8.8%)
						<input type="checkbox" name="earner5" class="money3">근로/사업소득자
					</td>
				</tr>
				<%} %>
			</table>
			<!-- 부양가족~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
			<h2>부양가족</h2>
			
				<table class="insertemp3" style="cellpadding:5; cellspacing:0;">
					<tr>
						<th><input type="checkbox" class="fychkall" name="fychkall"></th>
						<th>관계</th>
						<th>성명</th>
						<th>구분</th>
						<th>장애여부</th>
						<th>건강보험</th>
						<th>동거여부</th>
						<th>다자녀</th>
					</tr>
					<%if(dpenList != null) {%>
					<%for(Dependents d : dpenList){ %>
					<% switch(d.getRship().trim()){
					
						case "0" : rship[0] = "selected";break;
						case "배우자" : rship[1] = "selected";break;
						case "아들" : rship[2] = "selected";break;
						case "딸" : rship[3] = "selected";break;
						case "부" : rship[4] = "selected";break;
						case "모" : rship[5] = "selected";break;
						case "형제" : rship[6] = "selected";break;
						case "자매" : rship[7] = "selected";break;
						case "장인" : rship[8] = "selected";break;
						case "장모" : rship[9] = "selected";break;
						case "조부" : rship[10] = "selected";break;
						case "조모" : rship[11] = "selected";break;
						case "시아버지" : rship[12] = "selected";break;
						case "시어머니" : rship[13] = "selected";break;
							}
						%>				
					 <tr>
						<td><input type="checkbox" class="fychk" name="fychk"></td>
						<td>
							<select id="rship" name="rship">
								<option value="0"<%=rship[0]%>>--관계구분--</option> 
								<option value="배우자"<%= rship[1] %>>배우자</option>
								<option value="아들"<%=rship[2] %>>아들</option>
								<option value="딸"<%=rship[3] %>>딸</option>
								<option value="부"<%=rship[4] %>>부</option>
								<option value="모"<%=rship[5] %>>모</option>
								<option value="형제"<%=rship[6] %>>형제</option>
								<option value="자매"<%=rship[7] %>>자매</option>
								<option value="장인"<%=rship[8] %>>장인</option>
								<option value="장모"<%=rship[9] %>>장모</option>
								<option value="조부"<%=rship[10] %>>조부</option>
								<option value="조모"<%=rship[11] %>>조모</option>
								<option value="시아버지"<%=rship[12] %>>시아버지</option>
								<option value="시어머니"<%=rship[13] %>>시어머니</option>
							</select>
						</td>
						<td><input type="text" name="fyname" value="<%=d.getfName() %>" required></td>
						<td>
						<%if(d.getItNalfor().equals("내국인")== true){ %>
							<select id="itfor" name="fyitfornal"> 
								<option value="내국인" selected>내국인</option>
								<option value="외국인">외국인</option>
							</select>
							<%}else{ %>
							<select id="itfor" name="fyitfornal"> 
								<option value="내국인">내국인</option>
								<option value="외국인" selected>외국인</option>
							</select>
							<%} %>
						</td>
						<td><%if(d.getDibility().equals("Y")==true){ %>
							<select id="itfor" name="DIBILITY"> 
								<option value="N">아니오</option>
								<option value="Y" selected>예</option>
							</select>
							<%}else{ %>
							<select id="itfor" name="DIBILITY"> 
								<option value="N" selected>아니오</option>
								<option value="Y">예</option>
							</select>
							<%} %>
						</td>
						<td><%if(d.gethIsc().equals("Y") == true){ %>
							<select id="itfor" name="H_ISC"> 
								<option value="N">아니오</option>
								<option value="Y" selected>예</option>
							</select>
							<%}else{ %>
							<select id="itfor" name="H_ISC"> 
								<option value="N" selected>아니오</option>
								<option value="Y">예</option>
							</select>
							<%} %>
							</td>
						<td><%if(d.getiTogether().equals("Y") == true){ %>
							<select id="itfor" name="I_TOGETHER"> 
								<option value="N">아니오</option>
								<option value="Y" selected>예</option>
							</select>
							<%}else{ %>
							<select id="itfor" name="I_TOGETHER"> 
								<option value="N" selected>아니오</option>
								<option value="Y">예</option>
							</select>
							<%} %>
							</td>
						<td><%if(d.getmChild().equals("Y") == true){ %>
							<select id="itfor" name="M_CHILD"> 
								<option value="N">아니오</option>
								<option value="Y" selected>예</option>
							</select>
							<%}else{ %>
							<select id="itfor" name="M_CHILD"> 
								<option value="N" selected>아니오</option>
								<option value="Y">예</option>
							</select>
							<%} %>
							</td>
					</tr>
						<%} %>
					<%}else{%>
					<tr>
						<td><input type="checkbox" class="fychk" name="fychk"></td>
						<td>
							<select id="rship" name="rship">
								<option value="0">--관계구분--</option> 
								<option value="배우자">배우자</option>
								<option value="아들">아들</option>
								<option value="딸">딸</option>
								<option value="부">부</option>
								<option value="모">모</option>
								<option value="형제">형제</option>
								<option value="자매">자매</option>
								<option value="장인">장인</option>
								<option value="장모">장모</option>
								<option value="조부">조부</option>
								<option value="조모">조모</option>
								<option value="시아버지">시아버지</option>
								<option value="시어머니">시어머니</option>
							</select>
						</td>
						<td><input type="text" name="fyname" required></td>
						<td>
							<select id="itfor" name="fyitfornal"> 
								<option value="내국인">내국인</option>
								<option value="외국인">외국인</option>
							</select>
						</td>
						<td>
							<select id="itfor" name="DIBILITY"> 
								<option value="N">아니오</option>
								<option value="Y">예</option>
							</select>
						</td>
						<td><select id="itfor" name="H_ISC"> 
								<option value="N">아니오</option>
								<option value="Y">예</option>
							</select></td>
						<td><select id="itfor" name="I_TOGETHER"> 
								<option value="N">아니오</option>
								<option value="Y">예</option>
							</select></td>
						<td><select id="itfor" name="M_CHILD"> 
								<option value="N">아니오</option>
								<option value="Y">예</option>
							</select></td>
					</tr>
					<tr>
						<td><input type="checkbox" class="fychk" name="fychk"></td>
						<td>
							<select id="rship" name="rship">
								<option value="0">--관계구분--</option> 
								<option value="배우자">배우자</option>
								<option value="아들">아들</option>
								<option value="딸">딸</option>
								<option value="부">부</option>
								<option value="모">모</option>
								<option value="형제">형제</option>
								<option value="자매">자매</option>
								<option value="장인">장인</option>
								<option value="장모">장모</option>
								<option value="조부">조부</option>
								<option value="조모">조모</option>
								<option value="시아버지">시아버지</option>
								<option value="시어머니">시어머니</option>
							</select>
						</td>
						<td><input type="text" name="fyname" required></td>
						<td>
							<select id="itfor" name="fyitfornal"> 
								<option value="내국인">내국인</option>
								<option value="외국인">외국인</option>
							</select>
						</td>
						<td>
							<select id="itfor" name="DIBILITY"> 
								<option value="N">아니오</option>
								<option value="Y">예</option>
							</select>
						</td>
						<td><select id="itfor" name="H_ISC"> 
								<option value="N">아니오</option>
								<option value="Y">예</option>
							</select></td>
						<td><select id="itfor" name="I_TOGETHER"> 
								<option value="N">아니오</option>
								<option value="Y">예</option>
							</select></td>
						<td><select id="itfor" name="M_CHILD"> 
								<option value="N">아니오</option>
								<option value="Y">예</option>
							</select></td>
					</tr>
					
					<%} %>
				</table>
			<!-- 학력정보페이지~~~ -->
				<h2>학력정보</h2>
				<table class="insertemp4" style="cellpadding:5; cellspacing:0;" >
					<tr>
						<th>&nbsp;&nbsp;<input type="checkbox" class="shchkall" name="shchkall"></th>
						<th>구분</th>
						<th>입학년월</th>
						<th>졸업년월</th>
						<th>학교명</th>
						<th>전공</th>
						<th>이수</th>
					</tr>
					<tr>
					<%if(eduList != null){ %>
					<%for(Education e : eduList){ %>
						<td><input type="checkbox" class="shchk" name="shchk"></td>
						<td>
						<% switch(e.getItnalFor().trim()){
					
						case "0" : fyshcl[0] = "selected";break;
						case "초등학교" : fyshcl[1] = "selected";break;
						case "중학교" : fyshcl[2] = "selected";break;
						case "고등학교" : fyshcl[3] = "selected";break;
						case "대학교" : fyshcl[4] = "selected";break;
						case "석사" : fyshcl[5] = "selected";break;
						case "박사" : fyshcl[6] = "selected";break;
						}%>	
							<select id="shcool" name="shcool">
								<option value="0"<%=fyshcl[0]%>>--구분--</option> 
								<option value="초등학교" <%=fyshcl[1]%>>초등학교</option>
								<option value="중학교" <%=fyshcl[2]%>>중학교</option>
								<option value="고등학교" <%=fyshcl[3]%>>고등학교</option>
								<option value="대학교" <%=fyshcl[4]%>>대학교</option>
								<option value="석사" <%=fyshcl[5]%>>석사</option>
								<option value="박사" <%=fyshcl[6]%>>박사</option>
							</select>
						</td>
						<td><input type="date" name="AD_DATE" min="1960-01-01" max="9999-12-31" value="<%=e.getAdDate() %>" required></td>
						<td><input type="date" name="GR_DATE" min="1960-01-01" max="9999-12-31" value="<%=e.getGrDate() %>" required></td>
						<td><input type="text" name="SCH_NAME"value="<%=e.getSchName() %>" required></td>
						<td><input type="text" name="MAJOR" value="<%=e.getMajor() %>" required></td>
						<td>
						<% switch(e.getTaking()){
					
						case "0" : taking[0] = "selected";break;
						case "졸업" : taking[1] = "selected";break;
						case "수료" : taking[2] = "selected";break;
						case "자퇴" : taking[3] = "selected";break;
						case "재학" : taking[4] = "selected";break;
						}%>	
							<select id="TAKING" name="TAKING">
								<option value="0"<%=taking[0]%>>--구분--</option> 
								<option value="졸업"<%=taking[1]%>>졸업</option>
								<option value="수료"<%=taking[2]%>>수료</option>
								<option value="자퇴"<%=taking[3]%>>자퇴</option>
								<option value="재학"<%=taking[4]%>>재학</option>
							</select>
						</td>
					</tr>
					
					<%} %>
					<%}else{ %>
						<tr>
						<td><input type="checkbox" class="shchk" name="shchk"></td>
						<td>
							<select id="shcool" name="shcool">
								<option value="0">--구분--</option> 
								<option value="초등학교">초등학교</option>
								<option value="중학교">중학교</option>
								<option value="고등학교">고등학교</option>
								<option value="대학교">대학교</option>
								<option value="석사">석사</option>
								<option value="박사">박사</option>
							</select>
						</td>
						<td><input type="date" name="AD_DATE" min="1960-01-01" max="9999-12-31" required></td>
						<td><input type="date" name="GR_DATE" min="1960-01-01" max="9999-12-31" required></td>
						<td><input type="text" name="SCH_NAME" required></td>
						<td><input type="text" name="MAJOR" required></td>
						<td>
							<select id="TAKING" name="TAKING">
								<option value="0">--구분--</option> 
								<option value="졸업">졸업</option>
								<option value="수료">수료</option>
								<option value="자퇴">자퇴</option>
								<option value="재학">재학</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><input type="checkbox" class="shchk" name="shchk"></td>
						<td>
							<select id="shcool" name="shcool">
								<option value="0">--구분--</option> 
								<option value="초등학교">초등학교</option>
								<option value="중학교">중학교</option>
								<option value="고등학교">고등학교</option>
								<option value="대학교">대학교</option>
								<option value="석사">석사</option>
								<option value="박사">박사</option>
							</select>
						</td>
						<td><input type="date" name="AD_DATE" min="1960-01-01" max="9999-12-31" required></td>
						<td><input type="date" name="GR_DATE" min="1960-01-01" max="9999-12-31" required></td>
						<td><input type="text" name="SCH_NAME" required></td>
						<td><input type="text" name="MAJOR" required></td>
						<td>
							<select id="TAKING" name="TAKING">
								<option value="0">--구분--</option> 
								<option value="졸업">졸업</option>
								<option value="수료">수료</option>
								<option value="자퇴">자퇴</option>
								<option value="재학">재학</option>
							</select>
						</td>
					</tr>
					<%} %>
				</table>
				<!-- 경력사항 테이블~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
				<h2>경력사항</h2>
				<table class="insertemp5" style="cellpadding:5; cellspacing:0;">
					<tr>
						<th>&nbsp;&nbsp;<input type="checkbox" class="comchkall" name="comchkall"></th>
						<th>회사명</th>
						<th>입사일자</th>
						<th>퇴사일자</th>
						<th>근무기간</th>
						<th>최종직위</th>
						<th>담당직무</th>
						<th>퇴사사유</th>
					</tr>
					<%if(carList != null){ %>
						<%for(Career c : carList){ %>
					<tr>
						<td><input type="checkbox" class="comchk" name="comchk"<%=emp.getEmpId() %>></td>
						<td><input type="text" class="comchk" name="COM_NAME"value="<%=c.getComName() %>" required></td>
						<td><input type="date" name="HIRE_DATE" min="1960-01-01" max="9999-12-31" value="<%=c.getHireDate() %>" required></td>
						<td><input type="date" name="LAST_DATE" min="1960-01-01" max="9999-12-31" value="<%=c.getLastDate() %>" required></td>
						<td><input type="text" name="WORK_TEAM" value="<%=c.getWorkTeam() %>" required></td>
						<td><input type="text" name="LAST_POSITION" value="<%=c.getLastPosition() %>" required></td>
						<td><input type="text" name="RES_BILTIES" value="<%=c.getResBilties() %>" required></td>
						<td><textarea rows="1" cols="15" name="LEAVE_REASON" required><%=c.getLeaveReason() %></textarea></td>
					</tr>
					<%} %>
					<%}else{ %>
					<tr>
						<td><input type="checkbox" class="comchk" name="comchk"></td>
						<td><input type="text" class="comchk" name="COM_NAME" required></td>
						<td><input type="date" min="1960-01-01" max="9999-12-31" name="HIRE_DATE" required></td>
						<td><input type="date" min="1960-01-01" max="9999-12-31" name="LAST_DATE" required></td>
						<td><input type="text" name="WORK_TEAM" required></td>
						<td><input type="text" name="LAST_POSITION" required></td>
						<td><input type="text" name="RES_BILTIES" required></td>
						<td><textarea rows="1" cols="15" name="LEAVE_REASON" required></textarea></td>
					</tr>
					<tr>
						<td><input type="checkbox" class="comchk" name="comchk"></td>
						<td><input type="text" class="comchk" name="COM_NAME" required></td>
						<td><input type="date" min="1960-01-01" max="9999-12-31" name="HIRE_DATE" required></td>
						<td><input type="date" min="1960-01-01" max="9999-12-31" name="LAST_DATE" required></td>
						<td><input type="text" name="WORK_TEAM" required></td>
						<td><input type="text" name="LAST_POSITION" required></td>
						<td><input type="text" name="RES_BILTIES" required></td>
						<td><textarea rows="1" cols="15" name="LEAVE_REASON" required></textarea></td>
					<%} %>
				</table>
				<div class="insert">
				<input type="submit" id="sub" value="수정하기" >&nbsp;<input type="reset" id="re" value="취소">
				</div>
			</form>	
		</div>
			
			</div>
			<!--**********************************
         	 컨텐츠바디 종료
        ***********************************-->


			<!--**********************************
            Footer start
        ***********************************-->
			<div class="footer">
				<div class="copyright">
					<p>
						Copyright &copy; Designed And Developed by <a
							href="https://themeforest.net/user/quixlab">이민삼수</a> 2018
					</p>
				</div>
			</div>
			<!--**********************************
            Footer end
        ***********************************-->
		</div>
		<!--**********************************
        Main wrapper end
    ***********************************-->

		<!--**********************************
        Scripts
    ***********************************-->
		<script src="/NHMP/resources/ERP/css/plugins/common/common.min.js"></script>
		<script src="/NHMP/resources/ERP/js/custom.min.js"></script>
		<script src="/NHMP/resources/ERP/js/settings.js"></script>
		<script src="/NHMP/resources/ERP/js/gleek.js"></script>
		<script src="/NHMP/resources/ERP/js/styleSwitcher.js"></script>

		<!-- Chartjs -->
		<script
			src="/NHMP/resources/ERP/css/plugins/chart.js/Chart.bundle.min.js"></script>
		<!-- Circle progress -->
		<script
			src="/NHMP/resources/ERP/css/plugins/circle-progress/circle-progress.min.js"></script>
		<!-- Datamap -->
		<script src="/NHMP/resources/ERP/css/plugins/d3v3/index.js"></script>
		<script src="/NHMP/resources/ERP/css/plugins/topojson/topojson.min.js"></script>
		<script
			src="/NHMP/resources/ERP/css/plugins/datamaps/datamaps.world.min.js"></script>
		<!-- Morrisjs -->
		<script src="/NHMP/resources/ERP/css/plugins/raphael/raphael.min.js"></script>
		<script src="/NHMP/resources/ERP/css/plugins/morris/morris.min.js"></script>
		<!-- Pignose Calender -->
		<script src="/NHMP/resources/ERP/css/plugins/moment/moment.min.js"></script>
		<script
			src="/NHMP/resources/ERP/css/plugins/pg-calendar/js/pignose.calendar.min.js"></script>
		<!-- ChartistJS -->
		<script
			src="/NHMP/resources/ERP/css/plugins/chartist/js/chartist.min.js"></script>
		<script
			src="/NHMP/resources/ERP/css/plugins/chartist-plugin-tooltips/js/chartist-plugin-tooltip.min.js"></script>



		<script src="/NHMP/resources/ERP/js/dashboard/dashboard-1.js"></script>
</body>

</html>