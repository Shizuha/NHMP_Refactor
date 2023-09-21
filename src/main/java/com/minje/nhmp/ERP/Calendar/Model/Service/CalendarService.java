package com.minje.nhmp.ERP.Calendar.Model.Service;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.minje.nhmp.ERP.Calendar.Model.dao.CalendarDao;
import com.minje.nhmp.ERP.Calendar.Model.vo.Calendar;
import com.minje.nhmp.Main.vo.NursingHospitalVo;

import static com.minje.nhmp.common.JDBCTemplate.*;

public class CalendarService {
	
	CalendarDao cdao = new CalendarDao();

	public void InsertCalendar(JSONObject sendJson, String adminid) {
		Connection conn = getConnection();
		cdao.InsertCalendar(conn, sendJson, adminid);
		commit(conn);
		close(conn);
	}

	public ArrayList<Calendar> listCalendar(JSONObject sendJson, String adminid) {
		Connection conn = getConnection();
		ArrayList<Calendar> list = cdao.listCalendar(conn, sendJson, adminid);
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

	public ArrayList<Calendar> EmployeelistCalendar(JSONObject sendJson, String empname, String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Calendar> list = cdao.EmployeelistCalendar(conn, sendJson, empname);
		close(conn);
		return list;
	}

	public void EmployeeInsertCalendar(JSONObject sendJson, String empname, String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		cdao.EmployeeInsertCalendar(conn, sendJson, empname);
		commit(conn);
		close(conn);
		
	}

	public ArrayList<Calendar> EmployeeSelectList(String empname, String hostid, String hostpwd) {
		Connection conn = getConnection(hostid, hostpwd);
		ArrayList<Calendar> list = cdao.EmployeeSelectList(conn, empname);
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
