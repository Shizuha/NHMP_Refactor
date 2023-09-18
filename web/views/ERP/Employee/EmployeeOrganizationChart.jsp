<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ERP.Department.model.vo.Department,
				ERP.Team.model.vo.Team,
				ERP.Employee.model.vo.Employee,
				ERP.Ward.model.vo.Ward,
				ERP.Position.model.vo.Position,
				java.util.ArrayList" 
%>
<%
	Employee emp = (Employee)session.getAttribute("loginEmployee");
	@SuppressWarnings("unchecked")
	ArrayList<Employee> mList = (ArrayList<Employee>)request.getAttribute("mList");
	@SuppressWarnings("unchecked")
	ArrayList<Department> dList = (ArrayList<Department>)request.getAttribute("dList");
	/* ArrayList<Team> tList = (ArrayList<Team>)request.getAttribute("tList");
	ArrayList<Ward> wList = (ArrayList<Ward>)request.getAttribute("wList");
	ArrayList<Position> pList =(ArrayList<Position>)request.getAttribute("pList"); */
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


<!-- 메인 css 링크 -->
<link href="/NHMP/resources/ERP/css/style.css" rel="stylesheet">
<link href="/NHMP/resources/ERP/css/insertEmployee.css" rel="stylesheet">
<!-- 폰트 링크 추후 확인후 삭제 -->
<title>Insert title here</title>
  <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
   <script type="text/javascript">
 $(document).ready(function () {
            
            //[2] 자식 요소를 갖는 li에 대해서는 블릿이미지를 plus.gif를 지정
            $('.organ li:has(ul)') //자식 요소(ul)를 갖는 요소(li)에 대해서
                .css({cursor: 'pointer', 'list-style-image':'url(/NHMP/resources/ERP/images/closeForder.PNG)'})//+기호로 설정
                .children().hide(); //자식요소 숨기기
                
             
               
               
            //[3] +로 설정된 항목에 대해서 click이벤트 적용
            $('.deptList').click(function(event){
            	var index = $(this).index();
            	if($(event.target).is(".deptList")){
            	//클릭된 해당부서 기본정보 부서명에 추가
            	var DeptName = $(this).text().substring(0,6);
            	$("#deptname").val(DeptName);
            	
            	$.ajax({
      				url : "organlist",
      				data : { deptName : $(this).text().trim() },
      				type : "post",
      				dataType : "json",
      				success : function(data){
      					
      					var jsonStr = JSON.stringify(data);
      					
      					var json = JSON.parse(jsonStr);
      					
      					for(var i in json.list){
      						
      						$("#deptcode").val(json.list[i].deptcode);
      						$("#deptcount").val(json.list[i].count);
      						if(json.list[i].teamname != null){
      						var teamName = decodeURIComponent(json.list[i].teamname).replace(/\+/gi, " ");
      						var li = $("<li class='teamList' id='t" + teamName + "'  onclick='team(this.id, " + i + ");'>" + 
      								teamName +  "<ul></ul></li>");
      						}
      						$('.deptList').eq(index).children().append(li);
      						$('.teamList').css({'list-style-image':'url(/NHMP/resources/ERP/images/closeForder.PNG)'});
      					}
      				},
      				error :function(jqXHR, textStatus, errorThrown){
      					console.log("error : " + textStatus);
      				}
      			});
            		
            	}
                //this == event.target으로 현재 선택된 개체에 대해서 처리
                if(this == event.target){
                    //숨겨진 상태면 보이고 -기호로 설정 그렇지 않으면 숨기고 + 기호로 설정
                      if ($(this).children("ul").is(':hidden')) {
                    	  
                    	// 보이기
                          $(this).css('list-style-image', 'url(/NHMP/resources/ERP/images/openForder.PNG)').children().slideDown();
                    	  
                        
                    }else {
                        // 숨기기
                        $(this).css('list-style-image', 'url(/NHMP/resources/ERP/images/closeForder.PNG)').children().slideUp();
                        $('.teamList').css('list-style-image', 'url(/NHMP/resources/ERP/images/closeForder.PNG)').children().slideUp();
                        
                    }
 
                }
                return false;          
            });
                
        });
