package com.yqh.beans;

public class StuCou {
	
	private String sutId;
	private String courseId;
	
	public StuCou() {
	}

	public StuCou(String sutId, String courseId) {
		this.sutId = sutId;
		this.courseId = courseId;
	}

	public String getSutId() {
		return sutId;
	}

	public void setSutId(String sutId) {
		this.sutId = sutId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
}
