package ERP.patient.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Employee.model.vo.Employee;
import ERP.patient.model.service.PatientService;
import ERP.patient.model.vo.Patient;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class PatientUpdateServlet
 */
@WebServlet("/patientupdate")
public class PatientUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//환자 수정 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		String hostId = null;
		String hostPwd = null;
		
		String pat_nof = null;
		String pat_nob = null;
		String pat_no = null;
		
		String pat_phonef = null;
		String pat_phonem = null;
		String pat_phoneb = null;
		String pat_phone = null;
		
		Employee emp = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		if(emp != null) {
		
		hostId = emp.getHostId();
		hostPwd = emp.getHostPwd();
		}else {
			hostId = loginHospital.getNH_USERID();
			hostPwd = loginHospital.getNH_USERPWD();
		}
		
		Patient patient = new Patient();
		pat_nof = request.getParameter("pat_nof");
		pat_nob = request.getParameter("pat_nob");
		pat_no = pat_nof + "-" +  pat_nob;
		
		pat_phonef = request.getParameter("pat_phonef");
		pat_phonem = request.getParameter("pat_phonem");
		pat_phoneb = request.getParameter("pat_phoneb");
		pat_phone = pat_phonef + "-" + pat_phonem + "-" + pat_phoneb;
		
		patient.setPatName(request.getParameter("pat_name"));
		patient.setPatType(request.getParameter("pat_type"));
		patient.setPatGender(request.getParameter("pat_gender"));
		patient.setPatNo(pat_no);
		patient.setAddress(request.getParameter("address"));
		patient.setFamily(request.getParameter("family"));
		patient.setEmail(request.getParameter("email"));
		patient.setPatPhone(pat_phone);
		patient.setWard(request.getParameter("ward"));
		patient.setPatDoc(request.getParameter("pat_doc"));
		
		int result = new PatientService().updatePatient(hostId, hostPwd, patient);
		
		
		if(result > 0) {
			if(emp != null) {
				response.sendRedirect("views/ERP/Employee.jsp");
			}else {
				response.sendRedirect("views/ERP/Admin_main.jsp");
			}
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", patient.getPatNum() + "번 투약일지 수정 실패!");
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
