package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.close;
import static com.minje.nhmp.common.JDBCTemplate.commit;
import static com.minje.nhmp.common.JDBCTemplate.getConnection;
import static com.minje.nhmp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.EmployeeVo;
import com.minje.nhmp.ERP.dao.ErpNoticeDao;
import com.minje.nhmp.ERP.vo.ErpNoticeVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;


public class ErpNoticeService {
	private ErpNoticeDao ndao = new ErpNoticeDao();  //DI(의존성 주입)
	
	public ErpNoticeService() {}
	
	//공지사항 전체보기
	public ArrayList<ErpNoticeVo> selectAll(NursingHospitalVo loginHospital){
		//아이디랑 비밀번호로 게시판 구분
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectAll(conn);
		close(conn);
		return list;
	}

	//게시글 상세처리
	public ErpNoticeVo selectOne(String noticeNo, NursingHospitalVo loginHospital) {
		 
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ErpNoticeVo notice = ndao.selectOne(conn, noticeNo);
		close(conn);
		return notice;
	}

	
	//조회수 증가 위한 메소드
	public int updateReadCount(String noticeNo, NursingHospitalVo loginHospital) {
		
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
	public int getListCount(NursingHospitalVo loginHospital) {
		
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		int listCount = ndao.getListCount(conn);
		close(conn);
		return listCount;
	}
	
	//10개의 행만 조회처리 하는 서비스(페이징처리) user테이블만 아니라 tmts계정 도 가져와야댐
	public ArrayList<ErpNoticeVo> selectList(int startRow, int endRow, NursingHospitalVo loginHospital) {
		
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}
	public ArrayList<ErpNoticeVo> selectList(int startRow, int endRow, EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	//글쓰기
	public int insertNotice(ErpNoticeVo notice, NursingHospitalVo loginHospital) {
		
		
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
	public ArrayList<ErpNoticeVo> selectTop3(String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectTop3(conn);
		close(conn);
		return list;
	}

	
	//공지사항 수정
	public int updateNotice(ErpNoticeVo notice, NursingHospitalVo loginHospital) {
		
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
	public int deleteNotice(String noticeNo, NursingHospitalVo loginHospital) {
		
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
	public ArrayList<ErpNoticeVo> selectTitleSearch(String keyword, NursingHospitalVo loginHospital) {
		
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectTitleSearch(conn, keyword);
		close(conn);
		return list;
	}
	//작성자검색(관리자)
	public ArrayList<ErpNoticeVo> selectWriterSearch(String keyword, NursingHospitalVo loginHospital) {

		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectWriterSearch(conn, keyword);
		close(conn);
		return list;
	}
	//날짜검색(관리자)
	public ArrayList<ErpNoticeVo> selectDateSearch(Date from, Date to, NursingHospitalVo loginHospital) {

		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectDateSearch(conn, from, to);
		close(conn);
		return list;
	}

	public ArrayList<ErpNoticeVo> AdminselectTop3(String adminid, String adminpwd) {
		Connection conn = getConnection(adminid, adminpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectTop3(conn);
		close(conn);
		return list;
	}
	


	public int getListCount(EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		int listCount = ndao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public int updateReadCount(String noticeNo, EmployeeVo loginEmployee) {
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

	public ErpNoticeVo selectOne(String noticeNo, EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ErpNoticeVo notice = ndao.selectOne(conn, noticeNo);
		close(conn);
		return notice;
		
	}

	public ArrayList<ErpNoticeVo> selectTitleSearch(String keyword, EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectTitleSearch(conn, keyword);
		close(conn);
		return list;
	}

	public ArrayList<ErpNoticeVo> selectWriterSearch(String keyword, EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectWriterSearch(conn, keyword);
		close(conn);
		return list;
	}

	public ArrayList<ErpNoticeVo> selectDateSearch(Date from, Date to, EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<ErpNoticeVo> list = ndao.selectDateSearch(conn, from, to);
		close(conn);
		return list;
	}
}









