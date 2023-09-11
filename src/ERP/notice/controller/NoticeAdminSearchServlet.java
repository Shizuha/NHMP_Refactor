package ERP.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

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
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet("/nsearch.ad")
public class NoticeAdminSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 글 검색 처리용 컨트롤러(재목 검색, 작성자 검색, 작성날짜 검색 기능)
		
		//nursinghospital 의 로그인정보 받아오기
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		
		//1. 한글 깨짐 방지를 위한 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//2. 전송온 값 변수에 옮기기 / 서비스로 옮기기 위하여 3번을 하기 위해 변수로 담아둠
		String search = request.getParameter("search");
		
		//3. 서비스로 전송 온 값 옮기고 결과받을 변수 준비
		ArrayList<Notice> list = null;
		
		//4. 노티스 서비스 준비
		NoticeService nservice = new NoticeService();
		
		//5. 검색자 구분하기
		switch(search) {
		case "title":	String noticeTitle = request.getParameter("keyword");
						list = nservice.selectTitleSearch(noticeTitle, loginHospital);
						break;
		case "writer":	String noticeWriter = request.getParameter("keyword");
						list = nservice.selectWriterSearch(noticeWriter, loginHospital);
						break;
		case "date": String beginDate = request.getParameter("from");
					 String toDate = request.getParameter("to");
					 list = nservice.selectDateSearch(Date.valueOf(beginDate),Date.valueOf(toDate),loginHospital);	
					 	break;
		}
		
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		int listCount = list.size();  //테이블 검색한 목록 갯수 조회
		//총 페이지 수 계산
		int limit = 10;//계산을 위한 변수지정
		int maxPage = listCount / limit;
		if(listCount % limit > 0)
			maxPage++;
		
		//currentPage 가 속한 페이지그룹의 시작페이지숫자와 끝숫자 계산
		//예, 현재 34페이지이면 31 ~ 40 이 됨. (페이지그룹의 수를 10개로 한 경우)
		int beginPage = (currentPage / limit) * limit + 1;
		int endPage = beginPage + 9;
		if(endPage > maxPage)
			endPage = maxPage;
		
		//currentPage 에 출력할 목록의 조회할 행 번호 계산
		int startRow = (currentPage * limit) - 9;
		int endRow = currentPage * limit;
		
		//6. 리턴값 내보내서 성공 실패 확인하기(리스트는 일반 리스트인대 admin리스트는?뷰파일을 두개 넣는 방법은?)admin 서블릿을 만들어야됨
		RequestDispatcher view = null;
		if (list.size() >= 0) {
			view = request.getRequestDispatcher("views/ERP/Notice/ErpAdminNoticeListView.jsp");
			
			request.setAttribute("list", list);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("beginPage", beginPage);
			request.setAttribute("endPage", endPage);
			view.forward(request, response);
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", search + " 글 검색 조회 실패!");
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












