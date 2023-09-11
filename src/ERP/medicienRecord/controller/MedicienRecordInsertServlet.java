package ERP.medicienRecord.controller;

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

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ERP.Employee.model.vo.Employee;
import ERP.medicienRecord.model.service.MedicienRecordService;
import ERP.medicienRecord.model.vo.MedicienRecord;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class MedicienRecordInsertServlet
 */
@WebServlet("/recordinsert")
public class MedicienRecordInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicienRecordInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�닾�빟�씪吏� �벑濡� 泥섎━�슜 而⑦듃濡ㅻ윭
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
			request.setAttribute("message", "form enctype 속성이어야만 합니다.");
			view.forward(request, response);
		}
		
		int maxSize = 1024 * 1024 * 50;
		
		String savePath = request.getSession().getServletContext().getRealPath("/resources/ERP/mr_upfiles");
		
		MultipartRequest mrequest = new MultipartRequest(request, savePath,
				maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		MedicienRecord medicienRecord = new MedicienRecord();
		
		medicienRecord.setMrState(mrequest.getParameter("mr_state"));
		medicienRecord.setMrName(mrequest.getParameter("mr_name"));
		medicienRecord.setMrTime(mrequest.getParameter("mr_time"));
		medicienRecord.setMrMany(mrequest.getParameter("mr_many"));
		medicienRecord.setMrComment(mrequest.getParameter("mr_comment"));
		medicienRecord.setMrPatName(mrequest.getParameter("mr_pat_name"));
		medicienRecord.setMrEmpName(mrequest.getParameter("mr_emp_name"));
		medicienRecord.setMrOriginalFileName(mrequest.getParameter("mr_original_filename"));
		medicienRecord.setMrRenameFileName(mrequest.getParameter("mr_rename_filename"));
		
		String originalFileName = mrequest.getFilesystemName("mr_original_filename");
		
		if(originalFileName != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			
			String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()))
					+ "." + originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			
			File originFile = new File(savePath + "\\" +  originalFileName);
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
			
			medicienRecord.setMrOriginalFileName(originalFileName);
			medicienRecord.setMrRenameFileName(renameFileName);
		}
		
		int result = new MedicienRecordService().insertMedicienRecord(hostId, hostPwd, medicienRecord);
		
		if(result > 0) {
			if(emp != null) {
				response.sendRedirect("/NHMP/views/ERP/Employee.jsp");
			}else {
				response.sendRedirect("/NHMP/views/ERP/Admin_main.jsp");
			}
			
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "투약일지 등록 실패!");
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
