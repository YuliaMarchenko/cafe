package com.telran;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileProductStore implements ProductStore {
    public static final String PRODUCTS_PATH = "cafe/products.cafe";

    @Override
    public boolean add(Product product) {
        return new AddProductFile(product).add();
    }

    @Override
    public List<String> list() {
        List<String> list = FileHelper.readByLine(PRODUCTS_PATH);
        return list.stream().map(line -> getProductName(line)).collect(Collectors.toList());
    }

    private String getProductName(String line) {
        return line.split("\\^")[0];
    }

    @Override
    public List<String> listAvailable() {
        List<String> listAvailable = FileHelper.readByLine(PRODUCTS_PATH);
        return listAvailable.stream().filter(line -> line.endsWith("true")).map(line -> getProductName(line)).collect(Collectors.toList());
    }

    @Override
    public Product getProductInfoByPath(String pathname) throws FileNotFoundException {
        File directory = new File(pathname);
        if (!directory.exists()) {
            throw new FileNotFoundException();
        }
        return Product.fromString(FileHelper.readByLine(pathname).get(0));
    }

    @Override
    public Product getProductInfoByLookup(String category, String name) throws FileNotFoundException {
        String path = String.format("cafe/products/%s/%s.cafe", category, name);
        return getProductInfoByPath(path);
    }

    @Override
    public boolean deleteByPath(String path) {
        File file = new File(path);
        if (file.delete()) {
            List<String> list = FileHelper.readByLine(PRODUCTS_PATH);
            try (FileWriter fileWriter = new FileWriter(PRODUCTS_PATH);
                 BufferedWriter writer = new BufferedWriter(fileWriter)) {
                for (String each : list) {
                    if (!path.equals(each.split("\\^")[1])) {
                        writer.write(each);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteByLookup(String category, String name) {
        String path = String.format("cafe/products/%s/%s.cafe", category, name);
        return deleteByPath(path);
    }
}
