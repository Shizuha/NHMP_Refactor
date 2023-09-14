package Main.Cnotice.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import Main.Cnotice.model.dao.CnoticeDao;
import Main.Cnotice.model.vo.Cnotice;

public class CnoticeService {
	private CnoticeDao CADao = new CnoticeDao();

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

	public int insertNotice(Cnotice c) {
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

	public ArrayList<Cnotice> selectList(int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<Cnotice> list = CADao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	public int updatetNotice(Cnotice n) {
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

	public Cnotice selectOne(int noticeno) {
		Connection conn = getConnection();
		Cnotice notice = CADao.SelectOne(conn, noticeno);
		close(conn);
		return notice;
	}
}
