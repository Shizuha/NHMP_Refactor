package ERP.Allowance.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Allowance.model.service.AllowanceService;
import ERP.Allowance.model.vo.Allowance;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class AllowanceInsertServlet
 */
@WebServlet("/allowinsert")
public class AllowanceInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllowanceInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수당 등록용 컨트롤러
		request.setCharacterEncoding("utf-8");
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		AllowanceVo awna = new AllowanceVo();
		
		awna.setALLOWANCE_CODE(request.getParameter("Acode"));
		awna.setALLOWANCE_NAME(request.getParameter("Aname"));
		awna.setALLOWANCE_NO(Integer.parseInt(request.getParameter("Ano")));
		awna.setALLOWANCE_FORMULA(request.getParameter("Aformula"));
		awna.setALLOWANCE_ETC(request.getParameter("Aetc"));
		
		int result = new AllowanceService().insertAllowance(awna, loginHospital);
		RequestDispatcher view = null;
		if(result > 0 ) {
			ArrayList<AllowanceVo> list = new AllowanceService().selectList(loginHospital);
			view = request.getRequestDispatcher("views/ERP/Allowance/Allowance.jsp");
			request.setAttribute("list", list);
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "수당 등록 실패");
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
