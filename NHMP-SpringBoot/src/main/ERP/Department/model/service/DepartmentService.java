package ERP.Department.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Department.model.dao.DepartmentDao;
import ERP.Department.model.vo.Department;
import static common.JDBCTemplate.*;

public class DepartmentService {
	private DepartmentDao dDao = new DepartmentDao();
	
	public DepartmentService() {}
	
public ArrayList<Department> selectAll(String hostId, String hostPwd) {
	Connection conn = getConnection(hostId, hostPwd);
	ArrayList<Department> dList = dDao.selectAll(conn);
	close(conn);
	return dList;
}

public String selectDeptName(String deptCode, String hostId, String hostPwd) {
	Connection conn = getConnection(hostId, hostPwd);
	String deptName = dDao.selectDeptName(conn, deptCode);
	
	close(conn);
	return deptName;
}

public Department selectDeptCode(String deptName, String hostId, String hostPwd) {
	Connection conn = getConnection(hostId, hostPwd);
	Department dp = dDao.selectDeptCode(conn, deptName);
	
	close(conn);
	return dp;
}

	public Department selectAuDeptName(String hostId, String hostPwd, String deptCode) {
	//권한부여관리에서 사원 부서이름 조회용 서비스
	Connection conn = getConnection(hostId, hostPwd);
	Department dList = dDao.selectAuDeptName(conn, deptCode);
	close(conn);
	return dList;
}
}
