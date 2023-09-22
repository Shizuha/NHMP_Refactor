package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.CounselingLogDao;
import com.minje.nhmp.ERP.vo.CounselingLogVo;

public class CounselingLogService {
	private CounselingLogDao cldao = new CounselingLogDao();
	
	public CounselingLogService() {}
	
	public ArrayList<CounselingLogVo> ListView(String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<CounselingLogVo> list = cldao.ListView(conn);
		close(conn);
		return list;
	}
	
	public CounselingLogVo DetailView(String hostId, String hostPwd, int clNo) {
		Connection conn = getConnection(hostId, hostPwd);
		CounselingLogVo counselingLog = cldao.DetailView(conn, clNo);
		close(conn);
		return counselingLog;
	}
	
	public int insertCounselingLog(String hostId, String hostPwd, CounselingLogVo counselingLog) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = cldao.insertCounselingLog(conn, counselingLog);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateCounselingLog(String hostId, String hostPwd, CounselingLogVo counselingLog) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = cldao.updateCounselingLog(conn, counselingLog);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteCounselingLog(String hostId, String hostPwd, int clNo) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = cldao.deleteConselingLog(conn, clNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	public int getListCount(String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int listCount = cldao.getListCount(conn);
		close(conn);
		return listCount;
	}
}
