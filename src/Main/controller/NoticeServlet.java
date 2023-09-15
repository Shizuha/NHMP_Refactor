package Main.Cnotice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Main.Cnotice.model.service.*;
import Main.Cnotice.model.vo.Cnotice;

@WebServlet(urlPatterns = {"/mainnoticeinsert", "/mainnoticelist", "/mainnoticedetail", "/mainnoticedelete", "/mainnoticeupdate"})
public class CnoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CnoticeInsertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/mainnoticeinsert").equals("/mainnoticeinsert")) {
			// 공지사항 추가 컨트롤러
			// 1. 전송온 값에 한글이 있다면, 인코딩 처리 해야한다.
			request.setCharacterEncoding("UTF-8");
	
			// 2. 전송온 값 꺼내서 변수에 담기
			Cnotice c = new Cnotice();
			
			c.setNOTICE_TITLE(request.getParameter("title"));
			c.setNH_NAME(request.getParameter("writer"));
			c.setNOTICE_TYPE(request.getParameter("category"));
			c.setNOTICE_DATE(Date.valueOf(request.getParameter("date")));
			c.setNOTICE_CONTENT(request.getParameter("content"));
			int result = new CnoticeService().insertNotice(c);
			
			if(result > 0) {
				response.sendRedirect("/NHMP/mainnoticelist"); // 뷰만 내보낼때 sendredirect, 다른것들을 내보낼때  RequestDispatcher
			} else {
				RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "공지사항 등록이 실패했습니다.");
				view.forward(request, response);
			}
		}
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/mainnoticelist").equals("/mainnoticelist")) {
			
			int currentPage = 1;
			
			if(request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} 
			
			int limit = 14; // 한 페이지에 출력할 목록 갯수
			CnoticeService notice = new CnoticeService();
			
			int listCount = notice.getListCount(); //테이블의 전체 목록 갯수 조회
			//총 페이지 수 계산
			int maxPage = listCount / limit ;
			if(listCount % limit > 0) {
				maxPage++;
			};
			int startRow = (currentPage * limit) - 13;
			int endRow = currentPage * limit;
			
			//currentPage 가 속한 페이지그룹의 시작페이지숫자와 끝숫자 계산
			//예, 현재 34페이지이면 31~40이 됨. (페이지그룹의 수를 10개로 한 경우)
			int beginPage = (currentPage / limit) * limit + 1;
			int endPage = beginPage + 13;
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			// 전체 공지글 조회 컨트롤러
			ArrayList<Cnotice> list = notice.selectList(startRow, endRow);

			RequestDispatcher view = null;
			
			if (list.size() > 0) {
				view = request.getRequestDispatcher("views/Main/serviceNotice.jsp");
				request.setAttribute("list", list);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("beginPage", beginPage);
				request.setAttribute("endPage", endPage);
			} else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "공지사항 목록 조회 실패했습니다.");
			}
				view.forward(request, response);
		}
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/mainnoticedetail").equals("/mainnoticedetail")) {
			// 공지글 상세보기 처리용 컨트롤러
			int noticeno = Integer.parseInt(request.getParameter("no"));
			Cnotice notice = new CnoticeService().selectOne(noticeno);

			RequestDispatcher view = null;
			if (notice != null) {
				view = request.getRequestDispatcher("views/Main/serviceNoticeDetail.jsp");
				request.setAttribute("notice", notice);
			} else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", noticeno + "번글 공지 상세보기 처리 실패했습니다.");
			}
			
			view.forward(request, response);
		}
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/mainnoticeupdate").equals("/mainnoticeupdate")) {
			// 공지사항 삭제 컨트롤러
			
			request.setCharacterEncoding("UTF-8");
			int noticeno = Integer.parseInt(request.getParameter("no"));
			
			int result = new CnoticeService().deleteNotice(noticeno);
			
			if(result > 0) {
				response.sendRedirect("/NHMP/mainnoticelist");
			} else {
				RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", noticeno + "번 공지글 삭제 실패!");
				view.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("mainnoticeupdate").equals("mainnoticeupdate")) {
			// 공지사항 수정 컨트롤러
			request.setCharacterEncoding("utf-8");
			
			Cnotice n = new Cnotice();
			n.setNOTICE_NO(Integer.parseInt(request.getParameter("noticeno")));
			n.setNOTICE_TITLE(request.getParameter("title"));
			n.setNH_NAME(request.getParameter("writer"));
			n.setNOTICE_DATE(Date.valueOf(request.getParameter("date")));
			n.setNOTICE_CONTENT(request.getParameter("content"));
			n.setNOTICE_TYPE(request.getParameter("category"));
		
			
			int result = new CnoticeService().updatetNotice(n);
			RequestDispatcher view = null;
			if (result > 0) {
				response.sendRedirect("/NHMP/mainnoticedetail?no="+request.getParameter("noticeno")+"&update=complete");
			} else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", n.getNOTICE_NO() + "번 공지사항 글 수정 실패했습니다.");
				view.forward(request, response);
			}
		}
	}
}
