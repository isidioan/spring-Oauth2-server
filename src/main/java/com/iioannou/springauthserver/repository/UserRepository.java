package com.iioannou.springauthserver.repository;

import com.iioannou.springauthserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ioannou
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
