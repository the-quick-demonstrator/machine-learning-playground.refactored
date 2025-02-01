package com.github.curriculeon;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Represents a single training example with a context window (X) and target (Y).
 */
public class InputSequence {
    private final ContextWindow contextWindow;

    public InputSequence(ContextWindow contextWindow) {
        this.contextWindow = contextWindow;
    }

    /**
     * @return The input context as an ND4J tensor.
     */
    public INDArray getContextVector() {
        return contextWindow.toINDArray();
    }

    /**
     * @return The target character ID.
     */
    public int getTargetId() {
        return contextWindow.getTargetId();
    }
}
