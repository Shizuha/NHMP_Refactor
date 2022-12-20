package Main.Comment.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import Main.Comment.model.dao.CommentDao;
import Main.Comment.model.vo.Comment;

public class CommentService {
	private CommentDao CDao = new CommentDao();

	public int deleteComment(int commno) {
		Connection conn = getConnection();
		int result = CDao.deleteCommnet(conn, commno);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int insertComment(Comment com) {
		Connection conn = getConnection();
		int result = CDao.insertComment(conn, com);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int updateComment(Comment com) {
		Connection conn = getConnection();
		int result = CDao.updateComment(conn, com);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<Comment> selectList(int qnano) {
		Connection conn = getConnection();
		ArrayList<Comment> list = CDao.selectList(conn, qnano);
		close(conn);
		return list;
	}
}
