package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.close;
import static com.minje.nhmp.common.JDBCTemplate.commit;
import static com.minje.nhmp.common.JDBCTemplate.getConnection;
import static com.minje.nhmp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.DeductionDao;
import com.minje.nhmp.ERP.vo.DeductionVo;
import com.minje.nhmp.ERP.vo.EmployeeVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

public class DeductionService {
	private DeductionDao DDao = new DeductionDao();

	public DeductionVo updateDeduction() {
		DDao.updateDeduction();
		return null;
	}

	public ArrayList<DeductionVo> selectList(NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<DeductionVo> list = DDao.selectList(conn);
		close(conn);
		return list;
	}

	public int insertDeduction(DeductionVo deduction, NursingHospitalVo loginHospital) {
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

	public ArrayList<DeductionVo> EselectList(String hostid, String hostpwd){
		
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<DeductionVo> list = DDao.selectList(conn);
		close(conn);
		return list;
	}

	public String EselectFormula(String dcode, EmployeeVo loginEmployee) {
		Connection conn = getConnection(loginEmployee.getHostId(), loginEmployee.getHostPwd());
		String Formula = DDao.selectFormula(conn, dcode);
		close(conn);
		return Formula;
	}

	

}
