package ERP.notice.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;


import ERP.notice.model.service.NoticeService;
import ERP.notice.model.vo.Notice;
import Main.NursingHospital.model.*;
import Main.vo.NursingHospitalVo;


/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/nwrite.ad")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자용 공지글 등록 처리용 컨트롤러 : 파일 업로드 없음 오로지 관리자만 사용가능함
		
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		//널싱하스피럴로 형변환후 리퀘스트로요청 세션에 저장하여 getAttribute에서로그인호스팅값 구함
		
		
		
		
		//로그인유무 확인?
		
		//1.인코딩처리
		request.setCharacterEncoding("utf-8");
		
		//5. 전송온 값 꺼내서 객체에 저장하기
		Notice notice = new Notice();
		
		notice.setNoticeTitle(request.getParameter("title"));
		notice.setNoticeContent(request.getParameter("content"));
		notice.setNoticeWriter(loginHospital.getNH_NAME());
		
		
		
		//6.모델 서비스로 전달하고, 결과받기
		int result = new NoticeService().insertNotice(notice,loginHospital);
		
		
		RequestDispatcher view = null;
		//7 받은 결과로 성공/실패 패이지 내보내기
		if (result > 0) {
			response.sendRedirect("/NHMP/nlist.ad");
			
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "새 공지글 등록 실패!");
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
