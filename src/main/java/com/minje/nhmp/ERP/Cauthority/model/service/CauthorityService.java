package ERP.Cauthority.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Cauthority.model.dao.CauthorityDao;
import ERP.Cauthority.model.vo.Cauthority;
import Main.vo.NursingHospitalVo;

public class CauthorityService {
	private CauthorityDao CADao = new CauthorityDao();

	public ArrayList<Cauthority> selectList(NursingHospitalVo loginHospital) {
		String userid = loginHospital.getNH_USERID();
		String userpwd = loginHospital.getNH_USERPWD();
		Connection conn = getConnection(userid, userpwd);
		ArrayList<Cauthority> list = CADao.selectList(conn);
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
