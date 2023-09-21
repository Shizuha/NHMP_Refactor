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
    <title>HOME</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,500,600,700" rel="stylesheet">

    <link rel="stylesheet" href="/NHMP/resources/Main/css/open-iconic-bootstrap.min.css?after">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/animate.css?after">

    <link rel="stylesheet" href="/NHMP/resources/Main/css/owl.carousel.min.css?after">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/owl.theme.default.min.css?after">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/magnific-popup.css?after">

    <link rel="stylesheet" href="/NHMP/resources/Main/css/aos.css?after">

    <link rel="stylesheet" href="/NHMP/resources/Main/css/ionicons.min.css?after">

    <link rel="stylesheet" href="/NHMP/resources/Main/css/bootstrap-datepicker.css?after">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/jquery.timepicker.css?after">


    <link rel="stylesheet" href="/NHMP/resources/Main/css/flaticon.css?after">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/icomoon.css?after">
    <link rel="stylesheet" href="/NHMP/resources/Main/css/style.css?after">
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
                  <li class="nav-item active"><a href="index.jsp" class="nav-link">Home</a></li>
                  <!--  <li class="nav-item"><a href="views/main/about.html" class="nav-link">About</a></li> -->
                  <li class="nav-item"><a href="/NHMP/views/Main/serviceIntro.jsp" class="nav-link">서비스 소개</a></li>
                  <li class="nav-item"><a href="/NHMP/views/Main/functionIntro.jsp" class="nav-link" >기능소개</a></li>
                  <li class="nav-item"><a href="/NHMP/views/Main/serviceCenter.jsp" class="nav-link">온라인 고객센터</a></li>
                  <li class="nav-item"><a href="/NHMP/views/Main/introduce.jsp" class="nav-link">이민삼수 소개</a></li>
                  <% if(loginHospital == null) { //미 로그인%>
                  	<li class="nav-item"><a href="/NHMP/MLogPage" class="nav-link"><span>로그인</span></a></li>
                  <% } else if(loginHospital.getAUTHORITY_CODE().equals("G1") && loginHospital.getNH_USERID().equals("TMTS") ) { //마스터 로그인%>
                  	<li class="nav-item">
                  		<a href="/NHMP/views/Main/login.jsp" class="nav-link">
                  		<span>내정보</span></a>
                  		<li class="nav-item cta"><a href="/NHMP/views/ERP/master_main.jsp" class="nav-link"><span>시스템 관리</span></a></li>
                  	</li>
                  <% } else if ( loginHospital.getAUTHORITY_CODE().equals("G1") || loginHospital.getAUTHORITY_CODE().equals("G3") || loginHospital.getAUTHORITY_CODE().equals("G4") || loginHospital.getAUTHORITY_CODE().equals("G5") ) {	//관리자 로그인%> 
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
                  		<li class="nav-item cta"><a href="/NHMP/views/ERP/Employee.jsp" class="nav-link"><span>시스템 관리</span></a></li>
                  	</li>
                  <% } %>
                </ul>
              </div>
            </div>
          </nav>
    <!-- END nav -->

    <div class="hero-wrap js-fullheight img" style="background-image: url(/NHMP/resources/Main/images/bg_1.jpg);">
      <div class="overlay"></div>
      <div class="container-fluid px-0">
        <div class="row d-md-flex no-gutters slider-text align-items-center js-fullheight justify-content-center">
                <div class="col-md-8 text-center d-flex align-items-center ftco-animate js-fullheight">
                        <div class="text mt-5">
                                <span class="subheading">클라우드 ERP</span>
                    <h1 class="mb-3">The Best Cloud ERP System</h1>
                    <p>최고의 클라우드 ERP 시스템을 경험해 보세요!</p>
                 <!--   <p><a href="#" class="btn btn-secondary px-4 py-3">Get Started Now</a></p> -->
                  </div>
                </div>
                </div>
      </div>
    </div>
