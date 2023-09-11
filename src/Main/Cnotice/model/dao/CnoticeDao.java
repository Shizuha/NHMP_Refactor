package Main.Cnotice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Main.Cnotice.model.vo.Cnotice;

import static common.JDBCTemplate.close;

public class CnoticeDao {

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

	public int insertNotice(Connection conn, Cnotice c) {
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

	public ArrayList<Cnotice> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Cnotice> list = new ArrayList<Cnotice>();
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
				Cnotice c = new Cnotice();
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

	public int updateNotice(Connection conn, Cnotice n) {
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

	public Cnotice SelectOne(Connection conn, int noticeno) {
		Cnotice notice = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from cnotice where notice_no = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Cnotice();
				
				notice.setNOTICE_NO(noticeno);
				notice.setNOTICE_TITLE(rset.getString("notice_title"));
				notice.setNOTICE_TYPE(rset.getString("notice_type"));
				notice.setNOTICE_DATE(rset.getDate("notice_date"));
				notice.setNOTICE_COUNT(rset.getInt("notice_count"));
				notice.setNH_NAME(rset.getString("notice_nh_name"));
				notice.setNOTICE_CONTENT(rset.getString("notice_content"));
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
