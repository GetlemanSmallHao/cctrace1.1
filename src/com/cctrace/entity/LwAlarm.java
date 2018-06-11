package com.cctrace.entity;

import java.io.Serializable;

public class LwAlarm implements Serializable {
	// 主键id
	private Integer id;
	//  告警等级
	private String alarm_lev;
	// 告警编号
	private Integer alarm_num;
	// 英文告警内容
	private String eng_con;
	// 中文告警内容
	private String chi_con;

	public LwAlarm() {
		super();
	}

	public LwAlarm(Integer id, String alarm_lev, Integer alarm_num,
			String eng_con, String chi_con) {
		this();
		this.id = id;
		this.alarm_lev = alarm_lev;
		this.alarm_num = alarm_num;
		this.eng_con = eng_con;
		this.chi_con = chi_con;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlarm_lev() {
		return alarm_lev;
	}

	public void setAlarm_lev(String alarm_lev) {
		this.alarm_lev = alarm_lev;
	}

	public Integer getAlarm_num() {
		return alarm_num;
	}

	public void setAlarm_num(Integer alarm_num) {
		this.alarm_num = alarm_num;
	}

	public String getEng_con() {
		return eng_con;
	}

	public void setEng_con(String eng_con) {
		this.eng_con = eng_con;
	}

	public String getChi_con() {
		return chi_con;
	}

	public void setChi_con(String chi_con) {
		this.chi_con = chi_con;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alarm_lev == null) ? 0 : alarm_lev.hashCode());
		result = prime * result
				+ ((alarm_num == null) ? 0 : alarm_num.hashCode());
		result = prime * result + ((chi_con == null) ? 0 : chi_con.hashCode());
		result = prime * result + ((eng_con == null) ? 0 : eng_con.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		LwAlarm other = (LwAlarm) obj;
		if (alarm_lev == null) {
			if (other.alarm_lev != null)
				return false;
		} else if (!alarm_lev.equals(other.alarm_lev))
			return false;
		if (alarm_num == null) {
			if (other.alarm_num != null)
				return false;
		} else if (!alarm_num.equals(other.alarm_num))
			return false;
		if (chi_con == null) {
			if (other.chi_con != null)
				return false;
		} else if (!chi_con.equals(other.chi_con))
			return false;
		if (eng_con == null) {
			if (other.eng_con != null)
				return false;
		} else if (!eng_con.equals(other.eng_con))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LwAlarm [id=" + id + ", alarm_lev=" + alarm_lev
				+ ", alarm_num=" + alarm_num + ", eng_con=" + eng_con
				+ ", chi_con=" + chi_con + "]";
	}

}
