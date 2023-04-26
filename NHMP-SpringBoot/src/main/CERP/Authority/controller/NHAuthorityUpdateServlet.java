package CERP.Authority.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Cauthority.model.service.CauthorityService;
import ERP.Cauthority.model.vo.Cauthority;
import Main.NursingHospital.model.ov.NursingHospital;
import Main.NursingHospital.model.service.NHService;

/**
 * Servlet implementation class NHAuthorityUpdateServlet
 */
@WebServlet("/NHupauthority")
public class NHAuthorityUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NHAuthorityUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 권한 변경 처리용 컨트롤러
		
		//세션으로부터 로그인정보 받기
		NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
		
		
		
		String NHch = request.getParameter("NHch");
		String Authch = request.getParameter("AUch");
		
		int result = new NHService().UpdateAuthority(NHch, Authch, loginHospital);
		
		RequestDispatcher view = null;
		if(result > 0 ) {
			ArrayList<Cauthority> Alist = new CauthorityService().selectList(loginHospital);
			ArrayList<NursingHospital> list = new NHService().selectList();
			view = request.getRequestDispatcher("views/ERP/NH/CauthorityManager.jsp");
			request.setAttribute("list", list);
			request.setAttribute("Alist", Alist);
			
			
			
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "권한 변경 실패");
		}
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
