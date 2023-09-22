package com.minje.nhmp.ERP.vo;

import java.io.Serializable;

public class WardVo implements Serializable{
private static final long serialVersionUID = 5111L;
	
	
	
	private String wardCode;
	private String wardName;
	
	public WardVo() {}

	public WardVo(String wardCode, String wardName) {
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
