package com.minje.nhmp.Main.dao;

import static com.minje.nhmp.common.JDBCTemplate.close;
import static com.minje.nhmp.common.JDBCTemplate.commit;
import static com.minje.nhmp.common.JDBCTemplate.rollback;

import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.minje.nhmp.Main.vo.NursingHospitalVo;

public class NursingHospitalDao {
	private Properties prop;
	public NursingHospitalDao() {
		prop = new Properties();
		String currDir = NursingHospitalDao.class.getResource(".").getPath();
		try {
			prop.load(new FileReader(currDir+"../../../../../../properties/tableSample.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NursingHospitalVo loginCheck(Connection conn, String userid, String userpwd) {
		NursingHospitalVo nh = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from NURSING_HOSPITAL where nh_userid = ? and nh_userpwd = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			pstmt.setString(2, userpwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				nh = new NursingHospitalVo();
				
				nh.setNH_ID(rset.getInt("NH_ID"));
				nh.setNH_NAME(rset.getString("NH_NAME"));
				nh.setNH_NO(rset.getString("NH_NO"));
				nh.setNH_ADDRESS(rset.getString("NH_ADDRESS"));
				nh.setNH_ITNAL_FOR(rset.getString("NH_ITNAL_FOR"));
				nh.setNH_AD_TEL(rset.getString("NH_AD_TEL"));
				nh.setNH_PHONE(rset.getString("NH_PHONE"));
				nh.setNH_EMAIL(rset.getString("NH_EMAIL"));
				nh.setNH_USERID(rset.getString("NH_USERID"));
				nh.setNH_USERPWD(rset.getString("NH_USERPWD"));
				nh.setNH_ETC(rset.getString("NH_ETC"));
				nh.setNH_IMG(rset.getString("NH_IMG"));
				nh.setGENDER(rset.getString("GENDER"));
				nh.setCOMPANY_NAME(rset.getString("COMPANY_NAME"));
				nh.setCOMPANY_NO(rset.getString("COMPANY_NO"));
				nh.setNH_SERVICE_CODE(rset.getString("NH_SERVICE_CODE"));
				nh.setAUTHORITY_CODE(rset.getString("NH_AUTHORITY_CODE"));
				nh.setNH_SERVICE_HISTORY(rset.getString("NH_SERVICE_HISTORY"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(nh);
		return nh;
	}

	public int insertHPT(Connection conn, NursingHospitalVo hospital) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO NURSING_HOSPITAL VALUES((select max(NH_ID)+1 from nursing_hospital), ?, DEFAULT, DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, 'null', 'null', ?, ?, ?,DEFAULT,DEFAULT,DEFAULT)";
		//이름, 주민번호, 주소, 내/외, 회사 전화번호, 폰, 이메일, 아이디, 패스워드, 성별, 회사명 , 사업자 등록번호 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, hospital.getNH_NAME());			//이름
			pstmt.setString(2, hospital.getNH_NO());			//주민번호
			pstmt.setString(3, hospital.getNH_ADDRESS());		//주소
			pstmt.setString(4, hospital.getNH_ITNAL_FOR());		//내/외국인
			pstmt.setString(5, hospital.getNH_AD_TEL());		//회사 전화번호 AD
			pstmt.setString(6, hospital.getNH_PHONE());			//폰
			pstmt.setString(7, hospital.getNH_EMAIL());			//이메일
			pstmt.setString(8, hospital.getNH_USERID());		//아이디
			pstmt.setString(9, hospital.getNH_USERPWD());		//패스워드
			pstmt.setString(10, hospital.getGENDER());			//성별
			pstmt.setString(11, hospital.getCOMPANY_NAME());	//회사명
			pstmt.setString(12, hospital.getCOMPANY_NO());		//사업자 등록번호
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int idCheck(Connection conn, String userid) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT NH_USERID FROM NURSING_HOSPITAL WHERE NH_USERID = ?";
		
		try {			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			System.out.println(userid);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result += 1;
			}
			
			System.out.println("result : "+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public int idCheck2(Connection conn, String userid) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT NH_USERID FROM NURSING_HOSPITAL WHERE NH_USERID = ?";
		
		try {			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid.toUpperCase());
			System.out.println(userid.toUpperCase());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result += 1;
			}
			
			System.out.println("result : "+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public int idCheck3(Connection conn, String userid) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT NH_USERID FROM NURSING_HOSPITAL WHERE NH_USERID = ?";
		
		try {			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid.toLowerCase());
			System.out.println(userid.toLowerCase());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result += 1;
			}
			
			System.out.println("result : "+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int serviceUpdate(Connection conn, String service, NursingHospitalVo loginHospital) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		
		String query = "update nursing_hospital set nh_service_history = (select service_code from service where service_name = ?), nh_service_code = 'GS1' where nh_id = ?";
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, service);
			pstmt.setInt(2, loginHospital.getNH_ID());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public ArrayList<NursingHospitalVo> selectList(Connection conn) {
		ArrayList<NursingHospitalVo> list = new ArrayList<NursingHospitalVo>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from NURSING_HOSPITAL where NH_ID > 1 order by nh_id asc";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				NursingHospitalVo NH = new NursingHospitalVo();
				
				NH.setNH_ID(rset.getInt("NH_ID"));
				NH.setNH_NAME(rset.getString("NH_NAME"));
				NH.setNH_DATE(rset.getDate("NH_DATE"));
				NH.setNH_RSN_DATE(rset.getDate("NH_RSN_DATE"));
				NH.setNH_NO(rset.getString("NH_NO"));
				NH.setNH_ADDRESS(rset.getString("NH_ADDRESS"));
				NH.setNH_ITNAL_FOR(rset.getString("NH_ITNAL_FOR"));
				NH.setNH_AD_TEL(rset.getString("NH_AD_TEL"));
				NH.setNH_PHONE(rset.getString("NH_PHONE"));
				NH.setNH_EMAIL(rset.getString("NH_EMAIL"));
				NH.setNH_USERID(rset.getString("NH_USERID"));
				NH.setNH_USERPWD(rset.getString("NH_USERPWD"));
				NH.setNH_ETC(rset.getString("NH_ETC"));
				NH.setNH_IMG(rset.getString("NH_IMG"));
				NH.setGENDER(rset.getString("GENDER"));
				NH.setCOMPANY_NAME(rset.getString("COMPANY_NAME"));
				NH.setCOMPANY_NO(rset.getString("COMPANY_NO"));
				NH.setNH_SERVICE_CODE(rset.getString("NH_SERVICE_CODE"));
				NH.setAUTHORITY_CODE(rset.getString("NH_AUTHORITY_CODE"));
				NH.setNH_SERVICE_HISTORY(rset.getString("NH_SERVICE_HISTORY"));
			
				list.add(NH);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
		
	}

	public int UpdateAuthority(Connection conn, String nHch, String authch, NursingHospitalVo loginHospital) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String query = "update nursing_hospital set nh_authority_code = ?, nh_service_code = 'GS2' where nh_id = ?";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authch);
			pstmt.setInt(2, Integer.parseInt(nHch));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public void newuser(Connection conn, NursingHospitalVo loginHospital) {
		CallableStatement cstmt = null;
		
		String query = "{CALL NEWUSER(?, ?)}";
		System.out.println("계정 생성 ");
		System.out.println(loginHospital.getNH_USERID());
		System.out.println(loginHospital.getNH_USERPWD());
		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, loginHospital.getNH_USERID());
			cstmt.setString(2, loginHospital.getNH_USERPWD());
			
			cstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(cstmt);
		}
		
	}

	public void sample(Connection conn, NursingHospitalVo loginHospital) {
		System.out.println("기본 테이블 생성 ");
		Statement stmt = null;
		String Str[] = {"EMPMENT", "DEDUCTION", "ALLOWANCE", "WARD", "POSITIONS", "DEPARTMENT", "TEAM", "AUTHORITY", "AUTHORITYSAM", "HOLD_OFFICE", "EMPLOYEE", "SEQ_EMP_ID", "EMPSALARY", "DEPENDENTS", "DPEN_SEQ_CODE", "EDUCATION", "EDU_SEQ_CODE", "CAREER", "CAR_SEQ_CODE", "PATIENT", "PAT_SEQ", "MEDICIENRECORD", "MR_SEQ", "COUNSELINGLOG", "CL_SEQ", "SEQ_CNOTICE", "SEQ_QNA", "CAUTHORITY", "CAUTHORITYSAM", "SERVICE", "SERVICESAM", "NURSING_HOSPITAL", "DATAROOM", "DATAROOM_SEQ", "NOTICE", "NOTICE_SEQ", "QNA", "COMMENTS", "CNOTICE", "CREGISTRATION", "CALENDAR", "SEQ_CAL","DEDUCTIONSAM1","DEDUCTIONSAM2","DEDUCTIONSAM3","DEDUCTIONSAM4","ALLOWANCESAM1","ALLOWANCESAM2","ALLOWANCESAM3","ALLOWANCESAM4","ALLOWANCESAM5","WARDSAM1","WARDSAM2","WARDSAM3","DEPARTMENTSAM1","DEPARTMENTSAM2","DEPARTMENTSAM3","DEPARTMENTSAM4","DEPARTMENTSAM5","DEPARTMENTSAM6","TEAMSAM1","TEAMSAM2","TEAMSAM3","TEAMSAM4","TEAMSAM5","TEAMSAM6","TEAMSAM7","TEAMSAM8","TEAMSAM9","TEAMSAM10","TEAMSAM11","TEAMSAM12","TEAMSAM13","TEAMSAM14","TEAMSAM15","TEAMSAM16","TEAMSAM17","TEAMSAM18","POSITIONSSAM1","POSITIONSSAM2","POSITIONSSAM3","POSITIONSSAM4","POSITIONSSAM5","POSITIONSSAM6","EMPMENTSAM1","EMPMENTSAM2","EMPMENTSAM3","EMPMENTSAM4","EMPMENTSAM5","EMPMENTSAM6","EMPMENTSAM7","HOLD_OFFICESAM1","HOLD_OFFICESAM2","HOLD_OFFICESAM3","AUTHORITYSAM1","AUTHORITYSAM4"};
		System.out.println("Str length : "+Str.length);
		for(int i = 0; i <= Str.length-1; i++) {
			String query = prop.getProperty(Str[i]);
			
			try {
				stmt = conn.createStatement();
				stmt.executeQuery(query);
				
				System.out.println(i+"번째 "+Str[i]+"스크립트 작동");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(stmt);
			}
		}
	}

	public ArrayList<NursingHospitalVo> selectAllList(Connection conn) {
		ArrayList<NursingHospitalVo> list = new ArrayList<NursingHospitalVo>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from NURSING_HOSPITAL order by nh_id asc";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				NursingHospitalVo NH = new NursingHospitalVo();
				
				NH.setNH_ID(rset.getInt("NH_ID"));
				NH.setNH_NAME(rset.getString("NH_NAME"));
				NH.setNH_DATE(rset.getDate("NH_DATE"));
				NH.setNH_RSN_DATE(rset.getDate("NH_RSN_DATE"));
				NH.setNH_NO(rset.getString("NH_NO"));
				NH.setNH_ADDRESS(rset.getString("NH_ADDRESS"));
				NH.setNH_ITNAL_FOR(rset.getString("NH_ITNAL_FOR"));
				NH.setNH_AD_TEL(rset.getString("NH_AD_TEL"));
				NH.setNH_PHONE(rset.getString("NH_PHONE"));
				NH.setNH_EMAIL(rset.getString("NH_EMAIL"));
				NH.setNH_USERID(rset.getString("NH_USERID"));
				NH.setNH_USERPWD(rset.getString("NH_USERPWD"));
				NH.setNH_ETC(rset.getString("NH_ETC"));
				NH.setNH_IMG(rset.getString("NH_IMG"));
				NH.setGENDER(rset.getString("GENDER"));
				NH.setCOMPANY_NAME(rset.getString("COMPANY_NAME"));
				NH.setCOMPANY_NO(rset.getString("COMPANY_NO"));
				NH.setNH_SERVICE_CODE(rset.getString("NH_SERVICE_CODE"));
				NH.setAUTHORITY_CODE(rset.getString("NH_AUTHORITY_CODE"));
				NH.setNH_SERVICE_HISTORY(rset.getString("NH_SERVICE_HISTORY"));
			
				list.add(NH);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public NursingHospitalVo selectOne(Connection conn, String cname) {
		NursingHospitalVo NH = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select nh_userid, nh_userpwd from nursing_hospital where company_name = ?";
		System.out.println(cname);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cname);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				NH = new NursingHospitalVo();
				
				NH.setNH_USERID(rset.getString("NH_USERID"));
				NH.setNH_USERPWD(rset.getString("NH_USERPWD"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return NH;
	}

	public String NHcount(Connection conn) {
		String result = "0";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select COUNT(*)-1 from nursing_hospital where nh_authority_code != 'G0'";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	public String NHServiceCount(Connection conn) {
		String result = "0";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from NURSING_HOSPITAL where NH_SERVICE_CODE = 'GS1'";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
			
		return result;
	}	
}
