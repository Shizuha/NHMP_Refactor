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

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ERP.Dataroom.model.vo.Dataroom;
import ERP.Dataroom.model.service.DataroomService;
import Main.NursingHospital.model.*;
import Main.vo.NursingHospitalVo;


/**
 * Servlet implementation class DataroomWriteServlet
 */
@WebServlet("/drwrite.ad")
public class DataroomWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataroomWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 자료실 글쓰기
		// 관리자 정보를 불러올 NursingHospital 파일을 loginHospital로 지정 
		NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		//1. post 전송시에는 반드시 인코딩 처리함.
				//특히, 전송값에 한글이 있으면 반드시 인코딩 처리함
				request.setCharacterEncoding("utf-8");
				
		//2. multipart 방식으로 전송왔는지 확인함 : 아니면 에러 페이지 내보냄
		RequestDispatcher view = null;
		if (!ServletFileUpload.isMultipartContent(request)) {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "form enctype 속성이 multipart 여야 합니다");
				view.forward(request, response);		
		}
		
		//3. 업로드할 파일의 용량 제한 설정 : 50Mbtype 로
		int maxSize = 1024 * 1024 * 50;
		
		//4. 업로드할 파일이 저장될 폴더 지정
		//현재 서버 엔진(웹 컨테이너)에서 실행되고 있는 에플리케이션의 루트 안의 폴더일 때는
		String savePath = request.getSession().getServletContext().getRealPath("/resources/dataroom_upfiles");
		
		//5.파일 업로드 실행
		//cos.jar 는 MultipartRequest 클래스를 사용해서 request 를 MultipartRequest 로 바꿈
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		//객체 생성과 동시에 파일 업로드 됨.
		
		//6. 전송온 값 꺼내서 객체에 저장하기
		Dataroom dataroom = new Dataroom();
		
		dataroom.setDataroomTitle(mrequest.getParameter("title"));
		dataroom.setDataroomContent(mrequest.getParameter("content"));
		dataroom.setDataroomWriter(loginHospital.getNH_NAME());
		
		//7. 업로드 저장된 파일이름만 추출해서 변수에 기록하기
		String dataroomOriginalFileName = mrequest.getFilesystemName("upfile");
		
		dataroom.setDataroomOriginalFileName(dataroomOriginalFileName);
		
		//DB기록 처리
				//저장 폴더의 파일명 중복 해결 방법 : 파일명을 "yyyyMMddHHmmss.확장자" 로 바꾸기
				if(dataroomOriginalFileName != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String dataroomRenameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()))
							 + "." + dataroomOriginalFileName.substring(dataroomOriginalFileName.lastIndexOf(".") + 1);
					
					//파일명을 바꾸려면 File 객체의 renameTo() 사용함
					File originFile = new File(savePath + "\\" + dataroomOriginalFileName);
					File renameFile = new File(savePath + "\\" + dataroomRenameFileName);
					
					//파일 이름바꾸기 실행함 >> 실패한 경우에는 직접 바꾸기함
					if(!originFile.renameTo(renameFile)) {
						//파일 입출력 스트림 생성하고, 원본을 읽어서 바꿀이름 파일에 기록함
						int read = -1;
						byte[] buf = new byte[1024];	//한 번에 읽어서 저장할 바이트 배열
						
						FileInputStream fin  = new FileInputStream(originFile);
						FileOutputStream fout = new FileOutputStream(renameFile);
						
						while((read = fin.read(buf, 0, buf.length)) != -1) {
							fout.write(buf, 0, read);
						}
						
						fin.close();
						fout.close();
						originFile.delete();  //원본 파일 삭제함.
					}	//renameTo() 완료
						
					dataroom.setDataroomRenameFileName(dataroomRenameFileName);
					
				}	//파일 업로드시
				
		//8. 모델 서비스로 전달하고, 결과받기
		int result = new DataroomService().insertDataroom(dataroom, loginHospital);
		
		
		//9. 받은 결과로 성공/실패 페이지 내보내기
		if (result > 0) {
			response.sendRedirect("/NHMP/drlist.ad");
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











