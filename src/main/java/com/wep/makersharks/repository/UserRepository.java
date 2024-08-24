package com.wep.makersharks.repository;

import com.wep.makersharks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM users u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}
