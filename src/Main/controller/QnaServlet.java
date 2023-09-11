package Main.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Cnotice.model.service.CnoticeService;
import Main.Cnotice.model.vo.Cnotice;
import Main.Comment.model.service.CommentService;
import Main.Comment.model.vo.Comment;
import Main.service.QnaService;
import Main.vo.QnaVo;

@WebServlet(urlPatterns = {"/insertqna", "/viewqna", "/detailqna", "/updateqna", "/deleteqna"})
public class QnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QnaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Qna 등록 코드
		request.setCharacterEncoding("UTF-8"); // 전송온 값에 한글이 있다면, 인코딩 처리

		QnaVo qnaVoInsert = new QnaVo(); // 전송온 값 꺼내서 변수 e

		qnaVoInsert.setQNA_TITLE(request.getParameter("title"));
		qnaVoInsert.setNH_NAME(request.getParameter("writer"));
		qnaVoInsert.setQNA_TYPE(request.getParameter("category"));
		qnaVoInsert.setQNA_DATE(Date.valueOf(request.getParameter("date")));
		qnaVoInsert.setQNA_CONTENT(request.getParameter("content"));

		int resultInsert = new QnaService().insertQna(qnaVoInsert);

		if (resultInsert > 0) {
			response.sendRedirect("/NHMP/allqna"); // 뷰만 내보낼때 sendredirect, 다른것들을 내보낼때 RequestDispatcher
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "QNA 등록이 실패했습니다.");
			view.forward(request, response);
		}
		
		// Qna 페이징 코드
		int currentPage = 1;
		
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} 
		
		int limit = 14; // 한 페이지에 출력할 목록 갯수
		QnaService qnaPaging = new QnaService();
		
		int listCount = qnaPaging.getListCount(); //테이블의 전체 목록 갯수 조회
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
		
		// 전체 공지글 조회 
		ArrayList<QnaVo> qnaVoList = new QnaService().selectList(startRow, endRow);

		RequestDispatcher view = null;
		
		if (qnaVoList.size() > 0) {
			view = request.getRequestDispatcher("views/Main/serviceQna.jsp");
			request.setAttribute("qnaVoList", qnaVoList);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("beginPage", beginPage);
			request.setAttribute("endPage", endPage);
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "Qna 목록 조회 실패했습니다.");
		}
			view.forward(request, response);
			
		// Qna 디테일 코드
		int qnaNoDetail = Integer.parseInt(request.getParameter("no"));

		ArrayList<Comment> listDetail = new CommentService().selectList(qnaNoDetail);
		
		view = null;

		if (listDetail != null) {
			view = request.getRequestDispatcher("views/Main/serviceQnaDetail.jsp");
			request.setAttribute("qnaNoDetail", qnaNoDetail);
			request.setAttribute("listDetail", listDetail);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", qnaNoDetail + " 번 게시글 상세보기 실패했습니다.");
			view.forward(request, response);
		}
			
		// Qna 삭제용 코드
		request.setCharacterEncoding("UTF-8");
		int qnaNoDelete = Integer.parseInt(request.getParameter("no"));
		
		int resultDelete = new QnaService().deleteQna(qnaNoDelete);
		if(resultDelete > 0) {
			response.sendRedirect("/NHMP/allqna");
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", qnaNoDelete + "번 공지글 삭제 실패!");
			view.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Qna 수정 코드
		request.setCharacterEncoding("utf-8");
		
		QnaVo qnaUpdate = new QnaVo();

		qnaUpdate.setQNA_NO(Integer.parseInt(request.getParameter("qnano")));
		qnaUpdate.setQNA_TITLE(request.getParameter("title"));
		qnaUpdate.setNH_NAME(request.getParameter("writer"));
		qnaUpdate.setQNA_DATE(Date.valueOf(request.getParameter("date")));
		qnaUpdate.setQNA_CONTENT(request.getParameter("content"));
		qnaUpdate.setQNA_TYPE(request.getParameter("category"));
	
		int resultUpdate = new QnaService().updateQna(qnaUpdate);
		RequestDispatcher view = null;
		if (resultUpdate > 0) {
			response.sendRedirect("/NHMP/detailqna?no="+request.getParameter("qnano")+"&update=complete");
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", qnaUpdate.getQNA_NO() + "번 공지사항 글 수정 실패했습니다.");
			view.forward(request, response);
		}
	}
}

