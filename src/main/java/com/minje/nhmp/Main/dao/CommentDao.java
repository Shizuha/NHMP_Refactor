package com.minje.nhmp.Main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.minje.nhmp.Main.vo.CommentVo;

import static com.minje.nhmp.common.JDBCTemplate.close;


public class CommentDao {

	public int deleteCommnet(Connection conn, int commno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from comments where comment_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int insertComment(Connection conn, CommentVo com) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into comments values (SEQ_COM.nextval, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, com.getCOMMENT_ETC());
			pstmt.setInt(2, com.getQNA_NO());
			pstmt.setString(3, com.getNH_ID());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int updateComment(Connection conn, CommentVo com) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update comments set comment_etc = ? where comment_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, com.getCOMMENT_ETC());
			pstmt.setInt(2, com.getCOMMENT_NO());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public ArrayList<CommentVo> selectList(Connection conn, int qnano) {
		ArrayList<CommentVo> list = new ArrayList<CommentVo>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from comments where COMMENT_QNA_NO = ?";
		

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnano);

			rset = pstmt.executeQuery();
			while(rset.next()) {
				CommentVo com = new CommentVo();
				com.setCOMMENT_NO(rset.getInt("comment_no"));
				com.setCOMMENT_ETC(rset.getString("comment_etc"));
				com.setQNA_NO(rset.getInt("comment_qna_no"));
				com.setNH_ID(rset.getString("comment_nh_name"));
				
				list.add(com);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

}
