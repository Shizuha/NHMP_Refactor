package ERP.Ward.model.vo;

import java.io.Serializable;

public class Ward implements Serializable{
private static final long serialVersionUID = 5111L;
	
	
	
	private String wardCode;
	private String wardName;
	
	public Ward() {}

	public Ward(String wardCode, String wardName) {
		super();
		this.wardCode = wardCode;
		this.wardName = wardName;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	@Override
	public String toString() {
		return "Ward [wardCode=" + wardCode + ", wardName=" + wardName + "]";
	}
}
