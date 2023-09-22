package com.minje.nhmp.ERP.vo;

import java.io.Serializable;

public class AuthorityVo  implements Serializable{
	private static final long serialVersionUID = 5374L;
	
	private String authorityCode;
	private String authorityName;
	private String authorityEtc;
	
	
	public AuthorityVo() {}


	public AuthorityVo(String authorityCode, String authorityName, String authorityEtc) {
		super();
		this.authorityCode = authorityCode;
		this.authorityName = authorityName;
		this.authorityEtc = authorityEtc;
	}


	public String getAuthorityCode() {
		return authorityCode;
	}


	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}


	public String getAuthorityName() {
		return authorityName;
	}


	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}


	public String getAuthorityEtc() {
		return authorityEtc;
	}


	public void setAuthorityEtc(String authorityEtc) {
		this.authorityEtc = authorityEtc;
	}


	@Override
	public String toString() {
		return "Authority [authorityCode=" + authorityCode + ", authorityName=" + authorityName + ", authorityEtc="
				+ authorityEtc + "]";
	}
	
	
	

}
