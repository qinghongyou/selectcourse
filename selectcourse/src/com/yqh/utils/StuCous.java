package com.yqh.utils;

public class StuCous {
	
	private String stuid;
	private String stuname;
	private String couname;
	private String teacher;
	private String couexp;
	public String getStuid() {
		return stuid;
	}
	public void setStuid(String stuid) {
		this.stuid = stuid;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getCouname() {
		return couname;
	}
	public void setCouname(String couname) {
		this.couname = couname;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getCouexp() {
		return couexp;
	}
	public void setCouexp(String couexp) {
		this.couexp = couexp;
	}
	@Override
	public String toString() {
		return "StuCous [stuid=" + stuid + ", stuname=" + stuname
				+ ", couname=" + couname + ", teacher=" + teacher + ", couexp="
				+ couexp + "]";
	}
	public StuCous(String stuid, String stuname, String couname,
			String teacher, String couexp) {
		super();
		this.stuid = stuid;
		this.stuname = stuname;
		this.couname = couname;
		this.teacher = teacher;
		this.couexp = couexp;
	}
	public StuCous() {
	}
	
	

}
