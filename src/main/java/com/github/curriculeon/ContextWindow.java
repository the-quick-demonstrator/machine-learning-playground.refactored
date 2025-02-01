package com.github.curriculeon;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Encapsulates a rolling context window and its expected output.
 */
public class ContextWindow {
    private final int blockSize;
    private final Queue<Integer> windowQueue;
    private int targetId;

    public ContextWindow(int blockSize) {
        this.blockSize = blockSize;
        this.windowQueue = new LinkedList<>();
        initializeWindow();
    }

    /**
     * Initializes the context window with zeros.
     */
    private void initializeWindow() {
        for (int i = 0; i < blockSize; i++) {
            windowQueue.add(0);
        }
        this.targetId = -1; // Default invalid target
    }

    /**
     * Updates the context window with a new character ID.
     *
     * @param charId The integer representation of the character.
     */
    public void update(int charId) {
        if (windowQueue.size() >= blockSize) {
            windowQueue.poll(); // Remove oldest element
        }
        windowQueue.add(charId);
    }

    /**
     * Sets the target character ID.
     */
    public void setTarget(int targetId) {
        this.targetId = targetId;
    }

    /**
     * Converts the context window into an ND4J tensor.
     *
     * @return INDArray representing the context window.
     */
    public INDArray toINDArray() {
        INDArray contextVector = Nd4j.zeros(1, blockSize);
        int index = 0;
        for (int value : windowQueue) {
            contextVector.putScalar(0, index++, value);
        }
        return contextVector;
    }

    /**
     * Returns the target ID (Y value).
     */
    public int getTargetId() {
        return targetId;
    }

    /**
     * Clears and resets the context window.
     */
    public void reset() {
        windowQueue.clear();
        initializeWindow();
    }
}