function team(id, index){
	
	 var value = $("#"+id).text();
	 
	 if($(".teamList").children("ul") != null){
	 $.ajax({
			url : "teamlist",
			data : { teamName : value },
			type : "post",
			dataType : "json",
			success : function(data){
				
				//전송 온 object 를 string 으로 바꿈
				var jsonStr = JSON.stringify(data);
				//string 을 json 객체로 바꿈
				var json = JSON.parse(jsonStr);
				$("#teamname").val(value);
				//json 안에 list 가 들어있음.
				if(json != null){
				for(var i in json.list){
					
					$("#teamcode").val(json.list[i].tcode);
					$("#teamcount").val(json.list[i].count);
					var empName = decodeURIComponent(json.list[i].empname).replace(/\+/gi, " ");
					if(empName != null){
					var li = $("<li class='emp' id='" + empName + "' onclick='empList(this.id," + i + ");'><i class='mdi mdi-account'></i>&nbsp;" + 
							empName +  "</li>");
					$("#"+id).children().append(li);
						}
					}
				}
				
			},
			error :function(jqXHR, textStatus, errorThrown){
				console.log("error : " + textStatus);
			}
		});
	 }
	 
	 if ($("#"+id).children("ul").is(':hidden')) {
		 $("#"+id).children().slideDown();
		 $("#"+id).css('list-style-image', 'url(/NHMP/resources/ERP/images/openForder.PNG)');
	     }else{
	    	$("#"+id).children().slideUp(); 
	    	 $("#"+id).css('list-style-image', 'url(/NHMP/resources/ERP/images/closeForder.PNG)')
	     }
	return false;  
 
	
	
 }
 
function empList(id, index){
	$.ajax({
		url : "emp",
		data : { empName : id },
		type : "post",
		dataType : "json",
		success : function(data){
			
			var jsonStr = JSON.stringify(data);
			//string 을 json 객체로 바꿈
			var json = JSON.parse(jsonStr);
			
			if(data != null){
				
				for(var i in json.list){
				$(".imgs").remove();
				$("#empImg").prepend("<img class='imgs'alt='' src='/NHMP/resources/ERP/emp_Img_file/" + json.list[i].empimg +"' width='100%' height='100%'>");
				$("#name").val(decodeURIComponent(json.list[i].empname).replace(/\+/gi, " "));
				$("#itfor").val(decodeURIComponent(json.list[i].itfor).replace(/\+/gi, " "));
				$("#phone").val(json.list[i].phone);
				$("#address").text(decodeURIComponent(json.list[i].address).replace(/\+/gi, " "));
				$("#hiredate").val(json.list[i].hiredate); 
				$("#email").val(json.list[i].email); 
				}
				return false;
			}
			
		},
		error :function(jqXHR, textStatus, errorThrown){
			
			console.log("error : " + textStatus);
		}
		
	});
	event.stopPropagation();
	return false;
}


</script> 

<style type="text/css">
table th {
background-color: #7571f9;
}
body{
	color:black;
	font-family: Georgia, "맑은 고딕", serif;
}
.footer{
	margin-top:600px;
}
.organMain{
	
	margin:50px;
	background:white;
	border-radius:5px;
	display:inline-block;
	padding:20px;
	overflow:auto;
	box-shadow:1px 1px 1px 2px #dddddd;
	
}
.organHeader{
	width:100%;
	display:inline-block;
		
}
 .organHeader-left{
	width:450px;
	float:left;
	
	
}
.organHeader-right{
	
	display:inline-block;
	float:right;
}
h3{
	margin: 0px 0px 0px 0px;	
}
.h3{
	float:left;
	margin:0px 460px 0px 0px;
} 
.dept{
	border-radius:3px;
	
	width:100%;
	height:470px;
	padding:5px;
	box-shadow:1px 1px 1px 2px #dddddd;
	
}
.dept-left{
	border-radius:3px;
	box-shadow:1px 1px 3px 2px #dddddd;
	float:left;
	width:430px;
	padding:30px;
	height:460px;
	background: rgba(117, 113, 249, 0.17);
}
.dept-right{
	border-radius:3px;
	box-shadow:1px 1px 3px 2px #dddddd;
	float:right;
	width:550px;
	height:460px;
	padding:20px;
	background: rgba(117, 113, 249, 0.17);
	
}
/*  input[type="checkbox"]:checked~ul {
        display:none;
        transition:1.5s;
    }  */ 
input[type="checkbox"]{
        display:none;
    } 
ul {
	list-style:none;
	
}
.dept li{
	margin-left:20px;
	width:100px
}
.organ{
	border-radius:10px;
	padding:25px;
	
	height:400px;
	width:300px;
	overflow:auto;
	font-weight: bold;
	margin-left:35px;
	background:white;
	box-shadow:1px 1px 3px 2px #dddddd inset;;
}
.organ li{
	width:250px;
}

