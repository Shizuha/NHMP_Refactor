package Main.Comment.controller;

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
import Main.vo.NursingHospitalVo;
import Main.vo.QnaVo;

@WebServlet(urlPatterns = {"/maincommnetinsert", "/maincommentlist", "/maincommentupdate", "/maincommentdelete"})
public class CommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/maincommnetinsert").equals("/maincommnetinsert")) {
			// 댓글 등록 컨트롤러
			request.setCharacterEncoding("utf-8");
			
			Comment com = new Comment();
			
			com.setQNA_NO(Integer.parseInt(request.getParameter("qnano")));
			com.setNH_ID(request.getParameter("writer"));
			com.setCOMMENT_ETC(request.getParameter("comments"));
			
			int result = new CommentService().insertComment(com);
			RequestDispatcher view = null;
			if(result > 0) {
				response.sendRedirect("/NHMP/mainqnadetail?no="+com.getQNA_NO());
			} else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "댓글 생성 실패");
				view.forward(request, response);
			}
		}
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/maincommentlist").equals("/maincommentlist")) {
			QnaVo q = (QnaVo)request.getSession().getAttribute("qna");
			
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
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/commnetupdate").equals("/commnetupdate")) {
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
				response.sendRedirect("/NHMP/mainqnadetail?no="+ com.getQNA_NO() +"&update=complete");
			} else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", com.getQNA_NO() + "번 댓글 수정 실패했습니다.");
				view.forward(request, response);
			}
		}
		
		if(javax.servlet.annotation.WebServlet.urlPatterns("/maincommentdelete").equals("/maincommentdelete")) {
			// 댓글 삭제 컨트롤러
			int commno = Integer.parseInt(request.getParameter("no")); // <댓글번호 
			int commqnano = Integer.parseInt(request.getParameter("qnano")); // <- 안넘겨줌 
			
			int result = new CommentService().deleteComment(commno);
			
			if(result > 0) {
				response.sendRedirect("/NHMP/mainqnadetail?no="+ commqnano +"&delete=complete");
			} else {
				RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", commno + "번 댓글 삭제 실패!");
				view.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
