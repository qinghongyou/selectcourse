package com.yqh.beans;

public class Course {
	
	private String couId; // 课程编号
	private String couName; // 课程名称
	private String teacher; // 授课教师
	private String courseDes; // 课程描述
	private int credit; // 学分
	
	public Course() {
	}

	public Course(String couId, String couName, String teacher,
			String courseDes, int credit) {
		super();
		this.couId = couId;
		this.couName = couName;
		this.teacher = teacher;
		this.courseDes = courseDes;
		this.credit = credit;
	}

	public String getCouId() {
		return couId;
	}

	public void setCouId(String couId) {
		this.couId = couId;
	}

	public String getCouName() {
		return couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getCourseDes() {
		return courseDes;
	}

	public void setCourseDes(String courseDes) {
		this.courseDes = courseDes;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
}
