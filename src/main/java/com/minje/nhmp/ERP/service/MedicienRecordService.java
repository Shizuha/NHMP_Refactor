package com.minje.nhmp.ERP.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.MedicienRecordDao;
import com.minje.nhmp.ERP.vo.MedicienRecordVo;

import static com.minje.nhmp.common.JDBCTemplate.*;

public class MedicienRecordService {
	
	private MedicienRecordDao mrdao = new MedicienRecordDao();
	
	public ArrayList<MedicienRecordVo> ListView(int startRow, int endRow, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<MedicienRecordVo> list = mrdao.ListView(conn);
		close(conn);
		return list;
	}
	
	public MedicienRecordVo DetailView(String hostId, String hostPwd, int mrNo) {
		Connection conn = getConnection(hostId, hostPwd);
		MedicienRecordVo medicienRecord = mrdao.DetailView(conn, mrNo);
		close(conn);
		return medicienRecord;
	}
	
	public int insertMedicienRecord(String hostId, String hostPwd, MedicienRecordVo medicienRecord) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = mrdao.insertMedicienRecord(conn, medicienRecord);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateMedicienRecord(String hostId, String hostPwd, MedicienRecordVo medicienRecord) {
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
