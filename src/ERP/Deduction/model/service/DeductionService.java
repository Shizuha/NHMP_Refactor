package ERP.Deduction.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ERP.Deduction.model.dao.DeductionDao;
import ERP.Deduction.model.vo.Deduction;
import ERP.Employee.model.vo.Employee;
import Main.vo.NursingHospitalVo;

public class DeductionService {
	private DeductionDao DDao = new DeductionDao();

	public Deduction updateDeduction() {
		Connection conn = getConnection();
		DDao.updateDeduction();
		return null;
	}

	public ArrayList<Deduction> selectList(NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Deduction> list = DDao.selectList(conn);
		close(conn);
		return list;
	}

	public int insertDeduction(Deduction deduction, NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		int result = DDao.insertDeduction(conn, deduction);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}



	public String selectFormula(String dcode, NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		String Formula = DDao.selectFormula(conn, dcode);
		close(conn);
		return Formula;
	}

	public int deleteDeduction(String code, NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid,userpwd);
		int result = DDao.deleteDeduction(conn, code);
		close(conn);
		return result;
	}

	public ArrayList<Deduction> EselectList(String hostid, String hostpwd){
		
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Deduction> list = DDao.selectList(conn);
		close(conn);
		return list;
	}

	public String EselectFormula(String dcode, Employee loginEmployee) {
		Connection conn = getConnection(loginEmployee.getHostId(), loginEmployee.getHostPwd());
		String Formula = DDao.selectFormula(conn, dcode);
		close(conn);
		return Formula;
	}

	

}
