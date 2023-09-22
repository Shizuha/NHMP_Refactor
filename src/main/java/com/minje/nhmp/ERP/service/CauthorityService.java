package com.minje.nhmp.ERP.service;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.CauthorityDao;
import com.minje.nhmp.ERP.vo.CauthorityVo;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

public class CauthorityService {
	private CauthorityDao CADao = new CauthorityDao();

	public ArrayList<CauthorityVo> selectList(NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<CauthorityVo> list = CADao.selectList(conn);
		close(conn);
		return list;
	}

	public CauthorityService UpdateAuthorrity() {
		
		return null;
	}

	public CauthorityService DeleteAuthorrity() {
		// TODO Auto-generated method stub
		return null;
	}

	public CauthorityService InsertAuthorrity() {
		// TODO Auto-generated method stub
		return null;
	}

}
