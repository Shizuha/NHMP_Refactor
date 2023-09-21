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
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class EmployeeInsertServlet
 */
@WebServlet("/empin")
public class EmployeeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeInsertServlet() {
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
		
		
		Employee emp = new Employee();
		//사원 기본정보 담기
		String hostId = null;
		String hostPwd = null;
		Employee emp1 = (Employee)request.getSession().getAttribute("loginEmployee");
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		if(emp1 != null) {
		
		hostId = emp1.getHostId();
		hostPwd = emp1.getHostPwd();
		}else {
			hostId = loginHospital.getNH_USERID();
			hostPwd = loginHospital.getNH_USERPWD();
		}
		
		emp.setHostId(hostId);
		emp.setHostPwd(hostPwd);
		emp.setEmpName(mrequest.getParameter("empname"));
		emp.setEmpNo(mrequest.getParameter("empno1") + "-" + mrequest.getParameter("empno2"));
		String address1 = mrequest.getParameter("address1");
		if(address1 != null)
			address1 += ",";
		String address2 = mrequest.getParameter("address2");
		if(address2 != null)
			address2 += ",";
		String address3 = mrequest.getParameter("address3");
		if(address3 != null)
			address3 += ",";
		String address4 = mrequest.getParameter("address4");
		if(address4 != null)
			address4 += " ";
		
