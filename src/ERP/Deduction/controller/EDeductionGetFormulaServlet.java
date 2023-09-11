package ERP.Deduction.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Deduction.model.service.DeductionService;
import ERP.Employee.model.vo.Employee;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class EDeductionGetFormulaServlet
 */
@WebServlet("/Egetdeduc")
public class EDeductionGetFormulaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EDeductionGetFormulaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 직원 공제 수식 처리용 컨트롤러 
		Employee loginEmployee = (Employee)request.getSession().getAttribute("loginEmployee");
		String Dcode = request.getParameter("Dcode");
		System.out.println(Dcode);
		
		String dformula = new DeductionService().EselectFormula(Dcode, loginEmployee);
		
		System.out.println(dformula);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(dformula);
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
