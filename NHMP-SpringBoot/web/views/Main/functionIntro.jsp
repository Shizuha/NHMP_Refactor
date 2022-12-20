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
    <title>기능소개</title>
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
          	<p class="breadcrumbs mb-0"><span class="mr-3"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>기능소개</span></p>
            <h1 class="mb-3 bread">기능소개</h1>
          </div>
        </div>
      </div>
    </div>
  
    <section class="ftco-section">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-4 text-center ftco-animate">
    				<div class="steps">
    					<div class="icon active mb-4 d-flex justify-content-center align-items-center">
    						<span>1</span>
    					</div>
    					<h3>어떤 기능을 선택할지 골라보세요!</h3>
    					<p>어떤 ERP를 사용하실지, 어떤 기능이 필요한지 고민해보세요.</p>
    				</div>
    			</div>
    			<div class="col-md-4 text-center ftco-animate">
    				<div class="steps">
    					<div class="icon mb-4 d-flex justify-content-center align-items-center">
    						<span>2</span>
    					</div>
    					<h3>계정을 만드세요!</h3>
    					<p>계정을 만들어서 당신이 바로 ERP를 사용할 수 있는 기능을 준비하세요.</p>
    				</div>
    			</div>
    			<div class="col-md-4 text-center ftco-animate">
    				<div class="steps">
    					<div class="icon mb-4 d-flex justify-content-center align-items-center">
    						<span>3</span>
    					</div>
    					<h3>실행시키세요!</h3>
    					<p>웹에서 바로 들어갈 수 있는 ERP를 사용하세요.</p>
    				</div>
    			</div>
    		</div>
    	</div>
    </section>

    <section class="ftco-section bg-primary">
    	<div class="container">
    		<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section heading-section-white ftco-animate">
            <h2 class="mb-4">최고의 가격</h2>
            <p>4개의 버전과 가격을 저렴한 요금으로 만나보세요!</p>
          </div>
        </div>
    		<div class="row no-gutters d-flex">
	        <div class="col-lg-3 col-md-6 ftco-animate">
	          <div class="block-7 bg-light">
	            <div class="text-center">
		            <h2 class="heading">데모</h2>
		            <span class="price"><sup>₩</sup> <span class="number">0<small class="per">/달</small></span>
		            <span class="excerpt d-block">100% 무료 데모입니다.</span>
		            <h3 class="heading-2 mb-3">자그마한 샘플이 들어 있습니다.</h3>
		            
		            <ul class="pricing-text mb-4">
		              <li><strong>환자</strong> 10명</li>
		              <li><strong>직원</strong> 3명</li>
		              <li><strong>관리자</strong> 1명</li>
		              <li>데모</li>
		            </ul>
		            <a href="/NHMP/views/Main/serviceCenter.jsp" class="btn btn-secondary d-block px-3 py-3 mb-4">선택하기</a>
	            </div>
	          </div>
	        </div>
	        <div class="col-lg-3 col-md-6 ftco-animate">
	          <div class="block-7">
	            <div class="text-center">
		            <h2 class="heading">경량화</h2>
		            <span class="price"><sup>₩</sup> <span class="number">10만<small class="per">/달</small></span></span>
		            <span class="excerpt d-block">1년 단위로 갱신해야합니다.</span>
		            <h3 class="heading-2 mb-3">소규모의 병원이 필요한 기능들입니다.</h3>
		            
		            <ul class="pricing-text mb-4">
		              <li><strong>환자</strong> 50명</li>
		              <li><strong>직원</strong> 10명</li>
		              <li><strong>관리자</strong> 1명</li>
		              <li>경량</li>
		            </ul>
		            <a href="/NHMP/views/Main/serviceCenter.jsp" class="btn btn-secondary d-block px-3 py-3 mb-4">선택하기</a>
	            </div>
	          </div>
	        </div>
	        <div class="col-lg-3 col-md-6 ftco-animate">
	          <div class="block-7 bg-light">
	            <div class="text-center">
		            <h2 class="heading">고급형</h2>
		            <span class="price"><sup>₩</sup> <span class="number">30만<small class="per">/달</small></span></span>
		            <span class="excerpt d-block">1년 단위로 갱신해야합니다.</span>
		            <h3 class="heading-2 mb-3">중규모 병원이 필요한 기능들입니다.</h3>
		            
		            <ul class="pricing-text mb-4">
		              <li><strong>환자</strong> 100명</li>
		              <li><strong>직원</strong> 30명</li>
		              <li><strong>관리자</strong> 2명</li>
		              <li>고급</li>
		            </ul>
		            <a href="/NHMP/views/Main/serviceCenter.jsp" class="btn btn-secondary d-block px-3 py-3 mb-4">선택하기</a>
	            </div>
	          </div>
	        </div>
	        <div class="col-lg-3 col-md-6 ftco-animate">
	          <div class="block-7">
	            <div class="text-center">
		            <h2 class="heading">프리미엄</h2>
		            <span class="price"><sup>₩</sup> <span class="number">60만<small class="per">/달</small></span></span>
		            <span class="excerpt d-block">1년 단위로 갱신해야합니다.</span>
		            <h3 class="heading-2 mb-3">대규모 병원이 필요한 기능입니다.</h3>
		            
		            <ul class="pricing-text mb-4">
		              <li><strong>환자</strong> 300명</li>
		              <li><strong>직원</strong> 50명</li>
		              <li><strong>관리자</strong> 3명</li>
		              <li>프로</li>
		            </ul>
			          <a href="/NHMP/views/Main/serviceCenter.jsp" class="btn btn-secondary d-block px-3 py-3 mb-4">선택하기</a>
	            </div>
	          </div>
	        </div>
	      </div>
    	</div>
    </section>

    <section class="ftco-section ftco-no-pt ftco-no-pb">
    	<div class="container">
    		<div class="row d-flex">
    			<div class="col-lg-6 order-lg-last d-flex">
    				<div class="bg-primary py-md-5 d-flex align-self-stretch">
    					<div class="main">
		    				<img src="/NHMP/resources/Main/images/undraw_data_report_bi6l.svg" class="img-fluid svg" alt="">
		    				<div class="heading-section heading-section-white ftco-animate mt-5 px-3 px-md-5">
			            <h2 class="mb-4">저희들의 주 기능입니다</h2>
			            <p>장기요양 서비스 환경의 Up-grade와 노인장기요양 제도의 변화에 빠른 대응이고 어르신의 상태변화에 대한 신속 대응과 가족의 요구에 대한 반응입니다. 또한 업무처리에 필요한 시스템 구축 및 업무 자동화 요구, 전문성 및 종합 요양서비스를 위한 시스템 확보 요구, 업무처리와 정보시스템간의 연계강화를 통해서 조직 운영상의 효율성 제고입니다.</p>
			          </div>
		          </div>
	          </div>
    			</div>
    			<div class="col-lg-6 py-5">
    				<div class="row pt-md-5">
    					<div class="col-md-6 ftco-animate">
    						<div class="media block-6 services text-center">
		            	<div class="icon d-flex align-items-center justify-content-center">
		            		<span class="flaticon-cloud-computing"></span>
		            	</div>
		              <div class="mt-3 media-body media-body-2">
		                <h3 class="heading">투약내역</h3>
		                <p>데이터의 코드화 및 수치화로 어르신의 상태를 실시간 분석합니다.</p>
		              </div>
		            </div>
    					</div>
    					<div class="col-md-6 ftco-animate">
    						<div class="media block-6 services text-center">
		            	<div class="icon d-flex align-items-center justify-content-center">
		            		<span class="flaticon-cloud"></span>
		            	</div>
		              <div class="mt-3 media-body media-body-2">
		                <h3 class="heading">관리자</h3>
		                <p>업무구분별로 분류가 명확한 시스템의 기능 추가, 변경 및 업그레이드 서비스를 받을 수 있습니다.</p>
		              </div>
		            </div>
    					</div>
    					<div class="col-md-6 ftco-animate">
    						<div class="media block-6 services text-center">
		            	<div class="icon d-flex align-items-center justify-content-center">
		            		<span class="flaticon-server"></span>
		            	</div>
		              <div class="mt-3 media-body media-body-2">
		                <h3 class="heading">이미지 파일</h3>
		                <p>출력되는 데이터는 이미지 파일로 변환이 가능합니다.</p>
		              </div>
		            </div>
    					</div>
    					<div class="col-md-6 ftco-animate">
    						<div class="media block-6 services text-center">
		            	<div class="icon d-flex align-items-center justify-content-center">
		            		<span class="flaticon-database"></span>
		            	</div>
		              <div class="mt-3 media-body media-body-2">
		                <h3 class="heading">교육 불필요</h3>
		                <p>사용하는데 있어 간단한 프로그램이므로 메뉴얼만 있으면 교육이 불필요합니다.</p>
		              </div>
		            </div>
    					</div>
    				</div>
          </div>
    		</div>
    	</div>
    </section>

    <section class="ftco-section bg-light">
    	<div class="container">
    		<div class="row justify-content-center mb-5">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">자주 묻는 질문들</h2>
            <p>고객분들이 자주 묻는 질문들을 정리했습니다</p>
          </div>
        </div>
    		<div class="row">
    			<div class="col-md-12 ftco-animate">
    				<div id="accordion">
    					<div class="row">
    						<div class="col-md-6">
    							<div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menuone" aria-expanded="true" aria-controls="menuone">데모버전은 언제까지 사용할 수 있나요?<span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menuone" class="collapse show">
						          <div class="card-body">
												<p>데모버전은 계속 사용하실수 있습니다. 계속 사용하시고 만족하시면 저희의 상위 제품을 사용해주시길 바랍니다.</p>
						          </div>
						        </div>
						      </div>

						      <div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menutwo" aria-expanded="false" aria-controls="menutwo">직원 아이디 비밀번호는 어떻게 만드나요?<span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menutwo" class="collapse">
						          <div class="card-body">
												<p>직원 아이디 비밀번호는 고객이 저희 제품을 신청해주신 뒤 저희가 직접 데이터베이스에 추가할 예정입니다.</p>
						          </div>
						        </div>
						      </div>

						      <div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menu3" aria-expanded="false" aria-controls="menu3">신청시 바로 ERP를 사용할 수 있나요? <span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menu3" class="collapse">
						          <div class="card-body">
												<p>아닙니다. 신청이 완료되면 저희 개발팀이 확인을 하여 빠른 시일내 사용하실수 있도록 조치를 취할 예정입니다.</p>
						          </div>
						        </div>
						      </div>
    						</div>

    						<div class="col-md-6">
    							<div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menu4" aria-expanded="false" aria-controls="menu4">사용 기간은 언제까지 인가요? <span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menu4" class="collapse">
						          <div class="card-body">
												<p>사용 기간은 1년 단위로 갱신하는 시스템이어서 1년 단위로 결제하시면 됩니다. 결제는 QNA 게시판에 글을 적어놓으시면 자세하게 얘기드리겠습니다.</p>
						          </div>
						        </div>
						      </div>

						      <div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menu5" aria-expanded="false" aria-controls="menu5">관리자 아이디 비밀번호를 잊어버리면 어떻게하나요? <span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menu5" class="collapse">
						          <div class="card-body">
												<p>관리자 아이디와 비밀번호는 저희 TMTS회사의 데이터베이스에 저장이 되있고 잊어버렸을시 저희들이 바로 발급가능하므로 걱정하실 필요는 없습니다.</p>
						          </div>
						        </div>
						      </div>

						      <div class="card">
						        <div class="card-header">
										  <a class="card-link" data-toggle="collapse"  href="#menu6" aria-expanded="false" aria-controls="menu6">교육은 따로 필요 없나요?<span class="collapsed"><i class="ion-ios-arrow-up"></i></span><span class="expanded"><i class="ion-ios-arrow-down"></i></span></a>
						        </div>
						        <div id="menu6" class="collapse">
						          <div class="card-body">
												<p>교육은 따로 필요가 없을 정도로 편리하게 ERP를 이용하실 수 있습니다. 혹시나 조금 더 알고 싶으실 경우에 저희가 드리는 메뉴얼 책을 참조하시길 바랍니다.</p>
						          </div>
						        </div>
						      </div>
    						</div>
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