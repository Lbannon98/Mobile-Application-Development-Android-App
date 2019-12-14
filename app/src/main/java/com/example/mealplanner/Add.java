package com.example.mealplanner;


/*
    This class contains all the variables that will store all necessary information for adding
    a meal on the Add screen
    Add takes in the parameters of name and imageUri
*/


public class Add {

    private String name;
    private String imageUri;

    public Add(){ }

    public Add(String name, String imageUri) {
        if(name.trim().equals("")) {
            name = "No name";
        }

        this.name = name;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String imageUri() {
        return imageUri;
    }

    public void setImageUrl(String imageUri) {
        this.imageUri = imageUri;
    }
}
