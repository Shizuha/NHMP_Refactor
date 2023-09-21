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
 * Servlet implementation class AuthorityManagerServlet
 */
@WebServlet("/Cauthmanager")
public class AuthorityManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorityManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 권한 적용 컨트롤러
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		ArrayList<NursingHospitalVo> list = new NHService().selectList();
		ArrayList<Cauthority> Alist = new CauthorityService().selectList(loginHospital);
		
		RequestDispatcher view = null;
		System.out.println(Alist);
		if(list.size() > 0) {
			
			view = request.getRequestDispatcher("views/ERP/NH/CauthorityManager.jsp");
			request.setAttribute("list", list);
			request.setAttribute("Alist", Alist);
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "사용자 조회 실패");
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
