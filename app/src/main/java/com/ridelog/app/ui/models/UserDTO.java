package com.ridelog.app.ui.models;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Set<Long> friendIds;
    private Set<Long> visitedParkIds;
    private Set<Long> wishlistParkIds;

    public UserDTO() {
    }
    public UserDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDTO(Long id, String name, String email, Set<Long> friendIds, Set<Long> visitedParkIds, Set<Long> wishlistParkIds) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public Set<Long> getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(Set<Long> friendIds) {
        this.friendIds = friendIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
