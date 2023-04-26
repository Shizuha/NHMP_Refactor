package ERP.Employee.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ERP.Career.model.service.CareerService;
import ERP.Career.model.vo.Career;
import ERP.Dependents.model.service.DependentsService;
import ERP.Dependents.model.vo.Dependents;
import ERP.Education.model.service.EducationService;
import ERP.Education.model.vo.Education;
import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import ERP.Empsalary.model.service.EmpSalaryService;
import ERP.Empsalary.model.vo.EmpSalary;
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class EmployeeUpdateServlet
 */
@WebServlet("/empup")
public class EmployeeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if(isMulti != true) {
			pw.println("<script >");
			pw.println("alert('정상적인 발송방식이 아닙니다 확인하세요.')");
			pw.println("history.back()");
			pw.println("</script>");
			pw.flush();
			pw.close();
			
		}
		
		int maxSize = 1024 * 1024 * 10;
		
		String savePath = request.getSession().getServletContext().getRealPath("/resources/ERP/emp_Img_file");
		
		MultipartRequest mrequest = new MultipartRequest(request , savePath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		
		String hostId = null;
		String hostPwd = null;
		Employee emp1 = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
		System.out.println(emp1);
		System.out.println(loginHospital);
		if(emp1 != null) {
		
		hostId = emp1.getHostId();
		hostPwd = emp1.getHostPwd();
		}else {
			hostId = loginHospital.getNH_USERID();
			hostPwd = loginHospital.getNH_USERPWD();
		}
		
		
		
		Employee emp = new Employee();
		//사원 기본정보 담기
		emp.setHostId(hostId);
		emp.setHostPwd(hostPwd);
		emp.setEmpName(mrequest.getParameter("empname"));
		emp.setEmpNo(mrequest.getParameter("empno1") + "-" + mrequest.getParameter("empno2"));
		String address1 = mrequest.getParameter("address1");
		
		if(address1 == null)
			address1 = ",";
		String address2 = mrequest.getParameter("address2");
		
		if(address2 == null)
			address2 = ",";
		String address3 = mrequest.getParameter("address3");
		
		if(address3 == null)
			address3 = ",";
		String address4 = mrequest.getParameter("address4");
		
		if(address4 == null)
			address4 = ",";
		
		emp.setAddress(address1 +"," + address2 + "," + address3 + "," + address4);
		String itfor = mrequest.getParameter("itfornal");
		emp.setItnalFor(itfor);
		
		emp.setAdTel(mrequest.getParameter("adtel1") + "-" + 
		mrequest.getParameter("adtel2") +"-"+ mrequest.getParameter("adtel3"));
		emp.setPhone(mrequest.getParameter("phone")+"-"+
				mrequest.getParameter("phone2") +"-" + mrequest.getParameter("phone3"));
		emp.setEmail(mrequest.getParameter("email"));
		emp.setSalary(Integer.parseInt(mrequest.getParameter("salary")));
		emp.setUserId(mrequest.getParameter("empids"));
		emp.setUserpwd(mrequest.getParameter("emppwds"));
		emp.setEmpEtc(mrequest.getParameter("etc"));
		emp.setGender(mrequest.getParameter("gender"));
		
		String empment = mrequest.getParameter("empment");
		if(empment.equals("0") != true) {
			emp.setEmpmentCode(empment);
		}else {
			empment = "0";
			emp.setEmpmentCode(empment);
		}
		String dept = mrequest.getParameter("dept");
		if(dept.equals("0") != true) 
			emp.setDeptCode(dept);
		else {
			dept = "0";
			emp.setDeptCode(dept);
		}
		String team = mrequest.getParameter("team");
		if(team.equals("0") != true) 
			emp.setTeamCode(team);
		else {
			team = "0";
			emp.setTeamCode(team);
		}
		String job = mrequest.getParameter("job");
		if(job.equals("job") != true)
			emp.setPosCode(job);
		else {
			job = "0";
			emp.setPosCode(job);
		}
		emp.setAuthorityCode(mrequest.getParameter("author"));
		String ward = mrequest.getParameter("ward");
		if(ward.equals("0") != true)
			emp.setWardCode(ward);
		else {
			ward = "0";
			emp.setWardCode(ward);
		}
		String hold = mrequest.getParameter("hold");
		System.out.println(hold);
		if(hold.equals("0") != true)
			emp.setHoldOffice(hold);
		else {
			hold = "0";
			emp.setHoldOffice(hold);
		}
		String originalImgFileName = mrequest.getFilesystemName("upfiles");
		
		
		if(originalImgFileName != null) {
			
			emp.setEmpImgOriginalFilename(originalImgFileName);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String reNameImgFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()))
					+ "." + originalImgFileName.substring(originalImgFileName.lastIndexOf(".") +1);
			
			
			File originalFile = new File(savePath + "\\" + originalImgFileName);
			File reNameFile = new File(savePath + "\\" + reNameImgFileName);
			
			
			if(!originalFile.renameTo(reNameFile)) {
				
				int read = -1;
				byte[] buf = new byte[1024];
				
				
				FileInputStream in = new FileInputStream(originalFile);
				FileOutputStream out = new FileOutputStream(reNameFile);
				
				
				while((read = in.read(buf, 0, buf.length)) != -1) {
					
					out.write(buf, 0, read);
					out.flush();
				}
				
				in.close();
				out.close();
				originalFile.delete();
			}
			emp.setEmpRenameFilename(reNameImgFileName);
			new File(savePath + "\\" + mrequest.getParameter("rfile")).delete();
			
		}else {
			emp.setEmpImgOriginalFilename(mrequest.getParameter("ofile"));
			emp.setEmpRenameFilename(mrequest.getParameter("rfile"));
		}
		String empId = mrequest.getParameter("empid"); 
		emp.setEmpId(empId);
		System.out.println("사원정보 업데이트 하기전 사원 객체" + emp);
		int result = new EmployeeService().updateEmployee(emp);
		
		
		if(result == 0) {
			pw.println("<script >");
			pw.println("alert('사원기본 정보 업데이트 실패.')");
			pw.println("history.back()");
			pw.println("</script>");
			pw.flush();
			pw.close();
			
		}
		
		
		//사원 급여정보란
		EmpSalary empSal = new EmpSalary();
		
		
		String natPension = mrequest.getParameter("no1");
		String healInrance = mrequest.getParameter("no2");
		int healRdc = Integer.parseInt(mrequest.getParameter("no2up"));
		String oldInrance = mrequest.getParameter("no3");
		int oldRdc = Integer.parseInt(mrequest.getParameter("no3up"));
		String ementInrance = mrequest.getParameter("no4");
		String earIncome = mrequest.getParameter("earner1");
		int earInrance = Integer.parseInt(mrequest.getParameter("earner1up"));//새액퍼센트
		String bsnIncome = mrequest.getParameter("earner2");
		String dailyJob = mrequest.getParameter("earner3");
		String etcIncome = mrequest.getParameter("earner4");
		String earBsnIncome = mrequest.getParameter("earner5");
		
		empSal.setEmpId(empId);
		empSal.setNatPension(natPension);
		empSal.setHealInrance(healInrance);
		empSal.setHealRdc(healRdc);
		empSal.setOldInrance(oldInrance);
		empSal.setOldRdc(oldRdc);
		empSal.setEmentInrance(ementInrance);
		empSal.setEarIncome(earIncome);
		empSal.setEarInrance(earInrance);
		empSal.setEarBsnIncome(earBsnIncome);
		empSal.setDailyJob(dailyJob);
		empSal.setEtcIncome(etcIncome);
		empSal.setBsnIncome(bsnIncome);
		System.out.println(empSal);
		
		result = new EmpSalaryService().updateEmpSalary(empSal,hostId, hostPwd);
		int inserResult = 0;
		if(result == 0) {
			inserResult = new EmpSalaryService().insertEmpSalary(empSal, hostId,hostPwd);
			if(inserResult == 0) {
				pw.println("<script >");
				pw.println("alert('급여정보 업데이트  실패!')");
				pw.println("history.back()");
				pw.println("</script>");
				pw.flush();
				pw.close();
			}else {
				
			}
		}
			
		
		
		
		String[] rship = mrequest.getParameterValues("rship");
		String[] fyname = mrequest.getParameterValues("fyname");
		String[] fyitfornal = mrequest.getParameterValues("fyitfornal");
		System.out.println(fyitfornal);
		String[] dibility = mrequest.getParameterValues("DIBILITY");
		String[] hIsc = mrequest.getParameterValues("H_ISC");
		String[] iTogether = mrequest.getParameterValues("I_TOGETHER");
		String[] mChild = mrequest.getParameterValues("M_CHILD");
		if(rship != null && fyname != null && fyitfornal != null && dibility != null && 
				hIsc != null && iTogether != null && mChild != null) {
		ArrayList<Dependents> drr = new ArrayList<Dependents>();
		
		for(int i = 0; i < rship.length; i++) {
			
			String rrship = rship[i];
			String rfyname = fyname[i];
			String rfyitfornal = fyitfornal[i];
			String rDIBILITY = dibility[i];
			String rhISC = hIsc[i];
			String riTOGETHER = iTogether[i];
			String rmChild = mChild[i];
			
		drr.add(new Dependents(rrship, rfyname, rfyitfornal, rDIBILITY, rhISC, riTOGETHER, rmChild, empId));
			}
		System.out.println(drr);
		int result2 = 0;
		String[] fyNo = new String[drr.size()];
		String fyno1 = null;
		fyNo = new DependentsService().selectDepenCode(empId, fyNo.length,hostId, hostPwd);
		
		int i = 0;
		for(Dependents d : drr) {
			fyno1 = fyNo[i]; 
			result2 = new DependentsService().updateDependent(d, fyno1,hostId, hostPwd);
			i++;
			
		}
		System.out.println(result2);
		if(result2 == 0) {
			pw.println("<script >");
			pw.println("alert('부양가족수정실패!')");
			pw.println("history.back()");
			pw.println("</script>");
			pw.flush();
			pw.close();
				}
			
		}
		
		String[] itforNal = mrequest.getParameterValues("shcool");
		if(itforNal.equals("0") == true) {
			itforNal = null;
		}
		String[] adDate = mrequest.getParameterValues("AD_DATE");
		String[] grDate = mrequest.getParameterValues("GR_DATE");
		String[] schName = mrequest.getParameterValues("SCH_NAME");
		String[] major = mrequest.getParameterValues("MAJOR");
		String[] taking = mrequest.getParameterValues("TAKING");
		
		if(adDate != null && grDate != null && schName != null && 
				major != null && taking != null) {
		ArrayList<Education> eduList = new ArrayList<Education>();
		
		for(int i = 0; i < itforNal.length; i++) {
			
			String itforNal1 = itforNal[i];
			String adDate1 = adDate[i];
			String grDate1 = grDate[i];
			String schName1 = schName[i];
			String major1 = major[i];
			String taking1 = taking[i];
			
			
			
			eduList.add(new Education(itforNal1, adDate1, grDate1, schName1, major1, taking1, empId));
			}
			String[] eduCode = new String[eduList.size()];
			String eduCode1 = null;
			eduCode = new EducationService().selectEduCode(empId, eduCode.length,hostId, hostPwd);
		
			int b = 0;
			int result3 = 0;
			for(Education e : eduList) {
				eduCode1 = eduCode[b];
			 result3 = new EducationService().updateEdu(e, eduCode1,hostId, hostPwd);
			 b++;
			}
		
		 if(result3 == 0) {
			 pw.println("<script >");
				pw.println("alert('학력정보등록실패')");
				pw.println("history.back()");
				pw.println("</script>");
				pw.flush();
				pw.close();
			 }
			 
		 	
		}
		
		 	String[] comName = mrequest.getParameterValues("COM_NAME");
			String[] hireDate = mrequest.getParameterValues("HIRE_DATE");
			String[] lastDate = mrequest.getParameterValues("LAST_DATE");
			String[] workTeam = mrequest.getParameterValues("WORK_TEAM");
			String[] lastPosition = mrequest.getParameterValues("LAST_POSITION");
			String[] resBilties = mrequest.getParameterValues("RES_BILTIES");
			String[] leaveReason = mrequest.getParameterValues("LEAVE_REASON");
			
			if(comName != null && hireDate != null && lastDate != null && workTeam != null && 
					lastPosition != null && resBilties != null && leaveReason != null) {
			ArrayList<Career> carList = new ArrayList<Career>();
			
			for(int i = 0; i < comName.length; i++) {
				
				String comName1 = comName[i];
				System.out.println(comName1);
				String hireDate1 = hireDate[i];
				System.out.println(hireDate1);
				String lastDate1 = lastDate[i];
				System.out.println(lastDate1);
				String workTeam1 = workTeam[i];
				System.out.println(workTeam1);
				String lastPosition1 = lastPosition[i];
				System.out.println(lastPosition1);
				String resBilties1 = resBilties[i];
				System.out.println(resBilties1);
				String leaveReason1 = leaveReason[i];
				System.out.println(leaveReason1);
				
				
				carList.add(new Career(empId, comName1, hireDate1, lastDate1, workTeam1, lastPosition1, resBilties1, leaveReason1));
				}
			System.out.println(carList);
			String[] car = new String[carList.size()];
			String car2 = null;
			
			car = new CareerService().selectCarCode(empId, car.length,hostId, hostPwd);
			
			for(Career c : carList) {
				int result4 = new CareerService().updateCar(c,hostId, hostPwd);
			
			}
			if(result == carList.size()) {
				response.sendRedirect("/NHMP/list");
				
			}else{
					pw.println("<script >");
					pw.println("alert('error')");
					pw.println("history.back()");
					pw.println("</script>");
					pw.flush();
					pw.close();
				}
				
			
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
