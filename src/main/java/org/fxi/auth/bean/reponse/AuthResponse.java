package org.fxi.auth.bean.reponse;


import org.fxi.common.bean.reponse.Response;

public class AuthResponse extends Response {
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static class User {
		
		private String id;
		private String email;
		private String roles;
		private String name;
		
		public User() {}
		public User(String id, String email, String roles, String name) {
			this.id = id;
			this.email = email;
			this.roles = roles;
			this.name = name;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getRoles() {
			return roles;
		}
		public void setRoles(String roles) {
			this.roles = roles;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
