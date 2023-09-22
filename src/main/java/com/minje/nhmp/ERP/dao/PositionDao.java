package com.minje.nhmp.ERP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.PositionVo;

import static com.minje.nhmp.common.JDBCTemplate.close;

public class PositionDao {
	public String selectPositionName(Connection conn, String posCode) {
		String posName = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select pos_name from POSITIONS where pos_code = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, posCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				posName = rs.getString("pos_name");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return posName;
	}

	public ArrayList<PositionVo> selectAll(Connection conn) {
		ArrayList<PositionVo> pList = new ArrayList<PositionVo>();
		PositionVo po = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select * from positions";
		
		try {
			stmt = conn.createStatement();
			
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				po = new PositionVo();
				po.setPosCode(rs.getString("pos_code"));
				po.setPosName(rs.getString("pos_name"));
				
				
				pList.add(po);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		
		return pList;
	}

	public PositionVo selectAuPositionName(Connection conn, String posCode) {
		PositionVo posName = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select pos_name from POSITIONS where pos_code = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, posCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				posName = new PositionVo();
				posName.setPosName(rs.getString("pos_name"));
			}
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return posName;
	}
}
