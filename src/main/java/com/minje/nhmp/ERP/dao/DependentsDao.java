package com.minje.nhmp.ERP.dao;

import static com.minje.nhmp.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.DependentsVo;

public class DependentsDao {
	
public int insertDependent(Connection conn,DependentsVo drr) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into DEPENDENTS values(DPEN_SEQ_CODE.NEXTVAL,?,?,?,?,?,?,?,?)";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, drr.getRship());
				pstmt.setString(2, drr.getfName());
				pstmt.setString(3, drr.getItNalfor());
				pstmt.setString(4, drr.getDibility());
				pstmt.setString(5, drr.gethIsc());
				pstmt.setString(6, drr.getiTogether());
				pstmt.setString(7, drr.getmChild());
				pstmt.setString(8, drr.getDepId());
					
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		
		
		return result;
		
	}

	public ArrayList<DependentsVo> selectOne(Connection conn, String empId) {
		ArrayList<DependentsVo> dpenList = new ArrayList<DependentsVo>();
		DependentsVo dpen = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from dependents where dep_id = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				dpen = new DependentsVo();
				
				dpen.setRship(rs.getString("RSHIP"));
				dpen.setfName(rs.getString("F_NAME"));
				dpen.setItNalfor(rs.getString("ITNAL_FOR"));
				dpen.setDibility(rs.getString("DIBILITY"));
				dpen.sethIsc(rs.getString("H_ISC"));
				dpen.setiTogether(rs.getString("I_TOGETHER"));
				dpen.setmChild(rs.getString("M_CHILD"));
				
				dpenList.add(dpen);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		
		
		
		return dpenList;
	}

	public int updateDependent(Connection conn, DependentsVo d, String fyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update dependents set RSHIP = ?, F_NAME = ? , ITNAL_FOR = ?, DIBILITY = ?, "
				+ "H_ISC = ?, I_TOGETHER = ?, M_CHILD = ?, DEP_ID = ? "
				+ "where DEP_ID = ? AND dpen_code = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, d.getRship());
				pstmt.setString(2, d.getfName());
				pstmt.setString(3, d.getItNalfor());
				pstmt.setString(4, d.getDibility());
				pstmt.setString(5, d.gethIsc());
				pstmt.setString(6, d.getiTogether());
				pstmt.setString(7, d.getmChild());
				pstmt.setString(8, d.getDepId());
				pstmt.setString(9, d.getDepId());
				pstmt.setString(10, fyNo);
					
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public String[] selectDepenCode(Connection conn, String empId, int dpenSize) {
		String[] dpenCode = new String[dpenSize];
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select dpen_code from dependents where dep_id = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				System.out.println("dao=" + i);
				dpenCode[i] = rs.getString("dpen_code");
				System.out.println("dao" + dpenCode[i]);
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return dpenCode;
	}
}
