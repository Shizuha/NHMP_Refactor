<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@
	page
	import="com.minje.nhmp.Main.vo.NursingHospitalVo,
			com.minje.nhmp.Main.vo.NoticeVo,
			java.util.ArrayList"%>

<%
	NursingHospitalVo loginHospital = (NursingHospitalVo)session.getAttribute("loginHospital");
	@SuppressWarnings("unchecked")
	ArrayList<NoticeVo> list = (ArrayList<NoticeVo>)request.getAttribute("list");
	
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
 	int beginPage = ((Integer)request.getAttribute("beginPage")).intValue();
 	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
 	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>온라인 고객센터</title>
<style type="text/css">
#selectUL {
	margin: 0px;
	margin-left: 350px;
	padding: 0;
	padding-top: 0px;
	width: 300px;
	height: 60px;
	background: #207dff;
	list-style: none;
	padding-top: 15px;
}

#selectUL li a {
	float: left;
	width: 100px;
	height: 60px;
	padding-top: 5px;
	padding-bottom: 5px;
	margin-right: 0px;
	font-size: 25px;
	text-align: center;
	color: #fff;
}

#selectUL li a:hover {
	float: left;
	width: 100px;
	height: 60px;
	padding-top: 5px;
	padding-bottom: 5px;
	margin-right: 0px;
	font-size: 25px;
	text-align: center;
	color: #58b909;
}

#indiv {
	width: 1200px;
	height: 800px;
	margin-left: 350px;
	/* border: 1px solid red; */
}

#indiv .singupT {
	/* border: 1px solid red; */
	width: 900px;
	margin-left: 150px;
	float: center;
	text-align: center;
}

#indiv .singupT th {
	padding-top: 10px;
	padding-bottom: 10px;
	width: 300px;
	float: center;
	text-align: center;
}

#indiv .singupT td input {
	width: 600px;
	float: center;
	text-align: center;
}

#indiv #selectTable {
	/* border: 1px solid blue; */
	margin-left: 50px;
	text-align: center;
	width: 1100px;
	height: 500px;
}

#indiv #selectTable td {
	width: 33%;
}

#indiv .Sbtn {
	width: 1100px;
	align: center;
	text-align: center;
	background: 207dff;
	margin-left: 50px;
}

#indiv .Sbtn input {
	width: 150px;
	align: center;
	text-align: center;
	background: 207dff;
}
</style>

<!--  page style 코드가 기니 닫아주십시오 -->
<style>
.pagination {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  padding-left: 0;
  list-style: none;
  border-radius: 0.25rem;
}

.page-link {
  position: relative;
  display: block;
  padding: 0.5rem 0.75rem;
  margin-left: -1px;
  line-height: 1.25;
  color: #2FA4E7;
  background-color: #fff;
  border: 1px solid #dee2e6;
}

.page-link:hover {
  z-index: 2;
  color: #157ab5;
  text-decoration: none;
  background-color: #e9ecef;
  border-color: #dee2e6;
}

.page-link:focus {
  z-index: 2;
  outline: 0;
  -webkit-box-shadow: 0 0 0 0.2rem rgba(47, 164, 231, 0.25);
          box-shadow: 0 0 0 0.2rem rgba(47, 164, 231, 0.25);
}

.page-item:first-child .page-link {
  margin-left: 0;
  border-top-left-radius: 0.25rem;
  border-bottom-left-radius: 0.25rem;
}

.page-item:last-child .page-link {
  border-top-right-radius: 0.25rem;
  border-bottom-right-radius: 0.25rem;
}

.page-item.active .page-link {
  z-index: 1;
  color: #fff;
  background-color: #2FA4E7;
  border-color: #2FA4E7;
}

.page-item.disabled .page-link {
  color: #868e96;
  pointer-events: none;
  cursor: auto;
  background-color: #fff;
  border-color: #dee2e6;
}

.pagination-lg .page-link {
  padding: 0.75rem 1.5rem;
  font-size: 1.25rem;
  line-height: 1.5;
}

.pagination-lg .page-item:first-child .page-link {
  border-top-left-radius: 0.3rem;
  border-bottom-left-radius: 0.3rem;
}

.pagination-lg .page-item:last-child .page-link {
  border-top-right-radius: 0.3rem;
  border-bottom-right-radius: 0.3rem;
}

.pagination-sm .page-link {
  padding: 0.25rem 0.5rem;
  font-size: 0.875rem;
  line-height: 1.5;
}

