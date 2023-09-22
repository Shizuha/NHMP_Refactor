package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.AllowanceDao;
import com.minje.nhmp.ERP.vo.AllowanceVo;
import com.minje.nhmp.ERP.vo.EmployeeVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

public class AllowanceService {
	private AllowanceDao ADao = new AllowanceDao();
	
	public AllowanceVo updateAllowance() {
		ADao.updateAllowance();
		return null;
	}

	public ArrayList<AllowanceVo> selectList(NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<AllowanceVo> list = ADao.selectList(conn);
		close(conn);
		return list;
		
	}

	public AllowanceVo insertAllowance() {
		ADao.insertAllowance();
		return null;
	}

	public int insertAllowance(AllowanceVo awna, NursingHospitalVo loginHospital) {
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

	public String selectFormula(String acode, NursingHospitalVo loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		String Formula = ADao.selectFormula(conn, acode);
		close(conn);
		return Formula;
	}

	public int deleteAllowance(String code, NursingHospitalVo loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		int result = ADao.deleteAllowance(conn, code);
		close(conn);
		return result;
	}

	public ArrayList<AllowanceVo> EselectList(String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<AllowanceVo> list = ADao.selectList(conn);
		close(conn);
		return list;
	}

	public String EselectFormula(String acode, EmployeeVo loginEmployee) {
		Connection conn = getConnection(loginEmployee.getHostId(), loginEmployee.getHostPwd());
		String Formula = ADao.selectFormula(conn, acode);
		close(conn);
		return Formula;
	}
}	
