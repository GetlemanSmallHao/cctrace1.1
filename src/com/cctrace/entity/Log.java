package com.cctrace.entity;

import java.io.Serializable;

public class Log implements Serializable {

	private Integer id;
	//该动作的执行者
	private String operator;
	//该动作的执行时间
	private String operatorTime;
	//该动作的长整型执行时间
	private Long operatorLongTime;
	//操作参数
	private String operatorParams;
	
	private Integer companyId;
	//该操作的具体内容
	private String content;
	//操作信息
	private Oinfo oinfo;
	

	public Log() {
		super();
	}


	public Log(Integer id, String operator, String operatorTime,
			Long operatorLongTime, String operatorParams, Integer companyId,
			String content, Oinfo oinfo) {
		super();
		this.id = id;
		this.operator = operator;
		this.operatorTime = operatorTime;
		this.operatorLongTime = operatorLongTime;
		this.operatorParams = operatorParams;
		this.companyId = companyId;
		this.content = content;
		this.oinfo = oinfo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((oinfo == null) ? 0 : oinfo.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime
				* result
				+ ((operatorLongTime == null) ? 0 : operatorLongTime.hashCode());
		result = prime * result
				+ ((operatorParams == null) ? 0 : operatorParams.hashCode());
		result = prime * result
				+ ((operatorTime == null) ? 0 : operatorTime.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (oinfo == null) {
			if (other.oinfo != null)
				return false;
		} else if (!oinfo.equals(other.oinfo))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (operatorLongTime == null) {
			if (other.operatorLongTime != null)
				return false;
		} else if (!operatorLongTime.equals(other.operatorLongTime))
			return false;
		if (operatorParams == null) {
			if (other.operatorParams != null)
				return false;
		} else if (!operatorParams.equals(other.operatorParams))
			return false;
		if (operatorTime == null) {
			if (other.operatorTime != null)
				return false;
		} else if (!operatorTime.equals(other.operatorTime))
			return false;
		return true;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	public String getOperatorTime() {
		return operatorTime;
	}


	public void setOperatorTime(String operatorTime) {
		this.operatorTime = operatorTime;
	}


	public Long getOperatorLongTime() {
		return operatorLongTime;
	}


	public void setOperatorLongTime(Long operatorLongTime) {
		this.operatorLongTime = operatorLongTime;
	}


	public String getOperatorParams() {
		return operatorParams;
	}


	public void setOperatorParams(String operatorParams) {
		this.operatorParams = operatorParams;
	}


	public Integer getCompanyId() {
		return companyId;
	}


	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Oinfo getOinfo() {
		return oinfo;
	}


	public void setOinfo(Oinfo oinfo) {
		this.oinfo = oinfo;
	}


	@Override
	public String toString() {
		return "Log [id=" + id + ", operator=" + operator + ", operatorTime="
				+ operatorTime + ", operatorLongTime=" + operatorLongTime
				+ ", operatorParams=" + operatorParams + ", companyId="
				+ companyId + ", content=" + content + ", oinfo=" + oinfo + "]";
	}





	
}
