package com.ridelog.app.ui.models;

import java.util.List;

public class ParkDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private List<String> imageUrls;
    private Integer atraccionesCount;
    private Double averageRating;

    public ParkDTO() {
    }

    public ParkDTO(Long id, String name, String location, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public ParkDTO(Long id, String name, String location, String description, List<String> imageUrls, Integer atraccionesCount, Double averageRating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.imageUrls = imageUrls;
        this.atraccionesCount = atraccionesCount;
        this.averageRating = averageRating;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Integer getAtraccionesCount() {
        return atraccionesCount;
    }

    public void setAtraccionesCount(Integer atraccionesCount) {
        this.atraccionesCount = atraccionesCount;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
