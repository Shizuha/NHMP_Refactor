package ERP.Allowance.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Allowance.model.dao.AllowanceDao;
import ERP.Allowance.model.vo.Allowance;
import ERP.Employee.model.vo.Employee;
import Main.NursingHospital.model.ov.NursingHospital;

public class AllowanceService {
	private AllowanceDao ADao = new AllowanceDao();
	
	public Allowance updateAllowance() {
		Connection conn = getConnection();
		ADao.updateAllowance();
		return null;
	}

	public ArrayList<Allowance> selectList(NursingHospital loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Allowance> list = ADao.selectList(conn);
		close(conn);
		return list;
		
	}

	public Allowance insertAllowance() {
		ADao.insertAllowance();
		return null;
	}

	public int insertAllowance(Allowance awna, NursingHospital loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(),loginHospital.getNH_USERPWD());
		int result = ADao.insertAllowance(conn, awna);
		close(conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public String selectFormula(String acode, NursingHospital loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		String Formula = ADao.selectFormula(conn, acode);
		close(conn);
		return Formula;
	}

	public int deleteAllowance(String code, NursingHospital loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		int result = ADao.deleteAllowance(conn, code);
		close(conn);
		return result;
	}

	public ArrayList<Allowance> EselectList(String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Allowance> list = ADao.selectList(conn);
		close(conn);
		return list;
	}

	public String EselectFormula(String acode, Employee loginEmployee) {
		Connection conn = getConnection(loginEmployee.getHostId(), loginEmployee.getHostPwd());
		String Formula = ADao.selectFormula(conn, acode);
		close(conn);
		return Formula;
	}
}	
