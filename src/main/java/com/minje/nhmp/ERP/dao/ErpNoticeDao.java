package com.minje.nhmp.ERP.dao;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.ErpNoticeVo;

public class ErpNoticeDao {

	public ArrayList<ErpNoticeVo> selectAll(Connection conn) {
		//공지사항 전체 화면
		ArrayList<ErpNoticeVo> list = new ArrayList<ErpNoticeVo>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice order by notice_no desc";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				ErpNoticeVo notice = new ErpNoticeVo();
				
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setNoticeCount(rset.getInt("notice_count"));
				notice.setNoticeWriter(rset.getString("notice_writer"));
				notice.setNoticeDate(rset.getDate("notice_date"));
			
				list.add(notice);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public ErpNoticeVo selectOne(Connection conn, String noticeNo) {
		// 공지사항 클릭시 수정 화면
		ErpNoticeVo notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null; 
		
		String query = "select * from notice where notice_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new ErpNoticeVo();
				
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setNoticeCount(rset.getInt("notice_count"));
				notice.setNoticeWriter(rset.getString("notice_writer"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}


	// 조회수 처리하는 dao
	public int updateReadCount(Connection conn, String noticeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query ="update notice "
				+ "set notice_count = notice_count + 1 "
				+ "where notice_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}	
		return result;
	}
	
		//조회수 페이징
		public int getListCount(Connection conn) {
			int listCount = 0;
			Statement stmt = null;
			ResultSet rset = null;
			
			String query = "select count(*) from notice";
			
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
	

		//페이징 처리
		public ArrayList<ErpNoticeVo> selectList(Connection conn, int startRow, int endRow) {
			ArrayList<ErpNoticeVo> list = new ArrayList<ErpNoticeVo>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			//
			String query = "select * from ("
					+ "select rownum rnum, notice_no, notice_title, notice_content,"
					+ "notice_count, notice_writer,"
					+ " notice_date from (select * from notice order by notice_no desc)) " 
					+ " where rnum >= ? and rnum <= ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					ErpNoticeVo notice = new ErpNoticeVo();
					
					notice.setNoticeNo(rset.getInt("notice_no"));
					notice.setNoticeTitle(rset.getString("notice_title"));
					notice.setNoticeContent(rset.getString("notice_content"));
					notice.setNoticeCount(rset.getInt("notice_count"));
					notice.setNoticeWriter(rset.getString("notice_writer"));
					notice.setNoticeDate(rset.getDate("notice_date"));
					
					list.add(notice);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
		
		//글 추가 dao
		public int insertNotice(Connection conn, ErpNoticeVo notice) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "INSERT INTO NOTICE VALUES(NOTICE_SEQ.NEXTVAL, ?, ?, default, SYSDATE, ?)";
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, notice.getNoticeTitle());
				pstmt.setString(2, notice.getNoticeContent());
				pstmt.setString(3, notice.getNoticeWriter());
				
				result = pstmt.executeUpdate();
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return result;
		}

		public ArrayList<ErpNoticeVo> selectTop3(Connection conn) {
			ArrayList<ErpNoticeVo> list = new ArrayList<ErpNoticeVo>();
			Statement stmt = null;
			ResultSet rset = null;
			
			String query = "SELECT * FROM (SELECT ROWNUM RNUM, NOTICE_NO, NOTICE_TITLE, NOTICE_DATE " + 
					"FROM (SELECT * FROM NOTICE ORDER BY NOTICE_DATE DESC)) WHERE RNUM >= 1 AND RNUM <= 8";
			
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				while(rset.next()) {
					ErpNoticeVo n = new ErpNoticeVo();
					
					n.setNoticeNo(rset.getInt("notice_no"));
					n.setNoticeTitle(rset.getString("notice_title"));
					n.setNoticeDate(rset.getDate("notice_date"));
		
					list.add(n);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset); 
				close(stmt);
			}
			
			return list;
		}

		public int updateNotice(Connection conn, ErpNoticeVo notice) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "update notice set notice_title = ?, "
					  + "notice_content = ? "
					  + "where notice_no = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, notice.getNoticeTitle());
				pstmt.setString(2, notice.getNoticeContent());
				pstmt.setInt(3, notice.getNoticeNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			return result;
		}
		
		//글 삭제 dao
		public int deleteNotice(Connection conn, String noticeNo) {
			int result = 0;
			PreparedStatement pstmt = null;
			
			String query = "delete from notice where notice_no = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, noticeNo);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}		
			
			return result;
		}
		
		//제목 검색 dao
		public ArrayList<ErpNoticeVo> selectTitleSearch(Connection conn, String keyword) {
			ArrayList<ErpNoticeVo> list = new ArrayList<ErpNoticeVo>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "select * from notice where notice_title like ? order by notice_no desc";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + keyword + "%");
				
				rset = pstmt.executeQuery();
				
				//while문으로 객체 옮겨 담기
				while(rset.next()) {
					ErpNoticeVo notice = new ErpNoticeVo();
					
					notice.setNoticeNo(rset.getInt("notice_no"));
					notice.setNoticeTitle(rset.getString("notice_title"));
					notice.setNoticeWriter(rset.getString("notice_writer"));
					notice.setNoticeDate(rset.getDate("notice_date"));
					notice.setNoticeCount(rset.getInt("notice_count"));	
					
					list.add(notice);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
		
		//작성자로 검색
		public ArrayList<ErpNoticeVo> selectWriterSearch(Connection conn, String keyword) {
			ArrayList<ErpNoticeVo> list = new ArrayList<ErpNoticeVo>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "select * from notice where notice_writer like ? order by notice_no desc";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + keyword + "%");
				
				rset = pstmt.executeQuery();
				
				//while문으로 객체 옮겨 담기
				while (rset.next()) {
					ErpNoticeVo notice = new ErpNoticeVo();
					
					notice.setNoticeNo(rset.getInt("notice_no"));
					notice.setNoticeTitle(rset.getString("notice_title"));
					notice.setNoticeWriter(rset.getString("notice_writer"));
					notice.setNoticeDate(rset.getDate("notice_date"));
					notice.setNoticeCount(rset.getInt("notice_count"));	
					
					list.add(notice);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			return list;
		}

		public ArrayList<ErpNoticeVo> selectDateSearch(Connection conn, Date from, Date to) {
			ArrayList<ErpNoticeVo> list = new ArrayList<ErpNoticeVo>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "select * from notice where notice_date between ? and ? order by notice_no desc";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setDate(1, from);
				pstmt.setDate(2, to);
				
				rset = pstmt.executeQuery();
				
				while (rset.next()) {
					ErpNoticeVo notice = new ErpNoticeVo();
					
					notice.setNoticeNo(rset.getInt("notice_no"));
					notice.setNoticeTitle(rset.getString("notice_title"));
					notice.setNoticeWriter(rset.getString("notice_writer"));
					notice.setNoticeDate(rset.getDate("notice_date"));
					notice.setNoticeCount(rset.getInt("notice_count"));	
					
					list.add(notice);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
}