.pagination-sm .page-item:first-child .page-link {
  border-top-left-radius: 0.2rem;
  border-bottom-left-radius: 0.2rem;
}

.pagination-sm .page-item:last-child .page-link {
  border-top-right-radius: 0.2rem;
  border-bottom-right-radius: 0.2rem;
}
</style>

<!-- button style 코드가 기니 닫아주십시오 -->
<style type="text/css">
.btn {
  display: inline-block;
  font-weight: 400;
  color: #495057;
  text-align: center;
  vertical-align: middle;
  -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
  background-color: transparent;
  border: 1px solid transparent;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: 0.25rem;
  -webkit-transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
}

@media (prefers-reduced-motion: reduce) {
  .btn {
    -webkit-transition: none;
    transition: none;
  }
}

.btn:hover {
  color: #495057;
  text-decoration: none;
}

.btn:focus, .btn.focus {
  outline: 0;
  -webkit-box-shadow: 0 0 0 0.2rem rgba(47, 164, 231, 0.25);
          box-shadow: 0 0 0 0.2rem rgba(47, 164, 231, 0.25);
}

.btn.disabled, .btn:disabled {
  opacity: 0.65;
}

a.btn.disabled,
fieldset:disabled a.btn {
  pointer-events: none;
}

.btn-primary {
  color: #fff;
  background-color: #2FA4E7;
  border-color: #2FA4E7;
}

.btn-primary:hover {
  color: #fff;
  background-color: #1992d7;
  border-color: #178acc;
}

.btn-primary:focus, .btn-primary.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(78, 178, 235, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(78, 178, 235, 0.5);
}

.btn-primary.disabled, .btn-primary:disabled {
  color: #fff;
  background-color: #2FA4E7;
  border-color: #2FA4E7;
}

.btn-primary:not(:disabled):not(.disabled):active, .btn-primary:not(:disabled):not(.disabled).active,
.show > .btn-primary.dropdown-toggle {
  color: #fff;
  background-color: #178acc;
  border-color: #1682c0;
}

.btn-primary:not(:disabled):not(.disabled):active:focus, .btn-primary:not(:disabled):not(.disabled).active:focus,
.show > .btn-primary.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(78, 178, 235, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(78, 178, 235, 0.5);
}

.btn-secondary {
  color: #212529;
  background-color: #e9ecef;
  border-color: #e9ecef;
}

.btn-secondary:hover {
  color: #212529;
  background-color: #d3d9df;
  border-color: #cbd3da;
}

.btn-secondary:focus, .btn-secondary.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(203, 206, 209, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(203, 206, 209, 0.5);
}

.btn-secondary.disabled, .btn-secondary:disabled {
  color: #212529;
  background-color: #e9ecef;
  border-color: #e9ecef;
}

.btn-secondary:not(:disabled):not(.disabled):active, .btn-secondary:not(:disabled):not(.disabled).active,
.show > .btn-secondary.dropdown-toggle {
  color: #212529;
  background-color: #cbd3da;
  border-color: #c4ccd4;
}

.btn-secondary:not(:disabled):not(.disabled):active:focus, .btn-secondary:not(:disabled):not(.disabled).active:focus,
.show > .btn-secondary.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(203, 206, 209, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(203, 206, 209, 0.5);
}

.btn-success {
  color: #fff;
  background-color: #73A839;
  border-color: #73A839;
}

.btn-success:hover {
  color: #fff;
  background-color: #5f8b2f;
  border-color: #59822c;
}

.btn-success:focus, .btn-success.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(136, 181, 87, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(136, 181, 87, 0.5);
}

.btn-success.disabled, .btn-success:disabled {
  color: #fff;
  background-color: #73A839;
  border-color: #73A839;
}

.btn-success:not(:disabled):not(.disabled):active, .btn-success:not(:disabled):not(.disabled).active,
.show > .btn-success.dropdown-toggle {
  color: #fff;
  background-color: #59822c;
  border-color: #527829;
}

.btn-success:not(:disabled):not(.disabled):active:focus, .btn-success:not(:disabled):not(.disabled).active:focus,
.show > .btn-success.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(136, 181, 87, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(136, 181, 87, 0.5);
}

.btn-info {
  color: #fff;
  background-color: #033C73;
  border-color: #033C73;
}

.btn-info:hover {
  color: #fff;
  background-color: #02294e;
  border-color: #022241;
}

.btn-info:focus, .btn-info.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(41, 89, 136, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(41, 89, 136, 0.5);
}

.btn-info.disabled, .btn-info:disabled {
  color: #fff;
  background-color: #033C73;
  border-color: #033C73;
}

