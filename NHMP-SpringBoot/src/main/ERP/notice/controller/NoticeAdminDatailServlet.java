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
import Main.NursingHospital.model.ov.*;

/**
 * Servlet implementation class NoticeDatailServlet
 */
@WebServlet("/ndetail.ad")
public class NoticeAdminDatailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminDatailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 상세보기 처리용 컨트롤러
		// 1. 인코딩처리 필요없음
				String noticeNo = (String)request.getParameter("no");
				System.out.println(noticeNo);
				String currentPage = (String)request.getParameter("page");
				System.out.println("noticeNo 값 옴 :" + noticeNo);
				System.out.println("currentPage 값 옴 : " + currentPage);
				//로그인한 id pwd 가져오기(로그인 정보로 구분짓기 위하여)
				
				//코드 & 코드 해석
				NursingHospital loginHospital = (NursingHospital)request.getSession().getAttribute("loginHospital");
				//널싱하스피럴로 형변환후 리퀘스트로요청 세션에 저장하여 getAttribute에서로그인호스팅값 구함
				
				
				
				//1증가된 조회수 셀렉트
				NoticeService noticeservice = new NoticeService();
				noticeservice.updateReadCount(noticeNo,loginHospital); //조회수 1 증가 처리함
				
				
				//로그인정보가 왔는지 확인
				
				//로그인 정보 안 NH_USERID, NH_USERPWD 가져오기 ???서비스로 보낼때 loginHospital에 담긴 id, pwd같이 가는건 아닌지?
				
				//노티스 정보 서비스로 보내기
				Notice notice = new NoticeService().selectOne(noticeNo, loginHospital);
				
				RequestDispatcher view = null;
				if (notice != null) {
					view = request.getRequestDispatcher("views/ERP/Notice/ErpAdminNoticeDetailView.jsp");
					request.setAttribute("notice", notice);
					request.setAttribute("currentPage", currentPage);//추가
					
					
				}else {
					view = request.getRequestDispatcher("views/common/Error.jsp");
					request.setAttribute("message", noticeNo + "번 관리자 공지 상세보기 실패!");
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
