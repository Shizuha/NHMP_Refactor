package Main.NursingHospital.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import Main.NursingHospital.model.dao.NHDao;
import Main.NursingHospital.model.ov.NursingHospital;

import static common.JDBCTemplate.*;

public class NHService {
	private NHDao ndao = new NHDao();
	
	public NHService() {}

	public NursingHospital loginCheck(String userid, String userpwd) {
		Connection conn = getConnection();
		NursingHospital nh = ndao.loginCheck(conn, userid, userpwd);
		close(conn);
		return nh;
	}

	public int insertHPT(NursingHospital hospital) {
		int result = 0;
		Connection conn = getConnection();
		result = ndao.insertHPT(conn, hospital);
		
		if(result > 0) {
			commit(conn);			
		}else {
			rollback(conn);			
		}
		return result;
	}

	public int idCheck(String userid) {
		Connection conn = getConnection();
		int result = ndao.idCheck(conn, userid);
		close(conn);
		return result;
	}
	
	public int idCheck2(String userid) {
		Connection conn = getConnection();
		int result = ndao.idCheck2(conn, userid);
		close(conn);
		return result;
	}

	public int idCheck3(String userid) {
		Connection conn = getConnection();
		int result = ndao.idCheck3(conn, userid);
		close(conn);
		return result;
	}

	public int serviceUpdate(String service, NursingHospital loginHospital) {
		Connection conn = getConnection();
		int result = ndao.serviceUpdate(conn, service, loginHospital);
		close(conn);
		return result;
	}

	public ArrayList<NursingHospital> selectList() {
		Connection conn = getConnection();
		ArrayList<NursingHospital> list = ndao.selectList(conn);
		return list;
	}

	public int UpdateAuthority(String nHch, String authch, NursingHospital loginHospital) {
		
		Connection conn = getConnection();
		int result = ndao.UpdateAuthority(conn, nHch, authch, loginHospital);
		close(conn);
		return result;
	}

	public void newuser(NursingHospital loginHospital) {
		Connection conn = getConnection();
		ndao.newuser(conn, loginHospital);
		close(conn);
				
	}

	public void sample(NursingHospital loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		ndao.sample(conn, loginHospital);
		close(conn);
	}

	public ArrayList<NursingHospital> selectAllList() {
		Connection conn = getConnection();
		ArrayList<NursingHospital> list = ndao.selectAllList(conn);
		return list;
	}

	public NursingHospital selectOne(String cname) {
		Connection conn = getConnection();
		NursingHospital NH = ndao.selectOne(conn, cname);
		return NH;
	}

	public ArrayList<NursingHospital> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public String NHcount(NursingHospital loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		String result = ndao.NHcount(conn);
		return result;
	}

	public String NHServiceCount(NursingHospital loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		String result = ndao.NHServiceCount(conn);
		return result;
	}



	
	
	
}
