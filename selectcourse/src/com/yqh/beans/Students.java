package com.yqh.beans;

public class Students {
	
	// 定义成员变量
	private String stuId; //学号
	private String stuName; //姓名
	private String stuLoginName; // 登录名
	private String stuInstitute; //学院
	private String stuPwd; //密码
	private String stuSex; //性别
	
	// 无参构造函数
	public Students() {
	}

	// 带参数的构造函数
	public Students(String stuId, String stuName, String stuLoginName,
			String stuInstitute, String stuPwd, String stuSex) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.stuLoginName = stuLoginName;
		this.stuInstitute = stuInstitute;
		this.stuPwd = stuPwd;
		this.stuSex = stuSex;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuInstitute() {
		return stuInstitute;
	}

	public void setStuInstitute(String stuInstitute) {
		this.stuInstitute = stuInstitute;
	}

	public String getStuPwd() {
		return stuPwd;
	}

	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}

	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public String getStuLoginName() {
		return stuLoginName;
	}

	public void setStuLoginName(String stuLoginName) {
		this.stuLoginName = stuLoginName;
	}
	
	@Override
	public String toString() {
		System.out.println(stuId+stuName+stuLoginName+
				stuInstitute+stuPwd+stuSex);
		return super.toString();
	}
	
}
