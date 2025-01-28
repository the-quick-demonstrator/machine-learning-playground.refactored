package com.github.curriculeon;

import org.nd4j.linalg.api.ndarray.INDArray;

public class MainApplication {
    public static void main(String[] args) {
        final String resourcesDirectory = System.getProperty("user.dir") + "/src/main/resources";
        final String filePath = resourcesDirectory + "/names.txt";
        prepareTrainingData(filePath);
    }

    /**
     * Create our X-input and Y-output vectors for Training;
     *
     * @param file_path the file to read data from;
     * @return a Pair consisting of X,Y values;
     */
    public static INDArray[] prepareTrainingData(String file_path) {
        return new MultiLayerPerceptionExample().prepareTrainingData(file_path);
    }
}
