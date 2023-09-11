package Main.vo;

import java.io.Serializable;

public class CregistrationVo implements Serializable{
	private static final long serialVersionUID = 8222L;
	private int NH_ID;
	private String FUNCTION_NAME;
	private String CREGISTRATION_CHECK;

	public CregistrationVo() {}

	public void setNH_ID(int nH_ID) {
		NH_ID = nH_ID;
	}

	public void setFUNCTION_NAME(String fUNCTION_NAME) {
		FUNCTION_NAME = fUNCTION_NAME;
	}

	public void setCREGISTRATION_CHECK(String cREGISTRATION_CHECK) {
		CREGISTRATION_CHECK = cREGISTRATION_CHECK;
	}

	public int getNH_ID() {
		return NH_ID;
	}

	public String getFUNCTION_NAME() {
		return FUNCTION_NAME;
	}

	public String getCREGISTRATION_CHECK() {
		return CREGISTRATION_CHECK;
	}

	@Override
	public String toString() {
		return "Cregistration [NH_ID=" + NH_ID + ", FUNCTION_NAME=" + FUNCTION_NAME + ", CREGISTRATION_CHECK="
				+ CREGISTRATION_CHECK + "]";
	}
	
	
	
}
