package com.ridelog.app.dao.repositories;


import com.ridelog.app.dao.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name ILIKE %:name%")
    Page<User> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.id IN :userIds")
    List<User> findAllByIdIn(@Param("userIds") List<Long> userIds);

    @Query("SELECT COUNT(v) FROM Review v WHERE v.userId = :userId")
    Long countRatingsByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(c) FROM Review c WHERE c.userId = :userId")
    Long countCommentsByUserId(@Param("userId") Long userId);
}