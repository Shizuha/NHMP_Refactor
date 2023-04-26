package Main.Qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Cnotice.model.service.CnoticeService;
import Main.Qna.model.service.QnaService;
import Main.Qna.model.vo.Qna;

/**
 * Servlet implementation class QnaDeleteServlet
 */
@WebServlet("/odelqna")
public class QnaDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Qna 삭제용 컨트롤러
		request.setCharacterEncoding("UTF-8");
		int qnano = Integer.parseInt(request.getParameter("no"));
		
		int result = new QnaService().deleteQna(qnano);
		if(result > 0) {
			response.sendRedirect("/NHMP/allqna");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", qnano + "번 공지글 삭제 실패!");
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
