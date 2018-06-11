package com.cctrace.entity;

import java.io.Serializable;

public class Transformationnew implements Serializable {
	// 主键id
	private Integer id;
	// 告警编号
	private String alarmNumber;
	// 英文告警内容
	private String alarmMessage1;
	// 中文告警内容
	private String alarmMessage2;
	// 英文告警类型
	private String alarmType1;
	// 中文告警类型
	private String alarmType2;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAlarmNumber() {
		return alarmNumber;
	}
	public void setAlarmNumber(String alarmNumber) {
		this.alarmNumber = alarmNumber;
	}
	public String getAlarmMessage1() {
		return alarmMessage1;
	}
	public void setAlarmMessage1(String alarmMessage1) {
		this.alarmMessage1 = alarmMessage1;
	}
	public String getAlarmMessage2() {
		return alarmMessage2;
	}
	public void setAlarmMessage2(String alarmMessage2) {
		this.alarmMessage2 = alarmMessage2;
	}
	public String getAlarmType1() {
		return alarmType1;
	}
	public void setAlarmType1(String alarmType1) {
		this.alarmType1 = alarmType1;
	}
	public String getAlarmType2() {
		return alarmType2;
	}
	public void setAlarmType2(String alarmType2) {
		this.alarmType2 = alarmType2;
	}

	

}
