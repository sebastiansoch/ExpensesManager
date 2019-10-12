package com.gmail.sebastiansoch.expensemanager.database.model;

public class PurchaseDTO {
    private int id;
    private int categoryId;
    private String date;
    private Double price;

    public PurchaseDTO(int id, int categoryId, String date, Double price) {
        this.id = id;
        this.categoryId = categoryId;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDate() {
        return date;
    }

    public Double getPrice() {
        return price;
    }
}
