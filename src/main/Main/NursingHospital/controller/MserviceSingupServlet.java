package Main.NursingHospital.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Main.NursingHospital.model.ov.NursingHospital;
import Main.NursingHospital.model.service.NHService;

/**
 * Servlet implementation class MserviceSingupServlet
 */
@WebServlet("/servicesingup")
public class MserviceSingupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MserviceSingupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서비스 신청 처리용 컨트롤러  
		String service = request.getParameter("selectType");
		System.out.println("service : "+service);//o
		//로그인 정보를 세션에서 가져옴
		NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
		//세션에서 가져온 로그인 정보에서 아이디와 비밀번호 추출
		String userid1 = loginHospital.getNH_USERID();//o
		String userpwd1 = loginHospital.getNH_USERPWD();//o
		//업데이트 적용 결과 리턴 받음
		int result = new NHService().serviceUpdate(service, loginHospital);
		System.out.println("result : " + result);
		//계정생성
		new NHService().newuser(loginHospital);
		//샘플 스크립트 생성
		new NHService().sample(loginHospital);
		
		
		
		if(result > 0 ) {
			HttpSession session = request.getSession(false);
			loginHospital = new NHService().loginCheck(userid1, userpwd1);
			session.setAttribute("loginHospital", loginHospital);
			System.out.println("in 안 : "+loginHospital);
			response.sendRedirect("/NHMP/views/Main/serviceCenter.jsp");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "서비스 신청을 다시 진행해주세요");
			view.forward(request, response);
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
