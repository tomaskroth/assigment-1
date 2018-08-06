package com.waes.differ.api.exception;

public class DiffCalculationException extends RuntimeException {

    private String id;

    public DiffCalculationException(String message, String id) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
