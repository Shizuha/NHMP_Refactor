package com.minje.nhmp.ERP.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.minje.nhmp.ERP.service.AllowanceService;
import com.minje.nhmp.ERP.vo.AllowanceVo;
import com.minje.nhmp.ERP.service.DeductionService;
import com.minje.nhmp.ERP.vo.DeductionVo;
import com.minje.nhmp.ERP.service.EmployeeService;
import com.minje.nhmp.ERP.vo.EmployeeVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

@WebServlet(urlPatterns = {"/Epaylist", "/getpay", "/paylist"})
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PayServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getServletPath().equals("/Epaylist")) {
				// 직원 급여 계산 처리용 컨트롤러
				EmployeeVo loginEmployee = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
				NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
				if(loginEmployee != null) {//관리자 로그인
				System.out.println(loginEmployee);
				String hostid = loginEmployee.getHostId();
				String hostpwd = loginEmployee.getHostPwd();
				System.out.println(hostid);
				System.out.println(hostpwd);
				
				ArrayList<DeductionVo> Dlist = new DeductionService().EselectList(hostid,hostpwd);
				System.out.println("Dlist : "+Dlist);
				ArrayList<AllowanceVo> Alist = new AllowanceService().EselectList(hostid,hostpwd);
				System.out.println("Alist : "+Alist);
				EmployeeVo emp = new EmployeeService().selectOne(loginEmployee.getEmpNo(), hostid, hostpwd);
				System.out.println("emp : "+emp);
				RequestDispatcher view = null;
				if(Dlist.size() > 0) {
					view = request.getRequestDispatcher("views/ERP/PaySum/EPaySum.jsp");
					request.setAttribute("Dlist", Dlist);
					request.setAttribute("Alist", Alist);
					request.setAttribute("emp", emp);
				}else {
					view = request.getRequestDispatcher("views/common/Error.jsp");
					request.setAttribute("message", "페이지 조회 실패");
				}
				view.forward(request, response);
			}else {//직원 로그인
				System.out.println(loginHospital);
				ArrayList<DeductionVo> Dlist = new DeductionService().selectList(loginHospital);
				ArrayList<AllowanceVo> Alist = new AllowanceService().selectList(loginHospital);
				ArrayList<EmployeeVo> Elist = new EmployeeService().selectAll(loginHospital);
				System.out.println(Elist);
				RequestDispatcher view = null;
				
				if(Dlist.size() > 0) {
					view = request.getRequestDispatcher("views/ERP/PaySum/PaySum.jsp");
					request.setAttribute("Dlist", Dlist);
					request.setAttribute("Alist", Alist);
					request.setAttribute("Elist", Elist);
				}else {
					view = request.getRequestDispatcher("views/common/Error.jsp");
					request.setAttribute("message", "페이지 조회 실패");
				}
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/getpay")) {
			// 선택된 직원 기본급 조회용 컨트롤러
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			String empid = request.getParameter("empid");
			System.out.println("servlet : "+empid);
			
			EmployeeVo emp = new EmployeeService().selectIDOne(empid,loginHospital);
			
			JSONObject job = new JSONObject();
			System.out.println("servlet : "+emp);
			job.put("empid", emp.getEmpId());
			job.put("salary", emp.getSalary());
			
			System.out.println("servlet : "+job);
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(job.toJSONString());
			out.flush();
			out.close();
		}
		
		if(request.getServletPath().equals("/paylist")) {
			// 급여 계산에 사용될 공제 및 수당 리스트 처리용 컨트롤러
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			System.out.println(loginHospital);
			ArrayList<DeductionVo> Dlist = new DeductionService().selectList(loginHospital);
			ArrayList<AllowanceVo> Alist = new AllowanceService().selectList(loginHospital);
			ArrayList<EmployeeVo> Elist = new EmployeeService().selectAll(loginHospital);
			System.out.println(Elist);
			RequestDispatcher view = null;
			
			if(Dlist.size() > 0) {
				view = request.getRequestDispatcher("views/ERP/PaySum/PaySum.jsp");
				request.setAttribute("Dlist", Dlist);
				request.setAttribute("Alist", Alist);
				request.setAttribute("Elist", Elist);
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "페이지 조회 실패");
			}
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
