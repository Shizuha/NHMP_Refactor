package Main.Qna.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Cnotice.model.service.CnoticeService;
import Main.Cnotice.model.vo.Cnotice;
import Main.Qna.model.service.QnaService;
import Main.Qna.model.vo.Qna;

/**
 * Servlet implementation class QnaInsertServlet
 */
@WebServlet("/oinqna")
public class QnaInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Qna 등록 컨트롤러
		// 1. 전송온 값에 한글이 있다면, 인코딩 처리 해야한다.
		request.setCharacterEncoding("UTF-8");

		// 2. 전송온 값 꺼내서 변수에 담기
		Qna q = new Qna();

		q.setQNA_TITLE(request.getParameter("title"));
		q.setNH_NAME(request.getParameter("writer"));
		q.setQNA_TYPE(request.getParameter("category"));
		q.setQNA_DATE(Date.valueOf(request.getParameter("date")));
		q.setQNA_CONTENT(request.getParameter("content"));

		int result = new QnaService().insertQna(q);

		if (result > 0) {
			response.sendRedirect("/NHMP/allqna"); // 뷰만 내보낼때 sendredirect, 다른것들을 내보낼때 RequestDispatcher
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "QNA 등록이 실패했습니다.");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
