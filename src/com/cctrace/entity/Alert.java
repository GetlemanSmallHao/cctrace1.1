package com.cctrace.entity;

import java.io.Serializable;

//预警表
public class Alert implements Serializable {
	private Integer id;
	// 冷藏箱编号
	private String containerId;
	// 告警时间
	private String alertTime;
	// 告警长整型时间
	private Long alertLongTime;
	// 告警类型
	private String alertType;
	// 告警内容
	private String alertContent;
	// 告警时的纬度
	private Double lat;
	// 告警时的经度
	private Double lon;
	// 是否读取
	private String readed;
	// 报警值
	private Integer alarm_num;
	// gps经纬度位置
	private String gpsPosition;
	// 最新告警时间
	private String updateTime;
	// 最新告警时间戳
	private Long updateLongTime;
	// 公司id
	private Integer companyId;
	
	//关联表
	private String buMenM;

	public Alert() {
		super();
	}

	public Alert(Integer id, String containerId, String alertTime,
			Long alertLongTime, String alertType, String alertContent,
			Double lat, Double lon, String readed, Integer alarm_num,
			String gpsPosition, String updateTime, Long updateLongTime,
			Integer companyId, String buMenM) {
		super();
		this.id = id;
		this.containerId = containerId;
		this.alertTime = alertTime;
		this.alertLongTime = alertLongTime;
		this.alertType = alertType;
		this.alertContent = alertContent;
		this.lat = lat;
		this.lon = lon;
		this.readed = readed;
		this.alarm_num = alarm_num;
		this.gpsPosition = gpsPosition;
		this.updateTime = updateTime;
		this.updateLongTime = updateLongTime;
		this.companyId = companyId;
		this.buMenM=buMenM;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getGpsPosition() {
		return gpsPosition;
	}

	public void setGpsPosition(String gpsPosition) {
		this.gpsPosition = gpsPosition;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}

	public Long getAlertLongTime() {
		return alertLongTime;
	}

	public void setAlertLongTime(Long alertLongTime) {
		this.alertLongTime = alertLongTime;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getAlertContent() {
		return alertContent;
	}

	public void setAlertContent(String alertContent) {
		this.alertContent = alertContent;
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

	public String getReaded() {
		return readed;
	}

	public void setReaded(String readed) {
		this.readed = readed;
	}

	public Integer getAlarm_num() {
		return alarm_num;
	}

	public void setAlarm_num(Integer alarm_num) {
		this.alarm_num = alarm_num;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateLongTime() {
		return updateLongTime;
	}

	public void setUpdateLongTime(Long updateLongTime) {
		this.updateLongTime = updateLongTime;
	}

	public String getBuMenM() {
		return buMenM;
	}

	public void setBuMenM(String buMenM) {
		this.buMenM = buMenM;
	}

	@Override
	public String toString() {
		return "Alert [id=" + id + ", containerId=" + containerId
				+ ", alertTime=" + alertTime + ", alertLongTime="
				+ alertLongTime + ", alertType=" + alertType
				+ ", alertContent=" + alertContent + ", lat=" + lat + ", lon="
				+ lon + ", readed=" + readed + ", alarm_num=" + alarm_num
				+ ", gpsPosition=" + gpsPosition + ", updateTime=" + updateTime
				+ ", updateLongTime=" + updateLongTime + ", companyId="
				+ companyId + ", buMenM=" + buMenM + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alarm_num == null) ? 0 : alarm_num.hashCode());
		result = prime * result
				+ ((alertContent == null) ? 0 : alertContent.hashCode());
		result = prime * result
				+ ((alertLongTime == null) ? 0 : alertLongTime.hashCode());
		result = prime * result
				+ ((alertTime == null) ? 0 : alertTime.hashCode());
		result = prime * result
				+ ((alertType == null) ? 0 : alertType.hashCode());
		result = prime * result + ((buMenM == null) ? 0 : buMenM.hashCode());
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result
				+ ((containerId == null) ? 0 : containerId.hashCode());
		result = prime * result
				+ ((gpsPosition == null) ? 0 : gpsPosition.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		result = prime * result + ((readed == null) ? 0 : readed.hashCode());
		result = prime * result
				+ ((updateLongTime == null) ? 0 : updateLongTime.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
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
		Alert other = (Alert) obj;
		if (alarm_num == null) {
			if (other.alarm_num != null)
				return false;
		} else if (!alarm_num.equals(other.alarm_num))
			return false;
		if (alertContent == null) {
			if (other.alertContent != null)
				return false;
		} else if (!alertContent.equals(other.alertContent))
			return false;
		if (alertLongTime == null) {
			if (other.alertLongTime != null)
				return false;
		} else if (!alertLongTime.equals(other.alertLongTime))
			return false;
		if (alertTime == null) {
			if (other.alertTime != null)
				return false;
		} else if (!alertTime.equals(other.alertTime))
			return false;
		if (alertType == null) {
			if (other.alertType != null)
				return false;
		} else if (!alertType.equals(other.alertType))
			return false;
		if (buMenM == null) {
			if (other.buMenM != null)
				return false;
		} else if (!buMenM.equals(other.buMenM))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (containerId == null) {
			if (other.containerId != null)
				return false;
		} else if (!containerId.equals(other.containerId))
			return false;
		if (gpsPosition == null) {
			if (other.gpsPosition != null)
				return false;
		} else if (!gpsPosition.equals(other.gpsPosition))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (readed == null) {
			if (other.readed != null)
				return false;
		} else if (!readed.equals(other.readed))
			return false;
		if (updateLongTime == null) {
			if (other.updateLongTime != null)
				return false;
		} else if (!updateLongTime.equals(other.updateLongTime))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}

}
