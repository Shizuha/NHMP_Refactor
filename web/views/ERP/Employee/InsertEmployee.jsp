<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ERP.Employee.model.vo.Employee"%>
<%
Employee emp = (Employee)session.getAttribute("loginEmployee");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>TMTS</title>
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="/NHMP/resources/ERP/images/common/favicon.png">
<!-- Pignose Calender -->
<link
	href="/NHMP/resources/ERP/css/plugins/pg-calendar/css/pignose.calendar.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="/NHMP/resources/ERP/css/plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css?after">
<!-- 메인 css 링크 -->
<link href="/NHMP/resources/ERP/css/style.css" rel="stylesheet">
<link href="/NHMP/resources/ERP/css/insertEmployee.css" rel="stylesheet">
<!-- 폰트 링크 추후 확인후 삭제 -->
<link href="/NHMP/resources/ERP/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/png" sizes="16x16"
	href="/NHMP/resources/ERP/images/common/favicon.png">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  
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

                var guideTextBox = document.getElementById("sample4_jibunAddress");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                   

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    
                } else {
                    guideTextBox.innerHTML = '';
                    
                }
            }
        }).open();
    }
</script>

    <!-- 스크립트 영역~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
<script type="text/javascript" src="/NHMP/resources/ERP/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

function formCheck(){
	
		  //휴대폰 끝자리
	      var regExp2 = /^\d{4}$/;
	  	  //휴대전화 정규식
	      var regExp = /^\d{3,4}$/;
	      //전화번호 정규식
	      var adTel1 = /^\d{2,3}$/;
	      var adTel2 = /^\d{3,4}$/;
	      var adTel3 = /^\d{4}$/;
	      //급여 정규식 숫자만
	      var salary = /^\d{1,}$/;
	      //주민번호 정규식
	      var empNo = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$/g;
	      //주민번호 뒷자리 정규식
	      var empNo2 = /^[1-4][0-9]{6}$/g;
	      //부양가족 이름 정규식
	      var fy_namechk = /^[가-힣a-zA-Z]{2,9}$/;
	  	 
	     /*  var shDate = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
     
	      if(!shDate.test($("input[name=HIRE_DATE]").val())) {            
	          alert("입사일짜 날짜 형식에 부합하지 않습니다");
	           $("input[name=HIRE_DATE]").focus();
	           return false;
	 		}
	      if(!shDate.test($("input[name=LAST_DATE]").val())) {            
	    	  alert("퇴사일짜 날짜 형식에 부합하지 않습니다");
	    	  $("input[name=LAST_DATE]").focus();
	           return false;
	 		}
	      if(!shDate.test($("input[name=AD_DATE]").val())) {            
	    	  alert("입학년월 날짜 형식에 부합하지 않습니다");
	    	  $("input[name=AD_DATE]").focus();
	           return false;
	 		}
	      if(!shDate.test($("input[name=GR_DATE]").val())) {            
	    	  alert("졸업년월 날짜 형식에 부합하지 않습니다");
	    	  $("input[name=GR_DATE]").focus();
	           return false;
	 		} */
	      
      if(!regExp2.test($("input[name=phone3]").val())) {            
         alert("휴대폰번호 끝자리는 3~4자리 사이로 입력하셔야 합니다.")
          $("#phone3").focus();
          return false;
		}
    
		
      if(!regExp.test($("input[name=phone2]").val())) {
    	  alert("휴대폰번호 중간리는 3~4자리 사이로 입력하셔야 합니다.")
    	  $("#phone2").focus();
          $("#phone2").val("");
          
          return false;
		}
      
     
      if(!adTel1.test($("input[name=adtel1]").val())) {            
          alert("전화 첫 번째 자리는 숫자 2자리~3자리 또는 숫자만 입력하십시오.");
          $("input[name=adtel1]").focus();
          return false;
		}
      if(!adTel2.test($("input[name=adtel2]").val())) {            
          alert("전화 두번째 자리는 숫자 3~4자리 또는 숫자만 입력하십시오.");
          $("input[name=adtel2]").focus();
          return false;
		}
      if(!adTel3.test($("input[name=adtel3]").val())) {            
          alert("전화 마지막 자리는 숫자 4자리 또는 숫자만 입력하십시오.");
          $("input[name=adtel3]").focus();
          return false;
		}
     
      if(!salary.test($("input[name=salary]").val())) {            
          alert("기본급은 특수기호없이 숫자만 입력하십시오.");
          return false;
		}
      
      if(!empNo.test($("input[name=empno1]").val())) {            
          alert("주민번호 형식에 맞지 않습니다.");
          $("input[name=empno1]").focus();
          return false;
		}
     
      if(!empNo2.test($("input[name=empno2]").val())) {            
    	  alert("주민번호 형식에 맞지 않습니다.");
    	  $("input[name=empno2]").focus();
          return false;
		}
  	
  	
  	if(!fy_namechk.test($("input[name=fyname]").val())){
 	  	alert("한글 또는 영문 2~9자리 사이로 입력 하셔야 합니다.");
 	   $("input[name=empname]").focus();
 	   return false;
 	  }
  	
    if( !idReg.test( $("input[name=empids]").val() ) ) {
  	  $(".succesIdCheck").css("display", "none");
  	  $(".errorIdCheck").css("display", "none");
  	  $(".IdCheck").css("display", "inline-block");
  	  $("#userId").focus();
        return false;
    }
    
    
    if($("input[name=email]").val() != null){
		var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	      
	      if(!emailRule.test($("input[name=email]").val())) {  
	    	  alert("email형식을 확인해주세요.")
	    	  $("input[name=email]").focus();
	                  return false;
	      	}
    }
    
   
  	return true;
}
</script>
<script type="text/javascript">
$(function(){
	
	
	
	
	$("#empname").on("focusout",function(){
		//사원이름 중복 체크 
		
		if($(this).val() != ""){
			var reg = /^[가-힣]{2,4}$/;
			var result = $(this).val()
			if(!reg.test(result)){
				  alert("이름형식을 확인하세요");
				  $(this).val("");
				   $(this).focus();
				   return false;
				  }
	      $.ajax({
	  		url: "/NHMP/namechk",
	  		type: "post",
	  		data: { empname : $("input[name=empname]").val()},
	  		success: function(data){
	  			console.log("success :" + data.length);
	  			
	  			if(data.trim() == "ok"){
	  				$(".empname").css("display", "inline-block");
					$(".empname2").css("display", "none");
	  				$("input[name=empname]").select();
	  				return false;
	  			}else{
	  				$(".empname2").css("display", "inline-block");
					$(".empname").css("display", "none");
	  				
	  			}
	  		},error : function(jqXHR,  textStatus,  errorThrown){//자료형은 자바스크립트에서 붙여주지 않는다.
	  	         console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
	  	       return false;
	  	      			}
	  				});
		  		
			}
		});
	
	$("#email").on("focusout",function(){
		if($(this).val() != ""){
		if($("input[name=email]").val() != ""){
			var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		      
		      if(!emailRule.test($("input[name=email]").val())) {            
		    	 		 $(".EmailCheck").css("display", "inline-block");
		    	 		$(".errorEmailCheck").css("display", "none");
		    	 		$(".succesEmailCheck").css("display", "none");
		    	 		 $("input[name=email]").val("");
		    	 		 $("input[name=email]").focus();
		                  return false;
		      	}
			$.ajax({
				url: "/NHMP/emailChk",
				type: "post",
				data: { email : $("input[name=email]").val()},
				success: function(data){
					console.log("success :" + data.length);
					
					if(data.trim() == "ok"){
						$(".errorEmailCheck").css("display", "inline-block");
						$(".succesEmailCheck").css("display", "none");
						$("input[name=email]").select();
						return false;
					}else{
						$(".succesEmailCheck").css("display", "inline-block");
						$("#userPwd").focus();
						$(".EmailCheck").css("display", "none");
						$(".errorEmailCheck").css("display", "none");
						return false;
					}
					
				},error : function(jqXHR,  textStatus,  errorThrown){//자료형은 자바스크립트에서 붙여주지 않는다.
			         console.log("error : " + jqXHR + ", " + textStatus + ", " + errorThrown);
			      }
				});
			
		}else	
			return false;
		}
	});	
	
	
		$("#empids").on("focusout",function(){
			
			if($("input[name=empids]").val() != ""){
				//아이디 정규식
				  var idReg = /^[a-zA-Z]+[a-zA-Z0-9]{5,19}$/g;
			      if( !idReg.test( $("input[name=empids]").val() ) ) {
			    
			    	  $(".succesIdCheck").css("display", "none");
			    	  $(".errorIdCheck").css("display", "none");
			    	  $(".IdCheck").css("display", "inline-block");
			    	  $(this).val("");
			    	  $(this).focus();
			          return false;
			      }else{
			    	  $(".IdCheck").css("display", "none");
			    	  $(".succesIdCheck").css("display", "inline-block");
			    	  
			      
				$.ajax({
					url: "/NHMP/idchk",
					type: "post",
					data: { userid : $("input[name=empids]").val()},
					success: function(data){
						console.log("success :" + data.length);
						
						if(data.trim() == "ok"){
							$(".errorIdCheck").css("display", "inline-block");
							$(".succesIdCheck").css("display", "none");
							$("#empids").val("");
							$("#empids").focus();
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
			      }
			}else	
				return false;
			e.stopPropagation();
		});
		
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
		//경력사항 삭제시 체크된 경력정보행 삭제
		$(".btn6").click(function(){
			
            if($("input[name=comchk]").is(":checked") == true){ //체크된 요소가 있으면               
                  var i = $("input[name=comchk]:checked").parents("tr");
                           i.remove();
              }else {
                  alert("기본값은 삭제할수 없습니다")
              }
        	
            return false;
         });
		//학력정보 삭제시 체크된 학력정보행 삭제처리
		$(".btn4").click(function(){
			
            if($("input[name=shchk]").is(":checked") == true){ //체크된 요소가 있으면               
                  var i = $("input[name=shchk]:checked").parents("tr");
                                       
                     i.remove();
              }else {
                  alert("기본값은 삭제할수 없습니다")
              }
        	
            return false;
         });
		//부양가족 삭제시 체크된 가족행 삭제처리
		$(".btn2").click(function(){
			
            if($("input[name=fychk]").is(":checked") == true){ //체크된 요소가 있으면               
                  var i = $("input[name=fychk]:checked").parents("tr");
                                       
                     i.remove();
                     
                  
              }else {
                  alert("기본값은 삭제할수 없습니다");
                  return false;
              }
      		return false;
           
         });
		
		$("input").on("focus",function(){
			
			$(this).css({"box-shadow":"0px 0px 5px #7571f9"});
			
		});
		$("input").on("focusout",function(){
			
			$(this).css({"box-shadow":"none"});
			
		});
		$(window).keydown(function(event){
		    if(event.keyCode == 13) {
		      event.preventDefault();
		      return false;
		    }
		  });
		
    });
</script>
<script type="text/javascript">
$(function(){
	//부양가족 추가버튼 누를시 처리
	$(".btn1").click(function(){
		
		$(".insertemp3").append("<tr>"
				+ "<td><input type='checkbox' class='fychk' name='fychk'></td>"
				+ "<td>"
				+ "	<select id='hold' name='hold'>"
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
	$("#emppwd2").on("focusout", function(event){
		
		 //암호정규식
	      var passRule = /^[a-zA-Z](([a-zA-Z])|([0-9])){5,12}$/gi;//숫자와 문자 포함 형태의 6~12자리 이내의 암호 정규식
	      
	      if(!passRule.test($("#emppwd").val())) {
	         
	    	  alert("암호는 첫번째 영문자를 포함한 숫자와 문자 포함 형태의 1~9자리 이내의 암호로 입력하셔야 합니다.");
	       		$("#emppwd").val("");
	       		$("#emppwd2").val("");
	       		$("#emppwd").focus();
	       		
	          return false;
	      }
		
		
			if($("#emppwd").val() != $(this).val()){
				$(".checkPwd").css("display", "inline-block");
				$("#emppwd").val("");
	       		$("#emppwd2").val("");
				$("#emppwd").focus();
				$(".checkPwd2").css("display", "none");
				return false;
				
				
				
			}else{
				$(".checkPwd2").css("display", "inline-block");
				$("input[name=email]").select();
				$(".checkPwd").css("display", "none");
				
				
				
				return false;
			}
		
		e.stopPropagation();
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
		$(".ear1up").eq("0").prop("selected", true);
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
	
});
</script>
<style>
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
.insertemp3 ,.insertemp4 ,.insertemp5 td{
text-align:center;
}
.btn1, .btn2{
    float:right;
    display: inline-block;
    width:80px;
    background:#7571f9;
 	color:#fff;
  	border:none;
  	position:relative;
  	cursor:pointer;
  	transition:600ms ease all;
  	outline:none;
  	padding:1px;
  	border-radius:3px;
  	margin:4px;
    }
    .btn3, .btn4{
     float:right;
    display: inline-block;
    width:80px;
    background:#7571f9;
 	color:#fff;
  	border:none;
  	position:relative;
  	cursor:pointer;
  	transition:600ms ease all;
  	outline:none;
  	padding:1px;
  	border-radius:3px;
  	margin:4px;
    }
    .btn5, .btn6{
     float:right;
    display: inline-block;
    width:80px;
    background:#7571f9;
 	color:#fff;
  	border:none;
  	position:relative;
  	cursor:pointer;
  	transition:600ms ease all;
  	outline:none;
  	padding:1px;
  	border-radius:3px;
  	margin:4px;
    }
   .btn2:hover{
    background:#fff;
  	color:#7571f9;
  	border: 1px solid #7571f9;
    }
    .btn1:hover{
     background:#fff;
  	color:#7571f9;
  	border: 1px solid #7571f9;
    }
     .btn3:hover{
    background:#fff;
  	color:#7571f9;
  	border: 1px solid #7571f9;
    }
    .btn4:hover{
     background:#fff;
  	color:#7571f9;
  	border: 1px solid #7571f9;
    }
     .btn5:hover{
    background:#fff;
  	color:#7571f9;
  	border: 1px solid #7571f9;
    }
    .btn6:hover{
     background:#fff;
  	color:#7571f9;
  	border: 1px solid #7571f9;
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
			<%if(emp != null){ %>
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
				<%if(emp != null){ %>
				<ul class="metismenu" id="menu">
					<%if(emp.getAuthorityCode().equals("G5")){ %>
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
						<%if(emp.getAuthorityCode().equals("G2")){ %>
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
						<li><a href="/NHMP/nlist" aria-expanded="false"> <i
							class="fa fa-slideshare"></i> <span class="nav-text">공지사항</span>
					</a></li>
					<li><a href="/NHMP/drlist" aria-expanded="false"> <i
							class="fa fa-download"></i> <span class="nav-text">자료실</span>
					</a></li>
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
							<li><a href="/NHMP/views/ERP/counselingLog/CounselingLogInsertView.jsp">상담일지 등록</a></li>
							<li><a href="/NHMP/views/ERP/medicienRecord/MedicienRecordInsertView.jsp">투약일지 등록</a></li>
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
						<li><a href="/NHMP/nlist.ad" aria-expanded="false"> <i
							class="fa fa-slideshare"></i> <span class="nav-text">공지사항</span>
					</a></li>
					<li><a href="/NHMP/drlist.ad" aria-expanded="false"><i
							class="fa fa-download"></i> <span class="nav-text">자료실</span>
					</a></li>
						<%} %>
					</ul>
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
        	<form action="/NHMP/empin" method="post"  onsubmit="return formCheck();" enctype="multipart/form-data">
			<table class="insertemp" style="cellpadding:6; cellspacing:0" >
				<tr>
					<th>성명(한글)</th>
						<td>
						<input type="text" name="empname" id="empname" style="border-radius:5px;" required>&nbsp;
						<span class="empname" style="color:red; display:none;"><b>이름이 중복 됩니다<i class="fa fa-thumbs-o-down" aria-hidden="true"></i></b></span>
						<span class="empname2" style="color:green; display:none;"><b>멋진 이름이네요 <i class="fa fa-thumbs-o-up" aria-hidden="true"></i></b></span>
						<span class="empname3" style="color:red; display:none;"><b>이름형식을확인해주세요(한글,영어 2~9자 이내)<i class="fa fa-thumbs-o-down" aria-hidden="true"></i></b></span>
						</td>
					<th>고용형태</th>
						<td>
							<select id="empment" name="empment" style="border-radius:5px;" required>
								<option value="EM7">--고용형태--</option> 
								<option value="EM1">일용직</option>
								<option value="EM2">위촉직</option>
								<option value="EM4">계약직</option>
								<option value="EM3">정규직</option>
								<option value="EM6">임시직</option>
								<option value="EM5">파견직</option>
								<option value="EM7">인턴</option>
							</select>
						</td>
				</tr>
				<tr>
				<th>부서</th>
					<td>
						<select id="dept" name="dept" style="border-radius:5px;" required>
							<option value="10">--부서구분--</option> 
							<option value="10">가정의학과</option>
							<option value="30">한방과</option>
							<option value="40">간호과</option>
							<option value="60">원무과</option>
							<option value="50">총무과</option>
							<option value="20">외과</option>
						</select>
					</td>
				<th>직위</th>
					<td>
						<select id="job" name="job" style="border-radius:5px;" required>
							<option value="0">--선택--</option> 
							<option value="PO2">과장</option>
							<option value="PO3">사원</option>
							<option value="PO5">간호사</option>
							<option value="PO1">병원장</option>
							<option value="PO4">팀장</option>
							<option value="PO6">수간호사</option>
						</select>
					</td>
				<tr>
				<tr>
				<th>내/외국인</th>
					<td>
						<select id="itfor" name="itfornal"> 
							<option value="내국인">내국인</option>
							<option value="외국인">외국인</option>
						</select>
					</td><th>주민번호</th><td><input type="text"maxlength="6" name="empno1"style="border-radius:5px;" required>-<input maxlength="7" type="text"style="border-radius:5px;" name="empno2" required></td>
				<tr>
				<tr>
					<th>주소</th>
					<td colspan="3">
						<input type="text" id="sample4_postcode"style="border-radius:5px;" placeholder="우편번호" readonly>
						<input type="button" onclick="sample4_execDaumPostcode();" value="우편번호 찾기" >&nbsp;
						<input type="text" id="sample4_roadAddress"name="address1" placeholder="도로명주소" style="border-radius:5px;" readonly>
						<input type="text" id="sample4_jibunAddress"name="address2" placeholder="지번주소" style="border-radius:5px; width:219px;" readonly><br>
						<input type="text" id="sample4_detailAddress"name="address3" placeholder="상세주소" style="border-radius:5px;">
						<input type="text" id="sample4_extraAddress"name="address4" placeholder="참고항목" style="border-radius:5px;" readonly>
					
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
						<td><input maxlength="3" type="Tel"style="border-radius:5px;" name="adtel1">-
						<input type="Tel"maxlength="4" style="border-radius:5px;" name="adtel2">-<input maxlength="4" type="Tel"style="border-radius:5px;" name="adtel3"></td>
					<th>휴대폰</th>
						<td>
							<select name="phone" style="border-radius:5px;">
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="017">017</option>
								<option value="016">016</option>
							</select>&nbsp;-<input type="Tel" maxlength="4" style="border-radius:5px;"id="phone2" name="phone2">-<input type="Tel" maxlength="4" id="phone3" style="border-radius:5px;" name="phone3">
						</td>
				</tr>
				<tr>
					<th>아이디</th>
						<td class="idtd">
						<input type="text"style="border-radius:5px;" name="empids" id="empids" required>&nbsp;
						<span class="succesIdCheck" style="display:none; color:green; font-size:10pt;"><b>사용가능한 아이디 입니다.<i class="fa fa-thumbs-o-up" aria-hidden="true"></i></b></span>
						&nbsp;<span class="errorIdCheck" style="display:none; color:red;font-size:10pt;"><b>이미 사용중인 아이디 입니다.<i class="fa fa-thumbs-o-down" aria-hidden="true"></i></b></span>
						<span class="IdCheck" style="display:none; color:red;font-size:10pt;"><b>아이디형식에 맞지않습니다<i class="fa fa-thumbs-o-down" aria-hidden="true"></i></b></span>
						</td>
					<th>비밀번호</th>
						<td><input type="password"style="border-radius:5px;" id="emppwd"name="emppwds" required>&nbsp;
						중복확인<input type="password"style="border-radius:5px;"id="emppwd2" required>
						<span class="checkPwd" style="color:red; display:none;"><b>불일치<i class="fa fa-thumbs-o-down" aria-hidden="true"></i></b></span>
						<span class="checkPwd2" style="color:green; display:none;"><b>일치<i class="fa fa-thumbs-o-up" aria-hidden="true"></i></b></span>
						</td>
				</tr>
				<tr>
					<th>이메일</th>
						<td>
						<input type="email" name="email" id="email" style="border-radius:5px;" required>
						<span class="succesEmailCheck" style="display:none; color:green; font-size:10pt;"><b>사용가능한 이메일 입니다<i class="fa fa-thumbs-o-up" aria-hidden="true"></i></b></span>
						&nbsp;<span class="errorEmailCheck" style="display:none; color:red;font-size:10pt;"><b>이미 사용중인 이메일 입니다<i class="fa fa-thumbs-o-down" aria-hidden="true"></i></b></span>
						<span class="EmailCheck" style="display:none; color:red;font-size:10pt;"><b>이메일 형식이 틀립니다<i class="fa fa-thumbs-o-down" aria-hidden="true"></i></b></span>
						</td>
					<th>기본급</th>
						<td><input type="text" name="salary"value="1750000"style="border-radius:5px;" required></td>
				</tr>
				<tr>
					<th>성별</th>
						<td><input type="radio" name="gender" value="M">남자&nbsp;
							<input type="radio" name="gender" value="F">여자</td>
					<th>이미지</th>
						<td><input type="file" name="upfiles"></td>
				</tr>
				<tr>
					<th>팀</th>
						<td>
							<select id="team" name="team" style="border-radius:5px;" required>
								<option value="TM1">--팀구분--</option> 
								<option value="TM1">병원간호팀</option>
								<option value="TM2">외래간호팀</option>
								<option value="TM3">간병팀</option>
								<option value="TM4">시설관리팀</option>
								<option value="TM5">환경팀</option>
								<option value="TM6">회계팀</option>
								<option value="TM7">홍보팀</option>
								<option value="TM8">인사교육팀</option>
								<option value="TM9">영양실</option>
								<option value="TM10">심사청구팀</option>
								<option value="TM11">의무기록팀</option>
								<option value="TM12">입원상담팀</option>
								<option value="TM13">사회복지팀</option>
								<option value="TM14">차량지원팀</option>
								<option value="TM15">약제실</option>
								<option value="TM16">물리치료실</option>
								<option value="TM17">방사선실</option>
								<option value="TM18">임상병리실</option>
							</select>
						</td>
							
					<th>재직형태</th>
						<td>
							<select id="hold" name="hold" style="border-radius:5px;" required>
								<option value="HOD2">--재직구분--</option> 
								<option value="HOD1">휴직</option>
								<option value="HOD2">재직</option>
								<option value="HOD3">퇴직</option>
							</select>
						</td>
				</tr>
				<tr>
					<th>병동</th>
						<td>
							<select id="ward" name="ward" style="border-radius:5px;" required>
								<option value="BD03">--병동구분--</option> 
								<option value="BD01">1병동</option>
								<option value="BD02">2병동</option>
								<option value="BD03">3병동</option>
							</select>
						</td>
							
					<th>권한</th>
						<td> &nbsp;사용자기본권한<input type="hidden" name="author" value="G1"></td>
				</tr>
				<tr>
					<th>기타사항</th>
						<td colspan="3"><textarea rows="4"name="etc" cols="100"></textarea></td>
				</tr>
			</table>
			<!--  급여정보 란~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
			<h2>급여정보</h2>
			<table class="insertemp2" style="cellpadding:5; cellspacing:0" >
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
						<input type="checkbox" name="earner3" class="daily" value="2.9" >일용직(2.9%)
						<input type="checkbox" name="earner4" class="etcbsn"value="8.8" >기타소득자(8.8%)
						<input type="checkbox" name="earner5" class="money3">근로/사업소득자
					</td>
				</tr>
			</table>
			<!-- 부양가족~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
			<h2>부양가족</h2>
			<div class="fbtn"><button class="btn1">추가</button>&nbsp;<button class="btn2">삭제</button></div>
				<table class="insertemp3" style="cellpadding:3; cellspacing:0">
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
					<tr>
						<td class="Chk"><input type="checkbox" class="fychk" name="fychk"></td>
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
						<td><input type="text" name="fyname" value="숙자"required></td>
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
						<td class="Chk"><input type="checkbox" class="fychk" name="fychk"></td>
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
						<td><input type="text" name="fyname" value="이모"required></td>
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
						<td class="Chk"><input type="checkbox" class="fychk" name="fychk"></td>
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
						<td><input type="text" name="fyname"value="하무이" required></td>
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
				</table>
			<!-- 학력정보페이지~~~ -->
				<h2>학력정보</h2>
			<div class="fbtn"><button class="btn3">추가</button>&nbsp;<button class="btn4">삭제</button></div>
				<table class="insertemp4" style="cellpadding:5; cellspacing:0; width:100%">
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
						<td class="Chk"><input type="checkbox" class="shchk" name="shchk"></td>
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
						<td><input type="date" name="AD_DATE"value="2015-05-18" min="1960-01-01" max="2999-12-31" required></td>
						<td><input type="date" name="GR_DATE"value="2016-04-03" min="1960-01-01" max="2999-12-31" required></td>
						<td><input type="text" name="SCH_NAME"value="좌항초등" required></td>
						<td><input type="text" name="MAJOR"value="컴퓨터" required></td>
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
						<td class="Chk"><input type="checkbox" class="shchk" name="shchk"></td>
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
						<td><input type="date" name="AD_DATE"value="2012-05-18" min="1960-01-01" max="9999-12-31" required></td>
						<td><input type="date" name="GR_DATE"value="2013-09-18" min="1960-01-01" max="9999-12-31" required></td>
						<td><input type="text" name="SCH_NAME"value="세일중" required></td>
						<td><input type="text" name="MAJOR"value="컴퓨터" required></td>
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
				</table>
				<!-- 경력사항 테이블~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
				<h2>경력사항</h2>
			<div class="fbtn"><button class="btn5">추가</button>&nbsp;<button class="btn6">삭제</button></div>
				<table class="insertemp5" style="cellpadding:1; cellspacing:0; width:100%">
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
					<tr>
						<td class="Chk"><input type="checkbox" class="comchk" name="comchk"></td>
						<td><input type="text" class="comchk" name="COM_NAME"value="퇼" required></td>
						<td><input type="date" name="HIRE_DATE"value="2015-05-18" required></td>
						<td><input type="date" name="LAST_DATE"value="2017-06-18" required></td>
						<td><input type="text" name="WORK_TEAM"value="텔레콜" required></td>
						<td><input type="text" name="LAST_POSITION"value="과장" required></td>
						<td><input type="text" name="RES_BILTIES"value="테스트" required></td>
						<td><textarea rows="1" cols="15" name="LEAVE_REASON" required>테스트입니다</textarea></td>
					</tr>
				</table>
				<div class="insert">
				<input type="submit" id="sub" value="등록하기" >&nbsp;<input type="reset" id="re" value="취소">
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