package com.github.curriculeon;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum CharacterToIntegerAlphabet {
    INSTANCE;

    private final Map<String, Integer> charToIntegerAlphabet;
    private final Map<Integer, String> integerToCharAlphabet;

    CharacterToIntegerAlphabet() {
        this.charToIntegerAlphabet = IntStream
                .rangeClosed('a', 'z')
                .boxed() //.mapToObj(c -> c), difference?
                .collect(Collectors.toMap(
                        c -> String.valueOf((char) c.intValue()), c -> c - 'a' + 1) // 'a' is ASCII value, subtract from c, convert to int, add 1.
                );
        this.charToIntegerAlphabet.put(".", 0);
        this.integerToCharAlphabet = charToIntegerAlphabet
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey)
                );
    }

    public Map<String, Integer> getCharToIntegerAlphabet() {
        return charToIntegerAlphabet;
    }

    public Map<Integer, String> getIntegerToCharAlphabet() {
        return integerToCharAlphabet;
    }
}
