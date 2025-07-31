package com.ridelog.app.dao.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(nullable = false)
    private String passwordHash;

    @ElementCollection
    @CollectionTable(name = "user_friends", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "friend_id")
    private Set<Long> friendIds = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "user_visited_parks", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "park_id")
    private Set<Long> visitedParkIds = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "user_wishlist_parks", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "park_id")
    private Set<Long> wishlistParkIds = new HashSet<>();
}
