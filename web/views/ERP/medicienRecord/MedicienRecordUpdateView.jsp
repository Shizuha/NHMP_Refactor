<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ERP.Employee.model.vo.Employee"%>
<%
	Employee emp = (Employee) session.getAttribute("loginEmployee");
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>NHMP</title>
<style type="text/css">
	.filebox.bs3-success label {
		color: #fff;
		background-color: #7571f9;
		border-color: #4cae4c;
		margin:0px;
		width:100%;
	}
	.filebox input[type="file"] {  /* 파일 필드 숨기기 */
		position: absolute;
		width: 1px;
		height: 1px;
		padding: 0;
		margin:0px;
		overflow: hidden;
		clip:rect(0,0,0,0);
		border: 0;
	}
	
	input[type="number"]{
		float:center;
		width:100%;
	}
	
	input[type="date"]{
		float:center;
		width:100%;
	}
	
	input[type="text"]{
		float:center;
		width:100%;
	}
	input[type=button],input[type="submit"],input[type="reset"]{
		background-color: #7571f9;
		border: none;
		color:#fff;
		border-radius:3px;
		text-align: center;
		text-decoration: none;
	    display: inline-block;
	    font-size: 15px;
	    margin: 4px;
	}
	
	div #root{
		width:100%;
		height:100%;
	}
	div #root h2{
		float:center;
		margin:0px;
		border:0px;
		padding:0px;
		
	}
	div #root table{
		float:center;
		
	}
	
	
	
	
</style>


<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="/NHMP/resources/ERP/images/common/favicon.png">
<!-- 메인 CSS 처리용 -->
<link href="/NHMP/resources/ERP/css/style.css?after" rel="stylesheet">
<link href="/NHMP/resources/ERP/css/employeeListViewCss.css"
	rel="stylesheet">



<!-- 스크립트태그 -->
<script type="text/javascript"
	src="/NHMP/resources/ERP/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
