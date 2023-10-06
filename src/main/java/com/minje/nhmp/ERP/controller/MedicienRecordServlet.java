package com.minje.nhmp.ERP.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.minje.nhmp.ERP.vo.EmployeeVo;
import com.minje.nhmp.ERP.service.MedicienRecordService;
import com.minje.nhmp.ERP.vo.MedicienRecordVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

@WebServlet(urlPatterns = {"/recordinsert", "/recordlistview", "/recorddetail", "/recordupdate", "/recorddelete", "/recordsearch", "/recordfdown"})
public class MedicienRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MedicienRecordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getServletPath().equals("recordinsert")) {
			//�닾�빟�씪吏� �벑濡� 泥섎━�슜 而⑦듃濡ㅻ윭
			request.setCharacterEncoding("utf-8");
			
			String hostId = null;
			String hostPwd = null;
			EmployeeVo emp = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
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
			
			MedicienRecordVo medicienRecord = new MedicienRecordVo();
			
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
		
		if(request.getServletPath().equals("/recordlistview")) {
			// 투약일지 전체조회 처리용 컨트롤러
			request.setCharacterEncoding("utf-8");
			
			String hostId = null;
			String hostPwd = null;
			EmployeeVo emp = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			if(emp != null) {
			
			hostId = emp.getHostId();
			hostPwd = emp.getHostPwd();
			}else {
				hostId = loginHospital.getNH_USERID();
				hostPwd = loginHospital.getNH_USERPWD();
			}
			
			int currentPage = 1;
			if(request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
			
			int limit = 10;		//한 페이지에 출력할 목록 갯수
			MedicienRecordService mservice = new MedicienRecordService();
			
			int listCount = mservice.getListCount(hostId, hostPwd);	//테이블의 전체 목록 갯수 조회
			//총 페이지 수 계산
			int maxPage = listCount / limit;
			if(listCount % limit > 0)
				maxPage++;
			
			//currentPage 가 속한 페이지그룹의 시작페이지숫자와 끝숫자 계산
			//예, 현재 34페이지이면 31 ~ 40 이 됨. (페이지그룹의 수를 10개로 한 경우)
			int beginPage = (currentPage / limit) * limit + 1;
			int endPage = beginPage + 9;
			if(endPage > maxPage)
				endPage = maxPage;
			
			//currentPage 에 출력할 목록의 조회할 행 번호 계산
			int startRow = (currentPage * limit) - 9;
			int endRow = currentPage * limit;
			
			ArrayList<MedicienRecordVo> list = new MedicienRecordService().ListView(startRow, endRow, hostId, hostPwd);
			
			RequestDispatcher view = null;
			
			if(list.size() > 0) {
				view = request.getRequestDispatcher("views/ERP/medicienRecord/MedicienRecordListView.jsp");
				request.setAttribute("list", list);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("beginPage", beginPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("listCount", listCount);
				view.forward(request, response);
			} else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "투약일지 전체조회 실패!");
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/recorddetail")) {
			//투약일지 상세조회 처리용 컨트롤러
			String hostId = null;
			String hostPwd = null;
			EmployeeVo emp = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
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
		
		if(request.getServletPath().equals("/recordupdate")) {
			//투약일지 수정 처리용 컨트롤러
			request.setCharacterEncoding("utf-8");
			
			String hostId = null;
			String hostPwd = null;
			EmployeeVo emp = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
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
				request.setAttribute("message", "form enctype 속성이 multipart 이어야 합니다!");
				view.forward(request, response);
			}
			
			int maxSize = 1024 * 1024 * 50;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/ERP/mr_upfiles");
			
			MultipartRequest mrequest = new MultipartRequest(request, savePath,
					maxSize, "UTF-8", new DefaultFileRenamePolicy());
			
			MedicienRecordVo medicienRecord = new MedicienRecordVo();
			
			medicienRecord.setMrNo(Integer.parseInt(mrequest.getParameter("mr_no")));
			medicienRecord.setMrDate(Date.valueOf(mrequest.getParameter("mr_date")));
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
				medicienRecord.setMrOriginalFileName(originalFileName);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
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
				
				medicienRecord.setMrRenameFileName(renameFileName);
				
				new File(savePath + "\\" +  mrequest.getParameter("rfile")).delete();
				
			}else {
				medicienRecord.setMrOriginalFileName(mrequest.getParameter("ofile"));
				medicienRecord.setMrRenameFileName(mrequest.getParameter("rfile"));
			}
			
			int result = new MedicienRecordService().updateMedicienRecord(hostId, hostPwd, medicienRecord);
			
			if(result > 0) {
				if(emp != null) {
					response.sendRedirect("views/ERP/Employee.jsp");
				}else {
					response.sendRedirect("views/ERP/Admin_main.jsp");
				}
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", medicienRecord.getMrNo() + "번 투약일지 수정 실패!");
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/recorddelete")) {
			//�닾�빟�씪吏� �궘�젣 泥섎━�슜 而⑦듃濡ㅻ윭
			request.setCharacterEncoding("utf-8");
			
			String hostId = null;
			String hostPwd = null;
			EmployeeVo emp = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
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
			} else {
				RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", mrNo + "踰� �닾�빟�씪吏� �궘�젣 �떎�뙣!");
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/recordsearch")) {
			//투약일지 검색 처리용 컨트롤러
			request.setCharacterEncoding("utf-8");
			
			String hostId = null;
			String hostPwd = null;
			EmployeeVo emp = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			if(emp != null) {
			
			hostId = emp.getHostId();
			hostPwd = emp.getHostPwd();
			} else {
				hostId = loginHospital.getNH_USERID();
				hostPwd = loginHospital.getNH_USERPWD();
			}
			
			String search = request.getParameter("search");
			
			ArrayList<MedicienRecordVo> list = null;
			
			// MedicienRecordService mservice = new MedicienRecordService();
			
			// switch(search) {
			// case "mr_pat_name" : String mrPatName = request.getParameter("mr_pat_name");
			// 						list = mservice.selectMrPatNameSearch(hostId, hostPwd, mrPatName);
			// 						break;
			// case "mr_emp_name" : String mrEmpName = request.getParameter("mr_emp_name");
			// 								list = mservice.selectMrEmpNameSearch(hostId, hostPwd, mrEmpName);
			// 								break;
			// }
			
			RequestDispatcher view = null;
			if(list.size() > 0) {
				view = request.getRequestDispatcher("views/ERP/medicienRecord/MedicienRecordListView.jsp");
				request.setAttribute("list", list);
				view.forward(request, response);
			} else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", search + "검색 조회 실패!");
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/recordfdown")) {
			//투약일지 파일 다운로드 처리용 컨트롤러
			String savePath = request.getSession().getServletContext().getRealPath("/resources/ERP/mr_upfiles");
			
			String originalFileName = request.getParameter("ofile");
			String renameFileName = request.getParameter("rfile");
			
			File downFile = new File(savePath + "\\" + renameFileName);
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(downFile));
			
			ServletOutputStream downOut = response.getOutputStream();
			
			response.setContentType("text/plain; charset=utf-8");
			response.addHeader("Content-Disposition", "attachment; filename=\'"
					+ new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1") + "\'");
			response.setContentLength((int)downFile.length());
			
			int read = -1;
			while((read = bin.read()) != -1) {
				downOut.write(read);
				downOut.flush();
			}
			
			downOut.close();
			bin.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
