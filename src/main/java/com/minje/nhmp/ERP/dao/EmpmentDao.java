package com.minje.nhmp.ERP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.minje.nhmp.common.JDBCTemplate.close;

public class EmpmentDao {
	
	public String selectEmpId(Connection conn, String empmentCode) {
		String empmentName = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select empment_name from empment where empment_code = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empmentCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				empmentName = rs.getString("empment_name");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return empmentName;
	}
}
