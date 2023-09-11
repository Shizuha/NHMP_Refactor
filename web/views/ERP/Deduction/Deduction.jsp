<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@
	page import="Main.vo.NursingHospitalVo,java.util.ArrayList,ERP.Deduction.model.vo.Deduction"%>

<%
NursingHospitalVo loginHospital = (NursingHospitalVo)session.getAttribute("loginHospital");
	ArrayList<Deduction> list = (ArrayList<Deduction>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>TMTS</title>
<style type="text/css">

	#dlistdiv{
		float: left;
		margin-left:50px;
		width:1300px;
	}
	
	#dlistdiv .btn{
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
	#dlistdiv #BLbtn{
		float:left;
		width:1300px;
	}
	#dlistdiv #BLbtn input{
		float:left;
	}
	#dlistdiv #BLbtn #SDbtn{
		float:right;
	}
	
		
	#dlistdiv .inFbtn{
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
	
	#dlistdiv table{
		width:100%;
		float:left;
		text-align:center;
		background:#f4f4f4;
		color:#000;
	}
	
	#dlistdiv table th{
		background: rgba(117, 113, 249, 0.5);
    	color:rgba(0, 0, 0, 1);
    	text-align:center;
    	font-weight:bold;
	}
	#dlistdiv table td{
		background:rgba(181, 179, 247, 0.44);
    	color:rgba(0, 0, 0, 1);
    	text-align:center;
    	font-weight:bold;
	}
	#dlistdiv table td input{
		margin:5px;
	}
</style>



<script type="text/javascript" src="/NHMP/resources/common/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
	function showPopup(url, name) { 
		window.open(url, name, "width=570px, height=650px, left=900, top=50"); 
	}
	
	$(function(){
		var count = <%= list.size()+1 %>;
		$("#btn_add_row").click(function(){
			var lastTr = 'lastTr'+count;
			var last = $('#'+lastTr);
			count +=1;
			var addtr = '<tr align="center" id="lastTr'+count+'">'+
						'	<td><input type="checkbox" style="text-align: center; vertical-align: middle; width: 1.0rem; height: 1.0rem"></td>'+
						'	<td><input type="text" name="Dname" style="text-align:center; width:100px;" placeholder="공제명"></td>'+
						'	<td><input type="text" name="Dno" value="'+count+'" readonly style="text-align:center; width:25px;"></td>'+
						'	<td><input type="text" name="Dcode" style="text-align:center; width:50px;" placeholder="코드" value="gong'+count+'" readonly></td>'+
						'	<td><input type="text" name="Dformula" id="Formula'+count+'" style="text-align:center; width:100px;" placeholder="계산식"></td>'+
						'	<td><input type="checkbox" id="inFCheck'+count+'" onclick="inFCheck('+count+')" style="text-align: center; vertical-align: middle; width: 1.0rem; height: 1.0rem"></td>'+
						'	<td><input type="button" class="inFbtn'+count+'" value="수식 입력" onclick="showPopup('+"'/NHMP/insertF?Bnum="+count+"','insertF'"+');"disabled></td>'+
						'	<td><input type="text" name="Detc" style="text-align:center; width:100px;" placeholder="설명"></td>'+
						'</tr>';
			last.after(addtr);  
		});
		
		$("#btn_delete_row").click(function(){
			if(count >= <%= list.size()+2 %>){
				var lastTr = 'lastTr'+count;		
				var last = $('#'+lastTr);
				last.remove();
				count -= 1;	
			}
		});
		
		$("#SDbtn").click(function(){
			var cnt = 0;
			for (var i = 1; i <= count-1; i++){
				var Did = "DnoCh"+i;
				if($("#"+Did).is(':checked') == true){
					var code = $("#Dcode"+i).val();
					$.ajax({
						url : "/NHMP/delDD",
						type : "post",
						data : {code : code},
						success : function(data){
							location.href = "/NHMP/deduclise"
							
						}, error : function(jqXHR, textStatus, errorThrown){
							console.log("error : " + jqXHR + ", " + textStatus + ", " +errorThrown);
						}
						
					});
					
				}
			}
			
			if($('input[type="checkbox"][class="Dcheck"]').is(':checked')){
				
			}else{
				//체크가 없으면
				alert("선택 삭제를 위해서는 저장된 공제 중 한 개 이상의 공제을 선택하여주십시오");
				return false;
			}
			
		});
		
	});
	
	
	function inFCheck(no){
		var checkbtn = "inFbtn"+no; //체크박스	
		var $checkbtn = $("#"+checkbtn);
		var inFbtn = "inFbtn"+no;	//수식넣기 버튼
		var $inFbtn = $("."+checkbtn);
		if( $inFbtn.attr("disabled") == "disabled" ){
			console.log($inFbtn.attr("disabled"));	
			$inFbtn.removeAttr("disabled");
		}else{
			console.log($inFbtn.attr("disabled") == "disabled");
			$inFbtn.attr("disabled", true);
		}
		
		
	}
