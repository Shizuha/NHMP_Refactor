package ERP.Calendar.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ERP.Calendar.Model.Service.CalendarService;
import ERP.Employee.model.vo.Employee;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class CalendarDeleteServlet
 */
@WebServlet("/caldelete")
public class CalendarDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 7003L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 캘린더 삭제 서블릿
			Employee emp = (Employee)request.getSession().getAttribute("loginEmployee");
			
			if(emp != null) {
			String hostid = emp.getHostId();
			String hostpwd = emp.getHostPwd();
			
			String jsoncal = request.getParameter("jsondelete");

			response.setCharacterEncoding("utf-8");
			JSONParser parser = new JSONParser();

			Object obj = parser.parse(jsoncal);
			JSONObject sendJson = (JSONObject) obj;
			//sendjson에 있는 username 과 session 에 있는 id 가 같으면 변경 실행 아니면 아이디가 다르다는 메세지 출력 
			CalendarService calendarService = new CalendarService();
			calendarService.deleteCalendar(sendJson, hostid, hostpwd);
			} else {
			String jsoncal = request.getParameter("jsondelete");

			response.setCharacterEncoding("utf-8");
			JSONParser parser = new JSONParser();

			Object obj = parser.parse(jsoncal);
			JSONObject sendJson = (JSONObject) obj;
			//sendjson에 있는 username 과 session 에 있는 id 가 같으면 변경 실행 아니면 아이디가 다르다는 메세지 출력 
			CalendarService calendarService = new CalendarService();
			calendarService.AdmindeleteCalendar(sendJson);
		}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
