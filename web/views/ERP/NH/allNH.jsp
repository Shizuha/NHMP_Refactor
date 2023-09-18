<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
%>

<%@ 
	page import="Main.vo.NursingHospitalVo,java.util.ArrayList"
%>	
<%
	NursingHospitalVo loginAdmin = (NursingHospitalVo)session.getAttribute("loginHospital");
		ArrayList<NursingHospitalVo> list = (ArrayList<NursingHospitalVo>)request.getAttribute("list");
	%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>TMTS</title>
<style type="text/css">
	#rootdiv{
		width:70%;
		margin-left:50px;
		margin-top:50px;
	}
	#rootdiv #NHlist_talbe{
		width:100%;
		float:left;
		text-align:center;
		background:#f4f4f4;
		color:#000;
	}
	#rootdiv #NHlist_talbe th{
		background: rgba(117, 113, 249, 0.5);
    	color:rgba(0, 0, 0, 1);
    	text-align:center;
    	font-weight:bold;
	}
	#rootdiv #NHlist_talbe td{
		background:rgba(181, 179, 247, 0.44);
    	color:rgba(0, 0, 0, 1);
    	text-align:center;
    	font-weight:bold;
	}
</style>
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="/NHMP/resources/ERP/images/common/favicon.png">
<!-- Pignose Calender -->
<link
	href="/NHMP/resources/ERP/css/plugins/pg-calendar/css/pignose.calendar.min.css"
	rel="stylesheet">
<!-- Chartist -->
<link rel="stylesheet"
	href="/NHMP/resources/ERP/css/plugins/chartist/css/chartist.min.css">
<link rel="stylesheet"
	href="/NHMP/resources/ERP/css/plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
<!-- Custom Stylesheet -->
<link href="/NHMP/resources/ERP/css/style.css?after" rel="stylesheet">
<!-- 캘린더 -->
<link href='/NHMP/resources/ERP/FullCaldendar/core/main.css' rel='stylesheet' />
<link href='/NHMP/resources/ERP/FullCaldendar/daygrid/main.css' rel='stylesheet' />
<link href='/NHMP/resources/ERP/FullCaldendar/list/main.css' rel='stylesheet' />

<script src='/NHMP/resources/ERP/FullCaldendar/core/main.js'></script>
<script src='/NHMP/resources/ERP/FullCaldendar/daygrid/main.js'></script>
<script src='/NHMP/resources/ERP/FullCaldendar/core/locales/ko.js'></script>
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
				<a href="/NHMP/views/ERP/master_main.jsp"> <b class="logo-abbr"><img
						src="/NHMP/resources/ERP/images/common/logo.png" alt=""> </b> <span
					class="logo-compact"><img
						src="/NHMP/resources/ERP/images/common/logo-compact.png" alt=""></span>
					<span class="brand-title"> <img align="middle"
						src="/NHMP/resources/ERP/images/common/logo-text.png" ailgn="">
				</span>
				</a>
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
										<li><a href="/NHMP/views/ERP/Calendar.jsp" onClick="window.open(this.href, '', 'width=800px, height=600px, left=400, top=200'); return false;"><i
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
							class="fa fa-users"></i><span class="nav-text">인사관리</span> <!-- <i class="icon-globe-alt menu-icon"></i><span class="nav-text">인사설정</span>-->
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/allNH">전체고객조회</a></li>
						</ul></li>
					<!-- <li class="nav-label">Apps</li> -->
					<li><a class="has-arrow" href="javascript:void()"
						aria-expanded="false"> <i class="fa fa-id-card"></i> <span
							class="nav-text">권한설정</span> <!--    <i class="icon-envelope menu-icon"></i> <span class="nav-text">권한설정</span> -->
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/Cautholist">권한관리</a></li>
							<li><a href="/NHMP/Cauthmanager">권한부여관리</a></li>
						</ul></li>
					
			</div>
			</ul>
		</div>

		<!--**********************************
            Sidebar end
        ***********************************-->

		<!--**********************************
            Content body start
        ***********************************-->
		<div class="content-body">
			<div id="rootdiv">
				<table id="NHlist_talbe" style="width:1000; cellspacing:0; cellpadding:5; border:1; float:left">
					<tr>
						<th align="center">고객번호</th>
						<th align="center">이름</th>
						<th align="center">가입일</th>
						<th align="center">연락처</th>
						<th align="center">이메일</th>
						<th align="center">회사명</th>
						<th align="center">사업자등록번호</th>
						<th align="center">권한코드</th>
					</tr>
					<%
					for(NursingHospitalVo NH : list){
					%>
					<tr align="center">
						<td><%= NH.getNH_ID() %></td>
						<td><%= NH.getNH_NAME() %></td>
						<td><%= NH.getNH_DATE() %></td>
						<td><%= NH.getNH_PHONE() %></td>
						<td><%= NH.getNH_EMAIL() %></td>
						<td><%= NH.getCOMPANY_NAME() %></td>
						<td><%= NH.getCOMPANY_NO() %></td>
						<td><%= NH.getAUTHORITY_CODE() %></td>
					<% } %>
				</table>
			</div>
				<!-- #/ container -->
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



		<!-- <script src="/NHMP/resources/ERP/js/dashboard/dashboard-1.js"></script> -->
</body>

</html>
