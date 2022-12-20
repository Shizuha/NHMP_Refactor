package ERP.notice.controller;

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

import ERP.Employee.model.vo.Employee;
import ERP.notice.model.service.NoticeService;
import ERP.notice.model.vo.Notice;
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class NoticeTopServlet
 */
@WebServlet("/ntop")
public class NoticeTopServlet extends HttpServlet {
	private static final long serialVersionUID = 7006L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeTopServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee loginEmployee = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospital loginAdmin = (NursingHospital)request.getSession().getAttribute("loginHospital");
		
		if(loginEmployee != null) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		
		ArrayList<Notice> list = new NoticeService().selectTop3(hostid, hostpwd);
		
		// 전송용 json 객체 생성
		JSONObject sendJson = new JSONObject();

		// list 옮겨 저장할 json 배열 객체를 생성
		JSONArray jarr = new JSONArray();

		// list를 jarr로 옮기기
		for (Notice n : list) {
			// b 객체 저장할 json 객체 생성
			JSONObject job = new JSONObject();
			job.put("no", n.getNoticeNo());
			// JSON에서 한글 깨짐 막으려면, java.net.URLEncoder.encode() 메소드로 인코딩 처리
			job.put("title", n.getNoticeTitle());
			job.put("date", String.valueOf(n.getNoticeDate()));

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
			String adminid = loginAdmin.getNH_USERID();
			String adminpwd = loginAdmin.getNH_USERPWD();
			
			ArrayList<Notice> list = new NoticeService().AdminselectTop3(adminid, adminpwd);
			
			// 전송용 json 객체 생성
			JSONObject sendJson = new JSONObject();

			// list 옮겨 저장할 json 배열 객체를 생성
			JSONArray jarr = new JSONArray();

			// list를 jarr로 옮기기
			for (Notice n : list) {
				// b 객체 저장할 json 객체 생성
				JSONObject job = new JSONObject();
				job.put("no", n.getNoticeNo());
				// JSON에서 한글 깨짐 막으려면, java.net.URLEncoder.encode() 메소드로 인코딩 처리
				job.put("title", n.getNoticeTitle());
				job.put("date", String.valueOf(n.getNoticeDate()));

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
