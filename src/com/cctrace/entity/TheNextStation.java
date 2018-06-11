package com.cctrace.entity;

import java.io.Serializable;
//下货站表
public class TheNextStation implements Serializable {
	private Integer id;
	//下货站站名
	private String stationName;
	//下货站所属公司id
	private Integer companyId;

	public TheNextStation() {
		super();
	}

	public TheNextStation(Integer id, String stationName, Integer companyId) {
		super();
		this.id = id;
		this.stationName = stationName;
		this.companyId = companyId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
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
				+ ((stationName == null) ? 0 : stationName.hashCode());
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
		TheNextStation other = (TheNextStation) obj;
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
		if (stationName == null) {
			if (other.stationName != null)
				return false;
		} else if (!stationName.equals(other.stationName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TheNextStation [id=" + id + ", stationName=" + stationName
				+ ", companyId=" + companyId + "]";
	}

}
