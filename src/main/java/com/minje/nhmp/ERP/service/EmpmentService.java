package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;

import com.minje.nhmp.ERP.dao.EmpmentDao;


public class EmpmentService {
	
	private EmpmentDao emDao = new EmpmentDao();
	
	public EmpmentService () {}

	public String selectEmpmentName(String empmentCode,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		String empmentName = emDao.selectEmpId(conn, empmentCode);
		close(conn);
		return empmentName;
	}
}
