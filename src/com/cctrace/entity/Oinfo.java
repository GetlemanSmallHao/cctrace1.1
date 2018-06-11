package com.cctrace.entity;

import java.io.Serializable;

public class Oinfo implements Serializable{
	//编号
	private int oinfoId;
	//操作内容
	private String oinfoContent;
	//操作内容别名
	private String oinfoAlias;
	public Oinfo(int oinfoId, String oinfoContent, String oinfoAlias) {
		super();
		this.oinfoId = oinfoId;
		this.oinfoContent = oinfoContent;
		this.oinfoAlias = oinfoAlias;
	}
	public Oinfo() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((oinfoAlias == null) ? 0 : oinfoAlias.hashCode());
		result = prime * result
				+ ((oinfoContent == null) ? 0 : oinfoContent.hashCode());
		result = prime * result + oinfoId;
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
		Oinfo other = (Oinfo) obj;
		if (oinfoAlias == null) {
			if (other.oinfoAlias != null)
				return false;
		} else if (!oinfoAlias.equals(other.oinfoAlias))
			return false;
		if (oinfoContent == null) {
			if (other.oinfoContent != null)
				return false;
		} else if (!oinfoContent.equals(other.oinfoContent))
			return false;
		if (oinfoId != other.oinfoId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Oinfo [oinfoId=" + oinfoId + ", oinfoContent=" + oinfoContent
				+ ", oinfoAlias=" + oinfoAlias + "]";
	}
	public int getOinfoId() {
		return oinfoId;
	}
	public void setOinfoId(int oinfoId) {
		this.oinfoId = oinfoId;
	}
	public String getOinfoContent() {
		return oinfoContent;
	}
	public void setOinfoContent(String oinfoContent) {
		this.oinfoContent = oinfoContent;
	}
	public String getOinfoAlias() {
		return oinfoAlias;
	}
	public void setOinfoAlias(String oinfoAlias) {
		this.oinfoAlias = oinfoAlias;
	}
	
	
	
}
