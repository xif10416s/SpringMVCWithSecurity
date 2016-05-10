package org.fxi.auth.repo;

import org.fxi.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);
}
