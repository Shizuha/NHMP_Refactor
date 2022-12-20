package Main.Qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Comment.model.service.CommentService;
import Main.Comment.model.vo.Comment;
import Main.Qna.model.service.QnaService;
import Main.Qna.model.vo.Qna;

/**
 * Servlet implementation class QnaDetailServlet
 */
@WebServlet("/detailqna")
public class QnaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Qna 디테일 컨트롤러
		int qnano = Integer.parseInt(request.getParameter("no"));
		// 3.
		Qna qna = new QnaService().detailQna(qnano);
		ArrayList<Comment> list = new CommentService().selectList(qnano);
		// 4.
		RequestDispatcher view = null;

		if (qna != null) {
			view = request.getRequestDispatcher("views/Main/serviceQnaDetail.jsp");
			request.setAttribute("qna", qna);
			request.setAttribute("list", list);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", qnano + " 번 게시글 상세보기 실패했습니다.");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
