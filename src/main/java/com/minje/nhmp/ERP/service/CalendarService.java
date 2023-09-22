package com.minje.nhmp.ERP.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.minje.nhmp.ERP.vo.CalendarVo;
import com.minje.nhmp.ERP.dao.CalendarDao;

import static com.minje.nhmp.common.JDBCTemplate.*;

public class CalendarService {
	
	CalendarDao cdao = new CalendarDao();

	public void InsertCalendar(JSONObject sendJson, String adminid) {
		Connection conn = getConnection();
		cdao.InsertCalendar(conn, sendJson, adminid);
		commit(conn);
		close(conn);
	}

	public ArrayList<CalendarVo> listCalendar(JSONObject sendJson, String adminid) {
		Connection conn = getConnection();
		ArrayList<CalendarVo> list = cdao.listCalendar(conn, sendJson, adminid);
		close(conn);
		return list;
	}

	public void updateCalendar(JSONObject sendJson, String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		cdao.updateCalendar(conn, sendJson);
		commit(conn);
		close(conn);
	}

	public void deleteCalendar(JSONObject sendJson, String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		cdao.deleteCalendar(conn, sendJson);
		commit(conn);
		close(conn);
		
	}

	public ArrayList<CalendarVo> EmployeelistCalendar(JSONObject sendJson, String empname, String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<CalendarVo> list = cdao.EmployeelistCalendar(conn, sendJson, empname);
		close(conn);
		return list;
	}

	public void EmployeeInsertCalendar(JSONObject sendJson, String empname, String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		cdao.EmployeeInsertCalendar(conn, sendJson, empname);
		commit(conn);
		close(conn);
		
	}

	public ArrayList<CalendarVo> EmployeeSelectList(String empname, String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<CalendarVo> list = cdao.EmployeeSelectList(conn, empname);
		close(conn);
		return list;
	}

	public void AdmindeleteCalendar(JSONObject sendJson) {
		Connection conn = getConnection();
		cdao.deleteCalendar(conn, sendJson);
		commit(conn);
		close(conn);
	}

	public void AdminupdateCalendar(JSONObject sendJson) {
		Connection conn = getConnection();
		cdao.updateCalendar(conn, sendJson);
		commit(conn);
		close(conn);
	}

	
	

}
