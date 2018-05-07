package com.infosupport.machinelearning.digitclassifier.models;

public class GenericError {
    private final String message;

    public GenericError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
