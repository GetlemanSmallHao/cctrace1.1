
package com.cctrace.entity;

import java.io.Serializable;

//冷藏箱信息表
public class Container implements Serializable {
	
	private Integer id;
	//冷藏箱编号
	private String containerId;
	//冷藏箱注册时间
	private String registTime;
	//冷藏箱注册的长整型时间
	private Long registLongTime;
	//设备编号
	private String deviceId;
	//更换设备时间
	private String modifyDeviceTime;
	//更换设备的长整型时间
	private Long modifyDeviceLongTime;
	//最低冷机电压
	private Double minRefBatVol;
	//最高冷机电压
	private Double maxRefBatVol;
	//最低回风温度
	private Double minBackWindTemp;
	//最高回风温度
	private Double maxBackWindTemp;
	//最小湿度
	private Double minTankHum;
	//最大湿度
	private Double maxTankHum;
	//最低油位
	private Double minOilLevel;
	//最高油位
	private Double maxOilLevel;
	//最低gps设备电压
	private Double minGpsBatVol;
	//最高gps设备电压
	private Double maxGpsBatVol;
	//最低环境温度
	private Double minEnviTemp;
	//最大环境温度
	private Double maxEnviTemp;
	//最低出风温度
	private Double minChuWindTemp;
	//最大出风温度
	private Double maxChuWindTemp;
	//最小中部温度
	private Double minCenBoxTemp;
	//最大中部温度
	private Double maxCenBoxTemp;
	//最低尾部箱温
	private Double minTailBoxTemp;
	//最高尾部箱温
	private Double maxTailBoxTemp;
	//远程开关机
	private String remoteSwiMac;
	//设定温度
	private Double setTemp;
	//启动除霜
	private String bootDef;
	//清除警告
	private String clearAlert;
	//自检功能
	private String selfCheck;
	//冷机运行模式
	private String refRunMode;
	//新风门模式
	private String newWindDoorMode;
	//公司id
	private Integer companyId;
	//最低蒸发线圈温度
	private Double minCoilTemp;
	//最高蒸发线圈温度
	private Double maxCoilTemp;
	// 冷机类型  thermoking：冷王   carrier：开利
	private String chillerType;
	//新风系统开关
	private String remoteXFSwiMac;
	//设定cfm
	private String setCfm;
	
	
	public Container() {
		super();
	}
	public Container(Integer id, String containerId, String registTime,
			Long registLongTime, String deviceId, String modifyDeviceTime,
			Long modifyDeviceLongTime, Double minRefBatVol,
			Double maxRefBatVol, Double minBackWindTemp,
			Double maxBackWindTemp, Double minTankHum, Double maxTankHum,
			Double minOilLevel, Double maxOilLevel, Double minGpsBatVol,
			Double maxGpsBatVol, Double minEnviTemp, Double maxEnviTemp,
			Double minChuWindTemp, Double maxChuWindTemp, Double minCenBoxTemp,
			Double maxCenBoxTemp, Double minTailBoxTemp, Double maxTailBoxTemp,
			String remoteSwiMac, Double setTemp, String bootDef,
			String clearAlert, String selfCheck, String refRunMode,
			String newWindDoorMode, Integer companyId, Double minCoilTemp,
			Double maxCoilTemp, String chillerType, String remoteXFSwiMac, String setCfm) {
		super();
		this.id = id;
		this.containerId = containerId;
		this.registTime = registTime;
		this.registLongTime = registLongTime;
		this.deviceId = deviceId;
		this.modifyDeviceTime = modifyDeviceTime;
		this.modifyDeviceLongTime = modifyDeviceLongTime;
		this.minRefBatVol = minRefBatVol;
		this.maxRefBatVol = maxRefBatVol;
		this.minBackWindTemp = minBackWindTemp;
		this.maxBackWindTemp = maxBackWindTemp;
		this.minTankHum = minTankHum;
		this.maxTankHum = maxTankHum;
		this.minOilLevel = minOilLevel;
		this.maxOilLevel = maxOilLevel;
		this.minGpsBatVol = minGpsBatVol;
		this.maxGpsBatVol = maxGpsBatVol;
		this.minEnviTemp = minEnviTemp;
		this.maxEnviTemp = maxEnviTemp;
		this.minChuWindTemp = minChuWindTemp;
		this.maxChuWindTemp = maxChuWindTemp;
		this.minCenBoxTemp = minCenBoxTemp;
		this.maxCenBoxTemp = maxCenBoxTemp;
		this.minTailBoxTemp = minTailBoxTemp;
		this.maxTailBoxTemp = maxTailBoxTemp;
		this.remoteSwiMac = remoteSwiMac;
		this.setTemp = setTemp;
		this.bootDef = bootDef;
		this.clearAlert = clearAlert;
		this.selfCheck = selfCheck;
		this.refRunMode = refRunMode;
		this.newWindDoorMode = newWindDoorMode;
		this.companyId = companyId;
		this.minCoilTemp = minCoilTemp;
		this.maxCoilTemp = maxCoilTemp;
		this.chillerType = chillerType;
		this.remoteXFSwiMac=remoteXFSwiMac;
		this.setCfm = setCfm;
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
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public Long getRegistLongTime() {
		return registLongTime;
	}
	public void setRegistLongTime(Long registLongTime) {
		this.registLongTime = registLongTime;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getModifyDeviceTime() {
		return modifyDeviceTime;
	}
	public void setModifyDeviceTime(String modifyDeviceTime) {
		this.modifyDeviceTime = modifyDeviceTime;
	}
	public Long getModifyDeviceLongTime() {
		return modifyDeviceLongTime;
	}
	public void setModifyDeviceLongTime(Long modifyDeviceLongTime) {
		this.modifyDeviceLongTime = modifyDeviceLongTime;
	}
	public Double getMinRefBatVol() {
		return minRefBatVol;
	}
	public void setMinRefBatVol(Double minRefBatVol) {
		this.minRefBatVol = minRefBatVol;
	}
	public Double getMaxRefBatVol() {
		return maxRefBatVol;
	}
	public void setMaxRefBatVol(Double maxRefBatVol) {
		this.maxRefBatVol = maxRefBatVol;
	}
	public Double getMinBackWindTemp() {
		return minBackWindTemp;
	}
	public void setMinBackWindTemp(Double minBackWindTemp) {
		this.minBackWindTemp = minBackWindTemp;
	}
	public Double getMaxBackWindTemp() {
		return maxBackWindTemp;
	}
	public void setMaxBackWindTemp(Double maxBackWindTemp) {
		this.maxBackWindTemp = maxBackWindTemp;
	}
	public Double getMinTankHum() {
		return minTankHum;
	}
	public void setMinTankHum(Double minTankHum) {
		this.minTankHum = minTankHum;
	}
	public Double getMaxTankHum() {
		return maxTankHum;
	}
	public void setMaxTankHum(Double maxTankHum) {
		this.maxTankHum = maxTankHum;
	}
	public Double getMinOilLevel() {
		return minOilLevel;
	}
	public void setMinOilLevel(Double minOilLevel) {
		this.minOilLevel = minOilLevel;
	}
	public Double getMaxOilLevel() {
		return maxOilLevel;
	}
	public void setMaxOilLevel(Double maxOilLevel) {
		this.maxOilLevel = maxOilLevel;
	}
	public Double getMinGpsBatVol() {
		return minGpsBatVol;
	}
	public void setMinGpsBatVol(Double minGpsBatVol) {
		this.minGpsBatVol = minGpsBatVol;
	}
	public Double getMaxGpsBatVol() {
		return maxGpsBatVol;
	}
	public void setMaxGpsBatVol(Double maxGpsBatVol) {
		this.maxGpsBatVol = maxGpsBatVol;
	}
	public Double getMinEnviTemp() {
		return minEnviTemp;
	}
	public void setMinEnviTemp(Double minEnviTemp) {
		this.minEnviTemp = minEnviTemp;
	}
	public Double getMaxEnviTemp() {
		return maxEnviTemp;
	}
	public void setMaxEnviTemp(Double maxEnviTemp) {
		this.maxEnviTemp = maxEnviTemp;
	}
	public Double getMinChuWindTemp() {
		return minChuWindTemp;
	}
	public void setMinChuWindTemp(Double minChuWindTemp) {
		this.minChuWindTemp = minChuWindTemp;
	}
	public Double getMaxChuWindTemp() {
		return maxChuWindTemp;
	}
	public void setMaxChuWindTemp(Double maxChuWindTemp) {
		this.maxChuWindTemp = maxChuWindTemp;
	}
	public Double getMinCenBoxTemp() {
		return minCenBoxTemp;
	}
	public void setMinCenBoxTemp(Double minCenBoxTemp) {
		this.minCenBoxTemp = minCenBoxTemp;
	}
	public Double getMaxCenBoxTemp() {
		return maxCenBoxTemp;
	}
	public void setMaxCenBoxTemp(Double maxCenBoxTemp) {
		this.maxCenBoxTemp = maxCenBoxTemp;
	}
	public Double getMinTailBoxTemp() {
		return minTailBoxTemp;
	}
	public void setMinTailBoxTemp(Double minTailBoxTemp) {
		this.minTailBoxTemp = minTailBoxTemp;
	}
	public Double getMaxTailBoxTemp() {
		return maxTailBoxTemp;
	}
	public void setMaxTailBoxTemp(Double maxTailBoxTemp) {
		this.maxTailBoxTemp = maxTailBoxTemp;
	}
	public String getRemoteSwiMac() {
		return remoteSwiMac;
	}
	public void setRemoteSwiMac(String remoteSwiMac) {
		this.remoteSwiMac = remoteSwiMac;
	}
	public Double getSetTemp() {
		return setTemp;
	}
	public void setSetTemp(Double setTemp) {
		this.setTemp = setTemp;
	}
	public String getBootDef() {
		return bootDef;
	}
	public void setBootDef(String bootDef) {
		this.bootDef = bootDef;
	}
	public String getClearAlert() {
		return clearAlert;
	}
	public void setClearAlert(String clearAlert) {
		this.clearAlert = clearAlert;
	}
	public String getSelfCheck() {
		return selfCheck;
	}
	public void setSelfCheck(String selfCheck) {
		this.selfCheck = selfCheck;
	}
	public String getRefRunMode() {
		return refRunMode;
	}
	public void setRefRunMode(String refRunMode) {
		this.refRunMode = refRunMode;
	}
	public String getNewWindDoorMode() {
		return newWindDoorMode;
	}
	public void setNewWindDoorMode(String newWindDoorMode) {
		this.newWindDoorMode = newWindDoorMode;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Double getMinCoilTemp() {
		return minCoilTemp;
	}
	public void setMinCoilTemp(Double minCoilTemp) {
		this.minCoilTemp = minCoilTemp;
	}
	public Double getMaxCoilTemp() {
		return maxCoilTemp;
	}
	public void setMaxCoilTemp(Double maxCoilTemp) {
		this.maxCoilTemp = maxCoilTemp;
	}
	public String getChillerType() {
		return chillerType;
	}
	public void setChillerType(String chillerType) {
		this.chillerType = chillerType;
	}
	public String getRemoteXFSwiMac() {
		return remoteXFSwiMac;
	}
	public void setRemoteXFSwiMac(String remoteXFSwiMac) {
		this.remoteXFSwiMac = remoteXFSwiMac;
	}
	public String getSetCfm() {
		return setCfm;
	}
	public void setSetCfm(String setCfm) {
		this.setCfm = setCfm;
	}
	@Override
	public String toString() {
		return "Container [id=" + id + ", containerId=" + containerId
				+ ", registTime=" + registTime + ", registLongTime="
				+ registLongTime + ", deviceId=" + deviceId
				+ ", modifyDeviceTime=" + modifyDeviceTime
				+ ", modifyDeviceLongTime=" + modifyDeviceLongTime
				+ ", minRefBatVol=" + minRefBatVol + ", maxRefBatVol="
				+ maxRefBatVol + ", minBackWindTemp=" + minBackWindTemp
				+ ", maxBackWindTemp=" + maxBackWindTemp + ", minTankHum="
				+ minTankHum + ", maxTankHum=" + maxTankHum + ", minOilLevel="
				+ minOilLevel + ", maxOilLevel=" + maxOilLevel
				+ ", minGpsBatVol=" + minGpsBatVol + ", maxGpsBatVol="
				+ maxGpsBatVol + ", minEnviTemp=" + minEnviTemp
				+ ", maxEnviTemp=" + maxEnviTemp + ", minChuWindTemp="
				+ minChuWindTemp + ", maxChuWindTemp=" + maxChuWindTemp
				+ ", minCenBoxTemp=" + minCenBoxTemp + ", maxCenBoxTemp="
				+ maxCenBoxTemp + ", minTailBoxTemp=" + minTailBoxTemp
				+ ", maxTailBoxTemp=" + maxTailBoxTemp + ", remoteSwiMac="
				+ remoteSwiMac + ", setTemp=" + setTemp + ", bootDef="
				+ bootDef + ", clearAlert=" + clearAlert + ", selfCheck="
				+ selfCheck + ", refRunMode=" + refRunMode
				+ ", newWindDoorMode=" + newWindDoorMode + ", companyId="
				+ companyId + ", minCoilTemp=" + minCoilTemp + ", maxCoilTemp="
				+ maxCoilTemp + ", chillerType=" + chillerType + ",remoteXFSwiMac=" 
				+ remoteXFSwiMac +",setCfm="+ setCfm +"]";
	}
	
	
	
	
	
	
}
