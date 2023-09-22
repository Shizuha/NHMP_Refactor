package com.minje.nhmp.ERP.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.minje.nhmp.ERP.dao.TeamDao;
import com.minje.nhmp.ERP.vo.TeamVo;
import static com.minje.nhmp.common.JDBCTemplate.*;

public class TeamService {

	
	private TeamDao tDao = new TeamDao();

	public ArrayList<TeamVo> selectAll() {
		
		return null;
	}

	public String selectTeamName(String teamCode, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		String deptName = tDao.selectTeamName(conn, teamCode);
		
		close(conn);
		return deptName;
	}

	public ArrayList<TeamVo> selectOrganTeamName(String deptName, String hostId, String hostPwd) {
		Connection conn = getConnection(hostId, hostPwd);
		ArrayList<TeamVo> team = tDao.selectOrganTeamName(conn, deptName);
		
		close(conn);
		return team;
	}

	public TeamVo selectTeamCode(String hostId, String hostPwd, String teamName) {
		Connection conn = getConnection(hostId, hostPwd);
		TeamVo t = tDao.selectTeamCode(conn, teamName);
		close(conn);
		return t;
	}

	
	
}








