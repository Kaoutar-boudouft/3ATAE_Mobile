package com.example.a3atae;

public class Annonce {
    String idannonce,title,description,images,anndate,annonceurid,city;

    public Annonce(String idannonce, String title, String description, String images, String anndate,String annonceurid,String city) {
        this.idannonce = idannonce;
        this.title = title;
        this.description = description;
        this.images = images;
        this.anndate = anndate;
        this.annonceurid=annonceurid;
        this.city=city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAnnonceurid() {
        return annonceurid;
    }

    public void setAnnonceurid(String annonceurid) {
        this.annonceurid = annonceurid;
    }

    public String getIdannonce() {
        return idannonce;
    }

    public void setIdannonce(String idannonce) {
        this.idannonce = idannonce;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAnndate() {
        return anndate;
    }

    public void setAnndate(String anndate) {
        this.anndate = anndate;
    }
}
