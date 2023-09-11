package Main.Cnotice.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Main.Cnotice.model.service.*;
import Main.Cnotice.model.vo.Cnotice;

/**
 * Servlet implementation class CnoticeInsertServlet
 */
@WebServlet("/gongin")
public class CnoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CnoticeInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 공지사항 추가 컨트롤러
		// 1. 전송온 값에 한글이 있다면, 인코딩 처리 해야한다.
		request.setCharacterEncoding("UTF-8");

		// 2. 전송온 값 꺼내서 변수에 담기
		Cnotice c = new Cnotice();
		
		c.setNOTICE_TITLE(request.getParameter("title"));
		c.setNH_NAME(request.getParameter("writer"));
		c.setNOTICE_TYPE(request.getParameter("category"));
		c.setNOTICE_DATE(Date.valueOf(request.getParameter("date")));
		c.setNOTICE_CONTENT(request.getParameter("content"));
		int result = new CnoticeService().insertNotice(c);
		
		if(result > 0) {
			response.sendRedirect("/NHMP/gongall"); // 뷰만 내보낼때 sendredirect, 다른것들을 내보낼때  RequestDispatcher
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "공지사항 등록이 실패했습니다.");
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
