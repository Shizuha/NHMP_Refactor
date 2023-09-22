package com.minje.nhmp.ERP.dao;

import static com.minje.nhmp.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.MedicienRecordVo;

public class MedicienRecordDao {

	public ArrayList<MedicienRecordVo> ListView(Connection conn) {
		ArrayList<MedicienRecordVo> list = new ArrayList<MedicienRecordVo>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from medicienrecord";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				MedicienRecordVo medicienRecord = new MedicienRecordVo();
				
				medicienRecord.setMrNo(rset.getInt("mr_no"));
				medicienRecord.setMrDate(rset.getDate("mr_date"));
				medicienRecord.setMrState(rset.getString("mr_state"));
				medicienRecord.setMrName(rset.getString("mr_name"));
				medicienRecord.setMrTime(rset.getString("mr_time"));
				medicienRecord.setMrMany(rset.getString("mr_many"));
				medicienRecord.setMrComment(rset.getString("mr_comment"));
				medicienRecord.setMrPatName(rset.getString("mr_pat_name"));
				medicienRecord.setMrEmpName(rset.getString("mr_emp_name"));
				
				list.add(medicienRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	public MedicienRecordVo DetailView(Connection conn, int mrNo) {
		MedicienRecordVo medicienRecord = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from medicienrecord where mr_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				medicienRecord = new MedicienRecordVo();
				
				medicienRecord.setMrNo(rset.getInt("mr_no"));
				medicienRecord.setMrDate(rset.getDate("mr_date"));
				medicienRecord.setMrState(rset.getString("mr_state"));
				medicienRecord.setMrName(rset.getString("mr_name"));
				medicienRecord.setMrTime(rset.getString("mr_time"));
				medicienRecord.setMrMany(rset.getString("mr_many"));
				medicienRecord.setMrComment(rset.getString("mr_comment"));
				medicienRecord.setMrPatName(rset.getString("mr_pat_name"));
				medicienRecord.setMrEmpName(rset.getString("mr_emp_name"));
				medicienRecord.setMrOriginalFileName(rset.getString("mr_original_filename"));
				medicienRecord.setMrRenameFileName(rset.getString("mr_rename_filename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return medicienRecord;
	}

	public int insertMedicienRecord(Connection conn, MedicienRecordVo medicienRecord) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into medicienrecord values("
				+ "mr_seq.nextval, sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, medicienRecord.getMrState());
			pstmt.setString(2, medicienRecord.getMrName());
			pstmt.setString(3, medicienRecord.getMrTime());
			pstmt.setString(4, medicienRecord.getMrMany());
			pstmt.setString(5, medicienRecord.getMrComment());
			pstmt.setString(6, medicienRecord.getMrPatName());
			pstmt.setString(7, medicienRecord.getMrEmpName());
			pstmt.setString(8, medicienRecord.getMrOriginalFileName());
			pstmt.setString(9, medicienRecord.getMrRenameFileName());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMedicienRecord(Connection conn, MedicienRecordVo medicienRecord) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query ="update medicienrecord set mr_date = ?, mr_state = ?, mr_name = ?,"
				+ "mr_time = ?, mr_many = ?, mr_comment = ?,"
				+ "mr_pat_name = ?, mr_emp_name = ?, mr_original_filename = ?, mr_rename_filename = ?"
				+ "where mr_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, medicienRecord.getMrDate());
			pstmt.setString(2, medicienRecord.getMrState());
			pstmt.setString(3, medicienRecord.getMrName());
			pstmt.setString(4, medicienRecord.getMrTime());
			pstmt.setString(5, medicienRecord.getMrMany());
			pstmt.setString(6, medicienRecord.getMrComment());
			pstmt.setString(7, medicienRecord.getMrPatName());
			pstmt.setString(8, medicienRecord.getMrEmpName());
			pstmt.setString(9, medicienRecord.getMrOriginalFileName());
			pstmt.setString(10, medicienRecord.getMrRenameFileName());
			pstmt.setInt(11, medicienRecord.getMrNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMedicienRecord(Connection conn, int mrNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from medicienrecord where mr_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mrNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from medicienrecord";
		
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
