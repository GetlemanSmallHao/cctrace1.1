package com.cctrace.entity;

import java.io.Serializable;

//用户所属公司
public class Company implements Serializable {
	private Integer id;
	//公司名称
	private String companyName;
	//公司电话
	private String companyPhone;
	//公司地址
	private String address;

	public Company() {
		super();
	}

	public Company(Integer id, String companyName, String companyPhone,
			String address) {
		this();
		this.id = id;
		this.companyName = companyName;
		this.companyPhone = companyPhone;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result
				+ ((companyPhone == null) ? 0 : companyPhone.hashCode());
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
		Company other = (Company) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (companyPhone == null) {
			if (other.companyPhone != null)
				return false;
		} else if (!companyPhone.equals(other.companyPhone))
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
		return "Company [id=" + id + ", companyName=" + companyName
				+ ", companyPhone=" + companyPhone + ", address=" + address
				+ "]";
	}

}
