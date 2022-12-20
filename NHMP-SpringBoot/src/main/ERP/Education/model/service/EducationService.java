package ERP.Education.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Education.model.dao.EducationDao;
import ERP.Education.model.vo.Education;
import static common.JDBCTemplate.*;

public class EducationService {
	
private EducationDao eDao = new EducationDao();
	
	
	public EducationService () {}


	public int insertEdu(Education e, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = eDao.insertEdu(conn, e);
		if(result > 0) 
			commit(conn);
		else
			rollback(conn);
		return result;
	}


	public ArrayList<Education> selectList(String empId,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Education> eduList = eDao.selectList(conn, empId);
		close(conn);
		return eduList;
	}


	public int updateEdu(Education e, String eduCode,String hostId, String hostPwd) {
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
