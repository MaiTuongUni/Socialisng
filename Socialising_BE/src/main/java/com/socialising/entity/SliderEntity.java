package com.socialising.entity;

import javax.persistence.*;

//@Entity
//@Table(name = "slider")
public class SliderEntity extends BaseEntity {

    private static final long serialVersionUID = -4988455421375043688L;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "link")
    private String link;

    @Column(name = "sequence")
    private int sequence;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
