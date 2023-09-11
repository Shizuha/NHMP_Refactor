package Main.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Main.vo.QnaVo;

public class QnaDao {

	public int updateQna(Connection conn, QnaVo q) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update qna set qna_title = ?, qna_content = ? " + 
				", qna_date = ? , qna_type = ? where qna_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, q.getQNA_TITLE());
			pstmt.setString(2, q.getQNA_CONTENT());
			pstmt.setDate(3, q.getQNA_DATE());
			pstmt.setString(4, q.getQNA_TYPE());
			pstmt.setInt(5, q.getQNA_NO());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<QnaVo> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<QnaVo> list = new ArrayList<QnaVo>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from( select rownum rnum, qna_no, qna_title, qna_type, qna_date, qna_nh_name " +
				"from (select * from qna order by qna_date desc)) where rnum >= ? and rnum <= ?";
		

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			while(rset.next()) {
				QnaVo q = new QnaVo();
				q.setQNA_NO(rset.getInt("qna_no"));
				q.setQNA_TITLE(rset.getString("qna_title"));
				q.setQNA_TYPE(rset.getString("qna_type"));
				q.setQNA_DATE(rset.getDate("qna_date"));
				q.setNH_NAME(rset.getString("qna_nh_name"));
				
				list.add(q);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
		
	}

	public int insertQna(Connection conn, QnaVo q) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into qna values(SEQ_CNOTICE.NEXTVAL, ?, ?, ?, 0, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, q.getQNA_TITLE());
			pstmt.setString(2, q.getQNA_TYPE());
			pstmt.setString(3, q.getQNA_CONTENT());
			pstmt.setDate(4, q.getQNA_DATE());
			pstmt.setString(5, q.getNH_NAME());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public QnaVo detailQna(Connection conn, int qnano) {
		QnaVo q = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from qna where qna_no = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnano);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				q = new QnaVo();
				
				q.setQNA_NO(rset.getInt("qna_no"));
				q.setQNA_TITLE(rset.getString("qna_title"));
				q.setNH_NAME(rset.getString("qna_nh_name"));
				q.setQNA_CONTENT(rset.getString("qna_content"));
				q.setQNA_DATE(rset.getDate("qna_date"));
				q.setQNA_TYPE(rset.getString("qna_type"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return q;
		
		
	}

	public int deleteQna(Connection conn, int qnano) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from qna where qna_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnano);
			
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
		
		String query = "select count(*) from qna";
		
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

}
