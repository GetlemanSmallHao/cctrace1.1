package com.cctrace.entity;

public class ErrorData {

//	主键id
	private Integer id;
//	接收内容
	private String content;
//	接收数据时间
	private String receiveTime;
//	接收时间戳
	private String receiveLongTime;
//	存入类型  read-write
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getReceiveLongTime() {
		return receiveLongTime;
	}
	public void setReceiveLongTime(String receiveLongTime) {
		this.receiveLongTime = receiveLongTime;
	}
	
	
}
