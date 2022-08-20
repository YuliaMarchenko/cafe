package com.telran;

public class Product {
    String name;
    int price;
    String description;
    String category;
    boolean isAvailable;

    public Product(String name, int price, String description, String category, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return String.format("%s^%d^%s^%s^%b", name, price, description, category, isAvailable);
    }

    public static Product fromString(String productString){
        String[] elements = productString.split("\\^");
        if (elements.length != 5){
            throw new IllegalArgumentException("You should provide all fields of products: name, price, description, category, isAvailable.");
        }
        return new Product(elements[0], Integer.parseInt(elements[1]), elements[2], elements[3], Boolean.parseBoolean(elements[4]));
    }
}
