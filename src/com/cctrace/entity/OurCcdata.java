package com.cctrace.entity;

import java.io.Serializable;

public class OurCcdata implements Serializable {
	// 主键id
	private Integer id;
	// 冷藏箱编号
	private String containerId;
	// 设备id和冷藏箱编号天生一对
	private String deviceId;
	// 油箱温度
	private Double oilTemp;
	// 箱内湿度
	private Double boxHum;
	// 尾部箱温
	private Double tailBoxTemp;
	// gps电量
	private Double gpsPower;
	// 油箱油位
	private Double oilLevel;
	// 纬度
	private Double lat;
	// 经度
	private Double lon;
	// GPS接收数据时间
	private String receiveTime;
	// GPS接收数据时间戳
	private Long receiveLongTime;
	// 公司编号,这里根据container表获取
	private Integer companyId;
	// 系统接收时间
	private String nowTime;
	// 系统接收长整型时间
	private Long nowLongTime;
	// 最低油箱温度
	private Double minoilTemp;
	// 最高油箱温度
	private Double maxoilTemp;

	// 最低箱内湿度
	private Double minboxHum;
	// 最高箱内湿度
	private Double maxboxHum;
	// 最低尾部箱温
	private Double mintailBoxTemp;
	// 最高尾部箱温
	private Double maxtailBoxTemp;
	// 最低gps电量
	private Double mingpsPower;
	// 最高gps电量
	private Double maxgpsPower;
	// 最低油箱油位
	private Double minoilLevel;
	// 最高油箱油位
	private Double maxoilLevel;
	// 冷机类型
	private String chillerType;
	//后门开关
	private String backDoorState;
	//gps经纬度位置
	private String gpsPosition; 
	//当前围栏的位置
	private Integer railId;
	public OurCcdata() {
		super();
	}
	public OurCcdata(Integer id, String containerId, String deviceId,
			Double oilTemp, Double boxHum, Double tailBoxTemp, Double gpsPower,
			Double oilLevel, Double lat, Double lon, String receiveTime,
			Long receiveLongTime, Integer companyId, String nowTime,
			Long nowLongTime, Double minoilTemp, Double maxoilTemp,
			Double minboxHum, Double maxboxHum, Double mintailBoxTemp,
			Double maxtailBoxTemp, Double mingpsPower, Double maxgpsPower,
			Double minoilLevel, Double maxoilLevel, String chillerType,
			String backDoorState,String gpsPosition) {
		super();
		this.id = id;
		this.containerId = containerId;
		this.deviceId = deviceId;
		this.oilTemp = oilTemp;
		this.boxHum = boxHum;
		this.tailBoxTemp = tailBoxTemp;
		this.gpsPower = gpsPower;
		this.oilLevel = oilLevel;
		this.lat = lat;
		this.lon = lon;
		this.receiveTime = receiveTime;
		this.receiveLongTime = receiveLongTime;
		this.companyId = companyId;
		this.nowTime = nowTime;
		this.nowLongTime = nowLongTime;
		this.minoilTemp = minoilTemp;
		this.maxoilTemp = maxoilTemp;
		this.minboxHum = minboxHum;
		this.maxboxHum = maxboxHum;
		this.mintailBoxTemp = mintailBoxTemp;
		this.maxtailBoxTemp = maxtailBoxTemp;
		this.mingpsPower = mingpsPower;
		this.maxgpsPower = maxgpsPower;
		this.minoilLevel = minoilLevel;
		this.maxoilLevel = maxoilLevel;
		this.chillerType = chillerType;
		this.backDoorState = backDoorState;
		this.gpsPosition = gpsPosition;
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
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Double getOilTemp() {
		return oilTemp;
	}
	public void setOilTemp(Double oilTemp) {
		this.oilTemp = oilTemp;
	}
	public Double getBoxHum() {
		return boxHum;
	}
	public void setBoxHum(Double boxHum) {
		this.boxHum = boxHum;
	}
	public Double getTailBoxTemp() {
		return tailBoxTemp;
	}
	public void setTailBoxTemp(Double tailBoxTemp) {
		this.tailBoxTemp = tailBoxTemp;
	}
	public Double getGpsPower() {
		return gpsPower;
	}
	public void setGpsPower(Double gpsPower) {
		this.gpsPower = gpsPower;
	}
	public Double getOilLevel() {
		return oilLevel;
	}
	public void setOilLevel(Double oilLevel) {
		this.oilLevel = oilLevel;
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
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public Long getReceiveLongTime() {
		return receiveLongTime;
	}
	public void setReceiveLongTime(Long receiveLongTime) {
		this.receiveLongTime = receiveLongTime;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getNowTime() {
		return nowTime;
	}
	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}
	public Long getNowLongTime() {
		return nowLongTime;
	}
	public void setNowLongTime(Long nowLongTime) {
		this.nowLongTime = nowLongTime;
	}
	public Double getMinoilTemp() {
		return minoilTemp;
	}
	public void setMinoilTemp(Double minoilTemp) {
		this.minoilTemp = minoilTemp;
	}
	public Double getMaxoilTemp() {
		return maxoilTemp;
	}
	public void setMaxoilTemp(Double maxoilTemp) {
		this.maxoilTemp = maxoilTemp;
	}
	public Double getMinboxHum() {
		return minboxHum;
	}
	public void setMinboxHum(Double minboxHum) {
		this.minboxHum = minboxHum;
	}
	public Double getMaxboxHum() {
		return maxboxHum;
	}
	public void setMaxboxHum(Double maxboxHum) {
		this.maxboxHum = maxboxHum;
	}
	public Double getMintailBoxTemp() {
		return mintailBoxTemp;
	}
	public void setMintailBoxTemp(Double mintailBoxTemp) {
		this.mintailBoxTemp = mintailBoxTemp;
	}
	public Double getMaxtailBoxTemp() {
		return maxtailBoxTemp;
	}
	public void setMaxtailBoxTemp(Double maxtailBoxTemp) {
		this.maxtailBoxTemp = maxtailBoxTemp;
	}
	public Double getMingpsPower() {
		return mingpsPower;
	}
	public void setMingpsPower(Double mingpsPower) {
		this.mingpsPower = mingpsPower;
	}
	public Double getMaxgpsPower() {
		return maxgpsPower;
	}
	public void setMaxgpsPower(Double maxgpsPower) {
		this.maxgpsPower = maxgpsPower;
	}
	public Double getMinoilLevel() {
		return minoilLevel;
	}
	public void setMinoilLevel(Double minoilLevel) {
		this.minoilLevel = minoilLevel;
	}
	public Double getMaxoilLevel() {
		return maxoilLevel;
	}
	public void setMaxoilLevel(Double maxoilLevel) {
		this.maxoilLevel = maxoilLevel;
	}
	public String getChillerType() {
		return chillerType;
	}
	public void setChillerType(String chillerType) {
		this.chillerType = chillerType;
	}
	public String getBackDoorState() {
		return backDoorState;
	}
	public void setBackDoorState(String backDoorState) {
		this.backDoorState = backDoorState;
	}
	public Integer getRailId() {
		return railId;
	}
	public void setRailId(Integer railId) {
		this.railId = railId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((backDoorState == null) ? 0 : backDoorState.hashCode());
		result = prime * result + ((boxHum == null) ? 0 : boxHum.hashCode());
		result = prime * result
				+ ((chillerType == null) ? 0 : chillerType.hashCode());
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result
				+ ((containerId == null) ? 0 : containerId.hashCode());
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result
				+ ((gpsPower == null) ? 0 : gpsPower.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		result = prime * result
				+ ((maxboxHum == null) ? 0 : maxboxHum.hashCode());
		result = prime * result
				+ ((maxgpsPower == null) ? 0 : maxgpsPower.hashCode());
		result = prime * result
				+ ((maxoilLevel == null) ? 0 : maxoilLevel.hashCode());
		result = prime * result
				+ ((maxoilTemp == null) ? 0 : maxoilTemp.hashCode());
		result = prime * result
				+ ((maxtailBoxTemp == null) ? 0 : maxtailBoxTemp.hashCode());
		result = prime * result
				+ ((minboxHum == null) ? 0 : minboxHum.hashCode());
		result = prime * result
				+ ((mingpsPower == null) ? 0 : mingpsPower.hashCode());
		result = prime * result
				+ ((minoilLevel == null) ? 0 : minoilLevel.hashCode());
		result = prime * result
				+ ((minoilTemp == null) ? 0 : minoilTemp.hashCode());
		result = prime * result
				+ ((mintailBoxTemp == null) ? 0 : mintailBoxTemp.hashCode());
		result = prime * result
				+ ((nowLongTime == null) ? 0 : nowLongTime.hashCode());
		result = prime * result + ((nowTime == null) ? 0 : nowTime.hashCode());
		result = prime * result
				+ ((oilLevel == null) ? 0 : oilLevel.hashCode());
		result = prime * result + ((oilTemp == null) ? 0 : oilTemp.hashCode());
		result = prime * result
				+ ((gpsPosition == null) ? 0 : gpsPosition.hashCode());
		result = prime * result
				+ ((receiveLongTime == null) ? 0 : receiveLongTime.hashCode());
		result = prime * result
				+ ((receiveTime == null) ? 0 : receiveTime.hashCode());
		result = prime * result
				+ ((tailBoxTemp == null) ? 0 : tailBoxTemp.hashCode());
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
		OurCcdata other = (OurCcdata) obj;
		if (backDoorState == null) {
			if (other.backDoorState != null)
				return false;
		} else if (!backDoorState.equals(other.backDoorState))
			return false;
		if (boxHum == null) {
			if (other.boxHum != null)
				return false;
		} else if (!boxHum.equals(other.boxHum))
			return false;
		if (chillerType == null) {
			if (other.chillerType != null)
				return false;
		} else if (!chillerType.equals(other.chillerType))
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
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (gpsPower == null) {
			if (other.gpsPower != null)
				return false;
		} else if (!gpsPower.equals(other.gpsPower))
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
		if (maxboxHum == null) {
			if (other.maxboxHum != null)
				return false;
		} else if (!maxboxHum.equals(other.maxboxHum))
			return false;
		if (maxgpsPower == null) {
			if (other.maxgpsPower != null)
				return false;
		} else if (!maxgpsPower.equals(other.maxgpsPower))
			return false;
		if (maxoilLevel == null) {
			if (other.maxoilLevel != null)
				return false;
		} else if (!maxoilLevel.equals(other.maxoilLevel))
			return false;
		if (maxoilTemp == null) {
			if (other.maxoilTemp != null)
				return false;
		} else if (!maxoilTemp.equals(other.maxoilTemp))
			return false;
		if (maxtailBoxTemp == null) {
			if (other.maxtailBoxTemp != null)
				return false;
		} else if (!maxtailBoxTemp.equals(other.maxtailBoxTemp))
			return false;
		if (minboxHum == null) {
			if (other.minboxHum != null)
				return false;
		} else if (!minboxHum.equals(other.minboxHum))
			return false;
		if (mingpsPower == null) {
			if (other.mingpsPower != null)
				return false;
		} else if (!mingpsPower.equals(other.mingpsPower))
			return false;
		if (minoilLevel == null) {
			if (other.minoilLevel != null)
				return false;
		} else if (!minoilLevel.equals(other.minoilLevel))
			return false;
		if (minoilTemp == null) {
			if (other.minoilTemp != null)
				return false;
		} else if (!minoilTemp.equals(other.minoilTemp))
			return false;
		if (mintailBoxTemp == null) {
			if (other.mintailBoxTemp != null)
				return false;
		} else if (!mintailBoxTemp.equals(other.mintailBoxTemp))
			return false;
		if (nowLongTime == null) {
			if (other.nowLongTime != null)
				return false;
		} else if (!nowLongTime.equals(other.nowLongTime))
			return false;
		if (nowTime == null) {
			if (other.nowTime != null)
				return false;
		} else if (!nowTime.equals(other.nowTime))
			return false;
		if (oilLevel == null) {
			if (other.oilLevel != null)
				return false;
		} else if (!oilLevel.equals(other.oilLevel))
			return false;
		if (oilTemp == null) {
			if (other.oilTemp != null)
				return false;
		} else if (!oilTemp.equals(other.oilTemp))
			return false;
		if (gpsPosition == null) {
			if (other.gpsPosition != null)
				return false;
		} else if (!gpsPosition.equals(other.gpsPosition))
			return false;
		if (receiveLongTime == null) {
			if (other.receiveLongTime != null)
				return false;
		} else if (!receiveLongTime.equals(other.receiveLongTime))
			return false;
		if (receiveTime == null) {
			if (other.receiveTime != null)
				return false;
		} else if (!receiveTime.equals(other.receiveTime))
			return false;
		if (tailBoxTemp == null) {
			if (other.tailBoxTemp != null)
				return false;
		} else if (!tailBoxTemp.equals(other.tailBoxTemp))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OurCcdata [id=" + id + ", containerId=" + containerId
				+ ", deviceId=" + deviceId + ", oilTemp=" + oilTemp
				+ ", boxHum=" + boxHum + ", tailBoxTemp=" + tailBoxTemp
				+ ", gpsPower=" + gpsPower + ", oilLevel=" + oilLevel
				+ ", lat=" + lat + ", lon=" + lon + ", receiveTime="
				+ receiveTime + ", receiveLongTime=" + receiveLongTime
				+ ", companyId=" + companyId + ", nowTime=" + nowTime
				+ ", nowLongTime=" + nowLongTime + ", minoilTemp=" + minoilTemp
				+ ", maxoilTemp=" + maxoilTemp + ", minboxHum=" + minboxHum
				+ ", maxboxHum=" + maxboxHum + ", mintailBoxTemp="
				+ mintailBoxTemp + ", maxtailBoxTemp=" + maxtailBoxTemp
				+ ", mingpsPower=" + mingpsPower + ", maxgpsPower="
				+ maxgpsPower + ", minoilLevel=" + minoilLevel
				+ ", maxoilLevel=" + maxoilLevel + ", chillerType="
				+ chillerType + ", backDoorState=" + backDoorState
				+ ", gpsPosition=" + gpsPosition + "]";
	}
	

	
}
