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
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/oupqna")
public class QnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Qna 수정 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		Qna q = new Qna();

		q.setQNA_NO(Integer.parseInt(request.getParameter("qnano")));
		q.setQNA_TITLE(request.getParameter("title"));
		q.setNH_NAME(request.getParameter("writer"));
		q.setQNA_DATE(Date.valueOf(request.getParameter("date")));
		q.setQNA_CONTENT(request.getParameter("content"));
		q.setQNA_TYPE(request.getParameter("category"));
	
		
		int result = new QnaService().updateQna(q);
		RequestDispatcher view = null;
		if (result > 0) {
			response.sendRedirect("/NHMP/detailqna?no="+request.getParameter("qnano")+"&update=complete");
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", q.getQNA_NO() + "번 공지사항 글 수정 실패했습니다.");
			view.forward(request, response);
		}
	}

}
