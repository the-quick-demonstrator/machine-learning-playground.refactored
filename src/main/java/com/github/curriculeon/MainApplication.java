package com.github.curriculeon;

import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        final String resourcesDirectory = System.getProperty("user.dir") + "/src/main/resources";
        final String filePath = resourcesDirectory + "/names.txt";
        System.out.println(prepareTrainingData(filePath));
    }

    /**
     * Create our X-input and Y-output vectors for Training;
     *
     * @param file_path the file to read data from;
     * @return a Pair consisting of X,Y values;
     */
    public static List<InputSequence> prepareTrainingData(String file_path) {
        final MultiLayerPerceptionExample example = new MultiLayerPerceptionExample(file_path, 3);
        return example.getTrainingData();
    }
}
