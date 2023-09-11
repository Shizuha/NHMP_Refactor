package ERP.medicienRecord.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Employee.model.vo.Employee;
import ERP.medicienRecord.model.service.MedicienRecordService;
import Main.vo.NursingHospitalVo;


/**
 * Servlet implementation class MedicienRecordDeleteServlet
 */
@WebServlet("/recorddelete")
public class MedicienRecordDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicienRecordDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�닾�빟�씪吏� �궘�젣 泥섎━�슜 而⑦듃濡ㅻ윭
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
		
		int mrNo = Integer.parseInt(request.getParameter("mr_no"));
		String renameFileName = request.getParameter("rfile");
		
		int result = new MedicienRecordService().deleteMedicienRecord(hostId, hostPwd, mrNo);
		
		if(result > 0) {
			if(renameFileName != null) {
				String savePath = request.getSession().getServletContext().getRealPath("/resources/ERP/mr_upfiles");
				File renameFile = new File(savePath + "\\" + renameFileName);
				renameFile.delete();
			}
			if(emp != null) {
				response.sendRedirect("views/ERP/Employee.jsp");
			}else {
				response.sendRedirect("views/ERP/Admin_main.jsp");
			}
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", mrNo + "踰� �닾�빟�씪吏� �궘�젣 �떎�뙣!");
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
