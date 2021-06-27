package com.qa.main.pojoClass;


import java.util.ArrayList;
import java.util.List;


public class Pet {

    protected String id;
    protected Category category;
    protected String name;
    protected List<String> photoUrls = new ArrayList();
    protected List<Tag> tags = new ArrayList();
    protected Status status;
    protected Pet() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "pet_id : '" + id + '\'' +
                ", category_id : " + category.getId() +
                ", category_name : " + category.getName() +
                ", pet_name : " + name +
                ", pet_photoUrls :" + photoUrls +
                ", array of tags :" + tags +
                ", status :" + status;
    }



}
