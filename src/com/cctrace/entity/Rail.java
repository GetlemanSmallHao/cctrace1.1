package com.cctrace.entity;

import java.io.Serializable;

//电子围栏表
public class Rail implements Serializable {

	private Integer id;
	//围栏名称
	private String railName;
	//围栏中心纬度
	private Double railLat;
	//围栏中心经度
	private Double railLon;
	//围栏半径
	private Double radius;
	//围栏所属公司id
	private Integer companyId;

	public Rail() {
		super();
	}

	
	public Rail(Integer id, String railName, Double railLat, Double railLon,
			Double radius, Integer companyId) {
		this();
		this.id = id;
		this.railName = railName;
		this.railLat = railLat;
		this.railLon = railLon;
		this.radius = radius;
		this.companyId = companyId;
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRailName() {
		return railName;
	}


	public void setRailName(String railName) {
		this.railName = railName;
	}


	public Double getRailLat() {
		return railLat;
	}


	public void setRailLat(Double railLat) {
		this.railLat = railLat;
	}


	public Double getRailLon() {
		return railLon;
	}


	public void setRailLon(Double railLon) {
		this.railLon = railLon;
	}


	public Double getRadius() {
		return radius;
	}


	public void setRadius(Double radius) {
		this.radius = radius;
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
		result = prime * result + ((radius == null) ? 0 : radius.hashCode());
		result = prime * result + ((railLat == null) ? 0 : railLat.hashCode());
		result = prime * result + ((railLon == null) ? 0 : railLon.hashCode());
		result = prime * result
				+ ((railName == null) ? 0 : railName.hashCode());
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
		Rail other = (Rail) obj;
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
		if (radius == null) {
			if (other.radius != null)
				return false;
		} else if (!radius.equals(other.radius))
			return false;
		if (railLat == null) {
			if (other.railLat != null)
				return false;
		} else if (!railLat.equals(other.railLat))
			return false;
		if (railLon == null) {
			if (other.railLon != null)
				return false;
		} else if (!railLon.equals(other.railLon))
			return false;
		if (railName == null) {
			if (other.railName != null)
				return false;
		} else if (!railName.equals(other.railName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rail [id=" + id + ", railName=" + railName + ", railLat="
				+ railLat + ", railLon=" + railLon + ", radius=" + radius
				+ ", companyId=" + companyId + "]";
	}

}
