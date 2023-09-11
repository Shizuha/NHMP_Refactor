package ERP.Dataroom.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.Dataroom.model.vo.Dataroom;
import Main.vo.NursingHospitalVo;
import ERP.Dataroom.model.service.DataroomService;


/**
 * Servlet implementation class DataroomAdminDeleteServlet
 */
@WebServlet("/drdel.ad")
public class DataroomAdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataroomAdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 전용 자료실글 삭제 처리용 컨트롤러
		//1.인코딩 utf-8처리
		request.setCharacterEncoding("utf-8");
		
		//nursinghospital 의 로그인정보 받아오기(공지사항구분을 위해)
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		
		//2. 자료실 번호 형변환후 가져오기
		String dataroomNo = (String)request.getParameter("no");
		
		//3.모델의 서비스단과 연곃하고 전달받고, 결과받기
		int result = new DataroomService().deleteDataroom(dataroomNo, loginHospital);
		
		//4.삭제 처리시 조건절
		if (result > 0) {
			response.sendRedirect("/NHMP/drlist.ad");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", dataroomNo + "번 자료실글 삭제 실패!");
			view.forward(request, response);
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
