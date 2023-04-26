package ERP.Ward.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Ward.model.dao.WardDao;
import ERP.Ward.model.vo.Ward;
import Main.NursingHospital.model.ov.NursingHospital;

import static common.JDBCTemplate.*;
public class WardService {
	
	private WardDao wDao = new WardDao();
	
	public WardService () {}
	

	public String selectWardName(String wardCode) {
		Connection conn = getConnection();
		String wardName = wDao.selectTeamName(conn, wardCode);
		
		close(conn);
		return wardName;
	}


	public ArrayList<Ward> selectAll() {
		Connection conn = getConnection();
		ArrayList<Ward> wList = wDao.selectAll(conn);
		close(conn);
		
		return wList;
	}


	public String WardCount(NursingHospital loginHospital) {
		// 환자 총명수 카운트 
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		String result = wDao.PatientCount(conn);
		return result;
	}


	public Ward selectAuWardName(String hostId, String hostPwd, String wardCode) {
		//권한부여사용자선택창에서 사원클릭시 병동조회해오는 서비스
		Connection conn = getConnection();
		Ward wd = wDao.selectAuDeptName(conn, wardCode);
		close(conn);
		
		return wd;
	}
}
