package com.telran;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddProductFile {
    Product product;

    public AddProductFile(Product product) {
        this.product = product;
    }

    public boolean add() {
        String path = "cafe/products/" + product.getCategory();
        String pathname = path + "/" + product.getName() + ".cafe";
        if (checkProductExists(pathname)) return false;
        ensureDirectoryExists(path);
        if (!writeProduct(product, pathname)) return false;
        return addToProductsList(product, pathname);
    }

    private boolean addToProductsList(Product product, String pathname) {
        try (FileWriter fileWriter = new FileWriter(FileProductStore.PRODUCTS_PATH, true);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(String.format("%s^%s^%b\n", product.getName(), pathname, product.isAvailable()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean writeProduct(Product product, String pathname) {
        try (FileWriter fileWriter = new FileWriter(pathname);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(product.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void ensureDirectoryExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private boolean checkProductExists(String pathname) {
        File productFile = new File(pathname);
        if (productFile.exists()) {
            return true;
        }
        return false;
    }
}
