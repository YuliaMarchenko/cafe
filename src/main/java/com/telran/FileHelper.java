package com.telran;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    public static List<String> readByLine(String path) {
        String line;
        List<String> result = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }

        return result;
    }
}
