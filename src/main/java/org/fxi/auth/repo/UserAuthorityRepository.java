package org.fxi.auth.repo;

import java.util.List;

import org.fxi.auth.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Integer> {
	
	List<UserAuthority> findByUserId(String userId);

}
