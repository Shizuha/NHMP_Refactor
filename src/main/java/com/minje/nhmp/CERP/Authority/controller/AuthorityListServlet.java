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
import Main.NursingHospital.model.service.NHService;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class AuthorityListServlet
 */
@WebServlet("/Cautholist")
public class AuthorityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorityListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 권한 전체 조회 처리용 컨트롤러
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		ArrayList<Cauthority> Alist = new CauthorityService().selectList(loginHospital);
		
		RequestDispatcher view = null;
		
		System.out.println(Alist);
		if(Alist.size() > 0) {
			view = request.getRequestDispatcher("views/ERP/NH/CauthorityList.jsp");
			request.setAttribute("Alist", Alist);
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "권한리스트 조회 실패");
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
