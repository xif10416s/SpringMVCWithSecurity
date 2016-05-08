package org.fxi.auth.repo;

import org.fxi.auth.entity.PersistentLogin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersistentLoginRepository extends JpaRepository<PersistentLogin, String> {

}
