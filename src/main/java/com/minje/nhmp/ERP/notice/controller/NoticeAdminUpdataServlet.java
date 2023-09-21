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
import Main.vo.NursingHospitalVo;

/**
 * Servlet implementation class NoticeAdminUpdataServlet
 */
@WebServlet("/nupdate")
public class NoticeAdminUpdataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminUpdataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //게시글 원글 수청 처리용 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//1.인코딩처리
				request.setCharacterEncoding("utf-8");
				
				//nursinghospital 의 로그인정보 받아오기(공지사항구분을 위해)
				NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
				
				
				//2. 전송온 값 꺼내서 객체에 저장하기
				Notice notice = new Notice();
				
				notice.setNoticeNo(Integer.parseInt(request.getParameter("no")));
				notice.setNoticeTitle(request.getParameter("title"));
				notice.setNoticeContent(request.getParameter("content"));
				notice.setNoticeWriter(request.getParameter("writer"));
				
				
				
				//3.모델 서비스로 전달하고, 결과받기
				int result = new NoticeService().updateNotice(notice,loginHospital);
				
				
				RequestDispatcher view = null;
				//4 받은 결과로 성공/실패 패이지 내보내기
				if (result > 0) {
					/*response.sendRedirect("/NHMP/ndetail.ad");*/
					response.sendRedirect("/NHMP/nlist.ad?page=" + Integer.parseInt(request.getParameter("page")));
				}else {
					view = request.getRequestDispatcher("views/common/Error.jsp");
					request.setAttribute("message", notice.getNoticeNo() +" 공지글 수정 실패했습니다");
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
