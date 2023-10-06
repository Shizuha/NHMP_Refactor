package com.minje.nhmp.ERP.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.minje.nhmp.ERP.service.CalendarService;
import com.minje.nhmp.ERP.service.CareerService;
import com.minje.nhmp.ERP.service.DepartmentService;
import com.minje.nhmp.ERP.vo.CalendarVo;
import com.minje.nhmp.ERP.vo.CareerVo;
import com.minje.nhmp.ERP.vo.DepartmentVo;
import com.minje.nhmp.ERP.service.DependentsService;
import com.minje.nhmp.ERP.vo.DependentsVo;
import com.minje.nhmp.ERP.service.EducationService;
import com.minje.nhmp.ERP.vo.EducationVo;
import com.minje.nhmp.ERP.service.EmployeeService;
import com.minje.nhmp.ERP.service.PositionService;
import com.minje.nhmp.ERP.service.TeamService;
import com.minje.nhmp.ERP.service.WardService;
import com.minje.nhmp.ERP.vo.EmployeeVo;
import com.minje.nhmp.ERP.vo.TeamVo;
import com.minje.nhmp.ERP.vo.WardVo;
import com.minje.nhmp.ERP.service.EmpSalaryService;
import com.minje.nhmp.ERP.vo.EmpSalaryVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = { "/empin", "/list", "/edetail", "/empup", "/mdel", "/esel", "/logins", "/emailChk", "/idchk",
	"/logouts", "/namechk", "/newpwd", "/uppwd", "/organlist", "/ochart", "/minfo", "/teamlist", "/emp" })

