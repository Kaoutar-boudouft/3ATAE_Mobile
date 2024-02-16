package com.example.a3atae;

import java.util.List;

public class DataModel {

    private List<SubCategory> nestedList;
    private String itemText;
    private String image;
    private boolean isExpandable;

    public DataModel(List<SubCategory> itemList, String itemText,String image) {
        this.nestedList = itemList;
        this.itemText = itemText;
        this.image=image;
        isExpandable = false;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public List<SubCategory> getNestedList() {
        return nestedList;
    }

    public String getItemText() {
        return itemText;
    }

    public boolean isExpandable() {
        return isExpandable;
    }
}
