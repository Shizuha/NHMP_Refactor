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
 * Servlet implementation class DataroomAdminUpdataServlet
 */
@WebServlet("/drupdate")
public class DataroomAdminUpdataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataroomAdminUpdataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 원글 수정 처리용 컨트롤러
		
		//nursinghospital 의 로그인정보 받아오기(자료실 구분을 위해)
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		
		//1. post 전송시에는 인코딩 처리 필요
		//특히 전송값에 한글이 있을경우 처리 필요
		request.setCharacterEncoding("utf-8");
		
		//form 태그에 enctype="multipart/form-data" 전송 확인
		//enctype 속성으로 전송하지 않았다면 에러 페이지 내보냄
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", "form 태그에 enctype 속성 사용 안 되었음.");
			view.forward(request, response);
		}
		
		//업로드할 첨부파일의 용량 지정 : 50Mbyte 로 제한한다면
		int maxSize = 1024 * 1024 * 50;
		
		//4. 업로드할 파일이 저장될 폴더 지정
		//현재 서버 엔진(웹 컨테이너)에서 실행되고 있는 에플리케이션의 루트 안의 폴더일 때는
		String savePath = request.getSession().getServletContext().getRealPath("/resources/dataroom_upfiles");
		
		//전송 온 request 를 MultipartRequest 로 바꿈 : 객체 생성
		//MultipartRequest 객체 생성이 성공하면, 자동 파일이 업로드됨
		MultipartRequest mrequest = new MultipartRequest(request, 
				savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		
		//6. 전송온 값 꺼내서 객체에 저장하기
		Dataroom dataroom = new Dataroom();
				
		dataroom.setDataroomTitle(mrequest.getParameter("title"));
		dataroom.setDataroomContent(mrequest.getParameter("content"));
		dataroom.setDataroomWriter(loginHospital.getNH_NAME());
		
		int dataroomNo = Integer.parseInt(mrequest.getParameter("no"));
		dataroom.setDataroomNo(dataroomNo);
		
		String ofile = mrequest.getParameter("ofile");
		String rfile = mrequest.getParameter("rfile");
		
		//업로드된 첨부파일명 추출해서 해당 필드에 기록
		String dataroomOriginalFileName = mrequest.getFilesystemName("upfile");
		
		//첨부된 파일이 있다면, 파일명 바꾸기 처리
		//"yyyyMMddhhmmss.확장자" 형식으로 바꿈
		if(dataroomOriginalFileName != null) {
			//바꿀 파일명에 대한 포맷 설정함
			SimpleDateFormat sdf = 
					new SimpleDateFormat("yyyyMMddHHmmss");
			//바꿀 파일명 만들기 : 확장자는 원본과 동일하게 함.
			String dataroomRenameFileName = sdf.format(
					new java.sql.Date(System.currentTimeMillis()))
					+ "." + dataroomOriginalFileName.substring(
							dataroomOriginalFileName.lastIndexOf(".") + 1);
			
			//현재 지정된 폴더에 저장된 원본파일의 파일명을 바꾸기하기 위해서
			//File 객체를 생성함.
			File originFile = new File(savePath + "\\" + dataroomOriginalFileName);
			File renameFile = new File(savePath + "\\" + dataroomRenameFileName);
			
			//파일명 바꾸기 실행함 >> 실패할 경우에는 직접 바꾸기함.
			if(!originFile.renameTo(renameFile)) {
				//저장 폴더 안에 새 파일을 만들고 
				//>> 원본 파일을 읽어서 새 파일에 복사 기록함
				//>> 복사가 끝나면 원본 파일을 삭제함.
				int read = -1;
				byte[] buf = new byte[1024]; //한 번에 읽을 배열 크기 지정
				
				//대상 파일과 읽기 또는 쓰기용 스트림 생성
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);
				
				//읽어서 복사 기록하기
				while((read = fin.read(buf, 0,buf.length)) != -1) {
					fout.write(buf, 0, read);
					//buf 에서 0 번 인덱스부터 read 가 받은 바이트 갯수만큼 꺼내서
					//기록함.
				} //복사 완료됨
				
				//스트림 반납
				fin.close();
				fout.close();
				//저장 폴더에 있는 원본 파일 삭제함
				originFile.delete();
				//새로운 첨부파일이 있으므로, 원본파일 및 이전rename 파일도 삭제함
				new File(savePath + "\\" + rfile).delete();
			}  //renameTo() 실패시 처리 완료
			
			dataroom.setDataroomOriginalFileName(dataroomOriginalFileName);
			dataroom.setDataroomRenameFileName(dataroomRenameFileName);
			
			
			
			
		}  else {
			//새로운 첨부파일이 없다면, 원래 파일명 필드에 기록
			dataroom.setDataroomOriginalFileName(ofile);
			dataroom.setDataroomRenameFileName(rfile);
		}
		
		//서비스로 전달하고 결과 받기
		int result = new DataroomService().updateBoard(dataroom, loginHospital);
		
		if(result > 0) {
			/*int currentPage = Integer.parseInt(mrequest.getParameter("page"));
			response.sendRedirect("/first/blist?page=" + currentPage);*/
			response.sendRedirect("/NHMP/drlist.ad?page=" + Integer.parseInt(mrequest.getParameter("page")));
		}else {
			view = request.getRequestDispatcher("views/common/Error.jsp");
			request.setAttribute("message", dataroomNo + "번 게시글 수정 실패!");
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











