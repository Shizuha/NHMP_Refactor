package com.minje.nhmp.ERP.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minje.nhmp.ERP.service.ErpNoticeService;
import com.minje.nhmp.ERP.vo.ErpNoticeVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

@WebServlet(urlPatterns = {"/nlist.ad", "/ndetail.ad", "/nupdate", "/nupview", "/ndel.ad", "/nsearch.ad"})
public class ErpNoticeAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ErpNoticeAdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getServletPath().equals("/nlist.ad")) {
			// 공지사항 전체 목록보기 출력 처리용 컨트롤러 모델서비스로 요청받고 처리(패이징 처리)
		
			//nursinghospital 의 로그인정보 받아오기
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
		
			int currentPage = 1;
			if(request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
			
			int limit = 10;  //한 페이지에 출력할 목록 갯수
			ErpNoticeService nservice = new ErpNoticeService();
			
			int listCount = nservice.getListCount(loginHospital);  //테이블의 전체 목록 갯수 조회
			//총 페이지 수 계산
			int maxPage = listCount / limit;
			if(listCount % limit > 0)
				maxPage++;
			
			//currentPage 가 속한 페이지그룹의 시작페이지숫자와 끝숫자 계산
			//예, 현재 34페이지이면 31 ~ 40 이 됨. (페이지그룹의 수를 10개로 한 경우)
			int beginPage = (currentPage / limit) * limit + 1;
			int endPage = beginPage + 9;
			if(endPage > maxPage)
				endPage = maxPage;
			
			//currentPage 에 출력할 목록의 조회할 행 번호 계산
			int startRow = (currentPage * limit) - 9;
			int endRow = currentPage * limit;
			
			//조회할 목록의 시작행과 끝행 번호 서비스로 전달하고 결과받기
			ArrayList<ErpNoticeVo> list = nservice.selectList(startRow, endRow, loginHospital);
			
			RequestDispatcher view = null;
				if(list.size() >= 0) {
					view = request.getRequestDispatcher("views/ERP/Notice/ErpAdminNoticeListView.jsp");
					request.setAttribute("list", list);
					request.setAttribute("maxPage", maxPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("beginPage", beginPage);
					request.setAttribute("endPage", endPage);
					request.setAttribute("loginHospital", loginHospital);
					
				}else {
					view = request.getRequestDispatcher("views/common/Error.jsp");
					request.setAttribute("message", currentPage + "공지사항 관리자화면 전체 목록 조회 실패!");
				} 
			view.forward(request, response);
		}
		
		if(request.getServletPath().equals("/ndetail.ad")) {
			// 공지글 상세보기 처리용 컨트롤러
			// 1. 인코딩처리 필요없음
			String noticeNo = (String)request.getParameter("no");
			System.out.println(noticeNo);
			String currentPage = (String)request.getParameter("page");
			System.out.println("noticeNo 값 옴 :" + noticeNo);
			System.out.println("currentPage 값 옴 : " + currentPage);
			//로그인한 id pwd 가져오기(로그인 정보로 구분짓기 위하여)
			
			//코드 & 코드 해석
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			//널싱하스피럴로 형변환후 리퀘스트로요청 세션에 저장하여 getAttribute에서로그인호스팅값 구함
			
			//1증가된 조회수 셀렉트
			ErpNoticeService noticeservice = new ErpNoticeService();
			noticeservice.updateReadCount(noticeNo,loginHospital); //조회수 1 증가 처리함
			
			
			//로그인정보가 왔는지 확인
			
			//로그인 정보 안 NH_USERID, NH_USERPWD 가져오기 ???서비스로 보낼때 loginHospital에 담긴 id, pwd같이 가는건 아닌지?
			
			//노티스 정보 서비스로 보내기
			ErpNoticeVo notice = new ErpNoticeService().selectOne(noticeNo, loginHospital);
			
			RequestDispatcher view = null;
			if (notice != null) {
				view = request.getRequestDispatcher("views/ERP/Notice/ErpAdminNoticeDetailView.jsp");
				request.setAttribute("notice", notice);
				request.setAttribute("currentPage", currentPage);//추가
				
				
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", noticeNo + "번 관리자 공지 상세보기 실패!");
			}
			
			view.forward(request, response);
		}
		
		if(request.getServletPath().equals("/nupdate")) {
			//1.인코딩처리
			request.setCharacterEncoding("utf-8");
			
			//nursinghospital 의 로그인정보 받아오기(공지사항구분을 위해)
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			
			
			//2. 전송온 값 꺼내서 객체에 저장하기
			ErpNoticeVo notice = new ErpNoticeVo();
			
			notice.setNoticeNo(Integer.parseInt(request.getParameter("no")));
			notice.setNoticeTitle(request.getParameter("title"));
			notice.setNoticeContent(request.getParameter("content"));
			notice.setNoticeWriter(request.getParameter("writer"));
			
			
			
			//3.모델 서비스로 전달하고, 결과받기
			int result = new ErpNoticeService().updateNotice(notice,loginHospital);
			
			
			RequestDispatcher view = null;
			//4 받은 결과로 성공/실패 패이지 내보내기
			if (result > 0) {
				/*response.sendRedirect("/NHMP/ndetail.ad");*/
				response.sendRedirect("/NHMP/nlist.ad?page=" + Integer.parseInt(request.getParameter("page")));
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", notice.getNoticeNo() +" 공지글 수정 실패했습니다");
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/nupview")) {
			// 수정페이지로 이동 처리하는 컨트롤러
			// 인코딩 필요없음
			
			//nursinghospital의 로그인정보 받아오기(공지사항구분을 위해)
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");

			// 전송온 값 변수에 담기
			String noticeNo = (String)request.getParameter("no");
			String currentPage = (String)request.getParameter("page");
			
			//패이지 지정 수정할 내용 출력
			ErpNoticeVo notice = new ErpNoticeService().selectOne(noticeNo,loginHospital);
			
			//전달할 정보 출력
			RequestDispatcher view = null;
			
			//성공 실패 판단하기
			if (notice != null) {
				view = request.getRequestDispatcher("views/ERP/Notice/ErpAdminNoticeUpdataView.jsp");
				request.setAttribute("notice", notice);
				
				//패이지 왔다갔다처리
				request.setAttribute("currentPage", currentPage);
				
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", noticeNo + "번 게시글 수정페이지 이동 실패");
				
			}
			view.forward(request, response);
		}
		
		if(request.getServletPath().equals("/ndel.ad")) {
			// 관리자용 공지글 삭제 처리용 컨트롤러
			//1.인코딩 utf-8처리
			request.setCharacterEncoding("utf-8");
			
			//nursinghospital 의 로그인정보 받아오기(공지사항구분을 위해)
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			
			
			//2.
			String noticeNo = (String)request.getParameter("no");
			
			//3.모델의 서비스단과 연곃하고 전달받고, 결과받기
			int result = new ErpNoticeService().deleteNotice(noticeNo,loginHospital);
			
			//4.삭제 처리시 조건절
			if (result > 0) {
				response.sendRedirect("/NHMP/nlist.ad");
			}else {
				RequestDispatcher view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", noticeNo + "번 공지글 삭제 실패!");
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/nsearch.ad")) {
			// 공지사항 글 검색 처리용 컨트롤러(재목 검색, 작성자 검색, 작성날짜 검색 기능)
			
			//nursinghospital 의 로그인정보 받아오기
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			
			//1. 한글 깨짐 방지를 위한 인코딩 처리
			request.setCharacterEncoding("utf-8");
			
			//2. 전송온 값 변수에 옮기기 / 서비스로 옮기기 위하여 3번을 하기 위해 변수로 담아둠
			String search = request.getParameter("search");
			
			//3. 서비스로 전송 온 값 옮기고 결과받을 변수 준비
			ArrayList<ErpNoticeVo> list = null;
			
			//4. 노티스 서비스 준비
			ErpNoticeService nservice = new ErpNoticeService();
			
			//5. 검색자 구분하기
			switch(search) {
			case "title":	String noticeTitle = request.getParameter("keyword");
							list = nservice.selectTitleSearch(noticeTitle, loginHospital);
							break;
			case "writer":	String noticeWriter = request.getParameter("keyword");
							list = nservice.selectWriterSearch(noticeWriter, loginHospital);
							break;
			case "date": String beginDate = request.getParameter("from");
						 String toDate = request.getParameter("to");
						 list = nservice.selectDateSearch(Date.valueOf(beginDate),Date.valueOf(toDate),loginHospital);	
						 	break;
			}
			
			int currentPage = 1;
			if(request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
			int listCount = list.size();  //테이블 검색한 목록 갯수 조회
			//총 페이지 수 계산
			int limit = 10;//계산을 위한 변수지정
			int maxPage = listCount / limit;
			if(listCount % limit > 0)
				maxPage++;
			
			//currentPage 가 속한 페이지그룹의 시작페이지숫자와 끝숫자 계산
			//예, 현재 34페이지이면 31 ~ 40 이 됨. (페이지그룹의 수를 10개로 한 경우)
			int beginPage = (currentPage / limit) * limit + 1;
			int endPage = beginPage + 9;
			if(endPage > maxPage)
				endPage = maxPage;
			
			// currentPage 에 출력할 목록의 조회할 행 번호 계산
			// int startRow = (currentPage * limit) - 9;
			// int endRow = currentPage * limit;
			
			//6. 리턴값 내보내서 성공 실패 확인하기(리스트는 일반 리스트인대 admin리스트는?뷰파일을 두개 넣는 방법은?)admin 서블릿을 만들어야됨
			RequestDispatcher view = null;
			if (list.size() >= 0) {
				view = request.getRequestDispatcher("views/ERP/Notice/ErpAdminNoticeListView.jsp");
				
				request.setAttribute("list", list);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("beginPage", beginPage);
				request.setAttribute("endPage", endPage);
				view.forward(request, response);
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", search + " 글 검색 조회 실패!");
				view.forward(request, response);	
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}


