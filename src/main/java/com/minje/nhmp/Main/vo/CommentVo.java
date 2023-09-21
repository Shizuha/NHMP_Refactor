package com.minje.nhmp.Main.vo;

import java.io.Serializable;

public class CommentVo implements Serializable{
	private static final long serialVersionUID = 8333L;
	private int COMMENT_NO;
	private java.sql.Date COMMENT_DATE;
	private String COMMENT_ETC;
	private int QNA_NO;
	private String NH_ID;
	
	public CommentVo() {}

	public void setCOMMENT_NO(int cOMMENT_NO) {
		COMMENT_NO = cOMMENT_NO;
	}

	public void setCOMMENT_DATE(java.sql.Date cOMMENT_DATE) {
		COMMENT_DATE = cOMMENT_DATE;
	}

	public void setCOMMENT_ETC(String cOMMENT_ETC) {
		COMMENT_ETC = cOMMENT_ETC;
	}

	public void setQNA_NO(int qNA_NO) {
		QNA_NO = qNA_NO;
	}

	public void setNH_ID(String nH_ID) {
		NH_ID = nH_ID;
	}

	public int getCOMMENT_NO() {
		return COMMENT_NO;
	}

	public java.sql.Date getCOMMENT_DATE() {
		return COMMENT_DATE;
	}

	public String getCOMMENT_ETC() {
		return COMMENT_ETC;
	}

	public int getQNA_NO() {
		return QNA_NO;
	}

	public String getNH_ID() {
		return NH_ID;
	}

	@Override
	public String toString() {
		return "Comment [COMMENT_NO=" + COMMENT_NO + ", COMMENT_DATE=" + COMMENT_DATE + ", COMMENT_ETC=" + COMMENT_ETC
				+ ", QNA_NO=" + QNA_NO + ", NH_ID=" + NH_ID + "]";
	}

	
	
}
