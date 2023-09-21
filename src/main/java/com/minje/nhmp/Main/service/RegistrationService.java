package com.minje.nhmp.Main.service;

// import static com.minje.nhmp.common.JDBCTemplate.*;

// import java.sql.Connection;

import com.minje.nhmp.Main.dao.RegistrationDao;
import com.minje.nhmp.Main.service.RegistrationService;
import com.minje.nhmp.Main.vo.RegistrationVo;

public class RegistrationService {
	RegistrationDao CRTDao = new RegistrationDao(); 

	public RegistrationVo insertCRT() {
//		Connection conn = getConnection();
		CRTDao.insertCRT();
		return null;
	}
}
