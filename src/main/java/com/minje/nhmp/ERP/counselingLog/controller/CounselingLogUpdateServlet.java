package ERP.counselingLog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ERP.Employee.model.vo.Employee;
import ERP.counselingLog.model.service.CounselingLogService;
import ERP.counselingLog.model.vo.CounselingLog;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class CounselingLogUpdateServlet
 */
@WebServlet("/counselupdate")
public class CounselingLogUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CounselingLogUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//환자 상담일지 수정 처리용 컨트롤러
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
		
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "form enctype 속성이어야만 합니다");
			view.forward(request, response);
		}
		
		int maxSize = 1024 * 1024 * 50;
		
		String savePath = request.getSession().getServletContext().getRealPath("/resources/ERP/cl_upfiles");
		
		MultipartRequest mrequest = new MultipartRequest(request, savePath,
				maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		CounselingLogVo counselingLog = new CounselingLogVo();
		
		counselingLog.setClNo(Integer.parseInt(mrequest.getParameter("cl_no")));
		counselingLog.setClTitle(mrequest.getParameter("cl_title"));
		counselingLog.setClDate(Date.valueOf(mrequest.getParameter("cl_date")));
		counselingLog.setClContents(mrequest.getParameter("cl_contents"));
		counselingLog.setClPhone(mrequest.getParameter("cl_phone"));
		counselingLog.setClComment(mrequest.getParameter("cl_comment"));
		counselingLog.setClPatName(mrequest.getParameter("cl_pat_name"));
		counselingLog.setClEmpName(mrequest.getParameter("cl_emp_name"));
		counselingLog.setClOriginalFileName(mrequest.getParameter("cl_original_filename"));
		counselingLog.setClRenameFileName(mrequest.getParameter("cl_rename_filename"));
		
		String originalFileName = mrequest.getParameter("cl_original_filename");
		
		if(originalFileName != null) {
			counselingLog.setClOriginalFileName(originalFileName);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()))
					+ "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			
			File originFile = new File(savePath + "\\" + originalFileName);
			File renameFile = new File(savePath + "\\" + renameFileName);
			
			if(!originFile.renameTo(renameFile)) {
				int read = -1;
				byte[] buf = new byte[1024];
				
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				
				while((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}
				
				fin.close();
				fout.close();
				originFile.delete();
			}
			
			counselingLog.setClRenameFileName(renameFileName);
			
			new File(savePath + "\\" + mrequest.getParameter("rfile")).delete();
			
		}else {
			counselingLog.setClOriginalFileName(mrequest.getParameter("ofile"));
			counselingLog.setClRenameFileName(mrequest.getParameter("rfile"));
		}
		
		int result = new CounselingLogService().updateCounselingLog(hostId, hostPwd, counselingLog);
		
		if(result > 0) {
			if(emp != null) {
				response.sendRedirect("views/ERP/Employee.jsp");
			}else {
				response.sendRedirect("views/ERP/Admin_main.jsp");
			}
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", counselingLog.getClNo() + "번 환자 상담일지 수정 실패!");
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
