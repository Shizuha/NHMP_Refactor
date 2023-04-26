package ERP.counselingLog.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Employee.model.vo.Employee;
import ERP.counselingLog.model.dao.CounselingLogDao;
import ERP.counselingLog.model.vo.CounselingLog;

public class CounselingLogService {
	private CounselingLogDao cldao = new CounselingLogDao();
	
	public CounselingLogService() {}
	
	public ArrayList<CounselingLog> ListView(String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<CounselingLog> list = cldao.ListView(conn);
		close(conn);
		return list;
	}
	
	public CounselingLog DetailView(String hostId, String hostPwd, int clNo) {
		Connection conn = getConnection(hostId, hostPwd);
		CounselingLog counselingLog = cldao.DetailView(conn, clNo);
		close(conn);
		return counselingLog;
	}
	
	public int insertCounselingLog(String hostId, String hostPwd, CounselingLog counselingLog) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = cldao.insertCounselingLog(conn, counselingLog);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateCounselingLog(String hostId, String hostPwd, CounselingLog counselingLog) {
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
