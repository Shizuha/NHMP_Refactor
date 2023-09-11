package Main.Comment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Comment.model.service.CommentService;
import Main.Comment.model.vo.Comment;
import Main.vo.NursingHospitalVo;
import Main.vo.QnaVo;

/**
 * Servlet implementation class CommentInsertServlet
 */
@WebServlet("/dinqna")
public class CommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 등록 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		Comment com = new Comment();
		
		com.setQNA_NO(Integer.parseInt(request.getParameter("qnano")));
		com.setNH_ID(request.getParameter("writer"));
		com.setCOMMENT_ETC(request.getParameter("comments"));
		
		int result = new CommentService().insertComment(com);
		RequestDispatcher view = null;
		if(result > 0) {
			response.sendRedirect("/NHMP/detailqna?no="+com.getQNA_NO());
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "댓글 생성 실패");
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
