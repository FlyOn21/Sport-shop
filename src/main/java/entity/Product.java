package entity;

import config.Config;

public class Product implements interfaces.IProduct {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String description;
    private String brand;
    private String category;
    private String subCategory;
    private String color;
    private boolean available;
    private double discount;

    public Product() {
    }

    public Product(String id, String name, double price,String color, int quantity, String description, String brand, String category, String subCategory, double discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.quantity = quantity;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.subCategory = subCategory;
        this.discount = discount;
        setAvailable();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setAvailable();
    }
    public double getPrice() {
        return this.price;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public void setAvailable() {
        this.available = this.quantity != 0;
    }

    public boolean isAvailable() {
        return available;
    }

    public String toString() {
        return String.format(
                "========================================%n"+
                "%-15s %s%n" +
                "%-15s %s%n" +
                "%-15s %s%n" +
                "%-15s %s%n" +
                "%-15s %s%n" +
                "%-15s %s%n" +
                "%-15s %s%n" +
                "%-15s %s%n" +
                "%-15s %s%n" +
                "%-15s %s%n",
                "ProductId:", this.id,
                "Name:", this.name,
                "Price:", this.price,
                "Discount:", this.discount,
                "Quantity:", this.quantity,
                "Description:", this.description,
                "Brand:", this.brand,
                "Category:", this.category,
                "Sub-Category:", this.subCategory,
                "Currency:", Config.CURRENCY
        );
    }
}
