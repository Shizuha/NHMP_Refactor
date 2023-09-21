package ERP.Allowance.model.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ERP.Allowance.model.vo.Allowance;

public class AllowanceDao {

	public void updateAllowance() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Allowance> selectList(Connection conn) {
		ArrayList<Allowance> list = new ArrayList<Allowance>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from allowance order by allowance_no asc";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Allowance Allow = new Allowance();
				
				Allow.setALLOWANCE_CODE(rset.getString("allowance_code"));
				Allow.setALLOWANCE_NO(rset.getInt("allowance_no"));
				Allow.setALLOWANCE_NAME(rset.getString("allowance_name"));
				Allow.setALLOWANCE_FORMULA(rset.getString("allowance_formula"));
				Allow.setALLOWANCE_ETC(rset.getString("allowance_etc"));
				
				list.add(Allow);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
				
	}

	public void insertAllowance() {
		// TODO Auto-generated method stub
		
	}

	public int insertAllowance(Connection conn, Allowance awna) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "insert into allowance values(?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, awna.getALLOWANCE_CODE());
			pstmt.setInt(2, awna.getALLOWANCE_NO());
			pstmt.setString(3, awna.getALLOWANCE_NAME());
			pstmt.setString(4, awna.getALLOWANCE_FORMULA());
			pstmt.setString(5, awna.getALLOWANCE_ETC());
			
			result = pstmt.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectFormula(Connection conn, String acode) {
		String result = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select allowance_formula from allowance where allowance_code = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, acode);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int deleteAllowance(Connection conn, String code) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "delete from allowance where allowance_code = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, code);
			
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
