package ERP.Employee.controller;

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
import ERP.Team.model.service.TeamService;
import ERP.Team.model.vo.Team;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class EmployeeOrganizationChartListServlet
 */
@WebServlet("/organlist")
public class EmployeeOrganizationChartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeOrganizationChartListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//조직도에서 부서 클릭했을시 부서정보 출력시키는 컨트롤러
		request.setCharacterEncoding("utf-8");
		String deptName = request.getParameter("deptName");
		
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
		
		
		if(deptName.length() < 13) {
		
		
		
		ArrayList<Team> team = new TeamService().selectOrganTeamName(deptName, hostId, hostPwd);
		
		Department dp = new DepartmentService().selectDeptCode(deptName, hostId, hostPwd);
		int empcount = 0;
		
		for(Team t : team) {
			empcount += new EmployeeService().teamEmpcount(t.getTeamCode(), hostId, hostPwd);
		}
		System.out.println(empcount);
		
		JSONObject sendJson = new JSONObject();
		
		JSONArray jarr = new JSONArray();
		if(team != null) {
			
			for(Team t : team) {
				JSONObject tn = new JSONObject();
				
				tn.put("teamname",URLEncoder.encode(t.getTeamName(), "utf-8"));
				tn.put("deptcode", dp.getDeptCode());
				tn.put("count", empcount);
				jarr.add(tn);
			}
			sendJson.put("list", jarr);
			
			
			response.setContentType("application/json");
			
			PrintWriter out = response.getWriter();
			
			out.print(sendJson.toJSONString());
			out.flush();
			out.close();
			}
		}else {
			String renameDeptName = deptName.substring(0, 10);
			String re2 = renameDeptName.trim();
			
			
			ArrayList<Team> team = new TeamService().selectOrganTeamName(re2, hostId, hostPwd);
			
			Department dp = new DepartmentService().selectDeptCode(re2, hostId, hostPwd);
			int empcount = 0;
			
			for(Team t : team) {
				empcount += new EmployeeService().teamEmpcount(t.getTeamCode(), hostId, hostPwd);
			}
			System.out.println(empcount);
			
			JSONObject sendJson = new JSONObject();
			
			JSONArray jarr = new JSONArray();
			if(team != null) {
					JSONObject tn = new JSONObject();
					
					
					tn.put("deptcode", dp.getDeptCode());
					tn.put("count", empcount);
					jarr.add(tn);
				
				sendJson.put("list", jarr);
				
				
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
