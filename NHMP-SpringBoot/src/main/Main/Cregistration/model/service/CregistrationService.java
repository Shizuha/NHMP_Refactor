package Main.Cregistration.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import Main.Cregistration.model.dao.CregistrationDao;
import Main.Cregistration.model.vo.Cregistration;

public class CregistrationService {
	CregistrationDao CRTDao = new CregistrationDao(); 

	public Cregistration insertCRT() {
		Connection conn = getConnection();
		CRTDao.insertCRT();
		return null;
	}

}
