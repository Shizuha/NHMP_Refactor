package com.minje.nhmp.ERP.dao;

import static com.minje.nhmp.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.PatientVo;

public class PatientDao {
	public PatientDao() {}

	public ArrayList<PatientVo> ListView(Connection conn) {
		ArrayList<PatientVo> list = new ArrayList<PatientVo>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from patient";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				PatientVo patient = new PatientVo();
				
				patient.setPatNum(rset.getInt("pat_num"));
				patient.setPatName(rset.getString("pat_name"));
				patient.setPatType(rset.getString("pat_type"));
				patient.setPatEntDate(rset.getDate("pat_entdate"));
				patient.setPatOutDate(rset.getDate("pat_outdate"));
				patient.setPatGender(rset.getString("pat_gender"));
				patient.setPatNo(rset.getString("pat_no"));
				patient.setAddress(rset.getString("address"));
				patient.setFamily(rset.getString("family"));
				patient.setEmail(rset.getString("email"));
				patient.setPatPhone(rset.getString("pat_phone"));
				patient.setWard(rset.getString("ward"));
				patient.setPatDoc(rset.getString("pat_doc"));
				
				list.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public PatientVo DetailView(Connection conn, int patNum) {
		PatientVo patient = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from patient where pat_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, patNum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				patient = new PatientVo();
				
				patient.setPatNum(patNum);
				patient.setPatName(rset.getString("pat_name"));
				patient.setPatType(rset.getString("pat_type"));
				patient.setPatEntDate(rset.getDate("pat_entdate"));
				patient.setPatOutDate(rset.getDate("pat_outdate"));
				patient.setPatGender(rset.getString("pat_gender"));
				patient.setPatNo(rset.getString("pat_no"));
				patient.setAddress(rset.getString("address"));
				patient.setFamily(rset.getString("family"));
				patient.setEmail(rset.getString("email"));
				patient.setPatPhone(rset.getString("pat_phone"));
				patient.setWard(rset.getString("ward"));
				patient.setPatDoc(rset.getString("pat_doc"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return patient;
	}

	public int insertPatient(Connection conn, PatientVo patient) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into patient values("
				+ "pat_seq.nextval, ?, ?, sysdate, default, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, patient.getPatName());
			pstmt.setString(2, patient.getPatType());
			pstmt.setString(3, patient.getPatGender());
			pstmt.setString(4, patient.getPatNo());
			pstmt.setString(5, patient.getAddress());
			pstmt.setString(6, patient.getFamily());
			pstmt.setString(7, patient.getEmail());
			pstmt.setString(8, patient.getPatPhone());
			pstmt.setString(9, patient.getWard());
			pstmt.setString(10, patient.getPatDoc());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePatient(Connection conn, PatientVo patient) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update patient set pat_name = ?, pat_type = ?, pat_entdate = ?, "
				+ "pat_outdate = default, pat_gender = ?, pat_no = ?, address = ?, family = ?, "
				+ "email = ?, pat_phone = ?, ward = ?, pat_doc = ?"
				+ "where pat_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, patient.getPatName());
			pstmt.setString(2, patient.getPatType());
			pstmt.setDate(3, patient.getPatEntDate());
			pstmt.setString(4, patient.getPatGender());
			pstmt.setString(5, patient.getPatNo());
			pstmt.setString(6, patient.getAddress());
			pstmt.setString(7, patient.getFamily());
			pstmt.setString(8, patient.getEmail());
			pstmt.setString(9, patient.getPatPhone());
			pstmt.setString(10, patient.getWard());
			pstmt.setString(11, patient.getPatDoc());
			pstmt.setInt(12, patient.getPatNum());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deletePatient(Connection conn, int patNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from patient where pat_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, patNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public String PatientCount(Connection conn) {
		String result = "0";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from patient";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
}
