package com.minje.nhmp.ERP.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.AuthorityDao;
import com.minje.nhmp.ERP.vo.AuthorityVo;
import static com.minje.nhmp.common.JDBCTemplate.*;

public class AuthorityService {
	
	private AuthorityDao aDao = new AuthorityDao();
	
	
	public AuthorityService() {}


	public ArrayList<AuthorityVo> selectAll(String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<AuthorityVo> auList = aDao.selectAll(conn);
		close(conn);
		return auList;
	}

}
