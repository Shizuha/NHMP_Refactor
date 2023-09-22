package com.minje.nhmp.ERP.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.DepartmentVo;
import com.minje.nhmp.ERP.dao.DepartmentDao;

import static com.minje.nhmp.common.JDBCTemplate.*;

public class DepartmentService {
	private DepartmentDao dDao = new DepartmentDao();
	
	public DepartmentService() {}
	
public ArrayList<DepartmentVo> selectAll(String hostId, String hostPwd) {
	Connection conn = getConnection(hostId, hostPwd);
	ArrayList<DepartmentVo> dList = dDao.selectAll(conn);
	close(conn);
	return dList;
}

public String selectDeptName(String deptCode, String hostId, String hostPwd) {
	Connection conn = getConnection(hostId, hostPwd);
	String deptName = dDao.selectDeptName(conn, deptCode);
	
	close(conn);
	return deptName;
}

public DepartmentVo selectDeptCode(String deptName, String hostId, String hostPwd) {
	Connection conn = getConnection(hostId, hostPwd);
	DepartmentVo dp = dDao.selectDeptCode(conn, deptName);
	
	close(conn);
	return dp;
}

	public DepartmentVo selectAuDeptName(String hostId, String hostPwd, String deptCode) {
	//권한부여관리에서 사원 부서이름 조회용 서비스
	Connection conn = getConnection(hostId, hostPwd);
	DepartmentVo dList = dDao.selectAuDeptName(conn, deptCode);
	close(conn);
	return dList;
}
}
