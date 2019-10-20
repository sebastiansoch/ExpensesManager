package com.gmail.sebastiansoch.expensemanager.data;

import java.util.Objects;

public class Purchase {
    private String categoryName;
    private String purchaseDate;
    private String price;

    public Purchase(String categoryName, String purchaseDate, String price) {
        this.categoryName = categoryName;
        this.purchaseDate = purchaseDate;
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(categoryName, purchase.categoryName) &&
                Objects.equals(purchaseDate, purchase.purchaseDate) &&
                Objects.equals(price, purchase.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, purchaseDate, price);
    }
}
