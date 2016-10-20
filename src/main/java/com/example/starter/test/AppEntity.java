package com.example.starter.test;

import javax.persistence.*;

/**
 * Created by kevin on 10/18/16.
 */
@Entity
@Table(name = "app", schema = "public", catalog = "project")
public class AppEntity {
    private long id;
    private String description;
    private String image;
    private String name;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppEntity appEntity = (AppEntity) o;

        if (id != appEntity.id) return false;
        if (description != null ? !description.equals(appEntity.description) : appEntity.description != null)
            return false;
        if (image != null ? !image.equals(appEntity.image) : appEntity.image != null) return false;
        if (name != null ? !name.equals(appEntity.name) : appEntity.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
