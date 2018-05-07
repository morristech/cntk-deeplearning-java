package com.infosupport.machinelearning.digitclassifier.models;

public class ClassificationResult {
    private final int digit;

    public ClassificationResult(int digit) {
        this.digit = digit;
    }

    public int getDigit() {
        return digit;
    }
}
