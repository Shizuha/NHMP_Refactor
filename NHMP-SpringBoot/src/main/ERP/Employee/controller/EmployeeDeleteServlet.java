package ERP.Employee.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class EmployeeDeleteServlet
 */
@WebServlet("/mdel")
public class EmployeeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] empNo = request.getParameterValues("empno");
		String hostId = null;
		String hostPwd = null;
		Employee emp = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
		System.out.println(emp);
		System.out.println(loginHospital);
		if(emp != null) {
		
		hostId = emp.getHostId();
		hostPwd = emp.getHostPwd();
		}else {
			hostId = loginHospital.getNH_USERID();
			hostPwd = loginHospital.getNH_USERPWD();
		}
		
		EmployeeService es = new EmployeeService();
		int result = 0;
		for(String id : empNo) {
			
			result = es.deleteEmployee(id,hostId, hostPwd);
			
		}
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		
		if(result > 0) {
			pw.println("<script>");
			pw.println("alert('정상적으로 삭제 되었습니다.')");
			pw.println("location.href='/NHMP/list'");
			pw.println("</script>");
		}else {
			
			pw.println("<script >");
			pw.println("alert('삭제가 되지 않았습니다 다시 확인하세요.')");
			pw.println("history.back()");
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