.btn-info:not(:disabled):not(.disabled):active, .btn-info:not(:disabled):not(.disabled).active,
.show > .btn-info.dropdown-toggle {
  color: #fff;
  background-color: #022241;
  border-color: #011c35;
}

.btn-info:not(:disabled):not(.disabled):active:focus, .btn-info:not(:disabled):not(.disabled).active:focus,
.show > .btn-info.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(41, 89, 136, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(41, 89, 136, 0.5);
}

.btn-warning {
  color: #fff;
  background-color: #DD5600;
  border-color: #DD5600;
}

.btn-warning:hover {
  color: #fff;
  background-color: #b74700;
  border-color: #aa4200;
}

.btn-warning:focus, .btn-warning.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(226, 111, 38, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(226, 111, 38, 0.5);
}

.btn-warning.disabled, .btn-warning:disabled {
  color: #fff;
  background-color: #DD5600;
  border-color: #DD5600;
}

.btn-warning:not(:disabled):not(.disabled):active, .btn-warning:not(:disabled):not(.disabled).active,
.show > .btn-warning.dropdown-toggle {
  color: #fff;
  background-color: #aa4200;
  border-color: #9d3d00;
}

.btn-warning:not(:disabled):not(.disabled):active:focus, .btn-warning:not(:disabled):not(.disabled).active:focus,
.show > .btn-warning.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(226, 111, 38, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(226, 111, 38, 0.5);
}

.btn-danger {
  color: #fff;
  background-color: #C71C22;
  border-color: #C71C22;
}

.btn-danger:hover {
  color: #fff;
  background-color: #a5171c;
  border-color: #9a161a;
}

.btn-danger:focus, .btn-danger.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(207, 62, 67, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(207, 62, 67, 0.5);
}

.btn-danger.disabled, .btn-danger:disabled {
  color: #fff;
  background-color: #C71C22;
  border-color: #C71C22;
}

.btn-danger:not(:disabled):not(.disabled):active, .btn-danger:not(:disabled):not(.disabled).active,
.show > .btn-danger.dropdown-toggle {
  color: #fff;
  background-color: #9a161a;
  border-color: #8f1418;
}

.btn-danger:not(:disabled):not(.disabled):active:focus, .btn-danger:not(:disabled):not(.disabled).active:focus,
.show > .btn-danger.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(207, 62, 67, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(207, 62, 67, 0.5);
}

.btn-light {
  color: #212529;
  background-color: #f8f9fa;
  border-color: #f8f9fa;
}

.btn-light:hover {
  color: #212529;
  background-color: #e2e6ea;
  border-color: #dae0e5;
}

.btn-light:focus, .btn-light.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(216, 217, 219, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(216, 217, 219, 0.5);
}

.btn-light.disabled, .btn-light:disabled {
  color: #212529;
  background-color: #f8f9fa;
  border-color: #f8f9fa;
}

.btn-light:not(:disabled):not(.disabled):active, .btn-light:not(:disabled):not(.disabled).active,
.show > .btn-light.dropdown-toggle {
  color: #212529;
  background-color: #dae0e5;
  border-color: #d3d9df;
}

.btn-light:not(:disabled):not(.disabled):active:focus, .btn-light:not(:disabled):not(.disabled).active:focus,
.show > .btn-light.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(216, 217, 219, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(216, 217, 219, 0.5);
}

.btn-dark {
  color: #fff;
  background-color: #343a40;
  border-color: #343a40;
}

.btn-dark:hover {
  color: #fff;
  background-color: #23272b;
  border-color: #1d2124;
}

.btn-dark:focus, .btn-dark.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(82, 88, 93, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(82, 88, 93, 0.5);
}

.btn-dark.disabled, .btn-dark:disabled {
  color: #fff;
  background-color: #343a40;
  border-color: #343a40;
}

.btn-dark:not(:disabled):not(.disabled):active, .btn-dark:not(:disabled):not(.disabled).active,
.show > .btn-dark.dropdown-toggle {
  color: #fff;
  background-color: #1d2124;
  border-color: #171a1d;
}

.btn-dark:not(:disabled):not(.disabled):active:focus, .btn-dark:not(:disabled):not(.disabled).active:focus,
.show > .btn-dark.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(82, 88, 93, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(82, 88, 93, 0.5);
}

.btn-outline-primary {
  color: #2FA4E7;
  border-color: #2FA4E7;
}

