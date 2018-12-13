package com.yqh.beans;

public class Admin {
	
	private int admId;
	private String admName;
	private String admPwd;
	
	public int getAdmId() {
		return admId;
	}
	public void setAdmId(int admId) {
		this.admId = admId;
	}
	public String getAdmName() {
		return admName;
	}
	public void setAdmName(String admName) {
		this.admName = admName;
	}
	public String getAdmPwd() {
		return admPwd;
	}
	public void setAdmPwd(String admPwd) {
		this.admPwd = admPwd;
	}
	
	@Override
	public String toString() {
		return "Admin [admId=" + admId + ", admName=" + admName + ", admPwd="
				+ admPwd + "]";
	}
	
	public Admin(int admId, String admName, String admPwd) {
		super();
		this.admId = admId;
		this.admName = admName;
		this.admPwd = admPwd;
	}
	
	public Admin() {
		super();
	}
	
	

}