<!-- 
    <section class="ftco-domain">
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
    </section>

 --> 
 <!--    
 	<section class="ftco-section ftco-partner">
        <div class="container">
                <div class="row">
                        <div class="col-sm ftco-animate">
                                <a href="#" class="partner"><img src="/NHMP/resources/Main/images/partner-1.png" class="img-fluid" alt="Colorlib Template"></a>
                        </div>
                        <div class="col-sm ftco-animate">
                                <a href="#" class="partner"><img src="/NHMP/resources/Main/images/partner-2.png" class="img-fluid" alt="Colorlib Template"></a>
                        </div>
                        <div class="col-sm ftco-animate">
                                <a href="#" class="partner"><img src="/NHMP/resources/Main/images/partner-3.png" class="img-fluid" alt="Colorlib Template"></a>
                        </div>
                        <div class="col-sm ftco-animate">
                                <a href="#" class="partner"><img src="/NHMP/resources/Main/images/partner-4.png" class="img-fluid" alt="Colorlib Template"></a>
                        </div>
                        <div class="col-sm ftco-animate">
                                <a href="#" class="partner"><img src="/NHMP/resources/Main/images/partner-5.png" class="img-fluid" alt="Colorlib Template"></a>
                        </div>
                </div>
        </div>
    </section>
 -->
 
 <!--    <section class="ftco-section services-section bg-light ftco-no-pb">
      <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">Why You Should Choose Us</h2>
            <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-start">
                <div class="icon d-flex align-items-center justify-content-center">
                        <span class="flaticon-cloud"></span>
                </div>
              <div class="media-body pl-4">
                <h3 class="heading">Super Fast Server</h3>
                <p class="mb-0">Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>
          </div>
          <div class="col-lg-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-start">
                <div class="icon d-flex align-items-center justify-content-center">
                        <span class="flaticon-server"></span>
                </div>
              <div class="media-body pl-4">
                <h3 class="heading">Daily Backups</h3>
                <p class="mb-0">Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>
          </div>
          <div class="col-lg-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-flex align-items-start">
                <div class="icon d-flex align-items-center justify-content-center">
                        <span class="flaticon-customer-service"></span>
                </div>
              <div class="media-body pl-4">
                <h3 class="heading">Technical Services</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="ftco-section ftco-counter bg-light img" id="section-counter">
        <div class="container">
                <div class="row justify-content-center mb-5">
          <div class="col-md-10 text-center heading-section ftco-animate">
            <h2 class="mb-4">More than 12,000 websites trusted hosted</h2>
          </div>
        </div>
                <div class="row justify-content-center">
          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
            <div class="block-18 text-center">
              <div class="text">
                <strong class="number" data-number="12000">0</strong>
                <span>CMS Installation</span>
              </div>
            </div>
          </div>
          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
            <div class="block-18 text-center">
              <div class="text">
                <strong class="number" data-number="100">0</strong>
                <span>Awards Won</span>
              </div>
            </div>
          </div>
          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
            <div class="block-18 text-center">
              <div class="text">
                <strong class="number" data-number="10000">0</strong>
                <span>Registered Domains</span>
              </div>
            </div>
          </div>
          <div class="col-md-3 d-flex justify-content-center counter-wrap ftco-animate">
            <div class="block-18 text-center">
              <div class="text">
                <strong class="number" data-number="9000">0</strong>
                <span>Satisfied Customers</span>
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
                                    <h2 class="mb-4">Our Main Services</h2>
                                    <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth.</p>
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
                                <h3 class="heading">Cloud VPS</h3>
                                <p>Even the all-powerful Pointing has no control about the blind texts</p>
                              </div>
                            </div>
                                        </div>
                                        <div class="col-md-6 ftco-animate">
                                                <div class="media block-6 services text-center">
                                <div class="icon d-flex align-items-center justify-content-center">
                                        <span class="flaticon-cloud"></span>
                                </div>
                              <div class="mt-3 media-body media-body-2">
                                <h3 class="heading">Share</h3>
                                <p>Even the all-powerful Pointing has no control about the blind texts</p>
                              </div>
                            </div>
                                        </div>
                                        <div class="col-md-6 ftco-animate">
                                                <div class="media block-6 services text-center">
                                <div class="icon d-flex align-items-center justify-content-center">
                                        <span class="flaticon-server"></span>
                                </div>
                              <div class="mt-3 media-body media-body-2">
                                <h3 class="heading">VPS</h3>
                                <p>Even the all-powerful Pointing has no control about the blind texts</p>
                              </div>
                            </div>
                                        </div>
                                        <div class="col-md-6 ftco-animate">
                                                <div class="media block-6 services text-center">
                                <div class="icon d-flex align-items-center justify-content-center">
                                        <span class="flaticon-database"></span>
                                </div>
                              <div class="mt-3 media-body media-body-2">
                                <h3 class="heading">Dedicated</h3>
                                <p>Even the all-powerful Pointing has no control about the blind texts</p>
                              </div>
                            </div>
                                        </div>
                                </div>
          </div>
                </div>
        </div>
    </section> 
    -->
