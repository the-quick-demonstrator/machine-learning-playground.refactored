package com.github.curriculeon;


import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;

import java.util.*;

public class MultiLayerPerceptionExample {
    private final INDArray tensorX;
    private final INDArray tensorY;
    private final Map<String, Integer> charToIntegerAlphabet;
    private final Map<Integer, String> integerToCharAlphabet;
    private final String filePath;
    private final Integer blockSize;

    MultiLayerPerceptionExample(final String file_path, final Integer blockSize) {
        this.blockSize = blockSize;
        this.filePath = file_path;
        this.tensorX = Nd4j.zeros(1, blockSize);
        this.tensorY = Nd4j.zeros(1, 1);
        this.charToIntegerAlphabet = CharacterToIntegerAlphabet.INSTANCE.getCharToIntegerAlphabet();
        this.integerToCharAlphabet = CharacterToIntegerAlphabet.INSTANCE.getIntegerToCharAlphabet();
    }
    /**
     * Create our X-input and Y-output vectors for Training;
     *
     * @return a Pair consisting of X,Y values;
     */
    INDArray[] prepareTrainingData() {
        final FileHandler fh = new FileHandler(filePath);
        // create map of idx to alphanumeric char

        fh.readWordsFromFile().forEach(word -> {
            System.out.println("=============");
            System.out.printf("Word: %s\n", word);

            // add array of len(context) to X for each letter in "word", where: len(context) == blockSize
            final String[] letters = word.toString().split("");
            for (int i = 0; i < letters.length; i++) {
                final String letter = letters[i];
                final Integer idx = charToIntegerAlphabet.get(letter);
                final INDArray currentContext = Nd4j.zeros(1, this.blockSize); // [0,0,0]
                final INDArray previousContext = Nd4j.zeros(1, this.blockSize); // [0,0,0]
                final INDArray newContext = Nd4j.zeros(1, this.blockSize); // always be from [0:] + idx
                final INDArray slice = tensorX.get(NDArrayIndex.interval(0, blockSize));
                try (INDArray idxVector = Nd4j.create(Collections.singletonList(slice), 1)) {}
                final INDArray hstack = Nd4j.hstack(tensorX, slice);

                //                // 4. arr[1,3]=10 - Assigns array element on index [1][3] the value 10
//                fourByFiveRandomZeroToOne.putScalar(new int[]{1, 3}, 10)


                tensorX.putScalar(1,2,5);
                tensorX.add(currentContext);
                tensorX.addi(previousContext);
                tensorX.addi(newContext);

                System.out.println(tensorX);

                System.out.printf("Curr Y: %d, %s\n", idx, letter);
                // we want to stack [[0,0,0],[0,0,5],[0,5,13]...[13,1,0]]
                // all context up to position context[blockSize], because blockSize is the amount of context we are considering;

                // append newContextVector to currContextVector; Nd4j.hstack(col1,col2);
                // get "moving array" / slice, aka n-most recent;
                // add new context slice to context vector; //TODO - correct this math;
                // append updates to each matrix in same order (x1,y1) + (x2,y2) ->
                // Ex: a_1x+=aI_x; b1x+=bI_x; Or, Nd4j.vstack(row1,row2)

                // if context0 = [0,0,0], then context1 = [context[1:],'e'], -> contextN = [context[1:],letter]
                //                tensorX.addi(context.get(NDArrayIndex.all(), NDArrayIndex.interval(1, blockSize - 1))); // shift upperbound index;
                //                System.out.printf("Context: %s\n", context);
                //
                //                INDArray newOutputVector = tensorY.addi(idx);
                //                tensorY.addiColumnVector(newOutputVector);
            }
            System.exit(1);
        });
        return null;
    }
}
