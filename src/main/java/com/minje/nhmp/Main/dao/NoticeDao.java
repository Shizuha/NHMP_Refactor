package com.minje.nhmp.Main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.minje.nhmp.Main.vo.NoticeVo;

import static com.minje.nhmp.common.JDBCTemplate.close;

public class NoticeDao {

	public int deleteNotice(Connection conn, int noticeno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from cnotice where notice_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertNotice(Connection conn, NoticeVo c) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into cnotice values(SEQ_CNOTICE.NEXTVAL, ?, ?, ?, 0, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, c.getNOTICE_TITLE());
			pstmt.setString(2, c.getNOTICE_TYPE());
			pstmt.setString(3, c.getNOTICE_CONTENT());
			pstmt.setDate(4, c.getNOTICE_DATE());
			pstmt.setString(5, c.getNH_NAME());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public ArrayList<NoticeVo> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<NoticeVo> list = new ArrayList<NoticeVo>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from( select rownum rnum, notice_no, notice_title, notice_type, notice_date, notice_nh_name " +
				"from (select * from cnotice order by notice_date desc)) where rnum >= ? and rnum <= ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeVo c = new NoticeVo();
				c.setNOTICE_NO(rset.getInt("notice_no"));
				c.setNOTICE_TITLE(rset.getString("notice_title"));
				c.setNOTICE_TYPE(rset.getString("notice_type"));
				c.setNOTICE_DATE(rset.getDate("notice_date"));
				c.setNH_NAME(rset.getString("notice_nh_name"));
				
				list.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
		
	}

	public int updateNotice(Connection conn, NoticeVo n) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update cnotice set notice_title = ?, notice_content = ?"
				+ ", notice_date = ? , notice_type = ? where notice_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNOTICE_TITLE());
			pstmt.setString(2, n.getNOTICE_CONTENT());
			pstmt.setDate(3, n.getNOTICE_DATE());
			pstmt.setString(4, n.getNOTICE_TYPE());
			pstmt.setInt(5, n.getNOTICE_NO());
			
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
		
		String query = "select count(*) from cnotice";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			} else {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public NoticeVo SelectOne(Connection conn, int noticeno) {
		NoticeVo notice = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from cnotice where notice_no = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				NoticeVo notice1 = new NoticeVo();
				
				notice1.setNOTICE_NO(noticeno);
				notice1.setNOTICE_TITLE(rset.getString("notice_title"));
				notice1.setNOTICE_TYPE(rset.getString("notice_type"));
				notice1.setNOTICE_DATE(rset.getDate("notice_date"));
				notice1.setNOTICE_COUNT(rset.getInt("notice_count"));
				notice1.setNH_NAME(rset.getString("notice_nh_name"));
				notice1.setNOTICE_CONTENT(rset.getString("notice_content"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}

}
