<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@
	page import="Main.NursingHospital.model.ov.NursingHospital"
%>

<%
	NursingHospital loginHospital = (NursingHospital)session.getAttribute("loginHospital");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>이민삼수 소개</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,500,600,700" rel="stylesheet">

    <link rel="stylesheet" href="/NHMP/resources/Main/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/animate.css">
    
    <link rel="stylesheet" href="/NHMP/resources/Main/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/magnific-popup.css">

    <link rel="stylesheet" href="/NHMP/resources/Main/css/aos.css">

    <link rel="stylesheet" href="/NHMP/resources/Main/css/ionicons.min.css">

    <link rel="stylesheet" href="/NHMP/resources/Main/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/jquery.timepicker.css">

    
    <link rel="stylesheet" href="/NHMP/resources/Main/css/flaticon.css">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/icomoon.css">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/style.css">
  </head>
  <body>
    
	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="/NHMP/index.jsp">이 민 삼 수</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item active"><a href="/NHMP/index.jsp" class="nav-link">Home</a></li>
                  <!--  <li class="nav-item"><a href="views/main/about.html" class="nav-link">About</a></li> -->
                  <li class="nav-item"><a href="/NHMP/views/Main/serviceIntro.jsp" class="nav-link">서비스 소개</a></li>
                  <li class="nav-item"><a href="/NHMP/views/Main/functionIntro.jsp" class="nav-link" >기능소개</a></li>
                  <li class="nav-item"><a href="/NHMP/views/Main/serviceCenter.jsp" class="nav-link">온라인 고객센터</a></li>
                  <li class="nav-item"><a href="/NHMP/views/Main/introduce.jsp" class="nav-link">이민삼수 소개</a></li>
                  <% if(loginHospital == null) { //미 로그인%>
                  	<li class="nav-item"><a href="/NHMP/MLogPage" class="nav-link"><span>로그인</span></a></li>
                  <% } else if ( loginHospital.getAUTHORITY_CODE().equals("G1") ) {	//관리자 로그인%> 
                  	<li class="nav-item">
                  		<a href="/NHMP/views/Main/login.jsp" class="nav-link">
                  		<span>내정보</span></a>
                  		<li class="nav-item cta"><a href="/NHMP/views/ERP/Admin_main.jsp" class="nav-link"><span>시스템 관리</span></a></li>
                  	</li>
                  <% } else if(loginHospital.getAUTHORITY_CODE().equals("G0")) { //미가입자 로그인%>
                  	<li class="nav-item">
                  		<a href="/NHMP/views/Main/login.jsp" class="nav-link">
                  		<span>내정보</span></a>
                  		<!-- <li class="nav-item cta"><a href="/NHMP/views/ERP/main.jsp" class="nav-link"><span>시스템 관리</span></a></li> -->
                  	</li>
                  <% }else{ //일반 사용자 %>
                  	<li class="nav-item">
                  		<a href="/NHMP/views/Main/login.jsp" class="nav-link">
                  		<span>내정보</span></a>
                  		<li class="nav-item cta"><a href="/NHMP/views/ERP/Admin_main.jsp" class="nav-link"><span>시스템 관리</span></a></li>
                  	</li>
                  <% } %>
              
              <%-- <% if(true) { // 미회원이 보는 화면%>
              	&nbsp;
              <% } else if( loginEmployee.equals("admin") ) { // 관리자 로그인 시 %>
              	<li class="nav-item cta"><a href="/NHMP/views/ERP/main.jsp" class="nav-link"><span>시스템 관리</span></a></li>
              <% } else { // 요양병원 가입자 접속 시 %>
              	&nbsp;
              <% } %> --%>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->

    <div class="hero-wrap hero-wrap-2" style="background-image: url('/NHMP/resources/Main/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
          <div class="col-md-8 ftco-animate text-center text-center mt-5">
          	<p class="breadcrumbs mb-0"><span class="mr-3"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>이민삼수 소개</span></p>
            <h1 class="mb-3 bread">이민삼수 소개</h1>
          </div>
        </div>
      </div>
    </div>
  
    <section class="ftco-section contact-section bg-primary">
      <div class="container">
        <div class="row d-flex mb-5 contact-info justify-content-center">
        	<div class="col-md-8">
        		<div class="row mb-5">
		          <div class="col-md-4 text-center py-4">
		          	<div class="icon">
		          		<span class="icon-map-o"></span>
		          	</div>
		            <p><span>회사 주소 :</span> KH정보교육원 서울특별시 강남구 강남구 테헤란로14길 6</p>
		          </div>
		          <div class="col-md-4 text-center border-height py-4">
		          	<div class="icon">
		          		<span class="icon-mobile-phone"></span>
		          	</div>
		            <p><span>Phone:</span>010-5315-8130</p>
		          </div>
		          <div class="col-md-4 text-center py-4">
		          	<div class="icon">
		          		<span class="icon-envelope-o"></span>
		          	</div>
		            <p><span>Email:</span>rlaghrb123@naver.com</p>
		          </div>
		        </div>
          </div>
        </div>
      </div>
    </section>
    
    <section class="ftco-section">
      <div class="container">
        <div class="row d-flex justify-content-center">
          <div class="col-md-10 text-center d-flex ftco-animate">
          	<div class="blog-entry justify-content-end">
              <a href="#" class="block-20 img" style="background-image: url('/NHMP/resources/Main/images/image_1.jpg');">
              </a>
              <div class="text pt-4 px-md-5">
                <h3 class="heading mt-2">기업을 위한 모든 CLOUD ERP와 Service를 제공하는 대한민국 대표 ICT 기업 TMTS</h3>
                <p>기업정보화 선도기업인 더존은 정보화에 필요한 각종 Solution과 Service를 제공하는 대한민국 대표 ICT기업으로 고객의 경쟁력 강화에 기여해 왔습니다.</p>
           		<p>TMTS은 ERP등 병원 정보화 소프트웨어 분야에서 시장 점유율 1위 기업입니다.</p>
           		<p>2019년 TMTS는 강남으로로 본사를 이전하며 클라우드 사업을 위한 핵심 거점 'T-클라우드 센터'를 구축, 클라우드와 연계된 클라우드 플랫폼, 첨단기술이 융합된 다양한 미래형 서비스 모델을 선보이고 있습니다.</p>
           		<p>ERP전문 요양병원ICT로 시장을 개척한 더존은 클라우드, 모바일 등 최첨단 기술을 통해 기업이 나아갈 바를 정확히 알고 기업의 탄생과 성장에 반드시 필요한 심장과 같은 역할을 수행하며 그 비전을 만들어 가고 있습니다.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">TMTS</h2>
              <p>누구보다 빠르고 효율적인 ERP를 추구하는 회사</p>
              <ul class="ftco-footer-social list-unstyled mb-0">
                <li class="ftco-animate"><a href="https://twitter.com/"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="https://www.facebook.com/"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="https://www.instagram.com/"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 ml-md-5">
              <h2 class="ftco-heading-2">여러 서버들</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">Servers</a></li>
                <li><a href="#" class="py-2 d-block">Windos Hosting</a></li>
                <li><a href="#" class="py-2 d-block">Cloud Hosting</a></li>
                <li><a href="#" class="py-2 d-block">OS Servers</a></li>
                <li><a href="#" class="py-2 d-block">Linux Servers</a></li>
                <li><a href="#" class="py-2 d-block">Policy</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">네비게이터</h2>
              <ul class="list-unstyled">
                <li><a href="/NHMP/index.jsp" class="py-2 d-block">홈</a></li>
                <li><a href="/NHMP/views/Main/serviceIntro.jsp" class="py-2 d-block">서비스 소개</a></li>
                <li><a href="/NHMP/views/Main/functionIntro.jsp" class="py-2 d-block">기능소개</a></li>
                <li><a href="/NHMP/views/Main/serviceCenter.jsp" class="py-2 d-block">온라인 고객센터</a></li>
                <li><a href="/NHMP/views/Main/introduce.jsp" class="py-2 d-block">이민삼수 소개</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
                <h2 class="ftco-heading-2">사무실</h2>
                <div class="block-23 mb-3">
                      <ul>
                        <li><span class="icon icon-map-marker"></span><span class="text">KH정보교육원 서울특별시 강남구 강남구 테헤란로14길 6</span></li>
                        <li><a href="#"><span class="icon icon-phone"></span><span class="text">02-1544-9970</span></a></li>
                        <li><a href="#"><span class="icon icon-envelope"></span><span class="text">rlaghrb123@naver.com</span></a></li>
                      </ul>
                    </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">

            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | TMTS <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


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
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="/NHMP/resources/Main/js/google-map.js"></script>
  <script src="/NHMP/resources/Main/js/main.js"></script>
    
  </body>
</html>