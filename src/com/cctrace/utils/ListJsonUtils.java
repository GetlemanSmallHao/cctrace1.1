package com.cctrace.utils;

import java.io.Serializable;
import java.util.List;

import com.cctrace.entity.Alert;
public class ListJsonUtils implements Serializable {
	public static final int SUCCESS=0;
	public static final int ERROR=1;
	private int state;
	private String message = "";
	private List<Alert> alerts;
	public ListJsonUtils(){
		state = SUCCESS;
	}
	
	public ListJsonUtils(int state, String message, List<Alert> alerts) {
		super();
		this.state = state;
		this.message = message;
		this.alerts = alerts;
	}
	public ListJsonUtils(Throwable e){
		this(ERROR,e.getMessage(),null);
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static int getSuccess() {
		return SUCCESS;
	}
	public static int getError() {
		return ERROR;
	}

	public List<Alert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

	@Override
	public String toString() {
		return "ListJsonUtils [state=" + state + ", message=" + message
				+ ", alerts=" + alerts + "]";
	}
	
	
	
	
}
