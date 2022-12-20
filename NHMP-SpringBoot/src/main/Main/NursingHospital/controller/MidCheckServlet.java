package Main.NursingHospital.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.NursingHospital.model.service.NHService;

/**
 * Servlet implementation class MidCheckServlet
 */
@WebServlet("/idcheck")
public class MidCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MidCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 아이디 중복 확인 컨트롤러(Ajax)
		
		String userid = request.getParameter("userid");
		System.out.println(userid);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		int result1 = new NHService().idCheck(userid);
		int result2 = new NHService().idCheck2(userid);
		int result3 = new NHService().idCheck3(userid);
		int result = result1 + result2 + result3;
		System.out.println("servlet : "+ result );
		/*RequestDispatcher view = null;*/
		if(result > 0 ) {
			out.append("이미 사용중인 아이디 입니다.");
			
		}else {
			out.append("사용가능한 아이디 입니다.");
			request.setAttribute("result", result);
		}
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
