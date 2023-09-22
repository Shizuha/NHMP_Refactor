package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.close;
import static com.minje.nhmp.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.PositionDao;
import com.minje.nhmp.ERP.vo.PositionVo;

public class PositionService {
private PositionDao pDao = new PositionDao();
	
	public PositionService() {}

	public String selectPositionName(String posCode,String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		String posName = pDao.selectPositionName(conn, posCode);
		
		close(conn);
		return posName;
	}

	public ArrayList<PositionVo> selectAll(String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<PositionVo> pList = pDao.selectAll(conn);
		close(conn);
		return pList;
	}

	public PositionVo selectAuPositionName(String hostId, String hostPwd, String posCode) {
		Connection conn = getConnection(hostId, hostPwd);
		PositionVo posName = pDao.selectAuPositionName(conn, posCode);
		
		close(conn);
		return posName;
	}
}
