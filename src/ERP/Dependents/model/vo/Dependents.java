package ERP.Dependents.model.vo;

import java.io.Serializable;

public class Dependents implements Serializable{
private static final long serialVersionUID = 5111L;
	

	private String rship;//직원과의 관계
	private String fName;//가족성명
	private String itNalfor;//내/외국인 구분
	private String dibility;//장애여부
	private String hIsc;//건강보험여부
	private String iTogether;//동거여부
	private String mChild;//다자녀유무
	private String depId;//사원번호
	
	
	public Dependents() {}


	public Dependents(String rship, String fName, String itNalfor, String dibility, String hIsc,
			String iTogether, String mChild, String depId) {
		super();
		this.rship = rship;
		this.fName = fName;
		this.itNalfor = itNalfor;
		this.dibility = dibility;
		this.hIsc = hIsc;
		this.iTogether = iTogether;
		this.mChild = mChild;
		this.depId = depId;
	}



	



	public String getRship() {
		return rship;
	}


	public void setRship(String rship) {
		this.rship = rship;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getItNalfor() {
		return itNalfor;
	}


	public void setItNalfor(String itNalfor) {
		this.itNalfor = itNalfor;
	}

	public String getDibility() {
		return dibility;
	}


	public void setDibility(String dibility) {
		this.dibility = dibility;
	}


	public String gethIsc() {
		return hIsc;
	}


	public void sethIsc(String hIsc) {
		this.hIsc = hIsc;
	}


	public String getiTogether() {
		return iTogether;
	}


	public void setiTogether(String iTogether) {
		this.iTogether = iTogether;
	}


	public String getmChild() {
		return mChild;
	}


	public void setmChild(String mChild) {
		this.mChild = mChild;
	}


	public String getDepId() {
		return depId;
	}


	public void setDepId(String depId) {
		this.depId = depId;
	}


	@Override
	public String toString() {
		return "Dependents [rship=" + rship + ", fName=" + fName + ", itNalfor=" + itNalfor
				+ ", dibility=" + dibility + ", hIsc=" + hIsc + ", iTogether=" + iTogether + ", mChild=" + mChild
				+ ", depId=" + depId + "]";
	}
}
