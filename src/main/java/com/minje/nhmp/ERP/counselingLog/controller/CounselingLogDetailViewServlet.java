package ERP.counselingLog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Employee.model.vo.Employee;
import ERP.counselingLog.model.service.CounselingLogService;
import ERP.counselingLog.model.vo.CounselingLog;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class CounselingLogDetailViewServlet
 */
@WebServlet("/counseldetail")
public class CounselingLogDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CounselingLogDetailViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 환자 상담일지 상세보기 처리용 컨트롤러
		String hostId = null;
		String hostPwd = null;
		Employee emp = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		if(emp != null) {
		
		hostId = emp.getHostId();
		hostPwd = emp.getHostPwd();
		}else {
			hostId = loginHospital.getNH_USERID();
			hostPwd = loginHospital.getNH_USERPWD();
		}
		
		int clNo = Integer.parseInt(request.getParameter("cl_no"));
		CounselingLogVo counselingLog = new CounselingLogService().DetailView(hostId, hostPwd, clNo);
				
		RequestDispatcher view = null;
		if(counselingLog != null) {
			view = request.getRequestDispatcher("views/ERP/counselingLog/CounselingLogDetailView.jsp");
			request.setAttribute("counselingLog", counselingLog);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", clNo + "번 상담일지 상세조회 실패!");
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