public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(EmployeeServlet.class);

    public EmployeeServlet() {
	super();
    }

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	boolean isMulti;
	PrintWriter pw;
	String savePath;
	MultipartRequest mrequest;
	String hostId;
	String hostPwd;
	EmployeeVo emp;
	String address1;
	String address2;
	String address3;
	String address4;
	String itfor;
	String empment;
	String dept;
	String team;
	String job;
	String ward;
	String hold;
	String originalImgFileName;
	int result;
	EmpSalaryVo empSal;
	String natPension;
	String healInrance;
	int healRdc;
	String oldInrance;
	int oldRdc;
	String ementInrance;
	String earIncome;
	String bsnIncome;
	String dailyJob;
	String etcIncome;
	String earBsnIncome;
	String[] rship;
	String[] fyname;
	String[] fyitfornal;
	String[] DIBILITY;
	String[] hISC;
	String[] iTOGETHER;
	String[] mChild;
	ArrayList<DependentsVo> drr;
	String[] itforNal;
	String[] adDate;
	String[] grDate;
	String[] schName;
	String[] major;
	String[] taking;
	NursingHospitalVo loginHospital;

	if (request.getServletPath().equals("/empin")) {

	    isMulti = ServletFileUpload.isMultipartContent(request);
	    request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html; charset=UTF-8");
	    pw = response.getWriter();
	    if (isMulti != true) {
		pw.println("<script >");
		pw.println("alert('정상적인 발송방식이 아닙니다 확인하세요.')");
		pw.println("history.back()");
		pw.println("</script>");
		pw.flush();
		pw.close();
	    }

	    int maxSize = 1024 * 1024 * 10;

	    savePath = request.getSession().getServletContext().getRealPath("/resources/ERP/emp_Img_file");

	    mrequest = new MultipartRequest(request, savePath, maxSize, "utf-8", new DefaultFileRenamePolicy());

	    hostId = null;
	    hostPwd = null;
	    // 사원 기본정보 담기
	    emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
	    loginHospital = (NursingHospitalVo) request.getSession().getAttribute("loginHospital");
	    if (emp != null) {
		hostId = emp.getHostId();
		hostPwd = emp.getHostPwd();
	    } else {
		hostId = loginHospital.getNH_USERID();
		hostPwd = loginHospital.getNH_USERPWD();
	    }

	    emp.setHostId(hostId);
	    emp.setHostPwd(hostPwd);
	    emp.setEmpName(mrequest.getParameter("empname"));
	    emp.setEmpNo(mrequest.getParameter("empno1") + "-" + mrequest.getParameter("empno2"));
	    address1 = mrequest.getParameter("address1");
	    if (address1 != null)
		address1 += ",";
	    address2 = mrequest.getParameter("address2");
	    if (address2 != null)
		address2 += ",";
	    address3 = mrequest.getParameter("address3");
	    if (address3 != null)
		address3 += ",";
	    address4 = mrequest.getParameter("address4");
	    if (address4 != null)
		address4 += " ";

	    emp.setAddress(address1 + address2 + address3 + address4);
	    itfor = mrequest.getParameter("itfornal");
	    emp.setItnalFor(itfor);

	    emp.setAdTel(mrequest.getParameter("adtel1") + "-" + mrequest.getParameter("adtel2") + "-"
		    + mrequest.getParameter("adtel3"));
	    emp.setPhone(mrequest.getParameter("phone") + "-" + mrequest.getParameter("phone2") + "-"
		    + mrequest.getParameter("phone3"));
	    emp.setEmail(mrequest.getParameter("email"));
	    emp.setSalary(Integer.parseInt(mrequest.getParameter("salary")));
	    emp.setUserId(mrequest.getParameter("empids"));
	    emp.setUserpwd(mrequest.getParameter("emppwds"));
	    emp.setEmpEtc(mrequest.getParameter("etc"));
	    emp.setGender(mrequest.getParameter("gender"));

	    empment = mrequest.getParameter("empment");
	    if (empment.equals("0") != true) {
		emp.setEmpmentCode(empment);
	    } else {
		empment = null;
		emp.setEmpmentCode(empment);
	    }
	    dept = mrequest.getParameter("dept");
	    if (dept.equals("0") != true)
		emp.setDeptCode(dept);
	    else {
		dept = null;
		emp.setDeptCode(dept);
	    }
	    team = mrequest.getParameter("team");
	    if (team.equals("0") != true)
		emp.setTeamCode(team);
	    else {
		team = null;
		emp.setTeamCode(team);
	    }
	    job = mrequest.getParameter("job");
	    if (job.equals("0") != true)
		emp.setPosCode(job);
	    else {
		job = null;
		emp.setPosCode(job);
	    }
	    emp.setAuthorityCode(mrequest.getParameter("author"));
	    ward = mrequest.getParameter("ward");
	    if (ward.equals("0") != true)
		emp.setWardCode(ward);
	    else {
		ward = null;
		emp.setWardCode(ward);
	    }
	    hold = mrequest.getParameter("hold");
	    if (hold.equals("0") != true)
		emp.setHoldOffice(hold);
	    else {
		hold = null;
		emp.setHoldOffice(hold);
	    }
	    originalImgFileName = mrequest.getFilesystemName("upfiles");

	    if (originalImgFileName != null) {

		emp.setEmpImgOriginalFilename(originalImgFileName);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String reNameImgFileName = sdf.format(new java.sql.Date(System.currentTimeMillis())) + "."
			+ originalImgFileName.substring(originalImgFileName.lastIndexOf(".") + 1);

		File originalFile = new File(savePath + "\\" + originalImgFileName);
		File reNameFile = new File(savePath + "\\" + reNameImgFileName);

		if (!originalFile.renameTo(reNameFile)) {

		    int read = -1;
		    byte[] buf = new byte[1024];

		    FileInputStream in = new FileInputStream(originalFile);
		    FileOutputStream out = new FileOutputStream(reNameFile);

		    while ((read = in.read(buf, 0, buf.length)) != -1) {

			out.write(buf, 0, read);
			out.flush();
		    }

		    in.close();
		    out.close();
		    originalFile.delete();
		}
		emp.setEmpRenameFilename(reNameImgFileName);

	    }
	    log.info("insert하기전 employee 정보 =" + emp);
	    result = new EmployeeService().insertEmp(emp);

	    if (result == 0) {
		pw.println("<script >");
		pw.println("alert('사원정보 등록실패')");
		pw.println("history.back()");
		pw.println("</script>");
		pw.flush();
		pw.close();
	    }
	    log.info("사원정보 등록한 행=" + result);
	    log.info("주민번호로 사원 한명 조회해오기전 사원주민번호=" + emp.getEmpNo());
	    EmployeeVo emp2 = new EmployeeService().selectOne(emp.getEmpNo(), hostId, hostPwd);
	    log.info("insert한뒤 사원주민번호로 사원조회해온 객체=" + emp2);
	    // 사원 급여정보란
	    empSal = new EmpSalaryVo();

	    natPension = mrequest.getParameter("no1");
	    healInrance = mrequest.getParameter("no2");
	    healRdc = Integer.parseInt(mrequest.getParameter("no2up"));
	    int intHealRdc = 0;
	    if (String.valueOf(healRdc) != null) {
		empSal.setHealRdc(healRdc);
	    } else {
		empSal.setHealRdc(intHealRdc);
	    }
	    log.info(String.valueOf(healRdc));
	    oldInrance = mrequest.getParameter("no3");

	    String oldRdc2 = mrequest.getParameter("no3up");
	    oldRdc = 0;
	    if (oldRdc2 != null) {
		empSal.setOldRdc(Integer.parseInt(mrequest.getParameter("no3up")));
	    } else {
		empSal.setHealRdc(oldRdc);
	    }
	    ementInrance = mrequest.getParameter("no4");
	    earIncome = mrequest.getParameter("earner1");
	    int earInrance = 0;
	    String earInrance2 = mrequest.getParameter("earner1up");// 새액퍼센트
	    if (earInrance2 != null) {
		empSal.setEarInrance(Integer.parseInt(earInrance2));
	    } else {
		empSal.setEarInrance(earInrance);
	    }

	    bsnIncome = mrequest.getParameter("earner2");
	    dailyJob = mrequest.getParameter("earner3");
	    etcIncome = mrequest.getParameter("earner4");
	    earBsnIncome = mrequest.getParameter("earner5");

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
	    log.info("data= {}, secondData= {}", new Object[] { empSal, "로그 데이터 입니다." });

	    result = new EmpSalaryService().insertEmpSalary(empSal, hostId, hostPwd);

	    if (result == 0) {
		pw.println("<script >");
		pw.println("alert('기본정보 등록실패!')");
		pw.println("history.back()");
		pw.println("</script>");
		pw.flush();
		pw.close();
	    }
	    int result2 = 0;

	    rship = mrequest.getParameterValues("rship");
	    fyname = mrequest.getParameterValues("fyname");
	    fyitfornal = mrequest.getParameterValues("fyitfornal");
	    DIBILITY = mrequest.getParameterValues("DIBILITY");
	    hISC = mrequest.getParameterValues("H_ISC");
	    iTOGETHER = mrequest.getParameterValues("I_TOGETHER");
	    mChild = mrequest.getParameterValues("M_CHILD");

	    drr = new ArrayList<DependentsVo>();
	    if (rship != null) {
		for (int i = 0; i < rship.length; i++) {

		    String rrship = rship[i];
		    String rfyname = fyname[i];
		    String rfyitfornal = fyitfornal[i];
		    String rDIBILITY = DIBILITY[i];
		    String rhISC = hISC[i];
		    String riTOGETHER = iTOGETHER[i];
		    String rmChild = mChild[i];

		    drr.add(new DependentsVo(rrship, rfyname, rfyitfornal, rDIBILITY, rhISC, riTOGETHER, rmChild,
			    emp2.getEmpId()));
		}

		for (DependentsVo d : drr) {

		    result2 = new DependentsService().insertDependent(d, hostId, hostPwd);

		}
		log.info("data= {}, secondData= {}", new Object[] { result2, "로그 데이터 입니다." });
		if (result2 == 0) {
		    pw.println("<script >");
		    pw.println("alert('부양가족 등록실패.')");
		    pw.println("history.back()");
		    pw.println("</script>");
		    pw.flush();
		    pw.close();
		}
	    }

	    itforNal = mrequest.getParameterValues("shcool");
	    adDate = mrequest.getParameterValues("AD_DATE");
	    grDate = mrequest.getParameterValues("GR_DATE");
	    schName = mrequest.getParameterValues("SCH_NAME");
	    major = mrequest.getParameterValues("MAJOR");
	    taking = mrequest.getParameterValues("TAKING");
	    if (itforNal != null) {

		ArrayList<EducationVo> eduList = new ArrayList<EducationVo>();

		for (int i = 0; i < itforNal.length; i++) {

		    String itforNal1 = itforNal[i];
		    String adDate1 = adDate[i];
		    String grDate1 = grDate[i];
		    String schName1 = schName[i];
		    String major1 = major[i];
		    String taking1 = taking[i];

		    eduList.add(
			    new EducationVo(itforNal1, adDate1, grDate1, schName1, major1, taking1, emp2.getEmpId()));
		}

		int result3 = 0;
		for (EducationVo e : eduList) {

		    result3 = new EducationService().insertEdu(e, hostId, hostPwd);

		}
		log.info("data= {}, secondData= {}", new Object[] { eduList, "로그 데이터 입니다." });
		if (result3 == 0) {
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

	    if (comName != null) {
		ArrayList<CareerVo> carList = new ArrayList<CareerVo>();

		for (int i = 0; i < comName.length; i++) {

		    String comName1 = comName[i];
		    log.info(comName1);
		    String hireDate1 = hireDate[i];
		    log.info(hireDate1);
		    String lastDate1 = lastDate[i];
		    log.info(lastDate1);
		    String workTeam1 = workTeam[i];
		    log.info(workTeam1);
		    String lastPosition1 = lastPosition[i];
		    log.info(lastPosition1);
		    String resBilties1 = resBilties[i];
		    log.info(resBilties1);
		    String leaveReason1 = leaveReason[i];
		    log.info(leaveReason1);

		    carList.add(new CareerVo(emp2.getEmpId(), comName1, hireDate1, lastDate1, workTeam1, lastPosition1,
			    resBilties1, leaveReason1));
		}

		log.info("data= {}, secondData= {}", new Object[] { carList, "로그 데이터 입니다." });
		result = 0;
		for (CareerVo c : carList) {
		    result = new CareerService().inserCar(c, hostId, hostPwd);
		}

		if (result == carList.size()) {
		    if (emp != null) {
			response.sendRedirect("/NHMP/views/ERP/Employee.jsp");
		    }
		    response.sendRedirect("/NHMP/views/ERP/Admin_main.jsp");
		} else {
		    pw.println("<script >");
		    pw.println("alert('error')");
		    pw.println("history.back()");
		    pw.println("</script>");
		    pw.flush();
		    pw.close();
		}

		if (request.getServletPath().equals("/list")) {
		    // 메뉴바에서 전체사원 조회클릭시 전체사원리스트 처리용 컨트롤러

		    emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		    NursingHospitalVo loginHospital1 = (NursingHospitalVo) request.getSession()
			    .getAttribute("loginHospital");
		    hostId = null;
		    hostPwd = null;
		    if (emp != null) {

			hostId = emp.getHostId();
			hostPwd = emp.getHostPwd();
		    } else {
			hostId = loginHospital1.getNH_USERID();
			hostPwd = loginHospital1.getNH_USERPWD();
		    }
		    log.info(hostId + hostPwd);
		    // 댓글게시글 전체목록 조회 처리용 컨트롤러 : 페이징 처리됨
		    int currentPage = 1;
		    if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		    }

		    int limit = 10; // 한 페이지에 출력할 목록갯수
		    EmployeeService eservice = new EmployeeService();
		    log.info("전체사원조회 서블릿에서 받은 호스트아이디 비밀번호 :" + hostId + "," + hostPwd);
		    int listCount = eservice.getListCount(hostId, hostPwd);// 현재 테이블의 전체 목록 갯수 조회
		    log.info("data= {}, secondData= {}", new Object[] { listCount, "로그 데이터 입니다." });
		    // 총 페이지수 계산
		    int maxPage = listCount / limit;
		    if (listCount % limit > 0) {
			maxPage++;
		    }
		    // currentPage 가 속한 페이지그룹의 시작 페이지 숫자화 끝숫자 계산
		    // 예, 현재 34페이지면 31 ~ 40 이 됨. (페이지그룹의 수를 10개로 한 경우)
		    int beginPage = (currentPage / limit) * limit + 1;
		    int endPage = beginPage + 9;
		    if (endPage > maxPage) {
			endPage = maxPage;
		    }

		    int startRow = (currentPage * limit) - 9;
		    int endRow = currentPage * limit;

		    // 조회할 목록의 시작행과 끝행 번호 전달하고 결과받기
		    ArrayList<EmployeeVo> empList = eservice.selectList(startRow, endRow, hostId, hostPwd);
		    log.info("data= {}, secondData= {}", new Object[] { empList, "로그 데이터 입니다." });
		    RequestDispatcher view = null;
		    if (empList.size() > 0) {

			view = request.getRequestDispatcher("views/ERP/Employee/EmployeeListView.jsp");
			request.setAttribute("empList", empList);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("beginPage", beginPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
		    } else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter pw1 = response.getWriter();

			pw1.println("<script >");
			pw1.println("alert('사원이없습니다. 사원을 먼저 등록해 주세요^^')");
			pw1.println("location.href='/NHMP/views/ERP/Employee/InsertEmployee.jsp'");
			pw1.println("</script>");
			pw1.flush();
			pw1.close();
		    }
		    view.forward(request, response);
		}
	    }

	    if (request.getServletPath().equals("/edetail")) {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	    }

	    if (request.getServletPath().equals("/empup")) {
		isMulti = ServletFileUpload.isMultipartContent(request);
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		pw = response.getWriter();
		if (isMulti != true) {
		    pw.println("<script >");
		    pw.println("alert('정상적인 발송방식이 아닙니다 확인하세요.')");
		    pw.println("history.back()");
		    pw.println("</script>");
		    pw.flush();
		    pw.close();

		}

		int maxSize1 = 1024 * 1024 * 10;

		savePath = request.getSession().getServletContext().getRealPath("/resources/ERP/emp_Img_file");

		mrequest = new MultipartRequest(request, savePath, maxSize1, "utf-8", new DefaultFileRenamePolicy());

		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		NursingHospitalVo loginHospital1 = (NursingHospitalVo) request.getSession()
			.getAttribute("loginHospital");
		log.info("data= {}, secondData= {}", new Object[] { emp, "로그 데이터 입니다." });
		log.info("data= {}, secondData= {}", new Object[] { loginHospital1, "로그 데이터 입니다." });
		if (emp != null) {

		    hostId = emp.getHostId();
		    hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital1.getNH_USERID();
		    hostPwd = loginHospital1.getNH_USERPWD();
		}

		EmployeeVo Employee = new EmployeeVo();
		// 사원 기본정보 담기
		Employee.setHostId(hostId);
		Employee.setHostPwd(hostPwd);
		Employee.setEmpName(mrequest.getParameter("empname"));
		Employee.setEmpNo(mrequest.getParameter("empno1") + "-" + mrequest.getParameter("empno2"));
		address1 = mrequest.getParameter("address1");

		if (address1 == null)
		    address1 = ",";
		address2 = mrequest.getParameter("address2");

		if (address2 == null)
		    address2 = ",";
		address3 = mrequest.getParameter("address3");

		if (address3 == null)
		    address3 = ",";
		address4 = mrequest.getParameter("address4");

		if (address4 == null)
		    address4 = ",";

		Employee.setAddress(address1 + "," + address2 + "," + address3 + "," + address4);
		itfor = mrequest.getParameter("itfornal");
		Employee.setItnalFor(itfor);

		Employee.setAdTel(mrequest.getParameter("adtel1") + "-" + mrequest.getParameter("adtel2") + "-"
			+ mrequest.getParameter("adtel3"));
		Employee.setPhone(mrequest.getParameter("phone") + "-" + mrequest.getParameter("phone2") + "-"
			+ mrequest.getParameter("phone3"));
		Employee.setEmail(mrequest.getParameter("email"));
		Employee.setSalary(Integer.parseInt(mrequest.getParameter("salary")));
		Employee.setUserId(mrequest.getParameter("empids"));
		Employee.setUserpwd(mrequest.getParameter("emppwds"));
		Employee.setEmpEtc(mrequest.getParameter("etc"));
		Employee.setGender(mrequest.getParameter("gender"));

		empment = mrequest.getParameter("empment");
		if (empment.equals("0") != true) {
		    Employee.setEmpmentCode(empment);
		} else {
		    empment = "0";
		    Employee.setEmpmentCode(empment);
		}
		dept = mrequest.getParameter("dept");
		if (dept.equals("0") != true)
		    Employee.setDeptCode(dept);
		else {
		    dept = "0";
		    Employee.setDeptCode(dept);
		}
		team = mrequest.getParameter("team");
		if (team.equals("0") != true)
		    Employee.setTeamCode(team);
		else {
		    team = "0";
		    Employee.setTeamCode(team);
		}
		job = mrequest.getParameter("job");
		if (job.equals("job") != true)
		    Employee.setPosCode(job);
		else {
		    job = "0";
		    Employee.setPosCode(job);
		}
		Employee.setAuthorityCode(mrequest.getParameter("author"));
		ward = mrequest.getParameter("ward");
		if (ward.equals("0") != true)
		    Employee.setWardCode(ward);
		else {
		    ward = "0";
		    Employee.setWardCode(ward);
		}
		hold = mrequest.getParameter("hold");
		log.info(hold);
		if (hold.equals("0") != true)
		    Employee.setHoldOffice(hold);
		else {
		    hold = "0";
		    Employee.setHoldOffice(hold);
		}
		originalImgFileName = mrequest.getFilesystemName("upfiles");

		if (originalImgFileName != null) {

		    Employee.setEmpImgOriginalFilename(originalImgFileName);

		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String reNameImgFileName = sdf.format(new java.sql.Date(System.currentTimeMillis())) + "."
			    + originalImgFileName.substring(originalImgFileName.lastIndexOf(".") + 1);

		    File originalFile = new File(savePath + "\\" + originalImgFileName);
		    File reNameFile = new File(savePath + "\\" + reNameImgFileName);

		    if (!originalFile.renameTo(reNameFile)) {

			int read = -1;
			byte[] buf = new byte[1024];

			FileInputStream in = new FileInputStream(originalFile);
			FileOutputStream out = new FileOutputStream(reNameFile);

			while ((read = in.read(buf, 0, buf.length)) != -1) {

			    out.write(buf, 0, read);
			    out.flush();
			}

			in.close();
			out.close();
			originalFile.delete();
		    }
		    Employee.setEmpRenameFilename(reNameImgFileName);
		    new File(savePath + "\\" + mrequest.getParameter("rfile")).delete();

		} else {
		    Employee.setEmpImgOriginalFilename(mrequest.getParameter("ofile"));
		    Employee.setEmpRenameFilename(mrequest.getParameter("rfile"));
		}
		String empId = mrequest.getParameter("empid");
		Employee.setEmpId(empId);
		log.info("사원정보 업데이트 하기전 사원 객체" + Employee);
		result = new EmployeeService().updateEmployee(Employee);

		if (result == 0) {
		    pw.println("<script >");
		    pw.println("alert('사원기본 정보 업데이트 실패.')");
		    pw.println("history.back()");
		    pw.println("</script>");
		    pw.flush();
		    pw.close();

		}

		// 사원 급여정보란
		empSal = new EmpSalaryVo();

		natPension = mrequest.getParameter("no1");
		healInrance = mrequest.getParameter("no2");
		healRdc = Integer.parseInt(mrequest.getParameter("no2up"));
		oldInrance = mrequest.getParameter("no3");
		oldRdc = Integer.parseInt(mrequest.getParameter("no3up"));
		ementInrance = mrequest.getParameter("no4");
		earIncome = mrequest.getParameter("earner1");
		earInrance = Integer.parseInt(mrequest.getParameter("earner1up"));// 새액퍼센트
		bsnIncome = mrequest.getParameter("earner2");
		dailyJob = mrequest.getParameter("earner3");
		etcIncome = mrequest.getParameter("earner4");
		earBsnIncome = mrequest.getParameter("earner5");

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
		log.info("data= {}, secondData= {}", new Object[] { empSal, "로그 데이터 입니다." });

		result = new EmpSalaryService().updateEmpSalary(empSal, hostId, hostPwd);
		int inserResult = 0;
		if (result == 0) {
		    inserResult = new EmpSalaryService().insertEmpSalary(empSal, hostId, hostPwd);
		    if (inserResult == 0) {
			pw.println("<script >");
			pw.println("alert('급여정보 업데이트  실패!')");
			pw.println("history.back()");
			pw.println("</script>");
			pw.flush();
			pw.close();
		    } else {

		    }
		}

		rship = mrequest.getParameterValues("rship");
		fyname = mrequest.getParameterValues("fyname");
		fyitfornal = mrequest.getParameterValues("fyitfornal");
		log.info("data= {}, secondData= {}", new Object[] { fyitfornal, "로그 데이터 입니다." });
		DIBILITY = mrequest.getParameterValues("DIBILITY");
		hISC = mrequest.getParameterValues("H_ISC");
		iTOGETHER = mrequest.getParameterValues("I_TOGETHER");
		mChild = mrequest.getParameterValues("M_CHILD");
		if (rship != null && fyname != null && fyitfornal != null && DIBILITY != null && hISC != null
			&& iTOGETHER != null && mChild != null) {
		    drr = new ArrayList<DependentsVo>();

		    for (int i = 0; i < rship.length; i++) {

			String rrship = rship[i];
			String rfyname = fyname[i];
			String rfyitfornal = fyitfornal[i];
			String rDIBILITY = DIBILITY[i];
			String rhISC = hISC[i];
			String riTOGETHER = iTOGETHER[i];
			String rmChild = mChild[i];

			drr.add(new DependentsVo(rrship, rfyname, rfyitfornal, rDIBILITY, rhISC, riTOGETHER, rmChild,
				empId));
		    }
		    log.info("data= {}, secondData= {}", new Object[] { drr, "로그 데이터 입니다." });
		    result = 0;
		    String[] fyNo = new String[drr.size()];
		    String fyno1 = null;
		    fyNo = new DependentsService().selectDepenCode(empId, fyNo.length, hostId, hostPwd);

		    int i = 0;
		    for (DependentsVo d : drr) {
			fyno1 = fyNo[i];
			result = new DependentsService().updateDependent(d, fyno1, hostId, hostPwd);
			i++;

		    }
		    log.info("data= {}, secondData= {}", new Object[] { result, "로그 데이터 입니다." });
		    if (result2 == 0) {
			pw.println("<script >");
			pw.println("alert('부양가족수정실패!')");
			pw.println("history.back()");
			pw.println("</script>");
			pw.flush();
			pw.close();
		    }

		}

		itforNal = mrequest.getParameterValues("shcool");
		for (int i = 0; i < itforNal.length; i++) {
		    if (itforNal[i].equals("0") == true) {
			itforNal = null;
		    }
		}
		adDate = mrequest.getParameterValues("AD_DATE");
		grDate = mrequest.getParameterValues("GR_DATE");
		schName = mrequest.getParameterValues("SCH_NAME");
		major = mrequest.getParameterValues("MAJOR");
		taking = mrequest.getParameterValues("TAKING");

		if (adDate != null && grDate != null && schName != null && major != null && taking != null) {
		    ArrayList<EducationVo> eduList = new ArrayList<EducationVo>();

		    for (int i = 0; i < itforNal.length; i++) {

			String itforNal1 = itforNal[i];
			String adDate1 = adDate[i];
			String grDate1 = grDate[i];
			String schName1 = schName[i];
			String major1 = major[i];
			String taking1 = taking[i];

			eduList.add(new EducationVo(itforNal1, adDate1, grDate1, schName1, major1, taking1, empId));
		    }
		    String[] eduCode = new String[eduList.size()];
		    String eduCode1 = null;
		    eduCode = new EducationService().selectEduCode(empId, eduCode.length, hostId, hostPwd);

		    int b = 0;
		    int result3 = 0;
		    for (EducationVo e : eduList) {
			eduCode1 = eduCode[b];
			result3 = new EducationService().updateEdu(e, eduCode1, hostId, hostPwd);
			b++;
		    }

		    if (result3 == 0) {
			pw.println("<script >");
			pw.println("alert('학력정보등록실패')");
			pw.println("history.back()");
			pw.println("</script>");
			pw.flush();
			pw.close();
		    }
		}

		comName = mrequest.getParameterValues("COM_NAME");
		hireDate = mrequest.getParameterValues("HIRE_DATE");
		lastDate = mrequest.getParameterValues("LAST_DATE");
		workTeam = mrequest.getParameterValues("WORK_TEAM");
		lastPosition = mrequest.getParameterValues("LAST_POSITION");
		resBilties = mrequest.getParameterValues("RES_BILTIES");
		leaveReason = mrequest.getParameterValues("LEAVE_REASON");

		if (comName != null && hireDate != null && lastDate != null && workTeam != null && lastPosition != null
			&& resBilties != null && leaveReason != null) {
		    ArrayList<CareerVo> carList = new ArrayList<CareerVo>();

		    for (int i = 0; i < comName.length; i++) {

			String comName1 = comName[i];
			log.info(comName1);
			String hireDate1 = hireDate[i];
			log.info(hireDate1);
			String lastDate1 = lastDate[i];
			log.info(lastDate1);
			String workTeam1 = workTeam[i];
			log.info(workTeam1);
			String lastPosition1 = lastPosition[i];
			log.info(lastPosition1);
			String resBilties1 = resBilties[i];
			log.info(resBilties1);
			String leaveReason1 = leaveReason[i];
			log.info(leaveReason1);

			carList.add(new CareerVo(empId, comName1, hireDate1, lastDate1, workTeam1, lastPosition1,
				resBilties1, leaveReason1));
		    }
		    log.info("data= {}, secondData= {}", new Object[] { drr, "로그 데이터 입니다." });
		    String[] car = new String[carList.size()];

		    car = new CareerService().selectCarCode(empId, car.length, hostId, hostPwd);

		    for (CareerVo c : carList) {
			result = new CareerService().updateCar(c, hostId, hostPwd);

		    }
		    if (result == carList.size()) {
			response.sendRedirect("/NHMP/list");

		    } else {
			pw.println("<script >");
			pw.println("alert('error')");
			pw.println("history.back()");
			pw.println("</script>");
			pw.flush();
			pw.close();
		    }
		}
	    }

	    if (request.getServletPath().equals("/mdel")) {
		String[] empNo = request.getParameterValues("empno");
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo) request.getSession().getAttribute("loginHospital");
		System.out.println(emp);
		System.out.println(loginHospital);
		if (emp != null) {

		    hostId = emp.getHostId();
		    hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}

		EmployeeService es = new EmployeeService();
		result = 0;
		for (String id : empNo) {

		    result = es.deleteEmployee(id, hostId, hostPwd);

		}
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		pw = response.getWriter();

		if (result > 0) {
		    pw.println("<script>");
		    pw.println("alert('정상적으로 삭제 되었습니다.')");
		    pw.println("location.href='/NHMP/list'");
		    pw.println("</script>");
		} else {
		    pw.println("<script >");
		    pw.println("alert('삭제가 되지 않았습니다 다시 확인하세요.')");
		    pw.println("history.back()");
		    pw.println("</script>");
		}
	    }

	    if (request.getServletPath().equals("/esel")) {
		request.setCharacterEncoding("utf-8");
		int currentPage = 1;
		if (request.getParameter("page") != null) {

		    currentPage = Integer.parseInt(request.getParameter("page"));
		}

		String empName = request.getParameter("empname");
		String deptName = request.getParameter("dept");
		hostId = null;
		hostPwd = null;
		EmployeeVo emp1 = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo) request.getSession()
			.getAttribute("loginHospital");
		System.out.println(emp1);
		System.out.println(loginHospital);
		if (emp1 != null) {

		    hostId = emp1.getHostId();
		    hostPwd = emp1.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}
		System.out.println(empName + "," + deptName);
		ArrayList<EmployeeVo> empList = null;

		// 나이구분 도 같이 날아와서 처리함.
		if (deptName.equals("--부서구분--") == true) {
		    empList = new EmployeeService().selectName(empName, hostId, hostPwd);

		} else {

		    empList = new EmployeeService().selectEMPOne(empName, deptName, hostId, hostPwd);
		}

		System.out.println(empList);
		if (empList == null) {
		    response.setContentType("text/html; charset=UTF-8");
		    pw = response.getWriter();

		    pw.println("<script >");
		    pw.println("alert('해당 사원이 없습니다.')");
		    pw.println("location.href='/NHMP/list'");
		    pw.println("</script>");
		    pw.flush();
		    pw.close();
		}

		int limit = 10;
		int listCount = empList.size();
		int maxPage = listCount / limit;

		if (listCount % limit > 0) {
		    maxPage++;
		}
		int beginPage = (currentPage / limit) * limit + 1;
		int endPage = beginPage + 9;
		if (endPage > maxPage) {
		    endPage = maxPage;
		}

		RequestDispatcher view = null;
		if (empList.size() > 0 && empList != null) {

		    view = request.getRequestDispatcher("views/ERP/Employee/EmployeeListView.jsp");
		    request.setAttribute("empList", empList);
		    request.setAttribute("maxPage", maxPage);
		    request.setAttribute("currentPage", currentPage);
		    request.setAttribute("beginPage", beginPage);
		    request.setAttribute("endPage", endPage);

		} else {
		    response.setContentType("text/html; charset=UTF-8");
		    pw = response.getWriter();

		    pw.println("<script >");
		    pw.println("alert('해당 정보가 없습니다.')");
		    pw.println("location.href='/NHMP/list'");
		    pw.println("</script>");
		    pw.flush();
		    pw.close();

		}
		view.forward(request, response);
	    }

	    if (request.getServletPath().equals("/logins")) {
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		hostId = request.getParameter("hostid");
		hostPwd = request.getParameter("hostpwd");

		emp = new EmployeeService().loginCheck(userId, userPwd, hostId, hostPwd);
		System.out.println("mem" + emp);
		emp.setHostId(hostId);
		emp.setHostPwd(hostPwd);
		DepartmentVo dp = new DepartmentService().selectAuDeptName(hostId, hostPwd, emp.getDeptCode());
		String tm = new TeamService().selectTeamName(emp.getTeamCode(), hostId, hostPwd);
		WardVo wd = new WardService().selectAuWardName(hostId, hostPwd, emp.getWardCode());

		/*
		 * ArrayList<Notice> noList = new NoticeService().selectList();
		 * System.out.println("noList" + noList);
		 */
		RequestDispatcher view = null;
		System.out.println(dp.getDeptName());

		if (emp.equals(null)) {
		    String empname = emp.getEmpName();
		    ArrayList<CalendarVo> list = new CalendarService().EmployeeSelectList(empname, hostId, hostPwd); // 캘린더 수정 처리

		    HttpSession session = request.getSession();
		    session.setAttribute("list", list);
		    session.setAttribute("loginEmployee", emp);
		    session.setAttribute("dp", dp);
		    session.setAttribute("tm", tm);
		    session.setAttribute("wd", wd);
		    /* session.setAttribute("noList", noList); */
		    response.sendRedirect("/NHMP/views/ERP/Employee.jsp");
		} else {
		    view = request.getRequestDispatcher("views/common/error.jsp");
		    request.setAttribute("message", "로그인정보 불일치");
		    view.forward(request, response);
		}
	    }

	    if (request.getServletPath().equals("/emailChk")) {
		String email = request.getParameter("email");
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		if(emp != null) {
		
		hostId = emp.getHostId();
		hostPwd = emp.getHostPwd();
		}else {
			hostId = loginHospital.getNH_USERID();
			hostPwd = loginHospital.getNH_USERPWD();
		}
		
		System.out.println(email);
		int count = new EmployeeService().selectCheckEmail(email,hostId, hostPwd);
		System.out.println(count);
		String returnValue = null;
		
		
		if(count > 0) {
		    returnValue = "ok";
		} else {
		    returnValue = "dup";
		}
		response.setContentType("text/html; charset=utf-8");
		pw = response.getWriter();
		
		pw.println(returnValue);
		pw.flush();
		pw.close();
	    }

	    if (request.getServletPath().equals("/idchk")) {
		String userId = request.getParameter("userid");
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		if(emp != null) {
		
		hostId = emp.getHostId();
		hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}
		
		int count = new EmployeeService().selectCheckId(userId,hostId, hostPwd);
		
		String returnValue = null;
		
		if(count > 0) {
		    returnValue = "ok";
		} else {
		    returnValue = "dup";
		}
		response.setContentType("text/html; charset=utf-8");
		pw = response.getWriter();
		
		pw.println(returnValue);
		pw.flush();
		pw.close();
	    }

	    if (request.getServletPath().equals("/logouts")) {
		//로그아웃 처리용 컨트롤러 : 해당 클라이언트의 세션 객체를 없앰
		HttpSession session = request.getSession(false);
		if(session != null) {
		    //세션 객체가 존재한다면 없애라.
		    session.invalidate();
		    response.sendRedirect("/NHMP/index.jsp");
		}
	    }

	    if (request.getServletPath().equals("/namechk")) {
		String empName = request.getParameter("empname");
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo) request.getSession().getAttribute("loginHospital");
		if (emp != null) {
		    hostId = emp.getHostId();
		    hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}

		System.out.println(empName);
		int count = new EmployeeService().selectCheckEmpName(empName, hostId, hostPwd);
		System.out.println(count);
		String returnValue = null;

		if (count > 0) {
		    returnValue = "ok";
		} else {
		    returnValue = "dup";
		}
		response.setContentType("text/html; charset=utf-8");
		pw = response.getWriter();

		pw.println(returnValue);
		pw.flush();
		pw.close();
	    }

	    if (request.getServletPath().equals("/newpwd")) {
		String empNo = request.getParameter("empno");
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo) request.getSession()
			.getAttribute("loginHospital");

		if (emp != null) {
		    hostId = emp.getHostId();
		    hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}

		emp = new EmployeeService().selectOne(empNo, hostId, hostPwd);

		String deptName = new DepartmentService().selectDeptName(emp.getDeptCode(), hostId, hostPwd);
		String posName = new PositionService().selectPositionName(emp.getPosCode(), hostId, hostPwd);

		RequestDispatcher view = null;

		if (emp != null && deptName != null && posName != null) {
		    view = request.getRequestDispatcher("views/ERP/Employee/newPwd.jsp");
		    request.setAttribute("emp", emp);
		    request.setAttribute("deptName", deptName);
		    request.setAttribute("posName", posName);

		} else {
		    view = request.getRequestDispatcher("views/common/Error.jsp");
		    request.setAttribute("message", "실패!");
		}
		view.forward(request, response);
	    }

	    if (request.getServletPath().equals("/uppwd")) {
		String newPwd = request.getParameter("newpwd");
		String empId = request.getParameter("empid");
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo) request.getSession()
			.getAttribute("loginHospital");

		if (emp != null) {

		    hostId = emp.getHostId();
		    hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}
		result = new EmployeeService().empNewPwdUpdate(newPwd, empId, hostId, hostPwd);
		response.setContentType("text/html; charset=UTF-8");
		pw = response.getWriter();
		System.out.println("비밀번호 수정 완료값 =" + result);
		if (result > 0) {
		    pw.println("<script>");
		    pw.println("alert('정상적으로 변경 되었습니다.')");
		    pw.println("window.close()");
		    pw.println("</script>");
		    pw.flush();
		    pw.close();
		} else {
		    RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
		    request.setAttribute("message", "실패!");
		    view.forward(request, response);
		}
	    }

	    if (request.getServletPath().equals("/organlist")) {
		// 조직도에서 부서 클릭했을시 부서정보 출력시키는 컨트롤러
		request.setCharacterEncoding("utf-8");
		String deptName = request.getParameter("deptName");
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo) request.getSession().getAttribute("loginHospital");
		if (emp != null) {
		    hostId = emp.getHostId();
		    hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}

		if (deptName.length() < 13) {

		    ArrayList<TeamVo> team1 = new TeamService().selectOrganTeamName(deptName, hostId, hostPwd);

		    DepartmentVo dp = new DepartmentService().selectDeptCode(deptName, hostId, hostPwd);
		    int empcount = 0;

		    for (TeamVo t : team1) {
			empcount += new EmployeeService().teamEmpcount(t.getTeamCode(), hostId, hostPwd);
		    }
		    System.out.println(empcount);

		    JSONObject sendJson = new JSONObject();

		    JSONArray jarr = new JSONArray();
		    if (team != null) {

			for (TeamVo t : team1) {
			    JSONObject tn = new JSONObject();

			    tn.put("teamname", URLEncoder.encode(t.getTeamName(), "utf-8"));
			    tn.put("deptcode", dp.getDeptCode());
			    tn.put("count", empcount);
			    jarr.add(tn);
			}
			sendJson.put("list", jarr);

			response.setContentType("application/json");

			PrintWriter out = response.getWriter();

			out.print(sendJson.toJSONString());
			out.flush();
			out.close();
		    }
		} else {
		    String renameDeptName = deptName.substring(0, 10);
		    String re2 = renameDeptName.trim();

		    ArrayList<TeamVo> team1 = new TeamService().selectOrganTeamName(re2, hostId, hostPwd);

		    DepartmentVo dp = new DepartmentService().selectDeptCode(re2, hostId, hostPwd);
		    int empcount = 0;

		    for (TeamVo t : team1) {
			empcount += new EmployeeService().teamEmpcount(t.getTeamCode(), hostId, hostPwd);
		    }
		    System.out.println(empcount);

		    JSONObject sendJson = new JSONObject();

		    JSONArray jarr = new JSONArray();
		    if (team != null) {
			JSONObject tn = new JSONObject();

			tn.put("deptcode", dp.getDeptCode());
			tn.put("count", empcount);
			jarr.add(tn);

			sendJson.put("list", jarr);

			response.setContentType("application/json");

			PrintWriter out = response.getWriter();

			out.print(sendJson.toJSONString());
			out.flush();
			out.close();
		    }
		}
	    }

	    if (request.getServletPath().equals("/ochart")) {
		// 메인에서 조직도로 넘어갈때 객체정보 담아서 넘기는 컨트롤러
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo) request.getSession().getAttribute("loginHospital");
		System.out.println(emp);
		System.out.println(loginHospital);
		if (emp != null) {
		    hostId = emp.getHostId();
		    hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}
		ArrayList<EmployeeVo> mList = new EmployeeService().selectEmployeeList(hostId, hostPwd);
		ArrayList<DepartmentVo> dList = new DepartmentService().selectAll(hostId, hostPwd);
		/*
		 * ArrayList<Team> tList = new TeamService().selectAll(); ArrayList<Ward> wList
		 * = new WardService().selectAll(); ArrayList<Position> pList = new
		 * PositionService().selectAll();
		 */

		RequestDispatcher view = null;
		if (mList != null && dList != null /* && tList != null && wList != null && pList != null */) {
		    view = request.getRequestDispatcher("views/ERP/Employee/EmployeeOrganizationChart.jsp");
		    request.setAttribute("mList", mList);
		    request.setAttribute("dList", dList);
		    /*
		     * request.setAttribute("tList", tList); request.setAttribute("wList", wList);
		     * request.setAttribute("pList", pList);
		     */
		    view.forward(request, response);
		} else {
		    view = request.getRequestDispatcher("views/common/Error.jsp");
		    request.setAttribute("message", "에러");
		    view.forward(request, response);
		}
	    }

	    if (request.getServletPath().equals("/minfo")) {
		String empId = request.getParameter("empid");
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo) request.getSession().getAttribute("loginHospital");

		if (emp != null) {
		    hostId = emp.getHostId();
		    hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}
		emp = new EmployeeService().selectEmpId(empId, hostId, hostPwd);

		empSal = new EmpSalaryService().selectOne(empId, hostId, hostPwd);
		System.out.println("내정보보기 눌렀을때 가져온 사원 급여정보 : " + empSal);
		ArrayList<DependentsVo> dpenList = new DependentsService().selectOne(empId, hostId, hostPwd);
		System.out.println("내정보보기 를 눌렀을때 가져온 부양가족 정보:" + dpenList);
		if (dpenList.size() == 0)
		    dpenList = null;

		ArrayList<CareerVo> carList = new CareerService().selectList(empId, hostId, hostPwd);
		System.out.println("내정보보기 눌었을때 가져온 사원 경력사항 정보:" + carList);
		if (carList.size() == 0)
		    carList = null;

		ArrayList<EducationVo> eduList = new EducationService().selectList(empId, hostId, hostPwd);
		if (eduList.size() == 0)
		    eduList = null;
		System.out.println("내정보보기 눌렀을때 가져온 사원 학력정보  : " + eduList);

		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		pw = response.getWriter();
		if (emp != null) {
		    RequestDispatcher view = request.getRequestDispatcher("views/ERP/Employee/updateEmployee.jsp");
		    request.setAttribute("emp", emp);
		    request.setAttribute("empSal", empSal);
		    request.setAttribute("dpenList", dpenList);
		    request.setAttribute("carList", carList);
		    request.setAttribute("eduList", eduList);
		    view.forward(request, response);

		} else {
		    pw.println("<script>");
		    pw.println("alert('해당사원정보가 없습니다.')");
		    pw.println("location.href='/NHMP/list'");
		    pw.println("</script>");
		}
	    }

	    if (request.getServletPath().equals("/teamlist")) {
		// 조직도에서 팀이름 클릭했을때 사원정보 출력처리하는 컨트롤러
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo) request.getSession()
			.getAttribute("loginHospital");

		if (emp != null) {
		    hostId = emp.getHostId();
		    hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}

		request.setCharacterEncoding("utf-8");
		String teamName = request.getParameter("teamName");

		if (teamName.length() < 7) {

		    ArrayList<EmployeeVo> empList = new EmployeeService().selectOrganEmpList(hostId, hostPwd, teamName);
		    TeamVo tcode = new TeamService().selectTeamCode(hostId, hostPwd, teamName);
		    System.out.println("조직도 팀코드=" + tcode);
		    int tCount = new EmployeeService().teamEmpcount(tcode.getTeamCode(), hostId, hostPwd);

		    JSONObject sendJson = new JSONObject();

		    JSONArray jarr = new JSONArray();
		    if (empList != null && tCount > 0) {

			for (EmployeeVo e : empList) {
			    JSONObject tn = new JSONObject();

			    tn.put("empname", URLEncoder.encode(e.getEmpName(), "utf-8"));
			    tn.put("tcode", tcode.getTeamCode());
			    tn.put("count", tCount);
			    jarr.add(tn);
			}
			sendJson.put("list", jarr);

			response.setContentType("application/json");

			PrintWriter out = response.getWriter();

			out.print(sendJson.toJSONString());
			out.flush();
			out.close();
		    }

		} else {

		    String renameDeptName = teamName.substring(0, 6);
		    String re2 = renameDeptName.trim();

		    ArrayList<EmployeeVo> empList = new EmployeeService().selectOrganEmpList(hostId, hostPwd, re2);

		    TeamVo tcode = new TeamService().selectTeamCode(hostId, hostPwd, re2);
		    if (empList != null && tcode != null) {
			int tCount = new EmployeeService().teamEmpcount(tcode.getTeamCode(), hostId, hostPwd);

			JSONObject sendJson = new JSONObject();

			JSONArray jarr = new JSONArray();
			if (empList != null && tCount > 0) {
			    JSONObject tn = new JSONObject();

			    tn.put("tcode", tcode.getTeamCode());
			    tn.put("count", tCount);
			    jarr.add(tn);

			    sendJson.put("list", jarr);

			    response.setContentType("application/json");

			    PrintWriter out = response.getWriter();

			    out.print(sendJson.toJSONString());
			    out.flush();
			    out.close();
			} // 108
		    } // 100
		} // 94

	    }

	    if (request.getServletPath().equals("/emp")) {
		// 조직도에서 사원정보 클릭해서 받았을때 기본정보밑에 사원정보 출력 처리하는 컨트롤러
		request.setCharacterEncoding("utf-8");
		hostId = null;
		hostPwd = null;
		emp = (EmployeeVo) request.getSession().getAttribute("loginEmployee");
		loginHospital = (NursingHospitalVo) request.getSession().getAttribute("loginHospital");

		if (emp != null) {
		    hostId = emp.getHostId();
		    hostPwd = emp.getHostPwd();
		} else {
		    hostId = loginHospital.getNH_USERID();
		    hostPwd = loginHospital.getNH_USERPWD();
		}

		String empName = request.getParameter("empName");
		System.out.println("empName 값 받음 = " + empName);

		EmployeeVo empOne = new EmployeeService().selectOrganEmpOne(hostId, hostPwd, empName);

		JSONObject sendJson = new JSONObject();

		JSONArray jarr = new JSONArray();
		if (empOne != null) {

		   JSONObject job1 = new JSONObject();

		    job1.put("empimg", empOne.getEmpRenameFilename());
		    job1.put("empname", URLEncoder.encode(empOne.getEmpName(), "utf-8"));
		    job1.put("itfor", URLEncoder.encode(empOne.getItnalFor(), "utf-8"));
		    job1.put("phone", empOne.getPhone());
		    job1.put("address", URLEncoder.encode(empOne.getAddress(), "utf-8"));
		    job1.put("hiredate", empOne.getHireDate().toString());
		    job1.put("email", empOne.getEmail());
		    jarr.add(job1);

		    sendJson.put("list", jarr);

		    response.setContentType("text/html; charset=utf-8");
		    pw = response.getWriter();

		    pw.print(sendJson.toJSONString());
		    pw.flush();
		    pw.close();
		}

	    }
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}
