package com.bus.entity;

public class Category {
    private Integer categoryID;
    private String category_name;
    private String category_desc;

    public Category() {
    }

    public Category(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Category(Integer categoryID, String category_name) {
        this.categoryID = categoryID;
        this.category_name = category_name;
    }

    public Category(String category_name, String category_desc) {
        this.category_name = category_name;
        this.category_desc = category_desc;
    }

    public Category(Integer categoryID, String category_name, String category_desc) {
        this.categoryID = categoryID;
        this.category_name = category_name;
        this.category_desc = category_desc;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_desc() {
        return category_desc;
    }

    public void setCategory_desc(String category_desc) {
        this.category_desc = category_desc;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", category_name='" + category_name + '\'' +
                ", category_desc='" + category_desc + '\'' +
                '}';
    }
}
