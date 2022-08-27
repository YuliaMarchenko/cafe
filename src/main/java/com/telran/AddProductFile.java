package com.telran;

import java.io.*;

public class AddProductFile {
    Product product;

    public AddProductFile(Product product) {
        this.product = product;
    }

    public boolean add() {
        String path = ProductFileHelper.getProductsPath(product);
        String pathname = ProductFileHelper.getPathName(product);
        if (checkProductExists(pathname)) return false;
        ensureDirectoryExists(path);
        return writeProduct(product, pathname);
    }


    private boolean writeProduct(Product product, String pathname) {
        try (FileOutputStream fos = new FileOutputStream(pathname);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(product);
            oos.flush();
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
