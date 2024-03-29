package com.minje.nhmp.ERP.dao;

import static com.minje.nhmp.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.WardVo;

public class WardDao {
	
	public String selectTeamName(Connection conn, String wardCode) {
		String wardName = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select ward_name from ward where ward_code = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, wardCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				wardName = rs.getString("ward_name");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return wardName;
	}

	public ArrayList<WardVo> selectAll(Connection conn) {
		ArrayList<WardVo> wList = new ArrayList<WardVo>();
		WardVo ward = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select * from ward";
		
		try {
			stmt = conn.createStatement();
			
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				ward = new WardVo();
				ward.setWardCode(rs.getString("ward_code"));
				ward.setWardName(rs.getString("ward_name"));
				
				wList.add(ward);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		
		return wList;
	}

	public String PatientCount(Connection conn) {
		String result = "0";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select COUNT(*) from ward";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString(1);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public WardVo selectAuDeptName(Connection conn, String wardCode) {
		WardVo wardName = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from ward where ward_code = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, wardCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				wardName = new WardVo(); 
				
				wardName.setWardName(rs.getString("ward_name"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return wardName;
	}
}