.btn-outline-primary:hover {
  color: #fff;
  background-color: #2FA4E7;
  border-color: #2FA4E7;
}

.btn-outline-primary:focus, .btn-outline-primary.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(47, 164, 231, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(47, 164, 231, 0.5);
}

.btn-outline-primary.disabled, .btn-outline-primary:disabled {
  color: #2FA4E7;
  background-color: transparent;
}

.btn-outline-primary:not(:disabled):not(.disabled):active, .btn-outline-primary:not(:disabled):not(.disabled).active,
.show > .btn-outline-primary.dropdown-toggle {
  color: #fff;
  background-color: #2FA4E7;
  border-color: #2FA4E7;
}

.btn-outline-primary:not(:disabled):not(.disabled):active:focus, .btn-outline-primary:not(:disabled):not(.disabled).active:focus,
.show > .btn-outline-primary.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(47, 164, 231, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(47, 164, 231, 0.5);
}

.btn-outline-secondary {
  color: #e9ecef;
  border-color: #e9ecef;
}

.btn-outline-secondary:hover {
  color: #212529;
  background-color: #e9ecef;
  border-color: #e9ecef;
}

.btn-outline-secondary:focus, .btn-outline-secondary.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(233, 236, 239, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(233, 236, 239, 0.5);
}

.btn-outline-secondary.disabled, .btn-outline-secondary:disabled {
  color: #e9ecef;
  background-color: transparent;
}

.btn-outline-secondary:not(:disabled):not(.disabled):active, .btn-outline-secondary:not(:disabled):not(.disabled).active,
.show > .btn-outline-secondary.dropdown-toggle {
  color: #212529;
  background-color: #e9ecef;
  border-color: #e9ecef;
}

.btn-outline-secondary:not(:disabled):not(.disabled):active:focus, .btn-outline-secondary:not(:disabled):not(.disabled).active:focus,
.show > .btn-outline-secondary.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(233, 236, 239, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(233, 236, 239, 0.5);
}

.btn-outline-success {
  color: #73A839;
  border-color: #73A839;
}

.btn-outline-success:hover {
  color: #fff;
  background-color: #73A839;
  border-color: #73A839;
}

.btn-outline-success:focus, .btn-outline-success.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(115, 168, 57, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(115, 168, 57, 0.5);
}

.btn-outline-success.disabled, .btn-outline-success:disabled {
  color: #73A839;
  background-color: transparent;
}

.btn-outline-success:not(:disabled):not(.disabled):active, .btn-outline-success:not(:disabled):not(.disabled).active,
.show > .btn-outline-success.dropdown-toggle {
  color: #fff;
  background-color: #73A839;
  border-color: #73A839;
}

.btn-outline-success:not(:disabled):not(.disabled):active:focus, .btn-outline-success:not(:disabled):not(.disabled).active:focus,
.show > .btn-outline-success.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(115, 168, 57, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(115, 168, 57, 0.5);
}

.btn-outline-info {
  color: #033C73;
  border-color: #033C73;
}

.btn-outline-info:hover {
  color: #fff;
  background-color: #033C73;
  border-color: #033C73;
}

.btn-outline-info:focus, .btn-outline-info.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(3, 60, 115, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(3, 60, 115, 0.5);
}

.btn-outline-info.disabled, .btn-outline-info:disabled {
  color: #033C73;
  background-color: transparent;
}

.btn-outline-info:not(:disabled):not(.disabled):active, .btn-outline-info:not(:disabled):not(.disabled).active,
.show > .btn-outline-info.dropdown-toggle {
  color: #fff;
  background-color: #033C73;
  border-color: #033C73;
}

.btn-outline-info:not(:disabled):not(.disabled):active:focus, .btn-outline-info:not(:disabled):not(.disabled).active:focus,
.show > .btn-outline-info.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(3, 60, 115, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(3, 60, 115, 0.5);
}

.btn-outline-warning {
  color: #DD5600;
  border-color: #DD5600;
}

.btn-outline-warning:hover {
  color: #fff;
  background-color: #DD5600;
  border-color: #DD5600;
}

.btn-outline-warning:focus, .btn-outline-warning.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(221, 86, 0, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(221, 86, 0, 0.5);
}

.btn-outline-warning.disabled, .btn-outline-warning:disabled {
  color: #DD5600;
  background-color: transparent;
}

.btn-outline-warning:not(:disabled):not(.disabled):active, .btn-outline-warning:not(:disabled):not(.disabled).active,
.show > .btn-outline-warning.dropdown-toggle {
  color: #fff;
  background-color: #DD5600;
  border-color: #DD5600;
}

