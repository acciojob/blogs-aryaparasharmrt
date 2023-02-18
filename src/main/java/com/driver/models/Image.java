package com.driver.models;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String discription;
    private String dimension;

    @ManyToOne
    @JoinColumn
    private Blog blog;
    public Image() {
    }

    public Image(int id, String discription, String dimension, Blog blog) {
        Id = id;
        this.discription = discription;
        this.dimension = dimension;
        this.blog = blog;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
}