.dp-cp input[type="text"]{
	text-align:center;
	margin-left:30px;
	border:none;
}
.dp-cp {
	border:none;
	background:white;
}
.tm-cp{
	background:white;
}
.tm-cp input[type="text"]{
	text-align:center;
	margin-left:30px;
	border:none;
}
#empOne input[type="text"]{
	margin-left:0px;
	border:none;
	width:160px;
}
#bigEmp{
	display: inline-block;
	width:500px;
	height:240px;
	padding:10px;
	border-radius:3px;
	background:white;
	box-shadow: 1px 1px 5px 1px grey;
}
.empList{
	width:510px;
	height:290px;
	margin-top:10px;
	padding:10px;
	display:inline-block;
	
	
}
#empImg{
	display:inline-block;
	box-shadow:1px 1px 3px 2px #dddddd;
	width:200px;
	height:200px;
	float:left;
	border-radius:3px;
	
	
}

#detailEmp{
	margin-right:20px;
	display:inline-block;
	float:right;
	width:230px;
	height:200px;
	
	
}
#detailEmp li {
	margin-top:8px;
	width:250px;
}

.deptList li{
	width:
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
					<li><a href="/NHMP/drlist.ad" aria-expanded="false"> <i
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
			<div class="organMain">
			<div class="organHeader">
				<div class="organHeader-left">
					<h3>부서정보</h3>
				</div>
				<div class="organHeader-right">
					<h3 class="h3">기본정보</h3>
						
				</div>
			</div>
				<div class="dept">
					<div class="dept-left">
					<%if(dList != null){%>
						<div class="organ">
							<ul>
							<%for(Department d : dList){ %>
								<li class="deptList"><%= d.getDeptName()%>
								<ul></ul>
								</li>
					           <%} %>
					        </ul>
						</div>
					</div>
					<%}else{ %>
						<div class="organ">
							<ul>
					            <li>간호과
					                <ul>
					                    <li>dd</li>
					                    <li>dd</li>
					                    <li>dd</li>
					                </ul> 
					           </li>
					           <li>가정의학과
					                <ul>
					                   
					                </ul>
					            </li>
					            <li>외과
					                <ul>
					                   
					                </ul>
					            </li>
					            <li>원무과
					                <ul>
					                   
					                </ul>
					            </li>
					            <li>총무과
					                <ul>
					                   
					                </ul>
					            </li>
					            <li>한방과
					                <ul>
					                   
					                </ul>
					            </li>
					        </ul>
						</div>
					</div>
					<%} %>
					<div class="dept-right">
					<table class="dp-cp" style="width:100%;">
						<tr>
						<th>부서명</th><th>부서코드</th><th>부서인원</th>
						</tr>
						<tr>
						<td><input type="text" name="deptname" id="deptname"readonly></td>
						<td><input type="text" name="deptcode" id="deptcode"readonly></td>
						<td><input type="text" name="deptcount" id="deptcount"readonly></td>
						</tr>
					</table>
					<br />
					<table class="tm-cp" style="width:100%;">
						<tr>
						<th>팀 명&nbsp;</th><th>팀코드&nbsp;</th><th>팀인원&nbsp;</th>
						</tr>
						<tr>
						<td><input type="text" name="teamname" id="teamname"readonly></td>
						<td><input type="text" name="teamcode" id="teamcode"readonly></td>
						<td><input type="text" name="teamcount" id="teamcount"readonly></td>
						</tr>
					</table>
					<div class="empList">
						<h4><b>사원상세정보</b></h4>
						<div id="bigEmp">
						<div id="empImg">
						
						</div>
						<div id="detailEmp">
						<ul id="empOne">
							<li><b>이름:</b><input type="text" id="name" readonly></li>
							<li><b>내/외국인:</b><input type="text" id="itfor" readonly></li>
							<li><b>입사일:</b><input type="text" id="hiredate" readonly></li>
							<li><b>이메일:</b><input type="text" id="email" readonly></li>
							<li><b>휴대폰:</b><input type="text" id="phone" readonly></li>
							<li><b>주소</b><br>
							<textarea rows="2" cols="30" id="address" readonly></textarea>
							</li>
						</ul>
						</div>
						</div>
					</div>
				</div>
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
					Copyright &copy; Designed & Developed by <a
						href="https://themeforest.net/user/quixlab">Quixlab</a> 2018
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


		



		
</body>
</html>