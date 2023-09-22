package ERP.Employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Career.model.service.CareerService;
import ERP.Career.model.vo.Career;
import ERP.Dependents.model.service.DependentsService;
import ERP.Dependents.model.vo.Dependents;
import ERP.Education.model.service.EducationService;
import ERP.Education.model.vo.Education;
import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import ERP.Empsalary.model.service.EmpSalaryService;
import ERP.Empsalary.model.vo.EmpSalary;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class EmpMInfoServlet
 */
@WebServlet("/minfo")
public class EmpMInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpMInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empid");
		String hostId = null;
		String hostPwd = null;
		Employee emp1 = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		
		if(emp1 != null) {
		
		hostId = emp1.getHostId();
		hostPwd = emp1.getHostPwd();
		}else {
			hostId = loginHospital.getNH_USERID();
			hostPwd = loginHospital.getNH_USERPWD();
		}
		Employee emp = new EmployeeService().selectEmpId(empId,hostId, hostPwd);
		
		EmpSalaryVo empSal = new EmpSalaryService().selectOne(empId,hostId, hostPwd);
		System.out.println("내정보보기 눌렀을때 가져온 사원 급여정보 : " + empSal);
		ArrayList<DependentsVo> dpenList = new DependentsService().selectOne(empId,hostId, hostPwd);
		System.out.println("내정보보기 를 눌렀을때 가져온 부양가족 정보:" + dpenList);
		if(dpenList.size() == 0)
			dpenList = null;
			
		ArrayList<CareerVo> carList = new CareerService().selectList(empId,hostId, hostPwd);
		System.out.println("내정보보기 눌었을때 가져온 사원 경력사항 정보:" + carList);
		if(carList.size() == 0)
			carList = null;
		
		ArrayList<EducationVo> eduList = new EducationService().selectList(empId,hostId, hostPwd);
		if(eduList.size() == 0)
			eduList = null;
		System.out.println("내정보보기 눌렀을때 가져온 사원 학력정보  : " + eduList);
		
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		if(emp != null) {
			RequestDispatcher view = request.getRequestDispatcher("views/ERP/Employee/updateEmployee.jsp");
			request.setAttribute("emp", emp);
			request.setAttribute("empSal", empSal);
			request.setAttribute("dpenList", dpenList);
			request.setAttribute("carList", carList);
			request.setAttribute("eduList", eduList);
			view.forward(request, response);
			
		}else {
			pw.println("<script>");
			pw.println("alert('해당사원정보가 없습니다.')");
			pw.println("location.href='/NHMP/list'");
			pw.println("</script>");
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
