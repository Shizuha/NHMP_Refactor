package ERP.Career.model.vo;

import java.io.Serializable;

public class Career implements Serializable{
private static final long serialVersionUID = 5222L;
	
	private String carId;
	private String comName;
	private String hireDate;
	private String lastDate;
	private String workTeam;
	private String lastPosition;
	private String resBilties;
	private String leaveReason;
	
	
	public Career() {}



	public Career(String carId, String comName, String hireDate, String lastDate, String workTeam,
			String lastPosition, String resBilties, String leaveReason) {
		super();
		this.carId = carId;
		this.comName = comName;
		this.hireDate = hireDate;
		this.lastDate = lastDate;
		this.workTeam = workTeam;
		this.lastPosition = lastPosition;
		this.resBilties = resBilties;
		this.leaveReason = leaveReason;
	}

	




	public String getCarId() {
		return carId;
	}



	public void setCarId(String carId) {
		this.carId = carId;
	}



	public String getComName() {
		return comName;
	}


	public void setComName(String comName) {
		this.comName = comName;
	}


	public String getHireDate() {
		return hireDate;
	}


	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}


	public String getLastDate() {
		return lastDate;
	}


	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}


	public String getWorkTeam() {
		return workTeam;
	}


	public void setWorkTeam(String workTeam) {
		this.workTeam = workTeam;
	}


	public String getLastPosition() {
		return lastPosition;
	}


	public void setLastPosition(String lastPosition) {
		this.lastPosition = lastPosition;
	}


	public String getResBilties() {
		return resBilties;
	}


	public void setResBilties(String resBilties) {
		this.resBilties = resBilties;
	}


	public String getLeaveReason() {
		return leaveReason;
	}


	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}



	@Override
	public String toString() {
		return "Career [carId=" + carId + ", comName=" + comName + ", hireDate=" + hireDate
				+ ", lastDate=" + lastDate + ", workTeam=" + workTeam + ", lastPosition=" + lastPosition
				+ ", resBilties=" + resBilties + ", leaveReason=" + leaveReason + "]";
	}
}
