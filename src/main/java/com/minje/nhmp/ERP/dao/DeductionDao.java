package com.minje.nhmp.ERP.dao;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.DeductionVo;

public class DeductionDao {

	public ArrayList<DeductionVo> selectList(Connection conn) {
		ArrayList<DeductionVo> list = new ArrayList<DeductionVo>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from deduction order by deduction_no asc";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				DeductionVo DDuc = new DeductionVo();
				
				DDuc.setDEDUCTION_CODE(rset.getString("deduction_code"));
				DDuc.setDEDUCTION_NO(rset.getInt("deduction_no"));
				DDuc.setDEDUCTION_NAME(rset.getString("deduction_name"));
				DDuc.setDEDUCTION_FORMULA(rset.getString("deduction_formula"));
				DDuc.setDEDUCTION_ETC(rset.getString("deduction_etc"));
				
				list.add(DDuc);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
		
	}

	public int insertDeduction(Connection conn, DeductionVo deduction) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "insert into deduction values(?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, deduction.getDEDUCTION_CODE());
			pstmt.setInt(2, deduction.getDEDUCTION_NO());
			pstmt.setString(3, deduction.getDEDUCTION_NAME());
			pstmt.setString(4, deduction.getDEDUCTION_FORMULA());
			pstmt.setString(5, deduction.getDEDUCTION_ETC());
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}


	public String selectFormula(Connection conn, String dcode) {
		String result = "";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select deduction_formula from deduction where deduction_code = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dcode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString(1);
				System.out.println("dao : "+result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int deleteDeduction(Connection conn, String code) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "delete from deduction where deduction_code = ?";
		
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

	public void updateDeduction() {
		
		
	}



}
