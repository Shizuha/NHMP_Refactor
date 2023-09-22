package com.minje.nhmp.ERP.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.AuthorityVo;
import static com.minje.nhmp.common.JDBCTemplate.close;
public class AuthorityDao {

	public ArrayList<AuthorityVo> selectAll(Connection conn) {
		ArrayList<AuthorityVo> auList = new ArrayList<AuthorityVo>();
		
		Statement stmt = null;
		ResultSet rs = null;
		AuthorityVo au = null;
		String query = "select * from AUTHORITY ";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				au = new AuthorityVo();
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
