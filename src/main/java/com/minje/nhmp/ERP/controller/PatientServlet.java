package com.minje.nhmp.ERP.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minje.nhmp.ERP.vo.EmployeeVo;
import com.minje.nhmp.ERP.service.CounselingLogService;
import com.minje.nhmp.ERP.service.PatientService;
import com.minje.nhmp.ERP.vo.PatientVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

@WebServlet(urlPatterns = {"/patientinsert", "/patientlistview", "/patientdetail", "/patientupdate", "/patientdelete"})
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PatientServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getServletPath().equals("/patientinsert")) {
			//환자 등록 처리용 컨트롤러
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
			
			EmployeeVo emp = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			if(emp != null) {
			
			hostId = emp.getHostId();
			hostPwd = emp.getHostPwd();
			}else {
				hostId = loginHospital.getNH_USERID();
				hostPwd = loginHospital.getNH_USERPWD();
			}
			
			PatientVo patient = new PatientVo();
			pat_nof = request.getParameter("pat_nof");
			pat_nob = request.getParameter("pat_nob");
			pat_no = pat_nof + "-" +  pat_nob;
			System.out.println(pat_no);
			
			pat_phonef = request.getParameter("pat_phonef");
			pat_phonem = request.getParameter("pat_phonem");
			pat_phoneb = request.getParameter("pat_phoneb");
			pat_phone = pat_phonef + "-" + pat_phonem + "-" + pat_phoneb;
			System.out.println(pat_phone);
			
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
			
			int result = new PatientService().insertPatient(hostId, hostPwd, patient);
			
			if(result > 0) {
				if(emp != null) {
					response.sendRedirect("views/ERP/Employee.jsp");
				}else {
					response.sendRedirect("views/ERP/Admin_main.jsp");
				}
				
			}else {
				RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "환자 등록 실패!");
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/patientlistview")) {
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
			CounselingLogService cservice = new CounselingLogService();
			
			int listCount = cservice.getListCount(hostId, hostPwd);	//테이블의 전체 목록 갯수 조회
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
			
			ArrayList<PatientVo> list = new PatientService().ListView(startRow, endRow, hostId, hostPwd);
			
			RequestDispatcher view = null;
			if(list.size() > 0) {
				view = request.getRequestDispatcher("views/ERP/patient/PatientListView.jsp");
				request.setAttribute("list", list);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("beginPage", beginPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("listCount", listCount);
				view.forward(request, response);
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "환자 전체 조회 실패!");
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/patientdetail")) {
			//환자 상세조회 처리용 컨트롤러
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
			
			int patNum = Integer.parseInt(request.getParameter("pat_num"));
			PatientVo patient = new PatientService().DetailView(hostId, hostPwd, patNum);
			
			RequestDispatcher view = null;
			
			if(patient != null) {
				view = request.getRequestDispatcher("views/ERP/patient/PatientDetailView.jsp");
				request.setAttribute("patient", patient);
				view.forward(request, response);
			} else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", patNum + "번 환자 상세조회 실패!");
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/patientupdate")) {
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
			
			EmployeeVo emp = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			if(emp != null) {
			
			hostId = emp.getHostId();
			hostPwd = emp.getHostPwd();
			}else {
				hostId = loginHospital.getNH_USERID();
				hostPwd = loginHospital.getNH_USERPWD();
			}
			
			PatientVo patient = new PatientVo();
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
		
		if(request.getServletPath().equals("/patientdelete")) {
			//환자 삭제 처리용 컨트롤러
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
			
			int patNum = Integer.parseInt(request.getParameter("pat_num"));
			
			int result = new PatientService().deletePatient(hostId, hostPwd, patNum);
			
			if(result > 0) {
				if(emp != null) {
					response.sendRedirect("views/ERP/Employee.jsp");
				} else {
					response.sendRedirect("views/ERP/Admin_main.jsp");
				}
			} else {
				RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", patNum + "번 환자정보 삭제 실패!");
				view.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
