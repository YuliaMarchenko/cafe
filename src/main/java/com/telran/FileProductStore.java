package com.telran;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileProductStore implements ProductStore{

    public static final String PRODUCTS_PATH = "cafe/products.cafe";

    @Override
    public boolean add(Product product) {
        String path = "cafe/products/" + product.getCategory();
        String fileName = product.getName() + ".cafe";

        String pathname = path + "/" + fileName;
        File productFile = new File(pathname);
        if (productFile.exists()){
           return false;
        }

        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdirs();
        }

        try(FileWriter fileWriter = new FileWriter(pathname);
            BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(product.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try(FileWriter fileWriter = new FileWriter(PRODUCTS_PATH, true);
            BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(String.format("%s^%s^%b\n", product.getName(), pathname, product.isAvailable()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<String> list() {
        List<String> list = readByLine(PRODUCTS_PATH);
        return list.stream().map(line -> getProductName(line)).collect(Collectors.toList());
    }

    private String getProductName(String line) {
        return line.split("\\^")[0];
    }

    @Override
    public List<String> listAvailable() {
        List<String> listAvailable = readByLine(PRODUCTS_PATH);
        return listAvailable.stream().filter(line -> line.endsWith("true")).map(line -> getProductName(line)).collect(Collectors.toList());
    }

    private static List<String> readByLine(String path){

        String line;
        List<String> result = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        }catch (IOException ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }

        return result;
    }
}
