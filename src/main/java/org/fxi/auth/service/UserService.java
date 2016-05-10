package org.fxi.auth.service;


import org.fxi.auth.entity.User;
import org.fxi.auth.entity.UserAuthority;

public interface UserService {

    User getUserById(String userId);
	
	User getUserByEmail(String email);
	
	User saveUser(User user);
	
	void delUser(User user);

	UserAuthority addAuth(User user, String auth);

	void delAuth(User user, String auth);

}
