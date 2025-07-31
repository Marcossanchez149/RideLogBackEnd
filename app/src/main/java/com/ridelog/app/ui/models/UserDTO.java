package com.ridelog.app.ui.models;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Set<Long> friendIds;
    private Set<Long> visitedParkIds;
    private Set<Long> wishlistParkIds;

}
