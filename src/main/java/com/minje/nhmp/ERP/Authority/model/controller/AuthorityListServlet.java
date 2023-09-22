package ERP.Authority.model.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Authority.model.service.AuthorityService;
import ERP.Authority.model.vo.Authority;
import ERP.Employee.model.vo.Employee;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class AuthorityListServlet
 */
@WebServlet("/authlist")
public class AuthorityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorityListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//권한부여페이지 클릭시 권한정보 담아가는 컨트롤러
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
		ArrayList<AuthorityVo> auList = new AuthorityService().selectAll(hostId, hostPwd);
		
		RequestDispatcher view = null;
		System.out.println("권한서블릿 조회해온값 :" + auList);
		if(auList.size() > 0) {
			view = request.getRequestDispatcher("views/ERP/Authority/AuthorityList.jsp");
			request.setAttribute("auList", auList);
		}else {
			view = request.getRequestDispatcher("views/ERP/common/Error.jsp");
			request.setAttribute("message", "실패");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
