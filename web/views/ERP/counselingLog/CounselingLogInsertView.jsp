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
	function movelist() {
		location.href = "/NHMP/counsellistview";
		return false;
	}
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
			<h2 align="center">상담일지 등록 페이지</h2>
			<form action="/NHMP/counselinsert" method="post"
				enctype="multipart/form-data">
				<table style="align:center; width:800; cellspacing:0; cellpadding:5; border:3">
					<tr>
						<th style="font-size: 16px;">제목</th>
						<td><input type="text" name="cl_title" id="cl_title"
							style="font-size: 16px; width: 400px;"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">내용</th>
						<td><input type="text" name="cl_contents" id="cl_contents"
							style="font-size: 16px; width: 400px;"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">연락처</th>
						<td><input type="tel" name="cl_phonef"
							style="font-size: 16px; width: 129px;" maxlength="3">-
							<input type="tel" name="cl_phonem"
							style="font-size : 16px; width: 129px;" maxlength="4">-
							<input type="tel" name="cl_phoneb"
							style="font-size : 16px; width: 129px;" maxlength="4"></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">비고</th>
						<td><input type="text" name="cl_comment" id="cl_comment"
							style="font-size: 16px; width: 400px;""></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">환자명</th>
						<td><input type="text" name="cl_pat_name" id="cl_pat_name"
							style="font-size: 16px; width: 400px;""></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">상담자</th>
						<td><input type="text" name="cl_emp_name" id="cl_emp_name"
							placeholder="ex) 김상담, 이상담"style="font-size: 16px; width: 400px;""></td>
					</tr>
					<tr>
						<th style="font-size: 16px;">첨부파일</th>
						<td><input type="file" name="cl_original_filename"
							id="cl_original_filename" style="font-size: 16px; width: 400px;""></td>
					</tr>
					<tr>
						<th colspan="2" align="center"><input type="submit"
							value="등록하기" style="font-size: 16px; margin-left : 250px;"> &nbsp; &nbsp;
							&nbsp; <input type="reset" value="취소하기" style="font-size: 16px;">
							&nbsp; &nbsp; &nbsp; <input type="button" value="조회하기"
							onclick="return movelist();" style="font-size: 16px;">
							&nbsp; &nbsp; &nbsp; <a href="javascript:history.go(-1)"
							style="font-size: 16px; float : right;">이전페이지로 이동</a></th>
					</tr>
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
					Copyright &copy; Designed And Developed by <a
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