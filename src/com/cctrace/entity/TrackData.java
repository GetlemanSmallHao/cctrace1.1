package com.cctrace.entity;

//运行轨迹表
public class TrackData {

	private String ContainerId;
	private Double lat;
	private Double lon;
	private Double jzLat;
	private Double jzLon;
	private String gpsTime;
	private Double gpsBatVol;
	private String trainId;
	private Double backWindTemp;

	public TrackData() {
		super();
	}

	public TrackData(String containerId, Double lat, Double lon, Double jzLat,
			Double jzLon, String gpsTime, Double gpsBatVol, String trainId,
			Double backWindTemp) {
		this();
		ContainerId = containerId;
		this.lat = lat;
		this.lon = lon;
		this.jzLat = jzLat;
		this.jzLon = jzLon;
		this.gpsTime = gpsTime;
		this.gpsBatVol = gpsBatVol;
		this.trainId = trainId;
		this.backWindTemp = backWindTemp;
	}

	public String getContainerId() {
		return ContainerId;
	}

	public void setContainerId(String containerId) {
		ContainerId = containerId;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getJzLat() {
		return jzLat;
	}

	public void setJzLat(Double jzLat) {
		this.jzLat = jzLat;
	}

	public Double getJzLon() {
		return jzLon;
	}

	public void setJzLon(Double jzLon) {
		this.jzLon = jzLon;
	}

	public String getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(String gpsTime) {
		this.gpsTime = gpsTime;
	}

	public Double getGpsBatVol() {
		return gpsBatVol;
	}

	public void setGpsBatVol(Double gpsBatVol) {
		this.gpsBatVol = gpsBatVol;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public Double getBackWindTemp() {
		return backWindTemp;
	}

	public void setBackWindTemp(Double backWindTemp) {
		this.backWindTemp = backWindTemp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ContainerId == null) ? 0 : ContainerId.hashCode());
		result = prime * result
				+ ((backWindTemp == null) ? 0 : backWindTemp.hashCode());
		result = prime * result
				+ ((gpsBatVol == null) ? 0 : gpsBatVol.hashCode());
		result = prime * result + ((gpsTime == null) ? 0 : gpsTime.hashCode());
		result = prime * result + ((jzLat == null) ? 0 : jzLat.hashCode());
		result = prime * result + ((jzLon == null) ? 0 : jzLon.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		result = prime * result + ((trainId == null) ? 0 : trainId.hashCode());
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
		TrackData other = (TrackData) obj;
		if (ContainerId == null) {
			if (other.ContainerId != null)
				return false;
		} else if (!ContainerId.equals(other.ContainerId))
			return false;
		if (backWindTemp == null) {
			if (other.backWindTemp != null)
				return false;
		} else if (!backWindTemp.equals(other.backWindTemp))
			return false;
		if (gpsBatVol == null) {
			if (other.gpsBatVol != null)
				return false;
		} else if (!gpsBatVol.equals(other.gpsBatVol))
			return false;
		if (gpsTime == null) {
			if (other.gpsTime != null)
				return false;
		} else if (!gpsTime.equals(other.gpsTime))
			return false;
		if (jzLat == null) {
			if (other.jzLat != null)
				return false;
		} else if (!jzLat.equals(other.jzLat))
			return false;
		if (jzLon == null) {
			if (other.jzLon != null)
				return false;
		} else if (!jzLon.equals(other.jzLon))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
			return false;
		if (trainId == null) {
			if (other.trainId != null)
				return false;
		} else if (!trainId.equals(other.trainId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrackData [ContainerId=" + ContainerId + ", lat=" + lat
				+ ", lon=" + lon + ", jzLat=" + jzLat + ", jzLon=" + jzLon
				+ ", gpsTime=" + gpsTime + ", gpsBatVol=" + gpsBatVol
				+ ", trainId=" + trainId + ", backWindTemp=" + backWindTemp
				+ "]";
	}

}
