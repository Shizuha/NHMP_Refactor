package ERP.Dataroom.model.service;

import ERP.Employee.model.vo.Employee;
import ERP.Dataroom.model.vo.Dataroom;
import ERP.Dataroom.model.dao.DataroomDao;
import Main.NursingHospital.model.ov.NursingHospital;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.*;



public class DataroomService {
	
	private DataroomDao drdao = new DataroomDao();  //DI(의존성 주입)
	
	public DataroomService() {}
	//리스트화면카운트처리(관리자단)
	public int getListCount(NursingHospital loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		int listCount = drdao.getListCount(conn);
		close(conn);
		return listCount;
	}
	//리스트화면카운트처리(직원단)
	public int getListCount(Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		int listCount = drdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	
	
	//리스트보기(관리자)
	public ArrayList<Dataroom> selectList(int startRow, int endRow, NursingHospital loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Dataroom> list = drdao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}
	//리스트보기(직원)
	public ArrayList<Dataroom> selectList(int startRow, int endRow, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Dataroom> list = drdao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}
	
	//자료실 조회수 증가처리 서비스(관리자)
	public int updateReadCount(String dataroomNo, NursingHospital loginHospital) {
		
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		int result = drdao.updateReadCount(conn, dataroomNo);
		if (result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
	}
	
	//자료실 조회수 증가처리 서비스(직원)
	public int updateReadCount(String dataroomNo, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		int result = drdao.updateReadCount(conn, dataroomNo);
		if (result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	//자료실 게시글 상세처리(관리자)
	public Dataroom selectOne(String dataroomNo, NursingHospital loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		Dataroom dataroom = drdao.selectOne(conn, dataroomNo);
		close(conn);
		return dataroom;
	}
	//자료실 게시글 상세처리(직원)
	public Dataroom selectOne(String dataroomNo, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		Dataroom dataroom = drdao.selectOne(conn, dataroomNo);
		close(conn);
		return dataroom;
	}
	
	//자료실 글작성
	public int insertDataroom(Dataroom dataroom, NursingHospital loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		
		int result = drdao.insertNotice(conn, dataroom);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}
	public int updateBoard(Dataroom dataroom, NursingHospital loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		int result = drdao.updateNotice(conn, dataroom);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int deleteDataroom(String dataroomNo, NursingHospital loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		int result = drdao.deleteNotice(conn, dataroomNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//재목검색 서비스(관리자)
	public ArrayList<Dataroom> selectTitleSearch(String keyword, NursingHospital loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Dataroom> list = drdao.selectTitleSearch(conn, keyword);
		return list;
	}
	
	//작성자검색(관리자)
	public ArrayList<Dataroom> selectWriterSearch(String keyword, NursingHospital loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Dataroom> list = drdao.selectWriterSearch(conn, keyword);
		close(conn);
		return list;
	}
	public ArrayList<Dataroom> selectTitleSearch(String keyword, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Dataroom> list = drdao.selectTitleSearch(conn, keyword);
		return list;
	}
	public ArrayList<Dataroom> selectWriterSearch(String keyword, Employee loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Dataroom> list = drdao.selectWriterSearch(conn, keyword);
		close(conn);
		return list;
	}
	

	
	
	
	
	
	
	
	
	
}









