package Main.NursingHospital.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.NursingHospital.model.ov.NursingHospital;
import Main.NursingHospital.model.service.NHService;

/**
 * Servlet implementation class MsingupServlet
 */
@WebServlet("/singup")
public class MsingupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MsingupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입용 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		NursingHospital Hospital = new NursingHospital();
		//아이디
		Hospital.setNH_USERID(request.getParameter("userid"));
		//패스워드
		Hospital.setNH_USERPWD(request.getParameter("userpwd"));
		//이름
		Hospital.setNH_NAME(request.getParameter("username"));
		//성별
		Hospital.setGENDER(request.getParameter("gender"));
		//주민번호
		String no = request.getParameter("before")+"-"+request.getParameter("after");
		Hospital.setNH_NO(no);
		//내/외국인
		Hospital.setNH_ITNAL_FOR(request.getParameter("intnal"));
		//전화번호
		String phone = request.getParameter("fphone")+"-"+request.getParameter("mphone")+"-"+request.getParameter("lphone");
		Hospital.setNH_PHONE(phone);
		//이메일
		Hospital.setNH_EMAIL(request.getParameter("email"));
		//회사 전화
		String Cphone = request.getParameter("fcomnum")+"-"+request.getParameter("mcomnum")+"-"+request.getParameter("lcomnum");
		Hospital.setNH_AD_TEL(Cphone);
		//사업자등록번호
		String comp_no = request.getParameter("psnCobsEnprNO1")+"-"+request.getParameter("psnCobsEnprNO2")+"-"+request.getParameter("psnCobsEnprNO3"); 
		Hospital.setCOMPANY_NO(comp_no);
		//회사 상호명
		Hospital.setCOMPANY_NAME(request.getParameter("chkBsnsNM"));
		//회사 주소
		String commAddr = request.getParameter("comaddrnum") +"번지, "+ request.getParameter("comaddrmain") +" "+ request.getParameter("comaddrdetail");
		System.out.println(commAddr);
		Hospital.setNH_ADDRESS(commAddr);
		//기타사항
		Hospital.setNH_ETC(request.getParameter("etc"));
		
		System.out.println("Hospital : " + Hospital);
		
		int result = new NHService().insertHPT(Hospital);
		
		if(result > 0 ) {
			//성공 화면 넘기기
			response.sendRedirect("/NHMP/MLogPage");			
		}else {
			//실패
			RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "회원 가입 애러 다시 실행하여 주세요^^");
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
