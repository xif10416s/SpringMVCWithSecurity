package org.fxi.auth.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_authorities")
public class UserAuthority {

	public final static String ROLE_ADMIN = "ROLE_ADMIN";
	public final static String ROLE_USER = "ROLE_USER";

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false)
	private String authority;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
