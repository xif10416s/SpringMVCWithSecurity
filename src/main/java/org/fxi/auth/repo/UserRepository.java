package org.fxi.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;


public interface UserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);
}
