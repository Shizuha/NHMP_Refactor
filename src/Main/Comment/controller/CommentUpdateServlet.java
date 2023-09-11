package Main.Comment.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Comment.model.service.CommentService;
import Main.Comment.model.vo.Comment;
import Main.vo.QnaVo;

/**
 * Servlet implementation class CommentUpdateServlet
 */
@WebServlet("/dupqna")
public class CommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 수정 컨트롤러
		
		request.setCharacterEncoding("utf-8");
		
		Comment com = new Comment();

		com.setCOMMENT_NO(Integer.parseInt(request.getParameter("commno")));
		com.setCOMMENT_ETC(request.getParameter("commetc"));
		com.setNH_ID(request.getParameter("commname"));
		com.setQNA_NO(Integer.parseInt(request.getParameter("commqnano")));
	
		int result = new CommentService().updateComment(com);
		
		RequestDispatcher view = null;
		if (result > 0) {
			response.sendRedirect("/NHMP/detailqna?no="+ com.getQNA_NO() +"&update=complete");
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", com.getQNA_NO() + "번 댓글 수정 실패했습니다.");
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
