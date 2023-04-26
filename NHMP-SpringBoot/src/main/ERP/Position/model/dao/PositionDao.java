package ERP.Position.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ERP.Position.model.vo.Position;
import ERP.Ward.model.vo.Ward;

import static common.JDBCTemplate.close;

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

	public ArrayList<Position> selectAll(Connection conn) {
		ArrayList<Position> pList = new ArrayList<Position>();
		Position po = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select * from positions";
		
		try {
			stmt = conn.createStatement();
			
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				po = new Position();
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

	public Position selectAuPositionName(Connection conn, String posCode) {
		Position posName = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select pos_name from POSITIONS where pos_code = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, posCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				posName = new Position();
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
