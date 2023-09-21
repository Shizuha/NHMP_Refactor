package ERP.Employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class EmployeeListServlet
 */
@WebServlet("/list")
public class EmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//메뉴바에서 전체사원 조회클릭시 전체사원리스트 처리용 컨트롤러
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
		System.out.println(hostId + hostPwd);
		// 댓글게시글 전체목록 조회 처리용 컨트롤러 : 페이징 처리됨
		int currentPage = 1;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int limit = 10; // 한 페이지에 출력할 목록갯수
		EmployeeService eservice = new EmployeeService();
		System.out.println("전체사원조회 서블릿에서 받은 호스트아이디 비밀번호 :" + hostId + "," + hostPwd);
		int listCount = eservice.getListCount(hostId, hostPwd);// 현재 테이블의 전체 목록 갯수 조회
		 System.out.println(listCount);
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
		ArrayList<Employee> empList = eservice.selectList(startRow, endRow, hostId, hostPwd);
		System.out.println(empList);
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
			PrintWriter pw = response.getWriter();
			
				pw.println("<script >");
				pw.println("alert('사원이없습니다. 사원을 먼저 등록해 주세요^^')");
				pw.println("location.href='/NHMP/views/ERP/Employee/InsertEmployee.jsp'");
				pw.println("</script>");
				pw.flush();
				pw.close();
				
			

		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
