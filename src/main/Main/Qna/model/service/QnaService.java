package Main.Qna.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import Main.Cnotice.model.vo.Cnotice;
import Main.Qna.model.dao.QnaDao;
import Main.Qna.model.vo.Qna;

public class QnaService {
	private QnaDao QDao = new QnaDao();
	
	public int updateQna(Qna q) {
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

	public ArrayList<Qna> selectList(int startRow, int endRow) {
		Connection conn = getConnection();
		ArrayList<Qna> list = QDao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	public int insertQna(Qna q) {
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

	public Qna detailQna(int qnano) {
		Connection conn = getConnection();
		Qna q = QDao.detailQna(conn, qnano);
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
