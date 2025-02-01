package com.github.curriculeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InputSequenceFactory {
    private final ContextWindow contextWindow;
    private final Map<String, Integer> charToIntegerAlphabet;

    public InputSequenceFactory(final int blockSize) {
        this(new ContextWindow(blockSize));
    }

    public InputSequenceFactory(final ContextWindow contextWindow) {
        this.contextWindow = contextWindow;
        this.charToIntegerAlphabet = CharacterToIntegerAlphabet.INSTANCE.getCharToIntegerAlphabet();
    }

    public List<InputSequence> getInputSequenceList(final String[] tokens) {
        final List<InputSequence> trainingData = new ArrayList<>();
        for (int i = 0; i < tokens.length - 1; i++) {
            final String currentLetter = tokens[i];
            final String nextLetter = tokens[i + 1];

            final Integer currentId = charToIntegerAlphabet.getOrDefault(currentLetter, -1);
            final Integer nextId = charToIntegerAlphabet.getOrDefault(nextLetter, -1);

            if (currentId == -1 || nextId == -1) {
                continue; // Skip unknown characters
            }

            // Update the context window and set the target character
            contextWindow.update(currentId);
            contextWindow.setTarget(nextId);

            // Create an InputSequence and add it to the list
            trainingData.add(new InputSequence(contextWindow));

            System.out.println("X (context): " + contextWindow.toINDArray());
            System.out.printf("Y (prediction target): %d (%s)\n", nextId, nextLetter);
        }
        return trainingData;
    }
}
