<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@
	page import="com.minje.nhmp.Main.vo.NursingHospitalVo"%>

<%
NursingHospitalVo loginHospital = (NursingHospitalVo)session.getAttribute("loginHospital");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>온라인 고객센터</title>
<style type="text/css">
	#selectUL{
		margin:0px;
		margin-left:350px;
		padding:0;
		padding-top:0px;
		width:300px;
		height:60px;
		background:#207dff;
		list-style:none;
		padding-top:15px;
	}
	#selectUL li a{
		float:left; 
		width:100px;
		height:60px;
		padding-top:5px;
		padding-bottom:5px;
		margin-right:0px;
		font-size: 25px;
		text-align:center;
		color:#fff;
	}
	#selectUL li a:hover{
		float:left; 
		width:100px;
		height:60px;
		padding-top:5px;
		padding-bottom:5px;
		margin-right:0px;
		font-size: 25px;
		text-align:center;
		color:#58b909;
	}
	#indiv{
		width:1200px;
		height:800px;
		margin-left:350px;
	}
	#indiv .singupT {
		width:900px;
		margin-left:150px;
		float:center;
		text-align:center;
	}
	#indiv .singupT th {
		padding-top:10px;
		padding-bottom:10px;
		width:300px;
		float:center;
		text-align:center;
	}
	#indiv .singupT td input{
		width:600px;
		float:center;
		text-align:center;
	}
	#indiv #selectTable{
		margin-left:50px;
		text-align:center;
		width:1100px;
		height:500px;
	}
	#indiv #selectTable td{
		width:33%;
	}
	#indiv .Sbtn {
		width:1100px;
		align:center;
		text-align:center;
		background:207dff;
		margin-left:50px;
	}
	#indiv .Sbtn input{
		width:150px;
		align:center;
		text-align:center;
		background:207dff;
	}
	

</style>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,500,600,700"
	rel="stylesheet">

<link rel="stylesheet"
	href="/NHMP/resources/Main/css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" href="/NHMP/resources/Main/css/animate.css">

<link rel="stylesheet"
	href="/NHMP/resources/Main/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="/NHMP/resources/Main/css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="/NHMP/resources/Main/css/magnific-popup.css">

<link rel="stylesheet" href="/NHMP/resources/Main/css/aos.css">

<link rel="stylesheet" href="/NHMP/resources/Main/css/ionicons.min.css">

<link rel="stylesheet"
	href="/NHMP/resources/Main/css/bootstrap-datepicker.css">
<link rel="stylesheet"
	href="/NHMP/resources/Main/css/jquery.timepicker.css">


