package com.gmail.sebastiansoch.expensemanager.database.model;

public class PurchaseDTO {
    private int id;
    private int categoryGroupId;
    private int categoryId;
    private String purchaseDate;
    private String entryDate;
    private Double price;

    public PurchaseDTO(int id, int categoryGroupId, int categoryId, String purchaseDate, String entryDate, Double price) {
        this.id = id;
        this.categoryGroupId = categoryGroupId;
        this.categoryId = categoryId;
        this.purchaseDate = purchaseDate;
        this.entryDate = entryDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getCategoryGroupId() {
        return categoryGroupId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public Double getPrice() {
        return price;
    }
}
