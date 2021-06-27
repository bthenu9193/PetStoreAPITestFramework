package com.qa.main.pojoClass;

import java.util.ArrayList;
import java.util.List;

public class PetBuilder {
    private String id;
    private Category category;
    private String name;
    private List<String> photoUrls = new ArrayList();
    private List<Tag> tags = new ArrayList();
    private Status status;

    public PetBuilder() {

    }

    public PetBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public PetBuilder inCategory(Category category) {
        this.category = category;
        return this;
    }

    public PetBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder withPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public PetBuilder withTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public PetBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public Pet build() {
        Pet pet = new Pet();
        pet.id = this.id;
        pet.name = this.name;
        pet.category = this.category;
        pet.photoUrls = this.photoUrls;
        pet.tags = this.tags;
        pet.status = this.status;
        return pet;
    }

}
