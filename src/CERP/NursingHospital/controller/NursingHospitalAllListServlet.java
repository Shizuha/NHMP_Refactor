package CERP.NursingHospital.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.NursingHospital.model.service.NHService;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class NursingHospitalAllListServlet
 */
@WebServlet("/allNH")
public class NursingHospitalAllListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NursingHospitalAllListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// NH 전체 조회용 컨트롤러
		ArrayList<NursingHospitalVo> list = new NHService().selectList();
		
		RequestDispatcher view = null;
		if(list.size() > 0) {
			view = request.getRequestDispatcher("/views/ERP/NH/allNH.jsp");
			request.setAttribute("list", list);
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "전체 고객 조회 실패");
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
