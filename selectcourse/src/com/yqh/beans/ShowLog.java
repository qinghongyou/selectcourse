package com.yqh.beans;

import java.util.Date;

public class ShowLog {
	private int logId; // Id
	private String userName;// 用户
	private String userId;
	private String doing;  // 操作
	private String msg; // 描述
	private String logDate; // 时间
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDoing() {
		return doing;
	}
	public void setDoing(String doing) {
		this.doing = doing;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ShowLog() {
	}
	
	
	public ShowLog(int logId, String userName, String doing, String msg,
			String logDate) {
		super();
		this.logId = logId;
		this.userName = userName;
		this.doing = doing;
		this.msg = msg;
		this.logDate = logDate;
	}
	
	
	public ShowLog( String userId, String doing, String msg) {
		super();
		this.userId = userId;
		this.doing = doing;
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "ShowLog [logId=" + logId + ", userName=" + userName
				+ ", doing=" + doing + ", msg=" + msg + ", logDate=" + logDate
				+ "]";
	}
	
	
	
	

}
