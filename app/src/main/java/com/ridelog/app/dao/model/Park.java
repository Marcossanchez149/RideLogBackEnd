package com.ridelog.app.dao.model;

import java.util.List;

public class Park {
    private Integer id;
    private String name;
    private String location;
    private String descripcion;
    private List<Attraction> attractions;
    private List<Show> shows;
    private List<String> images;
    private List<Review> reviews;

}