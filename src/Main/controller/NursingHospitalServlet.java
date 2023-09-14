package Main.NursingHospital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ERP.Calendar.Model.Service.CalendarService;
import ERP.Calendar.Model.vo.Calendar;
import ERP.Department.model.service.DepartmentService;
import ERP.Department.model.vo.Department;
import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import ERP.Team.model.service.TeamService;
import ERP.Ward.model.service.WardService;
import ERP.Ward.model.vo.Ward;
import Main.NursingHospital.model.service.NHService;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class MloginServlet
 */
@WebServlet(urlPatterns = {"/mainhospitallogin", "/mainhospitalhostinfo", "/hospitalidcheck", "/mainhospitallist", "/mainhospitallogout", "/mainhospitalservicesingup", "/mainhospitalsingup"})
public class MloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MloginServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/mainhospitallogin").equals("/mainhospitallogin")) {
			//메인 로그인 처리용 서블릿
			request.setCharacterEncoding("UTF-8");
			String Cname = request.getParameter("Cname");
			String userid = "";
			String userpwd = "";
			NursingHospitalVo loginHospital = null;
			Employee loginEmployee = null;
			RequestDispatcher view = null;
			if(!Cname.equals("관리자")) {//직원
				String hostid = request.getParameter("hostid");
				String hostpwd = request.getParameter("hostpwd");
				userid = request.getParameter("userid");
				userpwd = request.getParameter("userpwd");
				loginEmployee = new EmployeeService().loginCheck(userid, userpwd, hostid, hostpwd);
				if( loginEmployee != null ) {
					Department dp = new DepartmentService().selectAuDeptName(hostid, hostpwd, loginEmployee.getDeptCode());
					String tm = new TeamService().selectTeamName(loginEmployee.getTeamCode(), hostid, hostpwd);
					Ward wd = new WardService().selectAuWardName(hostid, hostpwd, loginEmployee.getWardCode());
					HttpSession session = request.getSession();
					String empname = loginEmployee.getEmpName();
					ArrayList<Calendar> list = new CalendarService().EmployeeSelectList(empname, hostid, hostpwd);
					session.setAttribute("list", list);
					session.setAttribute("loginEmployee", loginEmployee);
					session.setAttribute("dp", dp);
					session.setAttribute("tm", tm);
					session.setAttribute("wd", wd);
					response.sendRedirect("/NHMP/views/ERP/Employee.jsp");
						
				}else{
					view = request.getRequestDispatcher("views/common/Error.jsp");
					request.setAttribute("message", "로그인 실패!!");
					view.forward(request, response);
				}
				
			}else {//관리자
				userid = request.getParameter("userid");
				userpwd = request.getParameter("userpwd");
				loginHospital = new NHService().loginCheck(userid, userpwd);
				
				if( loginHospital != null ) {
					HttpSession session = request.getSession();
					session.setAttribute("loginHospital", loginHospital);
					response.sendRedirect("/NHMP/views/Main/mainhospitallogin.jsp");
						
				}else{
					view = request.getRequestDispatcher("views/common/Error.jsp");
					request.setAttribute("message", "로그인 실패!!");
					view.forward(request, response);
				}
			}
		}
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/mainhospitalhostinfo").equals("/mainhospitalhostinfo")) {
			// 로그인 시 회사 선택 처리용 서블릿
			String Cname = request.getParameter("Cname");
			System.out.println("Cname : "+Cname);
			NursingHospitalVo NH = new NHService().selectOne(Cname);
			System.out.println("NH : "+NH);
			
			JSONObject job = new JSONObject();
			job.put("hostid", NH.getNH_USERID());
			job.put("hostpwd", NH.getNH_USERPWD());
			//확인
			System.out.println("job : " + job.toJSONString());
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(job.toJSONString());	//객체 정보를 문자열로 바꾸어 내보냄.
			out.flush();
			out.close();
		}
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/hospitalidcheck").equals("/hospitalidcheck")) {
			// 아이디 중복 확인 컨트롤러(Ajax)
			String userid = request.getParameter("userid");
			System.out.println(userid);
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			int result1 = new NHService().idCheck(userid);
			int result2 = new NHService().idCheck2(userid);
			int result3 = new NHService().idCheck3(userid);
			int result = result1 + result2 + result3;
			System.out.println("servlet : "+ result );
			/*RequestDispatcher view = null;*/
			if(result > 0 ) {
				out.append("이미 사용중인 아이디 입니다.");
				
			}else {
				out.append("사용가능한 아이디 입니다.");
				request.setAttribute("result", result);
			}
			out.flush();
			out.close();
		}
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/mainhospitallist").equals("/mainhospitallist")) {
			
			HttpSession session = request.getSession(false);
			if(session != null) {
				session.invalidate();
			}
			ArrayList<NursingHospitalVo> list = new NHService().selectAllList();
			
			RequestDispatcher view = null;
			if(list.size() > 0) {
				view = request.getRequestDispatcher("views/Main/mainhospitallogin.jsp");
				request.setAttribute("list", list);
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "로그인 페이지 접속 실패");
			}
			view.forward(request, response);
		}
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/mainhospitallogout").equals("/mainhospitallogout")) {
			// 로그아웃 서블릿
			HttpSession session = request.getSession(false);
			if(session != null) {
				session.invalidate();
				response.sendRedirect("/NHMP/index.jsp");
		}
			
		if(javax.servlet.annotation.WebServlet.urlPatterns("/mainhospitalservicesingup").equals("/mainhospitalservicesingup")) {
			// 서비스 신청 처리용 컨트롤러  
			String service = request.getParameter("selectType");
			System.out.println("service : "+service);//o
			//로그인 정보를 세션에서 가져옴
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			//세션에서 가져온 로그인 정보에서 아이디와 비밀번호 추출
			String userid1 = loginHospital.getNH_USERID();//o
			String userpwd1 = loginHospital.getNH_USERPWD();//o
			//업데이트 적용 결과 리턴 받음
			int result = new NHService().serviceUpdate(service, loginHospital);
			System.out.println("result : " + result);
			//계정생성
			new NHService().newuser(loginHospital);
			//샘플 스크립트 생성
			new NHService().sample(loginHospital);
			
			if(result > 0 ) {
				HttpSession session = request.getSession(false);
				loginHospital = new NHService().loginCheck(userid1, userpwd1);
				session.setAttribute("loginHospital", loginHospital);
				System.out.println("in 안 : "+loginHospital);
				response.sendRedirect("/NHMP/views/Main/serviceCenter.jsp");
			}else {
				RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "서비스 신청을 다시 진행해주세요");
				view.forward(request, response);
			}
		}
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/mainhospitalsingup").equals("/mainhospitalsingup")) {
			// 회원가입용 컨트롤러
			request.setCharacterEncoding("utf-8");
			
			NursingHospitalVo Hospital = new NursingHospitalVo();
			//아이디
			Hospital.setNH_USERID(request.getParameter("userid"));
			//패스워드
			Hospital.setNH_USERPWD(request.getParameter("userpwd"));
			//이름
			Hospital.setNH_NAME(request.getParameter("username"));
			//성별
			Hospital.setGENDER(request.getParameter("gender"));
			//주민번호
			String no = request.getParameter("before")+"-"+request.getParameter("after");
			Hospital.setNH_NO(no);
			//내/외국인
			Hospital.setNH_ITNAL_FOR(request.getParameter("intnal"));
			//전화번호
			String phone = request.getParameter("fphone")+"-"+request.getParameter("mphone")+"-"+request.getParameter("lphone");
			Hospital.setNH_PHONE(phone);
			//이메일
			Hospital.setNH_EMAIL(request.getParameter("email"));
			//회사 전화
			String Cphone = request.getParameter("fcomnum")+"-"+request.getParameter("mcomnum")+"-"+request.getParameter("lcomnum");
			Hospital.setNH_AD_TEL(Cphone);
			//사업자등록번호
			String comp_no = request.getParameter("psnCobsEnprNO1")+"-"+request.getParameter("psnCobsEnprNO2")+"-"+request.getParameter("psnCobsEnprNO3"); 
			Hospital.setCOMPANY_NO(comp_no);
			//회사 상호명
			Hospital.setCOMPANY_NAME(request.getParameter("chkBsnsNM"));
			//회사 주소
			String commAddr = request.getParameter("comaddrnum") +"번지, "+ request.getParameter("comaddrmain") +" "+ request.getParameter("comaddrdetail");
			System.out.println(commAddr);
			Hospital.setNH_ADDRESS(commAddr);
			//기타사항
			Hospital.setNH_ETC(request.getParameter("etc"));
			
			System.out.println("Hospital : " + Hospital);
			
			int result = new NHService().insertHPT(Hospital);
			
			if(result > 0 ) {
				//성공 화면 넘기기
				response.sendRedirect("/NHMP/mainhospitallist");
			}else {
				//실패
				RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "회원 가입 에러 다시 실행 해주세요!");
				view.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
