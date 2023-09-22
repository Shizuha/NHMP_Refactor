package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.close;
import static com.minje.nhmp.common.JDBCTemplate.commit;
import static com.minje.nhmp.common.JDBCTemplate.getConnection;
import static com.minje.nhmp.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.minje.nhmp.ERP.dao.EmpSalaryDao;
import com.minje.nhmp.ERP.vo.EmpSalaryVo;

public class EmpSalaryService {
	
public EmpSalaryService() {}
	
	
	private EmpSalaryDao eDao = new EmpSalaryDao();


	public int insertEmpSalary(EmpSalaryVo empSal,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.insertEmpSalary(conn, empSal);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}


	public EmpSalaryVo selectOne(String empId,String hostId,String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		EmpSalaryVo empSal = eDao.selectOne(conn, empId);
		close(conn);
		return empSal;
	}


	public int updateEmpSalary(EmpSalaryVo empSal,String hostId,String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.updateEmpSalary(conn, empSal);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
}
