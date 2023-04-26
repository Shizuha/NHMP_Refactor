package ERP.Position.model.vo;

import java.io.Serializable;

public class Position implements Serializable{
private static final long serialVersionUID = 5888L;
	
	private String posCode;
	private String posName;
	
	
	public Position() {}


	public Position(String posCode, String posName) {
		super();
		this.posCode = posCode;
		this.posName = posName;
	}


	public String getPosCode() {
		return posCode;
	}


	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}


	public String getPosName() {
		return posName;
	}


	public void setPosName(String posName) {
		this.posName = posName;
	}


	@Override
	public String toString() {
		return "Position [posCode=" + posCode + ", posName=" + posName + "]";
	}
}
