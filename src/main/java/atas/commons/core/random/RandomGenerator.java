package atas.commons.core.random;

import java.util.Random;

import atas.commons.core.index.Index;

/**
 * Represents a psuedo-random number generator.
 *
 * Makes use of the {@code Random} Java class.
 */
public class RandomGenerator {

    private static final int SEED = 1; // arbitrary number chosen for some consistency

    private final Random rng;

    private RandomGenerator(int seed) {
        rng = new Random(seed);
    }

    /**
     * Returns a new {@code RandomGenerator} with arbitrary seed 1.
     */
    public static RandomGenerator makeRandomGenerator() {
        return new RandomGenerator(SEED);
    }

    /**
     * Returns the zero-based index of the next randomly-selected student.
     */
    public Index getNextIndex(int max) {
        return Index.fromZeroBased(rng.nextInt(max));
    }

}
