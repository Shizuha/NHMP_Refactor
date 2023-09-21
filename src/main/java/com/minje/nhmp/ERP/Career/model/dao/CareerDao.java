package ERP.Career.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ERP.Career.model.vo.Career;
import static common.JDBCTemplate.close;

public class CareerDao {
	public int insertCar(Connection conn, Career c) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into career values(CAR_SEQ_CODE.NEXTVAL,?,?,?,?,?,?,?,?)";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, c.getCarId());
				pstmt.setString(2, c.getComName());
				pstmt.setString(3, c.getHireDate());
				pstmt.setString(4, c.getLastDate());
				pstmt.setString(5, c.getWorkTeam());
				pstmt.setString(6, c.getLastPosition());
				pstmt.setString(7, c.getResBilties());
				pstmt.setString(8, c.getLeaveReason());
					
			
				result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Career> selectList(Connection conn, String empId) {
		ArrayList<Career> carList = new ArrayList<Career>();
		Career car = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from career where car_id = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				car = new Career();
				
				car.setComName(rs.getString("COM_NAME"));
				car.setHireDate(rs.getString("HIRE_DATE"));
				car.setLastDate(rs.getString("LAST_DATE"));
				car.setWorkTeam(rs.getString("WORK_TEAM"));
				car.setLastPosition(rs.getString("LAST_POSITION"));
				car.setResBilties(rs.getString("RES_BILTIES"));
				car.setLeaveReason(rs.getString("LEAVE_REASON"));
				
				
				carList.add(car);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return carList;
	}

	public int updateCar(Connection conn, Career c) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update career set COM_NAME = ?, HIRE_DATE = ?, LAST_DATE = ?, WORK_TEAM = ?, "
				+ "LAST_POSITION = ?, RES_BILTIES = ?, LEAVE_REASON = ? "
				+ "where car_id = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
				
				pstmt.setString(1, c.getComName());
				pstmt.setString(2, c.getHireDate());
				pstmt.setString(3, c.getLastDate());
				pstmt.setString(4, c.getWorkTeam());
				pstmt.setString(5, c.getLastPosition());
				pstmt.setString(6, c.getResBilties());
				pstmt.setString(7, c.getLeaveReason());
				pstmt.setString(8, c.getCarId());
			
				result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public String[] selectCarCode(String empId, int length, Connection conn) {
		String[] carCode = new String[length];
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select car_code from career where car_id = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				carCode[i] = rs.getString("car_code");
				
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return carCode;
	}
}