<!-- 
    <section class="ftco-section bg-primary">
        <div class="container">
                <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section heading-section-white ftco-animate">
            <h2 class="mb-4">Our Best Pricing</h2>
            <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
          </div>
        </div>
                <div class="row no-gutters d-flex">
                <div class="col-lg-3 col-md-6 ftco-animate d-flex">
                  <div class="block-7 bg-light d-flex align-self-stretch">
                    <div class="text-center">
                            <h2 class="heading">Free</h2>
                            <span class="price"><sup>$</sup> <span class="number">0<small class="per">/mo</small></span>
                            <span class="excerpt d-block">100% free. Forever</span>
                            <h3 class="heading-2 mb-3">Enjoy All The Features</h3>

                            <ul class="pricing-text mb-4">
                              <li><strong>150 GB</strong> Bandwidth</li>
                              <li><strong>100 GB</strong> Storage</li>
                              <li><strong>$1.00 / GB</strong> Overages</li>
                              <li>All features</li>
                            </ul>
                            <a href="#" class="btn btn-secondary d-block px-3 py-3 mb-4">Choose Plan</a>
                    </div>
                  </div>
                </div>
                <div class="col-lg-3 col-md-6 ftco-animate d-flex">
                  <div class="block-7 d-flex align-self-stretch">
                    <div class="text-center">
                            <h2 class="heading">Startup</h2>
                            <span class="price"><sup>$</sup> <span class="number">19<small class="per">/mo</small></span></span>
                            <span class="excerpt d-block">All features are included</span>
                            <h3 class="heading-2 mb-3">Enjoy All The Features</h3>

                            <ul class="pricing-text mb-4">
                              <li><strong>450 GB</strong> Bandwidth</li>
                              <li><strong>400 GB</strong> Storage</li>
                              <li><strong>$2.00 / GB</strong> Overages</li>
                              <li>All features</li>
                            </ul>
                            <a href="#" class="btn btn-secondary d-block px-3 py-3 mb-4">Choose Plan</a>
                    </div>
                  </div>
                </div>
                <div class="col-lg-3 col-md-6 ftco-animate d-flex">
                  <div class="block-7 active d-flex align-self-stretch">
                    <div class="text-center">
 <h2 class="heading">Premium</h2>
                            <span class="price"><sup>$</sup> <span class="number">49<small class="per">/mo</small></span></span>
                            <span class="excerpt d-block">All features are included</span>
                            <h3 class="heading-2 mb-3">Enjoy All The Features</h3>

                            <ul class="pricing-text mb-4">
                              <li><strong>250 GB</strong> Bandwidth</li>
                              <li><strong>200 GB</strong> Storage</li>
                              <li><strong>$5.00 / GB</strong> Overages</li>
                              <li>All features</li>
                            </ul>
                            <a href="#" class="btn btn-primary d-block px-3 py-3 mb-4">Choose Plan</a>
                    </div>
                  </div>
                </div>
                <div class="col-lg-3 col-md-6 ftco-animate d-flex">
                  <div class="block-7 d-flex align-self-stretch">
                    <div class="text-center">
                            <h2 class="heading">Pro</h2>
                            <span class="price"><sup>$</sup> <span class="number">99<small class="per">/mo</small></span></span>
                            <span class="excerpt d-block">All features are included</span>
                            <h3 class="heading-2 mb-3">Enjoy All The Features</h3>

                            <ul class="pricing-text mb-4">
                              <li><strong>450 GB</strong> Bandwidth</li>
                              <li><strong>400 GB</strong> Storage</li>
                              <li><strong>$20.00 / GB</strong> Overages</li>
                              <li>All features</li>
                            </ul>
                                  <a href="#" class="btn btn-secondary d-block px-3 py-3 mb-4">Choose Plan</a>
                    </div>
                  </div>
                </div>
              </div>
        </div>
    </section>

 -->    
 	<section class="ftco-section testimony-section">
      <div class="container">
        <div class="row justify-content-center mb-5">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">만족한 고객사들이 얘기합니다</h2>
            <p>대부분의 고객사 분들이 저희 ERP에 대하여 우수한 성능을 자랑한다고 만족하셨습니다.</p>
          </div>
        </div>
        <div class="row ftco-animate">
          <div class="col-md-12">
            <div class="carousel-testimony owl-carousel ftco-owl">
              <div class="item">
                <div class="testimony-wrap p-4 text-center">
                  <div class="user-img mb-4" style="background-image: url(/NHMP/resources/Main/images/person_1.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text">
                    <p class="mb-4">사용하기 편리하고 알맞는 기능만 있는 ERP에 대해서 만족스럽습니다.</p>
                    <p class="name">홍길동</p>
                    <span class="position">천축요양병원장</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap p-4 text-center">
                  <div class="user-img mb-4" style="background-image: url(/NHMP/resources/Main/images/person_2.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text">
                    <p class="mb-4">편리할 뿐만 아니라 세부적인 기능들도 있어 만족스럽습니다.</p>
                    <p class="name">조국</p>
                    <span class="position">열린우리요양병원장</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap p-4 text-center">
                  <div class="user-img mb-4" style="background-image: url(/NHMP/resources/Main/images/person_3.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text">
                    <p class="mb-4">일손이 많아지지않고 효율적으로 병원을 운영할 수 있게 되었습니다.</p>
                    <p class="name">이순신</p>
                    <span class="position">명량요양병원장</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap p-4 text-center">
                  <div class="user-img mb-4" style="background-image: url(/NHMP/resources/Main/images/person_1.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text">
                    <p class="mb-4">긴급한 상황이나 문제가 있을시 대처를 확실하게 하는것이 좋습니다.</p>
                    <p class="name">제갈량</p>
                    <span class="position">만두요양병원장</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap p-4 text-center">
                  <div class="user-img mb-4" style="background-image: url(/NHMP/resources/Main/images/person_1.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text">
                    <p class="mb-4">병동이 많을수록 관리하는사람들이 많이 필요한데 적은 인원수로 돌아가서 좋습니다.</p>
                    <p class="name">진시황</p>
                    <span class="position">만리요양병원장</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
