package com.minje.nhmp.ERP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.minje.nhmp.ERP.vo.CalendarVo;
import static com.minje.nhmp.common.JDBCTemplate.close;

public class CalendarDao {

	public ArrayList<CalendarVo> listCalendar(Connection conn, JSONObject sendJson, String adminid) {
		ArrayList<CalendarVo> list = new ArrayList<CalendarVo>();
		
		PreparedStatement pstmt = null;
		ResultSet rest = null; 

		String query = "select cal_num, id_, title, description, " + 
				"to_char(start_date, 'yyyy-mm-dd')||'T'||to_char(start_date, 'HH24:MM') as start_date, " + 
				"to_char(end_date, 'yyyy-mm-dd')||'T'||to_char(end_date, 'HH24:MM') as end_date, " + 
				"cate_gory, backgroundcolor, textcolor from calendar where id_ = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, adminid);
			
			rest = pstmt.executeQuery();
			
			while(rest.next()) {
				CalendarVo cal = new CalendarVo();
				cal.setCalnum(rest.getInt("cal_num"));
				cal.setTitle(rest.getString("title"));
				cal.setDescription(rest.getString("description"));
				cal.setStartdate(rest.getString("start_date"));
				cal.setEnddate(rest.getString("end_date"));
				cal.setCategory(rest.getString("cate_gory"));
				cal.setBackgroundcolor(rest.getString("backgroundcolor"));
				cal.setTextcolor(rest.getString("textcolor"));
				cal.setUsername(rest.getString("id_"));
				list.add(cal);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return list;
	}

	public void InsertCalendar(Connection conn, JSONObject sendJson, String adminid) {
		
		System.out.println(sendJson);
		
		PreparedStatement pstmt = null;
		String query = "INSERT INTO CALENDAR VALUES (seq_cal.nextval, "
				+ "?, ?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi'), "
				+ "to_date(?, 'yyyy-mm-dd hh24:mi'), "
				+ "?, ?, DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, adminid);
			pstmt.setString(2, "");
			pstmt.setString(3, String.valueOf(sendJson.get("title")));
			pstmt.setString(4, String.valueOf(sendJson.get("description")));
			pstmt.setString(5, String.valueOf(sendJson.get("start")));
			pstmt.setString(6, String.valueOf(sendJson.get("end")));
			pstmt.setString(7, String.valueOf(sendJson.get("type")));
			pstmt.setString(8, String.valueOf(sendJson.get("backgroundColor")));
		
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return;
	}

	public void updateCalendar(Connection conn, JSONObject sendJson) {
		PreparedStatement pstmt = null;
		
		String query = "update calendar set title = ?, description = ?, "
				+ "start_date = to_date(?, 'yyyy-mm-dd hh24:mi'), "
				+ "end_date = to_date(?, 'yyyy-mm-dd hh24:mi'), "
				+ "cate_gory = ?, backgroundcolor = ? where cal_num = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, String.valueOf(sendJson.get("title")));
			pstmt.setString(2, String.valueOf(sendJson.get("description")));
			pstmt.setString(3, String.valueOf(sendJson.get("start")));
			pstmt.setString(4, String.valueOf(sendJson.get("end")));
			pstmt.setString(5, String.valueOf(sendJson.get("type")));
			pstmt.setString(6, String.valueOf(sendJson.get("backgroundColor")));
			pstmt.setInt(7, Integer.parseInt((String) sendJson.get("_id")));
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return;
	}

	public void deleteCalendar(Connection conn, JSONObject sendJson) {
		PreparedStatement pstmt = null;
		
		String query = "delete from calendar where cal_num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt((String) sendJson.get("_id")));
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return;
		
	}

	public ArrayList<CalendarVo> EmployeelistCalendar(Connection conn, JSONObject sendJson, String empname) {
		ArrayList<CalendarVo> list = new ArrayList<CalendarVo>();
		
		PreparedStatement pstmt = null;
		ResultSet rest = null; 

		String query = "select cal_num, emp_name, title, description, " + 
				" to_char(start_date, 'yyyy-mm-dd')||'T'||to_char(start_date, 'HH24:MM') as start_date, " + 
				" to_char(end_date, 'yyyy-mm-dd')||'T'||to_char(end_date, 'HH24:MM') as end_date, " + 
				" cate_gory, backgroundcolor, textcolor from calendar where emp_name = ?";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empname);
			
			rest = pstmt.executeQuery();
			
			while(rest.next()) {
				CalendarVo cal = new CalendarVo();
				cal.setCalnum(rest.getInt("cal_num"));
				cal.setTitle(rest.getString("title"));
				cal.setDescription(rest.getString("description"));
				cal.setStartdate(rest.getString("start_date"));
				cal.setEnddate(rest.getString("end_date"));
				cal.setCategory(rest.getString("cate_gory"));
				cal.setBackgroundcolor(rest.getString("backgroundcolor"));
				cal.setTextcolor(rest.getString("textcolor"));
				cal.setempname(rest.getString("emp_name"));
				list.add(cal);
				System.out.println(cal);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return list;
	}

	public void EmployeeInsertCalendar(Connection conn, JSONObject sendJson, String empname) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO CALENDAR VALUES (seq_cal.nextval, "
				+ "?, ?, ?, ?, to_date(?, 'yyyy-mm-dd hh24:mi'), "
				+ "to_date(?, 'yyyy-mm-dd hh24:mi'), "
				+ "?, ?, DEFAULT)";
//		String query = "insert into calendar(cal_num, title, description, start, end, type, backgrouondColor ) values(seq_cal.nextval,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "");
			pstmt.setString(2, empname);
			pstmt.setString(3, String.valueOf(sendJson.get("title")));
			pstmt.setString(4, String.valueOf(sendJson.get("description")));
			pstmt.setString(5, String.valueOf(sendJson.get("start")));
			pstmt.setString(6, String.valueOf(sendJson.get("end")));
			pstmt.setString(7, String.valueOf(sendJson.get("type")));
			pstmt.setString(8, String.valueOf(sendJson.get("backgroundColor")));
			
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return;
		
	}

	public ArrayList<CalendarVo> EmployeeSelectList(Connection conn, String empname) {
		ArrayList<CalendarVo> list = new ArrayList<CalendarVo>();
		
		PreparedStatement pstmt = null;
		ResultSet rest = null; 

		String query = "select title, description, start_date " + 
				" from calendar where emp_name = ? and to_char( start_date, 'yyyymmdd' ) = to_char( sysdate, 'yyyymmdd')";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empname);
			
			rest = pstmt.executeQuery();
			
			while(rest.next()) {
				CalendarVo cal = new CalendarVo();
				cal.setTitle(rest.getString("title"));
				cal.setDescription(rest.getString("description"));
				cal.setStartdate(rest.getString("start_date"));

				list.add(cal);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return list;
	}

}
