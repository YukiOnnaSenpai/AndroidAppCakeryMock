package com.example.joydivision.menu;

public class ProductType {

    private int id;
    private String name;
    private String image;


    public ProductType(){

    }

    public ProductType(String name){

        this.name = name;
    }

    public ProductType(String name, String image){

        this.image = image;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String url) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
