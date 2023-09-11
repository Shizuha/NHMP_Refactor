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

import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import ERP.Team.model.service.TeamService;
import ERP.Team.model.vo.Team;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class OrganEmployeeListServlet
 */
@WebServlet("/teamlist")
public class OrganEmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrganEmployeeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//조직도에서 팀이름 클릭했을때 사원정보 출력처리하는 컨트롤러
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
		
		request.setCharacterEncoding("utf-8");
		String teamName = request.getParameter("teamName");
		
		
		
		if(teamName.length() < 7) {
			
			ArrayList<Employee>	empList = new EmployeeService().selectOrganEmpList(hostId, hostPwd, teamName);
			Team tcode = new TeamService().selectTeamCode(hostId, hostPwd, teamName);
			System.out.println("조직도 팀코드=" + tcode);
			int tCount = new EmployeeService().teamEmpcount(tcode.getTeamCode(),hostId, hostPwd);
			
			
			
			JSONObject sendJson = new JSONObject();
			
			JSONArray jarr = new JSONArray();
			if(empList != null && tCount > 0) {
				
				for(Employee e : empList) {
					JSONObject tn = new JSONObject();
					
					tn.put("empname",URLEncoder.encode(e.getEmpName(), "utf-8"));
					tn.put("tcode", tcode.getTeamCode());
					tn.put("count", tCount);
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
			
			String renameDeptName = teamName.substring(0, 6);
			String re2 = renameDeptName.trim();
			
			ArrayList<Employee>	empList = new EmployeeService().selectOrganEmpList(hostId, hostPwd, re2);
			
			Team tcode = new TeamService().selectTeamCode(hostId, hostPwd, re2);
			if(empList != null && tcode != null) {
			int tCount = new EmployeeService().teamEmpcount(tcode.getTeamCode(),hostId, hostPwd);
			
			JSONObject sendJson = new JSONObject();
			
			JSONArray jarr = new JSONArray();
			if(empList != null && tCount > 0) {
					JSONObject tn = new JSONObject();
					
					
					tn.put("tcode", tcode.getTeamCode());
					tn.put("count", tCount);
					jarr.add(tn);
				
				sendJson.put("list", jarr);
				
				
				response.setContentType("application/json");
				
				PrintWriter out = response.getWriter();
				
				out.print(sendJson.toJSONString());
				out.flush();
				out.close();
				}// 108
			}//100
		}//94
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
