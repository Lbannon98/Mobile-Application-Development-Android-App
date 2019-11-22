package com.example.mealplanner;

public class FavouritesItem {
    private int image;
    private String name;

    public FavouritesItem(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
