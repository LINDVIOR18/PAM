package com.telemedicine.activities;

public class Doctor {
    public String FullName;
    public int DocId;
    public String Specs;
    public String Address;
    public String About;
    public double Stars;
    public String Photo;
    public String StarsIcon;
    public String locationIcon;

    public Doctor() {
    }

    public Doctor(String FullName, int DocId, String Specs, String Address, String About, double Stars, String Photo, String StarsIcon, String locationIcon) {

        this.FullName = FullName;
        this.DocId = DocId;
        this.Specs = Specs;
        this.Address = Address;
        this.About = About;
        this.Stars = Stars;
        this.Photo = Photo;
        this.StarsIcon = StarsIcon;
        this.locationIcon = locationIcon;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getStarsIcon() {
        return StarsIcon;
    }

    public void setStarsIcon(String StarsIcon) {
        this.StarsIcon = StarsIcon;
    }

    public String getLocationIcon() {
        return locationIcon;
    }

    public void setLocationIcon(String locationIcon) {
        this.locationIcon = locationIcon;
    }

    public int getDocId() {
        return DocId;
    }

    public void setDocId(int DocId) {
        this.DocId = DocId;
    }

    public String getSpecs() {
        return Specs;
    }

    public void setDocSpecs(String Specs) {
        this.Specs = Specs;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getAbout() {
        return About;
    }

    public void setABout(String about) {
        this.About = about;
    }

    public double getStars() {
        return Stars;
    }

    public void setDocStars(double Stars) {
        this.Stars = Stars;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

}
