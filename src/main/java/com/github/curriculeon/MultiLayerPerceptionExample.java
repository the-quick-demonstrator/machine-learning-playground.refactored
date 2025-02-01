package com.github.curriculeon;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import java.util.*;

public class MultiLayerPerceptionExample {
    private List<InputSequence> trainingData;
    private final String filePath;
    private final Integer blockSize;

    public MultiLayerPerceptionExample(final String file_path, final Integer blockSize) {
        this.blockSize = blockSize;
        this.filePath = file_path;
        this.trainingData = new ArrayList<>();
    }

    /**
     * Prepare training data using InputSequence.
     *
     * @return A list of InputSequence objects.
     */
    public List<InputSequence> getTrainingData() {
        if(!trainingData.isEmpty()) {
            final FileHandler fh = new FileHandler(filePath);
            final List<InputSequence> trainingData = new ArrayList<>();
            for (final String word : fh.readWordsFromFile()) {
                trainingData.addAll(new InputSequenceFactory(blockSize).getInputSequenceList(word.split("")));
            }
            return trainingData;
        }
        return trainingData;
    }

    /**
     * Converts the list of InputSequence objects into ND4J tensors for training.
     *
     * @return A pair of tensors (X, Y).
     */
    public INDArray[] getTrainingTensors() {
        final int dataSize = getTrainingData().size();
        final INDArray tensorX = Nd4j.zeros(dataSize, blockSize);
        final INDArray tensorY = Nd4j.zeros(dataSize, 1);

        for (int i = 0; i < dataSize; i++) {
            tensorX.putRow(i, getTrainingData().get(i).getContextVector());
            tensorY.putScalar(i, 0, getTrainingData().get(i).getTargetId());
        }

        return new INDArray[]{tensorX, tensorY};
    }
}
