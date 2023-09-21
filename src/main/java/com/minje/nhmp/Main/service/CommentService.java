package com.minje.nhmp.Main.service;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.Main.dao.CommentDao;
import com.minje.nhmp.Main.vo.CommentVo;

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

	public int insertComment(CommentVo com) {
		Connection conn = getConnection();
		int result = CDao.insertComment(conn, com);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int updateComment(CommentVo com) {
		Connection conn = getConnection();
		int result = CDao.updateComment(conn, com);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<CommentVo> selectList(int qnano) {
		Connection conn = getConnection();
		ArrayList<CommentVo> list = CDao.selectList(conn, qnano);
		close(conn);
		return list;
	}
}
