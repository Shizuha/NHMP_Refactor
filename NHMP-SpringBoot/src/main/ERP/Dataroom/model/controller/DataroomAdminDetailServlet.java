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
import Main.NursingHospital.model.ov.*;

/**
 * Servlet implementation class DataroomAdminDetailServlet
 */
@WebServlet("/drdetail.ad")
public class DataroomAdminDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataroomAdminDetailServlet() {
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
			System.out.println(dataroomNo);
			String currentPage = (String)request.getParameter("page");
			System.out.println("noticeNo 값 옴 :" + dataroomNo);
			System.out.println("currentPage 값 옴 : " + currentPage);
			
			//로그인한 id pwd 가져오기(로그인 정보로 구분짓기 위하여)
			
			//코드 & 코드 해석
			NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
			//널싱하스피럴로 형변환후 리퀘스트로요청 세션에 저장하여 getAttribute에서로그인호스팅값 구함
			
			
			//조회수1증가 하여 서비스보내기
			DataroomService dataroomservice = new DataroomService();
			dataroomservice.updateReadCount(dataroomNo, loginHospital); //조회수 1 증가 처리함
			
			
			//상세보기 dataroom출력정보 서비스로 보내기
			Dataroom dataroom = new DataroomService().selectOne(dataroomNo,loginHospital);
			
			
			RequestDispatcher view = null;
			if (dataroom != null) {
				view = request.getRequestDispatcher("views/ERP/Dataroom/ErpAdminDataroomDetailView.jsp");
				request.setAttribute("dataroom", dataroom);
				request.setAttribute("currentPage", currentPage);
				
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", dataroomNo + "번 관리자 자료실 상세보기 실패!");
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

