<!-- 
    <section class="ftco-section bg-light">
      <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2>Recent Blog</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in</p>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4 ftco-animate">
            <div class="blog-entry">
              <a href="/NHMP/views/main/blog-single.html" class="block-20" style="background-image: url(/NHMP/resources/Main/images/image_1.jpg);">
              </a>
              <div class="text text-center py-3">
                <div class="meta mb-2">
                  <div><a href="#">Aug 5, 2019</a></div>
                  <div><a href="#">Admin</a></div>
                  <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                </div>
                <div class="desc">
                        <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
                      </div>
              </div>
            </div>
          </div>
          <div class="col-md-4 ftco-animate">
            <div class="blog-entry" data-aos-delay="100">
              <a href="/NHMP/views/main/blog-single.html" class="block-20" style="background-image: url(/NHMP/resources/Main/images/image_2.jpg);">
              </a>
              <div class="text text-center py-3">
                <div class="meta mb-2">
                  <div><a href="#">Aug 5, 2019</a></div>
                  <div><a href="#">Admin</a></div>
                  <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                </div>
                <div class="desc">
                        <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
                      </div>
              </div>
            </div>
          </div>
          <div class="col-md-4 ftco-animate">
            <div class="blog-entry" data-aos-delay="200">
              <a href="/NHMP/views/main/blog-single.html" class="block-20" style="background-image: url(/NHMP/resources/Main/images/image_3.jpg);">
              </a>
              <div class="text text-center py-3">
                <div class="meta mb-2">
                  <div><a href="#">Aug 5, 2019</a></div>
 				  <div><a href="#">Admin</a></div>
                  <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                </div>
                <div class="desc">
                        <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
                      </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
 -->
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


  <script src="resources/Main/js/jquery.min.js"></script>
  <script src="resources/Main/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="resources/Main/js/popper.min.js"></script>
  <script src="resources/Main/js/bootstrap.min.js"></script>
  <script src="resources/Main/js/jquery.easing.1.3.js"></script>
  <script src="resources/Main/js/jquery.waypoints.min.js"></script>
  <script src="resources/Main/js/jquery.stellar.min.js"></script>
  <script src="resources/Main/js/owl.carousel.min.js"></script>
  <script src="resources/Main/js/jquery.magnific-popup.min.js"></script>
  <script src="resources/Main/js/aos.js"></script>
  <script src="resources/Main/js/jquery.animateNumber.min.js"></script>
  <script src="resources/Main/js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="resources/Main/js/google-map.js"></script>
  <script src="resources/Main/js/main.js"></script>

  </body>
</html>
       