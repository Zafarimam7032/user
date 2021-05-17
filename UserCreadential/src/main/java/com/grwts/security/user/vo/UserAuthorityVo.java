package com.grwts.security.user.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserAuthority_Z")
public class UserAuthorityVo {
	@Id
	private int id;
	private String authrity;
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

	public String getAuthrity() {
		return authrity;
	}

	public void setAuthrity(String authrity) {
		this.authrity = authrity;
	}

	public UserAuthorityVo(int id, String authrity) {
		super();
		this.id = id;
		this.authrity = authrity;
	}

	public UserAuthorityVo() {
		super();
	}

	@Override
	public String toString() {
		return "UserAuthorityVo [id=" + id + ", authrity=" + authrity + "]";
	}

}
