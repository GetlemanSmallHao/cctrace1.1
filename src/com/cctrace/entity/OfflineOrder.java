package com.cctrace.entity;

import java.io.Serializable;

public class OfflineOrder implements Serializable {
	private String deviceId;
	private String containerId;
	private String type;
	private String data;
	private String chillerType;
	private String returnVal;
	private String userName;
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getChillerType() {
		return chillerType;
	}
	public void setChillerType(String chillerType) {
		this.chillerType = chillerType;
	}
	public String getReturnVal() {
		return returnVal;
	}
	public void setReturnVal(String returnVal) {
		this.returnVal = returnVal;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	public String getExt5() {
		return ext5;
	}
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
}
