package Main.vo;

import java.io.Serializable;

public class QnaVo implements Serializable{
	private static final long serialVersionUID = 8000L;
	private int QNA_NO;
	private String QNA_TITLE;
	private String QNA_TYPE;
	private String QNA_CONTENT;
	private int QNA_COUNT;
	private java.sql.Date QNA_DATE;
	private String NH_NAME;
	
	public QnaVo() {}

	public void setQNA_NO(int qNA_NO) {
		QNA_NO = qNA_NO;
	}

	public void setQNA_TITLE(String qNA_TITLE) {
		QNA_TITLE = qNA_TITLE;
	}

	public void setQNA_TYPE(String qNA_TYPE) {
		QNA_TYPE = qNA_TYPE;
	}

	public void setQNA_CONTENT(String qNA_CONTENT) {
		QNA_CONTENT = qNA_CONTENT;
	}

	public void setQNA_COUNT(int qNA_COUNT) {
		QNA_COUNT = qNA_COUNT;
	}

	public void setQNA_DATE(java.sql.Date qNA_DATE) {
		QNA_DATE = qNA_DATE;
	}

	public void setNH_NAME(String nH_NAME) {
		NH_NAME = nH_NAME;
	}

	public int getQNA_NO() {
		return QNA_NO;
	}

	public String getQNA_TITLE() {
		return QNA_TITLE;
	}

	public String getQNA_TYPE() {
		return QNA_TYPE;
	}

	public String getQNA_CONTENT() {
		return QNA_CONTENT;
	}

	public int getQNA_COUNT() {
		return QNA_COUNT;
	}

	public java.sql.Date getQNA_DATE() {
		return QNA_DATE;
	}

	public String getNH_NAME() {
		return NH_NAME;
	}

	@Override
	public String toString() {
		return "Cregistration [QNA_NO=" + QNA_NO + ", QNA_TITLE=" + QNA_TITLE + ", QNA_TYPE=" + QNA_TYPE
				+ ", QNA_CONTENT=" + QNA_CONTENT + ", QNA_COUNT=" + QNA_COUNT + ", QNA_DATE=" + QNA_DATE + ", NH_NAME="
				+ NH_NAME + "]";
	}
}
