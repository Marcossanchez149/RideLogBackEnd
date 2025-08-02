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

    public User() {
    }
    public User(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User(Long id, String name, String email, String passwordHash, Set<Long> friendIds, Set<Long> visitedParkIds, Set<Long> wishlistParkIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.friendIds = friendIds;
        this.visitedParkIds = visitedParkIds;
        this.wishlistParkIds = wishlistParkIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(Set<Long> friendIds) {
        this.friendIds = friendIds;
    }

    public Set<Long> getVisitedParkIds() {
        return visitedParkIds;
    }

    public void setVisitedParkIds(Set<Long> visitedParkIds) {
        this.visitedParkIds = visitedParkIds;
    }

    public Set<Long> getWishlistParkIds() {
        return wishlistParkIds;
    }

    public void setWishlistParkIds(Set<Long> wishlistParkIds) {
        this.wishlistParkIds = wishlistParkIds;
    }
}
