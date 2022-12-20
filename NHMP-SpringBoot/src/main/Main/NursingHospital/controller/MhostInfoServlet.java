package Main.NursingHospital.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Main.NursingHospital.model.ov.NursingHospital;
import Main.NursingHospital.model.service.NHService;

/**
 * Servlet implementation class MhostInfoServlet
 */
@WebServlet("/hostinfo")
public class MhostInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MhostInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 시 회사 선택 처리용 서블릿
		String Cname = request.getParameter("Cname");
		System.out.println("Cname : "+Cname);
		NursingHospital NH = new NHService().selectOne(Cname);
		System.out.println("NH : "+NH);
		
		JSONObject job = new JSONObject();
		job.put("hostid", NH.getNH_USERID());
		job.put("hostpwd", NH.getNH_USERPWD());
		
		//확인
		System.out.println("job : " + job.toJSONString());
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(job.toJSONString());	//객체 정보를 문자열로 바꾸어 내보냄.
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
