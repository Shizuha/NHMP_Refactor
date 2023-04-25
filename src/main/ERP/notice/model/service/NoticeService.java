package ERP.notice.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import ERP.Employee.model.vo.Employee;
import ERP.notice.model.dao.NoticeDao;
import ERP.notice.model.vo.Notice;
import Main.NursingHospital.model.ov.NursingHospital;


public class NoticeService {
	private NoticeDao ndao = new NoticeDao();  //DI(의존성 주입)
	
	public NoticeService() {}
	
	//공지사항 전체보기
	public ArrayList<Notice> selectAll(NursingHospital loginHospital){
		//아이디랑 비밀번호로 게시판 구분
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Notice> list = ndao.selectAll(conn);
		close(conn);
		return list;
	}

	//게시글 상세처리
	public Notice selectOne(String noticeNo, NursingHospital loginHospital) {
		 
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		Notice notice = ndao.selectOne(conn, noticeNo);
		close(conn);
		return notice;
	}

	
	//조회수 증가 위한 메소드
	public int updateReadCount(String noticeNo, NursingHospital loginHospital) {
		
				String userid = loginHospital.getNH_USERID();
				String userpwd = loginHospital.getNH_USERPWD();
				Connection conn = getConnection(userid, userpwd);
				int result = ndao.updateReadCount(conn, noticeNo);
				if (result > 0) {
					commit(conn);
				}else {
					rollback(conn);
				}
				close(conn);
				
				return result;
	}
	
	//조회수 보이기
	public int getListCount(NursingHospital loginHospital) {
		
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		int listCount = ndao.getListCount(conn);
		close(conn);
		return listCount;
	}
	
	//10개의 행만 조회처리 하는 서비스(페이징처리) user테이블만 아니라 tmts계정 도 가져와야댐
	public ArrayList<Notice> selectList(int startRow, int endRow, NursingHospital loginHospital) {
		
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Notice> list = ndao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}
	public ArrayList<Notice> selectList(int startRow, int endRow, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Notice> list = ndao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	//글쓰기
	public int insertNotice(Notice notice, NursingHospital loginHospital) {
		
		
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		
		int result = ndao.insertNotice(conn, notice);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}
	//top3처리 서비스 
	public ArrayList<Notice> selectTop3(String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Notice> list = ndao.selectTop3(conn);
		close(conn);
		return list;
	}

	
	//공지사항 수정
	public int updateNotice(Notice notice, NursingHospital loginHospital) {
		
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		int result = ndao.updateNotice(conn, notice);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	//공지사항 삭제
	public int deleteNotice(String noticeNo, NursingHospital loginHospital) {
		
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		int result = ndao.deleteNotice(conn, noticeNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//제목검색 서비스(관리자)
	public ArrayList<Notice> selectTitleSearch(String keyword, NursingHospital loginHospital) {
		
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Notice> list = ndao.selectTitleSearch(conn, keyword);
		close(conn);
		return list;
	}
	//작성자검색(관리자)
	public ArrayList<Notice> selectWriterSearch(String keyword, NursingHospital loginHospital) {

		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Notice> list = ndao.selectWriterSearch(conn, keyword);
		close(conn);
		return list;
	}
	//날짜검색(관리자)
	public ArrayList<Notice> selectDateSearch(Date from, Date to, NursingHospital loginHospital) {

		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Notice> list = ndao.selectDateSearch(conn, from, to);
		close(conn);
		return list;
	}

	public ArrayList<Notice> AdminselectTop3(String adminid, String adminpwd) {
		Connection conn = getConnection(adminid, adminpwd);
		ArrayList<Notice> list = ndao.selectTop3(conn);
		close(conn);
		return list;
	}
	


	public int getListCount(Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		int listCount = ndao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public int updateReadCount(String noticeNo, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		int result = ndao.updateReadCount(conn, noticeNo);
		if (result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public Notice selectOne(String noticeNo, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		Notice notice = ndao.selectOne(conn, noticeNo);
		close(conn);
		return notice;
		
	}

	public ArrayList<Notice> selectTitleSearch(String keyword, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Notice> list = ndao.selectTitleSearch(conn, keyword);
		close(conn);
		return list;
	}

	public ArrayList<Notice> selectWriterSearch(String keyword, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Notice> list = ndao.selectWriterSearch(conn, keyword);
		close(conn);
		return list;
	}

	public ArrayList<Notice> selectDateSearch(Date from, Date to, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Notice> list = ndao.selectDateSearch(conn, from, to);
		close(conn);
		return list;
	}
	
	
}









