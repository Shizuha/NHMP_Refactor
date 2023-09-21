package ERP.Employee.controller;

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

/**
 * Servlet implementation class EmployeeCheckLogin
 */
@WebServlet("/logins")
public class EmployeeCheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeCheckLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		String hostId = request.getParameter("hostid");
		String hostPwd = request.getParameter("hostpwd");
		
		Employee emp = new EmployeeService().loginCheck(userId, userPwd,hostId, hostPwd);
		System.out.println("mem" + emp);
		emp.setHostId(hostId);
		emp.setHostPwd(hostPwd);
		Department dp = new DepartmentService().selectAuDeptName(hostId, hostPwd, emp.getDeptCode());
		String tm = new TeamService().selectTeamName(emp.getTeamCode(), hostId, hostPwd);
		Ward wd = new WardService().selectAuWardName(hostId, hostPwd, emp.getWardCode());
		
		/*ArrayList<Notice> noList = new NoticeService().selectList();
		System.out.println("noList" + noList);*/
		RequestDispatcher view  = null;
		System.out.println(dp.getDeptName());
		 
		if(emp != null) {
			String empname = emp.getEmpName();
			ArrayList<Calendar> list = new CalendarService().EmployeeSelectList(empname, hostId, hostPwd); // 캘린더 수정 처리
			
			HttpSession session = request.getSession();
			session.setAttribute("list", list);
			session.setAttribute("loginEmployee", emp);
			session.setAttribute("dp", dp);
			session.setAttribute("tm", tm);
			session.setAttribute("wd", wd);
			/*session.setAttribute("noList", noList);*/
			response.sendRedirect("/NHMP/views/ERP/Employee.jsp");
		}else {
			
			view = request.getRequestDispatcher("views/common/error.jsp");
			
			request.setAttribute("message", "로그인정보 불일치");
			
			view.forward(request, response);
			
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
