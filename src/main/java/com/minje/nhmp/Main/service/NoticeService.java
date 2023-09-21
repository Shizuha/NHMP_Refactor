package com.minje.nhmp.Main.service;

import static com.minje.nhmp.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.Main.dao.NoticeDao;
import com.minje.nhmp.Main.vo.NoticeVo;

public class NoticeService {
	private NoticeDao CADao = new NoticeDao();

	public int deleteNotice(int noticeno) {
		Connection conn = getConnection();
		int result = CADao.deleteNotice(conn, noticeno);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int insertNotice(NoticeVo c) {
		Connection conn = getConnection();
		int result = CADao.insertNotice(conn, c);
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<NoticeVo> selectList(int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<NoticeVo> list = CADao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	public int updatetNotice(NoticeVo n) {
		Connection conn = getConnection();
		int result = CADao.updateNotice(conn, n);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = CADao.getListCount(conn);
		close(conn);
		return listCount;
	}

	public NoticeVo selectOne(int noticeno) {
		Connection conn = getConnection();
		NoticeVo notice = CADao.SelectOne(conn, noticeno);
		close(conn);
		return notice;
	}
}
