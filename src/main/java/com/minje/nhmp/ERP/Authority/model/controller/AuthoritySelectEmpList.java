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
import ERP.Position.model.vo.Position;
import ERP.Ward.model.service.WardService;
import ERP.Ward.model.vo.Ward;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class AuthoritySelectEmpList
 */
@WebServlet("/selAuEmp")
public class AuthoritySelectEmpList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthoritySelectEmpList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 선택창 사원목록에서 선택적용 클릭시 선택된 사원목록으로 이동시키는 컨트롤러
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
		String[] empId = request.getParameterValues("empid");
		ArrayList<Employee> empList = new ArrayList<Employee>();
		for(String i : empId) {
			Employee emp = new EmployeeService().selectEmpId(i, hostId, hostPwd);
			empList.add(emp);
		}
		
			ArrayList<Department> dList = new ArrayList<Department>();
			ArrayList<Ward> wList= new ArrayList<Ward>();
		for(Employee e : empList) {
			Department dp = new DepartmentService().selectAuDeptName(hostId, hostPwd, e.getDeptCode());
			Ward wd = new WardService().selectAuWardName(hostId, hostPwd, e.getWardCode());
			dList.add(dp);
			wList.add(wd);
		}
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
			for(Department d : dList) {
				JSONObject tn = new JSONObject();
				tn.put("deptname", URLEncoder.encode(d.getDeptName(), "utf-8"));
				jarr2.add(tn);
			}
			for(Ward w : wList) {
				JSONObject tn = new JSONObject();
				tn.put("posname", URLEncoder.encode(w.getWardName(), "utf-8"));
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
