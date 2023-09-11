<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@
	page import="Main.vo.NursingHospitalVo"
%>

<%
NursingHospitalVo loginHospital = (NursingHospitalVo)session.getAttribute("loginHospital");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>서비스 소개</title>
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
          	<p class="breadcrumbs mb-0"><span class="mr-3"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>서비스 소개</span></p>
            <h1 class="mb-3 bread">서비스 소개</h1>
          </div>
        </div>
      </div>
    </div>

    <!-- <section class="ftco-domain">
    	<div class="container">
    		<div class="row d-flex align-items-center justify-content-center">
    			<div class="col-lg-10 p-5 ftco-wrap ftco-animate">
    				<form action="#" class="domain-form d-flex mb-3">
              <div class="form-group domain-name">
                <input type="text" class="form-control name px-4" placeholder="Enter your domain name...">
              </div>
              <div class="form-group domain-select d-flex">
	              <div class="select-wrap">
                  <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                  <select name="" id="" class="form-control">
                  	<option value="">.com</option>
                    <option value="">.net</option>
                    <option value="">.biz</option>
                    <option value="">.co</option>
                    <option value="">.me</option>
                  </select>
                </div>
                <input type="submit" class="search-domain btn btn-primary text-center" value="Search">
	            </div>
            </form>
            <p class="domain-price mt-2 text-center">
            	<span><small>.com</small> $9.75</span> 
            	<span><small>.net</small> $9.50</span> 
            	<span><small>.biz</small> $8.95</span> 
            	<span><small>.co</small> $7.80</span>
            	<span><small>.me</small> $7.95</span>
            </p>
    			</div>
    		</div>
    	</div>
    </section> -->
  
    <section class="ftco-section services-section bg-light ftco-no-pb">
      <div class="container">
      <div class="row justify-content-center mb-5">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">NHMP</h2>
            <p>Nursing Hospital Management Program</p>
          </div>
        </div>
      	<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">그 많은 고객들이 저희를 선택하신 이유</h2>
            <p>다양한 서비스가 한 곳에서 제공되는 요양병원에 최적화된 업무환경이 필요합니다.</p>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-start">
            	<div class="icon d-flex align-items-center justify-content-center">
            		<span class="flaticon-cloud"></span>
            	</div>
              <div class="media-body pl-4">
                <h3 class="heading">빠른 도입</h3>
                <p class="mb-0">설치형 프로그램이 아니기 때문에 도입 결정과 동시에 즉시 사용할 수 있습니다.</p>
              </div>
            </div>      
          </div>
          <div class="col-lg-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-start">
            	<div class="icon d-flex align-items-center justify-content-center">
            		<span class="flaticon-server"></span>
            	</div>
              <div class="media-body pl-4">
                <h3 class="heading">매일마다 백업</h3>
                <p class="mb-0">사용자가 입력한 자료는 개인 PC나 자체 서버, USB가 아닌 TMTS 서버에 안전하게 보관됩니다.</p>
              </div>
            </div>    
          </div>
          <div class="col-lg-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-start">
            	<div class="icon d-flex align-items-center justify-content-center">
            		<span class="flaticon-customer-service"></span>
            	</div>
              <div class="media-body pl-4">
                <h3 class="heading">전문적인 고객상담</h3>
                <p>전화, 게시판 상담 외에도 요청에 따라 원격으로 문제를 해결할 수 있습니다.</p>
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
              <a href="#" class="block-20 img" style="background-image: url('/NHMP/resources/Main/images/image_4.jpg');">
              </a>
              <div class="text pt-4 px-md-5">
                <h3 class="heading mt-2">PC설치형과 클라우드 설치형 중에 선택을 할 수 있습니다.</h3>
                <h4>PC설치형</h4>
                <p>서버 PC와 직원 PC에 프로그램 및 DB 설치 방식 프로그램이 설치된 PC에서만 사용 가능</p>
           		<p>서버, 스토리지, 네트워크, 보안 등 제반 ICT자원을 기업이 직접 관리</p>
           		<h4>클라우드 설치형</h4>
           		<p>인터넷을 통해 로그인하여 사용하는 방식 (프로그램 설치 불필요)</p>
           		<p>프로그램이 설치되어 있지 않은 PC는 물론 모바일 기기(스마트폰, 태블릿)에서도 사용 가능</p>
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