package com.cctrace.entity;

public class CommandStore {
	
//	控制指令存储id
	private int id;
//	存储内容
	private String content;
//	操作者姓名
	private String userName;
//	操作时间
	private String time;
//	操作时时间戳
	private String longTime;
//	指令类型 (0：请求数据指令,1：控制指令)
	private String type;
//	控制指令
	private String command;
//	冷藏箱编号
	private String containerId;
//	状态（Y：指令发送成功，N：指令发送成失败)
	private String status;
//	指令值
	private String value;
//	冷机状态显示
	private String display;
	
//关联表查询buMenM
	private String buMenM;
	
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLongTime() {
		return longTime;
	}
	public void setLongTime(String longTime) {
		this.longTime = longTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBuMenM() {
		return buMenM;
	}
	public void setBuMenM(String buMenM) {
		this.buMenM = buMenM;
	}
	
	
	
	
}
