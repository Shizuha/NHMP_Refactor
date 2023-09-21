package ERP.Pay.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ERP.Allowance.model.service.AllowanceService;
import ERP.Allowance.model.vo.Allowance;
import ERP.Deduction.model.service.DeductionService;
import ERP.Deduction.model.vo.Deduction;
import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class EpaysumListServlet
 */
@WebServlet("/Epaylist")
public class EpaysumListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EpaysumListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 직원 급여 계산 처리용 컨트롤러
		Employee loginEmployee = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		if(loginEmployee != null) {//관리자 로그인
			System.out.println(loginEmployee);
			String hostid = loginEmployee.getHostId();
			String hostpwd = loginEmployee.getHostPwd();
			System.out.println(hostid);
			System.out.println(hostpwd);
			
			ArrayList<Deduction> Dlist = new DeductionService().EselectList(hostid,hostpwd);
			System.out.println("Dlist : "+Dlist);
			ArrayList<Allowance> Alist = new AllowanceService().EselectList(hostid,hostpwd);
			System.out.println("Alist : "+Alist);
			Employee emp = new EmployeeService().selectOne(loginEmployee.getEmpNo(), hostid, hostpwd);
			System.out.println("emp : "+emp);
			RequestDispatcher view = null;
			if(Dlist.size() > 0) {
				view = request.getRequestDispatcher("views/ERP/PaySum/EPaySum.jsp");
				request.setAttribute("Dlist", Dlist);
				request.setAttribute("Alist", Alist);
				request.setAttribute("emp", emp);
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "페이지 조회 실패");
			}
			view.forward(request, response);
		}else {//직원 로그인
			System.out.println(loginHospital);
			ArrayList<Deduction> Dlist = new DeductionService().selectList(loginHospital);
			ArrayList<Allowance> Alist = new AllowanceService().selectList(loginHospital);
			ArrayList<Employee> Elist = new EmployeeService().selectAll(loginHospital);
			System.out.println(Elist);
			RequestDispatcher view = null;
			
			if(Dlist.size() > 0) {
				view = request.getRequestDispatcher("views/ERP/PaySum/PaySum.jsp");
				request.setAttribute("Dlist", Dlist);
				request.setAttribute("Alist", Alist);
				request.setAttribute("Elist", Elist);
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "페이지 조회 실패");
			}
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
