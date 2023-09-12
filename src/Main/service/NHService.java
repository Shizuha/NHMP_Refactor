package Main.NursingHospital.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import Main.NursingHospital.model.dao.NHDao;
import Main.vo.NursingHospitalVo;

import static common.JDBCTemplate.*;

public class NHService {
	private NHDao ndao = new NHDao();
	
	public NHService() {}

	public NursingHospitalVo loginCheck(String userid, String userpwd) {
		Connection conn = getConnection();
		NursingHospitalVo nh = ndao.loginCheck(conn, userid, userpwd);
		close(conn);
		return nh;
	}

	public int insertHPT(NursingHospitalVo hospital) {
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

	public int serviceUpdate(String service, NursingHospitalVo loginHospital) {
		Connection conn = getConnection();
		int result = ndao.serviceUpdate(conn, service, loginHospital);
		close(conn);
		return result;
	}

	public ArrayList<NursingHospitalVo> selectList() {
		Connection conn = getConnection();
		ArrayList<NursingHospitalVo> list = ndao.selectList(conn);
		return list;
	}

	public int UpdateAuthority(String nHch, String authch, NursingHospitalVo loginHospital) {
		
		Connection conn = getConnection();
		int result = ndao.UpdateAuthority(conn, nHch, authch, loginHospital);
		close(conn);
		return result;
	}

	public void newuser(NursingHospitalVo loginHospital) {
		Connection conn = getConnection();
		ndao.newuser(conn, loginHospital);
		close(conn);
				
	}

	public void sample(NursingHospitalVo loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		ndao.sample(conn, loginHospital);
		close(conn);
	}

	public ArrayList<NursingHospitalVo> selectAllList() {
		Connection conn = getConnection();
		ArrayList<NursingHospitalVo> list = ndao.selectAllList(conn);
		return list;
	}

	public NursingHospitalVo selectOne(String cname) {
		Connection conn = getConnection();
		NursingHospitalVo NH = ndao.selectOne(conn, cname);
		return NH;
	}

	public ArrayList<NursingHospitalVo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public String NHcount(NursingHospitalVo loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		String result = ndao.NHcount(conn);
		return result;
	}

	public String NHServiceCount(NursingHospitalVo loginHospital) {
		Connection conn = getConnection(loginHospital.getNH_USERID(), loginHospital.getNH_USERPWD());
		String result = ndao.NHServiceCount(conn);
		return result;
	}
}
