package com.example.siteautobarcode.POJO;

import java.util.Arrays;
import java.util.List;

public class Product implements Comparable<Product> {
    private int id;
    private int price;
    private String nameOfProduct;
    private List<String> data;
    private int quantity;
    private String description;

    public Product(int id, int price, String nameOfProduct, String[] data, int quantity, String description)
    {
        this.id = id;
        this.price = price;
        this.nameOfProduct = nameOfProduct;
        this.data = Arrays.asList(data);
        this.quantity = quantity;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<String> getData() {
        return data;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Product o) {
        int compareQuantity = ((Product) o).getId();

        //ascending order
        return this.id - compareQuantity;

        //descending order
        //return compareQuantity - this.id;
    }
}