.btn-outline-warning:not(:disabled):not(.disabled):active:focus, .btn-outline-warning:not(:disabled):not(.disabled).active:focus,
.show > .btn-outline-warning.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(221, 86, 0, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(221, 86, 0, 0.5);
}

.btn-outline-danger {
  color: #C71C22;
  border-color: #C71C22;
}

.btn-outline-danger:hover {
  color: #fff;
  background-color: #C71C22;
  border-color: #C71C22;
}

.btn-outline-danger:focus, .btn-outline-danger.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(199, 28, 34, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(199, 28, 34, 0.5);
}

.btn-outline-danger.disabled, .btn-outline-danger:disabled {
  color: #C71C22;
  background-color: transparent;
}

.btn-outline-danger:not(:disabled):not(.disabled):active, .btn-outline-danger:not(:disabled):not(.disabled).active,
.show > .btn-outline-danger.dropdown-toggle {
  color: #fff;
  background-color: #C71C22;
  border-color: #C71C22;
}

.btn-outline-danger:not(:disabled):not(.disabled):active:focus, .btn-outline-danger:not(:disabled):not(.disabled).active:focus,
.show > .btn-outline-danger.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(199, 28, 34, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(199, 28, 34, 0.5);
}

.btn-outline-light {
  color: #f8f9fa;
  border-color: #f8f9fa;
}

.btn-outline-light:hover {
  color: #212529;
  background-color: #f8f9fa;
  border-color: #f8f9fa;
}

.btn-outline-light:focus, .btn-outline-light.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(248, 249, 250, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(248, 249, 250, 0.5);
}

.btn-outline-light.disabled, .btn-outline-light:disabled {
  color: #f8f9fa;
  background-color: transparent;
}

.btn-outline-light:not(:disabled):not(.disabled):active, .btn-outline-light:not(:disabled):not(.disabled).active,
.show > .btn-outline-light.dropdown-toggle {
  color: #212529;
  background-color: #f8f9fa;
  border-color: #f8f9fa;
}

.btn-outline-light:not(:disabled):not(.disabled):active:focus, .btn-outline-light:not(:disabled):not(.disabled).active:focus,
.show > .btn-outline-light.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(248, 249, 250, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(248, 249, 250, 0.5);
}

.btn-outline-dark {
  color: #343a40;
  border-color: #343a40;
}

.btn-outline-dark:hover {
  color: #fff;
  background-color: #343a40;
  border-color: #343a40;
}

.btn-outline-dark:focus, .btn-outline-dark.focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(52, 58, 64, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(52, 58, 64, 0.5);
}

.btn-outline-dark.disabled, .btn-outline-dark:disabled {
  color: #343a40;
  background-color: transparent;
}

.btn-outline-dark:not(:disabled):not(.disabled):active, .btn-outline-dark:not(:disabled):not(.disabled).active,
.show > .btn-outline-dark.dropdown-toggle {
  color: #fff;
  background-color: #343a40;
  border-color: #343a40;
}

.btn-outline-dark:not(:disabled):not(.disabled):active:focus, .btn-outline-dark:not(:disabled):not(.disabled).active:focus,
.show > .btn-outline-dark.dropdown-toggle:focus {
  -webkit-box-shadow: 0 0 0 0.2rem rgba(52, 58, 64, 0.5);
          box-shadow: 0 0 0 0.2rem rgba(52, 58, 64, 0.5);
}

.btn-link {
  font-weight: 400;
  color: #2FA4E7;
  text-decoration: none;
}

.btn-link:hover {
  color: #157ab5;
  text-decoration: underline;
}

.btn-link:focus, .btn-link.focus {
  text-decoration: underline;
  -webkit-box-shadow: none;
          box-shadow: none;
}

.btn-link:disabled, .btn-link.disabled {
  color: #868e96;
  pointer-events: none;
}

.btn-lg, .btn-group-lg > .btn {
  padding: 0.5rem 1rem;
  font-size: 1.25rem;
  line-height: 1.5;
  border-radius: 0.3rem;
}

.btn-sm, .btn-group-sm > .btn {
  padding: 0.25rem 0.5rem;
  font-size: 0.875rem;
  line-height: 1.5;
  border-radius: 0.2rem;
}

.btn-block {
  display: block;
  width: 100%;
}

.btn-block + .btn-block {
  margin-top: 0.5rem;
}

