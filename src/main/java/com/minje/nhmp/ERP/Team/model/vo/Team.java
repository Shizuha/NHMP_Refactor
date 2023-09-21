package ERP.Team.model.vo;

import java.io.Serializable;

public class Team implements Serializable{
private static final long serialVersionUID = 5111L;
	
	
	private String teamCode;
	private String teamName;
	private String posCode;
	
	
	public Team() {}


	public Team(String teamCode, String teamName, String posCode) {
		super();
		this.teamCode = teamCode;
		this.teamName = teamName;
		this.posCode = posCode;
	}


	public String getTeamCode() {
		return teamCode;
	}


	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}


	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	public String getPosCode() {
		return posCode;
	}


	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}


	@Override
	public String toString() {
		return "Team [teamCode=" + teamCode + ", teamName=" + teamName + ", posCode=" + posCode + "]";
	}

}
