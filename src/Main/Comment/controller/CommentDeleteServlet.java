package Main.Comment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Comment.model.service.CommentService;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/ddelqna")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 삭제 컨트롤러
		int commno = Integer.parseInt(request.getParameter("no")); // <댓글번호 
		int commqnano = Integer.parseInt(request.getParameter("qnano")); // <- 안넘겨줌 
		
		int result = new CommentService().deleteComment(commno);
		
		if(result > 0) {
			response.sendRedirect("/NHMP/detailqna?no="+ commqnano +"&delete=complete");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", commno + "번 댓글 삭제 실패!");
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
