package Main.NursingHospital.controller;

import java.io.IOException;
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
@WebServlet("/login")
public class MloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MloginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				response.sendRedirect("/NHMP/views/Main/login.jsp");
					
			}else{
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "로그인 실패!!");
				view.forward(request, response);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
