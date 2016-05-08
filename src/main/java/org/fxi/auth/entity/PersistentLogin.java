package org.fxi.auth.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="persistent_logins")
public class PersistentLogin {

	@Column(nullable=false)
	private String username;
	
	@Id @Column
	private String series;
	
	@Column(nullable=false)
	private String token;
	
	@Column(name="last_used", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUsed;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	public static void main(String[] args){
		new PersistentLogin();
	}
}
