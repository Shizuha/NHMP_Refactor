package ERP.Calendar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ERP.Calendar.Model.Service.CalendarService;
import ERP.Calendar.Model.vo.Calendar;
import ERP.Employee.model.vo.Employee;
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class CalendarListServlet
 */
@WebServlet("/callist")
public class CalendarListServlet extends HttpServlet {
	private static final long serialVersionUID = 7002L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalendarListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 캘린더 값 가져오는 서블릿
		try {
			NursingHospital nh = (NursingHospital) request.getSession().getAttribute("loginHospital"); // 유효성 검사
			Employee emp = (Employee) request.getSession().getAttribute("loginEmployee");
			
			String jsondata = request.getParameter("jsondata");
			System.out.println(jsondata);
			JSONParser parser = new JSONParser();

			Object obj = parser.parse(jsondata);

			JSONObject sendJson = (JSONObject) obj;
			
			if(nh != null && emp == null) {
				String adminid = nh.getNH_USERID();
				System.out.println(adminid);
				JSONArray jarr = new JSONArray();
				
				ArrayList<Calendar> list = new CalendarService().listCalendar(sendJson, adminid);
				
				// list를 jarr로 옮기기
				for (Calendar c : list) {
					JSONObject job = new JSONObject();
					job.put("_id", c.getCalnum()); 
					job.put("title",c.getTitle());
					job.put("description", c.getDescription());
					job.put("start", c.getStartdate());
					job.put("end", String.valueOf(c.getEnddate()));
					job.put("type", c.getCategory());
					job.put("username", c.getUsername());
					job.put("backgroundColor", c.getBackgroundcolor());
					job.put("textColor", c.getTextcolor());
					job.put("allDay", "false");

					jarr.add(job);
				}
				
				
				// json 배열을 전송용 json 객체에 저장한다.
				sendJson.put("list", jarr);
				
				// 요청한 뷰로 응답처리한다.
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write(sendJson.toJSONString());
				out.flush();
				out.close();
				
			} else {
				String empname = emp.getEmpName();
				String hostid = emp.getHostId();
				String hostpwd = emp.getHostPwd();
				
				JSONArray jarr = new JSONArray();
				
				ArrayList<Calendar> list = new CalendarService().EmployeelistCalendar(sendJson, empname, hostid, hostpwd);
				
				// list를 jarr로 옮기기
				for (Calendar c : list) {
					JSONObject job = new JSONObject();
					job.put("_id", c.getCalnum()); 
					job.put("title",c.getTitle());
					job.put("description", c.getDescription());
					job.put("start", c.getStartdate());
					job.put("end", String.valueOf(c.getEnddate()));
					job.put("type", c.getCategory());
					job.put("username", c.getempname());
					job.put("backgroundColor", c.getBackgroundcolor());
					job.put("textColor", c.getTextcolor());
					job.put("allDay", "false");

					jarr.add(job);
				}
				
				// json 배열을 전송용 json 객체에 저장한다.
				sendJson.put("list", jarr);
				
				// 요청한 뷰로 응답처리한다.
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write(sendJson.toJSONString());
				out.flush();
				out.close();
				
				
			}
			

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
