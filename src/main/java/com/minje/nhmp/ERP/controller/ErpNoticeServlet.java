package com.minje.nhmp.ERP.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.minje.nhmp.ERP.service.ErpNoticeService;
import com.minje.nhmp.ERP.vo.EmployeeVo;
import com.minje.nhmp.ERP.vo.ErpNoticeVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

@WebServlet(urlPatterns = {"/nwrite.ad", "/nlist", "/ndetail", "/nsearch", "/ntop"})
public class ErpNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ErpNoticeServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getServletPath().equals("/nwrite.ad")) {
			// 관리자용 공지글 등록 처리용 컨트롤러 : 파일 업로드 없음 오로지 관리자만 사용가능함
			
			NursingHospitalVo loginHospital = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			//NursingHospital로 형변환  후 리퀘스트로 요청, 세션에 저장하여 getAttribute에서 로그인 호스팅 값 구함.
			
			//로그인유무 확인?
			
			//1.인코딩처리
			request.setCharacterEncoding("utf-8");
			
			//5. 전송온 값 꺼내서 객체에 저장하기
			ErpNoticeVo notice = new ErpNoticeVo();
			
			notice.setNoticeTitle(request.getParameter("title"));
			notice.setNoticeContent(request.getParameter("content"));
			notice.setNoticeWriter(loginHospital.getNH_NAME());
			
			//6.모델 서비스로 전달하고, 결과받기
			int result = new ErpNoticeService().insertNotice(notice,loginHospital);
		
			RequestDispatcher view = null;
			//7 받은 결과로 성공/실패 페이지 내보내기
			if (result > 0) {
				response.sendRedirect("/NHMP/nlist.ad");
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", "새 공지글 등록 실패!");
				view.forward(request, response);
			}
		}
		
		if(request.getServletPath().equals("/nlist")) {
			// 공지사항 전체 목록보기 출력 처리용 컨트롤러 모델서비스로 요청받고 처리(패이징 처리)
			
			//Employee  의 로그인정보(loginEmployee) 받아오기
			EmployeeVo loginEmployee = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
	
			int currentPage = 1;
			if(request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
			
			int limit = 10;  //한 페이지에 출력할 목록 갯수
			
			//nservice변수 생성
			ErpNoticeService nservice = new ErpNoticeService();
			
			int listCount = nservice.getListCount(loginEmployee);  //테이블의 전체 목록 갯수 조회 + 1
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
			
			//조회할 목록의 시작행과 끝행 번호 전달하고 결과받기
			ArrayList<ErpNoticeVo> list = nservice.selectList(startRow, endRow,loginEmployee);	//여기부터 수정
			
			RequestDispatcher view = null;
			if(list.size() >= 0) {
				view = request.getRequestDispatcher("views/ERP/Notice/ErpNoticeListView.jsp");
				request.setAttribute("list", list);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("beginPage", beginPage);
				request.setAttribute("endPage", endPage);		
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", currentPage + "공지사항 전체 목록 조회 실패!");
			}
			view.forward(request, response);
		}
		
		if(request.getServletPath().equals("/ndetail")) {
			// 공지글 상세보기 처리용 컨트롤러
			String noticeNo = (String)request.getParameter("no");
			
			//Employee  의 로그인정보(loginEmployee) 받아오기
			EmployeeVo loginEmployee = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
	
			//1증가된 조회수 셀렉트
			ErpNoticeService noticeservice = new ErpNoticeService();
			noticeservice.updateReadCount(noticeNo,loginEmployee); //조회수 1 증가 처리함
	
			ErpNoticeVo notice = new ErpNoticeService().selectOne(noticeNo, loginEmployee);
			
			RequestDispatcher view = null;
			if (notice != null) {
				view = request.getRequestDispatcher("views/ERP/Notice/ErpNoticeDetailView.jsp");
				request.setAttribute("notice", notice);
				request.setAttribute("noticeno", noticeNo);
						
			}else {
				view = request.getRequestDispatcher("views/common/Error.jsp");
				request.setAttribute("message", noticeNo + "번 공지 상세보기 실패!");
			}
			view.forward(request, response);
		}
		
		if(request.getServletPath().equals("/nsearch")) {
			// 공지사항 글 검색 처리용 컨트롤러(재목 검색, 작성자 검색, 작성날짜 검색 기능)
			
			//Employee  의 로그인정보(loginEmployee) 받아오기
			EmployeeVo loginEmployee = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
			
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
							list = nservice.selectTitleSearch(noticeTitle, loginEmployee);
							break;
			case "writer":	String noticeWriter = request.getParameter("keyword");
							list = nservice.selectWriterSearch(noticeWriter, loginEmployee);
							break;
			case "date": String beginDate = request.getParameter("from");
						 String toDate = request.getParameter("to");
						 list = nservice.selectDateSearch(Date.valueOf(beginDate),Date.valueOf(toDate),loginEmployee);	
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
				view = request.getRequestDispatcher("views/ERP/Notice/ErpNoticeListView.jsp");
				
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
		
		if(request.getServletPath().equals("/ntop")) {
			EmployeeVo loginEmployee = (EmployeeVo)request.getSession().getAttribute("loginEmployee");
			NursingHospitalVo loginAdmin = (NursingHospitalVo)request.getSession().getAttribute("loginHospital");
			
			if(loginEmployee != null) {
			String hostid = loginEmployee.getHostId();
			String hostpwd = loginEmployee.getHostPwd();
			
			ArrayList<ErpNoticeVo> list = new ErpNoticeService().selectTop3(hostid, hostpwd);
			
			// 전송용 json 객체 생성
			JSONObject sendJson = new JSONObject();

			// list 옮겨 저장할 json 배열 객체를 생성
			JSONArray jarr = new JSONArray();

			// list를 jarr로 옮기기
			for (ErpNoticeVo n : list) {
				// b 객체 저장할 json 객체 생성
				JSONObject job = new JSONObject();
				job.put("no", n.getNoticeNo());
				// JSON에서 한글 깨짐 막으려면, java.net.URLEncoder.encode() 메소드로 인코딩 처리
				job.put("title", n.getNoticeTitle());
				job.put("date", String.valueOf(n.getNoticeDate()));

				jarr.add(job);
			}
			// json 배열을 전송용 json 객체에 저장한다.
			sendJson.put("list", jarr);
			// 요청한 뷰로 응답처리한다.
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(sendJson.toJSONString());
			out.flush();
			out.close();
			} else {
				String adminid = loginAdmin.getNH_USERID();
				String adminpwd = loginAdmin.getNH_USERPWD();
				
				ArrayList<ErpNoticeVo> list = new ErpNoticeService().AdminselectTop3(adminid, adminpwd);
				
				// 전송용 json 객체 생성
				JSONObject sendJson = new JSONObject();

				// list 옮겨 저장할 json 배열 객체를 생성
				JSONArray jarr = new JSONArray();

				// list를 jarr로 옮기기
				for (ErpNoticeVo n : list) {
					// b 객체 저장할 json 객체 생성
					JSONObject job = new JSONObject();
					job.put("no", n.getNoticeNo());
					// JSON에서 한글 깨짐 막으려면, java.net.URLEncoder.encode() 메소드로 인코딩 처리
					job.put("title", n.getNoticeTitle());
					job.put("date", String.valueOf(n.getNoticeDate()));

					jarr.add(job);
				}
				// json 배열을 전송용 json 객체에 저장한다.
				sendJson.put("list", jarr);
				// 요청한 뷰로 응답처리한다.
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write(sendJson.toJSONString());
				out.flush();
				out.close();
			}
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
