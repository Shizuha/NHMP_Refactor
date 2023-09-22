package com.minje.nhmp.ERP.service;

import com.minje.nhmp.ERP.vo.EmployeeVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;
import com.minje.nhmp.ERP.vo.DataroomVo;
import com.minje.nhmp.ERP.dao.DataroomDao;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;



public class DataroomService {
	
	private DataroomDao drdao = new DataroomDao();  //DI(의존성 주입)
	
	public DataroomService() {}
	//리스트화면카운트처리(관리자단)
	public int getListCount(NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		int listCount = drdao.getListCount(conn);
		close(conn);
		return listCount;
	}
	//리스트화면카운트처리(직원단)
	public int getListCount(EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		int listCount = drdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	
	
	//리스트보기(관리자)
	public ArrayList<DataroomVo> selectList(int startRow, int endRow, NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<DataroomVo> list = drdao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}
	//리스트보기(직원)
	public ArrayList<DataroomVo> selectList(int startRow, int endRow, EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<DataroomVo> list = drdao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}
	
	//자료실 조회수 증가처리 서비스(관리자)
	public int updateReadCount(String dataroomNo, NursingHospitalVo loginHospital) {
		
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
	public int updateReadCount(String dataroomNo, EmployeeVo loginEmployee) {
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
	public DataroomVo selectOne(String dataroomNo, NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		DataroomVo dataroom = drdao.selectOne(conn, dataroomNo);
		close(conn);
		return dataroom;
	}
	//자료실 게시글 상세처리(직원)
	public DataroomVo selectOne(String dataroomNo, EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		DataroomVo dataroom = drdao.selectOne(conn, dataroomNo);
		close(conn);
		return dataroom;
	}
	
	//자료실 글작성
	public int insertDataroom(DataroomVo dataroom, NursingHospitalVo loginHospital) {
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
	public int updateBoard(DataroomVo dataroom, NursingHospitalVo loginHospital) {
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
	public int deleteDataroom(String dataroomNo, NursingHospitalVo loginHospital) {
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
	public ArrayList<DataroomVo> selectTitleSearch(String keyword, NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<DataroomVo> list = drdao.selectTitleSearch(conn, keyword);
		return list;
	}
	
	//작성자검색(관리자)
	public ArrayList<DataroomVo> selectWriterSearch(String keyword, NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<DataroomVo> list = drdao.selectWriterSearch(conn, keyword);
		close(conn);
		return list;
	}
	public ArrayList<DataroomVo> selectTitleSearch(String keyword, EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<DataroomVo> list = drdao.selectTitleSearch(conn, keyword);
		return list;
	}
	public ArrayList<DataroomVo> selectWriterSearch(String keyword, EmployeeVo loginEmployee) {
		String hostid = loginEmployee.getHostId();
		String hostpwd = loginEmployee.getHostPwd();
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<DataroomVo> list = drdao.selectWriterSearch(conn, keyword);
		close(conn);
		return list;
	}
	

	
	
	
	
	
	
	
	
	
}









