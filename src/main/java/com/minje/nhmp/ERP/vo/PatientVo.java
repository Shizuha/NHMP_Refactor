package com.minje.nhmp.ERP.vo;

public class PatientVo implements java.io.Serializable{
	private static final long serialVersionUID = 6001L;
	
	private int patNum;
	private String patName;
	private String patType;
	private java.sql.Date patEntDate;
	private java.sql.Date patOutDate;
	private String patGender;	
	private String patNo;
	private String address;
	private String family;
	private String email;
	private String patPhone;
	private String phone;
	private String ward;
	private String patDoc;
	
	public PatientVo() {}
	
	public PatientVo(int patNum, String patName, String patType, java.sql.Date patEntDate, java.sql.Date patOutDate,
						String patGender, String patNo, String address, String family, String email, String patPhone,
						String phone, String ward, String patDoc) {
		super();
		this.patNum = patNum;
		this.patName = patName;
		this.patType = patType;
		this.patEntDate = patEntDate;
		this.patOutDate = patOutDate;
		this.patGender = patGender;
		this.patNo = patNo;
		this.address = address;
		this.family = family;
		this.email = email;
		this.patPhone = patPhone;
		this.phone = phone;
		this.ward = ward;
		this.patDoc = patDoc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPatNum() {
		return patNum;
	}

	public String getPatName() {
		return patName;
	}

	public String getPatType() {
		return patType;
	}

	public java.sql.Date getPatEntDate() {
		return patEntDate;
	}

	public java.sql.Date getPatOutDate() {
		return patOutDate;
	}

	public String getPatGender() {
		return patGender;
	}

	public String getPatNo() {
		return patNo;
	}

	public String getAddress() {
		return address;
	}

	public String getFamily() {
		return family;
	}

	public String getEmail() {
		return email;
	}

	public String getPatPhone() {
		return patPhone;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getWard() {
		return ward;
	}

	public String getPatDoc() {
		return patDoc;
	}

	public void setPatNum(int patNum) {
		this.patNum = patNum;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public void setPatType(String patType) {
		this.patType = patType;
	}

	public void setPatEntDate(java.sql.Date patEntDate) {
		this.patEntDate = patEntDate;
	}

	public void setPatOutDate(java.sql.Date patOutDate) {
		this.patOutDate = patOutDate;
	}

	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}

	public void setPatNo(String patNo) {
		this.patNo = patNo;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPatPhone(String patPhone) {
		this.patPhone = patPhone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setWard(String ward) {
		this.ward = ward;
	}

	public void setPatDoc(String patDoc) {
		this.patDoc = patDoc;
	}

	@Override
	public String toString() {
		return "Patient [patNum=" + patNum + ", patName=" + patName + ", patType=" + patType + ", patEntDate="
				+ patEntDate + ", patOutDate=" + patOutDate + ", patGender=" + patGender + ", patNo=" + patNo
				+ ", address=" + address + ", family=" + family + ", email=" + email + ", patPhone=" + patPhone
				+ ", phone=" + phone + ", ward=" + ward + ", patDoc=" + patDoc + "]";
	}
	
	
	
}
