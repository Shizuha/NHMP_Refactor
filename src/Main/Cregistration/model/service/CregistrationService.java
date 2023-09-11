package Main.Cregistration.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import Main.Cregistration.model.dao.CregistrationDao;
import Main.vo.CregistrationVo;

public class CregistrationService {
	CregistrationDao CRTDao = new CregistrationDao(); 

	public CregistrationVo insertCRT() {
		Connection conn = getConnection();
		CRTDao.insertCRT();
		return null;
	}

}
