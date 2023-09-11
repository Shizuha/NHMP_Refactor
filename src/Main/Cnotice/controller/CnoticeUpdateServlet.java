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
 * Servlet implementation class CnoticeUpdateServlet
 */
@WebServlet("/gongup")
public class CnoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CnoticeUpdateServlet() {
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
		// 공지사항 수정 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		Cnotice n = new Cnotice();
		n.setNOTICE_NO(Integer.parseInt(request.getParameter("noticeno")));
		n.setNOTICE_TITLE(request.getParameter("title"));
		n.setNH_NAME(request.getParameter("writer"));
		n.setNOTICE_DATE(Date.valueOf(request.getParameter("date")));
		n.setNOTICE_CONTENT(request.getParameter("content"));
		n.setNOTICE_TYPE(request.getParameter("category"));
	
		
		int result = new CnoticeService().updatetNotice(n);
		RequestDispatcher view = null;
		if (result > 0) {
			response.sendRedirect("/NHMP/Cdetail?no="+request.getParameter("noticeno")+"&update=complete");
		} else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", n.getNOTICE_NO() + "번 공지사항 글 수정 실패했습니다.");
			view.forward(request, response);
		}
	}

}