<link rel="stylesheet" href="/NHMP/resources/Main/css/flaticon.css">
<link rel="stylesheet" href="/NHMP/resources/Main/css/icomoon.css">
<link rel="stylesheet" href="/NHMP/resources/Main/css/style.css">
</head>
<body>

	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand" href="/NHMP/index.jsp">이 민 삼 수</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="/NHMP/index.jsp"
						class="nav-link">Home</a></li>
					<!--  <li class="nav-item"><a href="views/main/about.html" class="nav-link">About</a></li> -->
					<li class="nav-item"><a
						href="/NHMP/views/Main/serviceIntro.jsp" class="nav-link">서비스
							소개</a></li>
					<li class="nav-item"><a
						href="/NHMP/views/Main/functionIntro.jsp" class="nav-link">기능소개</a></li>
					<li class="nav-item"><a
						href="/NHMP/views/Main/serviceCenter.jsp" class="nav-link">온라인
							고객센터</a></li>
					<li class="nav-item"><a href="/NHMP/views/Main/introduce.jsp"
						class="nav-link">이민삼수 소개</a></li>
					<% if(loginHospital == null) { //미 로그인%>
					<li class="nav-item"><a href="/NHMP/MLogPage"
						class="nav-link"><span>로그인</span></a></li>
					<% } else if ( loginHospital.getAUTHORITY_CODE().equals("G1") ) {	//관리자 로그인%>
					<li class="nav-item"><a href="/NHMP/views/Main/login.jsp"
						class="nav-link"> <span>내정보</span></a>
					<li class="nav-item cta"><a
						href="/NHMP/views/ERP/Admin_main.jsp" class="nav-link"><span>시스템
								관리</span></a></li>
					</li>
					<% } else if(loginHospital.getAUTHORITY_CODE().equals("G0")) { //미가입자 로그인%>
					<li class="nav-item"><a href="/NHMP/views/Main/login.jsp"
						class="nav-link"> <span>내정보</span></a> <!-- <li class="nav-item cta"><a href="/NHMP/views/ERP/main.jsp" class="nav-link"><span>시스템 관리</span></a></li> -->
					</li>
					<% }else{ //일반 사용자 %>
					<li class="nav-item"><a href="/NHMP/views/Main/login.jsp"
						class="nav-link"> <span>내정보</span></a>
					<li class="nav-item cta"><a
						href="/NHMP/views/ERP/Admin_main.jsp" class="nav-link"><span>시스템
								관리</span></a></li>
					</li>
					<% } %>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END nav -->

	<div class="hero-wrap hero-wrap-2"
		style="background-image: url('/NHMP/resources/Main/images/bg_2.jpg');"
		data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-center justify-content-center">
				<div class="col-md-8 ftco-animate text-center text-center mt-5">
					<p class="breadcrumbs mb-0">
						<span class="mr-3"><a href="index.html">Home <i
								class="ion-ios-arrow-forward"></i></a></span> <span>온라인 고객센터</span>
					</p>
					<h1 class="mb-3 bread">온라인 고객센터</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section" >

		<form action="/NHMP/servicesingup" method="post">
			<ul id="selectUL" style="padding-top: 0px;">
				<li><a href="/NHMP/views/Main/serviceCenter.jsp">가입하기</a></li>
				<li><a href="/NHMP/allqna">QnA</a></li>
				<li><a href="/NHMP/gongall">공지사항</a></li>
			</ul>
			<div id="indiv">
				<% if (loginHospital == null) {	%>
					<h1 align="center">회원 가입 후 신청해주세요.</h1>
				<% } else { %>
				<% if(loginHospital.getNH_SERVICE_CODE().equals("GS0")) { %>
				<table class="singupT">
					<tr>
						<th>이름 :
							<td>
								<input type="text" value="<%= loginHospital.getNH_NAME() %>"></td>
							</th>
						</tr>
						<tr> 
							<th>회사명 : 
								<td>
									<input type="text" value="<%= loginHospital.getCOMPANY_NAME() %>">
								</td>
							</th>
						</tr>
						<tr> 
							<th>연락받을 이메일 :
								<td>
									<input type="email" value="<%= loginHospital.getNH_EMAIL() %>">
								</td>
							</th>
						</tr>
						<tr> 
							<th>연락받을 전화번호 :
								<td>
									<input type="text" value="<%= loginHospital.getNH_PHONE() %>">
								</td>
							</th>
						</tr>
				</table>
				<table id="selectTable">
					<tr>
						<td>소규모의 병원이 사용하는 기능들 입니다.<br> 최대 환자 50명, 직원 10명, 관리자 1명을 사용할 데이터를 제공합니다.</td>
						<td style=" height: 50%;">중규모의 병원이 사용하는 기능들 입니다.<br> 최대 환자 100명, 직원 30명, 관리자 2명을 사용할 데이터를 제공합니다.</td>
						<td style="height: 50%;">대규모의 병원이 사용하는 기능들 입니다.<br> 최대 환자 300명, 직원 50명, 관리자 3명을 사용할 데이터를 제공합니다.</td>
					</tr>
					<tr>
						<td style=" height: 50%;">경량화 <br>
							<input type="radio" name="selectType" value="basic"
										style="width:25px;height:25px;">
					</td>
						<td style=" height: 50%;">고급형 <br>
							<input type="radio" name="selectType" value="advanced"
										style="width:25px;height:25px;">
						</td>
						<td>프리미엄 <br>
							<input type="radio" name="selectType" value="premium"
										style="width:25px;height:25px;">
						</td>
				</tr>
				</table>
				<div class="Sbtn">
					<br>
					<input type="submit" value="신청하기" style="margin-right:10px;">
					<input type="reset" value="취소" style="margin-left:10px;">
				</div>
			
			</div>
		</form>
			<% }else if(loginHospital.getNH_SERVICE_CODE().equals("GS1")) { %>
					<h1 align="center">서비스 승인 대기중...</h1>
			<% }else { %>
					<h1 align="center">이미 서비스를 이용중입니다.</h1>
			<% } %>
		<% } %>
		
	</section>
	
	<footer class="ftco-footer ftco-bg-dark ftco-section">
		<div class="container">
			<div class="row mb-5"></div>
			<div class="row">
				<div class="col-md-12 text-center">

					<p>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;
						<script>document.write(new Date().getFullYear());</script>
						All rights reserved | This template is made with <i
							class="icon-heart" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>
				</div>
			</div>
		</div>
	</footer>



	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>


	<script src="/NHMP/resources/Main/js/jquery.min.js"></script>
	<script src="/NHMP/resources/Main/js/jquery-migrate-3.0.1.min.js"></script>
	<script src="/NHMP/resources/Main/js/popper.min.js"></script>
	<script src="/NHMP/resources/Main/js/bootstrap.min.js"></script>
	<script src="/NHMP/resources/Main/js/jquery.easing.1.3.js"></script>
	<script src="/NHMP/resources/Main/js/jquery.waypoints.min.js"></script>
	<script src="/NHMP/resources/Main/js/jquery.stellar.min.js"></script>
	<script src="/NHMP/resources/Main/js/owl.carousel.min.js"></script>
	<script src="/NHMP/resources/Main/js/jquery.magnific-popup.min.js"></script>
	<script src="/NHMP/resources/Main/js/aos.js"></script>
	<script src="/NHMP/resources/Main/js/jquery.animateNumber.min.js"></script>
	<script src="/NHMP/resources/Main/js/scrollax.min.js"></script>
	<script src="/NHMP/resources/Main/js/main.js"></script>

</body>
</html>