package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.DependentsDao;
import com.minje.nhmp.ERP.vo.DependentsVo;

public class DependentsService {
	
private DependentsDao dDao = new DependentsDao();
	
	
	public DependentsService() {}


	public int insertDependent(DependentsVo drr, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = dDao.insertDependent(conn, drr);
		if(result > 0) 
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}


	public ArrayList<DependentsVo> selectOne(String empId, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<DependentsVo> dpenList = dDao.selectOne(conn, empId);
		close(conn);
		return dpenList;
	}


	public int updateDependent(DependentsVo d, String fyno, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = dDao.updateDependent(conn, d, fyno);
		if(result > 0) 
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}


	public String[] selectDepenCode(String empId, int dpenSize, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		String[] result = dDao.selectDepenCode(conn, empId, dpenSize);
		
		close(conn);
		return result;
	}
}
