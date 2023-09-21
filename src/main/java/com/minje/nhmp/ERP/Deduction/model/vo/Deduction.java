package ERP.Deduction.model.vo;

import java.io.Serializable;

public class Deduction implements Serializable{
	private static final long serialVersionUID = 8555L;
	
	private String DEDUCTION_CODE;
	private int DEDUCTION_NO;
	private String DEDUCTION_NAME;
	private String DEDUCTION_FORMULA;
	private String DEDUCTION_ETC;
	
	public Deduction () {}

	public void setDEDUCTION_CODE(String dEDUCTION_CODE) {
		DEDUCTION_CODE = dEDUCTION_CODE;
	}

	public void setDEDUCTION_NO(int dEDUCTION_NO) {
		DEDUCTION_NO = dEDUCTION_NO;
	}

	public void setDEDUCTION_NAME(String dEDUCTION_NAME) {
		DEDUCTION_NAME = dEDUCTION_NAME;
	}

	public void setDEDUCTION_FORMULA(String dEDUCTION_FORMULA) {
		DEDUCTION_FORMULA = dEDUCTION_FORMULA;
	}

	public void setDEDUCTION_ETC(String dEDUCTION_ETC) {
		DEDUCTION_ETC = dEDUCTION_ETC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDEDUCTION_CODE() {
		return DEDUCTION_CODE;
	}

	public int getDEDUCTION_NO() {
		return DEDUCTION_NO;
	}

	public String getDEDUCTION_NAME() {
		return DEDUCTION_NAME;
	}

	public String getDEDUCTION_FORMULA() {
		return DEDUCTION_FORMULA;
	}

	public String getDEDUCTION_ETC() {
		return DEDUCTION_ETC;
	}

	@Override
	public String toString() {
		return "Deduction [DEDUCTION_CODE=" + DEDUCTION_CODE + ", DEDUCTION_NO=" + DEDUCTION_NO + ", DEDUCTION_NAME="
				+ DEDUCTION_NAME + ", DEDUCTION_FORMULA=" + DEDUCTION_FORMULA + ", DEDUCTION_ETC=" + DEDUCTION_ETC
				+ "]";
	}
	
	
	

}
