package com.cctrace.entity;

import java.io.Serializable;

public class QueryVo implements Serializable {
	private Ccdata ccdata;
	private OurCcdata ourccdata;
	private BindTable bindTable;
	private Company company;
	private Alert alert;
	private User user;
	private Yard yard;
	private TheNextStation nextStation;
	private Container container;
	public QueryVo() {
		super();
	}
	public QueryVo(Ccdata ccdata, OurCcdata ourccdata, BindTable bindTable,
			Company company, Alert alert, User user, Yard yard,
			TheNextStation nextStation, Container container) {
		super();
		this.ccdata = ccdata;
		this.ourccdata = ourccdata;
		this.bindTable = bindTable;
		this.company = company;
		this.alert = alert;
		this.user = user;
		this.yard = yard;
		this.nextStation = nextStation;
		this.container = container;
	}
	public Ccdata getCcdata() {
		return ccdata;
	}
	public OurCcdata getOurccdata() {
		return ourccdata;
	}
	public BindTable getBindTable() {
		return bindTable;
	}
	public Company getCompany() {
		return company;
	}
	public Alert getAlert() {
		return alert;
	}
	public User getUser() {
		return user;
	}
	public Yard getYard() {
		return yard;
	}
	public TheNextStation getNextStation() {
		return nextStation;
	}
	public Container getContainer() {
		return container;
	}
	public void setCcdata(Ccdata ccdata) {
		this.ccdata = ccdata;
	}
	public void setOurccdata(OurCcdata ourccdata) {
		this.ourccdata = ourccdata;
	}
	public void setBindTable(BindTable bindTable) {
		this.bindTable = bindTable;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setYard(Yard yard) {
		this.yard = yard;
	}
	public void setNextStation(TheNextStation nextStation) {
		this.nextStation = nextStation;
	}
	public void setContainer(Container container) {
		this.container = container;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alert == null) ? 0 : alert.hashCode());
		result = prime * result
				+ ((bindTable == null) ? 0 : bindTable.hashCode());
		result = prime * result + ((ccdata == null) ? 0 : ccdata.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((container == null) ? 0 : container.hashCode());
		result = prime * result
				+ ((nextStation == null) ? 0 : nextStation.hashCode());
		result = prime * result
				+ ((ourccdata == null) ? 0 : ourccdata.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((yard == null) ? 0 : yard.hashCode());
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
		QueryVo other = (QueryVo) obj;
		if (alert == null) {
			if (other.alert != null)
				return false;
		} else if (!alert.equals(other.alert))
			return false;
		if (bindTable == null) {
			if (other.bindTable != null)
				return false;
		} else if (!bindTable.equals(other.bindTable))
			return false;
		if (ccdata == null) {
			if (other.ccdata != null)
				return false;
		} else if (!ccdata.equals(other.ccdata))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (container == null) {
			if (other.container != null)
				return false;
		} else if (!container.equals(other.container))
			return false;
		if (nextStation == null) {
			if (other.nextStation != null)
				return false;
		} else if (!nextStation.equals(other.nextStation))
			return false;
		if (ourccdata == null) {
			if (other.ourccdata != null)
				return false;
		} else if (!ourccdata.equals(other.ourccdata))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (yard == null) {
			if (other.yard != null)
				return false;
		} else if (!yard.equals(other.yard))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "QueryVo [ccdata=" + ccdata + ", ourccdata=" + ourccdata
				+ ", bindTable=" + bindTable + ", company=" + company
				+ ", alert=" + alert + ", user=" + user + ", yard=" + yard
				+ ", nextStation=" + nextStation + ", container=" + container
				+ "]";
	}
	
}
