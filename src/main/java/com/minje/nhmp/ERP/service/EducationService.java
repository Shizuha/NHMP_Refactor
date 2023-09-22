package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.EducationDao;
import com.minje.nhmp.ERP.vo.EducationVo;

public class EducationService {
	
private EducationDao eDao = new EducationDao();
	
	
	public EducationService () {}


	public int insertEdu(EducationVo e, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.insertEdu(conn, e);
		if(result > 0) 
			commit(conn);
		else
			rollback(conn);
		return result;
	}


	public ArrayList<EducationVo> selectList(String empId,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<EducationVo> eduList = eDao.selectList(conn, empId);
		close(conn);
		return eduList;
	}


	public int updateEdu(EducationVo e, String eduCode,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.updateEdu(conn, e, eduCode);
		if(result > 0) 
			commit(conn);
		else
			rollback(conn);
		return result;
	}


	public String[] selectEduCode(String empId, int length,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		String[] dCode = eDao. selectEduCode(conn, empId, length);
		close(conn);
		return dCode;
	}
}
