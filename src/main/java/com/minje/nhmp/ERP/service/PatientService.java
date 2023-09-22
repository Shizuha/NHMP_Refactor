package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.close;
import static com.minje.nhmp.common.JDBCTemplate.commit;
import static com.minje.nhmp.common.JDBCTemplate.getConnection;
import static com.minje.nhmp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.PatientDao;
import com.minje.nhmp.ERP.vo.PatientVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

public class PatientService {
	PatientDao pdao = new PatientDao();
	
	public PatientService() {}

	public ArrayList<PatientVo> ListView(int startRow, int endRow, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<PatientVo> list = pdao.ListView(conn);
		close(conn);
		return list;
	}

	public PatientVo DetailView(String hostId, String hostPwd, int patNum) {
		Connection conn = getConnection(hostId, hostPwd);
		PatientVo patient = pdao.DetailView(conn, patNum);
		close(conn);
		return patient;
	}

	public int insertPatient(String hostId, String hostPwd, PatientVo patient) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = pdao.insertPatient(conn, patient);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updatePatient(String hostId, String hostPwd, PatientVo patient) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = pdao.updatePatient(conn, patient);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int deletePatient(String hostId, String hostPwd, int patNum) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = pdao.deletePatient(conn, patNum);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public String PatientCount(NursingHospitalVo loginHospital) {
		// 환자 총명수 카운트 
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		String result = pdao.PatientCount(conn);
		return result;
	}
}
