package ERP.Authority.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import ERP.Authority.model.dao.AuthorityDao;
import ERP.Authority.model.vo.Authority;
import static common.JDBCTemplate.*;

public class AuthorityService {
	
	private AuthorityDao aDao = new AuthorityDao();
	
	
	public AuthorityService() {}


	public ArrayList<Authority> selectAll(String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<Authority> auList = aDao.selectAll(conn);
		close(conn);
		return auList;
	}

}
