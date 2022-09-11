package com.telran;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileProductStore implements ProductStore {
    private Map<String, ProductListItem> productListItems;

    public FileProductStore() throws IOException, ClassNotFoundException {
        File productFile = new File(ProductFileHelper.PRODUCTS_PATH);
        if (productFile.exists()) {
            try (FileInputStream fis = new FileInputStream(ProductFileHelper.PRODUCTS_PATH);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                productListItems = (HashMap<String, ProductListItem>) ois.readObject();
            }
        }else {
            productListItems = new HashMap<>();
        }
    }

    @Override
    public boolean add(Product product) throws IOException {
        boolean result = new AddProductFile(product).add();
        if (result) {
            ProductListItem productListItem = new ProductListItem(product.getName(), ProductFileHelper.getPathName(product), product.isAvailable());
            productListItems.put(productListItem.getPath(), productListItem);
            writeProductsList();
            return true;
        }
        return false;
    }

    private void writeProductsList() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ProductFileHelper.PRODUCTS_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(productListItems);
            oos.flush();
        }
    }

    @Override
    public List<String> list() {
        return productListItems.values().stream().map(item -> item.getName()).collect(Collectors.toList());
    }

    @Override
    public List<String> listAvailable() {
        return productListItems.values().stream().filter(item -> item.isAvailable()).map(item -> item.getName()).collect(Collectors.toList());
    }

    @Override
    public Product getProductInfoByPath(String pathname) throws IOException, ClassNotFoundException {
        File directory = new File(pathname);
        if (!directory.exists()) {
            throw new FileNotFoundException();
        }
        return deserializeProduct(pathname);
    }

    private static Product deserializeProduct(String pathname) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(pathname);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Product) ois.readObject();
        }
    }

    @Override
    public Product getProductInfoByLookup(String category, String name) throws IOException, ClassNotFoundException {
        String path = ProductFileHelper.getPathName(new Product(name, 0, "", category, true));
        return getProductInfoByPath(path);
    }

    @Override
    public boolean deleteByPath(String path) throws IOException {
        File file = new File(path);
        if (file.delete()) {
            productListItems.remove(path);
            writeProductsList();
        }
        return true;
    }

    @Override
    public boolean deleteByLookup(String category, String name) throws IOException {
        String path = ProductFileHelper.getPathName(new Product(name, 0, "", category, true));
        return deleteByPath(path);
    }
}
