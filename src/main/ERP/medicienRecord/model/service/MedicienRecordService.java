package ERP.medicienRecord.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Employee.model.vo.Employee;
import ERP.medicienRecord.model.dao.MedicienRecordDao;
import ERP.medicienRecord.model.vo.MedicienRecord;

import static common.JDBCTemplate.*;

public class MedicienRecordService {
	private MedicienRecordDao mrdao = new MedicienRecordDao();
	
	public ArrayList<MedicienRecord> ListView(int startRow, int endRow, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<MedicienRecord> list = mrdao.ListView(conn);
		close(conn);
		return list;
	}
	
	public MedicienRecord DetailView(String hostId, String hostPwd, int mrNo) {
		Connection conn = getConnection(hostId, hostPwd);
		MedicienRecord medicienRecord = mrdao.DetailView(conn, mrNo);
		close(conn);
		return medicienRecord;
	}
	
	public int insertMedicienRecord(String hostId, String hostPwd, MedicienRecord medicienRecord) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = mrdao.insertMedicienRecord(conn, medicienRecord);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateMedicienRecord(String hostId, String hostPwd, MedicienRecord medicienRecord) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = mrdao.updateMedicienRecord(conn, medicienRecord);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteMedicienRecord(String hostId, String hostPwd, int mrNo) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = mrdao.deleteMedicienRecord(conn, mrNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int getListCount(String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		int listCount = mrdao.getListCount(conn);
		close(conn);
		return listCount;
	}
}
