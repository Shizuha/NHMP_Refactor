package Main.Comment.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Cnotice.model.vo.Cnotice;
import Main.Comment.model.service.CommentService;
import Main.Comment.model.vo.Comment;
import Main.Qna.model.vo.Qna;

/**
 * Servlet implementation class CommentListServlet
 */
@WebServlet("/comlist")
public class CommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Qna q = (Qna)request.getSession().getAttribute("qna");
		
		int qnano = Integer.parseInt(request.getParameter(String.valueOf(q.getQNA_NO())));
		ArrayList<Comment> list = new CommentService().selectList(qnano);
		

		RequestDispatcher view = null;
		
		if (list.size() > 0) {
			view = request.getRequestDispatcher("views/Main/detailqna?no=" + q.getQNA_NO());
			request.setAttribute("list", list);
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "공지사항 목록 조회 실패했습니다.");
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
