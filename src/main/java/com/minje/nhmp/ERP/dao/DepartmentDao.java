package com.minje.nhmp.ERP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.DepartmentVo;

import static com.minje.nhmp.common.JDBCTemplate.*;


public class DepartmentDao {
	public String selectDeptName(Connection conn, String deptCode) {
		String deptName = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select dept_name from department where dept_code = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deptCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				deptName = rs.getString("dept_name");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return deptName;
	}

	public ArrayList<DepartmentVo> selectAll(Connection conn) {
		ArrayList<DepartmentVo> dList = new ArrayList<DepartmentVo>();
		DepartmentVo dp = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select * from department";
		
		try {
			stmt = conn.createStatement();
			
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				dp = new DepartmentVo();
				
				dp.setDeptCode(rs.getString("dept_code"));
				dp.setDeptName(rs.getString("dept_name"));
				
				dList.add(dp);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		
		return dList;
	}

	public DepartmentVo selectDeptCode(Connection conn, String deptName) {
		
		DepartmentVo dp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select dept_code from department where dept_name like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + deptName + "%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dp = new DepartmentVo();
				
				dp.setDeptCode(rs.getString("dept_code"));
			
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return dp;
	}

	public DepartmentVo selectAuDeptName(Connection conn, String deptCode) {
		
		DepartmentVo dp = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select DEPT_NAME from department where DEPT_CODE = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deptCode);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dp = new DepartmentVo();
				dp.setDeptName(rs.getString("DEPT_NAME"));
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return dp;
	}
}
