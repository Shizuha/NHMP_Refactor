package com.minje.nhmp.ERP.vo;

import java.io.Serializable;

public class EmpSalaryVo implements Serializable{
	
private static final long serialVersionUID = 5433L;
	
	private String empId; //사번
	private String ementInrance;// 고용보험
	private String natPension; // 국민연금
	private String healInrance; // 건강보험
	private int healRdc; // 건강보험감면퍼센트
	private String oldInrance; // 노인장기요양보험
	private int oldRdc; //장기요양보험 감면
	private String earIncome;// 근로소득자
	private int earInrance;// 새액퍼센트
	private String bsnIncome;// 사업소득자
	private String dailyJob;// 일용직
	private String etcIncome;//기타소득자
	private String earBsnIncome;//근로/사업 소득자
	
	public EmpSalaryVo() {}

	public EmpSalaryVo(String empId, String ementInrance, String natPension, String healInrance, int healRdc,
			String oldInrance, int oldRdc, String earIncome, int earInrance, String bsnIncome, String dailyJob,
			String etcIncome, String earBsnIncome) {
		super();
		this.empId = empId;
		this.ementInrance = ementInrance;
		this.natPension = natPension;
		this.healInrance = healInrance;
		this.healRdc = healRdc;
		this.oldInrance = oldInrance;
		this.oldRdc = oldRdc;
		this.earIncome = earIncome;
		this.earInrance = earInrance;
		this.bsnIncome = bsnIncome;
		this.dailyJob = dailyJob;
		this.etcIncome = etcIncome;
		this.earBsnIncome = earBsnIncome;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmentInrance() {
		return ementInrance;
	}

	public void setEmentInrance(String ementInrance) {
		this.ementInrance = ementInrance;
	}

	public String getNatPension() {
		return natPension;
	}

	public void setNatPension(String natPension) {
		this.natPension = natPension;
	}

	public String getHealInrance() {
		return healInrance;
	}

	public void setHealInrance(String healInrance) {
		this.healInrance = healInrance;
	}

	public int getHealRdc() {
		return healRdc;
	}

	public void setHealRdc(int healRdc) {
		this.healRdc = healRdc;
	}

	public String getOldInrance() {
		return oldInrance;
	}

	public void setOldInrance(String oldInrance) {
		this.oldInrance = oldInrance;
	}

	public int getOldRdc() {
		return oldRdc;
	}

	public void setOldRdc(int oldRdc) {
		this.oldRdc = oldRdc;
	}

	public String getEarIncome() {
		return earIncome;
	}

	public void setEarIncome(String earIncome) {
		this.earIncome = earIncome;
	}

	public int getEarInrance() {
		return earInrance;
	}

	public void setEarInrance(int earInrance) {
		this.earInrance = earInrance;
	}

	public String getBsnIncome() {
		return bsnIncome;
	}

	public void setBsnIncome(String bsnIncome) {
		this.bsnIncome = bsnIncome;
	}

	public String getDailyJob() {
		return dailyJob;
	}

	public void setDailyJob(String dailyJob) {
		this.dailyJob = dailyJob;
	}

	public String getEtcIncome() {
		return etcIncome;
	}

	public void setEtcIncome(String etcIncome) {
		this.etcIncome = etcIncome;
	}

	public String getEarBsnIncome() {
		return earBsnIncome;
	}

	public void setEarBsnIncome(String earBsnIncome) {
		this.earBsnIncome = earBsnIncome;
	}

	@Override
	public String toString() {
		return "EmpSalary [empId=" + empId + ", ementInrance=" + ementInrance + ", natPension=" + natPension
				+ ", healInrance=" + healInrance + ", healRdc=" + healRdc + ", oldInrance=" + oldInrance + ", oldRdc="
				+ oldRdc + ", earIncome=" + earIncome + ", earInrance=" + earInrance + ", bsnIncome=" + bsnIncome
				+ ", dailyJob=" + dailyJob + ", etcIncome=" + etcIncome + ", earBsnIncome=" + earBsnIncome + "]";
	}
}
