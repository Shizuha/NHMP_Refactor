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
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class EmployeeEmailCheck
 */
@WebServlet("/emailChk")
public class EmployeeEmailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeEmailCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String hostId = null;
		String hostPwd = null;
		Employee emp = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		if(emp != null) {
		
		hostId = emp.getHostId();
		hostPwd = emp.getHostPwd();
		}else {
			hostId = loginHospital.getNH_USERID();
			hostPwd = loginHospital.getNH_USERPWD();
		}
		
		System.out.println(email);
		int count = new EmployeeService().selectCheckEmail(email,hostId, hostPwd);
		System.out.println(count);
		String returnValue = null;
		
		
		if(count > 0) {
			returnValue = "ok";
		}else 
			returnValue = "dup";
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		pw.println(returnValue);
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
