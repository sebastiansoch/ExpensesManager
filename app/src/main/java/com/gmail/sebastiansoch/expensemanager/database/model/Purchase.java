package com.gmail.sebastiansoch.expensemanager.database.model;

public class Purchase {
    private int id;
    private int category_id;
    private String date;
    private Double price;

    public Purchase(int id, int category_id, String date, Double price) {
        this.id = id;
        this.category_id = category_id;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getDate() {
        return date;
    }

    public Double getPrice() {
        return price;
    }
}
