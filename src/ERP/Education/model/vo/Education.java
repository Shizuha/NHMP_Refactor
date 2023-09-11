package ERP.Education.model.vo;

import java.io.Serializable;

public class Education implements Serializable{
	
	private static final long serialVersionUID = 5999L;
	
	
	private String itnalFor;
	private String adDate;
	private String grDate;
	private String schName;
	private String major;
	private String taking;
	private String eduId;
	
	public Education () {}

	

	public Education(String itnalFor, String adDate, String grDate, String schName, String major,
			String taking, String eduId) {
		super();
		
		this.itnalFor = itnalFor;
		this.adDate = adDate;
		this.grDate = grDate;
		this.schName = schName;
		this.major = major;
		this.taking = taking;
		this.eduId = eduId;
	}




	public String getItnalFor() {
		return itnalFor;
	}

	public void setItnalFor(String itnalFor) {
		this.itnalFor = itnalFor;
	}

	public String getAdDate() {
		return adDate;
	}

	public void setAdDate(String adDate) {
		this.adDate = adDate;
	}

	public String getGrDate() {
		return grDate;
	}

	public void setGrDate(String grDate) {
		this.grDate = grDate;
	}

	public String getSchName() {
		return schName;
	}

	public void setSchName(String schName) {
		this.schName = schName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTaking() {
		return taking;
	}

	public void setTaking(String taking) {
		this.taking = taking;
	}

	public String getEduId() {
		return eduId;
	}

	public void setEduId(String eduId) {
		this.eduId = eduId;
	}



	@Override
	public String toString() {
		return "Education [itnalFor=" + itnalFor + ", adDate=" + adDate + ", grDate=" + grDate
				+ ", schName=" + schName + ", major=" + major + ", taking=" + taking + ", eduId=" + eduId + "]";
	}
}
