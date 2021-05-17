package com.grwts.security.user.vo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UserRole {
	private  int id;
	private String role;
	@ManyToOne
	private UserVo userVo;
	
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserRole(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	public UserRole() {
		super();
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", role=" + role + "]";
	}
	

}
