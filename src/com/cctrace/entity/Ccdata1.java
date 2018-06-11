package com.cctrace.entity;

import java.io.Serializable;

//数据表 硬件返回数据存放表
public class Ccdata1 implements Serializable {

	private Integer id;
	// 集装箱号
	private String containerId;
	// 设备号
	private String deviceId;
	// gps电压
	private Double gpsBatVol;
	// 冷机电压
	private Double refBatVol;
	// 后门开关状态
	private String backDoorState;
	// 油位
	private Double oilLevel;
	// 中部箱温
	private Double cenBoxTemp;
	// 纬度
	private Double lat;
	// 经度
	private Double lon;
	// 基站纬度
	private Double jzLat;
	// 基站经度
	private Double jzLon;
	// 湿度
	private Double tankHum;
	// 围栏id
	private Integer railId;
	// 围栏状态
	private String railTag;
	// 围栏状态改变时间
	private String railChangeTime;
	// 冷机开关状态
	private String refSwiState;
	// 公司id
	private Integer companyId;
	// 冷寂运行模式(启停，连续)
	private String refRunMode;
	// 尾部箱温
	private Double tailBoxTemp;
	// 环境温度
	private Double enviTemp;
	// 回风温度
	private Double backWindTemp;
	// 出风温度
	private Double chuWindTemp;
	// gps时间
	private String nowTime;
	// gps长整型时间
	private Long nowLongTime;
	// 接收时间(这是后端接受到数据后new的Date对象装换的字符创)
	private String receiveTime;
	// 上面的时间字符创对异能的长整型数据
	private Long receiveLongTime;
	// 冷机运转模式（高、低速）
	private String operationMode;
	// 冷机工作模式()
	private String workMode;
	// 动力类型（柴油、电力）
	private String powerType;
	// 车辆运行时长
	private Double vecRunTime;
	// 发动机运行时长
	private Double engRunTime;
	// 油温
	private Double oilTemp;
	// GPS电量
	private Double gpsPower;
	// 设定温度
	private Double tempSet;
	// 蒸发线圈温度
	private Double coilTemp;
	// 冷机类型
	private String chillerType;
	// 通讯状态
	private String communicationState;
	// 冷藏箱型号大小
	private String lcxModel;
	// 新风门开关状态
	private String refXFSwiState;


	public Ccdata1() {
		super();
	}

	public Ccdata1(Integer id, String containerId, String deviceId,
			Double gpsBatVol, Double refBatVol, String backDoorState,
			Double oilLevel, Double cenBoxTemp, Double lat, Double lon,
			Double jzLat, Double jzLon, Double tankHum, Integer railId,
			String railTag, String railChangeTime, String refSwiState,
			Integer companyId, String refRunMode, Double tailBoxTemp,
			Double enviTemp, Double backWindTemp, Double chuWindTemp,
			String nowTime, Long nowLongTime, String receiveTime,
			Long receiveLongTime, String operationMode, String workMode,
			String powerType, Double vecRunTime, Double engRunTime,
			Double oilTemp, Double gpsPower, Double tempSet, Double coilTemp,
			String chillerType, String communicationState, String lcxModel, String refXFSwiState) {
		super();
		this.id = id;
		this.containerId = containerId;
		this.deviceId = deviceId;
		this.gpsBatVol = gpsBatVol;
		this.refBatVol = refBatVol;
		this.backDoorState = backDoorState;
		this.oilLevel = oilLevel;
		this.cenBoxTemp = cenBoxTemp;
		this.lat = lat;
		this.lon = lon;
		this.jzLat = jzLat;
		this.jzLon = jzLon;
		this.tankHum = tankHum;
		this.railId = railId;
		this.railTag = railTag;
		this.railChangeTime = railChangeTime;
		this.refSwiState = refSwiState;
		this.companyId = companyId;
		this.refRunMode = refRunMode;
		this.tailBoxTemp = tailBoxTemp;
		this.enviTemp = enviTemp;
		this.backWindTemp = backWindTemp;
		this.chuWindTemp = chuWindTemp;
		this.nowTime = nowTime;
		this.nowLongTime = nowLongTime;
		this.receiveTime = receiveTime;
		this.receiveLongTime = receiveLongTime;
		this.operationMode = operationMode;
		this.workMode = workMode;
		this.powerType = powerType;
		this.vecRunTime = vecRunTime;
		this.engRunTime = engRunTime;
		this.oilTemp = oilTemp;
		this.gpsPower = gpsPower;
		this.tempSet = tempSet;
		this.coilTemp = coilTemp;
		this.chillerType = chillerType;
		this.communicationState = communicationState;
		this.lcxModel = lcxModel;
		this.refXFSwiState=refXFSwiState;
	}

	public String getLcxModel() {
		return lcxModel;
	}