input[type="submit"].btn-block,
input[type="reset"].btn-block,
input[type="button"].btn-block {
  width: 100%;
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
				data-target="#ftco-nav" aria-controls="ftco
				-nav"
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

	<section class="ftco-section" border="1px solid red">

		<ul id="selectUL" style="padding-top: 0px;">
			<li><a href="/NHMP/views/Main/serviceCenter.jsp">가입하기</a></li>
			<li><a href="/NHMP/allqna">QnA</a></li>
			<li><a href="/NHMP/gongall">공지사항</a></li>
		</ul>
		<div id="indiv">
			<% if (loginHospital == null) {	%>
			<h1 align="center">회원 가입 후 신청해주세요.</h1>
			<% } else { %>
			<% if(loginHospital.getNH_SERVICE_CODE().equals("GS0") || loginHospital.getAUTHORITY_CODE().equals("G1") || loginHospital.getNH_SERVICE_CODE().equals("GS2")) { %>
			<table style="width: 100%; border-top: 1px solid #444444; border-collapse: collapse;">
				<tr>
					<th
						style="border-bottom: 1px solid #444444; padding: 10px; text-align: center; background-color: #bbdefb;">번호</th>
					<th
						style="border-bottom: 1px solid #444444; padding: 10px; text-align: center; background-color: #bbdefb;">제목</th>
					<th
						style="border-bottom: 1px solid #444444; padding: 10px; text-align: center; background-color: #bbdefb;">구분</th>
					<th
						style="border-bottom: 1px solid #444444; padding: 10px; text-align: center; background-color: #bbdefb;">작성일</th>
					<th
						style="border-bottom: 1px solid #444444; padding: 10px; text-align: center; background-color: #bbdefb;">작성자</th>
				</tr>
				<% 
				for(NoticeVo c : list){ %>
				<tr>
				<td style="border-bottom: 1px solid #444444; padding: 10px; text-align: center; background-color: #e3f2fd;">
					<%= c.getNOTICE_NO() %>
				</td>
				<td style="border-bottom: 1px solid #444444; padding: 10px; text-align: center; background-color: #e3f2fd;">
					<a href="/NHMP/Cdetail?no=<%= c.getNOTICE_NO() %>"><%= c.getNOTICE_TITLE() %></a>
					</td>
				<td style="border-bottom: 1px solid #444444; padding: 10px; text-align: center; background-color: #e3f2fd;">
					<%= c.getNOTICE_TYPE() %>
					</td>
				<td style="border-bottom: 1px solid #444444; padding: 10px; text-align: center; background-color: #e3f2fd;">
					<%= c.getNOTICE_DATE() %>
				</td>
				<td style="border-bottom: 1px solid #444444; padding: 10px; text-align: center; background-color: #e3f2fd;">
					<%= c.getNH_NAME() %>
				</td>
				</tr>
				<% } %>

			</table>
			<div style="max-width:800px; margin: auto;text-align: center;">
			  <ul class="pagination pagination-lg" style="justify-content: center;">
			    <% if((beginPage - 10) < 1){ %>
			    <li class="page-item disabled">
			      <a class="page-link" href="/NHMP/gongall?page=1">&laquo;</a>
			     <% }else{ %>
			      <a class="page-link" href="/NHMP/gongall?page=<%= beginPage - 10 %>">&laquo;</a>
			    </li>
			    <% } %>
			     <% for(int p = beginPage; p <= endPage; p++){ 
					if(p == currentPage){
				 %>
			    <li class="page-item active">
			      <a class="page-link" href="/NHMP/gongall?page=<%= p %>"><b><%= p %></b></a>
			    </li>
			    <% } else { %>
			    <li class="page-item">
			      <a class="page-link" href="/NHMP/gongall?page=<%= p %>"><%= p %></a>
			    </li>
			    <% }} %>
			    <% if((endPage + 10) > maxPage){ %>
			    <li class="page-item">
			      <a class="page-link" href="/NHMP/gongall?page=<%= maxPage %>">&raquo;</a>
			    </li>
			    <% }else{ %>
			    <li class="page-item">
			      <a class="page-link" href="/NHMP/gongall?page=<%= endPage + 10 %>">&raquo;</a>
			    </li>
			    <% } %>
			  </ul>
			  <% if(loginHospital.getCOMPANY_NAME().equals("TMTS")){ %>
			  <a href="/NHMP/views/Main/serviceNoticeInsert.jsp"><button type="button" class="btn btn-primary">공지사항 등록</button></a>
		  	  <%} else{ %>
		  	  	&nbsp;
		  	  <%} %>
		  </div>
		</div>
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