package com.minje.nhmp.ERP.vo;

import java.io.Serializable;
import java.sql.Date;

public class EmployeeVo implements Serializable{
	private static final long serialVersionUID = 5555L;
	
	private String empId;
	private String hostId;
	private String hostPwd;
	private String empName;
	private java.sql.Date hireDate;
	private java.sql.Date rsnDate;
	private String empNo;
	private String address;
	private String itnalFor;
	private String adTel;
	private String phone;
	private String email;
	private int salary;
	private String userId;
	private String userpwd;
	private String empEtc;
	private String empImgOriginalFilename;
	private String empRenameFilename;
	private String gender;
	private String empmentCode;
	private String deptCode;
	private String teamCode;
	private String posCode;
	private String deductionCode;
	private String allowanceCode;
	private String authorityCode;
	private String wardCode;
	private String holdOffice;
	
	public EmployeeVo() {}
	
	public EmployeeVo(String empId, String hostId, String hostPwd, String empName, Date hireDate, Date rsnDate,
			String empNo, String address, String itnalFor, String adTel, String phone, String email, int salary,
			String userId, String userpwd, String empEtc, String empImgOriginalFilename, String empRenameFilename,
			String gender, String empmentCode, String deptCode, String teamCode, String posCode, String deductionCode,
			String allowanceCode, String authorityCode, String wardCode, String holdOffice) {
		super();
		this.empId = empId;
		this.hostId = hostId;
		this.hostPwd = hostPwd;
		this.empName = empName;
		this.hireDate = hireDate;
		this.rsnDate = rsnDate;
		this.empNo = empNo;
		this.address = address;
		this.itnalFor = itnalFor;
		this.adTel = adTel;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
		this.userId = userId;
		this.userpwd = userpwd;
		this.empEtc = empEtc;
		this.empImgOriginalFilename = empImgOriginalFilename;
		this.empRenameFilename = empRenameFilename;
		this.gender = gender;
		this.empmentCode = empmentCode;
		this.deptCode = deptCode;
		this.teamCode = teamCode;
		this.posCode = posCode;
		this.deductionCode = deductionCode;
		this.allowanceCode = allowanceCode;
		this.authorityCode = authorityCode;
		this.wardCode = wardCode;
		this.holdOffice = holdOffice;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getHostPwd() {
		return hostPwd;
	}

	public void setHostPwd(String hostPwd) {
		this.hostPwd = hostPwd;
	}

	public String getHoldOffice() {
		return holdOffice;
	}

	public void setHoldOffice(String holdOffice) {
		this.holdOffice = holdOffice;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public java.sql.Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}

	public java.sql.Date getRsnDate() {
		return rsnDate;
	}

	public void setRsnDate(java.sql.Date rsnDate) {
		this.rsnDate = rsnDate;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getItnalFor() {
		return itnalFor;
	}

	public void setItnalFor(String itnalFor) {
		this.itnalFor = itnalFor;
	}

	public String getAdTel() {
		return adTel;
	}

	public void setAdTel(String adTel) {
		this.adTel = adTel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getEmpEtc() {
		return empEtc;
	}

	public void setEmpEtc(String empEtc) {
		this.empEtc = empEtc;
	}

	public String getEmpImgOriginalFilename() {
		return empImgOriginalFilename;
	}

	public void setEmpImgOriginalFilename(String empImgOriginalFilename) {
		this.empImgOriginalFilename = empImgOriginalFilename;
	}

	public String getEmpRenameFilename() {
		return empRenameFilename;
	}

	public void setEmpRenameFilename(String empRenameFilename) {
		this.empRenameFilename = empRenameFilename;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmpmentCode() {
		return empmentCode;
	}

	public void setEmpmentCode(String empmentCode) {
		this.empmentCode = empmentCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getDeductionCode() {
		return deductionCode;
	}

	public void setDeductionCode(String deductionCode) {
		this.deductionCode = deductionCode;
	}

	public String getAllowanceCode() {
		return allowanceCode;
	}

	public void setAllowanceCode(String allowanceCode) {
		this.allowanceCode = allowanceCode;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", hostId=" + hostId + ", hostPwd=" + hostPwd + ", empName=" + empName
				+ ", hireDate=" + hireDate + ", rsnDate=" + rsnDate + ", empNo=" + empNo + ", address=" + address
				+ ", itnalFor=" + itnalFor + ", adTel=" + adTel + ", phone=" + phone + ", email=" + email + ", salary="
				+ salary + ", userId=" + userId + ", userpwd=" + userpwd + ", empEtc=" + empEtc
				+ ", empImgOriginalFilename=" + empImgOriginalFilename + ", empRenameFilename=" + empRenameFilename
				+ ", gender=" + gender + ", empmentCode=" + empmentCode + ", deptCode=" + deptCode + ", teamCode="
				+ teamCode + ", posCode=" + posCode + ", deductionCode=" + deductionCode + ", allowanceCode="
				+ allowanceCode + ", authorityCode=" + authorityCode + ", wardCode=" + wardCode + ", holdOffice="
				+ holdOffice + "]";
	}
}
