package com.minje.nhmp.ERP.dao;

import static com.minje.nhmp.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.minje.nhmp.ERP.vo.TeamVo;

public class TeamDao {
	public String selectTeamName(Connection conn, String teamCode) {
		String teamName = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select team_name from team where team_code = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, teamCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				teamName = rs.getString("team_name");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return teamName;
	}

	public ArrayList<TeamVo> selectOrganTeamName(Connection conn, String deptName) {
		ArrayList<TeamVo> team = new ArrayList<TeamVo>();
		TeamVo t = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * " + 
				"from team " + 
				"where UP_DEPT = ( " + 
				"select dept_code " + 
				"from department " + 
				"where dept_name like ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + deptName + "%");
			
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				t = new TeamVo();
				t.setTeamCode(rs.getString("team_code"));
				t.setTeamName(rs.getString("team_name"));
				
				
				team.add(t);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return team;
	}

	public TeamVo selectTeamCode(Connection conn, String teamName) {
		TeamVo t = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "select * from team where team_name like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + teamName + "%");
			
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				t = new TeamVo();
				t.setTeamCode(rs.getString("team_code"));
				t.setTeamName(rs.getString("team_name"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return t;
	}

	
	
	
}