		emp.setAddress(address1 + address2 + address3 + address4);
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
			empment = null;
			emp.setEmpmentCode(empment);
		}
		String dept = mrequest.getParameter("dept");
		if(dept.equals("0") != true) 
			emp.setDeptCode(dept);
		else {
			dept = null;
			emp.setDeptCode(dept);
		}
		String team = mrequest.getParameter("team");
		if(team.equals("0") != true) 
			emp.setTeamCode(team);
		else {
			team = null;
			emp.setTeamCode(team);
		}
		String job = mrequest.getParameter("job");
		if(job.equals("0") != true)
			emp.setPosCode(job);
		else {
			job = null;
			emp.setPosCode(job);
		}
		emp.setAuthorityCode(mrequest.getParameter("author"));
		String ward = mrequest.getParameter("ward");
		if(ward.equals("0") != true)
			emp.setWardCode(ward);
		else {
			ward = null;
			emp.setWardCode(ward);
		}
		String hold = mrequest.getParameter("hold");
		if(hold.equals("0") != true)
			emp.setHoldOffice(hold);
		else {
			hold = null;
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
			
		}
		System.out.println("insert하기전 employee 정보 =" + emp);
		int result = new EmployeeService().insertEmp(emp);
		
		
		if(result == 0) {
			pw.println("<script >");
			pw.println("alert('사원정보 등록실패')");
			pw.println("history.back()");
			pw.println("</script>");
			pw.flush();
			pw.close();
		}
		System.out.println("사원정보 등록한 행=" + result);
		System.out.println("주민번호로 사원 한명 조회해오기전 사원주민번호=" + emp.getEmpNo());
		Employee emp2 = new EmployeeService().selectOne(emp.getEmpNo(),hostId, hostPwd);
		System.out.println("insert한뒤 사원주민번호로 사원조회해온 객체=" + emp2);
		//사원 급여정보란
		EmpSalary empSal = new EmpSalary();
		
		
		String natPension = mrequest.getParameter("no1");
		String healInrance = mrequest.getParameter("no2");
		String healRdc = mrequest.getParameter("no2up");
		int intHealRdc = 0;
		if(healRdc != null) {
			empSal.setHealRdc(Integer.parseInt(healRdc));
		}else {
			empSal.setHealRdc(intHealRdc); 
		}
		System.out.println(healRdc);
		String oldInrance = mrequest.getParameter("no3");
		
		String oldRdc2 = mrequest.getParameter("no3up");
		int oldRdc = 0;
		if(oldRdc2 != null) {
			empSal.setOldRdc(Integer.parseInt(mrequest.getParameter("no3up")));
		}else {
			empSal.setHealRdc(oldRdc); 
		}
		String ementInrance = mrequest.getParameter("no4");
		String earIncome = mrequest.getParameter("earner1");
		int earInrance = 0;
		String earInrance2 = mrequest.getParameter("earner1up");//새액퍼센트
		if(earInrance2 != null) {
			empSal.setEarInrance(Integer.parseInt(earInrance2));
		}else {
			empSal.setEarInrance(earInrance);
		}
		
		String bsnIncome = mrequest.getParameter("earner2");
		String dailyJob = mrequest.getParameter("earner3");
		String etcIncome = mrequest.getParameter("earner4");
		String earBsnIncome = mrequest.getParameter("earner5");
		
		empSal.setEmpId(emp2.getEmpId());
		empSal.setNatPension(natPension);
		empSal.setHealInrance(healInrance);
		empSal.setOldInrance(oldInrance);
		empSal.setEmentInrance(ementInrance);
		empSal.setEarIncome(earIncome);
		empSal.setEarBsnIncome(earBsnIncome);
		empSal.setDailyJob(dailyJob);
		empSal.setEtcIncome(etcIncome);
		empSal.setBsnIncome(bsnIncome);
		System.out.println(empSal);
		
		result = new EmpSalaryService().insertEmpSalary(empSal, hostId, hostPwd);
		
		if(result == 0) {
			pw.println("<script >");
			pw.println("alert('기본정보 등록실패!')");
			pw.println("history.back()");
			pw.println("</script>");
			pw.flush();
			pw.close();
		}
		int result2 = 0;
		
		
		String[] rship = mrequest.getParameterValues("rship");
		String[] fyname = mrequest.getParameterValues("fyname");
		String[] fyitfornal = mrequest.getParameterValues("fyitfornal");
		String[] DIBILITY = mrequest.getParameterValues("DIBILITY");
		String[] hISC = mrequest.getParameterValues("H_ISC");
		String[] iTOGETHER = mrequest.getParameterValues("I_TOGETHER");
		String[] mChild = mrequest.getParameterValues("M_CHILD");
		
		ArrayList<Dependents> drr = new ArrayList<Dependents>();
		if(rship != null) {
		for(int i = 0; i < rship.length; i++) {
			
			String rrship = rship[i];
			String rfyname = fyname[i];
			String rfyitfornal = fyitfornal[i];
			String rDIBILITY = DIBILITY[i];
			String rhISC = hISC[i];
			String riTOGETHER = iTOGETHER[i];
			String rmChild = mChild[i];
			
			
			drr.add(new Dependents(rrship, rfyname, rfyitfornal, rDIBILITY, rhISC, riTOGETHER, rmChild, emp2.getEmpId()));
			}
		
		
		for(Dependents d : drr) {
		
		result2 = new DependentsService().insertDependent(d,hostId, hostPwd);
		
		}
		System.out.println(result2);
		if(result2 == 0) {
			pw.println("<script >");
			pw.println("alert('부양가족 등록실패.')");
			pw.println("history.back()");
			pw.println("</script>");
			pw.flush();
			pw.close();
			}
		}
		
		String[] itforNal = mrequest.getParameterValues("shcool");
		String[] adDate = mrequest.getParameterValues("AD_DATE");
		String[] grDate = mrequest.getParameterValues("GR_DATE");
		String[] schName = mrequest.getParameterValues("SCH_NAME");
		String[] major = mrequest.getParameterValues("MAJOR");
		String[] taking = mrequest.getParameterValues("TAKING");
		if(itforNal != null) {
		
		ArrayList<Education> eduList = new ArrayList<Education>();
		
		for(int i = 0; i < itforNal.length; i++) {
			
			String itforNal1 = itforNal[i];
			String adDate1 = adDate[i];
			String grDate1 = grDate[i];
			String schName1 = schName[i];
			String major1 = major[i];
			String taking1 = taking[i];
			
			
			
			eduList.add(new Education(itforNal1, adDate1, grDate1, schName1, major1, taking1, emp2.getEmpId()));
			}
		
			int result3 = 0;
			for(Education e : eduList) {
				
			 result3 = new EducationService().insertEdu(e, hostId, hostPwd);
			
			}
		System.out.println(eduList);
		 if(result3 == 0) {
			 pw.println("<script >");
				pw.println("alert('학력정보 등록실패.')");
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
			
			if(comName != null) {
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
				
				
				carList.add(new Career(emp2.getEmpId(), comName1, hireDate1, lastDate1, workTeam1, lastPosition1, resBilties1, leaveReason1));
				}
			
			System.out.println(carList);
			int result4 = 0;
			for(Career c : carList) {
			result4 = new CareerService().inserCar(c, hostId, hostPwd);
			}
			
			if(result4 == carList.size()) {
				if(emp1 != null)
					response.sendRedirect("/NHMP/views/ERP/Employee.jsp");
					else 
					response.sendRedirect("/NHMP/views/ERP/Admin_main.jsp");
					
				
				
			}else {
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
