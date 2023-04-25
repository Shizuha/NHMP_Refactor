package ERP.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ERP.notice.model.service.NoticeService;
import ERP.notice.model.vo.Notice;
import Main.NursingHospital.model.ov.NursingHospital;

/**
 * Servlet implementation class NoticeAdminUpdateViewServlet
 */
@WebServlet("/nupview")
public class NoticeAdminUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정페이지로 이동 처리하는 컨트롤러
		// 인코딩 필요없음
		
		//nursinghospital 의 로그인정보 받아오기(공지사항구분을 위해)
		NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
		
		
		
		// 전송온 값 변수에 담기
		String noticeNo = (String)request.getParameter("no");
		String currentPage = (String)request.getParameter("page");
		
		//패이지 지정 수정할 내용 출력
		Notice notice = new NoticeService().selectOne(noticeNo,loginHospital);
		
		//전달할 정보 출력
		RequestDispatcher view = null;
		
		//성공 실패 판단하기
		if (notice != null) {
			view = request.getRequestDispatcher("views/ERP/Notice/ErpAdminNoticeUpdataView.jsp");
			request.setAttribute("notice", notice);
			
			//패이지 왔다갔다처리
			request.setAttribute("currentPage", currentPage);
			
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", noticeNo + "번 게시글 수정페이지 이동 실패");
			
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
