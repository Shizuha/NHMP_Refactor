package Main.Cnotice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.Cnotice.model.service.CnoticeService;
import Main.Cnotice.model.vo.Cnotice;

/**
 * Servlet implementation class CnoticeDetailServlet
 */
@WebServlet("/Cdetail")
public class CnoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CnoticeDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 공지글 상세보기 처리용 컨트롤러
		int noticeno = Integer.parseInt(request.getParameter("no"));
		Cnotice notice = new CnoticeService().selectOne(noticeno);

		RequestDispatcher view = null;
		if (notice != null) {
			view = request.getRequestDispatcher("views/Main/serviceNoticeDetail.jsp");
			request.setAttribute("notice", notice);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", noticeno + "번글 공지 상세보기 처리 실패했습니다.");
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
