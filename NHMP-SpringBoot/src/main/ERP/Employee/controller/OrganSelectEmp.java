package ERP.Employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class OrganSelectEmp
 */
@WebServlet("/emp")
public class OrganSelectEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrganSelectEmp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//조직도에서 사원정보 클릭해서 받았을때 기본정보밑에 사원정보 출력 처리하는 컨트롤러 
		request.setCharacterEncoding("utf-8");
		String hostId = null;
		String hostPwd = null;
		Employee emp = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
		
		if(emp != null) {
		
		hostId = emp.getHostId();
		hostPwd = emp.getHostPwd();
		}else {
			hostId = loginHospital.getNH_USERID();
			hostPwd = loginHospital.getNH_USERPWD();
		}
		
		String empName = request.getParameter("empName");
		System.out.println("empName 값 받음 = " + empName);
		
		Employee empOne = new EmployeeService().selectOrganEmpOne(hostId, hostPwd, empName);
		
		
		
		
		JSONObject sendJson = new JSONObject();
		
		JSONArray jarr = new JSONArray();
		if(empOne != null) {
			
			JSONObject job = new JSONObject();
			
			job.put("empimg", empOne.getEmpRenameFilename());
			job.put("empname", URLEncoder.encode(empOne.getEmpName(), "utf-8"));
			job.put("itfor",URLEncoder.encode(empOne.getItnalFor(), "utf-8"));
			job.put("phone", empOne.getPhone());
			job.put("address", URLEncoder.encode(empOne.getAddress(), "utf-8"));
			job.put("hiredate", empOne.getHireDate().toString());
			job.put("email", empOne.getEmail());
			jarr.add(job);
			
			
			sendJson.put("list", jarr);
			
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter pw = response.getWriter();
			
			pw.print(sendJson.toJSONString());
			pw.flush();
			pw.close();
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
