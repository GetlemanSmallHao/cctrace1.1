package com.cctrace.utils;

public class BaseResult {
	private Integer flag;
	private String message;
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	//当查询结果为真时，反馈的结果为成功
	public static BaseResult success(){
		BaseResult result = new BaseResult();
		result.setFlag(BaseResultEnum.SUCCESS.getFlag());
		result.setMessage(BaseResultEnum.SUCCESS.getMessage());
		return result;
	}
	
	//当查询的结果为假时，反馈的结果为失败
	public static BaseResult error(){
		BaseResult result = new BaseResult();
		result.setFlag(BaseResultEnum.ERROR.getFlag());
		result.setMessage(BaseResultEnum.ERROR.getMessage());
		return result;
	}
	
	@Override
	public String toString() {
		return "BaseResult [code=" + flag + ", message=" + message + "]";
	}
}
