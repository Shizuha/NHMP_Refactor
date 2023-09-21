package ERP.Cauthority.model.vo;

import java.io.Serializable;

public class Cauthority implements Serializable{
	private static final long serialVersionUID = 8555L;
	
	private String AUTHORITY_CODE;
	private String AUTHORITY_NAME;
	private String AUTHORITY_ETC;
	private String AUTHORITY_MENU;

	public Cauthority() {}

	public void setAUTHORITY_CODE(String aUTHORITY_CODE) {
		AUTHORITY_CODE = aUTHORITY_CODE;
	}

	public void setAUTHORITY_NAME(String aUTHORITY_NAME) {
		AUTHORITY_NAME = aUTHORITY_NAME;
	}

	public void setAUTHORITY_ETC(String aUTHORITY_ETC) {
		AUTHORITY_ETC = aUTHORITY_ETC;
	}

	public void setAUTHORITY_MENU(String aUTHORITY_MENU) {
		AUTHORITY_MENU = aUTHORITY_MENU;
	}

	public String getAUTHORITY_CODE() {
		return AUTHORITY_CODE;
	}

	public String getAUTHORITY_NAME() {
		return AUTHORITY_NAME;
	}

	public String getAUTHORITY_ETC() {
		return AUTHORITY_ETC;
	}

	public String getAUTHORITY_MENU() {
		return AUTHORITY_MENU;
	}

	@Override
	public String toString() {
		return "Cauthority [AUTHORITY_CODE=" + AUTHORITY_CODE + ", AUTHORITY_NAME=" + AUTHORITY_NAME
				+ ", AUTHORITY_ETC=" + AUTHORITY_ETC + ", AUTHORITY_MENU=" + AUTHORITY_MENU + "]";
	}

	
}
