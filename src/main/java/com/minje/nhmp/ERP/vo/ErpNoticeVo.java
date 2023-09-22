package com.minje.nhmp.ERP.vo;

import java.sql.*;

public class ErpNoticeVo implements java.io.Serializable {
	private static final long serialVersionUID = 333L;
	
	private int noticeNo; //공지사항 번호
	private String noticeTitle; //공지사항 제목
	private String noticeContent; //공지사항 글 내용
	private int noticeCount;	//공지사항 조회수
	private java.sql.Date noticeDate;	//공지사항 작성일
	private String noticeWriter;	//작성자
	
	public ErpNoticeVo() {}

	public ErpNoticeVo(int noticeNo, String noticeTitle, String noticeContent, int noticeCount, Date noticeDate,
			String noticeWriter) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeCount = noticeCount;
		this.noticeDate = noticeDate;
		this.noticeWriter = noticeWriter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public int getNoticeCount() {
		return noticeCount;
	}

	public java.sql.Date getNoticeDate() {
		return noticeDate;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

	public void setNoticeDate(java.sql.Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeCount=" + noticeCount + ", noticeDate=" + noticeDate + ", noticeWriter=" + noticeWriter
				+ "]";
	}

	
}





