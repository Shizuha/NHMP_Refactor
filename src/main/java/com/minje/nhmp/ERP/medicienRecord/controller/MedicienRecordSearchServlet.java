package ERP.medicienRecord.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Employee.model.vo.Employee;
import ERP.medicienRecord.model.service.MedicienRecordService;
import ERP.medicienRecord.model.vo.MedicienRecord;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class MedicienRecordSearchServlet
 */
@WebServlet("/recordsearch")
public class MedicienRecordSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicienRecordSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//투약일지 검색 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		
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
		
		String search = request.getParameter("search");
		
		ArrayList<MedicienRecordVo> list = null;
		MedicienRecordService mservice = new MedicienRecordService();
		
//		switch(search) {
//		case "mr_pat_name" : String mrPatName = request.getParameter("mr_pat_name");
//								list = mservice.selectMrPatNameSearch(hostId, hostPwd, mrPatName);
//								break;
//		case "mr_emp_name" : String mrEmpName = request.getParameter("mr_emp_name");
//										list = mservice.selectMrEmpNameSearch(hostId, hostPwd, mrEmpName);
//										break;
//		}
		
		RequestDispatcher view = null;
		if(list.size() > 0) {
			view = request.getRequestDispatcher("views/ERP/medicienRecord/MedicienRecordListView.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", search + "검색 조회 실패!");
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
