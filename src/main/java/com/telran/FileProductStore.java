package com.telran;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileProductStore implements ProductStore{
    @Override
    public boolean add(Product product) {
        String path = "products/" + product.getCategory();
        String fileName = product.getName() + ".cafe";

        File productFile = new File(path + "/" + fileName);
        if (productFile.exists()){
           return false;
        }

        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdirs();
        }

        try(FileWriter fileWriter = new FileWriter(path + "/" + fileName);
            BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(product.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
