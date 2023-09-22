package ERP.medicienRecord.controller;

import java.io.IOException;

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
 * Servlet implementation class MedicienRecordDetailViewServlet
 */
@WebServlet("/recorddetail")
public class MedicienRecordDetailViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicienRecordDetailViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//투약일지 상세조회 처리용 컨트롤러
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
		
		int mrNo = Integer.parseInt(request.getParameter("mr_no"));
		MedicienRecordVo medicienRecord = new MedicienRecordService().DetailView(hostId, hostPwd, mrNo);
				
		RequestDispatcher view = null;
		if(medicienRecord != null) {
			view = request.getRequestDispatcher("views/ERP/medicienRecord/MedicienRecordDetailView.jsp");
			request.setAttribute("medicienRecord", medicienRecord);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", mrNo + "투약일지 상세조회 실패!");
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
