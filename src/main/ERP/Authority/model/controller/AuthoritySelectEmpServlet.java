package ERP.Authority.model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ERP.Department.model.service.DepartmentService;
import ERP.Department.model.vo.Department;
import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import ERP.Position.model.service.PositionService;
import ERP.Position.model.vo.Position;
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class AuthoritySelectEmpServlet
 */
@WebServlet("/auEmp")
public class AuthoritySelectEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthoritySelectEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//권한 클리시 해당권한 가지고 있는 사원 가지고가는 서블릿
		String hostId = null;
		String hostPwd = null;
		Employee emp1 = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
		if(emp1 != null) {
		
		hostId = emp1.getHostId();
		hostPwd = emp1.getHostPwd();
		}else {
			hostId = loginHospital.getNH_USERID();
			hostPwd = loginHospital.getNH_USERPWD();
		}
		String auCode = request.getParameter("aucode");
		System.out.println("서블릿에서 권한코드 받은 내용 =" + auCode);
		
		ArrayList<Employee> empList = new EmployeeService().selectAuthorityEmp(hostId, hostPwd, auCode);
		System.out.println(empList);
			ArrayList<Department> dList = new ArrayList<Department>();
			ArrayList<Position> pList= new ArrayList<Position>();
			Department dp = null;
			Position po = null;
			String dps = " ";
			String pos = " ";
		for(Employee e : empList) {
			if(e.getDeptCode() != null) {
			dp = new DepartmentService().selectAuDeptName(hostId, hostPwd, e.getDeptCode());
			}else {
				dp = new Department(" ","없음");
			}
			if(e.getPosCode() != null) {
			po = new PositionService().selectAuPositionName(hostId, hostPwd, e.getPosCode());
			}else {
				po = new Position(" ", "없음");
			}
			dList.add(dp);
			pList.add(po);
		}
		System.out.println(dList);
		System.out.println(pList);
			
			
			
		JSONObject sendJson = new JSONObject();
		
		JSONArray jarr1 = new JSONArray();
		JSONArray jarr2 = new JSONArray();
		JSONArray jarr3 = new JSONArray();
		
		
		
		if(empList != null) {
			
			for(Employee e : empList) {
				JSONObject tn = new JSONObject();
				
				tn.put("empname", URLEncoder.encode(e.getEmpName(), "utf-8"));
				tn.put("empid", e.getEmpId());
				jarr1.add(tn);
			}
			System.out.println(dList);
			for(Department d : dList) {
				JSONObject tn = new JSONObject();
				tn.put("deptname", URLEncoder.encode(d.getDeptName(), "utf-8"));
				
				jarr2.add(tn);
			}
			for(Position p : pList) {
				JSONObject tn = new JSONObject();
				tn.put("posname", URLEncoder.encode(p.getPosName(), "utf-8"));
				jarr3.add(tn);
			}
			
			sendJson.put("list2", jarr2);
			sendJson.put("list3", jarr3);
			sendJson.put("list1", jarr1);
			
			response.setContentType("application/json");
			
			PrintWriter out = response.getWriter();
			
			out.print(sendJson.toJSONString());
			out.flush();
			out.close();
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
