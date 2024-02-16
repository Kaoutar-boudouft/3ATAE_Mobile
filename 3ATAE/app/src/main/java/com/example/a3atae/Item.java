package com.example.a3atae;

public class Item {
    String IdItem,Title,Description,AnnDate,City,Image;

    public Item(String idItem, String title, String description, String annDate, String city, String image) {
        IdItem = idItem;
        Title = title;
        Description = description;
        AnnDate = annDate;
        City = city;
        Image = image;
    }

    public String getIdItem() {
        return IdItem;
    }

    public void setIdItem(String idItem) {
        IdItem = idItem;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAnnDate() {
        return AnnDate;
    }

    public void setAnnDate(String annDate) {
        AnnDate = annDate;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
