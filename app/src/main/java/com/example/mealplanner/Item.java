package com.example.mealplanner;

/*
    This class contains all the variables that will store all necessary information for the
    meals on the Home screen.
    Item takes in the parameters of image (String as it is a url), name, link
*/

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
