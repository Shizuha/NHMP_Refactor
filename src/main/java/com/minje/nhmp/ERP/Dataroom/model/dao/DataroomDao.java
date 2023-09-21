package ERP.Dataroom.model.dao;

import static common.JDBCTemplate.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import ERP.Employee.model.vo.*;
import ERP.Dataroom.model.vo.*;
import ERP.Dataroom.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DataroomDao {
	
	public DataroomDao() {}
	
	
	//조회수 페이징처리 및 화면 보이기
	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from dataroom";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	
	
	//패이징처리
	public ArrayList<Dataroom> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Dataroom> list = new ArrayList<Dataroom>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from ("
				+ "select rownum rnum, dataroom_no, dataroom_title, dataroom_content,"
				+ "dataroom_original_filename, dataroom_rename_filename, dataroom_count, dataroom_writer, "
				+ " dataroom_date from (select * from dataroom order by dataroom_no desc)) " 
				+ " where rnum >= ? and rnum <= ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Dataroom dataroom = new Dataroom();
				
				dataroom.setDataroomNo(rset.getInt("dataroom_no"));
				dataroom.setDataroomTitle(rset.getString("dataroom_title"));
				dataroom.setDataroomContent(rset.getString("dataroom_content"));
				dataroom.setDataroomOriginalFileName(rset.getString("dataroom_original_filename"));
				dataroom.setDataroomRenameFileName(rset.getString("dataroom_rename_filename"));
				dataroom.setDataroomCount(rset.getInt("dataroom_count"));
				dataroom.setDataroomWriter(rset.getString("dataroom_writer"));
				dataroom.setDataroomDate(rset.getDate("dataroom_date"));
				
				list.add(dataroom);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	//상세화면 조회수 처리
	public int updateReadCount(Connection conn, String dataroomNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query ="update dataroom "
				+ "set dataroom_count = dataroom_count + 1 "
				+ "where dataroom_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dataroomNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}	
		return result;
	}

	//자료실 상세화면
	public Dataroom selectOne(Connection conn, String dataroomNo) {
		
		Dataroom dataroom = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null; 
		
		String query = "select * from dataroom where dataroom_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dataroomNo);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				dataroom = new Dataroom();
				
				dataroom.setDataroomNo(rset.getInt("dataroom_no"));
				dataroom.setDataroomTitle(rset.getString("dataroom_title"));
				dataroom.setDataroomContent(rset.getString("dataroom_content"));
				dataroom.setDataroomOriginalFileName(rset.getString("dataroom_original_filename"));
				dataroom.setDataroomRenameFileName(rset.getString("dataroom_rename_filename"));
				dataroom.setDataroomCount(rset.getInt("dataroom_count"));
				dataroom.setDataroomWriter(rset.getString("dataroom_writer"));
				dataroom.setDataroomDate(rset.getDate("dataroom_date"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return dataroom;
	}

	//자료실 글 작성
	public int insertNotice(Connection conn, Dataroom dataroom) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO dataroom VALUES(dataroom_SEQ.NEXTVAL, ?, ?, ?, ?, default, SYSDATE, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, dataroom.getDataroomTitle());
			pstmt.setString(2, dataroom.getDataroomContent());
			pstmt.setString(3, dataroom.getDataroomOriginalFileName());
			pstmt.setString(4, dataroom.getDataroomRenameFileName());
			pstmt.setString(5, dataroom.getDataroomWriter());
		
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	//자료실 글 수정
	public int updateNotice(Connection conn, Dataroom dataroom) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update dataroom set dataroom_title = ?, "
				+ "dataroom_content = ?, "
				+ "dataroom_original_filename = ?,"
				+ "dataroom_rename_filename = ?"
				+ "where dataroom_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, dataroom.getDataroomTitle());
			pstmt.setString(2, dataroom.getDataroomContent());
			pstmt.setString(3, dataroom.getDataroomOriginalFileName());
			pstmt.setString(4, dataroom.getDataroomRenameFileName());
			pstmt.setInt(5, dataroom.getDataroomNo());
		
			
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	//자료실 글 삭제
	public int deleteNotice(Connection conn, String dataroomNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from dataroom where dataroom_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dataroomNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}		
		
		return result;
	}

	//재목검색
	@SuppressWarnings("finally")
	public ArrayList<Dataroom> selectTitleSearch(Connection conn, String keyword) {
		ArrayList<Dataroom> list = new ArrayList<Dataroom>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from dataroom where dataroom_title like ? order by dataroom_no desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			//while문으로 객체 옮겨 담기
			while(rset.next()) {
				Dataroom dataroom = new Dataroom();
				
				dataroom.setDataroomNo(rset.getInt("dataroom_no"));
				dataroom.setDataroomTitle(rset.getString("dataroom_title"));
				dataroom.setDataroomWriter(rset.getString("dataroom_writer"));
				dataroom.setDataroomDate(rset.getDate("dataroom_date"));
				dataroom.setDataroomCount(rset.getInt("dataroom_count"));
				dataroom.setDataroomOriginalFileName(rset.getString("dataroom_original_filename"));
				dataroom.setDataroomRenameFileName(rset.getString("dataroom_rename_filename"));
				
				list.add(dataroom);
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
		return list;
			}
	}

	//작성자검색
	@SuppressWarnings("finally")
	public ArrayList<Dataroom> selectWriterSearch(Connection conn, String keyword) {
		ArrayList<Dataroom> list = new ArrayList<Dataroom>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from dataroom where dataroom_writer like ? order by dataroom_no desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			//while문으로 객체 옮겨 담기
			while(rset.next()) {
				Dataroom dataroom = new Dataroom();
				
				dataroom.setDataroomNo(rset.getInt("dataroom_no"));
				dataroom.setDataroomTitle(rset.getString("dataroom_title"));
				dataroom.setDataroomWriter(rset.getString("dataroom_writer"));
				dataroom.setDataroomDate(rset.getDate("dataroom_date"));
				dataroom.setDataroomCount(rset.getInt("dataroom_count"));
				
				list.add(dataroom);
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
		return list;
			}
	}

	
	
	
	
	
}






