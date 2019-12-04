package com.example.mealplanner;

public class Item {
    private int image;
    private String name;
    private String link;

    public Item(int image, String name, String link) {
        this.image = image;
        this.name = name;
        this.link = link;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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
