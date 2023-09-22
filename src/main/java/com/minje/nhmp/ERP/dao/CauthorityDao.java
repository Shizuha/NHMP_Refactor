package com.minje.nhmp.ERP.dao;

import static com.minje.nhmp.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.CauthorityVo;

public class CauthorityDao {

	public ArrayList<CauthorityVo> selectList(Connection conn) {
		ArrayList<CauthorityVo> list = new ArrayList<CauthorityVo>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from CAUTHORITY order by AUTHORITY_CODE asc";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				CauthorityVo autho = new CauthorityVo();
				
				autho.setAUTHORITY_CODE(rset.getString("AUTHORITY_CODE"));
				autho.setAUTHORITY_NAME(rset.getString("AUTHORITY_NAME"));
				autho.setAUTHORITY_ETC(rset.getString("AUTHORITY_ETC"));
				autho.setAUTHORITY_MENU(rset.getString("AUTHORITY_MENU"));
			
				list.add(autho);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

}
