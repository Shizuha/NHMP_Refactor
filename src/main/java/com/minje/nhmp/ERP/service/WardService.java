package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.WardVo;
import com.minje.nhmp.ERP.dao.WardDao;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

public class WardService {
	
	private WardDao wDao = new WardDao();
	
	public WardService () {}
	

	public String selectWardName(String wardCode) {
		Connection conn = getConnection();
		String wardName = wDao.selectTeamName(conn, wardCode);
		
		close(conn);
		return wardName;
	}


	public ArrayList<WardVo> selectAll() {
		Connection conn = getConnection();
		ArrayList<WardVo> wList = wDao.selectAll(conn);
		close(conn);
		
		return wList;
	}


	public String WardCount(NursingHospitalVo loginHospital) {
		// 환자 총명수 카운트 
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		String result = wDao.PatientCount(conn);
		return result;
	}


	public WardVo selectAuWardName(String hostId, String hostPwd, String wardCode) {
		//권한부여사용자선택창에서 사원클릭시 병동조회해오는 서비스
		Connection conn = getConnection();
		WardVo wd = wDao.selectAuDeptName(conn, wardCode);
		close(conn);
		
		return wd;
	}
}
