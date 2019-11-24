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

    public String getName() {
        return name;
    }

    public String getLink() { return link; }
}
