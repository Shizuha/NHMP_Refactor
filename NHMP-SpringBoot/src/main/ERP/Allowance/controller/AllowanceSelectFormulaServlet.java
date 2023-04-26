package ERP.Allowance.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import ERP.Allowance.model.service.AllowanceService;
import Main.NursingHospital.model.ov.NursingHospital;


/**
 * Servlet implementation class AllowanceSelectFormulaServlet
 */
@WebServlet("/aone")
public class AllowanceSelectFormulaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllowanceSelectFormulaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수당 수식 조회용 컨트롤러
		NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
		String Acode = request.getParameter("acode");
				
		String Formula = new AllowanceService().selectFormula(Acode, loginHospital);
		
		System.out.println("servlet formula : " + Formula);
		
		JSONObject job = new JSONObject();
		job.put("Formula", Formula);
		
		response.setContentType("text/html; charset=utf-8");
		System.out.println("servlet : "+job.toJSONString());
		PrintWriter out = response.getWriter();
		out.print(job.toJSONString());
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
