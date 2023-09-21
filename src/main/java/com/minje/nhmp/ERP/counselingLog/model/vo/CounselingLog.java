package ERP.counselingLog.model.vo;

public class CounselingLog implements java.io.Serializable{
	private static final long serialVersionUID = 6003L;
	private int clNo;
	private String clTitle;
	private java.sql.Date clDate;
	private String clContents;
	private String clPhone;
	private String clComment;
	private String clPatName;
	private String clEmpName;
	private String clOriginalFileName;
	private String clRenameFileName;
	
	public CounselingLog () {}

	public CounselingLog(int clNo, String clTitle, java.sql.Date clDate, String clContents, String clPhone, String clComment, String clPatName,
									String clEmpName) {
		super();
		this.clNo = clNo;
		this.clTitle = clTitle;
		this.clDate = clDate;
		this.clContents = clContents;
		this.clPhone = clPhone;
		this.clComment = clComment;
		this.clPatName = clPatName;
		this.clEmpName = clEmpName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getClNo() {
		return clNo;
	}
	
	public String getClTitle() {
		return clTitle;
	}

	public java.sql.Date getClDate() {
		return clDate;
	}

	public String getClContents() {
		return clContents;
	}

	public String getClPhone() {
		return clPhone;
	}

	public String getClComment() {
		return clComment;
	}

	public String getClPatName() {
		return clPatName;
	}

	public String getClEmpName() {
		return clEmpName;
	}
	
	public String getClOriginalFileName() {
		return clOriginalFileName;
	}
	
	public String getClRenameFileName() {
		return clRenameFileName;
	}

	public void setClNo(int clNo) {
		this.clNo = clNo;
	}
	
	public void setClTitle(String clTitle) {
		this.clTitle = clTitle;
	}

	public void setClDate(java.sql.Date clDate) {
		this.clDate = clDate;
	}

	public void setClContents(String clContents) {
		this.clContents = clContents;
	}

	public void setClPhone(String clPhone) {
		this.clPhone = clPhone;
	}

	public void setClComment(String clComment) {
		this.clComment = clComment;
	}

	public void setClPatName(String clPatName) {
		this.clPatName = clPatName;
	}

	public void setClEmpName(String clEmpName) {
		this.clEmpName = clEmpName;
	}
	
	public void setClOriginalFileName(String clOriginalFileName) {
		this.clOriginalFileName = clOriginalFileName;
	}
	
	public void setClRenameFileName(String clRenameFileName) {
		this.clRenameFileName = clRenameFileName;
	}

	@Override
	public String toString() {
		return "CounselingLog [clNo=" + clNo + ", clTitle" + clTitle + ", clDate=" + clDate + ", clContents=" + clContents 
				+ ", clPhone=" + clPhone	+ ", clComment=" + clComment + ", clPatName=" + clPatName 
				+ ", clEmpName=" + clEmpName	+ ", clOriginalFileName" + clOriginalFileName + ", clRenameFileName" + clRenameFileName + "]";
	}
	
}