/* 
// disabled 처리
$("#txtBox").attr("disabled",true);

// disabled 삭제
$("#txtBox").removeAttr("disabled"); */
	
	
	
</script>
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
<!-- Custom Stylesheet -->
<link href="/NHMP/resources/ERP/css/style.css?after" rel="stylesheet">
<!-- icon CDN 방식 적용 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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
				<% if(loginHospital.getAUTHORITY_CODE().equals("G1")){ %>
					<a href="/NHMP/views/ERP/master_main.jsp"> <b class="logo-abbr">
					<img src="/NHMP/resources/ERP/images/common/logo.png" alt=""> </b> 
					<span class="logo-compact"><img src="/NHMP/resources/ERP/images/common/logo-compact.png" alt=""></span>
					<span class="brand-title"> <img align="middle" src="/NHMP/resources/ERP/images/common/logo-text.png" ailgn=""></span>
					</a>
				<% }else if (loginHospital.getAUTHORITY_CODE().equals("G3") || loginHospital.getAUTHORITY_CODE().equals("G4") || loginHospital.getAUTHORITY_CODE().equals("G5")){ %>
					<a href="/NHMP/views/ERP/Admin_main.jsp"> <b class="logo-abbr">
					<img src="/NHMP/resources/ERP/images/common/logo.png" alt=""> </b> 
					<span class="logo-compact"><img src="/NHMP/resources/ERP/images/common/logo-compact.png" alt=""></span>
					<span class="brand-title"> <img align="middle" src="/NHMP/resources/ERP/images/common/logo-text.png" ailgn=""></span>
					</a>
				<% }else{ %>
					<a href="/NHMP/views/ERP/Employee.jsp"> <b class="logo-abbr">
					<img src="/NHMP/resources/ERP/images/common/logo.png" alt=""> </b> 
					<span class="logo-compact"><img src="/NHMP/resources/ERP/images/common/logo-compact.png" alt=""></span>
					<span class="brand-title"> <img align="middle" src="/NHMP/resources/ERP/images/common/logo-text.png" ailgn=""></span>
					</a>
				<% } %>
			</div>
		</div>
		<!--**********************************
            Nav header end
        ***********************************-->

		<!--**********************************
            	상단바 시작
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
										<li><a href="/NHMP/views/ERP/Calendar.jsp"><i
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
				<ul class="metismenu" id="menu">
					<li class="mega-menu mega-menu-sm"><a class="has-arrow"
						href="javascript:void()" aria-expanded="false"> <i
							class="fa fa-users"></i><span class="nav-text">인사관리</span> 
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/list">전체사원조회</a></li>
							<li><a href="/NHMP/views/ERP/Employee/InsertEmployee.jsp">인사정보등록</a></li>
							<li><a href="/NHMP/ochart">조직도</a></li>
						</ul></li>
					<li><a class="has-arrow" href="javascript:void()"
						aria-expanded="false"> <i class="fa fa-id-card"></i> <span
							class="nav-text">권한설정</span> 
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/authlist">권한부여관리</a></li>
						</ul></li>
					<li><a class="has-arrow" href="javascript:void()"
						aria-expanded="false"> <i class="fa fa-plus-square"></i><span
							class="nav-text">환자 관리</span> <!--   <i class="icon-screen-tablet menu-icon"></i><span class="nav-text">환자 관리</span> -->
					</a>

						<ul aria-expanded="false">
							<li><a href="/NHMP/patientlistview">전체환자 조회</a></li>
							<li><a href="/NHMP/views/ERP/patient/PatientInsertView.jsp">환자 입원 등록</a></li>
							<li><a href="/NHMP/views/ERP/counselingLog/CounselingLogInsertView.jsp">상담일지 등록</a></li>
							<li><a href="/NHMP/views/ERP/medicienRecord/MedicienRecordInsertView.jsp">투약일지 등록</a></li>
						</ul></li>
					<li><a class="has-arrow" href="javascript:void()"
						aria-expanded="false"> <i class="fa fa-usd"></i><span
							class="nav-text">급여 관리</span> <!--    <i class="icon-grid menu-icon"></i><span class="nav-text">급여 관리</span>  -->
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/deduclise">공제항목등록</a></li>
							<li><a href="/NHMP/allowlist">수당항목등록</a></li>
							<li><a href="/NHMP/paylist">급여계산</a></li>
						
						</ul>
					<li><a href="/NHMP/nlist.ad" aria-expanded="false"> <i
							class="fa fa-slideshare"></i> <span class="nav-text">공지사항</span>
					</a></li>
					<li><a href="/NHMP/drlist.ad" aria-expanded="false"> <i
							class="fa fa-download"></i> <span class="nav-text">자료실</span>
					</a></li>
				</ul>
			</div>
		</div>

		<!--**********************************
            Sidebar end
        ***********************************-->

		<!--**********************************
            Content body start
        ***********************************-->
		<div class="content-body" align="center" >
			<br>
			<div id="dlistdiv">
			<!-- <form action="" method="post"> -->
				<div id="BLbtn">
					<input type="button" value="┼" id="btn_add_row" class="btn"> &nbsp;
					<input type="button" value="─" id="btn_delete_row" class="btn">	
					<input type="button" id="SDbtn" value="선택삭제" class="btn">
				</div>
			<!-- </form>	 -->
				<br>
				<br>	
				<form action="/NHMP/deducinsert" method="post">
					<table width="1000" cellspacing="0" cellpadding="5" border="1" id="deduction_talbe" style="float: left; ">
						<tr>
							<th>선택</th>
							<th align="center">공제명칭</th>
							<th align="center">표시순서</th>
							<th align="center">계정코드</th>
							<th align="center">계산식</th>
							<th align="center">계산항목</th>
							<th align="center">수식 입력</th>
							<th align="center">비고</th>
						</tr>
						<% for(Deduction d : list){%>
						<tr align="center">
							<td><input type="checkbox" class="Dcheck" id="DnoCh<%= d.getDEDUCTION_NO() %>" style="text-align: center; vertical-align: middle; width: 1.0rem; height: 1.0rem"></td>
							<td><input type="text" value="<%= d.getDEDUCTION_NAME() %>" readonly style="text-align:center; width:100px;"></td>
							<td><input type="text" class="Dno" value="<%= d.getDEDUCTION_NO() %>" readonly style="text-align:center; width:25px;"></td>
							<td><input type="text" id="Dcode<%= d.getDEDUCTION_NO() %>" value="<%= d.getDEDUCTION_CODE() %>" readonly style="text-align:center; width:50px;"></td>
							<td><input type="text" value="<%= d.getDEDUCTION_FORMULA() %>" readonly style="text-align:center; width:100px;"></td>
							<td><input type="checkbox" id="inFCheck<%= d.getDEDUCTION_NO() %>" onclick="inFCheck(<%= d.getDEDUCTION_NO() %>)" style="text-align: center; vertical-align: middle; width: 1.0rem; height: 1.0rem"></td>
							<td><input type="button" class="inFbtn<%= d.getDEDUCTION_NO() %>" value="수식 입력" onclick="showPopup('/NHMP/insertF?Bnum=<%= d.getDEDUCTION_NO() %>', 'insertF');"disabled></td>
							<td><%= d.getDEDUCTION_NAME() %><br> <% if( !d.getDEDUCTION_NAME().equals("null") ) {%>
								<div id="Deduction">
									<div>
										<span><i class="fa fa-info-circle" aria-hidden="true"></i></span>
										<p class="arrow_box"><%= d.getDEDUCTION_ETC() %></p>
									</div>
								</div> <% } %>
						</tr>
						<% } %>
						<tr align="center" id="lastTr<%= list.size()+1 %>">
							<td><input type="checkbox" style="text-align: center; vertical-align: middle; width: 1.0rem; height: 1.0rem"></td>
							<td><input type="text" name="Dname" style="text-align:center; width:100px;" placeholder="공제명"></td>
							<td><input type="text" name="Dno" value="<%= list.size()+1 %>" readonly style="text-align:center; width:25px;"></td>
							<td><input type="text" name="Dcode" style="text-align:center; width:50px;" placeholder="코드" value="gong<%= list.size()+1 %>" readonly></td>
							<td><input type="text" name="Dformula" id="Formula<%= list.size()+1 %>" style="text-align:center; width:100px;" readonly placeholder="계산식"></td>
							<td><input type="checkbox" id="inFCheck<%= list.size()+1 %>" onclick="inFCheck(<%= list.size()+1 %>)" style="text-align: center; vertical-align: middle; width: 1.0rem; height: 1.0rem"></td>
							<td><input type="button"  class="inFbtn<%= list.size()+1 %>" value="수식입력"  onclick="showPopup('/NHMP/insertF?Bnum=<%= list.size()+1 %>', 'insertF');"disabled></td>
							<td><input type="text" name="Detc" style="text-align:center; width:100px;" placeholder="설명"></td>
						</tr>
						<!-- 디비에서 list 받아서 가져오기 -->
						<tr>
							<td colspan="8" align="center">
								<input type="submit" value="저장" class="btn" width="50px" > &nbsp;
								<input type="reset" value="초기화" class="btn" width="50px">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
			<!--**********************************
            Content body end
        ***********************************-->


			<!--**********************************
            Footer start
        ***********************************-->
			<div class="footer">
				<div class="copyright">
					<p>
						Copyright &copy; Designed & Developed by <a
							href="https://themeforest.net/user/quixlab">이민삼수</a> 2019
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
