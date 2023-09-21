package ERP.patient.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Employee.model.vo.Employee;
import ERP.patient.model.dao.PatientDao;
import ERP.patient.model.vo.Patient;
import Main.vo.NursingHospitalVo;

public class PatientService {
	PatientDao pdao = new PatientDao();
	
	public PatientService() {}

	public ArrayList<Patient> ListView(int startRow, int endRow, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Patient> list = pdao.ListView(conn);
		close(conn);
		return list;
	}

	public Patient DetailView(String hostId, String hostPwd, int patNum) {
		Connection conn = getConnection(hostId, hostPwd);
		Patient patient = pdao.DetailView(conn, patNum);
		close(conn);
		return patient;
	}

	public int insertPatient(String hostId, String hostPwd, Patient patient) {
		Connection conn = getConnection(hostId, hostPwd);
		int result = pdao.insertPatient(conn, patient);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int updatePatient(String hostId, String hostPwd, Patient patient) {
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
