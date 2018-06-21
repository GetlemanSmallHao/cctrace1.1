package com.cctrace.entity;

import java.io.Serializable;

//设备绑定表
public class BindTable implements Serializable {
	private Integer id;
	//执行绑定操作的操作者
	private String operator;
	//冷藏箱编号
	private String containerId;
	//设备编号
	private String deviceId;
	//路线类型
	private String routeType;
	//班列编号
	private String trainId;
	//执行绑定动作的时间
	private String bindTime;
	//执行绑定操作的长整型时间
	private Long bindLongTime;
	//堆场id,这个需要根据堆场id去获取堆场名称放到map中到前台去展示
	private Integer yardId;
	//下货站id,这个需要根据下货站id去获取下货站名称放到map中去到前台展示
	private Integer theNextStationId;
	//货物类型
	private String carGoType;
	//所属公司,这个插入时是根据执行绑定动作的用户的companyId获取的
	private Integer companyId;
	// 备注信息
	private String remark;
	
//	private String yardName;
//	private String theNextStationName;
	private String buMenM;

	


	public BindTable() {
		super();
	}


	public BindTable(Integer id, String operator, String containerId,
			String deviceId, String routeType, String trainId, String bindTime,
			Long bindLongTime, Integer yardId, Integer theNextStationId,
			String carGoType, Integer companyId, String remark, String buMenM) {
		super();
		this.id = id;
		this.operator = operator;
		this.containerId = containerId;
		this.deviceId = deviceId;
		this.routeType = routeType;
		this.trainId = trainId;
		this.bindTime = bindTime;
		this.bindLongTime = bindLongTime;
		this.yardId = yardId;
		this.theNextStationId = theNextStationId;
		this.carGoType = carGoType;
		this.companyId = companyId;
		this.remark = remark;
		this.buMenM = buMenM;
	}
	


	
	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public String getBindTime() {
		return bindTime;
	}

	public void setBindTime(String bindTime) {
		this.bindTime = bindTime;
	}

	public Long getBindLongTime() {
		return bindLongTime;
	}

	public void setBindLongTime(Long bindLongTime) {
		this.bindLongTime = bindLongTime;
	}

	public Integer getYardId() {
		return yardId;
	}

	public void setYardId(Integer yardId) {
		this.yardId = yardId;
	}

	public Integer getTheNextStationId() {
		return theNextStationId;
	}

	public void setTheNextStationId(Integer theNextStationId) {
		this.theNextStationId = theNextStationId;
	}

	public String getCarGoType() {
		return carGoType;
	}

	public void setCarGoType(String carGoType) {
		this.carGoType = carGoType;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getBuMenM() {
		return buMenM;
	}


	public void setBuMenM(String buMenM) {
		this.buMenM = buMenM;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bindLongTime == null) ? 0 : bindLongTime.hashCode());
		result = prime * result
				+ ((bindTime == null) ? 0 : bindTime.hashCode());
		result = prime * result + ((buMenM == null) ? 0 : buMenM.hashCode());
		result = prime * result
				+ ((carGoType == null) ? 0 : carGoType.hashCode());
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result
				+ ((containerId == null) ? 0 : containerId.hashCode());
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((routeType == null) ? 0 : routeType.hashCode());
		result = prime
				* result
				+ ((theNextStationId == null) ? 0 : theNextStationId.hashCode());
		result = prime * result + ((trainId == null) ? 0 : trainId.hashCode());
		result = prime * result + ((yardId == null) ? 0 : yardId.hashCode());
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
		BindTable other = (BindTable) obj;
		if (bindLongTime == null) {
			if (other.bindLongTime != null)
				return false;
		} else if (!bindLongTime.equals(other.bindLongTime))
			return false;
		if (bindTime == null) {
			if (other.bindTime != null)
				return false;
		} else if (!bindTime.equals(other.bindTime))
			return false;
		if (buMenM == null) {
			if (other.buMenM != null)
				return false;
		} else if (!buMenM.equals(other.buMenM))
			return false;
		if (carGoType == null) {
			if (other.carGoType != null)
				return false;
		} else if (!carGoType.equals(other.carGoType))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (routeType == null) {
			if (other.routeType != null)
				return false;
		} else if (!routeType.equals(other.routeType))
			return false;
		if (theNextStationId == null) {
			if (other.theNextStationId != null)
				return false;
		} else if (!theNextStationId.equals(other.theNextStationId))
			return false;
		if (trainId == null) {
			if (other.trainId != null)
				return false;
		} else if (!trainId.equals(other.trainId))
			return false;
		if (yardId == null) {
			if (other.yardId != null)
				return false;
		} else if (!yardId.equals(other.yardId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "BindTable [id=" + id + ", operator=" + operator
				+ ", containerId=" + containerId + ", deviceId=" + deviceId
				+ ", routeType=" + routeType + ", trainId=" + trainId
				+ ", bindTime=" + bindTime + ", bindLongTime=" + bindLongTime
				+ ", yardId=" + yardId + ", theNextStationId="
				+ theNextStationId + ", carGoType=" + carGoType
				+ ", companyId=" + companyId + ", remark=" + remark
				+ ", buMenM=" + buMenM + "]";
	}

	

}
