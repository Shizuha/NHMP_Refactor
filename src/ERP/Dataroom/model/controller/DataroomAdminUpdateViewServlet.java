package ERP.Dataroom.model.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ERP.Dataroom.model.vo.Dataroom;
import Main.vo.NursingHospitalVo;
import ERP.Dataroom.model.service.DataroomService;

/**
 * Servlet implementation class DataroomAdminUpdateViewServlet
 */
@WebServlet("/drupview")
public class DataroomAdminUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataroomAdminUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 자료실글 원글 수정페이지 이동 처리하는 컨트롤러
		// 인코딩 필요없음
		
		//nursinghospital 의 로그인정보 받아오기(공지사항구분을 위해)
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		
		//전송온 값 변수에 담기
		
		String dataroomNo = (String)request.getParameter("no");
		String currentPage = (String)request.getParameter("page");
		
		
		//패이지 지정하고 수정할 내용 출력
		Dataroom dataroom = new DataroomService().selectOne(dataroomNo, loginHospital);
		
		//전달할 정보 출력
		RequestDispatcher view = null;
		
		//성공 실패 판단하기
		if (dataroom != null) {
			view = request.getRequestDispatcher("views/ERP/Dataroom/ErpAdminDataroomUpdataView.jsp");
			request.setAttribute("dataroom", dataroom);
			
			//패이지 왔다갔다처리
			request.setAttribute("currentPage", currentPage);
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", dataroomNo + "번 게시글 수정페이지 이동 실패");
			
		}
		view.forward(request, response);
	}

	
	
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}










