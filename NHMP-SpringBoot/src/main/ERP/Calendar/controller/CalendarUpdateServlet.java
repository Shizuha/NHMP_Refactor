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
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class CalendarUpdateServlet
 */
@WebServlet("/calup")
public class CalendarUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 7004L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarUpdateServlet() {
        super();
        // TODO Auto-generated constructor stubS
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 캘린더 수정 서블릿
			Employee emp = (Employee)request.getSession().getAttribute("loginEmployee");
			if(emp != null) {
			String hostid = emp.getHostId();
			String hostpwd = emp.getHostPwd();
			
			String jsoncal = request.getParameter("jsonupdate");

			response.setCharacterEncoding("utf-8");
			JSONParser parser = new JSONParser();

			Object obj = parser.parse(jsoncal);
			JSONObject sendJson = (JSONObject) obj;
			//sendjson에 있는 username 과 session 에 있는 id 가 같으면 변경 실행 아니면 아이디가 다르다는 메세지 출력 
			CalendarService calendarService = new CalendarService();
			calendarService.updateCalendar(sendJson, hostid, hostpwd);
			} else {
				String jsoncal = request.getParameter("jsonupdate");

				response.setCharacterEncoding("utf-8");
				JSONParser parser = new JSONParser();
				
				Object obj = parser.parse(jsoncal);
				JSONObject sendJson = (JSONObject) obj;
				//sendjson에 있는 username 과 session 에 있는 id 가 같으면 변경 실행 아니면 아이디가 다르다는 메세지 출력 
				CalendarService calendarService = new CalendarService();
				calendarService.AdminupdateCalendar(sendJson);
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
