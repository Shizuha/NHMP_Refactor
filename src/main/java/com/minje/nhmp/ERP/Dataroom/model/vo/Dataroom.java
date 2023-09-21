package ERP.Dataroom.model.vo;

import java.sql.*;

// 직렬화
public class Dataroom implements java.io.Serializable {
	private static final long serialVersionUID = 111L;

	private int dataroomNo; //자료실번호
	private String dataroomTitle;	//자료실제목
	private String dataroomContent;	//자료실내용
	private String dataroomOriginalFileName;	//원본파일첨부명
	private String dataroomRenameFileName;		//바뀐파일첨부명
	private int dataroomCount;					//자료실조회수
	private java.sql.Date dataroomDate;			//자료실작성일
	private String dataroomWriter;				//자료실작성자
	
	public Dataroom() {}

	public Dataroom(int dataroomNo, String dataroomTitle, String dataroomContent, String dataroomOriginalFileName,
			String dataroomRenameFileName, int dataroomCount, Date dataroomDate, String dataroomWriter) {
		super();
		this.dataroomNo = dataroomNo;
		this.dataroomTitle = dataroomTitle;
		this.dataroomContent = dataroomContent;
		this.dataroomOriginalFileName = dataroomOriginalFileName;
		this.dataroomRenameFileName = dataroomRenameFileName;
		this.dataroomCount = dataroomCount;
		this.dataroomDate = dataroomDate;
		this.dataroomWriter = dataroomWriter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getDataroomNo() {
		return dataroomNo;
	}

	public String getDataroomTitle() {
		return dataroomTitle;
	}

	public String getDataroomContent() {
		return dataroomContent;
	}

	public String getDataroomOriginalFileName() {
		return dataroomOriginalFileName;
	}

	public String getDataroomRenameFileName() {
		return dataroomRenameFileName;
	}

	public int getDataroomCount() {
		return dataroomCount;
	}

	public java.sql.Date getDataroomDate() {
		return dataroomDate;
	}

	public String getDataroomWriter() {
		return dataroomWriter;
	}

	public void setDataroomNo(int dataroomNo) {
		this.dataroomNo = dataroomNo;
	}

	public void setDataroomTitle(String dataroomTitle) {
		this.dataroomTitle = dataroomTitle;
	}

	public void setDataroomContent(String dataroomContent) {
		this.dataroomContent = dataroomContent;
	}

	public void setDataroomOriginalFileName(String dataroomOriginalFileName) {
		this.dataroomOriginalFileName = dataroomOriginalFileName;
	}

	public void setDataroomRenameFileName(String dataroomRenameFileName) {
		this.dataroomRenameFileName = dataroomRenameFileName;
	}

	public void setDataroomCount(int dataroomCount) {
		this.dataroomCount = dataroomCount;
	}

	public void setDataroomDate(java.sql.Date dataroomDate) {
		this.dataroomDate = dataroomDate;
	}

	public void setDataroomWriter(String dataroomWriter) {
		this.dataroomWriter = dataroomWriter;
	}

	@Override
	public String toString() {
		return "Dataroom [dataroomNo=" + dataroomNo + ", dataroomTitle=" + dataroomTitle + ", dataroomContent="
				+ dataroomContent + ", dataroomOriginalFileName=" + dataroomOriginalFileName
				+ ", dataroomRenameFileName=" + dataroomRenameFileName + ", dataroomCount=" + dataroomCount
				+ ", dataroomDate=" + dataroomDate + ", dataroomWriter=" + dataroomWriter + "]";
	}

	

	

	
	
}














