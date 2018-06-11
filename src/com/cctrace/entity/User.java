package com.cctrace.entity;

import java.io.Serializable;
//操作用户表
public class User implements Serializable {
	private Integer id;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 手机
	private String phone;
	// 角色
	private String role;
	// 公司编号
	private Integer companyId;
	// 上次登录时间
	private String lastLoginTime;
	// 添加用户的操作人
	private String addPerson;
	// 公司名称
	private String companyName;
	

	public User() {
		super();
	}
	
	
	public User(Integer id, String username, String password, String phone,
			String role, Integer companyId, String lastLoginTime,
			String addPerson, String companyName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.companyId = companyId;
		this.lastLoginTime = lastLoginTime;
		this.addPerson = addPerson;
		this.companyName = companyName;
	}

	
	
	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getAddPerson() {
		return addPerson;
	}
	
	
	public void setAddPerson(String addPerson) {
		this.addPerson = addPerson;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addPerson == null) ? 0 : addPerson.hashCode());
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastLoginTime == null) ? 0 : lastLoginTime.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (addPerson == null) {
			if (other.addPerson != null)
				return false;
		} else if (!addPerson.equals(other.addPerson))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastLoginTime == null) {
			if (other.lastLoginTime != null)
				return false;
		} else if (!lastLoginTime.equals(other.lastLoginTime))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", phone=" + phone + ", role=" + role
				+ ", companyId=" + companyId + ", lastLoginTime="
				+ lastLoginTime + ", addPerson=" + addPerson + ", companyName="
				+ companyName + "]";
	}


	
	

}