</script>
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
				<%
					if (emp != null) {
				%>
				<a href="/NHMP/views/ERP/Employee.jsp"> <b class="logo-abbr"><img
						src="/NHMP/resources/ERP/images/logo.png" alt=""> </b> <span
					class="logo-compact"><img
						src="/NHMP/resources/ERP/images/logo-compact.png" alt=""></span> <span
					class="brand-title"> <img
						src="/NHMP/resources/ERP/images/common/logo-text.png" alt="">
				</span>
				</a>
				<%
					} else {
				%>
				<a href="/NHMP/views/ERP/Admin_main.jsp"> <b class="logo-abbr"><img
						src="/NHMP/resources/ERP/images/logo.png" alt=""> </b> <span
					class="logo-compact"><img
						src="/NHMP/resources/ERP/images/logo-compact.png" alt=""></span> <span
					class="brand-title"> <img
						src="/NHMP/resources/ERP/images/common/logo-text.png" alt="">
				</span>
				</a>
				<%
					}
				%>
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
										<li><a href="/NHMP/ERP/views/Employee/calendar.jsp"><i
												class="icon-lock"></i> <span>일정관리</span></a></li>
										<li><a href="/NHMP/logout"><i class="icon-key"></i> <span>로그아웃</span></a></li>
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
				<%
					if (emp != null) {
				%>
				<ul class="metismenu" id="menu">
					<%
						if (emp.getAuthorityCode().equals("G5")) {
					%>
					<li class="mega-menu mega-menu-sm"><a class="has-arrow"
						href="javascript:void()" aria-expanded="false"> <i
							class="fa fa-users"></i><span class="nav-text">인사관리</span>
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/list">전체사원조회</a></li>
							<li><a href="/NHMP/views/ERP/Employee/InsertEmployee.jsp">인사정보등록</a></li>
							<li><a href="/NHMP/ochart">조직도</a></li>
						</ul></li>
					<%
						}
					%>
					<%
						if (emp.getAuthorityCode().equals("G2")) {
					%>
					<li><a class="has-arrow" href="javascript:void()"
						aria-expanded="false"> <i class="fa fa-plus-square"></i><span
							class="nav-text">환자 관리</span> <!--   <i class="icon-screen-tablet menu-icon"></i><span class="nav-text">환자 관리</span> -->
					</a>
						<ul aria-expanded="false">
							<li><a href="/NHMP/patientlistview">전체환자 조회</a></li>
							<li><a href="/NHMP/views/ERP/patient/PatientInsertView.jsp">환자
									입원 등록</a></li>
							<li><a
								href="/NHMP/views/ERP/counselingLog/CounselingLogInsertView.jsp">상담일지
									등록</a></li>
							<li><a
								href="/NHMP/views/ERP/medicienRecord/MedicienRecordInsertView.jsp">투약일지
									등록</a></li>
						</ul></li>
					<%
						}
					%>
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
					<%
						} else {
					%>
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
								class="nav-text">환자 관리</span>
						</a>
							<ul aria-expanded="false">
								<li><a href="/NHMP/patientlistview">전체환자 조회</a></li>
								<li><a href="/NHMP/views/ERP/patient/PatientInsertView.jsp">환자
										입원 등록</a></li>
								<li><a
									href="/NHMP/views/ERP/counselingLog/CounselingLogInsertView.jsp">상담일지
										등록</a></li>
								<li><a
									href="/NHMP/views/ERP/medicienRecord/MedicienRecordInsertView.jsp">투약일지
										등록</a></li>
							</ul></li>
						<li><a class="has-arrow" href="javascript:void()"
							aria-expanded="false"> <i class="fa fa-usd"></i><span
								class="nav-text">급여 관리</span>
						</a>
							<ul aria-expanded="false">
								<li><a href="/NHMP/Epaylist">급여계산</a></li>
							</ul> <%
 	}
 %>
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
		<div class="content-body" align="center" style="padding: 40px;">
			<h2 align="center">투약일지 수정 페이지</h2>
			<form action="/NHMP/recordupdate" method="post"
				enctype="multipart/form-data">
				<table style="align:center; width:800; border:2; cellspacing:0; cellpadding:3">
					<tr>
						<th style="font-size: 16px;">투약일지번호</th>
						<td style="font-size: 16px;"><input type="number"
							name="mr_no"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">날짜</th>
						<td style="font-size: 16px;"><input type="date"
							name="mr_date"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">증상</th>
						<td style="font-size: 16px;"><input type="text"
							name="mr_state"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">투약명</th>
						<td style="font-size: 16px;"><input type="text"
							name="mr_name"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">투약시간</th>
						<td style="font-size: 16px;"><input type="text"
							name="mr_time"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">용량</th>
						<td style="font-size: 16px;"><input type="text"
							name="mr_many"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">비고</th>
						<td style="font-size: 16px;"><input type="text"
							name="mr_comment"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">환자명</th>
						<td style="font-size: 16px;"><input type="text"
							placeholder="ex) 김환자, 이환자" name="mr_pat_name"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">투약자</th>
						<td style="font-size: 16px;"><input type="text"
							placeholder="ex) 김투약, 이투약"name="mr_emp_name"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">첨부파일</th>
						<td style="font-size: 16px;">
							<div class="filebox bs3-success">
							  <label for="ex_file2">업로드</label> <input type="file" name="mr_original_filename"> 
							</div>
						</td>
					</tr>
					<tr>
						<th colspan="2" align="center" padding="40px">
							<input type="submit"value="수정하기" style="font-size: 16px;margin-left:140px;"> 
							<input type="reset" value="초기화" style="font-size: 16px;margin-left:10px;">
							<a style="float:right;"href="javascript:history.go(-1)"
							style="font-size: 16px;">이전페이지로 이동></a></th>
				</table>
			</form>
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
	<!-- <script src="/NHMP/resources/ERP/js/styleSwitcher.js"></script> -->

	<!-- 	<script src="/NHMP/resources/ERP/common/common.min.js"></script>
	<script src="/NHMP/resources/ERP/js/custom.min.js"></script>
	<script src="/NHMP/resources/ERP/js/settings.js"></script>
	<script src="/NHMP/resources/ERP/js/gleek.js"></script> -->

</body>

</html>