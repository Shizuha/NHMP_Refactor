package ERP.Pay.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Allowance.model.service.AllowanceService;
import ERP.Allowance.model.vo.Allowance;
import ERP.Deduction.model.service.DeductionService;
import ERP.Deduction.model.vo.Deduction;
import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class PaysumListServlet
 */
@WebServlet("/paylist")
public class PaysumListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaysumListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 급여 계산에 사용될 공제 및 수당 리스트 처리용 컨트롤러
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		System.out.println(loginHospital);
		ArrayList<DeductionVo> Dlist = new DeductionService().selectList(loginHospital);
		ArrayList<AllowanceVo> Alist = new AllowanceService().selectList(loginHospital);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
