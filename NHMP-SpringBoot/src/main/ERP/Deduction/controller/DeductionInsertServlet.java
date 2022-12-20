package ERP.Deduction.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Deduction.model.service.DeductionService;
import ERP.Deduction.model.vo.Deduction;
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class DeductionInsertServlet
 */
@WebServlet("/deducinsert")
public class DeductionInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeductionInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공제 추가 컨트롤러
		NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
		request.setCharacterEncoding("utf-8");
		
		Deduction deduction = new Deduction();
		
		
		deduction.setDEDUCTION_CODE(request.getParameter("Dcode"));
		deduction.setDEDUCTION_NAME(request.getParameter("Dname"));
		deduction.setDEDUCTION_NO(Integer.parseInt(request.getParameter("Dno")));
		deduction.setDEDUCTION_FORMULA(request.getParameter("Dformula"));
		deduction.setDEDUCTION_ETC(request.getParameter("Detc"));
		
		System.out.println(deduction);
		int result = new DeductionService().insertDeduction(deduction,loginHospital);
		RequestDispatcher view = null;
		if(result > 0) {
			ArrayList<Deduction> list = new DeductionService().selectList(loginHospital);
			view = request.getRequestDispatcher("views/ERP/Deduction/Deduction.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "공제 등록 실패");
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
