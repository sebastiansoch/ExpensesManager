package com.gmail.sebastiansoch.expensemanager.database.model;

public class Purchase {
    private String category;
    private String date;
    private Double price;

    public Purchase(String category, String date, Double price) {
        this.category = category;
        this.date = date;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
