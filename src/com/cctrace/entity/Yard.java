package com.cctrace.entity;

import java.io.Serializable;

//堆场表
public class Yard implements Serializable {
	private Integer id;
	//堆场名称
	private String yardName;
	//堆场所属公司id
	private Integer companyId;

	public Yard() {
		super();
	}

	public Yard(Integer id, String yardName, Integer companyId) {
		this();
		this.id = id;
		this.yardName = yardName;
		this.companyId = companyId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYardName() {
		return yardName;
	}

	public void setYardName(String yardName) {
		this.yardName = yardName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((yardName == null) ? 0 : yardName.hashCode());
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
		Yard other = (Yard) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (yardName == null) {
			if (other.yardName != null)
				return false;
		} else if (!yardName.equals(other.yardName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Yard [id=" + id + ", yardName=" + yardName + ", companyId="
				+ companyId + "]";
	}
}
