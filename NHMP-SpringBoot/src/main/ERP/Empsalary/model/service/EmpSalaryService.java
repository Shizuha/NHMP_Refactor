package ERP.Empsalary.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import ERP.Empsalary.model.dao.EmpSalaryDao;
import ERP.Empsalary.model.vo.EmpSalary;

public class EmpSalaryService {
	
public EmpSalaryService() {}
	
	
	private EmpSalaryDao eDao = new EmpSalaryDao();


	public int insertEmpSalary(EmpSalary empSal,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.insertEmpSalary(conn, empSal);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}


	public EmpSalary selectOne(String empId,String hostId,String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		EmpSalary empSal = eDao.selectOne(conn, empId);
		close(conn);
		return empSal;
	}


	public int updateEmpSalary(EmpSalary empSal,String hostId,String hostPwd) {
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
