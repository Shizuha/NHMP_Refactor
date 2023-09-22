package com.minje.nhmp.ERP.vo;

public class CalendarVo implements java.io.Serializable {

	private static final long serialVersionUID = 7000L;
	
	private int calnum;
	private String title;
	private String description;
	private String startdate;
	private String enddate;
	private String category;
	private String username;
	private String backgroundcolor;
	private String textcolor;
	private String empname;

	
	public CalendarVo() {}

	public CalendarVo(int calnum, String title, String description, String startdate, String enddate,
			String category, String username, String backgroundcolor, String textcolor, String empname) {
		super();
		this.calnum = calnum;
		this.title = title;
		this.description = description;
		this.startdate = startdate;
		this.enddate = enddate;
		this.category = category;
		this.username = username;
		this.backgroundcolor = backgroundcolor;
		this.textcolor = textcolor;
		this.empname = empname;
	}

	public int getCalnum() {
		return calnum;
	}

	public void setCalnum(int calnum) {
		this.calnum = calnum;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBackgroundcolor() {
		return backgroundcolor;
	}

	public void setBackgroundcolor(String backgroundcolor) {
		this.backgroundcolor = backgroundcolor;
	}

	public String getTextcolor() {
		return textcolor;
	}

	public void setTextcolor(String textcolor) {
		this.textcolor = textcolor;
	}
	
	public String getempname() {
		return empname;
	}

	public void setempname(String empname) {
		this.empname = empname;
	}

	@Override
	public String toString() {
		return "Calendar [calnum=" + calnum + ", title=" + title + ", description=" + description
				+ ", startdate=" + startdate + ", enddate=" + enddate + ", category=" + category + ", username="
				+ username + ", backgroundcolor=" + backgroundcolor + ", textcolor=" + textcolor + ", empname=" + empname + "]";
	}
	
}
