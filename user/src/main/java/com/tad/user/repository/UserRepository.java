package com.tad.user.repository;

import com.tad.user.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    void deleteUserByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.totalAttempt = u.totalAttempt + 1 WHERE u.uuid = :uuid")
    void incrementCounterByEmail(@Param("uuid") String uuid);

    Optional<User> findByUuid(String uuid);

    void deleteByUuid(String uuid);
}
