package com.github.curriculeon;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    private final String filePath;

    public FileHandler(final String filePath) {
        this.filePath = filePath;
    }

    public List<String> readWordsFromFile() {
        List<String> allWordsInFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allWordsInFile.addAll(Arrays.asList(line.split("\\s+")));
            }
        } catch (Exception e) {
            throw new Error(e);
        }
        return allWordsInFile;
    }
}
