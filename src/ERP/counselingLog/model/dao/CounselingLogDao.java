package ERP.counselingLog.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ERP.counselingLog.model.vo.CounselingLog;

public class CounselingLogDao {

	public ArrayList<CounselingLog> ListView(Connection conn) {
		ArrayList<CounselingLog> list = new ArrayList<CounselingLog>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from counselinglog";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				CounselingLog counselingLog = new CounselingLog();
				
				counselingLog.setClNo(rset.getInt("cl_no"));
				counselingLog.setClTitle(rset.getString("cl_title"));
				counselingLog.setClDate(rset.getDate("cl_date"));
				counselingLog.setClContents(rset.getString("cl_contents"));
				counselingLog.setClPhone(rset.getString("cl_phone"));
				counselingLog.setClComment(rset.getString("cl_comment"));
				counselingLog.setClPatName(rset.getString("cl_pat_name"));
				counselingLog.setClEmpName(rset.getString("cl_emp_name"));
				counselingLog.setClOriginalFileName(rset.getString("cl_original_filename"));
				counselingLog.setClRenameFileName(rset.getString("cl_rename_filename"));
				
				list.add(counselingLog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	public CounselingLog DetailView(Connection conn, int clNo) {
		CounselingLog counselingLog = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from counselinglog where cl_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, clNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				counselingLog = new CounselingLog();
				
				counselingLog.setClNo(rset.getInt("cl_no"));
				counselingLog.setClTitle(rset.getString("cl_title"));
				counselingLog.setClDate(rset.getDate("cl_date"));
				counselingLog.setClContents(rset.getString("cl_contents"));
				counselingLog.setClPhone(rset.getString("cl_phone"));
				counselingLog.setClComment(rset.getString("cl_comment"));
				counselingLog.setClPatName(rset.getString("cl_pat_name"));
				counselingLog.setClEmpName(rset.getString("cl_emp_name"));
				counselingLog.setClOriginalFileName(rset.getString("cl_original_filename"));
				counselingLog.setClRenameFileName(rset.getString("cl_rename_filename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return counselingLog;
	}

	public int insertCounselingLog(Connection conn, CounselingLog counselingLog) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into counselingLog values("
				+ "cl_seq.nextval, ?, sysdate, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, counselingLog.getClTitle());
			pstmt.setString(2, counselingLog.getClContents());
			pstmt.setString(3, counselingLog.getClPhone());
			pstmt.setString(4, counselingLog.getClComment());
			pstmt.setString(5, counselingLog.getClPatName());
			pstmt.setString(6, counselingLog.getClEmpName());
			pstmt.setString(7, counselingLog.getClOriginalFileName());
			pstmt.setString(8, counselingLog.getClRenameFileName());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateCounselingLog(Connection conn, CounselingLog counselingLog) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query ="update counselinglog set cl_title = ?, cl_date = ?,"
				+ "cl_contents = ?, cl_phone = ?, cl_comment = ?,"
				+ "cl_pat_name = ?, cl_emp_name = ?, cl_original_filename = ?,"
				+ "cl_rename_filename = ?"
				+ "where cl_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, counselingLog.getClTitle());
			pstmt.setDate(2, counselingLog.getClDate());
			pstmt.setString(3, counselingLog.getClContents());
			pstmt.setString(4, counselingLog.getClPhone());
			pstmt.setString(5, counselingLog.getClComment());
			pstmt.setString(6, counselingLog.getClPatName());
			pstmt.setString(7, counselingLog.getClEmpName());
			pstmt.setString(8, counselingLog.getClOriginalFileName());
			pstmt.setString(9, counselingLog.getClRenameFileName());
			pstmt.setInt(10, counselingLog.getClNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteConselingLog(Connection conn, int clNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from counselinglog where cl_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, clNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<CounselingLog> selectTitleSearch(Connection conn, String clTitle) {
		ArrayList<CounselingLog> list = new ArrayList<CounselingLog>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from counselinglog where cl_title like ? order by cl_no desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + clTitle + "%");
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				CounselingLog counselingLog = new CounselingLog();
				
				counselingLog.setClNo(rset.getInt("cl_no"));
				counselingLog.setClTitle(rset.getString("cl_title"));
				counselingLog.setClDate(rset.getDate("cl_date"));
				counselingLog.setClContents(rset.getString("cl_contents"));
				counselingLog.setClPhone(rset.getString("cl_phone"));
				counselingLog.setClComment(rset.getString("cl_comment"));
				counselingLog.setClPatName(rset.getString("cl_pat_name"));
				counselingLog.setClEmpName(rset.getString("cl_emp_name"));
				counselingLog.setClOriginalFileName(rset.getString("cl_original_filename"));
				counselingLog.setClRenameFileName(rset.getString("cl_rename_filename"));
				
				list.add(counselingLog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<CounselingLog> selectClEmpNameSearch(Connection conn, String clEmpName) {
		ArrayList<CounselingLog> list = new ArrayList<CounselingLog>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from counselinglog where cl_emp_name like ? order by cl_no desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + clEmpName + "%");
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				CounselingLog counselingLog = new CounselingLog();
				
				counselingLog.setClNo(rset.getInt("cl_no"));
				counselingLog.setClTitle(rset.getString("cl_title"));
				counselingLog.setClDate(rset.getDate("cl_date"));
				counselingLog.setClContents(rset.getString("cl_contents"));
				counselingLog.setClPhone(rset.getString("cl_phone"));
				counselingLog.setClComment(rset.getString("cl_comment"));
				counselingLog.setClPatName(rset.getString("cl_pat_name"));
				counselingLog.setClEmpName(rset.getString("cl_emp_name"));
				counselingLog.setClOriginalFileName(rset.getString("cl_original_filename"));
				counselingLog.setClRenameFileName(rset.getString("cl_rename_filename"));
				
				list.add(counselingLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from counselinglog";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
}
