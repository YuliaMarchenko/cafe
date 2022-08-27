package com.telran;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileProductStore implements ProductStore {
    public static final String PRODUCTS_PATH = "cafe/products.cafe";
    public static final String PATH_PREFIX = "cafe/products/";
    public static final String FILE_EXTENSION = ".cafe";

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
    public Product getProductInfoByPath(String pathname) throws IOException, ClassNotFoundException {
        File directory = new File(pathname);
        if (!directory.exists()) {
            throw new FileNotFoundException();
        }
        return deserialize(pathname);
    }

    private static Product deserialize(String pathname) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(pathname);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Product) ois.readObject();
        }
    }

    @Override
    public Product getProductInfoByLookup(String category, String name) throws IOException, ClassNotFoundException {
        String path = String.format("%s/%s/%s%s", PATH_PREFIX, category, name, FILE_EXTENSION);
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
        String path = String.format("%s/%s/%s%s", PATH_PREFIX, category, name, FILE_EXTENSION);
        return deleteByPath(path);
    }
}
