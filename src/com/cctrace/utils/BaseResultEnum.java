package com.cctrace.utils;

public enum BaseResultEnum {
	SUCCESS(0,"SUCCESS"),
	ERROR(1,"FAILED");
	private int flag;
	private String message;

	private BaseResultEnum(int flag, String message) {
		this.flag = flag;
		this.message = message;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
