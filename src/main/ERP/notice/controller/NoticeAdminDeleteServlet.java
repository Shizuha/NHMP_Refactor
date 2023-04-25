package ERP.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.notice.model.service.NoticeService;
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class NoticeAdminDeleteServlet
 */
@WebServlet("/ndel.ad")
public class NoticeAdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자용 공지글 삭제 처리용 컨트롤러
				//1.인코딩 utf-8처리
				request.setCharacterEncoding("utf-8");
				
				//nursinghospital 의 로그인정보 받아오기(공지사항구분을 위해)
				NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
				
				
				//2.
				String noticeNo = (String)request.getParameter("no");
				
				//3.모델의 서비스단과 연곃하고 전달받고, 결과받기
				int result = new NoticeService().deleteNotice(noticeNo,loginHospital);
				
				//4.삭제 처리시 조건절
				if (result > 0) {
					response.sendRedirect("/NHMP/nlist.ad");
				}else {
					RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
					request.setAttribute("message", noticeNo + "번 공지글 삭제 실패!");
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
















