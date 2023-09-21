package ERP.Authority.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Employee.model.service.EmployeeService;
import ERP.Employee.model.vo.Employee;
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class AuthorityDeleteEmployeeServlet
 */
@WebServlet("/auEmpDel")
public class AuthorityDeleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorityDeleteEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//권한삭제시 사원권한 G1으로 변경처리하는 컨트롤러
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
			String[] empIds = request.getParameterValues("empDel");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			String au = "G1";
			int result = 0;
			
				for(String empId : empIds) {
					result += new EmployeeService().DeleteAuEmployee(hostId, hostPwd, empId, au);
				}
				System.out.println("날아온 아이디 개수=" + empIds.length);
				System.out.println("결과 행 :" + result);
			if(result == empIds.length) {
				pw.append("삭제완료");
				pw.flush();
				pw.close();
			}else {
				pw.append("삭제실패");
				pw.flush();
				pw.close();
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
