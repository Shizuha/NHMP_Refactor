package ERP.Dependents.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import static common.JDBCTemplate.*;
import ERP.Dependents.model.dao.DependentsDao;
import ERP.Dependents.model.vo.Dependents;

public class DependentsService {
	
private DependentsDao dDao = new DependentsDao();
	
	
	public DependentsService() {}


	public int insertDependent(Dependents drr, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = dDao.insertDependent(conn, drr);
		if(result > 0) 
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}


	public ArrayList<Dependents> selectOne(String empId, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Dependents> dpenList = dDao.selectOne(conn, empId);
		close(conn);
		return dpenList;
	}


	public int updateDependent(Dependents d, String fyno, String hostId, String hostPwd) {
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
