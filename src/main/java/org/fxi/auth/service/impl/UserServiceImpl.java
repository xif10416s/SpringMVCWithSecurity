package org.fxi.auth.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.fxi.auth.entity.User;
import org.fxi.auth.entity.UserAuthority;
import org.fxi.auth.repo.UserAuthorityRepository;
import org.fxi.auth.repo.UserRepository;
import org.fxi.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserAuthorityRepository userAuthRepo;

    @Override
    public User getUserById(String userId) {
        return userRepo.findOne(userId);
    }

    @Override
	public User getUserByEmail(String email) {
		User user = userRepo.findByEmail(email);
		if (user == null) return null;
		List<UserAuthority> uas = userAuthRepo.findByUserId(user.getUserId());
		List<String> roles = new LinkedList<>();
		for (UserAuthority ua : uas) {
			roles.add(ua.getAuthority());
		}
		user.setAuthorities(roles);
		return user;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User saveUser(User user) {
		List<UserAuthority> oldAuthorities = userAuthRepo.findByUserId(user.getUserId());
		if (oldAuthorities != null) {
			for (UserAuthority ua : oldAuthorities) {
				userAuthRepo.delete(ua);
			}
		}
		List<String> authorities = user.getAuthorities();
		for (String auth : authorities) {
			UserAuthority newRole = new UserAuthority();
			newRole.setUserId(user.getUserId());
			newRole.setAuthority(auth);
			userAuthRepo.save(newRole);
		}
		userRepo.save(user);

		userRepo.flush();
		userAuthRepo.flush();
		return user;
	}

	@Override
	public void delUser(User user) {
		if (user == null) return;
		List<UserAuthority> oldAuths = userAuthRepo.findByUserId(user.getUserId());
		userRepo.delete(user);
		userAuthRepo.delete(oldAuths);
	}

	@Override
	public UserAuthority addAuth(User user, String auth) {
		UserAuthority newAuth = null;
		for (UserAuthority a : userAuthRepo.findByUserId(user.getUserId())) {
			if (a.getAuthority().equals(auth)) {
				newAuth = a;
			}
		}
		if (newAuth == null) {
			newAuth = new UserAuthority();
			newAuth.setUserId(user.getUserId());
			newAuth.setAuthority(auth);
			newAuth = userAuthRepo.saveAndFlush(newAuth);
			user.getAuthorities().add(newAuth.getAuthority());
		}
		return newAuth;
	}

	@Override
	public void delAuth(User user, String auth) {
		for (UserAuthority a : userAuthRepo.findByUserId(user.getUserId())) {
			if (a.getAuthority().equals(auth)) {
				userAuthRepo.delete(a);
				return;
			}
		}
	}
}
