package com.minje.nhmp.ERP.vo;

import java.io.Serializable;

public class AllowanceVo implements Serializable{
	private static final long serialVersionUID = 8666L;

	private String ALLOWANCE_CODE;
	private int ALLOWANCE_NO;
	private String ALLOWANCE_NAME;
	private String ALLOWANCE_FORMULA;
	private String ALLOWANCE_ETC;
	
	public AllowanceVo () {}

	public void setALLOWANCE_CODE(String aLLOWANCE_CODE) {
		ALLOWANCE_CODE = aLLOWANCE_CODE;
	}

	public void setALLOWANCE_NO(int aLLOWANCE_NO) {
		ALLOWANCE_NO = aLLOWANCE_NO;
	}

	public void setALLOWANCE_NAME(String aLLOWANCE_NAME) {
		ALLOWANCE_NAME = aLLOWANCE_NAME;
	}

	public void setALLOWANCE_FORMULA(String aLLOWANCE_FORMULA) {
		ALLOWANCE_FORMULA = aLLOWANCE_FORMULA;
	}

	public void setALLOWANCE_ETC(String aLLOWANCE_ETC) {
		ALLOWANCE_ETC = aLLOWANCE_ETC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getALLOWANCE_CODE() {
		return ALLOWANCE_CODE;
	}

	public int getALLOWANCE_NO() {
		return ALLOWANCE_NO;
	}

	public String getALLOWANCE_NAME() {
		return ALLOWANCE_NAME;
	}

	public String getALLOWANCE_FORMULA() {
		return ALLOWANCE_FORMULA;
	}

	public String getALLOWANCE_ETC() {
		return ALLOWANCE_ETC;
	}

	@Override
	public String toString() {
		return "Allowance [ALLOWANCE_CODE=" + ALLOWANCE_CODE + ", ALLOWANCE_NO=" + ALLOWANCE_NO + ", ALLOWANCE_NAME="
				+ ALLOWANCE_NAME + ", ALLOWANCE_FORMULA=" + ALLOWANCE_FORMULA + ", ALLOWANCE_ETC=" + ALLOWANCE_ETC
				+ "]";
	}

	
}