	public void setLcxModel(String lcxModel) {
		this.lcxModel = lcxModel;
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

	public Double getGpsBatVol() {
		return gpsBatVol;
	}

	public void setGpsBatVol(Double gpsBatVol) {
		this.gpsBatVol = gpsBatVol;
	}

	public Double getRefBatVol() {
		return refBatVol;
	}

	public void setRefBatVol(Double refBatVol) {
		this.refBatVol = refBatVol;
	}

	public String getBackDoorState() {
		return backDoorState;
	}

	public void setBackDoorState(String backDoorState) {
		this.backDoorState = backDoorState;
	}

	public Double getOilLevel() {
		return oilLevel;
	}

	public void setOilLevel(Double oilLevel) {
		this.oilLevel = oilLevel;
	}

	public Double getCenBoxTemp() {
		return cenBoxTemp;
	}

	public void setCenBoxTemp(Double cenBoxTemp) {
		this.cenBoxTemp = cenBoxTemp;
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

	public Double getTankHum() {
		return tankHum;
	}

	public void setTankHum(Double tankHum) {
		this.tankHum = tankHum;
	}

	public Integer getRailId() {
		return railId;
	}

	public void setRailId(Integer railId) {
		this.railId = railId;
	}

	public String getRailTag() {
		return railTag;
	}

	public void setRailTag(String railTag) {
		this.railTag = railTag;
	}

	public String getRailChangeTime() {
		return railChangeTime;
	}

	public void setRailChangeTime(String railChangeTime) {
		this.railChangeTime = railChangeTime;
	}

	public String getRefSwiState() {
		return refSwiState;
	}

	public void setRefSwiState(String refSwiState) {
		this.refSwiState = refSwiState;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getRefRunMode() {
		return refRunMode;
	}

	public void setRefRunMode(String refRunMode) {
		this.refRunMode = refRunMode;
	}

	public Double getTailBoxTemp() {
		return tailBoxTemp;
	}

	public void setTailBoxTemp(Double tailBoxTemp) {
		this.tailBoxTemp = tailBoxTemp;
	}

	public Double getEnviTemp() {
		return enviTemp;
	}

	public void setEnviTemp(Double enviTemp) {
		this.enviTemp = enviTemp;
	}

	public Double getBackWindTemp() {
		return backWindTemp;
	}

	public void setBackWindTemp(Double backWindTemp) {
		this.backWindTemp = backWindTemp;
	}

	public Double getChuWindTemp() {
		return chuWindTemp;
	}

	public void setChuWindTemp(Double chuWindTemp) {
		this.chuWindTemp = chuWindTemp;
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

	public String getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(String operationMode) {
		this.operationMode = operationMode;
	}

	public String getWorkMode() {
		return workMode;
	}

	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	public Double getVecRunTime() {
		return vecRunTime;
	}

	public void setVecRunTime(Double vecRunTime) {
		this.vecRunTime = vecRunTime;
	}

	public Double getEngRunTime() {
		return engRunTime;
	}

	public void setEngRunTime(Double engRunTime) {
		this.engRunTime = engRunTime;
	}

	public Double getOilTemp() {
		return oilTemp;
	}

	public void setOilTemp(Double oilTemp) {
		this.oilTemp = oilTemp;
	}

	public Double getGpsPower() {
		return gpsPower;
	}

	public void setGpsPower(Double gpsPower) {
		this.gpsPower = gpsPower;
	}

	public Double getTempSet() {
		return tempSet;
	}

	public void setTempSet(Double tempSet) {
		this.tempSet = tempSet;
	}

	public Double getCoilTemp() {
		return coilTemp;
	}

	public void setCoilTemp(Double coilTemp) {
		this.coilTemp = coilTemp;
	}

	public String getChillerType() {
		return chillerType;
	}

	public void setChillerType(String chillerType) {
		this.chillerType = chillerType;
	}

	public String getCommunicationState() {
		return communicationState;
	}

	public void setCommunicationState(String communicationState) {
		this.communicationState = communicationState;
	}
	public String getRefXFSwiState() {
		return refXFSwiState;
	}

	public void setRefXFSwiState(String refXFSwiState) {
		this.refXFSwiState = refXFSwiState;
	}

	@Override
	public String toString() {
		return "Ccdata1 [id=" + id + ", containerId=" + containerId
				+ ", deviceId=" + deviceId + ", gpsBatVol=" + gpsBatVol
				+ ", refBatVol=" + refBatVol + ", backDoorState="
				+ backDoorState + ", oilLevel=" + oilLevel + ", cenBoxTemp="
				+ cenBoxTemp + ", lat=" + lat + ", lon=" + lon + ", jzLat="
				+ jzLat + ", jzLon=" + jzLon + ", tankHum=" + tankHum
				+ ", railId=" + railId + ", railTag=" + railTag
				+ ", railChangeTime=" + railChangeTime + ", refSwiState="
				+ refSwiState + ", companyId=" + companyId + ", refRunMode="
				+ refRunMode + ", tailBoxTemp=" + tailBoxTemp + ", enviTemp="
				+ enviTemp + ", backWindTemp=" + backWindTemp
				+ ", chuWindTemp=" + chuWindTemp + ", nowTime=" + nowTime
				+ ", nowLongTime=" + nowLongTime + ", receiveTime="
				+ receiveTime + ", receiveLongTime=" + receiveLongTime
				+ ", operationMode=" + operationMode + ", workMode=" + workMode
				+ ", powerType=" + powerType + ", vecRunTime=" + vecRunTime
				+ ", engRunTime=" + engRunTime + ", oilTemp=" + oilTemp
				+ ", gpsPower=" + gpsPower + ", tempSet=" + tempSet
				+ ", coilTemp=" + coilTemp + ", chillerType=" + chillerType
				+ ", communicationState=" + communicationState + ", lcxModel="
				+ lcxModel + ",refXFSwiState=" + refXFSwiState +"]";
	}

}
