package com.example.mealplanner;

public class Item {
    public String image;
    public String name;
    public String link;

    public Item(String image, String name, String link) {
        this.image = image;
        this.name = name;
        this.link = link;
    }

    public Item() {}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
