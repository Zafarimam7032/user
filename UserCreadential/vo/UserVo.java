package com.grwts.security.user.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User_security_Z")
public class UserVo {
	@Id
	private int id;
	private String username;
	private String password;
	@OneToOne
	private UserCredentialControllerVo userCredentialControllerVo;
	@OneToMany(cascade = CascadeType.ALL)
	private List<UserAuthorityVo> authorityVos;
	@OneToMany(cascade = CascadeType.ALL)
	private List<UserRole> userRoles;

	public UserCredentialControllerVo getUserCredentialControllerVo() {
		return userCredentialControllerVo;
	}

	public void setUserCredentialControllerVo(UserCredentialControllerVo userCredentialControllerVo) {
		this.userCredentialControllerVo = userCredentialControllerVo;
	}

	public List<UserAuthorityVo> getAuthorityVos() {
		return authorityVos;
	}

	public void setAuthorityVos(List<UserAuthorityVo> authorityVos) {
		this.authorityVos = authorityVos;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public UserVo(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public UserVo() {
		super();
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", username=" + username + ", password=" + password
				+ ", userCredentialControllerVo=" + userCredentialControllerVo + ", authorityVos=" + authorityVos
				+ ", userRoles=" + userRoles + "]";
	}

	



}
