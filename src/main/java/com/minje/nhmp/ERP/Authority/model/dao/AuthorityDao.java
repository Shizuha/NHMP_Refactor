package ERP.Authority.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ERP.Authority.model.vo.Authority;
import static common.JDBCTemplate.close;
public class AuthorityDao {

	public ArrayList<Authority> selectAll(Connection conn) {
		ArrayList<Authority> auList = new ArrayList<Authority>();
		
		Statement stmt = null;
		ResultSet rs = null;
		Authority au = null;
		String query = "select * from AUTHORITY ";
		
		try {
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				au = new Authority();
				
				au.setAuthorityCode(rs.getString("AUTHORITY_CODE"));
				au.setAuthorityName(rs.getString("AUTHORITY_NAME"));
				au.setAuthorityEtc(rs.getString("AUTHORITY_ETC"));
				
				auList.add(au);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		
		
		return auList;
	}

}
