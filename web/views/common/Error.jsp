<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@
	page import="Main.vo.NursingHospitalVo"
%>

<%
String message = (String)request.getAttribute("message");
	String message1 = (String)request.getAttribute("message1");
	NursingHospitalVo loginHospital = (NursingHospitalVo)session.getAttribute("loginHospital");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error page</title>
  <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <link href="/NHMP/resources/ERP/css/style.css" rel="stylesheet">
</head>
<body style="padding:270px;">

    <div id="preloader">
        <div class="loader">
            <svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
            </svg>
        </div>
    </div>


<div class="login-form-bg h-100">
        <div class="container h-100">
            <div class="row justify-content-center h-100">
                <div class="col-xl-6">
                    <div class="error-content">
                        <div class="card mb-0">
                            <div class="card-body text-center pt-5">
                                <h1 class="error-text text-primary">404</h1>
                                <h4 class="mt-4"><i class="fa fa-thumbs-down text-danger"></i>에러발생</h4>
                                <p><%= message %></p>
                                <form class="mt-5 mb-5">
                                    <% if(loginHospital != null ) { %>
                                    <div class="text-center mb-4 mt-4"><a href="javascript:history.back();" class="btn btn-primary">이전화면으로</a>
                                    </div>
                                    <div class="text-center mb-4 mt-4"><a href="/NHMP/index.jsp" class="btn btn-primary">홈으로</a>
                                    </div>
                                    <% }else { %>
                                    <div class="text-center mb-4 mt-4"><a href="/NHMP/MLogPage" class="btn btn-primary">로그인페이지로</a>
                                    </div>
                                    <div class="text-center mb-4 mt-4"><a href="/NHMP/index.jsp" class="btn btn-primary">홈으로</a>
                                    </div>
                                    <% } %>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 <script src="/NHMP/resources/ERP/plugins/common/common.min.js"></script>
    <script src="/NHMP/resources/ERP/js/custom.min.js"></script>
    <script src="/NHMP/resources/ERP/js/settings.js"></script>
    <script src="/NHMP/resources/ERP/js/gleek.js"></script>
    <script src="/NHMP/resources/ERP/js/styleSwitcher.js"></script>

</body>
</html>