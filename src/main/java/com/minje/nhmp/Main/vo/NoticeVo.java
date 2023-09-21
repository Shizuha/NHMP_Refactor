package com.minje.nhmp.Main.vo;

import java.io.Serializable;

public class NoticeVo implements Serializable{
	private static final long serialVersionUID = 8444L;
	private int NOTICE_NO;
	private String NOTICE_TITLE;
	private String NOTICE_TYPE;
	private String NOTICE_CONTENT;
	private int NOTICE_COUNT;
	private java.sql.Date NOTICE_DATE;
	private String NH_NAME;

	public NoticeVo() {}

	public void setNOTICE_NO(int nOTICE_NO) {
		NOTICE_NO = nOTICE_NO;
	}

	public void setNOTICE_TITLE(String nOTICE_TITLE) {
		NOTICE_TITLE = nOTICE_TITLE;
	}

	public void setNOTICE_TYPE(String nOTICE_TYPE) {
		NOTICE_TYPE = nOTICE_TYPE;
	}

	public void setNOTICE_CONTENT(String nOTICE_CONTENT) {
		NOTICE_CONTENT = nOTICE_CONTENT;
	}

	public void setNOTICE_COUNT(int nOTICE_COUNT) {
		NOTICE_COUNT = nOTICE_COUNT;
	}

	public void setNOTICE_DATE(java.sql.Date nOTICE_DATE) {
		NOTICE_DATE = nOTICE_DATE;
	}

	public void setNH_NAME(String nH_NAME) {
		NH_NAME = nH_NAME;
	}

	public int getNOTICE_NO() {
		return NOTICE_NO;
	}

	public String getNOTICE_TITLE() {
		return NOTICE_TITLE;
	}

	public String getNOTICE_TYPE() {
		return NOTICE_TYPE;
	}

	public String getNOTICE_CONTENT() {
		return NOTICE_CONTENT;
	}

	public int getNOTICE_COUNT() {
		return NOTICE_COUNT;
	}

	public java.sql.Date getNOTICE_DATE() {
		return NOTICE_DATE;
	}

	public String getNH_NAME() {
		return NH_NAME;
	}

	@Override
	public String toString() {
		return "Cnotice [NOTICE_NO=" + NOTICE_NO + ", NOTICE_TITLE=" + NOTICE_TITLE + ", NOTICE_TYPE=" + NOTICE_TYPE
				+ ", NOTICE_CONTENT=" + NOTICE_CONTENT + ", NOTICE_COUNT=" + NOTICE_COUNT + ", NOTICE_DATE="
				+ NOTICE_DATE + ", NH_NAME=" + NH_NAME + "]";
	}

	public static int getListCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
