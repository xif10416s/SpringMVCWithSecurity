package org.fxi.auth.entity;

import java.util.List;

import javax.persistence.*;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

@Entity
@Table(name="users")
public class User {
	
	private final static Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	
	@Id
	private String userId;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String email;

	@Column
	private String name;

	@Column
	private String phone;

	@Transient
	private List<String> authorities;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean validatePassword(String rawPassword) {
		return this.password.equals(encoder.encodePassword(rawPassword, null));
	}
	
	public String getRawPassword() {
		return this.password;
	}
	
	public void setRawPassword(String md5Password) {
		this.password = md5Password;
	}

	public void setPassword(String password) {
		this.password = encoder.encodePassword(password, null);
	}

	public List<String> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
