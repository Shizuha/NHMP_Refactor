package ERP.Dataroom.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Dataroom.model.vo.Dataroom;
import ERP.Dataroom.model.service.DataroomService;
import ERP.Employee.model.vo.Employee;

/**
 * Servlet implementation class DataroomDetailServlet
 */
@WebServlet("/drdetail")
public class DataroomDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataroomDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//자료실글 상세보기 처리용 컨트롤러
				//1. 인코딩처리 필요없음
					String dataroomNo = (String)request.getParameter("no");
					
					//Employee  의 로그인정보(loginEmployee) 받아오기
					Employee loginEmployee = (Employee)request.getSession().getAttribute("loginEmployee");
					
					
					//1증가된 조회수 셀렉트
					DataroomService dataroomservice = new DataroomService();
					dataroomservice.updateReadCount(dataroomNo, loginEmployee);	//조회수 1증가 처리를 위한 서비스단 보내기
					
					Dataroom dataroom = new DataroomService().selectOne(dataroomNo, loginEmployee);
					
					RequestDispatcher view = null;
					
					if (dataroom != null) {
						view = request.getRequestDispatcher("views/ERP/Dataroom/ErpDataroomDetailView.jsp");
						request.setAttribute("dataroom", dataroom);
						request.setAttribute("dataroomno", dataroomNo);
						
					}else {
						view = request.getRequestDispatcher("views/common/Error.jsp");
						request.setAttribute("message", dataroomNo + "번 자료실 상세보기 실패!");
						
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



















