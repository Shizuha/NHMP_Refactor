package ERP.Education.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ERP.Education.model.vo.Education;
import static common.JDBCTemplate.close;

public class EducationDao {
	
	public EducationDao() {}

	public int insertEdu(Connection conn, Education e) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into education values(EDU_SEQ_CODE.NEXTVAL,?,?,?,?,?,?,?)";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, e.getItnalFor());
				pstmt.setString(2, e.getAdDate());
				pstmt.setString(3, e.getGrDate());
				pstmt.setString(4, e.getSchName());
				pstmt.setString(5, e.getMajor());
				pstmt.setString(6, e.getTaking());
				pstmt.setString(7, e.getEduId());
					
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		
		
		return result;
	}

	public ArrayList<Education> selectList(Connection conn, String empId) {
		ArrayList<Education> eduList = new ArrayList<Education>();
		Education edu = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from education where edu_id = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				edu = new Education();
				
				edu.setItnalFor(rs.getString("ITNAL_FOR"));
				edu.setAdDate(rs.getString("AD_DATE"));
				edu.setGrDate(rs.getString("GR_DATE"));
				edu.setSchName(rs.getString("SCH_NAME"));
				edu.setMajor(rs.getString("MAJOR"));
				edu.setTaking(rs.getString("TAKING"));
				
				
				
				eduList.add(edu);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return eduList;
	}

	public int updateEdu(Connection conn, Education e, String eduCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update education set ITNAL_FOR = ?, AD_DATE = ?, GR_DATE = ?, "
				+ " SCH_NAME = ?, MAJOR = ?, TAKING = ? "
				+ "where edu_id = ? and edu_code = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, e.getItnalFor());
				pstmt.setString(2, e.getAdDate());
				pstmt.setString(3, e.getGrDate());
				pstmt.setString(4, e.getSchName());
				pstmt.setString(5, e.getMajor());
				pstmt.setString(6, e.getTaking());
				pstmt.setString(7, e.getEduId());
				pstmt.setString(8, eduCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		
		
		return result;
	}

	public String[] selectEduCode(Connection conn, String empId, int length) {
		String[] eduCode = new String[length];
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select edu_code from education where edu_id = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				eduCode[i] = rs.getString("edu_code");
				
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return eduCode;
	}
}
