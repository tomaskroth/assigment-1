package com.waes.differ.api.model;

import javax.persistence.*;

@Entity
@Table(name = "diffable")
public class Diffable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", unique = true)
    private String description;

    @Column(name = "content_right")
    private String contentRight;

    @Column(name = "content_left")
    private String contentLeft;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentRight() {
        return contentRight;
    }

    public void setContentRight(String contentRight) {
        this.contentRight = contentRight;
    }

    public String getContentLeft() {
        return contentLeft;
    }

    public void setContentLeft(String contentLeft) {
        this.contentLeft = contentLeft;
    }

}
