package com.minje.nhmp.ERP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.minje.nhmp.ERP.vo.EmpSalaryVo;
import static com.minje.nhmp.common.JDBCTemplate.close;
public class EmpSalaryDao {

	public EmpSalaryDao() {}

	public int insertEmpSalary(Connection conn, EmpSalaryVo empSal) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "insert into empsalary values("
				+ "?, ?, ?, ?, ? ,? ,? ,? ,? ,? , ?, ? ,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empSal.getEmpId());
			pstmt.setString(2, empSal.getEmentInrance());
			pstmt.setString(3, empSal.getNatPension());
			pstmt.setString(4, empSal.getHealInrance());
			pstmt.setInt(5, empSal.getHealRdc());
			pstmt.setString(6, empSal.getOldInrance());
			pstmt.setInt(7, empSal.getOldRdc());
			pstmt.setString(8, empSal.getEarIncome());
			pstmt.setInt(9, empSal.getEarInrance());
			pstmt.setString(10, empSal.getBsnIncome());
			pstmt.setString(11, empSal.getDailyJob());
			pstmt.setString(12, empSal.getEtcIncome());
			pstmt.setString(13, empSal.getEarBsnIncome());
			
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public EmpSalaryVo selectOne(Connection conn, String empId) {
		EmpSalaryVo empSal = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "select * from empsalary where emp_Id = ?";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				empSal = new EmpSalaryVo();
				
				empSal.setEmentInrance(rs.getString("EMENT_INRANCE"));
				empSal.setNatPension(rs.getString("NAT_PENSION"));
				empSal.setHealInrance(rs.getString("HEAL_INRANCE"));
				empSal.setHealRdc(rs.getInt("HEAL_RDC"));
				empSal.setOldInrance(rs.getString("OLD_INRANCE"));
				empSal.setOldRdc(rs.getInt("OLD_RDC"));
				empSal.setEarIncome(rs.getString("EAR_INCOME"));
				empSal.setEarInrance(rs.getInt("EAR_INRANCE"));
				empSal.setBsnIncome(rs.getString("BSN_INCOME"));
				empSal.setDailyJob(rs.getString("DAILY_JOB"));
				empSal.setEtcIncome(rs.getString("ETC_INCOME"));
				empSal.setEarBsnIncome(rs.getString("EAR_BSN_INCOME"));

				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return empSal;
	}

	public int updateEmpSalary(Connection conn, EmpSalaryVo empSal) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update empsalary set EMENT_INRANCE =?, NAT_PENSION = ?, HEAL_INRANCE = ?, "
				+ "HEAL_RDC = ?, OLD_INRANCE = ?, OLD_RDC = ?, EAR_INCOME = ?, EAR_INRANCE = ?, "
				+ " BSN_INCOME = ?, DAILY_JOB = ?, ETC_INCOME = ?, EAR_BSN_INCOME = ? "
				+ "where emp_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, empSal.getEmentInrance());
			pstmt.setString(2, empSal.getNatPension());
			pstmt.setString(3, empSal.getHealInrance());
			pstmt.setInt(4, empSal.getHealRdc());
			pstmt.setString(5, empSal.getOldInrance());
			pstmt.setInt(6, empSal.getOldRdc());
			pstmt.setString(7, empSal.getEarIncome());
			pstmt.setInt(8, empSal.getEarInrance());
			pstmt.setString(9, empSal.getBsnIncome());
			pstmt.setString(10, empSal.getDailyJob());
			pstmt.setString(11, empSal.getEtcIncome());
			pstmt.setString(12, empSal.getEarBsnIncome());
			pstmt.setString(13, empSal.getEmpId());
			
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
}
