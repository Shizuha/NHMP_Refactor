package Main.NursingHospital.model.ov;

import java.io.Serializable;

public class NursingHospital implements Serializable{
	private static final long serialVersionUID = 8111L;
	private int NH_ID;
	private String NH_NAME;
	private java.sql.Date NH_DATE;
	private java.sql.Date NH_RSN_DATE;
	private String NH_NO;
	private String NH_ADDRESS;
	private String NH_ITNAL_FOR;
	private String NH_AD_TEL;
	private String NH_PHONE;
	private String NH_EMAIL;
	private String NH_USERID;
	private String NH_USERPWD;
	private String NH_ETC;
	private String NH_IMG;
	private String GENDER;
	private String COMPANY_NAME;
	private String COMPANY_NO;
	private String NH_SERVICE_CODE;
	private String AUTHORITY_CODE;
	private String NH_SERVICE_HISTORY;
	
	public NursingHospital() {}
	
	public NursingHospital(String nH_USERID, String nH_USERPWD) {
		super();
		NH_USERID = nH_USERID;
		NH_USERPWD = nH_USERPWD;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getNH_ID() {
		return NH_ID;
	}

	public String getNH_NAME() {
		return NH_NAME;
	}

	public java.sql.Date getNH_DATE() {
		return NH_DATE;
	}

	public java.sql.Date getNH_RSN_DATE() {
		return NH_RSN_DATE;
	}

	public String getNH_NO() {
		return NH_NO;
	}

	public String getNH_ADDRESS() {
		return NH_ADDRESS;
	}

	public String getNH_ITNAL_FOR() {
		return NH_ITNAL_FOR;
	}

	public String getNH_AD_TEL() {
		return NH_AD_TEL;
	}

	public String getNH_PHONE() {
		return NH_PHONE;
	}

	public String getNH_EMAIL() {
		return NH_EMAIL;
	}

	public String getNH_USERID() {
		return NH_USERID;
	}

	public String getNH_USERPWD() {
		return NH_USERPWD;
	}

	public String getNH_ETC() {
		return NH_ETC;
	}

	public String getNH_IMG() {
		return NH_IMG;
	}

	public String getGENDER() {
		return GENDER;
	}

	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}

	public String getCOMPANY_NO() {
		return COMPANY_NO;
	}

	public String getNH_SERVICE_CODE() {
		return NH_SERVICE_CODE;
	}

	public String getAUTHORITY_CODE() {
		return AUTHORITY_CODE;
	}

	public String getNH_SERVICE_HISTORY() {
		return NH_SERVICE_HISTORY;
	}

	public void setNH_ID(int nH_ID) {
		NH_ID = nH_ID;
	}

	public void setNH_NAME(String nH_NAME) {
		NH_NAME = nH_NAME;
	}

	public void setNH_DATE(java.sql.Date nH_DATE) {
		NH_DATE = nH_DATE;
	}

	public void setNH_RSN_DATE(java.sql.Date nH_RSN_DATE) {
		NH_RSN_DATE = nH_RSN_DATE;
	}

	public void setNH_NO(String nH_NO) {
		NH_NO = nH_NO;
	}

	public void setNH_ADDRESS(String nH_ADDRESS) {
		NH_ADDRESS = nH_ADDRESS;
	}

	public void setNH_ITNAL_FOR(String nH_ITNAL_FOR) {
		NH_ITNAL_FOR = nH_ITNAL_FOR;
	}

	public void setNH_AD_TEL(String nH_AD_TEL) {
		NH_AD_TEL = nH_AD_TEL;
	}

	public void setNH_PHONE(String nH_PHONE) {
		NH_PHONE = nH_PHONE;
	}

	public void setNH_EMAIL(String nH_EMAIL) {
		NH_EMAIL = nH_EMAIL;
	}

	public void setNH_USERID(String nH_USERID) {
		NH_USERID = nH_USERID;
	}

	public void setNH_USERPWD(String nH_USERPWD) {
		NH_USERPWD = nH_USERPWD;
	}

	public void setNH_ETC(String nH_ETC) {
		NH_ETC = nH_ETC;
	}

	public void setNH_IMG(String nH_IMG) {
		NH_IMG = nH_IMG;
	}

	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}

	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}

	public void setCOMPANY_NO(String cOMPANY_NO) {
		COMPANY_NO = cOMPANY_NO;
	}

	public void setNH_SERVICE_CODE(String nH_SERVICE_CODE) {
		NH_SERVICE_CODE = nH_SERVICE_CODE;
	}

	public void setAUTHORITY_CODE(String aUTHORITY_CODE) {
		AUTHORITY_CODE = aUTHORITY_CODE;
	}

	public void setNH_SERVICE_HISTORY(String nH_SERVICE_HISTORY) {
		NH_SERVICE_HISTORY = nH_SERVICE_HISTORY;
	}

	@Override
	public String toString() {
		return "NursingHospital [NH_ID=" + NH_ID + ", NH_NAME=" + NH_NAME + ", NH_DATE=" + NH_DATE + ", NH_RSN_DATE="
				+ NH_RSN_DATE + ", NH_NO=" + NH_NO + ", NH_ADDRESS=" + NH_ADDRESS + ", NH_ITNAL_FOR=" + NH_ITNAL_FOR
				+ ", NH_AD_TEL=" + NH_AD_TEL + ", NH_PHONE=" + NH_PHONE + ", NH_EMAIL=" + NH_EMAIL + ", NH_USERID="
				+ NH_USERID + ", NH_USERPWD=" + NH_USERPWD + ", NH_ETC=" + NH_ETC + ", NH_IMG=" + NH_IMG + ", GENDER="
				+ GENDER + ", COMPANY_NAME=" + COMPANY_NAME + ", COMPANY_NO=" + COMPANY_NO + ", NH_SERVICE_CODE="
				+ NH_SERVICE_CODE + ", AUTHORITY_CODE=" + AUTHORITY_CODE + ", NH_SERVICE_HISTORY=" + NH_SERVICE_HISTORY
				+ "]";
	}
	
}
