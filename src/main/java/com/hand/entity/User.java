package com.hand.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity(name="user")
public class User implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="id")
	@Expose
	private int id;

	@Column(name="accountName")
	@Expose
	private String accountName;

	@Column(name="realName")
	@Expose
	private String realName;


	@Column(name="password")
	@Expose
	private String password;

	@Column(name="identity")
	@Expose
	private Integer identity = 1; // 含有两个角色  用户和管理员  默认是普通用户

	@Column(name="deleteflag")
	@Expose
	private Integer deleteFlag = 0; // 删除标志符	  0 代表false 1 代表true-已删除

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
