package Main.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import Main.Cnotice.model.vo.Cnotice;
import Main.dao.QnaDao;
import Main.vo.QnaVo;

public class QnaService {
	private QnaDao QDao = new QnaDao();
	
	public int updateQna(QnaVo q) {
		Connection conn = getConnection();
		int result = QDao.updateQna(conn, q);
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<QnaVo> selectList(int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<QnaVo> list = QDao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	public int insertQna(QnaVo q) {
		Connection conn = getConnection();
		int result = QDao.insertQna(conn, q);
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public QnaVo detailQna(int qnano) {
		Connection conn = getConnection();
		QnaVo q = QDao.detailQna(conn, qnano);
		close(conn);
		return q;
	}

	public int deleteQna(int qnano) {
		Connection conn = getConnection();
		int result = QDao.deleteQna(conn, qnano);
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
		int listCount = QDao.getListCount(conn);
		close(conn);
		return listCount;
	}
}
