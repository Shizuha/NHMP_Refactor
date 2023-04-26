
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
import ERP.Team.model.service.TeamService;
import ERP.Team.model.vo.Team;
import ERP.Ward.model.service.WardService;
import ERP.Ward.model.vo.Ward;
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class InsertAuthorityEmp
 */
@WebServlet("/insertAuEmp")
public class InsertAuthorityEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAuthorityEmp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//권한부여 사용자 선택창에서 팀이름 클릭시 사원목록으로 이동처리하는 AJAX컨트롤러
		
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
				
				request.setCharacterEncoding("utf-8");
				String teamName = request.getParameter("teamName");
				
				
				
				if(teamName.length() < 7) {
					
					ArrayList<Employee>	empList = new EmployeeService().selectOrganEmpList(hostId, hostPwd, teamName);
					Team tcode = new TeamService().selectTeamCode(hostId, hostPwd, teamName);
					System.out.println("조직도 팀코드=" + tcode);
					
					
					ArrayList<Department> dList = new ArrayList<Department>();
					ArrayList<Ward> wList= new ArrayList<Ward>();
					Department dp = null;
					Ward po = null;
				for(Employee e : empList) {
					if(e.getDeptCode() != null) {
					dp = new DepartmentService().selectAuDeptName(hostId, hostPwd, e.getDeptCode());
					}else {
						dp = new Department(" ","없음");
					}
					if(e.getWardCode() != null) {
					po = new WardService().selectAuWardName(hostId, hostPwd, e.getWardCode());
					}else {
						po = new Ward("","없음");
					}
					dList.add(dp);
					wList.add(po);
				}
				System.out.println(dList);
				System.out.println(wList);
					
					
					
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
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
